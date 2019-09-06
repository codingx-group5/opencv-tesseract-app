package com.example.insumon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//import android.support.v7.app.AppCompatActivity;

public class Chart extends AppCompatActivity {
    TextView mTextMessage;
    private LineChart mchart;
    private Button beforemeal;
    private Button aftermeal;
    private static final String TAG = "Scanner";
    private WriteData2CSVThread writeData2CSVThread;
    private ReadCSVThread readCSVThread;
    private Search search;
    private  Transfer transfer = Scanner.transfer;
    public static FindSevenDays findSevenDays;
    private Intent b;
//    private int IndexNum;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent a = new Intent(Chart.this, Scanner.class);
                    startActivity(a);
                    break;
                case R.id.navigation_dashboard:
                    Intent b = new Intent(Chart.this, History.class);

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
        setContentView(R.layout.chart);

        writeData2CSVThread  = transfer.getWriteData2CSVThread();
        readCSVThread = transfer.getReadCSVThread();
        Log.v("readeCsv",""+readCSVThread);
        search = transfer.getSearch();

        findSevenDays = new FindSevenDays();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beforemeal=(Button) findViewById(R.id.before_meal);
        beforemeal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                beforePlot();
            }
        } );


        aftermeal=(Button) findViewById(R.id.after_meal);
        aftermeal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                afterPlot();
            }
        } );

//chart
    }
    private void beforePlot(){
        mchart = findViewById(R.id.chart);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();

        ArrayList bloodSugarList = findSevenDays.bloodSugarBeforeDish();
        int indexNum = findSevenDays.findIndexNumBF();



/*

        search.setDataBase(readCSVThread.getDataList());
//        Log.d("reader element", String.valueOf(readCSVThread.getDataList().get(0)[0]));
        String blood_sugar_0 = (String)search.searchBloodSugarBeforeDish("2019年9月17日 18點54分", "早餐");
        String blood_sugar_1 = (String)search.searchBloodSugarBeforeDish("2019年9月18日 18點54分", "早餐");
        String blood_sugar_2 = (String)search.searchBloodSugarBeforeDish("2019年9月19日 18點54分", "早餐");
        String blood_sugar_3 = (String)search.searchBloodSugarBeforeDish("2019年9月20日 18點54分", "早餐");
        String blood_sugar_4 = (String)search.searchBloodSugarBeforeDish("2019年9月21日 18點54分", "早餐");
        String blood_sugar_5 = (String)search.searchBloodSugarBeforeDish("2019年9月22日 18點54分", "早餐");
        String blood_sugar_6 = (String)search.searchBloodSugarBeforeDish("2019年9月23日 18點54分", "早餐");

        yValues.add(new Entry(0, Integer.parseInt(blood_sugar_0)));
        yValues.add(new Entry(1, Integer.parseInt(blood_sugar_1)));
        yValues.add(new Entry(2, Integer.parseInt(blood_sugar_2)));
        yValues.add(new Entry(3, Integer.parseInt(blood_sugar_3)));
        yValues.add(new Entry(4, Integer.parseInt(blood_sugar_4)));
        yValues.add(new Entry(5, Integer.parseInt(blood_sugar_5)));
        yValues.add(new Entry(6, Integer.parseInt(blood_sugar_6)));  */

        Log.d("indexNum", String.valueOf(indexNum));

        yValues.add(new Entry(0, Integer.parseInt((String) bloodSugarList.get(indexNum-7))));
        yValues.add(new Entry(1, Integer.parseInt((String) bloodSugarList.get(indexNum-6))));
        yValues.add(new Entry(2, Integer.parseInt((String) bloodSugarList.get(indexNum-5))));
        yValues.add(new Entry(3, Integer.parseInt((String) bloodSugarList.get(indexNum-4))));
        yValues.add(new Entry(4, Integer.parseInt((String) bloodSugarList.get(indexNum-3))));
        yValues.add(new Entry(5, Integer.parseInt((String) bloodSugarList.get(indexNum-2))));
        yValues.add(new Entry(6, Integer.parseInt((String) bloodSugarList.get(indexNum-1))));

        LineDataSet set1 = new LineDataSet(yValues, "Before Meal");
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

    private void afterPlot(){
        //getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//chart
        mchart = findViewById(R.id.chart);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();

        ArrayList bloodSugarList = findSevenDays.bloodSugarAfterDish();
        int indexNum = findSevenDays.findIndexNumAF();

        yValues.add(new Entry(0, Integer.parseInt((String) bloodSugarList.get(indexNum-7))));
        yValues.add(new Entry(1, Integer.parseInt((String) bloodSugarList.get(indexNum-6))));
        yValues.add(new Entry(2, Integer.parseInt((String) bloodSugarList.get(indexNum-5))));
        yValues.add(new Entry(3, Integer.parseInt((String) bloodSugarList.get(indexNum-4))));
        yValues.add(new Entry(4, Integer.parseInt((String) bloodSugarList.get(indexNum-3))));
        yValues.add(new Entry(5, Integer.parseInt((String) bloodSugarList.get(indexNum-2))));
        yValues.add(new Entry(6, Integer.parseInt((String) bloodSugarList.get(indexNum-1))));
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
}