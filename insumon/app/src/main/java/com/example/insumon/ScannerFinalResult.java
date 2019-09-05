package com.example.insumon;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ScannerFinalResult extends AppCompatActivity {
    private TextView mTextMessage;
    private static final String TAG = "ScannerFinalResult";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private WriteData2CSVThread writeData2CSVThread;
    private Search search;
    private Transfer transfer = Scanner.transfer;

    private ReadCSVThread readCSVThread;
    private String date;
    private String time;
    private Button enter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_dashboard:
                    Intent a = new Intent(ScannerFinalResult.this, History.class);
                    startActivity(a);
                    //Scanner.this.finish();
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(ScannerFinalResult.this, Chart.class);
                    startActivity(b);
                    //Scanner.this.finish();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_final_result);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.d("dit2", String.valueOf(getFilesDir()));


//        transfer=(Transfer) getIntent().getSerializableExtra("Transfer");
        writeData2CSVThread  = transfer.getWriteData2CSVThread();
        search = transfer.getSearch();
        readCSVThread=transfer.getReadCSVThread();




        Spinner spinner = (Spinner)findViewById(R.id.meal);
        final String[] lunch = {"飯後", "飯前"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(ScannerFinalResult.this,
                android.R.layout.simple_spinner_dropdown_item,
                lunch);spinner.setAdapter(lunchList);

        mDisplayDate =(TextView) findViewById(R.id.SelectDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Calendar cal =Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);

//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
//
//                Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
//
//                String str = formatter.format(curDate);
//                mDisplayDate.setText(str);


                DatePickerDialog dialog= new DatePickerDialog(ScannerFinalResult.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: date:"+year+"年"+month+"月"+day+"日");
                String date=year+"年"+month+"月"+day+"日";
                mDisplayDate.setText(date);
            }
        };




        mDisplayTime =(TextView) findViewById(R.id.SelectTime);
        mDisplayTime.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Calendar cal =Calendar.getInstance();
                int hourOfDay =cal.get(Calendar.HOUR_OF_DAY);
                int minute =cal.get(Calendar.MINUTE);


                TimePickerDialog dialog= new TimePickerDialog(ScannerFinalResult.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mTimeSetListener,hourOfDay,minute,false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: time:"+hourOfDay+"分"+minute+"秒");
                String time=hourOfDay+"點"+minute+"分";
                mDisplayTime.setText(time);
            }
        };


//        enter= findViewById(R.id.enter2);
//        enter.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                inputDataBase();
//            }
//        } );

    }


    public void inputDataBase(View view) {
        Log.d("dateTime", date+time);
        int eatTime;

        Spinner mySpinner = (Spinner) findViewById(R.id.meal);
        String text = mySpinner.getSelectedItem().toString();
        if (text=="飯前"){
            eatTime=0;
        }else eatTime=1;


        this.writeData2CSVThread.setDateTime(date+time);
        this.writeData2CSVThread.setBloodSugar("180");
        this.writeData2CSVThread.setEatTime(eatTime);
        this.writeData2CSVThread.setTime(1);
        this.writeData2CSVThread.run();
        this.readCSVThread.run();

        search.setDataBase(readCSVThread.getDataList());
        String blood =search.searchBloodSugarAfterDish(date + time, "1");
        Log.d("blood", blood);
    }

}
