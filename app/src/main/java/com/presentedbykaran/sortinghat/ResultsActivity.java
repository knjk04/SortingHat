package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private int mGryffindorScore;
    private int mRavenclawScore;
    private int mHufflepuffScore;
    private int mSlytherinScore;

    private TextView txtHouse;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        String house = intent.getStringExtra("house");
        txtHouse = findViewById(R.id.hogwartsHouse);
        txtHouse.setText(house);

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_SEND);
               intent.setType("text/plain");
               String shareBody = "https://github.com/knjk04/SortingHat: Find out your hogwarts house";
               String subSub = "Hogwarts fun";
               intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
               intent.putExtra(Intent.EXTRA_TEXT, shareBody);
               startActivity(Intent.createChooser(intent,"Share using"));
            }
        });
    }

}
