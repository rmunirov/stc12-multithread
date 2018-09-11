package ru.innopolis.stc12.multithread.Lock;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TimerThread implements Runnable {
    private long startTimeMillis = 0;
    private Thread thread;
    private Lock lock;
    private Condition condition;
    private MyCounter counter;

    TimerThread(MyCounter counter) {
        this.counter = counter;
        this.lock = counter.getLock();
        this.condition = counter.getCondition();
    }

    @Override
    public void run() {
        if (lock == null) return;
        if (condition == null) return;
        if (counter == null) return;

        System.out.println(LocalDateTime.now());
        startTimeMillis = System.currentTimeMillis();
        while (true) {
            try {
                lock.lockInterruptibly();

                Thread.sleep(1000);

                counter.setSeconds((System.currentTimeMillis() - startTimeMillis) / 1000);

                condition.signalAll();
                condition.await();

                System.out.println(LocalDateTime.now());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            } finally {
                lock.unlock();
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