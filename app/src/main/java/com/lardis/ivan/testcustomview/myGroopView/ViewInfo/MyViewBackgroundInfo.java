package com.lardis.ivan.testcustomview.myGroopView.ViewInfo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    int canvasWight;
    int canvasHeight;
Path pathTriangle;
    Paint mPaintBackground2;
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
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    mPaintBackground=new Paint();

    mPaintBackground.setAntiAlias(true);
    mPaintBackground.setStyle(Paint.Style.FILL);
    mPaintBackground1=new Paint();

    mPaintBackground.setColor(getResources().getColor(R.color.myViewBackgroundInfo));
    mPaintBackground1.setColor(getResources().getColor(R.color.myViewBackgroundInfo));
    mPaintBackground1.setAntiAlias(true);
      padding= ViewHelper.convertDpToPixel(7, getContext());
    round=ViewHelper.convertDpToPixel(7, getContext());
pathTriangle =new Path();
    mPaintBackground1.setShadowLayer(5.0f, 0.0f, 0.0f, getResources().getColor(R.color.selectedColumnShadowLayerColorExample));

}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasHeight=getHeight();
        canvasWight=getWidth();
    }
public void setRightTringulePath()

{pathTriangle.reset();
    pathTriangle.moveTo(0, canvasHeight / 2);
    pathTriangle.lineTo(padding, canvasHeight / 2 + padding);
    pathTriangle.lineTo(padding, canvasHeight / 2 - padding);

}

    public void setLeftTringulePath()

    {pathTriangle.reset();
        pathTriangle.moveTo(canvasWight, canvasHeight / 2);
        pathTriangle.lineTo(canvasWight - padding, canvasHeight / 2 + padding);
        pathTriangle.lineTo(canvasWight - padding, canvasHeight / 2 - padding);

    }


    public void setTopTringulePath()

    {
        pathTriangle.reset();
        pathTriangle.moveTo(canvasWight / 2, canvasHeight);
        pathTriangle.lineTo(canvasWight / 2 - padding, canvasHeight - padding);
        pathTriangle.lineTo(canvasWight / 2 + padding, canvasHeight - padding);
    }
    public void setBattomTringulePath()

    {
        pathTriangle.reset();
        pathTriangle.moveTo(canvasWight / 2, 0);
        pathTriangle.lineTo(canvasWight / 2 - padding, 0 + padding);
        pathTriangle.lineTo(canvasWight / 2 + padding, 0 + padding);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        rectF = new RectF(padding,padding,getWidth() - padding, getHeight() - padding);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaintBackground);
        canvas.drawRoundRect(rectF, round, round, mPaintBackground1);

canvas.drawPath(pathTriangle, mPaintBackground);



    }
}
