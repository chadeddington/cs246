package com.example.chad.multithread;

import android.widget.ListAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chad on 2/17/15.
 */
public class ReadNumbers implements Runnable {
    private File file;
    private List<String> numberlist = new ArrayList<String>();
    public ReadNumbers (File file) {
        this.file = file;
    }


    @Override
    public void run() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            String fileLine;
            try {
                while((fileLine = buffReader.readLine()) != null) {
                    numberlist.add(fileLine);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException inter_ex) {
                        inter_ex.printStackTrace();
                    }
                    System.out.println("Read: " + fileLine);
                }

            } catch (IOException io_ex) {
                io_ex.printStackTrace();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }



    }

}
