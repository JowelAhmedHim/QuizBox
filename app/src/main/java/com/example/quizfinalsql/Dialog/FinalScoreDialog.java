package com.example.quizfinalsql.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizfinalsql.Activity.MainActivity;
import com.example.quizfinalsql.R;

public class FinalScoreDialog {

    private Context mContext;
    private Dialog finalScoreDialog;

    private TextView finalScore;

    public FinalScoreDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void finalScoreDialog(int correctAns,int wrongAns,int totalSizeOfQuiz){

        finalScoreDialog = new Dialog(mContext);
        finalScoreDialog.setContentView(R.layout.final_score_dialog);
        final Button btnFinalScore = (Button)finalScoreDialog.findViewById(R.id.btn_final_score);

        finalScoreCal(correctAns,wrongAns,totalSizeOfQuiz);
        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreDialog.dismiss();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });

        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);
    }

    private void finalScoreCal(int correctAns, int wrongAns, int totalSizeOfQuiz) {


        int tempScore;

        finalScore = (TextView)finalScoreDialog.findViewById(R.id.final_score);

        if (correctAns == totalSizeOfQuiz){
            tempScore = (correctAns*20);
            finalScore.setText("Final Score : " + String.valueOf(tempScore));
        }else if (wrongAns == totalSizeOfQuiz){
            tempScore= 0;
            finalScore.setText("Final Score : " + String.valueOf(tempScore));
        }
        else if (correctAns > wrongAns){
            tempScore = (correctAns*20)-(wrongAns*5);
            finalScore.setText("Final Score : " + String.valueOf(tempScore));
        }
        else if (wrongAns > correctAns){
            tempScore = (wrongAns*5)-(correctAns*20);
            finalScore.setText("Final Score : " + String.valueOf(tempScore));
        }

    }
}
