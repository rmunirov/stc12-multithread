package ru.innopolis.stc12.multithread.Lock;

import ru.innopolis.stc12.multithread.Wait.Monitor;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        TimerThread timerThread = new TimerThread(monitor);
        MessageThread messageThread5 = new MessageThread(monitor, 5);
        MessageThread messageThread7 = new MessageThread(monitor, 7);

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
