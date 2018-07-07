package com.presentedbykaran.sortinghat;

import android.app.PendingIntent;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "QuizActivity";
    private TextView mTxtQuestion;
    private RadioButton rdBtn1, rdBtn2, rdBtn3, rdBtn4;
    private RadioGroup radioGroup;
    private final int NUM_QUESTIONS_TO_ASK = 8;

    private Question[] arrQAndA = new Question[15];
    private final int MAX_OPTIONS = 4;

    private int mGryffindorTally = 0;
    private int mRavenclawTally  = 0;
    private int mHufflepuffTally = 0;
    private int mSlytherinTally  = 0;


    private int currentIndexVal  = 0; // default value
    private int questionNum = 0; // since answers ArrayList starts from 0

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

//        for (int i = 0; i < NUM_QUESTIONS_TO_ASK; i++) {
//            mTxtQuestion.setText(arrQAndA[i].getQuestion());
//            rdBtn1.setText(arrQAndA[i].getOption1());
//            rdBtn2.setText(arrQAndA[i].getOption2());
//            rdBtn3.setText(arrQAndA[i].getOption3());
//            rdBtn4.setText(arrQAndA[i].getOption4());

//            currentIndexVal = 0;
        Log.d(TAG, "Before invoking nextQuestion()");
            nextQuestion();

//            while (currentIndexVal < NUM_QUESTIONS_TO_ASK) {
                rdBtn1.setOnClickListener(this);
                rdBtn2.setOnClickListener(this);
                rdBtn3.setOnClickListener(this);
                rdBtn4.setOnClickListener(this);
//            }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdBtn1:
                updateTally(arrQAndA[currentIndexVal].getOp1House());
                break;
            case R.id.rdBtn2:
                updateTally(arrQAndA[currentIndexVal].getOp2House());
                break;
            case R.id.rdBtn3:
                updateTally(arrQAndA[currentIndexVal].getOp3House());
                break;
            case R.id.rdBtn4:
                updateTally(arrQAndA[currentIndexVal].getOp4House());
                break;
        }
        currentIndexVal++;
        if(currentIndexVal == NUM_QUESTIONS_TO_ASK) {
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
//                toast = Toast.makeText(this, "Gryffindor score:" + mGryffindorTally, Toast.LENGTH_LONG);
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
//        for(int i = 1; currentIndexVal <= NUM_QUESTIONS_TO_ASK; i++) {
//            for(int i = 1; currentIndexVal <= 1; i++) {
//        int i = 1;
        if(currentIndexVal <= NUM_QUESTIONS_TO_ASK) {
            Log.d(TAG, "currentIndexVal in nextQuestion(): " + currentIndexVal);
            mTxtQuestion.setText(arrQAndA[currentIndexVal].getQuestion(currentIndexVal));
            rdBtn1.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn2.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn3.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
            rdBtn4.setText(arrQAndA[currentIndexVal].getOption(questionNum++));
//            rdBtn1.setText(arrQAndA[currentIndexVal].getOption1());
//            rdBtn2.setText(arrQAndA[currentIndexVal].getOption2());
//            rdBtn3.setText(arrQAndA[currentIndexVal].getOption3());
//            rdBtn4.setText(arrQAndA[currentIndexVal].getOption4());
        }
    }

    private void initQAndAArray() {
        int i = 0;

        arrQAndA[i++] = new Question(this, House.Hufflepuff, House.Slytherin,
                House.Ravenclaw, House.Gryffindor);
//        mTxtQuestion.setText(arrQAndA[i].getQuestion(i++));

        Log.d(TAG, "val of i after 1st q: " + i);
//
//        arrQAndA[i++] = new Question(this,
//                "Attempt to confuse the troll into letting all three of you pass without " +
//                        "fighting?",
//                "Suggest drawing lots to decide which of you will fight?",
//                "Suggest that all three of you should fight (without telling the troll)?",
//                "Volunteer to fight?", House.Ravenclaw, House.Hufflepuff, House.Slytherin,
//                House.Gryffindor);
//
//        Log.d(TAG, "val of i after 2nd q: " + i);
//
//       arrQAndA[i++] = new Question(this,
//                    "A crackling log fire",
//                    "The sea",
//                    "Fresh parchment",
//                    "Home", House.Gryffindor, House.Slytherin, House.Ravenclaw,
//               House.Hufflepuff);
//
//        Log.d(TAG, "val of i after 3rd q: " + i);
//
//       arrQAndA[i++] = new Question(this,
//                "Lie and say you don’t know (but hope that somebody else tells Professor " +
//                        "Flitwick the truth).",
//                "Tell Professor Flitwick that he ought to ask your classmate (and resolve to " +
//                        "tell your classmate that if he doesn’t tell the truth, you will).",
//                "Tell Professor Flitwick the truth. If your classmate is prepared to win by " +
//                        "cheating, he deserves to be found out. Also, as you are both in the same " +
//                        "house, any points he loses will be regained by you, for coming first in his " +
//                        "place.",
//                "You would not wait to be asked to tell Professor Flitwick the truth. If you " +
//                        "knew that somebody was using a forbidden quill, you would tell the teacher " +
//                        "before the exam started.",House.Hufflepuff,House.Gryffindor,House.Ravenclaw,
//                House.Slytherin);
//
//       arrQAndA[i++] = new Question(this,
//                "The silver leafed tree bearing golden apples",
//                "The fat red toadstools that appear to be talking to each other",
//                "The bubbling pool, in the depths of which something luminous is swirling",
//                "The statue of an old wizard with a strangely twinkling eye",
//                 House.Ravenclaw, House.Hufflepuff, House.Slytherin, House.Gryffindor);
//
//       arrQAndA[i++] = new Question(this,
//                "The foaming, frothing, silvery liquid that sparkles as though containing " +
//                        "ground diamonds.",
//                "The smooth, thick, richly purple drink that gives off a delicious smell of " +
//                        "chocolate and plums.",
//                "The golden liquid so bright that it hurts the eye, and which makes sunspots " +
//                        "dance all around the room.",
//                "The mysterious black liquid that gleams like ink, and gives off fumes that " +
//                        "make you see strange visions.",
//                 House.Ravenclaw, House.Hufflepuff, House.Gryffindor, House.Slytherin);
//
//       arrQAndA[i++] = new Question(this,
//                                    "The violin", "The trumpet", "The piano",
//                                    "The drum", House.Slytherin, House.Hufflepuff,
//                                     House.Ravenclaw, House.Gryffindor);
//
//       arrQAndA[i++] = new Question(this,
//               "Ordinary", "Ignorant", "Cowardly",
//               "Selfish", House.Slytherin, House.Ravenclaw, House.Gryffindor,
//                House.Hufflepuff);
//
//       arrQAndA[i++] = new Question(this,
//                "Miss you, but smile","Ask for more stories about your adventures",
//                "Think with admiration of your achievements",
//                "I don't care what people think of me after I'm dead; it's what they think of " +
//                       "me while I'm alive that counts", House.Hufflepuff, House.Gryffindor,
//                 House.Ravenclaw, House.Slytherin);
//
//       arrQAndA[i++] = new Question(this,
//                "The Wise", "The Good", "The Great", "The Bold",
//                 House.Ravenclaw, House.Hufflepuff, House.Slytherin, House.Gryffindor);
//
//       arrQAndA[i++] = new Question(this,
//                "Ask what makes them think so?",
//                "Agree, and ask whether they’d like a free sample of a jinx?",
//                "Agree, and walk away, leaving them to wonder whether you are bluffing?",
//                "Tell them that you are worried about their mental health, and offer to call " +
//                        "a doctor.", House.Ravenclaw, House.Slytherin, House.Gryffindor,
//                 House.Hufflepuff);
//
//       arrQAndA[i++] = new Question(this,
//                "Standing on top of something very high and realizing suddenly that there " +
//                        "are no hand- or footholds, nor any barrier to stop you falling.",
//                "An eye at the keyhole of the dark, windowless room in which you are locked.",
//                "Waking up to find that neither your friends nor your family have any idea " +
//                        "who you are.",
//                "Being forced to speak in such a silly voice that hardly anyone can " +
//                        "understand you, and everyone laughs at you.",
//                 House.Ravenclaw, House.Gryffindor, House.Hufflepuff, House.Slytherin);

//       arrQAndA[i++] = new Question(this,
//                "Love?", "Glory?", "Wisdom?", "Power?",
//                House.Hufflepuff, House.Gryffindor, House.Ravenclaw, House.Slytherin);
//
//       arrQAndA[i++] = new Question(this,
//                "The wide, sunny, grassy lane", "The narrow, dark, lantern-lit " +
//               "alley", "The twisting, leaf-strewn path through woods", "The cobbled " +
//               "street lined with ancient buildings", House.Hufflepuff, House.Slytherin,
//                House.Gryffindor, House.Ravenclaw);
//
       Log.d(TAG, "val of i before final q: " + i);
//
//       arrQAndA[i++] = new Question(this,
//               "Proceed with caution, keeping one hand on your concealed wand and an eye out " +
//                       "for any disturbance?",
//                "Draw your wand and try to discover the source of the noise?",
//                "Draw your wand and stand your ground?",
//                "Withdraw into the shadows to await developments, while mentally reviewing " +
//                        "the most appropriate defensive and offensive spells, should trouble occur?",
//               House.Hufflepuff, House.Gryffindor, House.Slytherin, House.Ravenclaw);
    }

    private String determineHouse() {
        int max = Math.max(mGryffindorTally, Math.max(mSlytherinTally,Math.max(mRavenclawTally, mHufflepuffTally)));

        if(max == mGryffindorTally) return "Gryffindor!";
        else if (max == mRavenclawTally) return "Ravenclaw!";
        else if (max == mHufflepuffTally) return "Hufflepuff!";
        return "Slytherin!";
    }
}
