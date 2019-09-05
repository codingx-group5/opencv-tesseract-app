package com.example.insumon;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Delete extends Thread implements Runnable{
    private Context context;
    private WriteData2CSVThread writeData2CSVThread;
    private String deleted;
    private ArrayList<Object[]> oldDataBase ;
    private FileOutputStream csv;

    public Delete(Context context){
        this.context = context;
    }

    @Override
    public void run(){
        Log.d("delete", "running" );
        try {
            newFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writeData2CSVThread = new WriteData2CSVThread(csv);
        String[] newfileList = context.fileList();
        for(int i = 0; i < newfileList.length; i++){
            Log.d("newFileList", newfileList[i]);
        }
        ArrayList<Object[]> oldDataBase = getOldDataBase();
        deleted = getDeleted();
        for(int i = 0;i < oldDataBase.size(); i++){
            if(oldDataBase.get(i)[0].equals(deleted)) {
                continue;
            }else {
                writeData2CSVThread.setDateTime((String) oldDataBase.get(i)[0]);
                writeData2CSVThread.setBloodSugar((String) oldDataBase.get(i)[1]);
                writeData2CSVThread.setEatTime((String) oldDataBase.get(i)[2]);
                writeData2CSVThread.setTime((String) oldDataBase.get(i)[3]);
                writeData2CSVThread.run();
            }
        }
    }

    public void setDeleted(String deletedDateTime){
        this.deleted = deletedDateTime;
    }
    public String getDeleted(){
        return deleted;
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
