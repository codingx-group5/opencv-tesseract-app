package com.mark.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Delete extends Thread{
    private static String deleted;
    private static ArrayList<Object[]> oldDataBase = new ArrayList<>();

    public void run(){
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();
        Delete delete = new Delete();
        delete.creatNewCsv();
        ArrayList<Object[]> OldDataBase = delete.getOldDataBase();
        deleted = delete.getDeleted();
        for(int i = 0;i < oldDataBase.size(); i++){
            if(OldDataBase.get(i)[0].equals(deleted)) {
                continue;
            }else {
                writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
                writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
                writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
                writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
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

    public void creatNewCsv(){
        File f = new File("D:/Importcsv/data_base.csv");
        if(f.exists()){
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
