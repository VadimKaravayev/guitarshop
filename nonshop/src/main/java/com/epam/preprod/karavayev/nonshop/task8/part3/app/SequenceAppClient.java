package com.epam.preprod.karavayev.nonshop.task8.part3.app;

import com.epam.preprod.karavayev.model.console.ConsoleHelper;
import com.epam.preprod.karavayev.nonshop.task8.part3.task.FindSequenceTask;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SequenceAppClient {

    private String file;
    private volatile int length = -1;
    private volatile boolean flag = false;
    private volatile byte[] byteResult;
    private int[] indexEntries;
    private ConsoleHelper console;
    private boolean isExit = false;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private byte[] getByteResult() {
        return byteResult;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    private int[] getIndexEntries() {
        return indexEntries;
    }

    public void setIndexEntries(int[] indexEntries) {
        this.indexEntries = indexEntries;
    }

    private SequenceAppClient() {
        console = new ConsoleHelper();
        setByteResult(new byte[0]);
    }

    private void runApp() {
        Thread worker = new Thread(new FindSequenceTask(this));
        worker.setDaemon(true);
        worker.start();

        while (!isExit) {
            console.promptMessage("Would you like to search? (Y/N)");
            String input = console.takeInput();
            if (input.equalsIgnoreCase("y")) {
                console.promptMessage("Enter file name");
                String fileName = console.takeInput();
                setFile(fileName);
                setFlag(true);
                while (isFlag()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150);
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                    System.out.print("\rCurrent length " + getLength());
                }
                if (getByteResult().length > 0) {
                    console.promptMessage("\nThe result: " + Arrays.toString(getByteResult()));
                    console.promptMessage("First index entry: " + getIndexEntries()[0] + ", second index entry: " + getIndexEntries()[1]);
                } else {
                    console.promptMessage("\nNo occurrences found");
                }
            } else if (input.equalsIgnoreCase("n")){
                isExit = true;
            } else {
                console.promptMessage("Wrong input. Try again");
            }
        }
        console.promptMessage("Goodbye");
    }

    public static void main(String[] args) {
        new SequenceAppClient().runApp();
    }
}
