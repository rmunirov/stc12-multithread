package ru.innopolis.stc12.multithread.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCounter {
    private static MyCounter ourInstance = new MyCounter();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private long seconds = 0;

    private MyCounter() {
    }

    public static MyCounter getInstance() {
        return ourInstance;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
