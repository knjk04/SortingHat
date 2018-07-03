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
    private int mRavenclawTally  = 0;
    private int mHufflepuffTally = 0;
    private int mSlytherinTally  = 0;


    private int currentIndexVal  = 0; //default value

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
        if(currentIndexVal <= NUM_QUESTIONS_TO_ASK) {
            mTxtQuestion.setText(arrQAndA[currentIndexVal].getQuestion());
            rdBtn1.setText(arrQAndA[currentIndexVal].getOption1());
            rdBtn2.setText(arrQAndA[currentIndexVal].getOption2());
            rdBtn3.setText(arrQAndA[currentIndexVal].getOption3());
            rdBtn4.setText(arrQAndA[currentIndexVal].getOption4());
        }
    }

    private void initQAndAArray() {
        int i = 0;

        Log.d(TAG, "val of i before first q: " + i);

        arrQAndA[i++] = new Question("Four boxes are placed before you. Which would you try and " +
                "open?",
                "The small tortoiseshell box, embellished with gold, inside " +
                        "which some small creature seems to be squeaking.",
                "The gleaming jet black box with a silver lock and key, " +
                        "marked with a mysterious rune that you know to be the mark " +
                        "of Merlin.",
                "The ornate golden casket, standing on clawed feet, whose " +
                        "inscription warns that both secret knowledge and unbearable " +
                        "temptation lie within.",
                "The small pewter box, unassuming and plain, with a " +
                        "scratched message upon it that reads ‘I open only for the " +
                        "worthy.’", House.Hufflepuff, House.Slytherin, House.Ravenclaw,
                House.Gryffindor);

        Log.d(TAG, "val of i after 1st q: " + i);

        arrQAndA[i++] = new Question("You and two friends need to cross a bridge guarded by a " +
                "river troll who insists on fighting one of you before he will let all of you pass. " +
                "Do you:",
                "Attempt to confuse the troll into letting all three of you pass without " +
                        "fighting?",
                "Suggest drawing lots to decide which of you will fight?",
                "Suggest that all three of you should fight (without telling the troll)?",
                "Volunteer to fight?", House.Ravenclaw, House.Hufflepuff, House.Slytherin,
                House.Gryffindor);

        Log.d(TAG, "val of i after 2nd q: " + i);

       arrQAndA[i++] = new Question("Once every century, the Flutterby bush produces flowers that adapt their scent to " +
                    "attract the unwary. If it lured you, it would smell of:",
                    "A crackling log fire",
                    "The sea",
                    "Fresh parchment",
                    "Home", House.Gryffindor, House.Slytherin, House.Ravenclaw,
               House.Hufflepuff);
//
        Log.d(TAG, "val of i after 3rd q: " + i);

       arrQAndA[i++] = new Question("One of your house mates has cheated in a Hogwarts exam " +
               "by using a Self-Spelling Quill. Now he has come top of the class in Charms, beating " +
               "you into second place. Professor Flitwick is suspicious of what happened. He draws " +
               "you to one side after his lesson and asks you whether or not your classmate used a " +
               "forbidden quill. What do you do?",
                "Lie and say you don’t know (but hope that somebody else tells Professor " +
                        "Flitwick the truth).",
                "Tell Professor Flitwick that he ought to ask your classmate (and resolve to " +
                        "tell your classmate that if he doesn’t tell the truth, you will).",
                "Tell Professor Flitwick the truth. If your classmate is prepared to win by " +
                        "cheating, he deserves to be found out. Also, as you are both in the same " +
                        "house, any points he loses will be regained by you, for coming first in his " +
                        "place.",
                "You would not wait to be asked to tell Professor Flitwick the truth. If you " +
                        "knew that somebody was using a forbidden quill, you would tell the teacher " +
                        "before the exam started.",House.Hufflepuff,House.Gryffindor,House.Ravenclaw,
                House.Slytherin);
//
       arrQAndA[i++] = new Question("You enter an enchanted garden. What would you be most " +
               "curious to examine first?",
                "The silver leafed tree bearing golden apples",
                "The fat red toadstools that appear to be talking to each other",
                "The bubbling pool, in the depths of which something luminous is swirling",
                "The statue of an old wizard with a strangely twinkling eye",
                 House.Ravenclaw, House.Hufflepuff, House.Slytherin, House.Gryffindor);

       arrQAndA[i++] = new Question("Four goblets are placed before you. Which would you " +
               "choose to drink?",
                "The foaming, frothing, silvery liquid that sparkles as though containing " +
                        "ground diamonds.",
                "The smooth, thick, richly purple drink that gives off a delicious smell of " +
                        "chocolate and plums.",
                "The golden liquid so bright that it hurts the eye, and which makes sunspots " +
                        "dance all around the room.",
                "The mysterious black liquid that gleams like ink, and gives off fumes that " +
                        "make you see strange visions.",
                 House.Ravenclaw, House.Hufflepuff, House.Gryffindor, House.Slytherin);

       arrQAndA[i++] = new Question("What kind of instrument most pleases your ear?",
                                    "The violin", "The trumpet", "The piano",
                                    "The drum", House.Slytherin, House.Hufflepuff,
                                     House.Ravenclaw, House.Gryffindor);
//
       arrQAndA[i++] = new Question("Which of the following would you most hate people to " +
               "call you?", "Ordinary", "Ignorant", "Cowardly",
               "Selfish", House.Slytherin, House.Ravenclaw, House.Gryffindor,
                House.Hufflepuff);

       arrQAndA[i++] = new Question("After you have died, what would you most like people " +
               "to do when they hear your name?",
                "Miss you, but smile","Ask for more stories about your adventures",
                "Think with admiration of your achievements",
                "I don't care what people think of me after I'm dead; it's what they think of " +
                       "me while I'm alive that counts", House.Hufflepuff, House.Gryffindor,
                 House.Ravenclaw, House.Slytherin);

       arrQAndA[i++] = new Question("How would you like to be known to history?",
                "The Wise", "The Good", "The Great", "The Bold",
                 House.Ravenclaw, House.Hufflepuff, House.Slytherin, House.Gryffindor);

       arrQAndA[i++] = new Question("A Muggle confronts you and says that they are sure you " +
               "are a witch or wizard. Do you:",
                "Ask what makes them think so?",
                "Agree, and ask whether they’d like a free sample of a jinx?",
                "Agree, and walk away, leaving them to wonder whether you are bluffing?",
                "Tell them that you are worried about their mental health, and offer to call " +
                        "a doctor.", House.Ravenclaw, House.Slytherin, House.Gryffindor,
                 House.Hufflepuff);

       arrQAndA[i++] = new Question("Which nightmare would frighten you most?",
                "Standing on top of something very high and realizing suddenly that there " +
                        "are no hand- or footholds, nor any barrier to stop you falling.",
                "An eye at the keyhole of the dark, windowless room in which you are locked.",
                "Waking up to find that neither your friends nor your family have any idea " +
                        "who you are.",
                "Being forced to speak in such a silly voice that hardly anyone can " +
                        "understand you, and everyone laughs at you.",
                 House.Ravenclaw, House.Gryffindor, House.Hufflepuff, House.Slytherin);
//
       arrQAndA[i++] = new Question("Given the choice, would you rather invent a potion " +
               "that would guarantee you:",
                "Love?", "Glory?", "Wisdom?", "Power?",
                House.Hufflepuff, House.Gryffindor, House.Ravenclaw, House.Slytherin);

       arrQAndA[i++] = new Question("Which road tempts you most?",
                "The wide, sunny, grassy lane", "The narrow, dark, lantern-lit " +
               "alley", "The twisting, leaf-strewn path through woods", "The cobbled " +
               "street lined with ancient buildings", House.Hufflepuff, House.Slytherin,
                House.Gryffindor, House.Ravenclaw);

       Log.d(TAG, "val of i before final q: " + i);

       arrQAndA[i++] = new Question("Late at night, walking alone down the street, you hear " +
               "a peculiar cry that you believe to have a magical source. Do you:",
               "Proceed with caution, keeping one hand on your concealed wand and an eye out " +
                       "for any disturbance?",
                "Draw your wand and try to discover the source of the noise?",
                "Draw your wand and stand your ground?",
                "Withdraw into the shadows to await developments, while mentally reviewing " +
                        "the most appropriate defensive and offensive spells, should trouble occur?",
               House.Hufflepuff, House.Gryffindor, House.Slytherin, House.Ravenclaw);
    }

    private String determineHouse() {
        int max = Math.max(mGryffindorTally, Math.max(mSlytherinTally,Math.max(mRavenclawTally, mHufflepuffTally)));

        if(max == mGryffindorTally) return "Gryffindor!";
        else if (max == mRavenclawTally) return "Ravenclaw!";
        else if (max == mHufflepuffTally) return "Hufflepuff!";
        return "Slytherin!";
    }
}
