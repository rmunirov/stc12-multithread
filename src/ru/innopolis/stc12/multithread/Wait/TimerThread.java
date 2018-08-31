package ru.innopolis.stc12.multithread.Wait;

import java.time.LocalDateTime;

public class TimerThread implements Runnable {

    private final Monitor monitor;
    private long startTimeMillis = 0;
    private Thread thread;

    TimerThread(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now());
        startTimeMillis = System.currentTimeMillis();
        while (true) {
            synchronized (monitor) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }

                monitor.setSeconds((System.currentTimeMillis() - startTimeMillis) / 1000);
                System.out.println(LocalDateTime.now());

                monitor.notifyAll();

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
