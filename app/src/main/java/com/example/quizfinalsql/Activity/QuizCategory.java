package com.example.quizfinalsql.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.quizfinalsql.Constains;
import com.example.quizfinalsql.R;

public class QuizCategory extends AppCompatActivity implements View.OnClickListener  {

    private long backPressedTime;
    CardView bangla,english,gScience,computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.ic_baseline_home_24);
        toolbar.setTitle("Quizzer");

        init();


        bangla.setOnClickListener(this);
        english.setOnClickListener(this);
        gScience.setOnClickListener(this);
        computer.setOnClickListener(this);

    }

    private void init() {
        bangla = findViewById(R.id.bangla_card);
        english = findViewById(R.id.english_card);
        gScience = findViewById(R.id.gScience_card);
        computer = findViewById(R.id.computer_card);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bangla_card:
                Intent intentBangla = new Intent(QuizCategory.this, MainActivity.class);
                intentBangla.putExtra("Category", Constains.BANGLA);
                startActivity(intentBangla);
                finish();
                break;
            case R.id.english_card:
                Intent intentEnglish = new Intent(QuizCategory.this,MainActivity.class);
                intentEnglish.putExtra("Category",Constains.ENGLISH);
                startActivity(intentEnglish);
                finish();
                break;
            case R.id.gScience_card:
                Intent intentMath = new Intent(QuizCategory.this,MainActivity.class);
                intentMath.putExtra("Category",Constains.GSCIENCE);
                startActivity(intentMath);
                finish();
                break;
            case R.id.computer_card:
                Intent intentScience = new Intent(QuizCategory.this,MainActivity.class);
                intentScience.putExtra("Category",Constains.MATH);
                startActivity(intentScience);
                finish();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis()){
            finish();

        }else {
            Toast.makeText(this, "Please press again back button", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.about:
                Intent intent = new Intent(QuizCategory.this, AboutActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}