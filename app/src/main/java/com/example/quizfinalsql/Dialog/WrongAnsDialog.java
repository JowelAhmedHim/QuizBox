package com.example.quizfinalsql.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizfinalsql.Activity.MainActivity;
import com.example.quizfinalsql.R;

public class WrongAnsDialog {

    private Context mContext;

    private Dialog wrongAnsDialog;
    private MainActivity mMainActivity;

    public WrongAnsDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void wrongDialog(String correctAnswer, final MainActivity mainActivity){
        mMainActivity = mainActivity;
        wrongAnsDialog = new Dialog(mContext);
        wrongAnsDialog.setContentView(R.layout.wrong_dialog);
        Button button = (Button)wrongAnsDialog.findViewById(R.id.btn_wrong);

        TextView textView =(TextView) wrongAnsDialog.findViewById(R.id.txt_wrong);
        textView.setText("Answer : " + String.valueOf(correctAnswer));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongAnsDialog.dismiss();
                mMainActivity.showQuestions();
            }
        });
        wrongAnsDialog.show();
        wrongAnsDialog.setCancelable(false);
        wrongAnsDialog.setCanceledOnTouchOutside(false);
    }
}
