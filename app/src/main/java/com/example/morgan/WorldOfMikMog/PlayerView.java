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
        init();
    }
    public PlayerView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    void init() {
        pressedScreen();
        releasedScreen();
    }

    public void pressedScreen(){
        Log.d("PRESSED_SCREEN", "pressed screen cordinate: " + touchStartX + ", " + touchStartY);
    }
    public void releasedScreen(){

        Log.d("PRESSED_SCREEN", "pressed screen cordinate: " + touchEndX + ", " + touchEndY);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action){
            case(MotionEvent.ACTION_DOWN):
                touchStartX = event.getX();
                touchStartY = event.getY();
                return true;
            case(MotionEvent.ACTION_UP):
                touchEndX = event.getX();
                touchEndY = event.getY();
                return true;
            default:
                return super.onTouchEvent(event);
        }

    }
}
