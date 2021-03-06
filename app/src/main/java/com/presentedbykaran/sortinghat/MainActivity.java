package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button btnBegin;
    private TextInputLayout txtInputFst, txtInputSnd;
    public static final String TAG = MainActivity.class.getSimpleName();
    private MusicController mMusicController;
    private String mFstName, mSndName;
    private ImageButton imBtnToggleSound;
    private boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mMusicController == null) createBackgroundMusic();

        txtInputFst = findViewById(R.id.txtInputFstName);
        txtInputSnd = findViewById(R.id.txtInputSndName);

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInputValid(view)) return;

                mMusicController.stop();
                startQuiz();
            }
        });

        imBtnToggleSound = findViewById(R.id.imBtnToggleSoundBtnMain);
    }

    private boolean isInputValid(View view) {
        mFstName = txtInputFst.getEditText().getText().toString().trim();
        mSndName = txtInputSnd.getEditText().getText().toString().trim();

        if (mFstName.isEmpty()) {
            txtInputFst.setError("Please enter in a first name");
            return false;
        } else if (mSndName.isEmpty()) {
            txtInputSnd.setError("Please enter in a last name");
            return false;
        }

        txtInputFst.setError(null); // removes error message
        txtInputSnd.setError(null); // removes error message
        return true;
    }

    public void toggleSoundMain(View view) {
        Log.d(TAG, "In MainActivity's toggleSound()");
        if (isMuted) {
            // If it was muted and you click on it, then it is no longer muted, so the button should
            // now be the muted button (because if the witch/wizard clicks on it, it will then mute
            // the sound
            imBtnToggleSound.setImageResource(R.drawable.mute_white_24dp);
            Log.d(TAG, "Was muted - MainActivity");
            mMusicController.resumeMusic();
        } else {
            imBtnToggleSound.setImageResource(R.drawable.volume_up_white_24dp);
            Log.d(TAG, "Wasn't muted - MainActivity");
            mMusicController.pauseMusic();
        }
        isMuted = !isMuted;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMusicController.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMusicController.resumeMusic();
    }

    private void createBackgroundMusic() {
        mMusicController = new MusicController(this);
        mMusicController.setFile(R.raw.bensound_slowmotion);
        mMusicController.start();
    }

    private void startQuiz() {
        mMusicController.stop();
        String[] fullName = {mFstName, mSndName};

        Log.d(TAG, "fstName: " + txtInputFst.getEditText().toString());
        Log.d(TAG, "sndName: " + txtInputSnd.getEditText().toString());

        Intent intent = new Intent(this, InstructionsActivity.class);
        intent.putExtra("fullName", fullName);
        startActivity(intent);
    }
}
