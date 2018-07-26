package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private TextView txtHouse;
    private Button btnShare;

    MediaPlayer mBackgroundMusic;
    ImageView imageView;
    private String house;

    private final String GRYFFINDOR = "gryffindor!";
    private final String RAVENCLAW  = "ravenclaw!";
    private final String HUFFLEPUFF = "hufflepuff!";
    private final String SLYTHERIN  = "slytherin!";

    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        house = intent.getStringExtra("house");
        txtHouse = findViewById(R.id.hogwartsHouse);
        imageView = findViewById(R.id.houseEmblem);
        setEmblem();
        txtHouse.setText(house);

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stopMusic();
               Intent intent = new Intent(Intent.ACTION_SEND);
               intent.setType("text/plain");
               String shareBody = "https://github.com/knjk04/SortingHat: Find out your hogwarts house";
//               String subSub = "Hogwarts fun";
               intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
               intent.putExtra(Intent.EXTRA_TEXT, shareBody);
               startActivity(Intent.createChooser(intent,"Share using"));
            }
        });
    }

    private void setEmblem() {
        Log.d(TAG, "In setEmblem()");
        switch(house.toLowerCase()) {
            case GRYFFINDOR:
                imageView.setImageResource(R.drawable.gryffindor_crest_transparent);
                mBackgroundMusic = MediaPlayer.create(this, R.raw.hana);
                break;
            case HUFFLEPUFF:
                imageView.setImageResource(R.drawable.hufflepuff_crest_transparent);
                mBackgroundMusic = MediaPlayer.create(this, R.raw.denali);
                break;
            case RAVENCLAW:
                imageView.setImageResource(R.drawable.ravenclaw_crest_transparent);
                mBackgroundMusic = MediaPlayer.create(this, R.raw.banshee);
                break;
            case SLYTHERIN:
                imageView.setImageResource(R.drawable.slytherin_crest_transparent);
                mBackgroundMusic = MediaPlayer.create(this, R.raw.castor);
        }
        mBackgroundMusic.start();

        if (mBackgroundMusic.isPlaying()) {
            Log.d(TAG, "Music is playing - setEmblem()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBackgroundMusic != null && mBackgroundMusic.isPlaying()) stopMusic();
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        createBackgroundMusic();
//    }
//

    private void stopMusic() {
        if(mBackgroundMusic.isPlaying()) mBackgroundMusic.stop();
    }
}
