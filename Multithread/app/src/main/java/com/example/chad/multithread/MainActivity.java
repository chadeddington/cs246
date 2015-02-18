package com.example.chad.multithread;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements ReadListener {

    ArrayAdapter adapter;
    List<String> numberlist;
    Thread readThread;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberlist = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplication(), R.layout.my_text, R.id.my_text, numberlist);
        String filename = "numbers.txt";
        Context context = this;

        //Create a new file
        final File file = new File(context.getFilesDir(), filename);
        ReadNumbers readNumbers = new ReadNumbers(file, context);
        readNumbers.addListener(this);
        readThread = new Thread(readNumbers);

        Button create_button = (Button) findViewById(R.id.create_button);
        Button load_button = (Button) findViewById(R.id.load_button);
        Button clear_button = (Button) findViewById(R.id.clear_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);


        //Write to the new file
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Thread t1 = new Thread(new Runnable() {
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
                                progressBar.setProgress(i);
                            }
                            progressBar.setProgress(0);

                            writer.flush();
                            writer.close();
                        } catch (IOException io_ex) {
                            io_ex.printStackTrace();
                        }
                    }
                });
                t1.start();

            }
        });

        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                readThread.start();

            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();

            }
        });

    }

    @Override
    public void numberLoaded(String num, int progress) {
        numberlist.add(num);
        progressBar.setProgress(progress);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
