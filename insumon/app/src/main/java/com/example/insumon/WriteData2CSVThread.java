package com.example.insumon;

import android.util.Log;

import java.io.FileOutputStream;

public class WriteData2CSVThread extends Thread implements Runnable{
    public String dateTime;
    public String bloodSugarValue;
    public String eatTime;
    public String time;
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

    private String getEatTime(){
        return eatTime;
    }

    public void setEatTime(String eatTime){
        Log.d("setdata","ok");
        this.eatTime = eatTime;
    }
    private String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getDateTime(){
        return dateTime;
    }

    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

}


