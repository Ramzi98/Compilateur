package edu.ubfc.st.vm.project.grp7.debug;

public class DebugONListener implements DebugListener {
    private final Waiter waiter;
    public DebugONListener(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void debug(int line) {
        waiter.waitForUser();
    }
}
