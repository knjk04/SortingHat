package com.presentedbykaran.sortinghat;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Random;

// Wrapper class for a MediaPlayer object
public class MusicController {
    private final int[] gryffindorMusic = {R.raw.hana, R.raw.tobruk};
    private final int[] hufflepuffMusic = {R.raw.denali, R.raw.calabria};
    private final int[] ravenclawMusic  = {R.raw.banshee, R.raw.celsius, R.raw.kailua, R.raw.waikiki};
    private final int[] slytherinMusic  = {R.raw.castor};

    private MediaPlayer mediaPlayer = null;
    private int mTime;
    private int mFile = Integer.MAX_VALUE;

    public static final String TAG = MusicController.class.getSimpleName();

    private Context mContext;

    public MusicController(Context context) {
        mContext = context;
    }

    public int getFile() {
        return mFile;
    }

    // This method is intended to be used in classes that want to play audio files that are not
    // dependent on a house
    public void setFile(int file) {
        mediaPlayer = MediaPlayer.create(mContext, mFile = file);
    }

    // This method is used for selecting music based on the house the wizard/witch was sorted into
    public void setFileHouse(House house) {
        Random rand = new Random();
        // all house music arrays should be the same length
//        int randomNum = rand.nextInt(gryffindorMusic.length);
        int randomNum;

        switch(house) {
            case Gryffindor:
                randomNum = rand.nextInt(gryffindorMusic.length);
                mFile = gryffindorMusic[randomNum];
                break;
            case Ravenclaw:
                randomNum = rand.nextInt(gryffindorMusic.length);
                mFile = ravenclawMusic[randomNum];
                break;
            case Hufflepuff:
                randomNum = rand.nextInt(gryffindorMusic.length);
                mFile = hufflepuffMusic[randomNum];
                break;
            case Slytherin:
                randomNum = rand.nextInt(gryffindorMusic.length);
                mFile = slytherinMusic[randomNum];
                break;
        }
        mediaPlayer = MediaPlayer.create(mContext,mFile);
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mTime = mediaPlayer.getCurrentPosition();
            stop();
        }
    }

    public void start() {
        if (mFile == Integer.MAX_VALUE) Log.e(TAG, "mFile not set in MusicController");
        else mediaPlayer.start();
    }

    public boolean isPlaying() {
       return mediaPlayer.isPlaying();
    }


    public void stop() {
        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

}
