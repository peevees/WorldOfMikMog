package com.example.morgan.WorldOfMikMog;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class PlayerView extends ImageView {

    private float touchStartX;
    private float touchStartY;
    private float touchEndX;
    private float touchEndY;
    private float deltaY;
    private float deltaX;

    private ImageView player;
    private float playerY;
    private float playerX;
    private float playerWidth;
    private float playerHeight;

    public PlayerView(Context context){
        super(context);
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

               // Log.d("PRESSED_DOWN", "press down on: " + touchStartX + ", " + touchStartY);
                return true;
            case(MotionEvent.ACTION_UP):
                touchEndX = event.getX();
                touchEndY = event.getY();

                //Log.d("PRESSED_UP", "press up on: " + touchEndX + ", " + touchEndY);
                deltaY = touchStartY - touchEndY;
                deltaX = touchStartX - touchEndX;
                Log.d("SWIPED", "Swiped with delta: " + deltaX + ", " + deltaY);

                if(deltaY > 50 && deltaX < 10){//up
                    Log.d("APP", " inside the allowed swipe UP area");
                    player.setY(playerY - playerHeight);
                }else if(deltaY < -50 && deltaX < 10){//down
                    Log.d("APP", " inside the allowed swipe DOWN area");
                    player.setY(playerY + playerHeight);
                }else if(deltaY < 10 && deltaX > 50){//left
                    Log.d("APP", " inside the allowed swipe LEFT area");
                    player.setX(playerX - playerWidth);
                }else if(deltaY < 10 && deltaX < -50){//right
                    Log.d("APP", " inside the allowed swipe RIGHT area");
                    player.setX(playerX + playerWidth);
                }

                return true;
            default:
                return super.onTouchEvent(event);
        }

    }
}