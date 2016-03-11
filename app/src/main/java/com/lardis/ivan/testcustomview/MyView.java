package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    Paint mPaint = new Paint();
    float minX = 0;
    float maxX;
    int npointTouch = -1;
    float minWidthBlock;
    float minWidthBorder;
    float nHideMonitor = 5;
    boolean isScroll;
    float leftBlok;

    public void setN(int n) {
        this.n = n;
        this.x = 0;
    }


    int n = 5;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    public void init() {


    }

    @Override
    protected void onDraw(Canvas canvas) {


        minWidthBlock = getWidth() / nHideMonitor;
        minWidthBorder = minWidthBlock / 3;
        mPaint.setStrokeWidth(10);
        maxX = (n - nHideMonitor) * minWidthBlock + minWidthBorder * 2;
        isScroll = !(minWidthBlock < getWidth() / n);


        if (isScroll) {
            leftBlok = minWidthBlock;
        } else {

            leftBlok = (getWidth() - minWidthBorder * 2) / n;

        }
        for (int i = 0; i < n; i++) {


            {
                if (i % 2 == 0) mPaint.setColor(Color.BLUE);
                if (i % 2 == 1) mPaint.setColor(Color.GREEN);
                canvas.drawRect(minWidthBorder + x + leftBlok * i, 0, minWidthBorder + x + leftBlok * (i + 1), getHeight(), mPaint);

            }


        }


        //border
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(getWidth() - minWidthBorder, 0, getWidth(), getHeight(), mPaint);
        canvas.drawRect(0, 0, minWidthBorder, getHeight(), mPaint);
        canvas.drawRect(0, getHeight() - minWidthBorder, getWidth(), getHeight(), mPaint);
//border

// дорисовка выделенной
        mPaint.setColor(Color.YELLOW);
        float leftrect = minWidthBorder + x + leftBlok * npointTouch;
        float rightrect = minWidthBorder + x + leftBlok * (npointTouch + 1);

        if (!(rightrect < minWidthBorder && leftrect > getWidth() - minWidthBorder)) {

            if (leftrect < minWidthBorder) leftrect = minWidthBorder;
            if (rightrect > getWidth() - minWidthBorder) rightrect = getWidth() - minWidthBorder;
            canvas.drawRect(leftrect, 0, rightrect, getHeight() - minWidthBorder, mPaint);
        }
        Log.d("Mylog","rightrect="+rightrect+"leftrect="+leftrect);
        if((rightrect-leftrect)>15)
        {
            Path mPath = new Path();
            mPaint.setColor(Color.BLUE);

            mPath.moveTo(leftrect, getHeight());
            mPath.lineTo(rightrect, getHeight());
            mPath.lineTo((leftrect + rightrect) / 2, getHeight() - minWidthBorder);
//canvas.
            canvas.drawPath(mPath, mPaint);
        }
//


        //стрелки для скрола
        if (isScroll) {
            Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strelka);
            mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (minWidthBlock / 3), (int) (minWidthBlock / 3), false);

            canvas.drawBitmap(mBitmap, 0, getHeight() - minWidthBlock, mPaint);
            Matrix matrix = new Matrix();
            matrix.postRotate(180);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
            canvas.drawBitmap(mBitmap, getWidth() - minWidthBlock / 3, getHeight() - minWidthBlock, mPaint);

        }

        //стрелки для скрола

    }


    float x;
    float bufX;
    float bufmy = 0;

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub


        switch (motionEvent.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                bufX = x;

                bufmy = motionEvent.getX();
                Log.d("Mylog", "bufmy" + bufmy);
                break;
            case (MotionEvent.ACTION_MOVE):
                x = bufX - (bufmy - motionEvent.getX());


                Log.d("Mylog", "bufmy" + bufmy);
                break;
            case (MotionEvent.ACTION_UP):
                if (Math.abs(bufX - x) < 4) {
                    if (isScroll)
                        npointTouch = (int) ((motionEvent.getX() - bufX - minWidthBorder) / minWidthBlock);
                    else
                        npointTouch = (int) ((motionEvent.getX() - minWidthBorder - bufX) * n / (getWidth() - minWidthBorder * 2));

//                    (minWidthBorder + x + (getWidth() - minWidthBorder * 2) * i / n, 0, minWidthBorder + x + (getWidth() - minWidthBorder * 2) * (i + 1) / n, getHeight(), mPaint);

                }
                x = bufX - (bufmy - motionEvent.getX());

                break;

        }
        if (x < -maxX) x = -maxX;
        if (x > minX) x = minX;
        invalidate();


        return true;
    }


}
