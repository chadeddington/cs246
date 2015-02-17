package com.example.chad.multithread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by chad on 2/17/15.
 */
public class WriteNumbers implements Runnable {
    private File file;
    public WriteNumbers(File file) {
        this.file = file;
    }
    @Override
    public void run() {

        try {
            FileWriter writer = new FileWriter(file);

            for (int i = 1; i < 11; i++) {
                writer.append(i + "\n");
                try {
                    Thread.sleep(250);

                } catch (InterruptedException in_ex) {
                    in_ex.printStackTrace();
                }
            System.out.println(i);
            }
        } catch (IOException io_ex) {
            io_ex.printStackTrace();
        }

    }
}
