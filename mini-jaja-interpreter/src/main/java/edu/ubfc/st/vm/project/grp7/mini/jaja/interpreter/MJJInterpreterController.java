package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

public class MJJInterpreterController {
    private final MJJInterpreterListener listener;

    public MJJInterpreterController(MJJInterpreterListener listener) {
        this.listener = listener;
    }

    public void write(String str) {
        this.listener.mjjWrite(str);
    }

    public void writeLn(String str) {
        this.listener.mjjWriteLn(str);
    }
}
