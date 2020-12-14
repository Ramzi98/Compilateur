package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.memory.Memory;

public abstract class Runner {

    public interface VoidFunction {
        void action();
    }

    public interface IntFunction {
        void action(int line);
    }

    Object lock = new Object();
    VoidFunction error = null;
    IntFunction notifier = null;
    boolean nomute = true;
    int lastNotifiedLine = 0;

    protected class RunnerThread implements Runnable {

        Runner runner;
        Memory memory;
        Boolean instantRun = false;

        RunnerThread(Runner runner, Memory memory, Boolean instantRun) {
            this.runner = runner;
            this.memory = memory;
            this.instantRun = instantRun;
        }

        @Override
        public void run() {
            try {
                if (!instantRun)
                    runner.pause();
                else
                    runner.run();
                runner.begin(memory);
            } catch (Exception e) {
                if (runner.error != null)
                    runner.error.action();
                if (nomute)
                    throw e;
            }

        }

    }

    Thread runThread;

    public void init(Memory memory) {
        if (runThread != null)
            return;
        runThread = new Thread(new RunnerThread(this, memory, false));
        runThread.start();
    }

    public void initAndRun(Memory memory) {
        if (runThread != null)
            return;
        runThread = new Thread(new RunnerThread(this, memory, true));
        runThread.start();
    }

    public Boolean initiated() {
        return this.runThread != null;
    }

    public void pause() {
        synchronized (runThread) {
            try {
                runThread.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public void resume() {
        synchronized (runThread) {
            runThread.notifyAll();
        }
    }

    public void awaitEnd() {
        synchronized (lock) {
            try {
                if (runThread != null) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public void kill() {
        if (runThread != null)
            runThread.interrupt();
    }

    public void whenError(VoidFunction voidFunction) {
        this.error = voidFunction;
    }

    public void whenLineNotified(IntFunction intFunction) {
        this.notifier = intFunction;
    }

    public void notifyLine(int line) {
        lastNotifiedLine = line;
        if (notifier != null)
            notifier.action(line);
    }

    public void mute() {
        nomute = false;
    }

    public abstract void begin(Memory memory);

    public abstract void run();

    public abstract void runStep();

    public abstract void runCheckType(Memory memory);

}

