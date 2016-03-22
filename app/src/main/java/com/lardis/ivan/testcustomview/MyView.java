package com.lardis.ivan.testcustomview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    float shiftPuctInValueDay = 0;
    float nItemInOneMesh = 1;
    float nPuct = 0;
    int days;
       String[] shortMonthName = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};
    ArrayList<Integer> daysInPunctArrayList = new ArrayList<Integer>();
    //входящие данные из layout
    ArrayList<String> arrayListName = new ArrayList<String>();
    ArrayList<String> arrayListTwoName;
    Calendar myCalendar = (Calendar) Calendar.getInstance();
    private TypeViewGraph typeView;
    private int dayMonday;
    private int nNepolWeek;

    //размеры экрана
    private final float MINHEIGHTBLOCK = 25;

    Region.Op regionREPLACE = Region.Op.REPLACE;

    private float canvasWidht;
    private float canvasHeight;
    //botder
    private float borderLeft;
    private float borderTop;
    private float borderBottom;
    private float borderRight;

    //толщина блока минимуи
    private float minWidthBlock = 100;
    private float widthBorder = 35;
    private float itemBorder = 13;
    private float itemRadius = 10;


    private float minX = 0;
    private float maxX;
    private int step;
    private Matrix matrixRotate;
    private ValueAnimator animator;
    private float workOblGrafikHeight;
    private float nStep;
    Path mPath;
    float leftRectSelected;
    float rightRectSelected;
    private Paint mPaintLinePunctire;
    private Paint mPaintBorderLine;
    private Paint mPaintLinePunctireSelected;
    private Paint mPaintPunctirAverage;
    private Paint mPaintMesh;
    private Paint mPaintTriangle;
    private Paint mPaintItem;
    private Paint mPaintItemSelected;
    private Paint mPaintBorder;
    private Paint mPaintSelectedColumn;
    private Paint mPaintFontAllColor;
    private Paint mPaintFontAverage;
    //смещение по x в данный момент
    private float offsetX;
    //номер выделеной
    private int npointTouch = -1;

    //скролим или влазеет
    private boolean isScroll;
    private ArrayList<String> arrayListStolbName = new ArrayList<String>();
    private ArrayList<Integer> arrayListStolbValue = new ArrayList<Integer>();
    private ArrayList<Integer> arrayListStolbValuesilka = new ArrayList<Integer>();
    //максимальное и среднее  значение столбцов
    private static float maxValueData = 0;
    private static float averageValueData = 0;

    private float widthBlok;
    //колво столбцов
    private int nArrayListSize;

    //ресурсы
    private int colorBorder;

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
        invalidateColor();
    }

    public void setColorBorderLine(int colorBorderLine) {
        this.colorBorderLine = colorBorderLine;
        invalidateColor();
    }

    private int colorBorderLine;


    public void setColorItemTop(int colorItemTop) {
        this.colorItemTop = colorItemTop;
        invalidateColor();
    }

    private int colorItemTop;

    public void setColorItemBottom(int colorItemBottom) {
        this.colorItemBottom = colorItemBottom;
        invalidateColor();
    }

    private int colorItemBottom;

    public void setColorItemSelectedTop(int colorItemSelectedTop) {
        this.colorItemSelectedTop = colorItemSelectedTop;
        invalidateColor();
    }

    private int colorItemSelectedTop;

    public void setColorItemSelectedBottom(int colorItemSelectedBottom) {
        this.colorItemSelectedBottom = colorItemSelectedBottom;
        invalidateColor();
    }

    private int colorItemSelectedBottom;

    public void setColorMeshOne(int colorMeshOne) {
        this.colorMeshOne = colorMeshOne;
        invalidateColor();
    }

    private int colorMeshOne;

    public void setColorMeshTwo(int colorMeshTwo) {
        this.colorMeshTwo = colorMeshTwo;
        invalidateColor();
    }

    private int colorMeshTwo;

    public void setColorPunctireLineAverage(int colorPunctireLineAverage) {
        this.colorPunctireLineAverage = colorPunctireLineAverage;
        invalidateColor();
    }

    private int colorPunctireLineAverage;

    public void setColorPunctireLineSelectedColor(int colorPunctireLineSelectedColor) {
        this.colorPunctireLineSelectedColor = colorPunctireLineSelectedColor;
        invalidateColor();
    }

    private int colorPunctireLineSelectedColor;

    public void setColorPunctirLine(int colorPunctirLine) {
        this.colorPunctirLine = colorPunctirLine;
        invalidateColor();
    }

    private int colorPunctirLine;

    public void setColorFontAll(int colorFontAll) {
        this.colorFontAll = colorFontAll;
        invalidateColor();
    }

    private int colorFontAll;

    public void setColorFontAverage(int colorFontAverage) {
        this.colorFontAverage = colorFontAverage;
        invalidateColor();
    }

    private int colorFontAverage;


    public void setColorTriangle(int colorTriangle) {
        this.colorTriangle = colorTriangle;
        invalidateColor();
    }

    private int colorTriangle;

    public void setColorSelectedItemShadowLayer(int colorSelectedItemShadowLayer) {
        this.colorSelectedItemShadowLayer = colorSelectedItemShadowLayer;
        invalidateColor();
    }

    private int colorSelectedItemShadowLayer;


    Bitmap mBitmap;

    Bitmap mBitmap1;


    public MyView(Context context) {
        super(context);
        init(null, 1);
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 1);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Myview, defStyle, 0);


        colorBorder = a.getColor(
                R.styleable.Myview_borderColor,
                getResources().getColor(R.color.borderColorExample));
        colorBorderLine = a.getColor(
                R.styleable.Myview_borderLineColor,
                getResources().getColor(R.color.borderLineColorExample));

        colorItemTop = a.getColor(
                R.styleable.Myview_itemTopColor,
                getResources().getColor(R.color.itemTopColorExample));
        colorItemBottom = a.getColor(
                R.styleable.Myview_itemBottomColor,
                getResources().getColor(R.color.itemBottomColorExample));
        colorItemSelectedTop = a.getColor(
                R.styleable.Myview_itemSelectedTopColor,
                getResources().getColor(R.color.itemSelectedTopColorExample));
        colorItemSelectedBottom = a.getColor(
                R.styleable.Myview_itemSelectedBottomColor,
                getResources().getColor(R.color.itemSelectedBottomColorExample));
        colorMeshOne = a.getColor(
                R.styleable.Myview_meshOneColor,
                getResources().getColor(R.color.meshOneColorExample));
        colorMeshTwo = a.getColor(
                R.styleable.Myview_meshTwoColor,
                getResources().getColor(R.color.meshTwoColorExample));
//
        colorPunctireLineAverage = a.getColor(
                R.styleable.Myview_punctireLineAverageColor,
                getResources().getColor(R.color.punctireLineAverageColorExample));
        colorPunctireLineSelectedColor = a.getColor(
                R.styleable.Myview_punctireLineSelectedColor,
                getResources().getColor(R.color.punctireLineSelectedColorExample));
        colorPunctirLine = a.getColor(
                R.styleable.Myview_punctirLineColor,
                getResources().getColor(R.color.punctirLineColorIntExample));

        colorFontAll = a.getColor(
                R.styleable.Myview_fontAllColor,
                getResources().getColor(R.color.fontAllColorExample));
        colorFontAverage = a.getColor(
                R.styleable.Myview_fontAverageColor,
                getResources().getColor(R.color.fontAverageColorExample));
//
//
        colorTriangle = a.getColor(
                R.styleable.Myview_triangleColor,
                getResources().getColor(R.color.triangleColorExample));

        colorSelectedItemShadowLayer = a.getColor(
                R.styleable.Myview_selectedColumnShadowLayerColor,
                getResources().getColor(R.color.selectedColumnShadowLayerColorExample));


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

        mPaintMesh = new Paint();

        matrixRotate = new Matrix();
        matrixRotate.postRotate(180);

        mPaintBorderLine = new Paint();
        mPaintBorderLine.setAntiAlias(true);

        mPaintBorderLine.setStrokeWidth(1.0f);
        mPaintBorderLine.setStyle(Paint.Style.STROKE);

        mPaintLinePunctire = new Paint();
        mPaintLinePunctire.setAntiAlias(true);
        mPaintLinePunctire.setStrokeWidth(4);

        mPaintLinePunctireSelected = new Paint();
        mPaintLinePunctireSelected.setAntiAlias(true);
        mPaintLinePunctireSelected.setStrokeWidth(4);

        mPaintPunctirAverage = new Paint();
        mPaintPunctirAverage.setPathEffect(new DashPathEffect(new float[]{3.0f, 8.0f}, 0));
        mPaintPunctirAverage.setAntiAlias(true);
        mPaintPunctirAverage.setTextSize(100);
        mPaintPunctirAverage.setStrokeWidth(5);


        mPaintTriangle = new Paint();


        mPaintItem = new Paint();

        mPaintItem.setAntiAlias(true);

        mPaintItemSelected = new Paint();

        mPaintItemSelected.setAntiAlias(true);


        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintBorder = new Paint();
        mPaintBorder.setStrokeWidth(10);


        mPaintFontAllColor = new Paint();
        mPaintFontAllColor.setAntiAlias(true);

        mPaintFontAllColor.setTextAlign(Paint.Align.CENTER);
        mPaintFontAverage = new Paint();
        mPaintFontAverage.setAntiAlias(true);
        mPaintFontAverage.setTextSize(18);

        mPaintFontAverage.setTextAlign(Paint.Align.CENTER);


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.strelka);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) widthBorder, (int) widthBorder, false);
        mBitmap1 = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrixRotate, true);
        invalidateColor();
    }

    private void invalidateColor() {
        mPaintFontAverage.setColor(colorFontAverage);
        mPaintFontAllColor.setColor(colorFontAll);

        mPaintBorder.setColor(colorBorder);
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mPaintItemSelected.setShader(new LinearGradient(0, 0, 0, getHeight(),
                colorItemSelectedTop
                , colorItemSelectedBottom, Shader.TileMode.MIRROR));

        mPaintItem.setShader(new LinearGradient(0, 0, 0, getHeight(),
                colorItemTop
                , colorItemBottom, Shader.TileMode.MIRROR));

        mPaintTriangle.setColor(colorTriangle);
        mPaintPunctirAverage.setColor(colorPunctireLineAverage);
        mPaintLinePunctireSelected.setColor(colorPunctireLineSelectedColor);
        mPaintLinePunctire.setColor(colorPunctirLine);
        mPaintBorderLine.setColor(colorBorderLine);


    }

    public void setDrawGraph(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph, TypeViewGraph typeViewGraph) {

        typeView = typeViewGraph;

        if (arrayListMetodDrawGraph != null) {
            arrayListName.clear();
            myCalendar.set(year, month, day);

            switch (typeView) {

                case MESH_MONTH_ITEM_WEEK:

                    daysInPunctArrayList.clear();


                    int DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
                    days = arrayListMetodDrawGraph.size() * 7;

                    nItemInOneMesh = 4;
                    itemBorder = 3;
                    itemRadius = 4;

                    if (DAY_OF_WEEK != 1) day = day + 2 - DAY_OF_WEEK;

                    else {
                        day = day - 6;
                    }
                    if (day <= 0) {
                        month--;

                        myCalendar.set(2016, month, 1);
                        day = myCalendar.getActualMaximum(Calendar.DATE) + day;


                    }
                    int bufMonth = month;
                    myCalendar.set(year, bufMonth, day);
                    days = days - (myCalendar.getActualMaximum(Calendar.DATE) - day + 1);
                    daysInPunctArrayList.add(myCalendar.getActualMaximum(Calendar.DATE) - day + 1);
                    arrayListName.add(shortMonthName[bufMonth] + "");
                    bufMonth++;

                    while (days > 0)


                    {
                        myCalendar.set(year, bufMonth, day);
                        arrayListName.add(shortMonthName[bufMonth] + "");
                        if (days < myCalendar.getActualMaximum(Calendar.DATE)) {
                            daysInPunctArrayList.add(days);
                            days = 0;
                        } else {
                            daysInPunctArrayList.add(myCalendar.getActualMaximum(Calendar.DATE));
                            days = days - myCalendar.getActualMaximum(Calendar.DATE);

                        }
                        if (bufMonth == 11) {
                            year++;
                            bufMonth = 0;
                        } else
                            bufMonth++;

                    }
                    nPuct = daysInPunctArrayList.size();
if(daysInPunctArrayList.get(0)<14)
{
    daysInPunctArrayList.set(0,daysInPunctArrayList.get(0)+14);

    arrayListMetodDrawGraph.add(0,-10);
    arrayListMetodDrawGraph.add(0,-10);
}
                    if(daysInPunctArrayList.get(daysInPunctArrayList.size()-1)<14)
                    {
                        daysInPunctArrayList.set(daysInPunctArrayList.size()-1,daysInPunctArrayList.get(daysInPunctArrayList.size()-1)+14);

                        arrayListMetodDrawGraph.add(-10);
                        arrayListMetodDrawGraph.add(-10);
                    }

                    break;

                case MESH_WEEK_ITEM_DAY:

                    nItemInOneMesh = 7;
                    itemBorder = 2;
                    itemRadius = 3;

                    int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (max_date == arrayListMetodDrawGraph.size()) {
                        typeView = TypeViewGraph.MESH_WEEK_ITEM_DAY;

                        int monday = (1 - myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7;

                        if (monday == 0) monday = 7;


                        for (int i = 0; i < arrayListMetodDrawGraph.size(); i++) {
                            if ((i + 1) % 7 == monday) arrayListName.add(i + 1 + "");
                            else arrayListName.add("");

                        }
                        dayMonday = monday;
                        nNepolWeek = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) / 7;
                        if (monday != 1) nNepolWeek++;
                        if (myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) % 7 + 1 - monday > 0)
                            nNepolWeek++;
                        dayMonday = 7;
                        if (dayMonday == 1) shiftPuctInValueDay = 0;
                        else shiftPuctInValueDay = (8f - dayMonday) / 7f;

                        nPuct = nNepolWeek;


                    }
                    else return;


                    break;

                case MESH_WEEK_ITEM_WEEK:
                    nItemInOneMesh = 1;
                    DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
                    if (DAY_OF_WEEK != 1) day = day + 2 - DAY_OF_WEEK;
                    else {
                        day = day - 6;
                    }
                    if (day <= 0) {
                        month--;
                        myCalendar.set(2016, month, 1);
                        day = myCalendar.getActualMaximum(Calendar.DATE) + day;
                    }
                    //day понедельник
                    int daySunday;
                    int monthSunday;
                    arrayListTwoName = new ArrayList<String>();
                    for (int i = 0; i < arrayListMetodDrawGraph.size(); i++)
                    {
                        myCalendar.set(year, month, day);
                        if (day + 6 > myCalendar.getActualMaximum(Calendar.DATE)) {
                            daySunday = day + 6 - myCalendar.getActualMaximum(Calendar.DATE);
                            if (month == 11) monthSunday = 0;
                            else
                                monthSunday = month + 1;
                        }
                        else {
                            monthSunday = month;
                            daySunday = day + 6;
                        }
                        arrayListName.add(day + shortMonthName[month] + "");
                        arrayListTwoName.add(daySunday + shortMonthName[monthSunday] + "");
                        if (day + 7 > myCalendar.getActualMaximum(Calendar.DATE)) {
                            day = day + 7 - myCalendar.getActualMaximum(Calendar.DATE);

                            if (month == 11) {
                                month = 0;
                                year++;
                            } else
                                month++;
                            Log.d("Mylog","day="+day+"month="+month);
                        } else {
                            day = day + 7;
                        }

                    }


                    break;

                case MESH_DAY_ITEM_DAY:
                case MESH_MONTH_ITEM_MONTH:
                    nItemInOneMesh = 1;

                    max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);


                    for (int i = 0; i < arrayListMetodDrawGraph.size(); i++)

                    {


                        if (typeView == TypeViewGraph.MESH_DAY_ITEM_DAY) {
                            arrayListName.add(day + " " + shortMonthName[month]);

                            if (day == max_date) {
                                day = 1;

                                if (month == 11) month = 0;
                                else month++;
                                myCalendar.set(year, month, day);
                                max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                            } else day++;
                        }

                        if (typeView == TypeViewGraph.MESH_MONTH_ITEM_MONTH) {
                            arrayListName.add(shortMonthName[month]);

                            if (month == 11) {
                                month = 0;


                            } else month++;
                        }


                    }
                    break;


            }
            setTwoArrayListNameStringValueInt(arrayListName, arrayListMetodDrawGraph);
        }
    }

    /////
    private void setTwoArrayListNameStringValueInt(ArrayList<String> arrayListStolbNamesilka, ArrayList<Integer> arrayListStolbValuesilk) {

        {

            this.arrayListStolbValuesilka = arrayListStolbValuesilk;
            if (arrayListStolbNamesilka != null) {
//                Log.d("Mylog","arrayListStolbNamesilka"+arrayListStolbNamesilka.size());
                this.arrayListStolbName = (ArrayList<String>) arrayListStolbNamesilka.clone();
                nArrayListSize = arrayListStolbValuesilk.size();
                offsetX = 0;
            }
            if ((typeView == TypeViewGraph.MESH_DAY_ITEM_DAY) || (typeView == TypeViewGraph.MESH_MONTH_ITEM_MONTH) || typeView == TypeViewGraph.MESH_WEEK_ITEM_WEEK)

            {
                shiftPuctInValueDay = 0;
                itemBorder = 13;
                itemRadius = 10;
                nItemInOneMesh = 1;
                nPuct = nArrayListSize;
            }
            if ((this.arrayListStolbName.size() == arrayListStolbValuesilk.size()) || (typeView == TypeViewGraph.MESH_MONTH_ITEM_WEEK)) {

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

                    maxX = (nArrayListSize - canvasWidht / (minWidthBlock / nItemInOneMesh)) * (minWidthBlock / nItemInOneMesh) + widthBorder * 2;
                    isScroll = !(minWidthBlock < canvasWidht / nArrayListSize);
                    if (TypeViewGraph.MESH_WEEK_ITEM_DAY == typeView) isScroll = false;
                    if (typeView == TypeViewGraph.MESH_MONTH_ITEM_WEEK)
                        isScroll = !(minWidthBlock < canvasWidht / arrayListStolbValuesilk.size() * 4);
                    ;
                    if (isScroll) {
                        widthBlok = minWidthBlock;


                    } else {

                        widthBlok = (canvasWidht - widthBorder * 2) * nItemInOneMesh / nArrayListSize;
                        if (typeView == TypeViewGraph.MESH_MONTH_ITEM_WEEK) {
                            widthBlok = (canvasWidht - widthBorder * 2) / arrayListStolbValuesilk.size() * 4;

                        }
                    }
                }


                //            шаг пунктирных линий
                step = getStepWeidht(((canvasHeight - widthBorder * 1.5f) / MINHEIGHTBLOCK), maxValueData, 1);
                nStep = maxValueData / step;
                workOblGrafikHeight = (canvasHeight - widthBorder * 1.33f - 20) / maxValueData;

                animator.start();
            }
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidht = getWidth();
        canvasHeight = getHeight();
        borderTop = widthBorder / 3;
        borderBottom = getHeight() - widthBorder;
        borderLeft = widthBorder;
        borderRight = getWidth() - widthBorder;
        mPaintItem.setShader(new LinearGradient(0, borderTop, 0, borderBottom, colorItemTop, colorItemBottom, Shader.TileMode.MIRROR));
        mPaintItemSelected.setShader(new LinearGradient(0, borderTop, 0, borderBottom, colorItemSelectedTop, colorItemSelectedBottom, Shader.TileMode.MIRROR));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (nArrayListSize != 0) {
//общее
            //border borderRight
            canvas.drawRect(borderRight, 0, canvasWidht, canvasHeight, mPaintBorder);
            //border borderBottom

            canvas.drawRect(0, 0, borderLeft, canvasHeight, mPaintBorder);
            //border borderBottom
            canvas.drawRect(0, borderBottom, canvasWidht, canvasHeight, mPaintBorder);
            //border borderTop
            canvas.drawRect(0, 0, canvasWidht, borderTop, mPaintBorder);
            //border
//start line тени для border
            //
            canvas.drawLine(0, borderTop, canvasWidht, borderTop, mPaintBorderLine);
            canvas.drawLine(0, borderBottom, canvasWidht, borderBottom, mPaintBorderLine);

            canvas.drawLine(0, 0, 0, canvasHeight, mPaintBorderLine);
            canvas.drawLine(canvasWidht, 0, canvasWidht, canvasHeight, mPaintBorderLine);

            canvas.drawLine(borderLeft, borderTop, borderLeft, borderBottom, mPaintBorderLine);
            canvas.drawLine(borderRight, borderTop, borderRight, borderBottom, mPaintBorderLine);


            //end line тени для border
            //start стрелки для скрола

            if (isScroll) {
                canvas.drawBitmap(mBitmap, 0, borderBottom - 30, mPaintMesh);
                canvas.drawBitmap(mBitmap1, borderRight, borderBottom - 30, mPaintMesh);
            }
            if (npointTouch!=-1) {
                if(arrayListStolbValue.get(npointTouch)==-10)npointTouch=-1;
            }
            //end стрелки для скрола

            canvas.clipRect(borderLeft, 0, borderRight, canvasHeight);
            //прямоугольники
            int k4 = 0, k5 = 0;
            for (int i = 0; i < nPuct; i++) {
                {
                    if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
                    else mPaintMesh.setColor(colorMeshTwo);

                    if (typeView == TypeViewGraph.MESH_MONTH_ITEM_WEEK) {

                        k5 = k4;

                        k4 = k4 + daysInPunctArrayList.get(i);
                        canvas.drawText(arrayListStolbName.get(i), widthBorder + offsetX + widthBlok / 28 * (k5 + (k4 - k5) / 2), borderBottom + 12, mPaintFontAllColor);

                        canvas.drawRect(widthBorder + offsetX + widthBlok / 28 * k5, borderTop, widthBorder + offsetX + widthBlok / 28 * k4, borderBottom, mPaintMesh);

                    } else {
                        canvas.drawRect(borderLeft + offsetX + widthBlok * (i - shiftPuctInValueDay), borderTop, widthBorder + offsetX + widthBlok * (i + 1 - shiftPuctInValueDay), borderBottom, mPaintMesh);

                    }
                }
            }


            //end прямоугольники
            //  текс снизу


            if (typeView != TypeViewGraph.MESH_MONTH_ITEM_WEEK) {
                for (int i = 0; i < nArrayListSize; i++) {
                    {
                        canvas.drawText(arrayListStolbName.get(i), widthBorder + offsetX + widthBlok / nItemInOneMesh * (i + 1.0f / 2.0f - shiftPuctInValueDay), borderBottom + 12, mPaintFontAllColor);
                        if (typeView == TypeViewGraph.MESH_WEEK_ITEM_WEEK)
                            canvas.drawText(arrayListTwoName.get(i), widthBorder + offsetX + widthBlok * (i + 1.0f / 2.0f), borderBottom + 22, mPaintFontAllColor);
                    }
                }
            }


            //end  текс снизу

            //края выделеного прямоугольника

            leftRectSelected = widthBorder + offsetX + widthBlok / nItemInOneMesh * npointTouch;
            rightRectSelected = widthBorder + offsetX + widthBlok / nItemInOneMesh * (npointTouch + 1);


            canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionREPLACE);
            //        дорисовка лининий пунктира
            for (int k = 1; k <= nStep; k++) {
                if (Math.abs(step * k - averageValueData) > step / 2) {
                    canvas.drawLine(0, borderBottom - 1 - workOblGrafikHeight * step * k

                            , canvasWidht, borderBottom - 1 - workOblGrafikHeight * step * k, mPaintLinePunctire);
                    canvas.drawText(step * k + "", widthBorder / 2, borderBottom - 1 - workOblGrafikHeight * step * k - 2, mPaintFontAllColor);

                }
            }


            //        дорисовка лининий пунктира


            canvas.clipRect(borderLeft, borderTop, borderRight, borderBottom, regionREPLACE);
            // дорисовка выделенной


//

            if (npointTouch != -1) {
                if (npointTouch % 2 == 0)
                    mPaintSelectedColumn.setColor(colorMeshOne);
                if (npointTouch % 2 == 1)
                    mPaintSelectedColumn.setColor(colorMeshTwo);
                canvas.drawRect(leftRectSelected, borderTop, rightRectSelected, borderBottom, mPaintSelectedColumn);
            }


            // end дорисовка выделенной

//пунктир для выделеной
//            общее
//
            {
                for (int k = 1; k <= nStep; k++) {
                    canvas.drawLine(leftRectSelected, borderBottom - 1 - workOblGrafikHeight * step * k

                            , rightRectSelected, borderBottom - 1 - workOblGrafikHeight * step * k, mPaintLinePunctireSelected);
                }
            }
            //пунктир для выделеной

//пункты графика

//
            {
                for (int i = 0; i < nArrayListSize; i++) {


                    canvas.drawPath(getPathtopRoundRect(borderLeft + offsetX + widthBlok / nItemInOneMesh * i + itemBorder,
                            borderBottom + 1 - workOblGrafikHeight * arrayListStolbValue.get(i), borderLeft + offsetX + widthBlok / nItemInOneMesh * (i + 1) - itemBorder, canvasHeight, itemRadius), mPaintItem);


                }
                if (npointTouch != -1 ) {


                        canvas.drawPath(getPathtopRoundRect(borderLeft + offsetX + widthBlok / nItemInOneMesh * npointTouch + itemBorder,
                                borderBottom - 1 - workOblGrafikHeight * arrayListStolbValue.get(npointTouch),
                                borderLeft + offsetX + widthBlok / nItemInOneMesh * (npointTouch + 1) - itemBorder, canvasHeight, itemRadius), mPaintItemSelected);


                }
            }


//пункты графика


            canvas.clipRect(borderLeft, 0, borderRight, canvasHeight, regionREPLACE);
            // start треугольник снизу и сверху выделеного блока


//

            mPath.reset();
            float lr = (rightRectSelected - leftRectSelected) / 3;
            if ((typeView == TypeViewGraph.MESH_WEEK_ITEM_DAY) || (typeView == TypeViewGraph.MESH_MONTH_ITEM_WEEK))
                lr = 0;
            if (typeView == TypeViewGraph.MESH_WEEK_ITEM_WEEK) {
                mPath.moveTo(leftRectSelected + lr, getHeight() - 3);
                mPath.lineTo(rightRectSelected - lr, getHeight() - 3);
                mPath.lineTo((leftRectSelected + rightRectSelected) / 2, getHeight() - widthBorder / 3);
            } else {
                mPath.moveTo(leftRectSelected + lr, getHeight() - widthBorder / 3 + 5);
                mPath.lineTo(rightRectSelected - lr, getHeight() - widthBorder / 3 + 5);
                mPath.lineTo((leftRectSelected + rightRectSelected) / 2, getHeight() - widthBorder * 2 / 3 + 5);
            }
            canvas.drawPath(mPath, mPaintTriangle);
            mPath.reset();
            mPath.moveTo(leftRectSelected + lr, widthBorder / 3 + 3);
            mPath.lineTo(rightRectSelected - lr, widthBorder / 3 + 3);
            mPath.lineTo((leftRectSelected + rightRectSelected) / 2, widthBorder * 2 / 3 + 3);

            canvas.drawPath(mPath, mPaintTriangle);


//end  треугольник снизу и сверху выделеного блока
            canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionREPLACE);
//общее
            //пунктир средней j,ott

            canvas.drawLine(0, borderBottom - 1 - workOblGrafikHeight * averageValueData
                    , getRight(), borderBottom - 1 - workOblGrafikHeight * averageValueData, mPaintPunctirAverage);
            canvas.drawText("ср", widthBorder / 2, borderBottom - 3 - workOblGrafikHeight * averageValueData - 2, mPaintFontAverage);
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
                        npointTouch = (int) ((motionEvent.getX() - bufX - widthBorder) / (minWidthBlock / nItemInOneMesh));
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
