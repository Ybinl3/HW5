package com.example.hw5;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Stroke {
    private Path path;
    private Paint paint;

    public Stroke(Paint paint){
        this.paint = paint;
    }
    public Path getPath(){
        return this.path;
    }
    public Paint getPaint(){
        return this.paint;
    }

    public void addPoint(PointF pt){
        if(this.path == null){
            this.path = new Path();
            this.path.moveTo(pt.x, pt.y);
        }
        else{
            path.lineTo(pt.x, pt.y);
        }
    }
}
