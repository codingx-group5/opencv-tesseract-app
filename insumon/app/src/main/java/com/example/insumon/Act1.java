package com.example.insumon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Act1 extends AppCompatActivity {
    TextView mTextMessage;
    private Transfer transfer;
    private WriteData2CSVThread writeData2CSVThread;
    private Search search;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent a = new Intent(Act1.this, MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.navigation_dashboard:
                    break;
                case R.id.navigation_notifications:
                    Intent b = new Intent(Act1.this, Act2.class);
                    startActivity(b);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message1);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        transfer=(Transfer) getIntent().getSerializableExtra("Transfer");
        writeData2CSVThread  = transfer.getWriteData2CSVThread();
        search = transfer.getSearch();
    }
}
