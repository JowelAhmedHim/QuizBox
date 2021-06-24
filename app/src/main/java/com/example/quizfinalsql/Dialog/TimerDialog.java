package com.example.quizfinalsql.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.quizfinalsql.Activity.QuizCategory;
import com.example.quizfinalsql.R;

public class TimerDialog {

   private Context context;
   private Dialog timerDialog;

    public TimerDialog(Context context) {
        this.context = context;
    }

    public void timerDialog(){
        timerDialog = new  Dialog(context);
        timerDialog.setContentView(R.layout.timer_dialog);

        final Button btnTimesUp = timerDialog.findViewById(R.id.timerbtn);
        btnTimesUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerDialog.dismiss();
                Intent intent = new Intent(context, QuizCategory.class);
                context.startActivity(intent);
            }
        });
        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);

    }
}
