package com.example.insumon;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Revise extends Thread {
    private Context context;
    private WriteData2CSVThread writeData2CSVThread;
    private static ArrayList<Object[]> oldDataBase;
    private FileOutputStream csv;

    public Revise(Context context){
        this.context = context;
    }

    public void setRevisedSugar(String dateTime, int eatTime, int time, int sugar, int newValue) {
        writeData2CSVThread = new WriteData2CSVThread(csv);
        try {
            newFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object[]> OldDataBase = getOldDataBase();
        for (int i = 0; i < oldDataBase.size(); i++) {
            if (((String) oldDataBase.get(i)[0]).equals(dateTime) && (int) oldDataBase.get(i)[2] == eatTime && (int) oldDataBase.get(i)[3] == time) {
                OldDataBase.get(i)[1] = newValue;
            }
        }
        for (int i = 0; i < oldDataBase.size(); i++) {
            writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
            writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
            writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
            writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
            writeData2CSVThread.run();
        }
    }
    public void setRevisedEatTime(String dateTime, int sugar, int eatTime, int time, int newValue) {
        writeData2CSVThread = new WriteData2CSVThread(csv);
        try {
            newFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object[]> OldDataBase = getOldDataBase();
        for (int i = 0; i < oldDataBase.size(); i++) {
            if (((String) oldDataBase.get(i)[0]).equals(dateTime) && (int) oldDataBase.get(i)[1] == sugar && (int) oldDataBase.get(i)[3] == time) {
                OldDataBase.get(i)[2] = newValue;
            }
        }
        for (int i = 0; i < oldDataBase.size(); i++) {
            writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
            writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
            writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
            writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
            writeData2CSVThread.run();
        }
    }
    public void setRevisedTime(String dateTime, int sugar, int eatTime, int time, int newValue) {
        writeData2CSVThread = new WriteData2CSVThread(csv);
        try {
            newFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object[]> OldDataBase = getOldDataBase();
        for (int i = 0; i < oldDataBase.size(); i++) {
            if (((String) oldDataBase.get(i)[0]).equals(dateTime) && (int) oldDataBase.get(i)[1] == sugar && (int) oldDataBase.get(i)[2] == eatTime) {
                OldDataBase.get(i)[3] = newValue;
            }
        }
        for (int i = 0; i < oldDataBase.size(); i++) {
            writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
            writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
            writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
            writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
            writeData2CSVThread.run();
        }
    }
    public void setRevisedDateTime(int sugar, int eatTime, int time, String dateTime, String newValue) {
        writeData2CSVThread = new WriteData2CSVThread(csv);
        try {
            newFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object[]> OldDataBase = getOldDataBase();
        for (int i = 0; i < oldDataBase.size(); i++) {
            if ((int) oldDataBase.get(i)[1] == sugar && (int) oldDataBase.get(i)[2] == eatTime && (int) oldDataBase.get(i)[3] == time){
                OldDataBase.get(i)[0] = newValue;
            }
        }
        for (int i = 0; i < oldDataBase.size(); i++) {
            writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
            writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
            writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
            writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
            writeData2CSVThread.run();
        }
    }

    public void setOldDataBase(ArrayList<Object[]> oldDataBase){
        this.oldDataBase = oldDataBase;
    }

    public ArrayList<Object[]> getOldDataBase(){
        return oldDataBase;
    }

    public void newFile() throws FileNotFoundException {
        Log.d("NewFile", "running");
        context.deleteFile("data.csv");
        String[] fileList = context.fileList();
        for(int i =0; i < fileList.length; i++){
            Log.d("fileList", fileList[i]);
        }

        csv = context.openFileOutput("data.csv", Context.MODE_APPEND);

        String[] fileList2 = context.fileList();
        for(int i =0; i < fileList2.length; i++){
            Log.d("fileList2", fileList2[i]);
        }
    }

}
