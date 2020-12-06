package edu.ubfc.st.vm.project.grp7.debug;

public interface DebugListener {
    void debug(int line);

    interface Waiter {
        void waitForUser();
        void nextBreakpoint();
        void nextStep();
    }
}