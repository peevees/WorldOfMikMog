package com.example.morgan.WorldOfMikMog;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class PlayerView extends ImageView {


    //movement
    private float touchStartX;
    private float touchStartY;
    private float touchEndX;
    private float touchEndY;
    private float deltaY;
    private float deltaX;

    //screen
    private int screenWidth;
    private int screenHeight;

    //player
    private ImageView player;
    private float playerY;
    private float playerX;
    private float playerWidth;
    private float playerHeight;


    public PlayerView(Context context, int screenW, int screenH){
        super(context);
        this.screenWidth = screenW;
        this.screenHeight = screenH;
    }
    public PlayerView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void insideGameArea(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        player = (ImageView) findViewById(R.id.player);
        playerY = player.getY();
        playerX = player.getX();
        playerWidth = player.getWidth();
        playerHeight = player.getHeight();

        switch(action){
            case(MotionEvent.ACTION_DOWN):
                touchStartX = event.getX();
                touchStartY = event.getY();

               // Log.d("PRESSED_DOWN", "press down on: " + touchStartX + ", " + touchStartY);
                return true;
            case(MotionEvent.ACTION_UP):
                touchEndX = event.getX();
                touchEndY = event.getY();

                //Log.d("PRESSED_UP", "press up on: " + touchEndX + ", " + touchEndY);
                deltaY = touchStartY - touchEndY;
                deltaX = touchStartX - touchEndX;
                Log.d("SWIPED", "Swiped with delta: " + deltaX + ", " + deltaY);

                if (deltaY > 50 && deltaX < 10) {//up
                    Log.d("APP", " inside the allowed swipe UP area");

                    Log.d("PLAYER_POS", "player was at: " + playerX + ", " + playerY);

                    if(playerY - playerHeight >= 0) {
                        player.setY(playerY - playerHeight);
                        playerY = player.getY();
                        Log.d("PLAYER_POS", "player is now at: " + playerX + ", " + playerY);
                    }

                } else if (deltaY < -50 && deltaX < 10) {//down
                    Log.d("APP", " inside the allowed swipe DOWN area");

                    Log.d("PLAYER_POS", "player was at: " + playerX + ", " + playerY);

                    if(playerY + playerHeight < screenHeight - playerHeight) {
                        player.setY(playerY + playerHeight);
                        playerY = player.getY();
                        Log.d("PLAYER_POS", "player is now at: " + playerX + ", " + playerY);
                    }

                } else if (deltaY < 10 && deltaX > 50) {//left
                    Log.d("APP", " inside the allowed swipe LEFT area");

                    Log.d("PLAYER_POS", "player was at: " + playerX + ", " + playerY);

                    if(playerX - playerWidth <= 0) {
                        player.setX(playerX - playerWidth);
                        playerX = player.getX();
                        Log.d("PLAYER_POS", "player is now at: " + playerX + ", " + playerY);
                    }

                } else if (deltaY < 10 && deltaX < -50) {//right
                    Log.d("APP", " inside the allowed swipe RIGHT area");

                    Log.d("TEST", "screen width: " + screenWidth);
                    Log.d("TEST", "screenwidth after removal of player width: " + (screenWidth - playerWidth));
                    Log.d("PLAYER_POS", "player was at: " + playerX + ", " + playerY);
                    if(playerX + playerWidth < screenWidth - playerWidth) {
                        player.setX(playerX + playerWidth);
                        playerX = player.getX();
                        Log.d("PLAYER_POS", "player is now at: " + playerX + ", " + playerY);
                    }
                }

                return true;

            default:
                return super.onTouchEvent(event);
        }

    }
}