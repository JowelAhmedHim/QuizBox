package com.example.quizfinalsql.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.quizfinalsql.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton fb,youtube,linkIn,github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();

        fb.setOnClickListener(this);
        youtube.setOnClickListener(this);
        linkIn.setOnClickListener(this);
        github.setOnClickListener(this);
    }
    private void init() {
        fb = findViewById(R.id.fb_btn);
        youtube = findViewById(R.id.youtube_btn);
        linkIn = findViewById(R.id.linkIn_btn);
        github = findViewById(R.id.github_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fb_btn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/jowelahmedhim")));
                break;
            case R.id.youtube_btn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UClDog-gMe4GC3lOhpX4P_Nw")));
                break;
            case R.id.linkIn_btn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/jowelahmedhim/")));
                break;
            case R.id.github_btn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JowelAhmedHim")));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AboutActivity.this,QuizCategory.class));
        super.onBackPressed();
    }
}