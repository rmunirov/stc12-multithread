package ru.innopolis.stc12.multithread.Wait;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        TimerThread timerThread = new TimerThread(monitor);
        MessageThread messageThreadEvery5 = new MessageThread(monitor, 5);
        MessageThread messageThreadEvery7 = new MessageThread(monitor, 7);

        timerThread.start();
        messageThreadEvery5.start();
        messageThreadEvery7.start();


        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timerThread.interrupt();
        messageThreadEvery5.interrupt();
        messageThreadEvery7.interrupt();
    }
}
