package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mjj.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mjj.interpreter.MJJInterpreterListener;
import edu.ubfc.st.vm.project.grp7.mjj.interpreter.MiniJajaInterpreter;
import edu.ubfc.st.vm.project.grp7.mjj.parser.ASTParsingException;
import edu.ubfc.st.vm.project.grp7.mjj.parser.MiniJajaLexer;
import edu.ubfc.st.vm.project.grp7.mjj.parser.MiniJajaListenerImpl;
import edu.ubfc.st.vm.project.grp7.mjj.parser.MiniJajaParser;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerException;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;

public class InterpreterMiniJajaModel implements MJJInterpreterListener{
    private ClasseNode classeNode;
    private Waiter waiter;
    private Executor threadWrite = Executors.newSingleThreadExecutor();
    private TextArea run;
    private TextArea error;
    private TextArea debug;
    private CodeArea codeArea;
    private List<Integer> breakpoints;
    private Memory memory;
    private BreakPoint breakPoint;
    private TypeChecker typeChecker;
    private String currentFile;
    ExecutorService writerun = Executors.newSingleThreadExecutor();
    ExecutorService writeerror = Executors.newSingleThreadExecutor();

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    InterpreterMiniJajaModel(TextArea run, TextArea error, TextArea debug, CodeArea codeArea){
        this.run = run;
        this.error = error;
        this.debug = debug;
        this.codeArea = codeArea;
        init();
    }

    public void setBreakpoints() {
        this.breakpoints = breakPoint.returnCheckedLine();
    }

    private CharStream codePointCharStream;
    private MiniJajaLexer lexer;
    private MiniJajaParser parser;
    private ParseTreeWalker walker = new ParseTreeWalker();
    private MiniJajaListenerImpl listener;

    public void init(String file)  {
        try {
            codePointCharStream = CharStreams.fromPath(Paths.get(file));
        }catch (IOException e){
            e.printStackTrace();
        }
        lexer = new MiniJajaLexer(codePointCharStream);
        parser = new MiniJajaParser(new CommonTokenStream(lexer));
        listener = new MiniJajaListenerImpl();
    }

    public void build(){
        try {
            walker.walk(listener, parser.classe());
        }catch (ASTParsingException e){
            error.appendText(e.getMessage());
            return;
        }
        classeNode = (ClasseNode) listener.getRoot();

    }

    public void typeCheck() throws TypeCheckerException {
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    public void interpret(boolean debug) throws Exception {
        if (!debug){
            waiter = new DebugOffWaiter();
        }else{
            waiter = new DebugOnWaiter();
        }
        run.appendText("\n-----Run MiniJaja-----\n\n");
        MiniJajaInterpreter.getFactory().createFrom(memory, classeNode).interpret(new MJJInterpreterController(this));
        run.appendText("\n----------------------\n\n");
    }

    @Override
    public void mjjWrite(String str) {
        run.appendText(str);
    }

    @Override
    public void mjjWriteLn(String str) {
        run.appendText(str);
        run.appendText("\n");
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void debug(int line) throws InterruptedException {
        if (breakpoints.contains(line)){
            debug.clear();
            debug.appendText("line : "+line+"\n");
            debug.appendText(memory.toString());
        }
        waiter.waitForUser(breakpoints.contains(line));
    }

    public void init(){
        IntFunction<Node> numberFactory = LineNumberFactory.get(codeArea);
        IntFunction<Node> graphicFactory;
        breakPoint = new BreakPoint(codeArea.currentParagraphProperty());
        graphicFactory = line -> {
            HBox hbox = new HBox(
                    breakPoint.apply(line),
                    numberFactory.apply(line));
            hbox.setAlignment(Pos.CENTER_LEFT);
            return hbox;
        };
        codeArea.setParagraphGraphicFactory(graphicFactory);
    }

    public ClasseNode getClasseNode() {
        return classeNode;
    }

    public void nextBreakPoint(ActionEvent actionEvent) {
        waiter.nextBreakpoint();
    }

    public void step(ActionEvent actionEvent) {
        waiter.nextStep();
    }

    public int runAll(String file,boolean debug, Memory memory) throws Exception {
        setBreakpoints();
        init(file);
        setMemory(memory);
        build();
        typeCheck();
        interpret(debug);
        return 0;
    }
}
