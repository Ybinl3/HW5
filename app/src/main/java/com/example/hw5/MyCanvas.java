package com.example.hw5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;


public class MyCanvas extends View {
    //HashMap <Integer, Path> activePaths;
    HashMap <Integer, Stroke> activePaths;
    ArrayList<Stroke> allPaths;
    ArrayList<Float> allStarsX;
    ArrayList<Float> allStarsY;

    ArrayList<Float> allHokieX;
    ArrayList<Float> allHokieY;

    ArrayList<Paint> pathPaints;
    Paint pathPaint;
    Path path;

    Bitmap sIcon;
    Bitmap hIcon;
    //Bitmap bitmap1;

    int counter = 0;
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        allPaths = new ArrayList<>();
        path = new Path();
        pathPaints = new ArrayList<>();
        allStarsX = new ArrayList<>();
        allStarsY = new ArrayList<>();

        allHokieX = new ArrayList<>();
        allHokieY = new ArrayList<>();

        sIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.staricon);
        hIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.hokieicon);

    }

    @Override
    protected void onDraw(Canvas canvas) {


        for(int i = 0; i < allStarsX.size(); i++){
            canvas.drawBitmap(sIcon, allStarsX.get(i) - (sIcon.getWidth()/2), allStarsY.get(i) - (sIcon.getHeight()/2), null);
        }
        for(int i = 0; i < allHokieX.size(); i++){
            canvas.drawBitmap(hIcon, allHokieX.get(i) - (hIcon.getWidth()/2), allHokieY.get(i) - (hIcon.getHeight()/2), null);
        }
        if(allPaths != null){
            for(Stroke stroke: allPaths){
                if(stroke != null){
                    Path path = stroke.getPath();
                    Paint cPaint = stroke.getPaint();
                    if ((path != null) && cPaint != null){
                        canvas.drawPath(path, cPaint);
                    }
                }
            }
        }

        /*
        for(int i = 0; i < allPaths.size(); i++){
            canvas.drawPath(allPaths.get(i), pathPaints.get(i));
        }

        */
        /*
        for(Path path: allPaths.values()){
            canvas.drawPath(path, pathPaint);
        }
        */
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        if(MainActivity2.pColor == 1) {
            paint.setColor(Color.RED);
        }
        else if(MainActivity2.pColor == 2){
            paint.setColor(Color.BLUE);
        }
        else if(MainActivity2.pColor == 3){
            paint.setColor(Color.GREEN);
        }
        else{
            paint.setColor(Color.BLACK);
        }
        PointF pt = new PointF(x, y);
        Stroke stroke = new Stroke(paint);
        stroke.addPoint(pt);

        allPaths.add(stroke);
        activePaths.put(id, stroke);
        invalidate();
    }
    public void hokieIcon(float x, float y){
        allHokieX.add(x);
        allHokieY.add(y);
    }
    public void starIcon(float x, float y){
        allStarsX.add(x);
        allStarsY.add(y);
    }
    public void updatePath(int id, float x, float y) {
        Stroke stroke = activePaths.get(id);
        if(stroke != null){
            PointF pt = new PointF(x,y);
            stroke.addPoint(pt);
        }
        invalidate();
    }
    public void undo(){
        if(allPaths.size() > 0){
            allPaths.remove(allPaths.size() - 1);
            invalidate();
        }


    }
    public void clear(){
        allHokieX = new ArrayList<>();
        allHokieY = new ArrayList<>();

        allStarsX = new ArrayList<>();
        allStarsX = new ArrayList<>();
        while(allPaths.size() != 0){
            allPaths.remove(allPaths.size() - 1);
        }
        invalidate();
    }

    /*
    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }
    */

}