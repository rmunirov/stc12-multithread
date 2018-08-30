package ru.innopolis.stc12.multithread;

import java.util.Objects;

public class MessageThread extends Thread {
    private final Object monitor;
    private long startTimer;
    private int interval;

    MessageThread(Object monitor, int interval) {
        this.monitor = monitor;
        this.interval = interval;
        startTimer = System.currentTimeMillis();
        System.out.println("message every " + interval + " second");
    }

    public void run() {
        synchronized (monitor) {
            while (!isInterrupted()) {
                if ((System.currentTimeMillis() - startTimer) >= (interval * 1000)) {
                    System.out.println("message every " + interval + " second");
                    startTimer = System.currentTimeMillis();
                } else {
                    monitor.notifyAll();    //TODO if remove parameter monitor, then executed exception
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageThread that = (MessageThread) o;
        return startTimer == that.startTimer &&
                interval == that.interval &&
                Objects.equals(monitor, that.monitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitor, startTimer, interval);
    }
}
