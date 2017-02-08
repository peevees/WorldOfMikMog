package com.example.morgan.WorldOfMikMog;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;


//TODO change walking code, add animation,
public class Main extends AppCompatActivity implements View.OnTouchListener {

    //gesture detector
    private GestureDetectorCompat detector;

    //TextViews


    //ImageViews
    private ImageView[][] Cell;
    private ImageView player;

    //Size
    private int screenWidth;
    private int screenHeight;
    private int rowCount = 6;
    private int columnCount = 15;
    private int pictureWidth;
    private int pictureHeight;

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

        //set immersive mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //detector
        detector = new GestureDetectorCompat(this, onSwipeListener);
        //player.setOnTouchListener(this);

        //music & sound
        sound = new SoundPlayer(this);
        music = new MediaPlayer();
        music = MediaPlayer.create(this, R.raw.success);//TODO change sound depending on location
        music.start();
        music.setVolume(0.1f, 0.1f);
        music.setLooping(true);

        //speed
        playerSpeed = Math.round(screenHeight / 60F);



        createWorld();
        printWorld();
        player();

        GridLayout swipeControl = (GridLayout) findViewById(R.id.grid);
        swipeControl.setOnTouchListener(this);


    }
    public void pictureSize(){
        pictureWidth = screenWidth / columnCount;
        pictureHeight = screenWidth / columnCount;
        Log.d("Picture_width", "the width of the picture is " + pictureWidth);

        /*pxtodp
        final float scale = getResources().getDisplayMetrics().density;
        dpWidthInPx  = (int) (pictureWidth * scale);//TODO make size of pictures depending on size of screen
        dpHeightInPx = (int) (pictureHeight * scale);
        */
    }
    public void player(){

        pictureSize();
        ImageView player = new ImageView(this);
        player.setImageResource(R.drawable.bob);
        player.setId(R.id.player);
        FrameLayout.LayoutParams playerParams = new FrameLayout.LayoutParams(pictureWidth ,pictureHeight );

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_main);
        frameLayout.invalidate();
        player.setLayoutParams(playerParams);
        frameLayout.addView(player);

        player.requestLayout();


    }
    public void printWorld(){//TODO make dependent on screensize

        pictureSize();
        ImageView myImageView;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        gridLayout.invalidate();
        gridLayout.setColumnCount(columnCount);
        gridLayout.setRowCount(rowCount);

        for(int i = 0; i < (columnCount*rowCount); i ++){
            myImageView = new ImageView(this);
            myImageView.setImageResource(R.drawable.grassdb);


            GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();

            myImageView.setLayoutParams(gridParams);

            gridLayout.addView(myImageView);
            myImageView.getLayoutParams().height = pictureHeight;//dpHeightInPx;
            myImageView.getLayoutParams().width = pictureWidth;
            myImageView.requestLayout();
        }
    }
    public void createWorld(){

        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        Log.d("screenWidth", "screen width is : " + screenWidth);
        Log.d("screenHeight", "screen height is : " + screenHeight);

        //Cell = new ImageView[][]
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