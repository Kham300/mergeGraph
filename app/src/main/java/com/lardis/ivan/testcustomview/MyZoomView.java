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
import android.view.View;
import android.view.Window;

/**
 * TODO: document your custom view class.
 */
public class MyZoomView extends View {
   private float x=0;
    private float y=0;
    private boolean show=false;
private Bitmap bitmap;
private Path circle;



    Paint mPaintCircle;

    public void setData(float x,float y,boolean show,Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.show = show;
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

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        circle=new Path();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaintCircle =new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Bitmap bitmap=Bitmap.createBitmap(21.21);
//    canvas.setBitmap(bitmap);
//  canvas.drawBitmap(bitmap,getWidth()/2,getHeight()/2,mPaintCircle);

        if(show) {
            circle.reset();
            circle.addCircle(x, y, getWidth() / 12, Path.Direction.CW);
//            canvas.drawCircle(x, y, getWidth() / 6, mPaintCircle);
//            canvas.drawRect(x-getWidth()/3,y-getHeight()/3,x+getWidth()/3,y+getHeight()/3,new Paint());

            Log.d("Mylog","x="+x+"y="+y);
//            matrix.
//            bitmap=B
            canvas.scale(2, 2, x, y);
            canvas.clipPath(circle, Region.Op.REPLACE);
            canvas.drawBitmap(bitmap,0,0,mPaintCircle);
            canvas.drawPath(circle, mPaintCircle);


        }

    }




}
