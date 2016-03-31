package com.lardis.ivan.testcustomview.myGroopView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.lardis.ivan.testcustomview.myGroopAbsoluteLayout;

/**
 * TODO: document your custom view class.
 */
public class MyZoomView extends View {
    private float x = 0;
    private float y = 0;


    public void show() {
        this.show = true;
        invalidate();
    }

    public void hide() {
        this.show = false;
        invalidate();
    }

    private boolean show = false;
    private Bitmap bitmap;
    private Path circle;

    int canvasWight;
    int canvasHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasHeight=getHeight();
        canvasWight=getWidth();
    }

    Paint mPaintCircle;
    Paint mPaintMarker;

    public void setData(float x, float y, Bitmap bitmap) {
        this.x = x;
        this.y = y;

        this.bitmap = bitmap;
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


        circle = new Path();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setStrokeWidth(5);
        mPaintMarker = new Paint();
        mPaintMarker.setColor(Color.WHITE);
        mPaintMarker.setStyle(Paint.Style.STROKE);
        mPaintMarker.setAntiAlias(true);
        mPaintMarker.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//canvas.drawRect(getWidth()/3,getHeight()/3,getWidth()*2/3,getHeight()*2/3,new Paint());
        if (show) {
            circle.reset();
            circle.addCircle(x, y, myGroopAbsoluteLayout.MY_ZOOM_VIEW_RADIUS / 2, Path.Direction.CW);

            canvas.scale(2, 2, x, y);
            canvas.clipPath(circle, Region.Op.REPLACE);
            canvas.drawBitmap(bitmap, 0, 0, mPaintCircle);
            canvas.clipRect(0, 0, canvasWight, canvasHeight, Region.Op.REPLACE);
            canvas.drawPath(circle, mPaintCircle);

            canvas.drawLine(x, y - 10, x, y + 10, mPaintMarker);
            canvas.drawLine(x - 10, y, x + 10, y, mPaintMarker);
        }

    }


}
