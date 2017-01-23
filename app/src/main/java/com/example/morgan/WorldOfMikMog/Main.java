package com.example.morgan.WorldOfMikMog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test to see if it updates the repository

    }

    // /better to assign method to xml layout
    public void leftClick(View view){
        //something happens
        Log.d("myTag", "it went left");
        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText("Went left");

    }
    public void rightClick(){
        Log.d("myTag", "it went right");
        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText("Went right");
    }
    public void upClick(){
        Log.d("myTag", "it went up");
        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText("Went up");
    }
    public void downClick(){
        Log.d("myTag", "it went down");
        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText("Went down");
    }

}
