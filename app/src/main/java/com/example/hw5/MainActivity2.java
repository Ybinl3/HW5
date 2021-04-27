package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    MyCanvas myCanvas;
    TouchListener touchListener;
    Random rd = new Random();

    Button redB;
    Button blueB;
    Button greenB;

    Button undoB;
    Button clearB;
    Button doneB;
    public static int pColor = 0; //0 = black, 1 = red, 2 = green, 3 = blue



    static final int REQUEST_IMAGE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        redB = (Button)findViewById(R.id.redB);
        redB.setOnClickListener(this);
        blueB = (Button)findViewById(R.id.blueB);
        blueB.setOnClickListener(this);
        greenB = (Button)findViewById(R.id.greenB);
        greenB.setOnClickListener(this);

        doneB = (Button)findViewById(R.id.doneB);
        doneB.setOnClickListener(this);

        undoB = (Button)findViewById(R.id.undoB);
        undoB.setOnClickListener(this);

        clearB = (Button)findViewById(R.id.clearB);
        clearB.setOnClickListener(this);
        myCanvas = (MyCanvas)findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);
        Intent intent = getIntent();
        if(intent != null){
            Bundle extras = intent.getBundleExtra("data");
            Bitmap thumbnail = (Bitmap)  extras.get("data");
            myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
        }

    }


    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        //myCanvas.removePath(id);
    }

    public void onDoubleTap(float x, float y) {
        myCanvas.hokieIcon(x, y);
    }

    public void onLongPress(float x, float y) {
        myCanvas.starIcon(x, y);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.redB:
                pColor = 1;
                break;
            case R.id.blueB:
                pColor = 2;
                break;
            case R.id.greenB:
                pColor = 3;
                break;
            case R.id.undoB:
                myCanvas.undo();
                break;
            case R.id.clearB:
                myCanvas.clear();
                break;
            case R.id.doneB:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}