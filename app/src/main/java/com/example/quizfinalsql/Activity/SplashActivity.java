package com.example.quizfinalsql.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.quizfinalsql.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    Animation middleAnimation;
    TextView appName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        appName = findViewById(R.id.appName);
        appName.setAnimation(middleAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent st = new Intent(SplashActivity.this, QuizCategory.class);
                startActivity(st);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}