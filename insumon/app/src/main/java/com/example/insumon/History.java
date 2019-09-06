package com.example.insumon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    TextView mTextMessage;
    private Transfer transfer = Scanner.transfer;
    private WriteData2CSVThread writeData2CSVThread;
    private Search search;
    private FindSevenDays findSevenDays = Chart.findSevenDays;
    private TextView rec1;
    private TextView rec2;
    private TextView rec3;
    private TextView rec4;
    private TextView rec5;
    private TextView rec6;
    private TextView rec7;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent a = new Intent(History.this, Scanner.class);
                    startActivity(a);
                    break;
                case R.id.navigation_dashboard:
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(History.this, Chart.class);
                    startActivity(b);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message1);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        writeData2CSVThread  = transfer.getWriteData2CSVThread();
        search = transfer.getSearch();
//
//        rec1 = findViewById(R.id.rec1);
//        rec2 = findViewById(R.id.rec2);
//        rec3 = findViewById(R.id.rec3);
//        rec4 = findViewById(R.id.rec4);
//        rec5 = findViewById(R.id.rec5);
//        rec6 = findViewById(R.id.rec6);
//        rec7 = findViewById(R.id.rec7);
//
//        int indexNum = findSevenDays.findIndexNumTotal();
//        ArrayList dataList = findSevenDays.getTotalList();
//
//        String sugar1 = String.valueOf(dataList.get(indexNum - 1));
//        String sugar2 = String.valueOf(dataList.get(indexNum - 2));
//        String sugar3 = String.valueOf(dataList.get(indexNum - 3));
//        String sugar4 = String.valueOf(dataList.get(indexNum - 4));
//        String sugar5 = String.valueOf(dataList.get(indexNum - 5));
//        String sugar6 = String.valueOf(dataList.get(indexNum - 6));
//        String sugar7 = String.valueOf(dataList.get(indexNum - 7));
//
//        rec1.setText(sugar1 + "2019年9月6日 11點41分 早餐 飯前");
//        rec2.setText(sugar2 + "2019年9月6日 11點41分 早餐 飯前");
//        rec3.setText(sugar3 + "2019年9月6日 11點41分 早餐 飯前");
//        rec4.setText(sugar4 + "2019年9月6日 11點41分 早餐 飯前");
//        rec5.setText(sugar5 + "2019年9月6日 11點41分 早餐 飯前");
//        rec6.setText(sugar6 + "2019年9月6日 11點41分 早餐 飯前");
//        rec7.setText(sugar7 + "2019年9月6日 11點41分 早餐 飯前");


    }
}
