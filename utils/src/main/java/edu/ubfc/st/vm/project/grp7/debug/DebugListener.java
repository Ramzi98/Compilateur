package edu.ubfc.st.vm.project.grp7.debug;

public interface DebugListener {
    void debug(int line) throws InterruptedException;

    interface Waiter {
        void waitForUser(boolean debug) throws InterruptedException;
        void nextBreakpoint();
        void nextStep();
    }
}