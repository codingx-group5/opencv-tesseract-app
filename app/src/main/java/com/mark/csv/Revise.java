package com.mark.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Revise extends Thread {
//    private static int revisedDateTimeCode;
//    private static int revisedSugarCode;
//    private static int revisedEatTimeCode;
//    private static int revisedTimeCode;
//    private static String revisedDateTime;
//    private static int revisedSugar;
//    private static int revisedEatTime;
//    private static int revisedTime;
    private static ArrayList<Object[]> oldDataBase;

    public void setRevisedSugar(String dateTime, int eatTime, int time, int sugar, int newValue) {
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();
        Revise revise = new Revise();
        revise.creatNewCsv();
        ArrayList<Object[]> OldDataBase = revise.getOldDataBase();
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
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();
        Revise revise = new Revise();
        revise.creatNewCsv();
        ArrayList<Object[]> OldDataBase = revise.getOldDataBase();
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
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();
        Revise revise = new Revise();
        revise.creatNewCsv();
        ArrayList<Object[]> OldDataBase = revise.getOldDataBase();
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
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();
        Revise revise = new Revise();
        revise.creatNewCsv();
        ArrayList<Object[]> OldDataBase = revise.getOldDataBase();
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
//        revisedDateTime = revise.getRevisedDateTime();
//        revisedSugar = revise.getRevisedSugar();
//        revisedEatTime = revise.getRevisedEatTime();
//        revisedTime = revise.getRevisedTime();
//        for (int i = 0; i < oldDataBase.size(); i++) {
//            if(((String)oldDataBase.get(i)[0]).equals(revisedDateTime)){
//                for(int j = 1; j < 4;j++){
//                    if(oldDataBase.get(i)[j] ==)
//                }
//            }
//        }
//        if(revisedCode == 0) {
//            revisedDateTime = revise.getRevisedDateTime();
//            for (int i = 0; i < oldDataBase.size(); i++) {
//                if (OldDataBase.get(i)[0].equals(revisedDateTime)){
//                    continue;
//                } else {
//                    writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
//                    writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
//                    writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
//                    writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
//                    writeData2CSVThread.run();
//                }
//            }
//        }else if(revisedCode == 1) {
//            revisedSugar = revise.getRevisedSugar();
//            for (int i = 0; i < oldDataBase.size(); i++) {
//                if (OldDataBase.get(i)[1].equals(revisedSugar)){
//                    continue;
//                } else {
//                    writeData2CSVThread.setDateTime((String) OldDataBase.get(i)[0]);
//                    writeData2CSVThread.setBloodSugar((String) OldDataBase.get(i)[1]);
//                    writeData2CSVThread.setEatTime((int) OldDataBase.get(i)[2]);
//                    writeData2CSVThread.setTime((int) OldDataBase.get(i)[3]);
//                    writeData2CSVThread.run();
//                }
//            }
//        }
//    }

//    public String getRevisedDateTime() {
//        return revisedDateTime;
//    }
//
//    public void setRevisedDateTime(String revisedDateTime ,int ifDelete) {
//        this.revisedDateTime = revisedDateTime;
//        this.revisedDateTimeCode = ifDelete;
//    }
//
//    public int getRevisedSugar() {
//        return revisedSugar;
//    }
//
//    public void setRevisedSugar(int revisedSugar, int ifDelete) {
//        this.revisedSugar = revisedSugar;
//        this.revisedSugarCode = 1;
//    }
//
//    public int getRevisedEatTime() {
//        return revisedEatTime;
//    }
//
//    public void setRevisedEatTime(int revisedEatTime, int ifDelete) {
//        this.revisedEatTime = revisedEatTime;
//        this.revisedEatTimeCode = 2;
//    }
//
//    public int getRevisedTime() {
//        return revisedTime;
//    }
//
//    public void setRevisedTime(int revisedTime, int ifDelete) {
//        this.revisedTime = revisedTime;
//        this.revisedTimeCode = 3;
//    }
//
//    public int getRevisedDateTimeCode(){
//        return  revisedDateTimeCode;
//    }
//
//    public int getRevisedSugarCode(){
//        return revisedSugarCode;
//    }
//
//    public int getRevisedEatTimeCode(){
//        return  revisedEatTimeCode;
//    }
//
//    public int getRevisedTimeCode(){
//        return  revisedTimeCode;
//    }

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
