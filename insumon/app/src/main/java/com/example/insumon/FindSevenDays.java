package com.example.insumon;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class FindSevenDays implements Serializable {
    private Transfer transfer = Scanner.transfer;
    private ReadCSVThread readCSVThread;
    private Search search;
    private ArrayList <Object[]> database;
    private static ArrayList bloodSugarListBF;
    private static ArrayList bloodSugarListAF;
    private static ArrayList totalList;
    private int indexNumBf = 0;
    private int indexNumAf = 0;
    private int indexNum = 0;
    private static final long serialVersionUID = -7060210544600464481L;

    public ArrayList bloodSugarBeforeDish(){
        readCSVThread = transfer.getReadCSVThread();
        search = transfer.getSearch();
        database = readCSVThread.getDataList();
        bloodSugarListBF = new ArrayList();
        search.setDataBase(readCSVThread.getDataList());

        for (int i = 0; i<database.size(); i++){
            String dateTime = String.valueOf(database.get(i)[0]);
            String bloodSugar;
            if (String.valueOf(database.get(i)[3]).equals("早餐")){
                bloodSugar = search.searchBloodSugarBeforeDish(dateTime,"早餐" );
                bloodSugarListBF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            }else if(String.valueOf(database.get(i)[3]).equals("午餐")){
                bloodSugar = search.searchBloodSugarBeforeDish(dateTime,"午餐" );
                bloodSugarListBF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            }else {
                bloodSugar = search.searchBloodSugarBeforeDish(dateTime,"晚餐" );
                bloodSugarListBF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            }
        }
        return bloodSugarListBF;

    }
    public ArrayList bloodSugarAfterDish() {
        readCSVThread = transfer.getReadCSVThread();
        search = transfer.getSearch();
        database = readCSVThread.getDataList();
        bloodSugarListAF = new ArrayList();
        search.setDataBase(readCSVThread.getDataList());

        for (int i = 0; i < database.size(); i++) {
            String dateTime = String.valueOf(database.get(i)[0]);
            String bloodSugar;
            if (String.valueOf(database.get(i)[3]).equals("早餐")) {
                bloodSugar = search.searchBloodSugarAfterDish(dateTime, "早餐");
                bloodSugarListAF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            } else if (String.valueOf(database.get(i)[3]).equals("午餐")) {
                bloodSugar = search.searchBloodSugarAfterDish(dateTime, "午餐");
                bloodSugarListAF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            } else {
                bloodSugar = search.searchBloodSugarAfterDish(dateTime, "晚餐");
                bloodSugarListAF.add(String.valueOf(bloodSugar));
//                totalList.add(String.valueOf(bloodSugar));
            }
        }
        return bloodSugarListAF;
    }

    public int findIndexNumBF(){
        for (int i = 0;i<bloodSugarListBF.size();i++){
            indexNumBf+=1;

        }
        Log.d("indexNum", String.valueOf(indexNumBf));
        return indexNumBf;
    }
    public int findIndexNumAF(){
        for (int i = 0;i<bloodSugarListAF.size();i++){
            indexNumAf+=1;
        }
        return indexNumAf;
    }

    public ArrayList getTotalList(){
        return totalList;
    }
    public int findIndexNumTotal(){
        for (int i = 0;i<totalList.size();i++){
            indexNum+=1;
        }
        return indexNum;
    }

}
