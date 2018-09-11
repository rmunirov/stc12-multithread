package ru.innopolis.stc12.multithread.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MessageThread implements Runnable {    //TODO what's better implement Runnable or extend Thread
    private int interval;
    private Thread thread;
    private Lock lock;
    private Condition condition;
    private MyCounter counter;

    MessageThread(MyCounter counter, int interval) {
        this.counter = counter;
        this.lock = counter.getLock();
        this.condition = counter.getCondition();
        this.interval = interval;
        System.out.println("message every " + interval + " second");
    }

    public void run() {
        if (lock == null) return;
        if (condition == null) return;
        if (counter == null) return;

        while (true) {
            try {
                lock.lockInterruptibly();
                if ((counter.getSeconds() > 0) && ((counter.getSeconds() % interval) == 0)) {
                    System.out.println("message every " + interval + " second");
                }
                condition.signalAll();
                condition.await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
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
