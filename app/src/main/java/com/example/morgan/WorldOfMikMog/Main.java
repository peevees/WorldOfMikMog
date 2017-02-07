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
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


//TODO change walking code, add animation,
public class Main extends AppCompatActivity implements View.OnTouchListener {

    //gesture detector
    private GestureDetectorCompat detector;

    //TextViews


    //ImageViews
    private ImageView player;

    //Size
    private int frameHeight;
    private int playerSize;
    private int screenWidth;
    private int screenHeight;
    private int dpWidthInPx;
    private int dpHeightInPx;

    //postion
    private int playerY;
    private int playerX;

    //Speed
    private int playerSpeed;

    //Sounds and music
    private SoundPlayer sound;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageView) findViewById(R.id.player);

        //detector
        detector = new GestureDetectorCompat(this, onSwipeListener);
        player.setOnTouchListener(this);

        //music & sound
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

        //speed
        playerSpeed = Math.round(screenHeight / 60F);

        world();
        player();

    }
    public void pxtodp(){
        final float scale = getResources().getDisplayMetrics().density;
        dpWidthInPx  = (int) (50 * scale);
        dpHeightInPx = (int) (50 * scale);

    }
    public void player(){
        pxtodp();
        ImageView player = new ImageView(this);

        player.setImageResource(R.drawable.bob);

        FrameLayout.LayoutParams playerParams = new FrameLayout.LayoutParams();

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        player.setLayoutParams(playerParams);
        frameLayout.addView(player);
        player.getLayoutParams().height = dpHeightInPx;
        player.getLayoutParams().width = dpWidthInPx;
        player.requestLayout();


    }
    public void world(){

        pxtodp();
        ImageView myImageView = new ImageView(this);
        myImageView.setImageResource(R.drawable.grass);


        GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        myImageView.setLayoutParams(gridParams);
        gridLayout.addView(myImageView);
        myImageView.getLayoutParams().height = dpHeightInPx;
        myImageView.getLayoutParams().width = dpWidthInPx;
        myImageView.requestLayout();


    }
    /*
    //change position
    public void changePos() {

        //move player
        switch (direction){
            case left:
                playerX -= playerSpeed;
                break;
            case right:
                playerX += playerSpeed;
                break;
            case up:
                playerY += playerSpeed;
                break;
            case down:
                playerX -= playerSpeed;
                break;
            default:
                playerX = playerX;
                break;
        }

        //Check player position//TODO check player to stay inside frame and against objects

    }
    */


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return detector.onTouchEvent(motionEvent);
    }

    OnSwipeListener onSwipeListener = new OnSwipeListener() {

        @Override
        public boolean onSwipe(Direction direction) {

            // Possible implementation
            if(direction == Direction.left|| direction == Direction.right) {
                Log.d("LEFTRIGHT", "left or right swipe");
                playerX -= playerSpeed;
                // Do something COOL like animation or whatever you want
                // Refer to your view if needed using a global reference
                return true;
            }
            else if(direction == Direction.up|| direction == Direction.down) {
                Log.d("DOWNUP", "up or down swipe");
                playerY -= playerSpeed;
                // Do something COOL like animation or whatever you want
                // Refer to your view if needed using a global reference
                return true;
            }
            return super.onSwipe(direction);
        }
    };

}