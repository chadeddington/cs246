package com.example.chad.multithread;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String filename = "numbers.txt";
        Context context = this;
        final List<String> numberlist = new ArrayList<String>();


        //Create a new file
        final File file = new File(context.getFilesDir(), filename);
        final WriteNumbers writeThread = new WriteNumbers(file);
        final ReadNumbers readThread = new ReadNumbers(file, this);

        Button create_button = (Button) findViewById(R.id.create_button);
        Button load_button = (Button) findViewById(R.id.load_button);
        Button clear_button = (Button) findViewById(R.id.clear_button);
        final ListView listView = (ListView) findViewById(R.id.list_view);


        //Write to the new file
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t1 = new Thread(writeThread);
                t1.start();
            }
        });

        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Thread load_thread = new Thread(new Runnable() {
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
                });

                load_thread.start();*/
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


                ArrayAdapter adapter = new ArrayAdapter<String>(getApplication(), R.layout.my_text, R.id.my_text, numberlist);
                listView.setAdapter(adapter);
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
