package com.example.morgan.WorldOfMikMog;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

public class SoundPlayer {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;

    private MediaPlayer mediaPlayer;
    private static SoundPool soundPool;
    private static int hitSound;
    private static int hurtSound;
    private static int splashSound;
    private static int walkSound;

    public SoundPlayer(Context context){

        //soundpool is deprecated in api level 21 (lollipop)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(audioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        }else{

            //SoundPool (int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);

        }


        mediaPlayer = MediaPlayer.create(context, R.raw.tes4title);


        //TODO new sounds needed
        /*
        splashSound = soundPool.load(context, R.raw.tes4title, 1);
        hitSound = soundPool.load(context, R.raw.success, 2);
        hurtSound = soundPool.load(context, R.raw.death, 3);
        */
        //sound effects here
        walkSound = soundPool.load(context, R.raw.footstep, 1);



    }
    public void stopSound(){
        mediaPlayer.release();
    }
    public void playSound(){
        Log.d("test", "kördes");
        mediaPlayer.start();
    }

    public void playHitSound(){
        //play(int soundID, float leftVolume, flot rightVolume, int priority, int loop, float rate);
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }
    public void playhurtSound(){
        //play(int soundID, float leftVolume, flot rightVolume, int priority, int loop, float rate);
        soundPool.play(hurtSound, 1.0f, 1.0f, 1, 0 , 1.0f);

    }
    public void playWalkSound(){
        soundPool.play(walkSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}