package ru.innopolis.stc12.multithread.Lock;

import ru.innopolis.stc12.multithread.Wait.Monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageThread implements Runnable {    //TODO what's better implement Runnable or extend Thread
    private final Monitor monitor;
    private int interval;
    private Thread thread;
    private Lock lock = new ReentrantLock();

    MessageThread(Monitor monitor, int interval) {
        this.monitor = monitor;
        this.interval = interval;
        System.out.println("message every " + interval + " second");
    }

    public void run() {
        while (true) {
            //TODO how else I dont know
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }

            try {
                lock.lockInterruptibly();
                if ((monitor.getSeconds() > 0) && ((monitor.getSeconds() % interval) == 0)) {
                    System.out.println("message every " + interval + " second");
                }
            } catch (InterruptedException e) {
                return;
            }
            lock.unlock();
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
