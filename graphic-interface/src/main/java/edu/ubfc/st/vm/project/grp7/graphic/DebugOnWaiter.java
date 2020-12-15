package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;

public class DebugOnWaiter implements DebugListener.Waiter {
     private final Thread thread;

     public DebugOnWaiter(Thread thread){
         this.thread = thread;
     }

    private boolean lineMode = false;

    @Override
    public void waitForUser(boolean breakpointedLine) throws InterruptedException {
        while(lineMode || breakpointedLine) {
            try {
                synchronized (this.thread){
                    System.out.println("here");
                    thread.wait();
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }

    @Override
    public void nextBreakpoint() {
        lineMode = false;
        try {
            synchronized (this.thread){
                thread.notify();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void nextStep() {
        lineMode = true;
        try {
            synchronized (this.thread){
                thread.notify();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}