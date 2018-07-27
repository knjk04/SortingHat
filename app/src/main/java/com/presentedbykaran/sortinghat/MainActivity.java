package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnBegin;
    private EditText editTextFst;
    private EditText editTextSnd;
    public static final String TAG = MainActivity.class.getSimpleName();
    private MusicController mMusicController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBackgroundMusic();

        editTextFst = findViewById(R.id.txtFstName);
        editTextSnd = findViewById(R.id.txtSndName);

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        mMusicController = new MusicController(this);
        mMusicController.setFile(R.raw.bensound_slowmotion);
        mMusicController.start();
    }

    private void stopMusic() {
        mMusicController.stop();
    }

    private void startQuiz() {
        stopMusic();

        String[] fullName = {editTextFst.getText().toString(), editTextSnd.getText().toString()};

        Intent intent = new Intent(this, InstructionsActivity.class);
        intent.putExtra("fullName", fullName);
        startActivity(intent);
    }
}
