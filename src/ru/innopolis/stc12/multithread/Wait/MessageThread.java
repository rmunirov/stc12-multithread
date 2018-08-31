package ru.innopolis.stc12.multithread.Wait;

public class MessageThread implements Runnable {    //TODO what's better implement Runnable or extend Thread
    private final Monitor monitor;
    private int interval;
    private Thread thread;

    MessageThread(Monitor monitor, int interval) {
        this.monitor = monitor;
        this.interval = interval;
        System.out.println("message every " + interval + " second");
    }

    public void run() {
        while (true) {

            synchronized (monitor) {
                if ((monitor.getSeconds() > 0) && ((monitor.getSeconds() % interval) == 0)) {
                    System.out.println("message every " + interval + " second");
                }
                monitor.notifyAll();    //TODO if remove parameter monitor, then executed exception
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();
    }

    public void interrupt() {
        thread.interrupt();
    }
}
