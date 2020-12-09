package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;


public class DebugOnWaiter implements DebugListener.Waiter {
     private final Thread thread;
    private final Object lock = new Object();

     public DebugOnWaiter(Thread thread){
         this.thread = thread;
     }

    private boolean lineMode = false;

    @Override
    public void waitForUser(boolean breakpointedLine) throws InterruptedException {
        while(lineMode || breakpointedLine) {
            thread.wait();
        }
    }

    @Override
    public void nextBreakpoint() {
        lineMode = false;
        thread.notify();
    }

    @Override
    public void nextStep() {
        lineMode = true;
        thread.notify();
    }
}