package com.presentedbykaran.sortinghat;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by karan on 30/06/18
 */
public class Question {

    private static final String TAG = "Question";

    private ArrayList<String> mQuestions = new ArrayList<>();
    private ArrayList<String> mAnswers = new ArrayList<>();

    private House op1House;
    private House op2House;
    private House op3House;
    private House op4House;

    private Context mContext;

    private final int NUM_OPTIONS = 4;

    public Question(Context context, House op1House, House op2House, House op3House, House op4House) {
        mContext = context;
        this.op1House = op1House;
        this.op2House = op2House;
        this.op3House = op3House;
        this.op4House = op4House;

        initQuestions();
    }

    private void initQuestions() {
        String data;
        // starting from 0 and going up to 4 where 0 represents the question and 1-4 the respective option numbers
        int currentOptionNum = 0;

        InputStream inputStream = mContext.getResources().openRawResource(R.raw.questions);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        if(inputStream != null) {
            try {
                while ((data = bufferedReader.readLine()) != null) {
                    Log.d(TAG, "In while loop");
                    if(currentOptionNum == 0) mQuestions.add(data);
                    else mAnswers.add(data);
                    Log.d(TAG, "After adding question/answer");

                    currentOptionNum++;
                    Log.d(TAG, "After incrementing currentOptionNum");

                    if(currentOptionNum > NUM_OPTIONS) currentOptionNum = 0; //reset

                    Log.d(TAG, "After possibly resetting currentOptionNum");
                    Log.d(TAG, "currentOptionNum = " + currentOptionNum);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "End of initQuestions()");
    }

    public String getQuestion(int index) {
        return mQuestions.get(index);
//        return question;
    }

    public String getOption(int optionNum) {
       return mAnswers.get(optionNum);
    }

    public House getOp1House() {
        return op1House;
    }

    public House getOp2House() {
        return op2House;
    }

    public House getOp3House() {
        return op3House;
    }

    public House getOp4House() {
        return op4House;
    }
}
