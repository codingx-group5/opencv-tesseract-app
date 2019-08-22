package com.example.insumon;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.texv);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



//        imageView =(ImageView) findViewById(R.id.imageView);
    }
//    ImageView imageView;
//    public void opencv(){
//        final Mat source = Imgcodecs.imread("C:/blood/test/145.jpg", CvType.CV_8UC1);
//        Bitmap bitmap= Bitmap.createBitmap(source.cols(), source.rows(),Bitmap.Config.RGB_565);
//        Utils.matToBitmap(source,bitmap);
//        imageView.setImageBitmap(bitmap);
//    }

}
