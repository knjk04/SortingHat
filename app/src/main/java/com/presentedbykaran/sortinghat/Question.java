package com.presentedbykaran.sortinghat;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by karan on 30/06/18
 */
public class Question {

    private static final String TAG = "QuestionsAndAnswers";

    private ArrayList<String> questions = new ArrayList<>();
//    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private House op1House;
    private House op2House;
    private House op3House;
    private House op4House;

    private Context context;

//    public Question(String question, String option1, String option2, String option3, String option4, House op1House, House op2House, House op3House, House op4House) {
//        this.question = question;
    public Question(Context context, String option1, String option2, String option3, String option4, House op1House, House op2House, House op3House, House op4House) {
        this.context = context;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.op1House = op1House;
        this.op2House = op2House;
        this.op3House = op3House;
        this.op4House = op4House;

        initQuestions();
    }

    private void initQuestions() {
        String data = "";
//        ArrayList<String> questionsList = new ArrayList<>();

        InputStream inputStream = context.getResources().openRawResource(R.raw.questions);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        if(inputStream != null) {
            try {
                while ((data = bufferedReader.readLine()) != null) {
//                    questionsList.add(data);
                    questions.add(data);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getQuestion(int index) {
        return questions.get(index);
//        return question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public House getOp1House() {
        return op1House;
    }

    public void setOp1House(House op1House) {
        this.op1House = op1House;
    }

    public House getOp2House() {
        return op2House;
    }

    public void setOp2House(House op2House) {
        this.op2House = op2House;
    }

    public House getOp3House() {
        return op3House;
    }

    public void setOp3House(House op3House) {
        this.op3House = op3House;
    }

    public House getOp4House() {
        return op4House;
    }

    public void setOp4House(House op4House) {
        this.op4House = op4House;
    }
}
