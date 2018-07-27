package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnBegin;
//    private EditText editTextFst;
//    private EditText editTextSnd;

    private TextInputLayout txtInputFst;
    private TextInputLayout txtInputSnd;

    public static final String TAG = MainActivity.class.getSimpleName();
    private MusicController mMusicController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBackgroundMusic();

//        editTextFst = findViewById(R.id.txtFstName);
//        editTextSnd = findViewById(R.id.txtSndName);

        txtInputFst = findViewById(R.id.txtInputFstName);
        txtInputSnd = findViewById(R.id.txtInputSndName);

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (editTextFst.getText().toString().isEmpty()) {
//                    editTextFst.setError("Please fill in all fields");
//                }

                if (!isInputValid(view)) return;

                stopMusic();
                startQuiz();
            }
        });
    }

    private boolean isInputValid(View view) {
        String fstName = txtInputFst.getEditText().getText().toString().trim();
        String sndName = txtInputSnd.getEditText().getText().toString().trim();

        if (fstName.isEmpty() || sndName.isEmpty()) {
            txtInputFst.setError("Name field cannot be empty");
            Log.d(TAG, "One of the name fields was empty");
            return false;
        }

        txtInputFst.setError(null); // removes error message
        return true;
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

//        String[] fullName = {editTextFst.getText().toString(), editTextSnd.getText().toString()};
        String[] fullName = {txtInputFst.getEditText().getText().toString(),
                txtInputSnd.getEditText().getText().toString()};

        Log.d(TAG, "fstName: " + txtInputFst.getEditText().toString());
        Log.d(TAG, "sndName: " + txtInputSnd.getEditText().toString());

        Intent intent = new Intent(this, InstructionsActivity.class);
        intent.putExtra("fullName", fullName);
        startActivity(intent);
    }
}
