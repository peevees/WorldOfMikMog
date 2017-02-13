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

    public PlayerView(Context context){
        super(context);
    }
    public PlayerView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action){
            case(MotionEvent.ACTION_DOWN):
                touchStartX = event.getX();
                touchStartY = event.getY();
                Log.d("PRESSED_DOWN", "press down on: " + touchStartX + ", " + touchStartY);
                return true;
            case(MotionEvent.ACTION_UP):
                touchEndX = event.getX();
                touchEndY = event.getY();
                Log.d("PRESSED_DOWN", "press down on: " + touchEndX + ", " + touchEndY);
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }
}
