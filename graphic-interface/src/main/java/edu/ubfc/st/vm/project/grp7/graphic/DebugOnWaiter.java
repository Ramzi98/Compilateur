package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class DebugOnWaiter implements DebugListener.Waiter {
     private final PausableExecutor thread;

     public DebugOnWaiter(PausableExecutor executor){
         this.thread = executor;
     }

    private boolean lineMode = false;

    @Override
    public void waitForUser(boolean breakpointedLine) throws InterruptedException {
        if (lineMode || breakpointedLine) {
            thread.pause();
        }
    }

    @Override
    public void nextBreakpoint() {
        lineMode = false;
        thread.resume();
    }

    @Override
    public void nextStep() {
        lineMode = true;
        thread.resume();
    }
}