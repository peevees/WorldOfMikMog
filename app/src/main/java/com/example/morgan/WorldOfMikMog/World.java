package com.example.morgan.WorldOfMikMog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

public class World {

    private ImageView imageView;
    //private Object[][] world;
    private Bitmap world;


    public World(Context context){

        GridLayout canvas = (GridLayout) ((Activity) context).findViewById(R.id.grid);
        Log.d("past", "Past the finding of grid");
        Bitmap world = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.grass)).getBitmap();
        /*
        if(world == ){
            Log.d("DECODE_PROCESS", "the decode process failed?");
        }
        */
        Log.d("past", "Past the decoding of image");
        imageView.setImageBitmap(world);
        Log.d("past", "Past the set image as imageview");
        canvas.addView(imageView);
        Log.d("past", "Past adding imageview");
    }

}
