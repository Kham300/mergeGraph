package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * TODO: document your custom view class.
 */
public class MyZoomView extends View  {
   private float x=0;
    private float y=0;



    public void  show() {
        this.show = true;
        invalidate();
    }
    public void  hide( ) {
        this.show = false;
        invalidate();
    }
    private boolean show=false;
private Bitmap bitmap;
private Path circle;



    Paint mPaintCircle;
    Paint mPaintMarker;

    public void setData(float x,float y,Bitmap bitmap) {
        this.x = x;
        this.y = y;

        this.bitmap=bitmap;
         invalidate();
    }



    public MyZoomView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyZoomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes



        circle=new Path();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaintCircle =new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setStrokeWidth(10);
        mPaintMarker =new Paint();
        mPaintMarker.setColor(Color.WHITE);
        mPaintMarker.setStyle(Paint.Style.STROKE);
        mPaintMarker.setAntiAlias(true);
        mPaintMarker.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//canvas.drawRect(getWidth()/3,getHeight()/3,getWidth()*2/3,getHeight()*2/3,new Paint());
        if(show) {
            circle.reset();
            circle.addCircle(x, y, getWidth() / 12, Path.Direction.CW);

            canvas.scale(2, 2, x, y);
            canvas.clipPath(circle, Region.Op.REPLACE);
            canvas.drawBitmap(bitmap, 0, 0, mPaintCircle);
            canvas.drawPath(circle, mPaintCircle);

canvas.drawLine(x, y - 10, x, y + 10, mPaintMarker);
canvas.drawLine(x-10,y,x+10,y,mPaintMarker);
        }

    }




}
