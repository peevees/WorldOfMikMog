package com.example.morgan.WorldOfMikMog;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
        import android.view.SurfaceView;

public class GameEngine extends AppCompatActivity {

    GameView gameView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
    }
    class GameView extends SurfaceView implements Runnable {

        Thread gameThread = null;

        SurfaceHolder ourHolder;

        volatile boolean playing;

        Canvas canvas;
        Paint paint;

        Long fps;

        private Long timeThisFrame;

        Bitmap bitmapBob;


        Boolean isMoving = false;

        float walkSpeedPerSecond = 150;

        float bobXPosition = 10;


        public GameView(Context context) {

            super(context);

            ourHolder = getHolder();
            paint = new Paint();

            bitmapBob = BitmapFactory.decodeResource(this.getResources(), R.drawable.bob);

        }

        @Override
        public void run() {
            while (playing) {

                long startFrameTime = System.currentTimeMillis();

                update();

                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame > 0) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }
    }



}
