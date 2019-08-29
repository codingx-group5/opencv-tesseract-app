package com.mark.csv;
import android.Manifest;
import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class WriteData2CSVThread extends Thread{
    public static String dateTime;
    public static String bloodSugarValue;
    public static int eatTime;
    public static int time;
    public void run(){

        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();

        try {

            File csv = new File(""); // CSV資料檔案
            Log.d("csv:","hi");
            Log.d("time:", "hi");
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // 附加
// 新增新的資料行
            Log.d("file:", String.valueOf(csv.getName()));
            Log.d("file:", String.valueOf(csv.getAbsolutePath()));
            String dateTime = writeData2CSVThread.getDateTime();
            bloodSugarValue = writeData2CSVThread.getBloodSugar();
            eatTime = writeData2CSVThread.getEatTime();
            time = writeData2CSVThread.getTime();
            bw.write(dateTime + "," + bloodSugarValue + "," + eatTime + "," + time);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
// File物件的建立過程中的異常捕獲
            e.printStackTrace();
        } catch (IOException e) {
// BufferedWriter在關閉物件捕捉異常
            e.printStackTrace();
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
