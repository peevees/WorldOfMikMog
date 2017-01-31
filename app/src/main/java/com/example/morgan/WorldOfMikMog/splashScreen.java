package com.example.morgan.WorldOfMikMog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class splashScreen extends AppCompatActivity {

    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
            sound = new MediaPlayer();
            sound = MediaPlayer.create(this, R.raw.tes4title);
            sound.start();


    }

    public void startGame(View view){
        sound.release();
        startActivity(new Intent(getApplicationContext(),Main.class));
    }
}
