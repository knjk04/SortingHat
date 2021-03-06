package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = QuizActivity.class.getSimpleName();
    private TextView mTxtQuestion;
    private RadioButton rdBtn1, rdBtn2, rdBtn3, rdBtn4;
    private RadioGroup radioGroup;
    private final int NUM_QUESTIONS_TO_ASK = 15;

    private Question[] arrQAndA = new Question[NUM_QUESTIONS_TO_ASK];

    private int mGryffindorTally = 0;
    private int mRavenclawTally  = 0;
    private int mHufflepuffTally = 0;
    private int mSlytherinTally  = 0;

    private int currentIndexVal = 0; // default value
    private int questionNum     = 0; // since answers ArrayList starts from 0

    private final int OPTION_1    = 0;
    private final int OPTION_2    = 1;
    private final int OPTION_3    = 2;
    private final int OPTION_4    = 3;
    private final int NUM_OPTIONS = 4;

    private MusicController mMusicController;

    private ProgressBar progressBar;
    private ImageButton imBtnSoundQuiz;
    private boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (mMusicController == null) createBackgroundMusic();

        mTxtQuestion = findViewById(R.id.txtQuestion);
        rdBtn1 = findViewById(R.id.rdBtn1);
        rdBtn2 = findViewById(R.id.rdBtn2);
        rdBtn3 = findViewById(R.id.rdBtn3);
        rdBtn4 = findViewById(R.id.rdBtn4);
        radioGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progressBar);

        initQAndAArray();
        nextQuestion();

        rdBtn1.setOnClickListener(this);
        rdBtn2.setOnClickListener(this);
        rdBtn3.setOnClickListener(this);
        rdBtn4.setOnClickListener(this);

        imBtnSoundQuiz = findViewById(R.id.imBtnToggleSoundQuiz);
    }

    public void toggleSoundQuiz(View view) {
        if (isMuted) {
            // If it was muted and you click on it, then it is no longer muted, so the button should
            // now be the muted button (because if the witch/wizard clicks on it, it will then mute
            // the sound
            imBtnSoundQuiz.setImageResource(R.drawable.mute_white_24dp);
            mMusicController.resumeMusic();
        } else {
            imBtnSoundQuiz.setImageResource(R.drawable.volume_up_white_24dp);
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
        mMusicController.setFile(R.raw.bensound_memories);
        mMusicController.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdBtn1:
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS *
                        currentIndexVal + OPTION_1)); // 0,4,8/,12
                break;
            case R.id.rdBtn2:
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS *
                        currentIndexVal + OPTION_2)); // 1,5,9,13
                break;
            case R.id.rdBtn3:
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS *
                        currentIndexVal + OPTION_3)); // 2,6,10,14
                break;
            case R.id.rdBtn4:
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS *
                        currentIndexVal + OPTION_4)); // 3,7,11,15
                break;
        }
        currentIndexVal++;
        if (currentIndexVal == NUM_QUESTIONS_TO_ASK) {
            mMusicController.stop();
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("house", determineHouse());
            startActivity(intent);
        }
        nextQuestion();
        radioGroup.clearCheck();
    }

    // Toast messages are kept as commented code to make it easy to quickly toggle them when debugging
    private void updateTally(House house) {
//        Toast toast;
        switch (house) {
            case Gryffindor:
                mGryffindorTally++;
//                toast = Toast.makeText(this, "Gryffindor score: " + mGryffindorTally, Toast.LENGTH_LONG);
//                toast.show();
                break;
            case Ravenclaw:
                mRavenclawTally++;
//                toast = Toast.makeText(this, "Ravenclaw score: " + mRavenclawTally, Toast.LENGTH_LONG);
//                toast.show();
                break;
            case Hufflepuff:
                mHufflepuffTally++;
//                toast = Toast.makeText(this, "Hufflepuff score: " + mHufflepuffTally, Toast.LENGTH_LONG);
//                toast.show();
                break;
            case Slytherin:
                mSlytherinTally++;
//                toast = Toast.makeText(this, "Slytherin score: " + mSlytherinTally, Toast.LENGTH_LONG);
//                toast.show();
                break;
        }
    }

    public void nextQuestion() {
        int incrementPercentBy = 100 / NUM_QUESTIONS_TO_ASK;
        if (currentIndexVal < NUM_QUESTIONS_TO_ASK) {
            mTxtQuestion.setText(arrQAndA[currentIndexVal].getQuestion(currentIndexVal));
            rdBtn1.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn2.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn3.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn4.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            progressBar.incrementProgressBy(incrementPercentBy);
        }
    }

    private void initQAndAArray() {
        for (int i = 0; i < arrQAndA.length; i++)
            arrQAndA[i] = new Question(this);
    }

    // There should be a better way to determine the house
    // This could return the wrong house if two houses have the same tally?
    private String determineHouse() {
        int max = Math.max(mGryffindorTally, Math.max(mSlytherinTally, Math.max(mRavenclawTally,
                mHufflepuffTally)));

        if (max == mGryffindorTally) {
            return "Gryffindor!";
        } else if (max == mRavenclawTally) {
            return "Ravenclaw!";
        } else if (max == mHufflepuffTally) {
            return "Hufflepuff!";
        }
        return "Slytherin!";
    }
}
