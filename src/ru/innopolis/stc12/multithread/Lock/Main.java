package ru.innopolis.stc12.multithread.Lock;

public class Main {
    public static void main(String[] args) {
        MyCounter condition = MyCounter.getInstance();

        TimerThread timerThread = new TimerThread(condition);
        MessageThread messageThread5 = new MessageThread(condition, 5);
        MessageThread messageThread7 = new MessageThread(condition, 7);

        timerThread.start();
        messageThread5.start();
        messageThread7.start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timerThread.interrupt();
        messageThread5.interrupt();
        messageThread7.interrupt();

    }
}
