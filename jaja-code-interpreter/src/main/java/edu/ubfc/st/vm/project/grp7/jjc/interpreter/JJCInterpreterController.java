package edu.ubfc.st.vm.project.grp7.jjc.interpreter;

public class JJCInterpreterController {
    private final JJCInterpreterListener listener;

    public JJCInterpreterController(JJCInterpreterListener listener) {
        this.listener = listener;
    }
    public void write(String str) {
        listener.jjcWrite(str);
    }

    public void writeLn(String str) {
        listener.jjcWriteLn(str);
    }
    
    public void nop() {
        listener.nop();
    }

    public void debug(int line) throws InterruptedException {
        listener.debug(line);
    }
}