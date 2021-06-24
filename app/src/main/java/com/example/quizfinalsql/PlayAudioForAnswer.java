package com.example.quizfinalsql;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.core.view.MenuItemCompat;

public class PlayAudioForAnswer {

    private Context mContext;
    private MediaPlayer mediaPlayer;

    public PlayAudioForAnswer(Context mContext) {
        this.mContext = mContext;
    }

    public void setAudioForAnswer(int flag){
        switch (flag){
            case 1:
                int mcorrectAudio = R.raw.right;
                playMusic(mcorrectAudio);
                break;
            case 2:
                int mwrongAudio = R.raw.wrong;
                playMusic(mwrongAudio);
                break;
        }
    }

    private void playMusic(int audiofile) {

        mediaPlayer = MediaPlayer.create(mContext,audiofile);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
    }
}
