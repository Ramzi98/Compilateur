package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreter;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterController;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterListener;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;

public class InterpreterJajaCodeModel implements  JJCInterpreterListener {
    private Waiter waiter;
    private Memory memory;
    private List<JajaCodeNode> nodes;
    private TextArea run;
    private TextArea error;
    private TextArea debug;
    private CodeArea codeArea;
    private List<Integer> listBreakpoints   ;
    private String code;
    private Thread pausable;
    private BreakPoint breakPoint;
    private Executor threadWrite = Executors.newSingleThreadExecutor();

    public void setCode(String code) {
        this.code = code;
        codeArea.clear();
        codeArea.appendText(code);
        init();
    }


    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public InterpreterJajaCodeModel(TextArea run, TextArea error, TextArea debug, CodeArea codeArea){
        this.run = run;
        this.error = error;
        this.debug = debug;
        this.codeArea = codeArea;
        this.pausable = new Thread(()->{
            try {
                runnable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setBreakpoints() {
        this.listBreakpoints = breakPoint.returnCheckedLine();
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
        if (debug){
            waiter = new DebugOffWaiter();
        }else{
            waiter = new DebugOnWaiter(pausable);
        }
    }


    public void runnable() throws Exception {
        this.threadWrite.execute(() -> {
            run.appendText("\nRun JajaCode\n\n");
        });

        JJCInterpreter.getFactory().createFrom(memory,nodes).interpret(new JJCInterpreterController(this) );

        this.threadWrite.execute(() -> {
            run.appendText("\n----------------------------------\n");
        });
    }

    public void nextBreakPoint(ActionEvent actionEvent) {
        waiter.nextBreakpoint();
    }

    public void step(ActionEvent actionEvent) {
        waiter.nextStep();
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
}
