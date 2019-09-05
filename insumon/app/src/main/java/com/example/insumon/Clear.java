package com.example.insumon;

import java.util.ArrayList;

public class Clear {
    private MainActivity app;
    private ArrayList<Object[]> oldDataBase ;

    public void run(){
        Delete delete = new Delete(app);
        int len = oldDataBase.size();
        for(int i = 0; i < len; i++){
            String dateTime = (String)oldDataBase.get(i)[0];
            delete.setDeleted(dateTime);
            delete.run();
        }
    }

    public void setOldDataBase(ArrayList<Object[]> oldDataBase){
        this.oldDataBase = oldDataBase;
    }
}
