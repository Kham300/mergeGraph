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
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    ArrayList<ItemArrayListData> arrayListsilka;
    ArrayList<ItemArrayListData> bufArrayList;

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
    float WidthBorder = 35;
    //скролим или влазеет
    boolean isScroll;
    ArrayList<ItemArrayListData> arrayList = new ArrayList<ItemArrayListData>();
    //максимальное значение столбцов
    private static float maxValueData;
    private static float maxValueDatasilka;
    private static float maxValueDatabuf;

    Timer timer;
    float leftBlok;
    //колво столбцов
    static int nArrayListSize;
    String name = "";
    int version = 0;

    public void setArrayList(String name, int version1, final ArrayList<ItemArrayListData> arrayList1) {

        if (!(this.name.equals(name) && version1 == version)) {
//            this.arrayList = arrayList1;
            this.name = name;
            version = version1;
            offsetX = 0;
            npointTouch = -1;
            arrayList=new ArrayList<ItemArrayListData>();


            for (int i = 0; i < arrayList1.size(); i++) {
                arrayList.add(i, new ItemArrayListData(arrayList1.get(i).name, 0));
                Log.d("Mylog",arrayList.get(i).getI()+"I");
            }
            maxValueData=0;
            for (int i = 0; i < arrayList1.size(); i++)
                if (arrayList1.get(i).getI() > maxValueData)
                    maxValueData = arrayList1.get(i).getI();


            nArrayListSize = arrayList.size();

//          this.arrayList.clear();

//arrayListsilka= (ArrayList<ItemArrayListData>) arrayList1.clone();
            final ValueAnimator animator1 = ValueAnimator.ofFloat(0, 1);
//            animator.setDuration(SyncStateContract.Constants.ANIM_DURATION);
            animator1.setInterpolator(new AccelerateInterpolator());
//            animator.setStartDelay(delay);
            animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    float value = ((Float) (animation.getAnimatedValue()))
                            .floatValue();
//                    Log.d("Mylog", maxValueData+"=maxValueData");
                    for (int i = 0; i < nArrayListSize; i++) {
                        arrayList.get(i).setI(arrayList1.get(i).getI()*value);
                    }

//arrayList= (ArrayList<ItemArrayListData>) arrayList1.clone();
                    invalidate();

                }
            });

            animator1.start();


        } else

        {

            arrayListsilka = (ArrayList<ItemArrayListData>) arrayList1.clone();
            bufArrayList = (ArrayList<ItemArrayListData>) arrayList.clone();
            maxValueDatasilka = 0;

            for (int i = 0; i < arrayList.size(); i++)
                if (arrayList1.get(i).getI() > maxValueDatasilka)
                    maxValueDatasilka = arrayList1.get(i).getI();
            maxValueDatabuf = maxValueData;
            final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//            animator.setDuration(SyncStateContract.Constants.ANIM_DURATION);
            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setStartDelay(delay);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    float value = ((Float) (animation.getAnimatedValue()))
                            .floatValue();

                    for (int i = 0; i < nArrayListSize; i++) {
                        arrayList.get(i).setI(bufArrayList.get(i).getI() + (arrayListsilka.get(i).getI() - bufArrayList.get(i).getI()) * value);
                    }
                    maxValueData = maxValueDatabuf + (maxValueDatasilka - maxValueDatabuf) * value;
                    Log.d("maxValueData", maxValueData + "");
                    invalidate();

                }
            });

            animator.start();
        }

    }


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

    //    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
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


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Mylog",maxValueData+"=maxValueData,"+arrayList.get(2).getI()+"arrayList.get(2).getI()");
        if (nArrayListSize != 0) {


            maxX = (nArrayListSize - getWidth() / minWidthBlock) * minWidthBlock + WidthBorder * 2;
            isScroll = !(minWidthBlock < getWidth() / nArrayListSize);


            if (isScroll) {
                leftBlok = minWidthBlock;
            } else {

                leftBlok = (getWidth() - WidthBorder * 2) / nArrayListSize;

            }
        }

        if (nArrayListSize != 0) {
            //border
            canvas.drawRect(getWidth() - WidthBorder, 0, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, WidthBorder, getHeight(), mPaintBorder);
            canvas.drawRect(0, getHeight() - WidthBorder, getWidth(), getHeight(), mPaintBorder);
            canvas.drawRect(0, 0, getWidth(), WidthBorder / 3, mPaintBorder);
            //border


            //start for

            for (int i = 0; i < nArrayListSize; i++) {


                {
                    if (i % 2 == 0) mPaint.setColor(getResources().getColor(R.color.block0));
                    if (i % 2 == 1) mPaint.setColor(getResources().getColor(R.color.block1));
                    canvas.drawRect(WidthBorder + offsetX + leftBlok * i, WidthBorder / 3, WidthBorder + offsetX + leftBlok * (i + 1), getHeight() - WidthBorder, mPaint);

                    String text = arrayList.get(i).name;
                    //                textPaint.getTextBounds(text, 0, text.length(), new Rect());

                    //
                    canvas.drawText(text, WidthBorder + offsetX + leftBlok * (i + 1.0f / 2.0f), getHeight() - WidthBorder * 2 / 3, textPaint);
                    //

                    mPaintPunctGtafica.setShader(new LinearGradient(0, 0, 0, getHeight(), getResources().getColor(R.color.vstrel1), getResources().getColor(R.color.strel1), Shader.TileMode.MIRROR));

                    canvas.drawPath(getPathtopRoundRect(WidthBorder + offsetX + leftBlok * i + WidthBorder / 2,getHeight() - WidthBorder*1.5f- (getHeight() - WidthBorder * 3) / maxValueData * arrayList.get(i).getI(), WidthBorder + offsetX + leftBlok * (i + 1) - WidthBorder / 2, getHeight() - WidthBorder, 10), mPaintPunctGtafica);


                }


            }
//end for!
// дорисовка выделенной
            float leftrect = WidthBorder + offsetX + leftBlok * npointTouch;
            float rightrect = WidthBorder + offsetX + leftBlok * (npointTouch + 1);

            if (!(rightrect < WidthBorder && leftrect > getWidth() - WidthBorder)) {


                mPaintSelectedColumn.setAntiAlias(true);
                if (npointTouch % 2 == 0)
                    mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocksvet));
                if (npointTouch % 2 == 1)
                    mPaintSelectedColumn.setColor(getResources().getColor(R.color.vblocktem));
                canvas.drawRect(leftrect, WidthBorder / 3, rightrect, getHeight() - WidthBorder, mPaintSelectedColumn);


                if (npointTouch != -1) {
//                    RectF rectF=new RectF(WidthBorder + offsetX + leftBlok * npointTouch + WidthBorder / 2,
//                            (getHeight() - WidthBorder * 3) / maxValueData * arrayList.get(npointTouch).getI(),
//                            WidthBorder + offsetX + leftBlok * (npointTouch + 1) - WidthBorder / 2, getHeight() - WidthBorder);

                    //                    canvas.drawRoundRect(rectF,15,15, mPaint);

                    canvas.drawPath(getPathtopRoundRect(WidthBorder + offsetX + leftBlok * npointTouch + WidthBorder / 2,
                            getHeight() - WidthBorder*1.5f- (getHeight() - WidthBorder * 3) / maxValueData * arrayList.get(npointTouch).getI(),
                            WidthBorder + offsetX + leftBlok * (npointTouch + 1) - WidthBorder / 2, getHeight() - WidthBorder, 10), mPaintPunctGtafica);


//                    canvas.drawPath(path, mPaint);
                }

            }
            // end дорисовка выделенной
            // start треугольник снизу
            if (leftrect < WidthBorder) leftrect = WidthBorder;
            if (rightrect > getWidth() - WidthBorder) rightrect = getWidth() - WidthBorder;

            if ((rightrect - leftrect) > 15) {
                Path mPath = new Path();


                float lr = (rightrect - leftrect) / 3;
                mPath.moveTo(leftrect + lr, getHeight() - WidthBorder / 3);
                mPath.lineTo(rightrect - lr, getHeight() - WidthBorder / 3);
                mPath.lineTo((leftrect + rightrect) / 2, getHeight() - WidthBorder * 2 / 3);

                canvas.drawPath(mPath, mPaintTriangle);
            }
//end треугольник снизу

            //start border боковые
            mPaint.setColor(getResources().getColor(R.color.border));
            canvas.drawRect(getWidth() - WidthBorder, 0, getWidth(), getHeight(), mPaint);
            canvas.drawRect(0, 0, WidthBorder, getHeight(), mPaint);


//end border боковые
//start line тени для border
            shadowPaint.setAntiAlias(true);
            shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));

            shadowPaint.setStrokeWidth(1.0f);
            shadowPaint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(0, getHeight() - WidthBorder, getWidth(), getHeight() - WidthBorder, shadowPaint);
            canvas.drawLine(0, WidthBorder / 3, getWidth(), WidthBorder / 3, shadowPaint);
            canvas.drawLine(0, 0, 0, getHeight(), shadowPaint);
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), shadowPaint);
            shadowPaint.setColor(getContext().getResources().getColor(R.color.vblockborde));
            canvas.drawLine(WidthBorder, WidthBorder / 3, WidthBorder, getHeight() - WidthBorder, shadowPaint);
            canvas.drawLine(getWidth() - WidthBorder, WidthBorder / 3, getWidth() - WidthBorder, getHeight() - WidthBorder, shadowPaint);

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
        }



//        дорисовка лининий
////        maxValueData
//        Paint mpa=new Paint();
//        mpa.setAntiAlias(true);
//        mpa.setColor(Color.BLACK);
//        mpa.setStrokeWidth(2);
//
//        float[] intervals = new float[] { 2.0f, 8.0f };
//        float phase = 0;
//
//
//        mpa.setPathEffect(new DashPathEffect(intervals, phase));
//
////       1.5f*WidthBorder;
//              float buf= getHeight()-WidthBorder*2.5f;
//        int my=0;
//                my=(int)(buf/100);
//        for(int k=1;k<=my;k++)
//        {
//
//
//            canvas.drawLine(0,getHeight() - WidthBorder*1.5f- (getHeight() - WidthBorder * 3)/maxValueData*100*k ,getRight(),getHeight() - WidthBorder*1.5f- (getHeight() - WidthBorder * 3)/maxValueData*100*k ,mpa);
//
//        }
//        canvas.drawPath(getPathtopRoundRect(WidthBorder + offsetX + leftBlok * npointTouch + WidthBorder / 2,
//                getHeight() - WidthBorder*1.5f- (getHeight() - WidthBorder * 3) / maxValueData * arrayList.get(npointTouch).getI(),
//                WidthBorder + offsetX + leftBlok * (npointTouch + 1) - WidthBorder / 2, getHeight() - WidthBorder, 10), mPaintPunctGtafica);

        //end стрелки для скрола
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
                        npointTouch = (int) ((motionEvent.getX() - bufX - WidthBorder) / minWidthBlock);
                    else
                        npointTouch = (int) ((motionEvent.getX() - WidthBorder - bufX) * nArrayListSize / (getWidth() - WidthBorder * 2));

//                    (WidthBorder + offsetX + (getWidth() - WidthBorder * 2) * i / nArrayListSize, 0, WidthBorder + offsetX + (getWidth() - WidthBorder * 2) * (i + 1) / nArrayListSize, getHeight(), mPaint);

                }
                offsetX = bufX - (bufmy - motionEvent.getX());

                break;

        }
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;
        invalidate();


        return true;
    }


}
