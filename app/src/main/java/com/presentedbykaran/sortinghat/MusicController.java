package com.presentedbykaran.sortinghat;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Random;

// Wrapper class for a MediaPlayer object
public class MusicController {
    private final int[] gryffindorMusic = {R.raw.tobruk, R.raw.parbat, R.raw.wallula, R.raw.leopard,
                                           R.raw.maggiore};

    private final int[] hufflepuffMusic = {R.raw.denali, R.raw.copacabana,
                                           R.raw.zanzibar, R.raw.flowers, R.raw.manticore};

    private final int[] ravenclawMusic  = {R.raw.banshee, R.raw.celsius, R.raw.mistry, R.raw.como,
                                           R.raw.elba};

    private final int[] slytherinMusic  = {R.raw.centurion, R.raw.lieutenant, R.raw.arachne,
                                           R.raw.nanga, R.raw.turin};

    private MediaPlayer mediaPlayer = null;
    private int mTime = 0;
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
        int randomNum;

        switch(house) {
            case Gryffindor:
                randomNum = rand.nextInt(gryffindorMusic.length);
                mFile = gryffindorMusic[randomNum];
                break;
            case Ravenclaw:
                randomNum = rand.nextInt(ravenclawMusic.length);
                mFile = ravenclawMusic[randomNum];
                break;
            case Hufflepuff:
                randomNum = rand.nextInt(hufflepuffMusic.length);
                mFile = hufflepuffMusic[randomNum];
                break;
            case Slytherin:
                randomNum = rand.nextInt(slytherinMusic.length);
                mFile = slytherinMusic[randomNum];
                break;
        }
        mediaPlayer = MediaPlayer.create(mContext,mFile);
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mTime = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mTime);
            mediaPlayer.start();
        }
    }

    public void start() {
        if (mFile == Integer.MAX_VALUE) Log.e(TAG, "mFile not set in MusicController");
        else mediaPlayer.start();
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) return mediaPlayer.isPlaying();
        return false;
    }


    public void stop() {
        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
