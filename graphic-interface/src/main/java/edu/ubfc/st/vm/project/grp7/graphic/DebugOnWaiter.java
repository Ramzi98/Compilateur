package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;

public class DebugOnWaiter implements DebugListener.Waiter {
     private Object lock = new Object();

     public DebugOnWaiter(){
     }

    private boolean lineMode = false;

    @Override
    public void waitForUser(boolean breakpointedLine) throws InterruptedException {
        if(lineMode || breakpointedLine) {
            try {
                synchronized (lock){
                    lock.wait();
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }

    @Override
    public void nextBreakpoint() {
        lineMode = false;
        try {
            synchronized (lock){
                lock.notify();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void nextStep() {
        lineMode = true;
        try {
            synchronized (lock){
                lock.notify();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}