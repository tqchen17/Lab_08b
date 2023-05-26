package com.example.lab08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


import androidx.annotation.Nullable;

public class DrawView extends View {
    int n = 5;
    ArrayList<Bamboo> bamboo;
    private Paint p = new Paint();
    private int y=0, dY=5, deg=30, dDeg=2, dir=1;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Random rand = new Random();
        bamboo = new ArrayList<Bamboo>(n);

        for(int i = 0; i < n; i++)
        {
            bamboo.add(new Bamboo((float)rand.nextInt(getWidth()-80), (float)rand.nextInt(getHeight()-750), rand.nextInt(360), (float)(3*rand.nextDouble()), (float)(3*rand.nextDouble())));
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw ears
        p.setColor(Color.DKGRAY);
        p.setStrokeWidth(10f);
        canvas.drawCircle(375,300,75f, p);
        canvas.drawCircle(725,300,75f, p);

        //draw wings
        canvas.save();
        canvas.rotate(deg,225,700);
        canvas.drawOval(50,600,400, 1500, p);
        canvas.restore();
        canvas.save();
        canvas.rotate(deg*-1, 875,700);
        canvas.drawOval(700,600,1050, 1500, p);
        canvas.restore();

        //draw legs
        p.setStrokeWidth(50);
        p.setColor(Color.rgb(217, 151, 25));
        canvas.drawLine(425, 1200, 425, 1600, p);
        canvas.drawLine(675, 1200, 675, 1600, p);

        //draw head and body
        p.setStrokeWidth(10f);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(550,875,365f, p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(550,450,225f, p);
        canvas.drawCircle(550,875,375f, p);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(550,450,215f, p);

        //random ball
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.MAGENTA);
        canvas.drawCircle(100,y,75.5f, p);

        //draw left eye
        p.setColor(Color.DKGRAY);
        canvas.save();
        canvas.rotate(40,465,425);
        canvas.drawOval(415,350,515, 500, p);
        canvas.restore();

        //draw right eye
        p.setColor(Color.DKGRAY);
        canvas.save();
        canvas.rotate(320,635,425);
        canvas.drawOval(585,350,685, 500, p);
        canvas.restore();

        //draw nose
        canvas.drawCircle(550, 495, 30f, p);
        p.setColor(Color.WHITE);
        canvas.drawRect(520, 465, 580, 485, p);
        canvas.drawCircle(550, 508, 2.5f, p);

        //draw pupils
        p.setColor(Color.BLACK);
        canvas.drawCircle(475,410,20f, p);
        canvas.drawCircle(625,410,20f, p);
        p.setColor(Color.WHITE);
        canvas.drawCircle(480,405,3f, p);
        canvas.drawCircle(620,405,3f, p);

        //draw mouth
        p.setColor(Color.BLACK);
        p.setStrokeWidth(7);
        canvas.drawLine(550, 530, 550, 545, p);
        canvas.drawLine(510, 545, 525, 555, p);
        canvas.drawLine(525, 555, 550, 545, p);
        canvas.drawLine(550, 545, 575, 555, p);
        canvas.drawLine(575, 555, 590, 545, p);

        for(int i = 0; i < bamboo.size(); i++)
        {
            bamboo.get(i).update(getWidth(), getHeight());
            for(int k = 0; k < bamboo.size(); k++) {
                if(i != k) {
                    if (bamboo.get(i).intersect(bamboo.get(k))) {
                        bamboo.get(i).flipSpeed(true, true);
                        bamboo.get(k).flipSpeed(true, true);
                    }
                }
            }
            if (bamboo.get(i).contains(550, 550)) {
                bamboo.remove(i);
            }
            bamboo.get(i).draw(canvas);
        }

        y+=dY;
        if(y>0) { y %= getHeight(); }
        else { y = getHeight(); }

        deg+=(dDeg*dir);
        if(deg > 120) { dir=-1; }
        else if(deg < 30) { dir=1; }

        invalidate();
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }


}
