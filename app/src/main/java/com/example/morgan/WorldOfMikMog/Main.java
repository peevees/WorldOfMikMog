package com.example.morgan.WorldOfMikMog;

import android.content.Intent;
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
public class Main extends AppCompatActivity {

    //TextViews


    //ImageViews
    private char[][] world;
    private int type;

    //Size
    private int screenWidth;
    private int screenHeight;
    private int rowCount = 10;
    private int columnCount = 15;
    private int pictureWidth;
    private int pictureHeight;

    //Sounds and music
    private SoundPlayer sound;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set immersive mode
        /*
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
                        */

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //music & sound
        sound = new SoundPlayer(this);
        music = new MediaPlayer();
        music = MediaPlayer.create(this, R.raw.forest);//TODO change sound depending on location
        music.setVolume(0.2f, 0.2f);
        music.start();
        music.setLooping(true);

        //create world print world and create player and print player
        createWorld();
        printWorld();
        player();
    }

    public void pictureSize(){

        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        pictureWidth = screenWidth / columnCount;
        pictureHeight = screenHeight / rowCount;

        /*pxtodp
        final float scale = getResources().getDisplayMetrics().density;
        dpWidthInPx  = (int) (pictureWidth * scale);
        dpHeightInPx = (int) (pictureHeight * scale);
        */
    }
    public void player(){

        pictureSize();
        PlayerView player = new PlayerView(this, screenWidth, screenHeight);
        player.setImageResource(R.drawable.bob);
        player.setId(R.id.player);
        FrameLayout.LayoutParams playerParams = new FrameLayout.LayoutParams(pictureWidth ,pictureHeight );

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_main);
        frameLayout.invalidate();
        player.setLayoutParams(playerParams);
        frameLayout.addView(player);

        player.getLayoutParams().height = pictureHeight;
        player.getLayoutParams().width = pictureWidth;

        player.requestLayout();

    }
    public void printWorld(){

        pictureSize();
        ImageView myImageView;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        gridLayout.invalidate();
        gridLayout.setColumnCount(columnCount);
        gridLayout.setRowCount(rowCount);

        for(int i = 0; i < rowCount; i ++){
            for(int j = 0; j < columnCount; j ++) {
                myImageView = new ImageView(this);

                switch(world[i][j]) {
                    case 'T':
                        type = (int)Math.floor(Math.random()*2);
                        Log.d("DEBUG_RANDOM", "RANDOM number is: " + type);
                        switch(type){
                            case 0:
                                myImageView.setImageResource(R.drawable.tree);
                                break;
                            case 1:
                                myImageView.setImageResource(R.drawable.tree2);
                                break;
                        }
                        //myImageView.setImageResource(R.drawable.tree);
                        break;
                    case 'S':

                        type = (int)Math.floor(Math.random()*3);
                        Log.d("DEBUG_RANDOM", "RANDOM number is: " + type);
                        switch(type){
                            case 0:
                                myImageView.setImageResource(R.drawable.stone);
                                break;
                            case 1:
                                myImageView.setImageResource(R.drawable.stone2);
                                break;
                            case 2:
                                myImageView.setImageResource(R.drawable.stone3);
                                break;
                        }

                        //myImageView.setImageResource(R.drawable.stone);
                        break;
                    case 'G':
                        type = (int)Math.floor(Math.random()*3);
                        Log.d("DEBUG_RANDOM", "RANDOM number is: " + type);
                        switch(type){
                            case 0:
                                myImageView.setImageResource(R.drawable.grass);
                                break;
                            case 1:
                                myImageView.setImageResource(R.drawable.grass3);
                                break;
                            case 2:
                                 myImageView.setImageResource(R.drawable.grass2);
                                break;
                        }
                        //myImageView.setImageResource(R.drawable.grass);
                        break;
                    case 'C':
                        myImageView.setImageResource(R.drawable.treasure);
                        break;
                    }
                Log.d("World", "" + world[i][j]);
                GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();

                myImageView.setLayoutParams(gridParams);
                gridLayout.addView(myImageView);

                myImageView.getLayoutParams().height = pictureHeight;//dpHeightInPx;
                myImageView.getLayoutParams().width = pictureWidth;
                myImageView.requestLayout();
            }
        }
    }
    public boolean moveAllowed(int posX, int posY){
        Log.d("IN_METHOD", "METHOD moveAllowed entered");
        if(posX < 0 || posY < 0 || posX >= columnCount || posY >= rowCount) {
            Log.d("IN_SCREEN_CHECK", "IF inside screen entered");
            return false;
        }
        if(world[posY][posX] == 'G') {
            Log.d("IN_GRASS_CHECK", "IF grass check entered");
            return true;
        }
        if(world[posY][posX] == 'C') {
            startActivity(new Intent(getApplicationContext(),GameOver.class));
            music.stop();
            music.reset();
            music.release();
        }
        Log.d("IN_METHOD", "IF moveAllowed left");
        return false;
    }
    public void createWorld(){

        world = new char[][]{
                {'G', 'G', 'G', 'G', 'S', 'T', 'S', 'G', 'G', 'G', 'G', 'T', 'T', 'T', 'T', 'T'},
                {'T', 'G', 'G', 'S', 'S', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'T', 'T', 'T', 'T'},
                {'T', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'T', 'T', 'T'},
                {'T', 'S', 'G', 'G', 'S', 'S', 'G', 'S', 'S', 'G', 'G', 'S', 'G', 'G', 'T', 'T'},
                {'G', 'G', 'G', 'G', 'S', 'S', 'G', 'S', 'G', 'G', 'S', 'S', 'S', 'G', 'G', 'T'},
                {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'S', 'S', 'C', 'G', 'G', 'G'},
                {'S', 'G', 'G', 'G', 'T', 'T', 'T', 'G', 'G', 'G', 'S', 'S', 'S', 'G', 'G', 'G'},
                {'G', 'G', 'G', 'T', 'G', 'G', 'G', 'T', 'G', 'G', 'G', 'S', 'G', 'G', 'G', 'S'},
                {'G', 'T', 'T', 'G', 'G', 'T', 'G', 'G', 'T', 'G', 'G', 'G', 'G', 'G', 'S', 'S'},
                {'G', 'G', 'G', 'G', 'T', 'T', 'T', 'G', 'G', 'G', 'G', 'G', 'G', 'S', 'S', 'S'}
        };

    }//TODO check player against objects

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