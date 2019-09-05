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

    public int searchBloodSugarBeforeDish( String dateTime, String time){
        int foundSugar = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[2]).equals("0")) && (((String)dataBase.get(i)[3]).equals(time))){
                foundSugar = (int)dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public String searchBloodSugarAfterDish( String dateTime, String time){
        Log.d("searching", "running");
        String foundSugar = "";

        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[2]).equals("1")) && (((String)dataBase.get(i)[3]).equals(time))){
                foundSugar = (String) dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public int searchEatTime( String dateTime, String sugar, String time){
        int foundEatTime = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && (((String)dataBase.get(i)[3]).equals(time))){
                foundEatTime = (int)dataBase.get(i)[2];
                break;
            }
        }
        return foundEatTime;
    }

    public int searchtime( String dateTime, String sugar, String eatTime){
        int foundTime = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && (((String)dataBase.get(i)[2]).equals(eatTime))){
                foundTime = (int)dataBase.get(i)[3];
                break;
            }
        }
        return foundTime;
    }

    public static String searchDateTime(String sugar, String eatTime, String time){
        String foundDateTime = null;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[1]).equals(sugar)) && (((String)dataBase.get(i)[2]).equals(eatTime)) && (((String)dataBase.get(i)[3]).equals(time))){
                foundDateTime = (String)(dataBase.get(i)[0]);
                break;
            }
        }
        return foundDateTime;
    }
}
