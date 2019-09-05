package com.example.insumon;

import android.content.Context;
import android.util.Log;

import com.example.insumon.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCSVThread {
    private Context context;
    private FileInputStream in = null;
    public ArrayList<Object[]>  dataList;
    public ReadCSVThread(Context context){
        this.context = context;
    }

    public void run() {
        try {
            in = context.openFileInput("data.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("reader", "running");
        dataList = new ArrayList<>();


        StringBuffer data = new StringBuffer();
        try {
            //開啟 getFilesDir() 目錄底下名稱為 test.txt 檔案

            //讀取該檔案的內容
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, "utf-8"));
            String line;
            // 要閱讀的最後一行
            int row = 0;
            while (reader.ready()) {
                line = reader.readLine();
// 資料元素分為一行
                StringTokenizer st = new StringTokenizer(line, ",");
                Object[] tokens = new Object[4];
                int colIndex = 0;
                while (st.hasMoreTokens()) {
// 每個元素都顯示在一個製表符分隔的線
                    tokens[colIndex] = st.nextToken();
                    colIndex++;
                }
                row++;
                dataList.add(tokens);
                System.out.println();
            }
        } catch (Exception e) {
            ;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                ;
            }
        }

//        this.delete.setOldDataBase(dataList);
//        this.revise.setOldDataBase(dataList);
        Object[] test = new Object[4];
        test = dataList.get(0);
        for(int i = 0; i < 4; i++) {
            Log.d("tag4", String.valueOf(test[i]));
        }
    }

//    public Search getSearch(){
//        Log.d("getsearch","running");
//    }

    public ArrayList<Object[]> getDataList(){
        return  dataList;
    }
    public int getTest(){
        return 100;
    }
}
