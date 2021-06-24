package com.example.quizfinalsql.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizfinalsql.Dialog.CorrectAnsDialog;
import com.example.quizfinalsql.Dialog.FinalScoreDialog;
import com.example.quizfinalsql.PlayAudioForAnswer;
import com.example.quizfinalsql.Model.QuestionModel;
import com.example.quizfinalsql.Database.QuizDbHelper;
import com.example.quizfinalsql.R;
import com.example.quizfinalsql.Dialog.TimerDialog;
import com.example.quizfinalsql.Dialog.WrongAnsDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView scoreView, rightAns, wrongAns, questionNo, questionView,timer;
    private RadioButton option1, option2, option3, option4;
    private RadioGroup radioGroup;
    private Button submit;


    private ArrayList<QuestionModel> questionsList;
    private int questionCounter;
    private int questionsTotalCount;
    private QuestionModel currentQuestion;
    private boolean answer;


    private Handler handler = new Handler();

    private ColorStateList btnLabelColor;

    private int correctans = 0;
    private int wrongans = 0;
    private int score = 0;


    private FinalScoreDialog finalScoreDialog;
    private int totalSizeOfQuiz = 0;


    private CorrectAnsDialog correctAnsDialog;
    private WrongAnsDialog wrongAnsDialog;
    private TimerDialog timerDialog;

    private PlayAudioForAnswer playAudioForAnswer;
    private int flag = 0;

    private static final long COUNTDOWN_IN_MILLS = 30000;
    private CountDownTimer countDownTimer;
    private  long timeLeftInMills;


    private String categoryValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupUI();
        Intent intent = getIntent();
        categoryValue = intent.getStringExtra("Category");
        fetchDB();

        btnLabelColor = option1.getTextColors();

        finalScoreDialog = new FinalScoreDialog(this);
        correctAnsDialog = new CorrectAnsDialog(this);
        wrongAnsDialog = new WrongAnsDialog(this);
        timerDialog = new TimerDialog(this);
        playAudioForAnswer = new PlayAudioForAnswer(this);
    }

    private void setupUI() {
        scoreView = findViewById(R.id.scoreView);
        rightAns = findViewById(R.id.rightans);
        wrongAns = findViewById(R.id.wrongans);
        questionNo = findViewById(R.id.questionno);
        questionView = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radiogroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submit = findViewById(R.id.button);
        timer = findViewById(R.id.timer);

    }

    private void fetchDB() {
        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        questionsList = quizDbHelper.getAllQuestionsWithCategory(categoryValue);
        startQuiz();
    }

    public void showQuestions() {
        radioGroup.clearCheck();

        option1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        option2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        option3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
        option4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));

        option1.setTextColor(Color.BLACK);
        option2.setTextColor(Color.BLACK);
        option3.setTextColor(Color.BLACK);
        option4.setTextColor(Color.BLACK);



        if (questionCounter < questionsTotalCount) {
            currentQuestion = questionsList.get(questionCounter);
            questionView.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            questionCounter++;
            answer = false;

            submit.setText("Confirm");
            questionNo.setText("Question : " + questionCounter + "/" + questionsTotalCount);

            timeLeftInMills = COUNTDOWN_IN_MILLS;
            startCountDown();


        } else {
            totalSizeOfQuiz = questionsList.size();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   // finalScoreDialog.finalScoreDialog(correctans, wrongans, totalSizeOfQuiz);
                    finalResult();
                }
            }, 2000);
        }
    }

    private void startQuiz() {

        questionsTotalCount = questionsList.size();
        Collections.shuffle(questionsList);

        showQuestions();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.option1:
                        option1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        option2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        break;
                    case R.id.option2:
                        option1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        option3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        break;
                    case R.id.option3:
                        option1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        option4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        break;
                    case R.id.option4:
                        option1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_background));
                        option4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answer) {

                    if (option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) {
                        quizeOperation();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void quizeOperation() {

        answer = true;
        countDownTimer.cancel();
        RadioButton rbselected = findViewById(radioGroup.getCheckedRadioButtonId());

        int answerNr = radioGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr, rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                if (currentQuestion.getAnswerNr() == answerNr) {
                    option1.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_currect));
                    option1.setTextColor(Color.WHITE);

                    correctans++;
                    rightAns.setText(""+correctans);

                    score += 10;
                    scoreView.setText(""+score);
                    correctAnsDialog.correctDialog(score,MainActivity.this);

                    flag = 1 ;
                    playAudioForAnswer.setAudioForAnswer(flag);




                } else {
                    changeIncorrectColor(rbselected);

                    wrongans++;
                    wrongAns.setText("" + String.valueOf(wrongans));

                    String correctAnswer = (String) option1.getText();
                    wrongAnsDialog.wrongDialog(correctAnswer,MainActivity.this);

                    flag = 2 ;
                    playAudioForAnswer.setAudioForAnswer(flag);

                }
                break;
            case 2:
                if (currentQuestion.getAnswerNr() == answerNr) {
                    option2.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_currect));
                    option2.setTextColor(Color.WHITE);

                    correctans++;
                    rightAns.setText("" + correctans);

                    score += 10;
                    scoreView.setText("" + score);
                    correctAnsDialog.correctDialog(score,MainActivity.this);

                    flag = 1 ;
                    playAudioForAnswer.setAudioForAnswer(flag);



                } else {
                    changeIncorrectColor(rbselected);

                    wrongans++;
                    wrongAns.setText("" + String.valueOf(wrongans));

                    String correctAnswer = (String) option2.getText();
                    wrongAnsDialog.wrongDialog(correctAnswer,MainActivity.this);

                    flag = 2 ;
                    playAudioForAnswer.setAudioForAnswer(flag);


                }
                break;
            case 3:
                if (currentQuestion.getAnswerNr() == answerNr) {
                    option3.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_currect));
                    option3.setTextColor(Color.WHITE);

                    correctans++;
                    rightAns.setText("" + correctans);

                    score += 10;
                    scoreView.setText("" + score);
                    correctAnsDialog.correctDialog(score,MainActivity.this);

                    flag = 1 ;
                    playAudioForAnswer.setAudioForAnswer(flag);



                } else {
                    changeIncorrectColor(rbselected);

                    wrongans++;
                    wrongAns.setText("" + String.valueOf(wrongans));

                    String correctAnswer = (String) option3.getText();
                    wrongAnsDialog.wrongDialog(correctAnswer,MainActivity.this);

                    flag = 2 ;
                    playAudioForAnswer.setAudioForAnswer(flag);


                }
                break;
            case 4:
                if (currentQuestion.getAnswerNr() == answerNr) {
                    option4.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_currect));
                    option4.setTextColor(Color.WHITE);

                    correctans++;
                    rightAns.setText(" " + correctans);

                    score += 10;
                    scoreView.setText(" " + score);
                    correctAnsDialog.correctDialog(score,MainActivity.this);

                    flag = 1 ;
                    playAudioForAnswer.setAudioForAnswer(flag);

                } else {
                    changeIncorrectColor(rbselected);

                    wrongans++;
                    wrongAns.setText("" + String.valueOf(wrongans));

                    String correctAnswer = (String) option4.getText();
                    wrongAnsDialog.wrongDialog(correctAnswer,MainActivity.this);

                    flag = 2 ;
                    playAudioForAnswer.setAudioForAnswer(flag);


                }
                break;

        }

    }

    private void changeIncorrectColor(RadioButton rbselected) {

        rbselected.setBackground(ContextCompat.getDrawable(this, R.drawable.when_answer_wrong));
        rbselected.setTextColor(Color.WHITE);

    }


    private void startCountDown(){

        countDownTimer = new CountDownTimer(timeLeftInMills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMills = millisUntilFinished;
                updateCountDownTimer();
            }

            @Override
            public void onFinish() {
                timeLeftInMills = 0;
                updateCountDownTimer();

            }
        }.start();
    }

    private  void updateCountDownTimer(){
        int minute = (int) (timeLeftInMills/1000) / 60;
        int seconds = (int) (timeLeftInMills/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minute,seconds);
        timer.setText(timeFormatted);

        if(timeLeftInMills<10000){
            timer.setTextColor(Color.RED);

        }else {
            timer.setTextColor(btnLabelColor);
        }

        if(timeLeftInMills == 0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                      timerDialog.timerDialog();
                }
            },1000);
        }

    }



    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }

    private void finalResult(){
        Intent resultdata = new Intent(this, FinalResult.class);
        resultdata.putExtra("UserScore",score);
        resultdata.putExtra("TotalQuestion",questionsTotalCount);
        resultdata.putExtra("CorrectAnswer",correctans);
        resultdata.putExtra("WrongAnswer",wrongans);
        startActivity(resultdata);

    }

    @Override
    public void onBackPressed() {

            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle("Do you want to exit?");
            alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), QuizCategory.class);
                    startActivity(intent);
                }
            });
            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog =alertdialog.create();
            alertDialog.show();
    }
}