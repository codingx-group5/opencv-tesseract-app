package com.example.insumon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class zzzPage extends AppCompatActivity {
    TextView mTextMessage;
    //private static final String TAG = "Scanner";
    //    private LineChart mchart;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_dashboard:
                    Intent a = new Intent(zzzPage.this, History.class);
                    startActivity(a);
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(zzzPage.this, Chart.class);
                    startActivity(b);
                    break;
            }
            return false;
        }
    };



//    InputStream inputStream;
//    String[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_manual_input);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}