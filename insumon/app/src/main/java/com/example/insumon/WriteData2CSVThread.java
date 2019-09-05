package com.example.insumon;

import android.Manifest;
import android.content.Context;
import android.util.Log;

import com.example.insumon.MainActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

public class WriteData2CSVThread extends Thread implements Runnable{
    public String dateTime;
    public String bloodSugarValue;
    public int eatTime;
    public int time;
    private FileOutputStream csv = null;

    public WriteData2CSVThread(FileOutputStream csv){
        this.csv = csv;
    }



    @Override
    public void run(){
        Log.d("writer", "running");
        try {
            //在 getFilesDir() 目錄底下建立 test.txt 檔案用來進行寫入

            //將資料寫入檔案中
            String dateTime = getDateTime();
            bloodSugarValue = getBloodSugar();
            eatTime = getEatTime();
            time = getTime();
            String newData = dateTime + "," + bloodSugarValue + "," + eatTime + "," + time + "\n";
            csv.write(newData.getBytes());
            csv.flush();
        } catch (Exception e) {
            ;
        } finally {
            try {
                csv.close();
            } catch (Exception e) {
                ;
            }
        }
    }

    public String getBloodSugar(){
        return bloodSugarValue;
    }

    public void setBloodSugar(String bloodSugar){
        this.bloodSugarValue = bloodSugar;
    }

    private int getEatTime(){
        return eatTime;
    }

    public void setEatTime(int eatTime){
        this.eatTime = eatTime;
    }
    private int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public String getDateTime(){
        return dateTime;
    }

    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

}


