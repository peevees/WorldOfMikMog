package com.example.morgan.WorldOfMikMog;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class PlayerView extends ImageView {//// TODO: chekc array movement is borked for now

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
    private int playerPosX = 0;
    private int playerPosY = 0;

    public PlayerView(Context context, int screenW, int screenH){//constructor
        super(context);
        this.screenWidth = screenW;
        this.screenHeight = screenH;
    }
    public PlayerView(Context context, AttributeSet attrs){
        super(context, attrs);
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
                return true;
            case(MotionEvent.ACTION_UP):
                touchEndX = event.getX();
                touchEndY = event.getY();
                deltaY = touchStartY - touchEndY;
                deltaX = touchStartX - touchEndX;
                if (deltaY > 50 && deltaX < 10) {//up
                    if(((Main)getContext()).moveAllowed(playerPosX, playerPosY-1)){
                        playerPosY--;

                        player.setY(playerY - playerHeight);
                        playerY = player.getY();
                    }
                    /*
                    if(playerY - playerHeight >= 0) {
                        player.setY(playerY - playerHeight);
                        playerY = player.getY();
                    }
                    */
                } else if (deltaY < -50 && deltaX < 10) {//down
                    if(((Main)getContext()).moveAllowed(playerPosX, playerPosY+1)){
                        playerPosY++;
                        player.setY(playerY + playerHeight);
                        playerY = player.getY();
                    }
                    /*
                    if(playerY + playerHeight < screenHeight - playerHeight) {
                        player.setY(playerY + playerHeight);
                        playerY = player.getY();
                    }
                    */
                } else if (deltaY < 10 && deltaX > 50) {//left
                    if(((Main)getContext()).moveAllowed(playerPosX-1, playerPosY)){
                        playerPosX--;
                        player.setX(playerX - playerWidth);
                        playerX = player.getX();
                    }
                    /*
                    if(playerX - playerWidth <= 0) {
                        player.setX(playerX - playerWidth);
                        playerX = player.getX();
                    }
                    */
                } else if (deltaY < 10 && deltaX < -50) {//right
                    if(((Main)getContext()).moveAllowed(playerPosX+1, playerPosY)){
                        playerPosX++;
                        player.setX(playerX + playerWidth);
                        playerX = player.getX();
                    }
                    /*
                    if(playerX + playerWidth < screenWidth - playerWidth) {
                        player.setX(playerX + playerWidth);
                        playerX = player.getX();
                    }
                    */
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}