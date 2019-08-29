package com.example.insumon;
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

    public int searchBloodSugarBeforeDish( String dateTime, int time){
        int foundSugar = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && ((int)dataBase.get(i)[2] == 0) && ((int)dataBase.get(i)[3] == time)){
                foundSugar = (int)dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public int searchBloodSugarAfterDish( String dateTime, int time){
        int foundSugar = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && ((int)dataBase.get(i)[2] == 1) && ((int)dataBase.get(i)[3] == time)){
                foundSugar = (int)dataBase.get(i)[1];
                break;
            }
        }
        return foundSugar;
    }

    public int searchEatTime( String dateTime, String sugar, int time){
        int foundEatTime = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && ((int)dataBase.get(i)[3] == time)){
                foundEatTime = (int)dataBase.get(i)[2];
                break;
            }
        }
        return foundEatTime;
    }

    public int searchtime( String dateTime, String sugar, int eatTime){
        int foundTime = 0;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[0]).equals(dateTime)) && (((String)dataBase.get(i)[1]).equals(sugar)) && ((int)dataBase.get(i)[2] == eatTime)){
                foundTime = (int)dataBase.get(i)[3];
                break;
            }
        }
        return foundTime;
    }

    public static String searchDateTime(String sugar, int eatTime, int time){
        String foundDateTime = null;
        search = new Search();
        dataBase = search.getDataBase();
        for(int i = 0; i < dataBase.size(); i++){
            if((((String)dataBase.get(i)[1]).equals(sugar)) && ((int)dataBase.get(i)[2] == eatTime) && ((int)dataBase.get(i)[3] == time)){
                foundDateTime = (String)(dataBase.get(i)[0]);
                break;
            }
        }
        return foundDateTime;
    }
}
