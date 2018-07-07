package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "QuizActivity";
    private TextView mTxtQuestion;
    private RadioButton rdBtn1, rdBtn2, rdBtn3, rdBtn4;
    private RadioGroup radioGroup;
    private final int NUM_QUESTIONS_TO_ASK = 8;

    private Question[] arrQAndA = new Question[15];
    private final int MAX_OPTIONS = 4;

    private int mGryffindorTally = 0;
    private int mRavenclawTally = 0;
    private int mHufflepuffTally = 0;
    private int mSlytherinTally = 0;


    private int currentIndexVal = 0; // default value
    private int questionNum = 0; // since answers ArrayList starts from 0 -- it's probably bad that this class knows that
    private int houseNum = 0;

    private final int OPTION_1 = 0;
    private final int OPTION_2 = 1;
    private final int OPTION_3 = 2;
    private final int OPTION_4 = 3;
    private final int NUM_OPTIONS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "in QuizActivity's onCreate()");

        mTxtQuestion = findViewById(R.id.txtQuestion);
        rdBtn1 = findViewById(R.id.rdBtn1);
        rdBtn2 = findViewById(R.id.rdBtn2);
        rdBtn3 = findViewById(R.id.rdBtn3);
        rdBtn4 = findViewById(R.id.rdBtn4);
        radioGroup = findViewById(R.id.radioGroup);

        Log.d(TAG, "before initQAndAArray");
        initQAndAArray();

        Log.d(TAG, "Before invoking nextQuestion()");
        nextQuestion();

        rdBtn1.setOnClickListener(this);
        rdBtn2.setOnClickListener(this);
        rdBtn3.setOnClickListener(this);
        rdBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick() currentIndexVal = " + currentIndexVal);
        switch (view.getId()) {
            case R.id.rdBtn1:
//                updateTally(arrQAndA[currentIndexVal].getHouse(0));
                Log.d(TAG, "btn1 = " + NUM_OPTIONS*currentIndexVal+OPTION_1);
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS*currentIndexVal+OPTION_1)); // 0,4,8,12
//                updateTally(arrQAndA[currentIndexVal].getOp1House());
                break;
            case R.id.rdBtn2:
//                updateTally(arrQAndA[currentIndexVal].getHouse(1));
                Log.d(TAG, "btn2 = " + NUM_OPTIONS*currentIndexVal+OPTION_2);
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS*currentIndexVal+OPTION_2)); // 1,5,9,13
//                updateTally(arrQAndA[currentIndexVal].getOp2House());
                break;
            case R.id.rdBtn3:
//                updateTally(arrQAndA[currentIndexVal].getHouse(2));
                Log.d(TAG, "btn3 = " + NUM_OPTIONS*currentIndexVal+OPTION_3);
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS*currentIndexVal+OPTION_3)); // 2,6,10,14
//                updateTally(arrQAndA[currentIndexVal].getOp3House());
                break;
            case R.id.rdBtn4:
//                updateTally(arrQAndA[currentIndexVal].getHouse(3));
                Log.d(TAG, "btn4 = " + NUM_OPTIONS*currentIndexVal+OPTION_4);
                updateTally(arrQAndA[currentIndexVal].getHouse(NUM_OPTIONS*currentIndexVal+OPTION_4)); // 3,7,11,15
//                updateTally(arrQAndA[currentIndexVal].getOp4House());
                break;
        }
        currentIndexVal++;
        if (currentIndexVal == NUM_QUESTIONS_TO_ASK) {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("house", determineHouse());
            startActivity(intent);
        }
        nextQuestion();
        radioGroup.clearCheck();
    }

    private void updateTally(House house) {
        Toast toast;
        switch (house) {
            case Gryffindor:
                mGryffindorTally++;
                toast = Toast.makeText(this, "Gryffindor score: " + mGryffindorTally, Toast.LENGTH_LONG);
                toast.show();
                break;
            case Ravenclaw:
                mRavenclawTally++;
                toast = Toast.makeText(this, "Ravenclaw score: " + mRavenclawTally, Toast.LENGTH_LONG);
                toast.show();
                break;
            case Hufflepuff:
                mHufflepuffTally++;
                toast = Toast.makeText(this, "Hufflepuff score: " + mHufflepuffTally, Toast.LENGTH_LONG);
                toast.show();
                break;
            case Slytherin:
                mSlytherinTally++;
                toast = Toast.makeText(this, "Slytherin score: " + mSlytherinTally, Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }

    public void nextQuestion() {
        if (currentIndexVal <= NUM_QUESTIONS_TO_ASK) {
            Log.d(TAG, "currentIndexVal in nextQuestion(): " + currentIndexVal);
            mTxtQuestion.setText(arrQAndA[currentIndexVal].getQuestion(currentIndexVal));
            rdBtn1.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn2.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn3.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn4.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
        }
    }

    private void initQAndAArray() {
        int i = 0;

        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);
        arrQAndA[i++] = new Question(this);

//        Log.d(TAG, "val of i after 1st q: " + i);

//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Hufflepuff,
//                House.Slytherin, House.Gryffindor);

//        Log.d(TAG, "val of i after 2nd q: " + i);

//        arrQAndA[i++] = new Question(this, House.Gryffindor, House.Slytherin,
//                House.Ravenclaw, House.Hufflepuff);
//
//        Log.d(TAG, "val of i after 3rd q: " + i);
//
//        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Gryffindor,
//                House.Ravenclaw, House.Slytherin);
//
//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Hufflepuff,
//                House.Slytherin, House.Gryffindor);
//
//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Hufflepuff,
//                House.Gryffindor, House.Slytherin);
//
//        arrQAndA[i++] = new Question(this, House.Slytherin, House.Hufflepuff,
//                House.Ravenclaw, House.Gryffindor);
//
//        arrQAndA[i++] = new Question(this, House.Slytherin, House.Ravenclaw,
//                House.Gryffindor, House.Hufflepuff);
//
//        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Gryffindor,
//                House.Ravenclaw, House.Slytherin);
//
//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Hufflepuff,
//                House.Slytherin, House.Gryffindor);
//
//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Slytherin, House.Gryffindor,
//                House.Hufflepuff);
//
//        arrQAndA[i++] = new Question(this, House.Ravenclaw, House.Gryffindor,
//                House.Hufflepuff, House.Slytherin);
//
//        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Gryffindor,
//                House.Ravenclaw, House.Slytherin);
//
//        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Slytherin,
//                House.Gryffindor, House.Ravenclaw);
//
//        Log.d(TAG, "val of i before final q: " + i);
//
//        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Gryffindor,
//                House.Slytherin, House.Ravenclaw);
    }

    // There should be a better way to determine the house
    // This could return the wrong house if two houses have the same tally?
    private String determineHouse() {
        int max = Math.max(mGryffindorTally, Math.max(mSlytherinTally, Math.max(mRavenclawTally, mHufflepuffTally)));

        if (max == mGryffindorTally) return "Gryffindor!";
        else if (max == mRavenclawTally) return "Ravenclaw!";
        else if (max == mHufflepuffTally) return "Hufflepuff!";
        return "Slytherin!";
    }
}
