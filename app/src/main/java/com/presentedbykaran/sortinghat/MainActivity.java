package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBegin;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d(TAG, "in main activity's onCreate()");

        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startQuiz() ;
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
//        Resources resources = getResources();
        startActivity(intent);
    }
}
