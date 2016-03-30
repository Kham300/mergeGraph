package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by i.larin on 30.03.2016.
 */
public class MyViewGroop extends ViewGroup {
    public MyViewGroop(Context context) {
        super(context);
    }

    public MyViewGroop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("Mylog","onLayout ok");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Mylog","ondraw ok");
        canvas.drawRect(getWidth()/3,getHeight()/3,getWidth()*2/3,getHeight()*2/3,new Paint());
    }
}
