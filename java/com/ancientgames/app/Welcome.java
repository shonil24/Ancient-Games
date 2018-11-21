package com.ancientgames.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class Welcome extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        Animation imganim = AnimationUtils.loadAnimation(this,R.anim.splashimgfade);
       /* Animation logoanm = AnimationUtils.loadAnimation(this,R.anim.splashlogozoom); */

        tv.startAnimation(imganim);
        iv.startAnimation(imganim);

        final Intent intent = new Intent(this,LoginActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();


    }
}