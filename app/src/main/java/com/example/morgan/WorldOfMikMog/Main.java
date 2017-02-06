package com.example.morgan.WorldOfMikMog;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


//TODO change walking code, add animation,
public class Main extends AppCompatActivity {

    //gesture detector
    private GestureDetectorCompat detector;
    //textview
    private TextView direction;

    //Sounds and music
    private SoundPlayer sound;
    private MediaPlayer music;

    //player
    private Player player;

    //size
    private int screenWidth;
    private int screenHeight;

    //position
    private int playerX;
    private int PlayerY;
    private int[] position = new int[2];

    //speed
    private int playerSpeed;

private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detector = new GestureDetectorCompat(context, onSwipeListener);



        //test to see if it updates the repository
        direction = (TextView) findViewById(R.id.textView1);

        //sound
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
        screenWidth = size.x;
        screenHeight = size.y;

    }

    //change position
    public void changePos() {

    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return detector.onTouchEvent(motionEvent);
    }

    OnSwipeListener onSwipeListener = new OnSwipeListener() {

        @Override
        public boolean onSwipe(Direction direction) {

            // Possible implementation
            if(direction == Direction.left|| direction == Direction.right) {
                // Do something COOL like animation or whatever you want
                // Refer to your view if needed using a global reference
                return true;
            }
            else if(direction == Direction.up|| direction == Direction.down) {
                // Do something COOL like animation or whatever you want
                // Refer to your view if needed using a global reference
                return true;
            }

            return super.onSwipe(direction);
        }
};}