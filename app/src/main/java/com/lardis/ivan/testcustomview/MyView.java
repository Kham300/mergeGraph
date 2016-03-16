package com.lardis.ivan.testcustomview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Timer;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    final float MINHEIGHTBLOCK = 50;
    ValueAnimator animator;
    Paint shadowPaint = new Paint();
    Paint mPaint = new Paint();
    float minX = 0;
    float maxX;
    Paint mPaintTriangle = new Paint();
    Paint mPaintPunctGtafica = new Paint();
    Paint mPaintBorder = new Paint();
    float offsetX;
    Paint mPaintSelectedColumn = new Paint();
    Paint textPaint = new Paint();
    //номер выделеной
    int npointTouch = -1;
    //толщина блока минимуи
    float minWidthBlock = 100;
    float widthBorder = 35;
    //скролим или влазеет
    boolean isScroll;
    ArrayList<String> arrayListStolbName = new ArrayList<String>();
    ArrayList<Integer> arrayListStolbValue = new ArrayList<Integer>();
    ArrayList<Integer> arrayListStolbValuesilka = new ArrayList<Integer>();

    //максимальное значение столбцов
    private static float maxValueData = 0;
    private static float maxValueDatasilka;
    private static float maxValueDatabuf;

    Timer timer;
    float leftBlok;
    //колво столбцов
    static int nArrayListSize;
    String name = "";
    int version = 0;

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


    public void init() {


        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        mPaintTriangle.setColor(getResources().getColor(R.color.strel1));

        mPaintPunctGtafica.setColor(getResources().getColor(R.color.strel1));
        mPaintPunctGtafica.setAntiAlias(true);


        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintSelectedColumn.setShadowLayer(8.0f, 0.0f, 0.0f, Color.BLACK);


        mPaintBorder.setStrokeWidth(10);
        mPaintBorder.setColor(getResources().getColor(R.color.border));


        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);

        animator = ValueAnimator.ofFloat(0, 1);
//            animator.setDuration(SyncStateContract.Constants.ANIM_DURATION);
        animator.setInterpolator(new AccelerateInterpolator());
    }

    public void setArrayList(String name, int version1, ArrayList<String> arrayListStolbNamesilka, ArrayList<Integer> arrayListStolbValuesilk) {
        this.arrayListStolbValuesilka = arrayListStolbValuesilk;
        if (arrayListStolbNamesilka != null) {
            this.arrayListStolbName = (ArrayList<String>) arrayListStolbNamesilka.clone();
            nArrayListSize = arrayListStolbValuesilka.size();
        }

        if (this.arrayListStolbName.size() == arrayListStolbValuesilk.size()) {

            animator.cancel();
            this.name = name;
            version = version1;
            offsetX = 0;
            npointTouch = -1;


            maxValueData = 0;
            for (int i = 0; i < nArrayListSize; i++)

            {
                if (maxValueData < arrayListStolbValuesilka.get(i))
                    maxValueData = arrayListStolbValuesilka.get(i);


            }
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    float value = ((Float) (animation.getAnimatedValue()))
                            .floatValue();
//                   arrayListStolbValue.clear();
                    for (int i = 0; i < nArrayListSize; i++) {
//

                        arrayListStolbValue.add(i, (int) (value * arrayListStolbValuesilka.get(i)));
                    }

                    invalidate();

                }
            });

            animator.start();


        }
//

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Log.d("Mylog",maxValueData+"=maxValueData,"+arrayList.get(2).getI()+"arrayList.get(2).getI()");
        if (nArrayListSize != 0) {


            maxX = (nArrayListSize - getWidth() / minWidthBlock) * minWidthBlock + widthBorder * 2;
            isScroll = !(minWidthBlock < getWidth() / nArrayListSize);


            if (isScroll) {
                leftBlok = minWidthBlock;
            } else {

                leftBlok = (getWidth() - widthBorder * 2) / nArrayListSize;

            }
        }

        if (nArrayListSize != 0) {
            //border
            canvas.drawRect(getWidth() - widthBorder, 0, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, widthBorder, getHeight(), mPaintBorder);
            canvas.drawRect(0, getHeight() - widthBorder, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, getWidth(), widthBorder / 3, mPaintBorder);
            //border


            //start for

            for (int i = 0; i < nArrayListSize; i++) {


                {
                    if (i % 2 == 0) mPaint.setColor(getResources().getColor(R.color.block0));
                    if (i % 2 == 1) mPaint.setColor(getResources().getColor(R.color.block1));
                    canvas.drawRect(widthBorder + offsetX + leftBlok * i, widthBorder / 3, widthBorder + offsetX + leftBlok * (i + 1), getHeight() - widthBorder, mPaint);

                    String text = arrayListStolbName.get(i);
                    //                textPaint.getTextBounds(text, 0, text.length(), new Rect());

                    //
                    canvas.drawText(text, widthBorder + offsetX + leftBlok * (i + 1.0f / 2.0f), getHeight() - widthBorder * 2 / 3, textPaint);
                    //

                    mPaintPunctGtafica.setShader(new LinearGradient(0, 0, 0, getHeight(), getResources().getColor(R.color.vstrel1), getResources().getColor(R.color.strel1), Shader.TileMode.MIRROR));

                    canvas.drawPath(getPathtopRoundRect(widthBorder + offsetX + leftBlok * i + widthBorder / 2, getHeight() - widthBorder * 1.5f - (getHeight() - widthBorder * 4) / maxValueData * arrayListStolbValue.get(i), widthBorder + offsetX + leftBlok * (i + 1) - widthBorder / 2, getHeight() - widthBorder, 10), mPaintPunctGtafica);


                }


            }
//end for!
// дорисовка выделенной

            float leftrect = widthBorder + offsetX + leftBlok * npointTouch;
            float rightrect = widthBorder + offsetX + leftBlok * (npointTouch + 1);

            if (!(rightrect < widthBorder && leftrect > getWidth() - widthBorder)) {


                mPaintSelectedColumn.setAntiAlias(true);
                if (npointTouch % 2 == 0)
                    mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocksvet));
                if (npointTouch % 2 == 1)
                    mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocktem));
                canvas.drawRect(leftrect, widthBorder / 3, rightrect, getHeight() - widthBorder, mPaintSelectedColumn);


                if (npointTouch != -1) {
 
                    canvas.drawPath(getPathtopRoundRect(widthBorder + offsetX + leftBlok * npointTouch + widthBorder / 2,
                            getHeight() - widthBorder * 1.5f - (getHeight() - widthBorder * 4) / maxValueData * arrayListStolbValue.get(npointTouch),
                            widthBorder + offsetX + leftBlok * (npointTouch + 1) - widthBorder / 2, getHeight() - widthBorder, 10), mPaintPunctGtafica);

                }

            }
            // end дорисовка выделенной
            // start треугольник снизу
            if (leftrect < widthBorder) leftrect = widthBorder;
            if (rightrect > getWidth() - widthBorder) rightrect = getWidth() - widthBorder;

            if ((rightrect - leftrect) > 15) {
                Path mPath = new Path();


                float lr = (rightrect - leftrect) / 3;
                mPath.moveTo(leftrect + lr, getHeight() - widthBorder / 3);
                mPath.lineTo(rightrect - lr, getHeight() - widthBorder / 3);
                mPath.lineTo((leftrect + rightrect) / 2, getHeight() - widthBorder * 2 / 3);

                canvas.drawPath(mPath, mPaintTriangle);
            }
//end треугольник снизу

            //start border боковые
            mPaint.setColor(getResources().getColor(R.color.border));
            canvas.drawRect(getWidth() - widthBorder, 0, getWidth(), getHeight(), mPaint);
            canvas.drawRect(0, 0, widthBorder, getHeight(), mPaint);


//end border боковые
//start line тени для border
            shadowPaint.setAntiAlias(true);
            shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));

            shadowPaint.setStrokeWidth(1.0f);
            shadowPaint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(0, getHeight() - widthBorder, getWidth(), getHeight() - widthBorder, shadowPaint);
            canvas.drawLine(0, widthBorder / 3, getWidth(), widthBorder / 3, shadowPaint);
            canvas.drawLine(0, 0, 0, getHeight(), shadowPaint);
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), shadowPaint);
            shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));
            canvas.drawLine(widthBorder, widthBorder / 3, widthBorder, getHeight() - widthBorder, shadowPaint);
            canvas.drawLine(getWidth() - widthBorder, widthBorder / 3, getWidth() - widthBorder, getHeight() - widthBorder, shadowPaint);

            //end line тени для border

            //start стрелки для скрола
            if (isScroll) {
                Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strelka);
                mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (minWidthBlock / 3), (int) (minWidthBlock / 3), false);

                canvas.drawBitmap(mBitmap, 0, getHeight() - minWidthBlock, mPaint);
                Matrix matrix = new Matrix();
                matrix.postRotate(180);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
                canvas.drawBitmap(mBitmap, getWidth() - minWidthBlock / 3, getHeight() - minWidthBlock, mPaint);

            }



        //        дорисовка лининий пунктира
////        maxValueData
        Paint mpa = new Paint();
        mpa.setAntiAlias(true);
        mpa.setColor(Color.BLACK);
        mpa.setStrokeWidth(2);
//
        float[] intervals = new float[]{2.0f, 8.0f};
        float phase = 0;
//
//
        mpa.setPathEffect(new DashPathEffect(intervals, phase));
//
////       1.5f*widthBorder;
        float buf = getHeight()- widthBorder * 1.5f;
        float my = 0;
        my =   (buf / MINHEIGHTBLOCK);
int step=getStepWeidht(my,maxValueData,1);


        for (int k = 1; k <= my ; k++)
        {

 float y=          getHeight() - widthBorder * 1.5f - (getHeight() - widthBorder * 4) / maxValueData * step*k;
            canvas.drawLine(0,y

                    , getRight(), y, mpa);

            canvas.drawText(step*k+"",widthBorder/2,y-2,textPaint);

        }
        }
        //        дорисовка лининий пунктира
    }

    @NonNull
    private Path getPathtopRoundRect(float left, float top, float right, float bottom, float radius) {
        Path path = new Path();


        path.moveTo(right, bottom);
        path.lineTo(left, bottom);

        path.arcTo(new RectF(left, top, left + radius * 2, top + radius * 2), 180, 90);

        path.arcTo(new RectF(right - radius * 2, top, right, top + radius * 2), 270, 90);

        path.close();
        return path;
    }

    float bufX;
    float bufmy = 0;


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub

        switch (motionEvent.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                bufX = offsetX;

                bufmy = motionEvent.getX();
                break;
            case (MotionEvent.ACTION_MOVE):
                offsetX = bufX - (bufmy - motionEvent.getX());


                break;
            case (MotionEvent.ACTION_UP):
                if (Math.abs(bufX - offsetX) < 4) {
                    if (isScroll)
                        npointTouch = (int) ((motionEvent.getX() - bufX - widthBorder) / minWidthBlock);
                    else
                        npointTouch = (int) ((motionEvent.getX() - widthBorder - bufX) * nArrayListSize / (getWidth() - widthBorder * 2));

//                    (widthBorder + offsetX + (getWidth() - widthBorder * 2) * i / nArrayListSize, 0, widthBorder + offsetX + (getWidth() - widthBorder * 2) * (i + 1) / nArrayListSize, getHeight(), mPaint);

                }
                offsetX = bufX - (bufmy - motionEvent.getX());

                break;

        }
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;
        invalidate();


        return true;
    }

    int getStepWeidht(float nElement, float maxValueData, int step) {
        if (  (maxValueData / (step * nElement)) > 5)
            step = getStepWeidht(nElement, maxValueData, step * 10);
        else if (  (maxValueData / (step * nElement)) > 2)
            step = getStepWeidht(nElement, maxValueData, step * 5);

        else if (  (maxValueData / (step * nElement)) > 1)
            step = getStepWeidht(nElement, maxValueData, step * 2);


        return step;

    }

}
