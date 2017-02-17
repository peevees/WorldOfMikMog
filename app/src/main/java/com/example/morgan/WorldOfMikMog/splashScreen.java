package com.example.morgan.WorldOfMikMog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

public class splashScreen extends AppCompatActivity {

    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Helskärm
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);



            sound = new MediaPlayer();
            sound = MediaPlayer.create(this, R.raw.start);
            sound.setVolume(0.2f, 0.2f);
            sound.start();
            sound.setLooping(true);


    }

    public void startGame(View view){
        sound.release();
        startActivity(new Intent(getApplicationContext(),Main.class));
    }
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
