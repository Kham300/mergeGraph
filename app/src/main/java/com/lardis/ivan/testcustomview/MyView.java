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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    //размеры экрана
    private final float MINHEIGHTBLOCK = 50;
    private float canvasWidht;
    private float canvasHeight;
    private float minX = 0;
    private float maxX;
    private int step;
    private Matrix matrixRotate;
    private ValueAnimator animator;
    private float workOblGrafik;
    private float nStep;
    Path mPath;
    float leftRectSelected;
    float rightRectSelected;
    private Paint mPaintLinePunctire;
    private Paint mPaintLineForBorder;
    private Paint mPaintLinePunctireop;
    private Paint mPaintLinePunctireaverageValueData;
    private Paint mPaint;
    private Paint mPaintTriangle;
    private Paint mPaintPunctGtafica;
    private Paint mPaintPunctGtaficav;
    private Paint mPaintBorder;
    private Paint mPaintSelectedColumn;
    private Paint textPaint;
    private Paint textPaintaverageValueData = new Paint();
    //смещение по x в данный момент
    private float offsetX;
    //номер выделеной
    private int npointTouch = -1;
    //толщина блока минимуи
    private float minWidthBlock = 100;
    private float widthBorder = 35;
    //скролим или влазеет
    private boolean isScroll;
    private ArrayList<String> arrayListStolbName = new ArrayList<String>();
    private ArrayList<Integer> arrayListStolbValue = new ArrayList<Integer>();
    private ArrayList<Integer> arrayListStolbValuesilka = new ArrayList<Integer>();
    //максимальное и среднее  значение столбцов
    private static float maxValueData = 0;
    private static float averageValueData = 0;

    private float leftBlok;
    //колво столбцов
    private int nArrayListSize;


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


        mPath = new Path();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = ((Float) (animation.getAnimatedValue()))
                        .floatValue();
                for (int i = 0; i < nArrayListSize; i++) {
                    arrayListStolbValue.add(i, (int) (value * arrayListStolbValuesilka.get(i)));
                }
                invalidate();

            }
        });

        mPaint = new Paint();

        matrixRotate = new Matrix();
        matrixRotate.postRotate(180);

        mPaintLineForBorder = new Paint();
        mPaintLineForBorder.setAntiAlias(true);
        mPaintLineForBorder.setColor(getContext().getResources().getColor(R.color.vblockborde));
        mPaintLineForBorder.setStrokeWidth(1.0f);
        mPaintLineForBorder.setStyle(Paint.Style.STROKE);

        mPaintLinePunctire = new Paint();
        mPaintLinePunctire.setAntiAlias(true);
        mPaintLinePunctire.setColor(getContext().getResources().getColor(R.color.mPaintLinePunctire));
        mPaintLinePunctire.setStrokeWidth(4);

        mPaintLinePunctireop = new Paint();
        mPaintLinePunctireop.setAntiAlias(true);
        mPaintLinePunctireop.setColor(getContext().getResources().getColor(R.color.mPaintLinePunctireop));
        mPaintLinePunctireop.setStrokeWidth(4);

        mPaintLinePunctireaverageValueData = new Paint();
        mPaintLinePunctireaverageValueData.setPathEffect(new DashPathEffect(new float[]{3.0f, 8.0f}, 0));
        mPaintLinePunctireaverageValueData.setAntiAlias(true);
        mPaintLinePunctireaverageValueData.setTextSize(100);
        mPaintLinePunctireaverageValueData.setColor(getContext().getResources().getColor(R.color.mPaintLinePunctireaverageValueData));
        mPaintLinePunctireaverageValueData.setStrokeWidth(5);


        mPaintTriangle = new Paint();
        mPaintTriangle.setColor(getResources().getColor(R.color.mPaintTriangle));

        mPaintPunctGtafica = new Paint();
        mPaintPunctGtafica.setColor(getResources().getColor(R.color.mPaintPunctGtafica));
        mPaintPunctGtafica.setAntiAlias(true);
        mPaintPunctGtafica.setShader(new LinearGradient(0, 0, 0, getHeight(), getResources().getColor(R.color.verxstrel1), getResources().getColor(R.color.nizstrel1), Shader.TileMode.MIRROR));

        mPaintPunctGtaficav = new Paint();
        mPaintPunctGtaficav.setColor(getResources().getColor(R.color.mPaintPunctGtafica));
        mPaintPunctGtaficav.setAntiAlias(true);
        mPaintPunctGtaficav.setShader(new LinearGradient(0, 0, 0, getHeight(), getResources().getColor(R.color.vverxstrel1), getResources().getColor(R.color.vnizstrel1), Shader.TileMode.MIRROR));

        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, getResources().getColor(R.color.mPaintSelectedColumnShadowLayer));

        mPaintBorder = new Paint();
        mPaintBorder.setStrokeWidth(10);
        mPaintBorder.setColor(getResources().getColor(R.color.border));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPaintaverageValueData.setAntiAlias(true);
        textPaintaverageValueData.setTextSize(18);
        textPaintaverageValueData.setColor(getContext().getResources().getColor(R.color.mPaintLinePunctireaverageValueData));
        textPaintaverageValueData.setTextAlign(Paint.Align.CENTER);


    }

    public void setArrayList(String name, int version1, ArrayList<String> arrayListStolbNamesilka, ArrayList<Integer> arrayListStolbValuesilk) {
        this.arrayListStolbValuesilka = arrayListStolbValuesilk;
        if (arrayListStolbNamesilka != null) {
            this.arrayListStolbName = (ArrayList<String>) arrayListStolbNamesilka.clone();
            nArrayListSize = arrayListStolbValuesilka.size();
            offsetX = 0;
        }

        if (this.arrayListStolbName.size() == arrayListStolbValuesilk.size()) {

            animator.cancel();
            npointTouch = -1;

//            находим максимальное и среднее значение
            maxValueData = 0;
            averageValueData = 0;
            for (int i = 0; i < nArrayListSize; i++)

            {
                if (maxValueData < arrayListStolbValuesilka.get(i))
                    maxValueData = arrayListStolbValuesilka.get(i);
                averageValueData = averageValueData + arrayListStolbValuesilka.get(i);

            }
            averageValueData = averageValueData / arrayListStolbValuesilka.size();

// границы скрола
            if (nArrayListSize != 0) {

                maxX = (nArrayListSize - canvasWidht / minWidthBlock) * minWidthBlock + widthBorder * 2;
                isScroll = !(minWidthBlock < canvasWidht / nArrayListSize);


                if (isScroll) {
                    leftBlok = minWidthBlock;
                } else {

                    leftBlok = (canvasWidht - widthBorder * 2) / nArrayListSize;

                }
            }


//            шаг пунктирных линий
            step = getStepWeidht(((canvasHeight - widthBorder * 1.5f) / MINHEIGHTBLOCK), maxValueData, 1);
            nStep = maxValueData / step;
            workOblGrafik = (canvasHeight - widthBorder * 1.33f - 20) / maxValueData;

            animator.start();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidht = getWidth();
        canvasHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (nArrayListSize != 0) {
            //border
            canvas.drawRect(getWidth() - widthBorder, 0, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, widthBorder, getHeight(), mPaintBorder);
            canvas.drawRect(0, getHeight() - widthBorder, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, getWidth(), widthBorder / 3, mPaintBorder);
            //border

            //прямоугольники текс снизу
            for (int i = 0; i < nArrayListSize; i++) {
                {
                    if (i % 2 == 0) mPaint.setColor(getResources().getColor(R.color.block0));
                    if (i % 2 == 1) mPaint.setColor(getResources().getColor(R.color.block1));
                    canvas.drawRect(widthBorder + offsetX + leftBlok * i, widthBorder / 3, widthBorder + offsetX + leftBlok * (i + 1), getHeight() - widthBorder, mPaint);
                    canvas.drawText(arrayListStolbName.get(i), widthBorder + offsetX + leftBlok * (i + 1.0f / 2.0f), getHeight() - widthBorder * 2 / 3, textPaint);
                }
            }
            //end прямоугольники текс снизу

            //края выделеного прямоугольника
            leftRectSelected = widthBorder + offsetX + leftBlok * npointTouch;
            rightRectSelected = widthBorder + offsetX + leftBlok * (npointTouch + 1);

            // start треугольник снизу и сверху выделеного блока
            if (leftRectSelected < widthBorder) leftRectSelected = widthBorder;
            if (rightRectSelected > getWidth() - widthBorder)
                rightRectSelected = getWidth() - widthBorder;
            if ((rightRectSelected - leftRectSelected) > 15) {
                float lr = (rightRectSelected - leftRectSelected) / 3;
                mPath.moveTo(leftRectSelected + lr, getHeight() - widthBorder / 3);
                mPath.lineTo(rightRectSelected - lr, getHeight() - widthBorder / 3);
                mPath.lineTo((leftRectSelected + rightRectSelected) / 2, getHeight() - widthBorder * 2 / 3);

                canvas.drawPath(mPath, mPaintTriangle);
                mPath.moveTo(leftRectSelected + lr, widthBorder / 3 + 3);
                mPath.lineTo(rightRectSelected - lr, widthBorder / 3 + 3);
                mPath.lineTo((leftRectSelected + rightRectSelected) / 2, widthBorder * 2 / 3 + 3);

                canvas.drawPath(mPath, mPaintTriangle);
            }
//end  треугольник снизу и сверху выделеного блока

            //start border боковые
            mPaint.setColor(getResources().getColor(R.color.border));
            canvas.drawRect(getWidth() - widthBorder, 0, getWidth(), getHeight(), mPaint);
            canvas.drawRect(0, 0, widthBorder, getHeight(), mPaint);
//end border боковые

//start line тени для border
            canvas.drawLine(0, getHeight() - widthBorder, getWidth(), getHeight() - widthBorder, mPaintLineForBorder);
            canvas.drawLine(0, widthBorder / 3, getWidth(), widthBorder / 3, mPaintLineForBorder);
            canvas.drawLine(0, 0, 0, getHeight(), mPaintLineForBorder);
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), mPaintLineForBorder);

            canvas.drawLine(widthBorder, widthBorder / 3, widthBorder, getHeight() - widthBorder, mPaintLineForBorder);
            canvas.drawLine(getWidth() - widthBorder, widthBorder / 3, getWidth() - widthBorder, getHeight() - widthBorder, mPaintLineForBorder);

            //end line тени для border

            //start стрелки для скрола
            if (isScroll) {
                Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strelka);
                mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (minWidthBlock / 3), (int) (minWidthBlock / 3), false);

                canvas.drawBitmap(mBitmap, 0, getHeight() - minWidthBlock, mPaint);

                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrixRotate, true);
                canvas.drawBitmap(mBitmap, getWidth() - minWidthBlock / 3, getHeight() - minWidthBlock, mPaint);

            }
            //end стрелки для скрола

            //        дорисовка лининий пунктира
            for (int k = 1; k <= nStep; k++) {
                if (Math.abs(step * k - averageValueData) > step / 2) {
                    canvas.drawLine(0, getHeight() - widthBorder - 1 - workOblGrafik * step * k

                            , getRight(), getHeight() - widthBorder - 1 - workOblGrafik * step * k, mPaintLinePunctire);
                    canvas.drawText(step * k + "", widthBorder / 2, getHeight() - widthBorder - 1 - workOblGrafik * step * k - 2, textPaint);
                }
            }
            //        дорисовка лининий пунктира


            canvas.clipRect(widthBorder, widthBorder / 3, getWidth() - widthBorder, getHeight() - widthBorder);
            // дорисовка выделенной

            if (!(rightRectSelected < widthBorder && leftRectSelected > getWidth() - widthBorder)) {
                if (npointTouch != -1) {
                    if (npointTouch % 2 == 0)
                        mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocksvet));
                    if (npointTouch % 2 == 1)
                        mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocktem));
                    canvas.drawRect(leftRectSelected, widthBorder / 3, rightRectSelected, getHeight() - widthBorder, mPaintSelectedColumn);
                }
            }
            // end дорисовка выделенной

//пунктир для выделеной
            for (int k = 1; k <= nStep; k++) {
                canvas.drawLine(leftRectSelected, getHeight() - widthBorder - 1 - workOblGrafik * step * k

                        , rightRectSelected, getHeight() - widthBorder - 1 - workOblGrafik * step * k, mPaintLinePunctireop);
            }
            //пунктир для выделеной

//пункты графика
            for (int i = 0; i < nArrayListSize; i++) {

                mPaintPunctGtafica.setShader(new LinearGradient(0, getHeight() - widthBorder - 1 - workOblGrafik * arrayListStolbValue.get(i), 0, getHeight(), getResources().getColor(R.color.verxstrel1), getResources().getColor(R.color.nizstrel1), Shader.TileMode.MIRROR));

                canvas.drawPath(getPathtopRoundRect(widthBorder + offsetX + leftBlok * i + widthBorder / 2,
                        getHeight() - widthBorder - 1 - workOblGrafik * arrayListStolbValue.get(i), widthBorder + offsetX + leftBlok * (i + 1) - widthBorder / 2, getHeight(), 10), mPaintPunctGtafica);


            }
            if (npointTouch != -1) {
                mPaintPunctGtaficav.setShader(new LinearGradient(0, getHeight() - widthBorder - 1 - workOblGrafik * arrayListStolbValue.get(npointTouch), 0, getHeight(), getResources().getColor(R.color.vverxstrel1), getResources().getColor(R.color.vnizstrel1), Shader.TileMode.MIRROR));

                canvas.drawPath(getPathtopRoundRect(widthBorder + offsetX + leftBlok * npointTouch + widthBorder / 2,
                        getHeight() - widthBorder - 1 - workOblGrafik * arrayListStolbValue.get(npointTouch),
                        widthBorder + offsetX + leftBlok * (npointTouch + 1) - widthBorder / 2, getHeight(), 10), mPaintPunctGtaficav);

            }
//пункты графика


            //пунктир средней
            canvas.restore();
            canvas.drawLine(0, getHeight() - widthBorder - 1 - workOblGrafik * averageValueData
                    , getRight(), getHeight() - widthBorder - 1 - workOblGrafik * averageValueData, mPaintLinePunctireaverageValueData);
            canvas.drawText("ср", widthBorder / 2, getHeight() - widthBorder - 3 - workOblGrafik * averageValueData - 2, textPaintaverageValueData);
            //пунктир средней
        }
    }

    //end onDraw
    @NonNull
//    возвращает путь прямоугольника с закруглеными верхними углами
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
                }
                offsetX = bufX - (bufmy - motionEvent.getX());

                break;

        }
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;
        invalidate();
        return true;
    }

    //возврашает шаг по максимальному значению и колво линий
    int getStepWeidht(float nElement, float maxValueData, int step) {
        if ((maxValueData / (step * nElement)) > 5)
            step = getStepWeidht(nElement, maxValueData, step * 10);
        else if ((maxValueData / (step * nElement)) > 2)
            step = getStepWeidht(nElement, maxValueData, step * 5);

        else if ((maxValueData / (step * nElement)) > 1)
            step = getStepWeidht(nElement, maxValueData, step * 2);


        return step;

    }

}
