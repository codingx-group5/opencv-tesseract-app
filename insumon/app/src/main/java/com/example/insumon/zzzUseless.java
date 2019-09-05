package com.example.insumon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class zzzUseless extends AppCompatActivity {
    ImageView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zzzuseless);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 1500);

        welcome = (ImageView) findViewById(R.id.imageView2);
        Animation am = new AlphaAnimation(0.0f, 1.0f);
        am.setDuration(1500);
        am.setRepeatCount(1);
        welcome.startAnimation(am);

    }

    private static final int GOTO_MAIN_ACTIVITY = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    //將原本Activity的換成MainActivity
                    intent.setClass(zzzUseless.this, Scanner.class);
                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }
        }

    };
}
