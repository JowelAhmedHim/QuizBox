package com.example.quizfinalsql.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizfinalsql.R;

public class FinalResult extends AppCompatActivity {

    TextView totalQuestion,correctAnswer,wrongAnswer,highScoretxt;
    Button mainMenu;

    int highScore = 0;
    public static final String SHARED_PREFERENCE = "shared_preference";
    public static final String SHARED_PREFERENCE_HIGH_SCORE="shared_preference_high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        totalQuestion = findViewById(R.id.total_question);
        correctAnswer =  findViewById(R.id.correct_ans);
        wrongAnswer = findViewById(R.id.wrong_ans);
        mainMenu =  findViewById(R.id.main_menu);
        highScoretxt = findViewById(R.id.highs_score);


        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizCategory.class);
                startActivity(intent);
            }
        });

        loadHighScore();

        Intent intent = getIntent();
        int score = intent.getIntExtra("UserScore",0);
        int totalquestion = intent.getIntExtra("TotalQuestion",0);
        int correctanswer = intent.getIntExtra("CorrectAnswer",0);
        int wronganswer = intent.getIntExtra("WrongAnswer",0);


        totalQuestion.setText("Total Question : " +String.valueOf(totalquestion));
        correctAnswer.setText("Correct Answer : " +String.valueOf(correctanswer));
        wrongAnswer.setText("Wrong Answer : " +String.valueOf(wronganswer));


        if(score > highScore){
            updatehighScore(score);
        }
    }

    private void updatehighScore(int newhighScore) {
        highScore = newhighScore;
        highScoretxt.setText("High Score : "+ String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE,highScore);
        editor.apply();
    }

    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE,0);
        highScoretxt.setText("High Score : "+ String.valueOf(highScore));

    }
}