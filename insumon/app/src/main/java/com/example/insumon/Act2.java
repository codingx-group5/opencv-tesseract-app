package com.example.insumon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Act2 extends AppCompatActivity {
    TextView mTextMessage;
    private LineChart mchart;
    private static final String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent a = new Intent(Act2.this, MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.navigation_dashboard:
                    Intent b = new Intent(Act2.this, Act1.class);
                    startActivity(b);
                    break;

                case R.id.navigation_notifications:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//chart
        mchart = findViewById(R.id.chart);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 60));
        yValues.add(new Entry(1, 50));
        yValues.add(new Entry(2, 60));
        yValues.add(new Entry(3, 20));
        yValues.add(new Entry(4, 40));
        yValues.add(new Entry(5, 10));
        yValues.add(new Entry(6, 70));
        LineDataSet set1 = new LineDataSet(yValues, "After Meal");
        set1.setFillAlpha(Color.GRAY);
        set1.setCircleColor(Color.GRAY);
        set1.setCircleRadius((float) 5.0);
        set1.setCircleHoleRadius((float) 2.0);
        set1.setColor(Color.GRAY);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mchart.setDescription(null);
        mchart.getXAxis().setDrawGridLines(false);
        mchart.setData(data);
        mchart.getAxisRight().setEnabled(false);
        XAxis xAxis = mchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }
    /*

//csv


        Search search = new Search();
//給定血糖、飯前/飯後、早/中/晚搜尋日期時間
        Search.searchDateTime( "90", 0, 1);*/

}
