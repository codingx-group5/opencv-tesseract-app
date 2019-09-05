package com.example.insumon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class History extends AppCompatActivity {
    TextView mTextMessage;
    private Transfer transfer = Scanner.transfer;
    private WriteData2CSVThread writeData2CSVThread;
    private Search search;

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
    }
}
