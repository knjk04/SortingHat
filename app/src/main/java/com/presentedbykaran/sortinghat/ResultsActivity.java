package com.presentedbykaran.sortinghat;

import android.content.Intent;
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

    private MusicController mMusicController;
    ImageView imageView;
    private String mHouse;

    private final String GRYFFINDOR = "gryffindor!";
    private final String RAVENCLAW  = "ravenclaw!";
    private final String HUFFLEPUFF = "hufflepuff!";
    private final String SLYTHERIN  = "slytherin!";

    public static final String TAG = ResultsActivity.class.getSimpleName();

    private int mTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        mHouse = intent.getStringExtra("house");
        txtHouse = findViewById(R.id.hogwartsHouse);
        imageView = findViewById(R.id.houseEmblem);
        setEmblem();
        txtHouse.setText(mHouse);

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
        switch(mHouse.toLowerCase()) {
            case GRYFFINDOR:
                imageView.setImageResource(R.drawable.gryffindor_crest_transparent);
//                createBackgroundMusic(R.raw.hana);
//                mFile = R.raw.hana;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.hana);
                break;
            case HUFFLEPUFF:
                imageView.setImageResource(R.drawable.hufflepuff_crest_transparent);
//                createBackgroundMusic(R.raw.denali);
                createBackgroundMusic();
//                mFile = R.raw.denali;
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.denali);
                break;
            case RAVENCLAW:
                imageView.setImageResource(R.drawable.ravenclaw_crest_transparent);
//                createBackgroundMusic(R.raw.banshee);
//                mFile = R.raw.banshee;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.banshee);
                break;
            case SLYTHERIN:
                imageView.setImageResource(R.drawable.slytherin_crest_transparent);
//                mFile = R.raw.castor;
                createBackgroundMusic();
//                mBackgroundMusic = MediaPlayer.create(this, R.raw.castor);
        }
//        mBackgroundMusic.start();
        mMusicController.start();

//        if (mBackgroundMusic.isPlaying()) {
//            Log.d(TAG, "Music is playing - setEmblem()");
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (mBackgroundMusic != null && mBackgroundMusic.isPlaying()) {
//            mTime = mBackgroundMusic.getCurrentPosition();
//            stopMusic();
//        }

        if (mMusicController != null && mMusicController.isPlaying()) {
            mTime = mMusicController.getCurrentPosition();
            stopMusic();
        }
    }

    @Override
    protected void onResume() {
       super.onResume();
//       mBackgroundMusic.start();
//       mBackgroundMusic.seekTo(mTime);

        mMusicController.start();
//       createBackgroundMusic();
    }

    private void createBackgroundMusic() {
//        mBackgroundMusic = MediaPlayer.create(this, mFile);
        mMusicController = new MusicController(this);
        strToHouse();
        mMusicController.start();
    }

    private void stopMusic() {
//        if(mBackgroundMusic.isPlaying()) mBackgroundMusic.stop();
        if(mMusicController.isPlaying()) mMusicController.stop();
    }

    private void strToHouse() {
        String house = mHouse.toLowerCase();
        Log.d(TAG, "house in strToHouse: " + txtHouse.getText());

        switch(house) {
            case "gryffindor!":
                Log.d(TAG, "in gryffindor case in strToHouse");
                mMusicController.setFileHouse(House.Gryffindor);
                break;
            case "hufflepuff!":
                Log.d(TAG, "in hufflepuff case in strToHouse");
                mMusicController.setFileHouse(House.Hufflepuff);
                break;
            case "ravenclaw!":
                Log.d(TAG, "in ravenclaw case in strToHouse");
                mMusicController.setFileHouse(House.Ravenclaw);
                break;
            case "slytherin!":
                Log.d(TAG, "in slytherin case in strToHouse");
                mMusicController.setFileHouse(House.Slytherin);
                break;
        }
    }
}
