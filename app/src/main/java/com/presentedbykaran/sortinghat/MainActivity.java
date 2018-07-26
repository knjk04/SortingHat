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
    public static final String TAG = MainActivity.class.getSimpleName();
//    private MediaPlayer mBackgroundMusic;
    private MusicController mMusicController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBackgroundMusic();

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Log.d(TAG, "In onClick()");
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
//        mBackgroundMusic = MediaPlayer.create(this, R.raw.bensound_slowmotion);
//        mBackgroundMusic.start();
        mMusicController = new MusicController(this);
        mMusicController.setFile(R.raw.bensound_slowmotion);
        mMusicController.start();
    }

    private void stopMusic() {
//            if(mBackgroundMusic != null) {
//                if(mBackgroundMusic.isPlaying()) mBackgroundMusic.stop();
//                mBackgroundMusic.release();
//                mBackgroundMusic = null;
//            }

//            Log.d(TAG, "Music was playing (MainActivity)");
        mMusicController.stop();
    }

    private void startQuiz() {
        stopMusic();
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
