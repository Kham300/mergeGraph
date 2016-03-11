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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    Paint shadowPaint = new Paint();
    Paint mPaint = new Paint();
    float minX = 0;
    float maxX;
    int npointTouch = -1;
    float minWidthBlock;
    float minWidthBorder;
    float nHideMonitor = 5;
    boolean isScroll;
    float leftBlok;

    static class Myclass {
        float i;
        String name;

        public float getI() {
            return i;
        }

        public Myclass(String name, int i) {
            this.i = i;
            this.name = name;
        }
    }

    static ArrayList<Myclass> arrayList = new ArrayList<Myclass>();
    Map<String, Integer> fg = new TreeMap<>();

    private static float maxvalueDan;

    static {
        arrayList.add(new Myclass("Января", 200));
        arrayList.add(new Myclass("Февраль ", 565));
        arrayList.add(new Myclass(" Март", 745));
        arrayList.add(new Myclass(" Апрель", 24));
        arrayList.add(new Myclass(" Май", 74));
        arrayList.add(new Myclass("Июнь", 374));
        arrayList.add(new Myclass("Июль ", 625));
        arrayList.add(new Myclass("Август", 365));
        arrayList.add(new Myclass("Сентябрь", 347));
        arrayList.add(new Myclass("Октябрь", 345));
        arrayList.add(new Myclass("Ноябрь", 353));
        arrayList.add(new Myclass("Декабрь", 746));
        for (int i = 0; i < arrayList.size(); i++)
            if (arrayList.get(i).getI() > maxvalueDan) maxvalueDan = arrayList.get(i).getI();


    }


//    public void setN(int n) {
//        this.n = n;
//        this.x = 0;
//    }


    int n = arrayList.size();

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

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {


        //border
        mPaint.setColor(getResources().getColor(R.color.border));
        canvas.drawRect(getWidth() - minWidthBorder, 0, getWidth(), getHeight(), mPaint);
        canvas.drawRect(0, 0, minWidthBorder, getHeight(), mPaint);
        canvas.drawRect(0, getHeight() - minWidthBorder, getWidth(), getHeight(), mPaint);
        canvas.drawRect(0, 0, getWidth(), minWidthBorder / 3, mPaint);
        //border


        //start for

        for (int i = 0; i < n; i++) {


            {
                if (i % 2 == 0) mPaint.setColor(getResources().getColor(R.color.block));
                if (i % 2 == 1) mPaint.setColor(getResources().getColor(R.color.border));
                canvas.drawRect(minWidthBorder + x + leftBlok * i, minWidthBorder / 3, minWidthBorder + x + leftBlok * (i + 1), getHeight() - minWidthBorder, mPaint);
                Paint textPaint = new Paint();
                textPaint.setAntiAlias(true);
                textPaint.setColor(Color.BLACK);
                String text = arrayList.get(i).name;
//                textPaint.getTextBounds(text, 0, text.length(), new Rect());
                textPaint.setTextAlign(Paint.Align.CENTER);
//
                canvas.drawText(text, minWidthBorder + x + leftBlok * (i + 1.0f / 2.0f), getHeight() - minWidthBorder * 2 / 3, textPaint);
//


                mPaint.setColor(getResources().getColor(R.color.strel1));


                canvas.drawRect(minWidthBorder + x + leftBlok * i + minWidthBorder / 2, (getHeight() - minWidthBorder * 3) / maxvalueDan * arrayList.get(i).getI(), minWidthBorder + x + leftBlok * (i + 1) - minWidthBorder / 2, getHeight() - minWidthBorder, mPaint);

            }


        }
//end for!
//


//border

// дорисовка выделенной
        mPaint.setColor(Color.YELLOW);
        float leftrect = minWidthBorder + x + leftBlok * npointTouch;
        float rightrect = minWidthBorder + x + leftBlok * (npointTouch + 1);

        if (!(rightrect < minWidthBorder && leftrect > getWidth() - minWidthBorder)) {


            Paint shadowPaint1 = new Paint();
            shadowPaint1.setAntiAlias(true);
            shadowPaint1.setColor(getResources().getColor(R.color.vblock));
            shadowPaint1.setTextSize(35.0f);
            shadowPaint1.setStrokeWidth(2.0f);
            shadowPaint1.setStyle(Paint.Style.FILL_AND_STROKE);
            shadowPaint1.setShadowLayer(8.0f, 0.0f, 0.0f, Color.BLACK);
            canvas.drawRect(leftrect, minWidthBorder / 3, rightrect, getHeight() - minWidthBorder, shadowPaint1);

            mPaint.setColor(getResources().getColor(R.color.vstrel1));
            if (npointTouch != -1)
                canvas.drawRect(minWidthBorder + x + leftBlok * npointTouch + minWidthBorder / 2, (getHeight() - minWidthBorder * 3) / maxvalueDan * arrayList.get(npointTouch).getI(), minWidthBorder + x + leftBlok * (npointTouch + 1) - minWidthBorder / 2, getHeight() - minWidthBorder, mPaint);


        }
        // треугольник снизу
        if (leftrect < minWidthBorder) leftrect = minWidthBorder;
        if (rightrect > getWidth() - minWidthBorder) rightrect = getWidth() - minWidthBorder;

        if ((rightrect - leftrect) > 15) {
            Path mPath = new Path();
            mPaint.setColor(getResources().getColor(R.color.strel1));


            float lr = (rightrect - leftrect) / 3;
            mPath.moveTo(leftrect + lr, getHeight() - minWidthBorder / 3);
            mPath.lineTo(rightrect - lr, getHeight() - minWidthBorder / 3);
            mPath.lineTo((leftrect + rightrect) / 2, getHeight() - minWidthBorder * 2 / 3);

            canvas.drawPath(mPath, mPaint);
        }
//треугольник снизу

        //border боковые
        mPaint.setColor(getResources().getColor(R.color.border));
        canvas.drawRect(getWidth() - minWidthBorder, 0, getWidth(), getHeight(), mPaint);
        canvas.drawRect(0, 0, minWidthBorder, getHeight(), mPaint);

//line

        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));

        shadowPaint.setStrokeWidth(1.0f);
        shadowPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, getHeight() - minWidthBorder, getWidth(), getHeight() - minWidthBorder, shadowPaint);
        canvas.drawLine(0, minWidthBorder / 3, getWidth(), minWidthBorder / 3, shadowPaint);
        canvas.drawLine(0, 0, 0, getHeight(), shadowPaint);
        canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), shadowPaint);
        shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));
        canvas.drawLine(minWidthBorder, minWidthBorder / 3, minWidthBorder, getHeight() - minWidthBorder, shadowPaint);
        canvas.drawLine(getWidth() - minWidthBorder, minWidthBorder / 3, getWidth() - minWidthBorder, getHeight() - minWidthBorder, shadowPaint);

        //border боковые

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
