package com.android.image_passwordapp;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran ALi on 2/8/2016.
 */
public class PointListener implements View.OnTouchListener {
    public static final int Num_Points = 4;
    private PointCollector pointCollector;
    private List<Point> points = new ArrayList<Point>();

    public void setPointCollector(PointCollector pointCollector) {
        this.pointCollector = pointCollector;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        String message = String.format("Coordinates are %d , %d",x,y);

        Log.d("TAG", message);

        points.add(new Point(x,y));

        if (points.size() == Num_Points ){

                pointCollector.listener(points);
        }
        return false;
    }
    public void clear(){
        points.clear();
    }
}
