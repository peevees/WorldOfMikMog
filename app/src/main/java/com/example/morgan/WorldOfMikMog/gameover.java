package com.example.morgan.WorldOfMikMog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Morgan on 2017-02-16.
 */

public class GameOver extends AppCompatActivity{

    protected MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Helskärm
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gameover);



        sound = new MediaPlayer();
        sound = MediaPlayer.create(this, R.raw.clear);
        sound.setVolume(0.2f, 0.2f);
        sound.start();


    }

    public void restartGame(View view){

        startActivity(new Intent(getApplicationContext(),splashScreen.class));
        sound.stop();
    }

    //disable return button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
