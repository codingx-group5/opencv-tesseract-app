package com.example.insumon;
import android.util.Log;

import java.io.ObjectStreamField;
import java.util.ArrayList;

public class Search {
    private static ArrayList<Object[]> dataBase;
    private static Search search;

    public void setDataBase(ArrayList<Object[]> dataBase){
        this.dataBase = dataBase;
    }

    public ArrayList<Object[]> getDataBase(){
        return dataBase;
    }

    public String searchBloodSugarBeforeDish( String dateTime, String time){
        String foundSugar = "";
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[2]).equals("飯前")) && (((String)dataBase.get(i)[3]).equals(time))){
                foundSugar = (String)dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public String searchBloodSugarAfterDish( String dateTime, String time){
        Log.d("searching", "running");
        String foundSugar = "";

        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[2]).equals("飯後")) && (((String)dataBase.get(i)[3]).equals(time))){
                foundSugar = (String) dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public String searchEatTime( String dateTime, String sugar, String time){
        String foundEatTime = "";
        dataBase = getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && (((String)dataBase.get(i)[3]).equals(time))){
                foundEatTime = (String)dataBase.get(i)[2];
                break;
            }
        }
        return foundEatTime;
    }

    public String searchtime( String dateTime, String sugar, String eatTime){
        String foundTime = "";
        dataBase = getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && (((String)dataBase.get(i)[2]).equals(eatTime))){
                foundTime = (String)dataBase.get(i)[3];
                break;
            }
        }
        return foundTime;
    }

    public String searchDateTime(String sugar, String eatTime, String time) {
        String foundDateTime = null;
        dataBase = getDataBase();
        for (int i = 0; i < dataBase.size(); i++) {
            if ((((String) dataBase.get(i)[1]).equals(sugar)) && (((String) dataBase.get(i)[2]).equals(eatTime)) && (((String) dataBase.get(i)[3]).equals(time))) {
                foundDateTime = (String) (dataBase.get(i)[0]);
                break;
            }
        }
        return foundDateTime;
    }
}
