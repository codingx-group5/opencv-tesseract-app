package com.example.insumon;


import android.util.Log;

import java.io.Serializable;

public class Transfer implements Serializable {

    private static final long serialVersionUID = -7060210544600464481L;

    public WriteData2CSVThread writeData2CSVThread;
    public Search search;
    public ReadCSVThread readCSVThread;
    public void setWriteData2CSVThread(WriteData2CSVThread writeData2CSVThread){
        Log.d("setwriter", "ok");
        this.writeData2CSVThread=writeData2CSVThread;
    }
    public WriteData2CSVThread getWriteData2CSVThread(){
        Log.d("getwriter", "ok");
        return writeData2CSVThread;
    }
    public void setSearch(Search search){
        this.search=search;
    }
    public Search getSearch(){
        return search;
    }
    public void setReader(ReadCSVThread reader){
        Log.d("setreader","ok");
//        Log.d("hey2", String.valueOf(readCSVThread.getTest()));
        this.readCSVThread = reader;
    }
    public ReadCSVThread getReadCSVThread(){
        Log.d("getreader", "ok");
        Log.d("hey2", String.valueOf(readCSVThread.getTest()));
        return readCSVThread;
    }

}
