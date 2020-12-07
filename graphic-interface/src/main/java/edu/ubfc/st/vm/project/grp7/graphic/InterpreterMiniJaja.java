package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterListener;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaLexer;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaListenerImpl;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaParser;
import javafx.scene.control.TextArea;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fxmisc.richtext.CodeArea;
import sun.rmi.runtime.NewThreadAction;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InterpreterMiniJaja implements MJJInterpreterListener{
    ClasseNode classeNode;
    Waiter waiter;
    private Thread interpreter;
    private Executor threadWrite = Executors.newSingleThreadExecutor();
    TextArea run;
    TextArea error;
    TextArea debug;
    List<Integer> breakpoints;
    private Memory memory;

    InterpreterMiniJaja(TextArea run,TextArea error,TextArea debug){
        this.run = run;
        this.error = error;
        this.debug = debug;
    }

    public void setBreakpoints(List<Integer> breakpoints) {
        this.breakpoints = breakpoints;
    }

    private CharStream codePointCharStream;
    private MiniJajaLexer lexer;
    private MiniJajaParser parser;
    private ParseTreeWalker walker = new ParseTreeWalker();
    private MiniJajaListenerImpl listener;

    public void init(String file) throws IOException {
        codePointCharStream = CharStreams.fromPath(Paths.get(file));
        lexer = new MiniJajaLexer(codePointCharStream);
        parser = new MiniJajaParser(new CommonTokenStream(lexer));
        listener = new MiniJajaListenerImpl();
    }

    public void interpret(){

        walker.walk(listener, parser.classe());
        classeNode = (ClasseNode)listener.getRoot();
    }

    public void run(boolean debug) throws Exception {
        if (debug){
            waiter = new DebugOffWaiter();
        }else{
            waiter = new DebugOnWaiter(interpreter);
        }
        this.threadWrite.execute(() -> {
            run.appendText("\nRun MiniJaja\n\n");
        });

        MiniJajaInterpreter.getFactory().createFrom(memory, classeNode).interpret(new MJJInterpreterController(this));

        this.threadWrite.execute(() -> {
            run.appendText("\n---------------------\n\n");
        });
    }

    @Override
    public void mjjWrite(String str) {
        this.threadWrite.execute(() -> {
            run.appendText(str);
        });
    }

    @Override
    public void mjjWriteLn(String str) {
        this.threadWrite.execute(() -> {
            run.appendText(str);
            run.appendText("\n");
        });
    }

    @Override
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void debug(int line) throws InterruptedException {

    }

    public ClasseNode getClasseNode() {
        return classeNode;
    }
}
