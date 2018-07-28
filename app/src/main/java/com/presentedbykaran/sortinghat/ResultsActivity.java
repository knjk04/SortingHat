package com.presentedbykaran.sortinghat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private TextView txtHouse;
    private ImageView imageView;

    private ImageButton imBtnShare;
    private ImageButton imBtnToggleSound;
    private boolean isMuted = false;

    private MusicController mMusicController;
    private String mHouse;

    private final String GRYFFINDOR = "gryffindor!";
    private final String RAVENCLAW = "ravenclaw!";
    private final String HUFFLEPUFF = "hufflepuff!";
    private final String SLYTHERIN = "slytherin!";

    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        mHouse = intent.getStringExtra("house");
        txtHouse = findViewById(R.id.hogwartsHouse);

        imageView = findViewById(R.id.houseEmblem);
        setEmblem();
        animate();

        txtHouse.setText(mHouse);

        imBtnShare = findViewById(R.id.imBtnShare);
        imBtnShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mMusicController.stop();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "I have been sorted into " + txtHouse.getText() +
                        "\n\nFind out which house you belong to: https://github.com/knjk04/SortingHat";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });

        imBtnToggleSound = findViewById(R.id.imToggleSoundBtn);
    }

    // On click listener
    public void toggleSound(View view) {
        Log.d(TAG, "In ResultsActivity's toggleSound()");
        if (isMuted) {
            imBtnToggleSound.setImageResource(R.drawable.mute_white_24dp);
            mMusicController.resumeMusic();
        } else {
            imBtnToggleSound.setImageResource(R.drawable.volume_up_white_24dp);
            mMusicController.pauseMusic();
        }
        isMuted = !isMuted;
    }

    private void animate() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimation.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotateAnimation);
        animatorSet.start();
    }

    private void setEmblem() {
        Log.d(TAG, "In setEmblem()");
        switch (mHouse.toLowerCase()) {
            case GRYFFINDOR:
                imageView.setImageResource(R.drawable.gryffindor_crest_transparent);
                createBackgroundMusic();
                break;
            case HUFFLEPUFF:
                imageView.setImageResource(R.drawable.hufflepuff_crest_transparent);
                createBackgroundMusic();
                break;
            case RAVENCLAW:
                imageView.setImageResource(R.drawable.ravenclaw_crest_transparent);
                createBackgroundMusic();
                break;
            case SLYTHERIN:
                imageView.setImageResource(R.drawable.slytherin_crest_transparent);
                createBackgroundMusic();
        }
        mMusicController.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMusicController.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMusicController.resumeMusic();
    }

    private void createBackgroundMusic() {
        mMusicController = new MusicController(this);
        strToHouse();
        mMusicController.start();
    }

    // Determines which house music to play
    private void strToHouse() {
        String house = mHouse.toLowerCase();
        Log.d(TAG, "house in strToHouse: " + txtHouse.getText());

        switch (house) {
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
