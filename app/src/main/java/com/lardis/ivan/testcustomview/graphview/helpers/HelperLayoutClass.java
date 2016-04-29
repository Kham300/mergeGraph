package com.lardis.ivan.testcustomview.graphview.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by aleksey.ivanov on 21.03.2016.
 */
public class HelperLayoutClass {
    public static float dpToPixels(Resources r, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    public static float pixelsToDp(Context ctx, float px) {
        WindowManager wm = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        return (float) Math.ceil(px * logicalDensity);
    }

    public static float setTextSizeForWidth(Paint paint, float desiredWidth,
                                            String text) {

        final float testTextSize = 100;
        paint.setTextSize(testTextSize);
        final float testWidth = paint.measureText(text);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / testWidth;

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
        return desiredTextSize;
    }

    public static void calculateOKTextSize(Paint paint, float stripeWidth, String[] text, float maxAllowedTextSize) {
        float minTextSize = 1000;
        for (int i = 0; i < text.length; ++i) {
            float currSize = setTextSizeForWidth(paint, stripeWidth, text[i]);
            if (currSize < minTextSize)
                minTextSize = currSize;
        }

        if (minTextSize > maxAllowedTextSize)
            paint.setTextSize(maxAllowedTextSize);
        else
            paint.setTextSize(minTextSize);
    }

    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics.widthPixels;
    }

    public static float getRateDpToPixel( Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float rateDpToPixel =    ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return rateDpToPixel;
    }

    public static float getTextHeight(Paint paint)
    {
        Rect rectSizeText = new Rect();
        paint.getTextBounds("s", 0, "s".length(), rectSizeText);

        return paint.measureText("s");

    }
}
