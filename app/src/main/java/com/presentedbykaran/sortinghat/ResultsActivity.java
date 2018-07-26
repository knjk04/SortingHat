package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
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

    private int mFile;
    private int mTime = 0;

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
               String shareBody = "I have been sorted into " + txtHouse.getText() +
                       "\n\nFind out which house you belong to: https://github.com/knjk04/SortingHat";
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
//                createBackgroundMusic(R.raw.hana);
                mFile = R.raw.hana;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.hana);
                break;
            case HUFFLEPUFF:
                imageView.setImageResource(R.drawable.hufflepuff_crest_transparent);
//                createBackgroundMusic(R.raw.denali);
                createBackgroundMusic();
                mFile = R.raw.denali;
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.denali);
                break;
            case RAVENCLAW:
                imageView.setImageResource(R.drawable.ravenclaw_crest_transparent);
//                createBackgroundMusic(R.raw.banshee);
                mFile = R.raw.banshee;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.banshee);
                break;
            case SLYTHERIN:
                imageView.setImageResource(R.drawable.slytherin_crest_transparent);
                mFile = R.raw.castor;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.castor);
        }
        mBackgroundMusic.start();

        if (mBackgroundMusic.isPlaying()) {
            Log.d(TAG, "Music is playing - setEmblem()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBackgroundMusic != null && mBackgroundMusic.isPlaying()) {
            mTime = mBackgroundMusic.getCurrentPosition();
            stopMusic();
        }
    }

    @Override
    protected void onResume() {
       super.onResume();
       mBackgroundMusic.start();
       mBackgroundMusic.seekTo(mTime);
//       createBackgroundMusic();
    }

    private void createBackgroundMusic() {
        mBackgroundMusic = MediaPlayer.create(this, mFile);
    }

    private void stopMusic() {
        if(mBackgroundMusic.isPlaying()) mBackgroundMusic.stop();
    }
}
