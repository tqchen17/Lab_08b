package com.example.lab08;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Bamboo extends RectF {
    private float startX, startY, endX, endY, dx, dy;
    private int angle, color;

    public Bamboo(float startX, float startY, float endX, float endY, int rotate, float dx, float dy, int color) {
        super(startX, startY, endX, endY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.angle = rotate;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public Bamboo(float startX, float startY, int rotate, float dx, float dy) {
        this(startX, startY, startX+75, startY+750, rotate, dx ,dy, Color.argb(200, 88, 140, 39));
    }

    public Bamboo(int rotate, int dx, int dy) {
        this(100, 100, 200, 500, rotate, dx, dy, Color.argb(200, 88, 140, 39));
    }

    public Bamboo() {
        this(30, 1, 1);
    }

    public void update(int width, int height) {
        offset(dx, dy);
        startX += dx;
        endX += dx;
        startY += dy;
        endY += dy;
        if(startX<0 || endX>width) { flipSpeed(true, false); }
        if(startY<0 || endY>height) { flipSpeed(false, true); }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.save();
        canvas.rotate(angle,(startX+endX)/2,(startY+endY)/2);
        Bamboo bamboo = new Bamboo(startX, startY, endX, endY, angle, dx, dy, color);
        Bamboo bambooCopy = new Bamboo(startX-15, startY-15, endX+15, endY+15, angle, dx, dy, Color.argb(255, 55, 102, 11));
        paint.setColor(Color.argb(255, 55, 102, 11));
        canvas.drawRect(bambooCopy, paint);
        paint.setColor(color);
        canvas.drawRect(bamboo, paint);
        canvas.restore();
    }

    public void flipSpeed(boolean a, boolean b) {
        if(a) { dx *= -1; }
        if(b) { dy *= -1; }
    }

    public boolean contains (float x, float y) {
        return super.contains(x, y);
    }
}
