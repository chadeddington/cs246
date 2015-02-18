package com.example.chad.multithread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by chad on 2/17/15.
 */
public interface ReadListener {
    public void numberLoaded(String num, int progress);
}
