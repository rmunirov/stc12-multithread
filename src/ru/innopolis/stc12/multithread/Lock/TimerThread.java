package ru.innopolis.stc12.multithread.Lock;

import ru.innopolis.stc12.multithread.Wait.Monitor;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimerThread implements Runnable {

    private final Monitor monitor;
    private long startTimeMillis = 0;
    private Thread thread;
    private Lock lock = new ReentrantLock();

    TimerThread(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now());
        startTimeMillis = System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }

            lock.lock();
            monitor.setSeconds((System.currentTimeMillis() - startTimeMillis) / 1000);
            lock.unlock();

            System.out.println(LocalDateTime.now());
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