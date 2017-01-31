package com.example.morgan.WorldOfMikMog;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


//TODO change walking code, add animation,
public class Main extends AppCompatActivity {

    private ImageView player;
    private TextView direction;

    //Sounds and music
    private SoundPlayer sound;
    private MediaPlayer music;

    //size
    private int playerHeight;
    private int playerWidth;
    private int screenWidth;
    private int screenHeight;

    //position
    private int playerX;
    private int PlayerY;
    private int[] position = new int[2];

    //speed
    private int playerSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test to see if it updates the repository
        direction = (TextView) findViewById(R.id.textView1);
        player = (ImageView) findViewById(R.id.player);
        playerHeight = player.getHeight();
        playerWidth = player.getWidth();

        //ljud
        sound = new SoundPlayer(this);
        music = new MediaPlayer();
        music = MediaPlayer.create(this, R.raw.success);//TODO change sound depending on location
        music.start();
        music.setVolume(0.1f, 0.1f);
        music.setLooping(true);

        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        player.getLocationOnScreen(position);
        Log.d("postion", String.valueOf(position[0]));
        Log.d("postion", String.valueOf(position[1]));
        screenWidth = size.x;
        screenHeight = size.y;

    }

    public void leftClick(View view){
        //something happens
        Log.d("left", "it went left");
        direction.setText("Went left");
        ((ViewGroup.MarginLayoutParams)player.getLayoutParams()).leftMargin-=20;
        player.requestLayout();


        player.getLocationOnScreen(position);
        Log.d("postion", String.valueOf(position[0]));
        Log.d("postion", String.valueOf(position[1]));
    }
    public void rightClick(View view){
        Log.d("right", "It went right");
        direction.setText("Went right");
        ((ViewGroup.MarginLayoutParams)player.getLayoutParams()).leftMargin+=20;
        player.requestLayout();

        player.getLocationOnScreen(position);
        Log.d("postion", String.valueOf(position[0]));
        Log.d("postion", String.valueOf(position[1]));
    }
    public void upClick(View view){
        Log.d("TEST", "Innan ljud");
        sound.playWalkSound();
        Log.d("TEST", "efter ljud");
        Log.d("up", "it went up");
        direction.setText("Went up");
        ((ViewGroup.MarginLayoutParams)player.getLayoutParams()).topMargin-=20;
        player.requestLayout();

        player.getLocationOnScreen(position);
        Log.d("postion", String.valueOf(position[0]));
        Log.d("postion", String.valueOf(position[1]));
    }
    public void downClick(View view){
        sound.playWalkSound();
        Log.d("down", "it went down");
        direction.setText("Went down");
        ((ViewGroup.MarginLayoutParams)player.getLayoutParams()).topMargin+=20;
        player.requestLayout();

        player.getLocationOnScreen(position);
        Log.d("postion", String.valueOf(position[0]));
        Log.d("postion", String.valueOf(position[1]));
    }

    //change position
    public void changePos(){

    }

}
