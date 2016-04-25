package com.lardis.ivan.testcustomview.graphview.base.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.lardis.ivan.testcustomview.graphview.helpers.HelperLayoutClass;

/**
 * Класс для лупы
 */
public class MyZoomView extends View {
    /**
     * координаты нахождения лупы
     */
    private float x = 0, y = 0;
    /**
     * показывать или нет Zoom
     */
    private boolean isShown = false;
    /**
     * скрин view графика
     */
    private Bitmap bitmapPrtScn;

    /**
     * путь для отрисовки лупы
     */
    private Path circle;

    private int canvasWight;
    private int canvasHeight;

    /**
     * кисть для обводки лупы
     */
    Paint mPaintCircle;

    /**
     * кисть для крестика в середине лупы
     */
    Paint mPaintMarker;

    /**
     * Радиус лупы
     */
    private int radius;


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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasHeight = getHeight();
        canvasWight = getWidth();
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void show() {
        this.isShown = true;
        invalidate();
    }

    public void hide() {
        this.isShown = false;
        invalidate();
    }

    /**
     * Даные для отрисовки лупы
     *
     * @param x      Координата распошложения
     * @param y      Координата распошложения
     */
    public void setData(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }

    /**
     * обновление принскрина
     *
     * @param bitmapPrtScn
     */
    public void updateBitmapPrtScn(Bitmap bitmapPrtScn) {
        this.bitmapPrtScn = bitmapPrtScn;
    }


    private void init(AttributeSet attrs, int defStyle) {
        radius = (int) HelperLayoutClass.dpToPixels(getResources(), 100);
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
        if (isShown) {
            circle.reset();
            circle.addCircle(x, y, radius / 2, Path.Direction.CW);
            canvas.scale(2, 2, x, y);
            canvas.clipPath(circle, Region.Op.REPLACE);
            if (bitmapPrtScn != null) canvas.drawBitmap(bitmapPrtScn, 0, 0, mPaintCircle);
            canvas.clipRect(0, 0, canvasWight, canvasHeight, Region.Op.REPLACE);
            canvas.drawPath(circle, mPaintCircle);
            canvas.drawLine(x, y - 10, x, y + 10, mPaintMarker);
            canvas.drawLine(x - 10, y, x + 10, y, mPaintMarker);
        }
    }

    @Override
    public boolean isShown() {
        return isShown;
    }
}
