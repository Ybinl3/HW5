package com.example.hw5;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;


public class TouchListener implements View.OnTouchListener {
    MainActivity2 mainActivity2;
    GestureDetectorCompat gestureDetectorCompat;
    int counter = 0;
    public TouchListener(MainActivity2 ma) {
        this.mainActivity2 = ma;
        gestureDetectorCompat = new GestureDetectorCompat(this.mainActivity2, new MyGestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();
        switch(maskedAction){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){

                    int id = motionEvent.getPointerId(i);
                    mainActivity2.addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    mainActivity2.updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                /*
            case MotionEvent.ACTION_UP:

                break;
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    mainActivity2.removePath(id);
                }
                break;
                */

        }
        return true;
    }



    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mainActivity2.onDoubleTap(e.getX(), e.getY());
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mainActivity2.onLongPress(e.getX(), e.getY());
            super.onLongPress(e);
        }
    }
}