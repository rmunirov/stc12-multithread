package ru.innopolis.stc12.multithread;

import java.time.LocalDateTime;

public class TimerThread extends Thread {

    private Object monitor;

    TimerThread(Object monitor) {
        this.monitor = monitor;
        System.out.println(LocalDateTime.now());
    }

    public void run() {
        synchronized (monitor) {
            while (!isInterrupted()) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(LocalDateTime.now());

                monitor.notifyAll();

                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
