package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBegin;
    public static final String TAG = "MainActivity";
    private MediaPlayer mBackgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBackgroundMusic();

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "In onClick()");
               stopMusic();
               startQuiz();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createBackgroundMusic();
    }

    private void createBackgroundMusic() {
        mBackgroundMusic = MediaPlayer.create(this, R.raw.bensound_slowmotion);
        mBackgroundMusic.start();
    }

    private void stopMusic() {
        if(mBackgroundMusic.isPlaying()) {
            mBackgroundMusic.stop();
//            Log.d(TAG, "Music was playing");
        }
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
//        Resources resources = getResources();
        startActivity(intent);
    }
}
