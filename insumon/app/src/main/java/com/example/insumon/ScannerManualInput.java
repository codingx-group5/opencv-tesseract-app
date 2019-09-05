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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ScannerManualInput extends AppCompatActivity {
    private TextView mTextMessage;
    private static final String TAG = "ScannerManualInput";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private WriteData2CSVThread writeData2CSVThread;
    private Search search;
    private EditText inputSugar;
    private Transfer transfer = Scanner.transfer;
    private ReadCSVThread readCSVThread;
    private String date;
    private String time;
    private Spinner spinner;
    private Spinner spinner2;
    private Button check;

    private static int indexNum=0;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_dashboard:
                    Intent a = new Intent(ScannerManualInput.this, History.class);
                    startActivity(a);
                    //Scanner.this.finish();
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(ScannerManualInput.this, Chart.class);
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
        setContentView(R.layout.scanner_manual_input);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        writeData2CSVThread  = transfer.getWriteData2CSVThread();
        search = transfer.getSearch();
        readCSVThread=transfer.getReadCSVThread();




        spinner = (Spinner)findViewById(R.id.meal);
        final String[] lunch = {"飯前", "飯後"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(ScannerManualInput.this,
                android.R.layout.simple_spinner_dropdown_item,
                lunch);spinner.setAdapter(lunchList);

        spinner2 = (Spinner)findViewById(R.id.spinnermealtype);
        final String[] mealType = {"早餐", "午餐","晚餐"};
        ArrayAdapter<String> mealTypeList = new ArrayAdapter<>(ScannerManualInput.this,
                android.R.layout.simple_spinner_dropdown_item,
                mealType);spinner2.setAdapter(mealTypeList);

        mDisplayDate =(TextView) findViewById(R.id.SelectDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Calendar cal =Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(ScannerManualInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int correct_month = month+1;
                Log.d(TAG, "onDateSet: date:"+year+"年"+correct_month+"月"+day+"日");
                Log.d("ondateset","running");
                date=year+"年"+correct_month+"月"+day+"日";
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


                TimePickerDialog dialog= new TimePickerDialog(ScannerManualInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mTimeSetListener,hourOfDay,minute,false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: time:"+hourOfDay+"分"+minute+"秒");
                time=hourOfDay+"點"+minute+"分";
                mDisplayTime.setText(time);


            }
        };




    }

    public void inputDataBase(View view) {
        inputSugar=findViewById(R.id.inputSugar);
        String blood_sugar = inputSugar.getText().toString();
        Log.d("inputdatabase","ok");
        Log.d("dateTime", date+time);

/*        String eatTime;
        String text1 = spinner.getSelectedItem().toString();
        if (text1.equals("飯前")){
            eatTime="0";
        }else eatTime="1";


        String mealType;
        String text2 = spinner.getSelectedItem().toString();
        if (text2.equals("早餐")){
            eatTime="0";
        }else if
        else eatTime="1";*/


        String eatTime = spinner.getSelectedItem().toString();
        String mealType = spinner2.getSelectedItem().toString();


        this.writeData2CSVThread.setDateTime(date+" "+time);
        this.writeData2CSVThread.setBloodSugar(blood_sugar);
        this.writeData2CSVThread.setEatTime(eatTime);
        this.writeData2CSVThread.setTime(mealType);
        this.writeData2CSVThread.run();
        indexNum+=1;
        this.readCSVThread.run();

        Intent intent = new Intent(this, History.class);
        startActivity(intent);

//        search.setDataBase(readCSVThread.getDataList());
//        String blood =search.searchBloodSugarAfterDish(date + time, "1");
//        Log.d("blood", blood);
    }


}
