package com.lardis.ivan.testcustomview.myGroopView.MyInfoView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.helper.ViewHelper;

/**
 *view заднего фона для блока информации
 */
public class MyViewBackgroundInfo extends View {
    /**
     *  маркер для треугольника
     */
   Paint mPaintTriangle;
    /**
     * маркер для заднего фона блока
     */
   Paint mPaintBackground;

    /**
     * размеры холста
     */
    int canvasWight,canvasHeight;
    /**
     * путь треугольника
     */
Path pathTriangle;
    /**
     * прямоугольник для отрисовки фона
     */
RectF rectF;
    /**
     * отступ от края
     */
    int padding;
    /**
     * радиус закругления блока
     */
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
    mPaintTriangle =new Paint();

    mPaintTriangle.setAntiAlias(true);
    mPaintTriangle.setStyle(Paint.Style.FILL);
    mPaintBackground =new Paint();

    mPaintTriangle.setColor(getResources().getColor(R.color.myViewBackgroundInfo));
    mPaintBackground.setColor(getResources().getColor(R.color.myViewBackgroundInfo));
    mPaintBackground.setAntiAlias(true);
      padding= ViewHelper.convertDpToPixel(7, getContext());
    round=ViewHelper.convertDpToPixel(7, getContext());
pathTriangle =new Path();
    mPaintBackground.setShadowLayer(5.0f, 0.0f, 0.0f, getResources().getColor(R.color.selectedColumnShadowLayerColorExample));

}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasHeight=getHeight();
        canvasWight=getWidth();
    }
    /**
     * переместить треугольник слева относительно блока
     */
public void locationTriangleLeft()

{pathTriangle.reset();
    pathTriangle.moveTo(0, canvasHeight / 2);
    pathTriangle.lineTo(padding, canvasHeight / 2 + padding);
    pathTriangle.lineTo(padding, canvasHeight / 2 - padding);

}
    /**
     * переместить треугольник справа относительно блока
     */
    public void locationTriangleRight()

    {pathTriangle.reset();
        pathTriangle.moveTo(canvasWight, canvasHeight / 2);
        pathTriangle.lineTo(canvasWight - padding, canvasHeight / 2 + padding);
        pathTriangle.lineTo(canvasWight - padding, canvasHeight / 2 - padding);

    }

    /**
     * переместить треугольник снизу относительно блока
     */
    public void locationTriangleBottom()

    {
        pathTriangle.reset();
        pathTriangle.moveTo(canvasWight / 2, canvasHeight);
        pathTriangle.lineTo(canvasWight / 2 - padding, canvasHeight - padding);
        pathTriangle.lineTo(canvasWight / 2 + padding, canvasHeight - padding);
    }

    /**
     * переместить треугольник сверху относительно блока
     */
    public void locationTriangleTop()

    {
        pathTriangle.reset();
        pathTriangle.moveTo(canvasWight / 2, 0);
        pathTriangle.lineTo(canvasWight / 2 - padding, 0 + padding);
        pathTriangle.lineTo(canvasWight / 2 + padding, 0 + padding);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        rectF = new RectF(padding,padding,getWidth() - padding, getHeight() - padding);

        canvas.drawRoundRect(rectF, round, round, mPaintBackground);

canvas.drawPath(pathTriangle, mPaintTriangle);



    }
}
