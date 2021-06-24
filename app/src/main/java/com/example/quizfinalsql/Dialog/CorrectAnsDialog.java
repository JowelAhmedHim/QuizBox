package com.example.quizfinalsql.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizfinalsql.Activity.MainActivity;
import com.example.quizfinalsql.R;

public class CorrectAnsDialog {

    private Context mContext;

    private Dialog correctAnsDialog;
    private MainActivity mMainActivity;

    public CorrectAnsDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score, final MainActivity mainActivity){
        mMainActivity = mainActivity;
        correctAnsDialog = new Dialog(mContext);
        correctAnsDialog.setContentView(R.layout.right_dialog);
        Button button = (Button)correctAnsDialog.findViewById(R.id.btn_correct);

        score(score);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnsDialog.dismiss();
                mMainActivity.showQuestions();
            }
        });
        correctAnsDialog.show();
        correctAnsDialog.setCancelable(false);
        correctAnsDialog.setCanceledOnTouchOutside(false);
    }

    private void score(int score) {
        TextView textView =(TextView) correctAnsDialog.findViewById(R.id.txt_correct);
        textView.setText("Score : " + String.valueOf(score));
    }
}
