package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mark.csv.WriteData2CSVThread;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WriteData2CSVThread writeData2CSVThread = new WriteData2CSVThread();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //還沒獲取權限要做什麼呢

            //和使用者要求權限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }else{
            //以獲取權限要做的事情

            Toast.makeText(this, "已經拿到權限囉!", Toast.LENGTH_SHORT).show();
        }


        writeData2CSVThread.setBloodSugar("156");
        writeData2CSVThread.run();
        Log.i("bloodSugar:",writeData2CSVThread.getBloodSugar());
    }
}
