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
    private final int NUM_OPTIONS = 4;

    private ArrayList<String> mQuestions = new ArrayList<>();
    // ArrayList (instead of arrays) used for Answers and Houses in case > 4 options
    private ArrayList<String> mAnswers = new ArrayList<>();
    private ArrayList<String> mHouses = new ArrayList<>();

//    private House op1House;
//    private House op2House;
//    private House op3House;
//    private House op4House;

    private Context mContext;


//    public Question(Context context, House op1House, House op2House, House op3House, House op4House) {
    public Question(Context context) {
        mContext = context;
//        this.op1House = op1House;
//        this.op2House = op2House;
//        this.op3House = op3House;
//        this.op4House = op4House;

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
                    if (currentOptionNum == 0) mQuestions.add(data);
                    else if (currentOptionNum <= NUM_OPTIONS)  mAnswers.add(data);
                    else mHouses.add(data);

//                    Log.d(TAG, "After adding question/answer/house");

                    currentOptionNum++;
                    Log.d(TAG, "After incrementing currentOptionNum");

                    // Adding since need to account for the NUM_OPTIONS number of options and
                    // NUM_OPTIONS number of Houses
                    if(currentOptionNum > NUM_OPTIONS+NUM_OPTIONS) currentOptionNum = 0; //reset

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

    public House getHouse(int houseNum) {
        switch (mHouses.get(houseNum).toLowerCase()) {
            case "gryffindor":
                return House.Gryffindor;
            case "hufflepuff":
                return House.Hufflepuff;
            case "ravenclaw":
                return House.Ravenclaw;
            default:
                return House.Slytherin;
        }
    }
}
