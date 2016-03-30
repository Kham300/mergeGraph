package com.lardis.ivan.testcustomview.myGroopView.ViewInfo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.helper.ViewHelper;

/**
 * Created by i.larin on 30.03.2016.
 */
public class MyViewBackgroundInfo extends View {
   Paint mPaintBackground;
   Paint mPaintBackground1;
RectF rectF;
    int padding;
    int round;
    public MyViewBackgroundInfo(Context context) {
        super(context);
        init();
    }

    public MyViewBackgroundInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewBackgroundInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
void init()
{
    mPaintBackground=new Paint();
mPaintBackground.setColor(Color.RED);
    mPaintBackground.setAntiAlias(true);
    mPaintBackground1=new Paint();
    mPaintBackground1.setColor(getResources().getColor(R.color.myViewBackgroundInfo));
    mPaintBackground1.setAntiAlias(true);
      padding= ViewHelper.convertDpToPixel(5, getContext());
    round=ViewHelper.convertDpToPixel(7, getContext());
}
    @Override
    protected void onDraw(Canvas canvas) {
        rectF=new RectF(padding,padding,getWidth() - padding, getHeight() - padding);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaintBackground);
        canvas.drawRoundRect(rectF,round,round, mPaintBackground1);



    }
}
