package com.example.chad.multithread;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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
    private ArrayAdapter<String> adapter;
    private Context context;
    private ReadListener listener;

    public ReadNumbers (File file, Context context) {
        this.file = file;
        this.context = context;
    }

    public void addListener(ReadListener listener) {
        this.listener = listener;
    }


    @Override
    public void run() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            String fileLine;
            try {
                int i = 0;
                while((fileLine = buffReader.readLine()) != null) {
                    i++;
                    try {
                        Thread.sleep(250);
                        listener.numberLoaded(fileLine, i);
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
