package com.mark.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCSVThread extends Thread {

    public void run() {
        ArrayList<Object[]>  dataList = new ArrayList<>();
        Search search = new Search();
        Delete delete = new Delete();
        Revise revise = new Revise();
        try {
            File csv = new File("D:/Importcsv/data_base.csv"); // CSV資料檔案

            BufferedReader br = new BufferedReader(new FileReader(csv));
// 要閱讀的最後一行
            int row = 0;
            while (br.ready()) {
                String line = br.readLine();
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
            search.setDataBase(dataList);
            delete.setOldDataBase(dataList);
            revise.setOldDataBase(dataList);
            br.close();

        } catch (FileNotFoundException e) {
// File物件的建立過程中的異常捕獲
            e.printStackTrace();
        } catch (IOException e) {
// BufferedReader在關閉物件捕捉異常
            e.printStackTrace();
        }
    }
}
