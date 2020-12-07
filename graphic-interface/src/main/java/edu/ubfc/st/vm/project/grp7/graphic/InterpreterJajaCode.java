package edu.ubfc.st.vm.project.grp7.graphic;

import com.sun.org.apache.bcel.internal.classfile.Code;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreter;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterController;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterListener;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import javafx.scene.control.TextArea;
import org.fxmisc.richtext.CodeArea;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InterpreterJajaCode implements  JJCInterpreterListener {
    Waiter waiter;
    Memory memory;
    List<JajaCodeNode> nodes;
    TextArea run;
    TextArea error;
    TextArea debug;
    CodeArea codeArea;
    List<Integer> breakpoints;
    String code;
    private Executor threadWrite = Executors.newSingleThreadExecutor();

    public void setCode(String code) {
        this.code = code;
        codeArea.clear();
        codeArea.appendText(code);
    }


    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public InterpreterJajaCode(TextArea run, TextArea error, TextArea debug, CodeArea codeArea){
        this.run = run;
        this.error = error;
        this.debug = debug;
        this.codeArea = codeArea;
    }

    public void setBreakpoints(List<Integer> breakpoints) {
        this.breakpoints = breakpoints;
    }

    public void setNodes(List<JajaCodeNode> nodes) {
        this.nodes = nodes;

    }

    public List<JajaCodeNode> getNodes() {
        return nodes;
    }

    @Override
    public void jjcWrite(String str) {
        this.threadWrite.execute(() -> {
            run.appendText(str);
        });
    }

    @Override
    public void jjcWriteLn(String str) {
        this.threadWrite.execute(() -> {
            run.appendText(str);
            run.appendText("\n");
        });
    }

    @Override
    public void nop() {

    }

    @Override
    public void debug(int line) throws InterruptedException {

    }

    public void run(boolean debug) throws Exception {
        this.threadWrite.execute(() -> {
            run.appendText("\nRun JajaCode\n\n");
        });
        JJCInterpreter.getFactory().createFrom(memory,nodes).interpret(new JJCInterpreterController(this) );
        this.threadWrite.execute(() -> {
            run.appendText("\n----------------------------------\n");
        });
    }
}
