package com.lardis.ivan.testcustomview.myGroopView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
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

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.myEnum.enumTypeViewGraph;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * TODO: document your custom view class.
 */
public class MyGraphView extends View {


    //холст
    private float canvasWidht;
    private float canvasHeight;

    //обработка жестов

    private boolean showZoom = false;

    private float offsetX;
    private float minX = 0;
    private float maxX;
    private float bufX;
    private float bufX2 = 0;
    //номер выделеного пункта графика
    private int nSelectedTouch = -1;

    //botder
    private float WIDTH_BORDER = 35;
    private float borderLeft;
    private float borderTop;
    private float borderBottom;
    private float borderRight;

    //толщина блока
    private final float MIN_WIDTH_BLOCK = 100;
    private final float MIN_HEIGHT_BLOCK = 25;
    private float widthBlok;

    float shiftPuctInValueDay = 0;
    float nItemInOneMesh = 1;
    float nPuct = 0;
    float startGorizontalGraph = 0;


    // пункты графика
    private float itemBorder = 13;
    private float itemRadius = 10;
    private int nItem;

    private float leftRectSelectedMesh;
    private float rightRectSelectedMesh;

    //два графика
    boolean twoGraph = false;

    //скролим или влазеет
    private boolean isScroll;


    private int nNepolWeek;
    private int step;

    //максимальное и среднее  значение пунктов
    private float maxValueData1 = 0;
    private float averageValueData1 = 0;
    private float maxValueData2 = 0;
    private float averageValueData2 = 0;


    private float workRegionGrafikHeight;
    private float nStep1;
    private float nStep2;

    //
    private Path mPath;
    private Path mPathStrelkaRight;
    private Path mPathStrelkaLeft;
    //
    private Paint mPaintLinePunctire;
    private Paint mPaintStrelka;
    private Paint mPaintBorderLine;
    private Paint mPaintLinePunctireSelected;
    private Paint mPaintPunctirAverage;
    private Paint mPaintMesh;
    private Paint mPaintTriangle;
    private Paint mPaintItem;
    private Paint mPaintItemSelected;
    private Paint mPaintItem2;
    private Paint mPaintItemSelected2;
    private Paint mPaintBorder;
    private Paint mPaintSelectedColumn;
    private Paint mPaintFontAllColor;
    private Paint mPaintFontAverage;
    private Paint mPaintCenterDelimeter;


    private ArrayList<Integer> daysInPunctArrayList = new ArrayList<Integer>();
    private ArrayList<String> arrayListName = new ArrayList<String>();
    private ArrayList<String> arrayListTwoName;
    private ArrayList<String> arrayListStolbName = new ArrayList<String>();
    private ArrayList<Integer> arrayListStolbValue1 = new ArrayList<Integer>();
    private ArrayList<Integer> arrayListStolbValueBuf1 = new ArrayList<Integer>();
    private ArrayList<Integer> arrayListStolbValue2 = new ArrayList<Integer>();
    private ArrayList<Integer> arrayListStolbValueBuf2 = new ArrayList<Integer>();

    public interface SelectedZoom {

        void doShow(boolean work);

        void setCoordinate(float x, float y,int nTouch);


    }

    public void setSelectedZoom(SelectedZoom selectedZoom) {
        this.selectedZoom = selectedZoom;
    }

    SelectedZoom selectedZoom;


    Region.Op regionREPLACE = Region.Op.REPLACE;

    private ValueAnimator animator;

    String[] shortMonthName = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};

    Calendar myCalendar = (Calendar) Calendar.getInstance();

    private enumTypeViewGraph typeView;


    public MyGraphView(Context context) {
        super(context);
        init(null, 1);
    }


    public MyGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 1);
    }

    public MyGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Myview, defStyle, 0);

        setOnLongClickListener(onLongClickListener);

        colorBorder = a.getColor(
                R.styleable.Myview_borderColor,
                getResources().getColor(R.color.borderColorExample));
        colorCenterDelimeter = a.getColor(
                R.styleable.Myview_centerDelimeterColor,
                getResources().getColor(R.color.colorCenterDelimeterExample));
        colorBorderLine = a.getColor(
                R.styleable.Myview_borderLineColor,
                getResources().getColor(R.color.borderLineColorExample));


        colorItemTop2 = a.getColor(
                R.styleable.Myview_itemTopColor2,
                getResources().getColor(R.color.itemTopColor2Example));
        colorItemBottom2 = a.getColor(
                R.styleable.Myview_itemBottomColor,
                getResources().getColor(R.color.itemBottomColor2Example));
        colorItemSelectedTop2 = a.getColor(
                R.styleable.Myview_itemSelectedTopColor,
                getResources().getColor(R.color.itemSelectedTopColor2Example));
        colorItemSelectedBottom2 = a.getColor(
                R.styleable.Myview_itemSelectedBottomColor,
                getResources().getColor(R.color.itemSelectedBottomColor2Example));


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

        colorTriangle = a.getColor(
                R.styleable.Myview_triangleColor,
                getResources().getColor(R.color.triangleColorExample));

        colorSelectedItemShadowLayer = a.getColor(
                R.styleable.Myview_selectedColumnShadowLayerColor,
                getResources().getColor(R.color.selectedColumnShadowLayerColorExample));


        mPath = new Path();
        mPathStrelkaLeft = new Path();
        mPathStrelkaRight = new Path();
        mPaintStrelka = new Paint();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = ((Float) (animation.getAnimatedValue()))
                        .floatValue();
                for (int i = 0; i < nItem; i++) {
                    arrayListStolbValue1.add(i, (int) (value * arrayListStolbValueBuf1.get(i)));
                    if (twoGraph)
                        arrayListStolbValue2.add(i, (int) (value * arrayListStolbValueBuf2.get(i)));
                }
                invalidate();

            }
        });

        mPaintMesh = new Paint();


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

        mPaintItem2 = new Paint();

        mPaintItem2.setAntiAlias(true);

        mPaintItemSelected2 = new Paint();

        mPaintItemSelected2.setAntiAlias(true);

        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintBorder = new Paint();
        mPaintBorder.setStrokeWidth(10);


        mPaintFontAllColor = new Paint();
        mPaintFontAllColor.setAntiAlias(true);
//mPaintFontAllColor.setTextSize(12);
        mPaintFontAllColor.setTextAlign(Paint.Align.CENTER);
        mPaintFontAverage = new Paint();
        mPaintFontAverage.setAntiAlias(true);
        mPaintFontAverage.setTextSize(18);

        mPaintFontAverage.setTextAlign(Paint.Align.CENTER);
        mPaintCenterDelimeter = new Paint();
        mPaintCenterDelimeter.setAntiAlias(true);
        mPaintCenterDelimeter.setStrokeWidth(4);
        mPaintCenterDelimeter.setColor(colorCenterDelimeter);

        invalidateColor();
    }

    private void invalidateColor() {
        mPaintFontAverage.setColor(colorFontAverage);
        mPaintFontAllColor.setColor(colorFontAll);

        mPaintBorder.setColor(colorBorder);
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mPaintItemSelected.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemSelectedTop
                , colorItemSelectedBottom, Shader.TileMode.MIRROR));
Log.d("Mylog","canvasHeight="+canvasHeight);
        mPaintItem.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemTop
                , colorItemBottom, Shader.TileMode.MIRROR));
        mPaintItemSelected2.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemSelectedTop2
                , colorItemSelectedBottom2, Shader.TileMode.MIRROR));

        mPaintItem2.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemTop2
                , colorItemBottom2, Shader.TileMode.MIRROR));

        mPaintTriangle.setColor(colorTriangle);
        mPaintPunctirAverage.setColor(colorPunctireLineAverage);
        mPaintLinePunctireSelected.setColor(colorPunctireLineSelectedColor);
        mPaintLinePunctire.setColor(colorPunctirLine);
        mPaintBorderLine.setColor(colorBorderLine);


    }

    public void setDrawGraph(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2, enumTypeViewGraph typeViewGraph) {
        invalidateColor();
        if (arrayListMetodDrawGraph1 != null) {
            typeView = typeViewGraph;
            this.arrayListStolbValueBuf1 = arrayListMetodDrawGraph1;
            arrayListName.clear();
            myCalendar.set(year, month, day);
            maxValueData1 = Collections.max(arrayListMetodDrawGraph1);
            averageValueData1 = getAverageArrayList(arrayListMetodDrawGraph1);
            if (arrayListMetodDrawGraph2 != null) {
                if (arrayListMetodDrawGraph2.size() == arrayListMetodDrawGraph1.size()) {
                    this.arrayListStolbValueBuf2 = arrayListMetodDrawGraph2;
                    maxValueData2 = Collections.max(arrayListMetodDrawGraph2);
                    averageValueData2 = getAverageArrayList(arrayListMetodDrawGraph2);

                    startGorizontalGraph = (borderBottom - borderTop) / (maxValueData1 + maxValueData2) * maxValueData1 + borderTop;

                    twoGraph = true;
                }

            } else {
                twoGraph = false;
                startGorizontalGraph = borderBottom;
                maxValueData2 = 0;
                averageValueData2 = 0;
            }


            switch (typeView) {

                case MESH_MONTH_ITEM_WEEK:
                    daysInPunctArrayList.clear();
                    int days;
                    int DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
                    days = arrayListMetodDrawGraph1.size() * 7;
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
                    while (days > 0) {
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
                    if (daysInPunctArrayList.get(0) < 14) {
                        daysInPunctArrayList.set(0, daysInPunctArrayList.get(0) + 14);
                        if (twoGraph) {
                            arrayListMetodDrawGraph2.add(0, 0);
                            arrayListMetodDrawGraph2.add(0, 0);
                        }
                        arrayListMetodDrawGraph1.add(0, 0);
                        arrayListMetodDrawGraph1.add(0, 0);
                    }
                    if (daysInPunctArrayList.get(daysInPunctArrayList.size() - 1) < 14) {
                        daysInPunctArrayList.set(daysInPunctArrayList.size() - 1, daysInPunctArrayList.get(daysInPunctArrayList.size() - 1) + 14);

                        arrayListMetodDrawGraph1.add(0);
                        arrayListMetodDrawGraph1.add(0);
                        if (twoGraph) {
                            arrayListMetodDrawGraph2.add(0);
                            arrayListMetodDrawGraph2.add(0);
                        }
                    }


                    break;

                case MESH_WEEK_ITEM_DAY:
                    nItemInOneMesh = 7;
                    itemBorder = 2;
                    itemRadius = 3;
                    int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (max_date == arrayListMetodDrawGraph1.size()) {

                        myCalendar.set(year, month, 1);

                        int monday = (1 - myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7;
                        if (monday == 0) monday = 7;
                        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
                            if ((i + 1) % 7 == monday) arrayListName.add(i + 1 + "");
                            else arrayListName.add("");
                        }
                        nNepolWeek = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) / 7;
                        if (monday != 1) nNepolWeek++;
                        if (myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) % 7 + 1 - monday > 0)
                            nNepolWeek++;
                        if (monday == 1) shiftPuctInValueDay = 0;
                        else shiftPuctInValueDay = (8f - monday) / 7f;
                        nPuct = nNepolWeek;
                    } else return;


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
                    for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
                        myCalendar.set(year, month, day);
                        if (day + 6 > myCalendar.getActualMaximum(Calendar.DATE)) {
                            daySunday = day + 6 - myCalendar.getActualMaximum(Calendar.DATE);
                            if (month == 11) monthSunday = 0;
                            else
                                monthSunday = month + 1;
                        } else {
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
                            Log.d("Mylog", "day=" + day + "month=" + month);
                        } else {
                            day = day + 7;
                        }
                    }
                    break;

                case MESH_DAY_ITEM_DAY:
                case MESH_MONTH_ITEM_MONTH:
                    nItemInOneMesh = 1;

                    max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                    for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
                        if (typeView == enumTypeViewGraph.MESH_DAY_ITEM_DAY) {
                            arrayListName.add(day + " " + shortMonthName[month]);

                            if (day == max_date) {
                                day = 1;

                                if (month == 11) month = 0;
                                else month++;
                                myCalendar.set(year, month, day);
                                max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                            } else day++;
                        }
                        if (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_MONTH) {
                            arrayListName.add(shortMonthName[month]);

                            if (month == 11) {
                                month = 0;


                            } else month++;
                        }
                    }
                    break;
            }

            invalidatePathStrelka();

            this.arrayListStolbName = arrayListName;

            nItem = arrayListMetodDrawGraph1.size();

            offsetX = 0;

            if ((typeView == enumTypeViewGraph.MESH_DAY_ITEM_DAY) || (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_MONTH) || typeView == enumTypeViewGraph.MESH_WEEK_ITEM_WEEK)

            {
                shiftPuctInValueDay = 0;
                itemBorder = 13;
                itemRadius = 10;
                nItemInOneMesh = 1;
                nPuct = nItem;
            }


            animator.cancel();
            nSelectedTouch = -1;
            // границы скрола
            if (nItem != 0) {
                maxX = (nItem - canvasWidht / (MIN_WIDTH_BLOCK / nItemInOneMesh)) * (MIN_WIDTH_BLOCK / nItemInOneMesh) + WIDTH_BORDER * 2;
                isScroll = !(MIN_WIDTH_BLOCK < canvasWidht / nItem);
                if (enumTypeViewGraph.MESH_WEEK_ITEM_DAY == typeView) isScroll = false;
                if (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_WEEK)
                    isScroll = !(MIN_WIDTH_BLOCK < canvasWidht / arrayListStolbValueBuf1.size() * 4);
                if (isScroll) {
                    widthBlok = MIN_WIDTH_BLOCK;

                } else {

                    widthBlok = (canvasWidht - WIDTH_BORDER * 2) * nItemInOneMesh / nItem;
                    if (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {
                        widthBlok = (canvasWidht - WIDTH_BORDER * 2) / arrayListStolbValueBuf1.size() * 4;

                    }
                }
            }
            //            шаг пунктирных линий
            step = getStep(((startGorizontalGraph - WIDTH_BORDER * 1.5f) / MIN_HEIGHT_BLOCK), maxValueData1, 1);
            nStep1 = maxValueData1 / step;
            nStep2 = maxValueData2 / step;
            workRegionGrafikHeight = (canvasHeight - WIDTH_BORDER * 1.33f - 40) / (maxValueData1 + maxValueData2);

            animator.start();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidht = getWidth();
        canvasHeight = getHeight();
        borderTop = WIDTH_BORDER / 3;
        borderBottom = getHeight() - WIDTH_BORDER;
        borderLeft = WIDTH_BORDER;
        borderRight = getWidth() - WIDTH_BORDER;

        mPaintStrelka.setStyle(Paint.Style.STROKE);
        mPaintStrelka.setAntiAlias(true);
        mPaintStrelka.setStrokeWidth(4);
        mPaintStrelka.setColor(Color.BLACK);


    }

    private boolean onLongClickListenerWork = false;

    OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (showZoom) selectedZoom.doShow(true);
            onLongClickListenerWork = true;


                if (isScroll)
                    nSelectedTouch = (int) ((X - bufX - WIDTH_BORDER) / (MIN_WIDTH_BLOCK / nItemInOneMesh));
                else
                    nSelectedTouch = (int) ((X - WIDTH_BORDER - bufX) * nItem / (getWidth() - WIDTH_BORDER * 2));

            invalidate();

            return false;
        }
    };
float X;
    float Y;
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub


        switch (motionEvent.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                X=motionEvent.getX();
                Y=motionEvent.getY();
                onLongClickListenerWork = false;
                selectedZoom.setCoordinate(motionEvent.getX(), motionEvent.getY(),nSelectedTouch);
                Log.d("Mylog", "startTime=" + motionEvent.getX() + "endTime=" + motionEvent.getY());

                showZoom = true;

                bufX = offsetX;

                bufX2 = motionEvent.getX();
                break;
            case (MotionEvent.ACTION_MOVE):
                X=motionEvent.getX();
                Y=motionEvent.getY();
                selectedZoom.setCoordinate(motionEvent.getX(), motionEvent.getY(),nSelectedTouch);


                if ((Math.abs(bufX - offsetX) > 4)) showZoom = false;


                if (!onLongClickListenerWork) {
                    offsetX = bufX - (bufX2 - motionEvent.getX());
                }

                if (onLongClickListenerWork) {
                      {
                        if (isScroll)
                            nSelectedTouch = (int) ((motionEvent.getX() - bufX - WIDTH_BORDER) / (MIN_WIDTH_BLOCK / nItemInOneMesh));
                        else
                            nSelectedTouch = (int) ((motionEvent.getX() - WIDTH_BORDER - bufX) * nItem / (getWidth() - WIDTH_BORDER * 2));

                    }


                }

                break;

            case (MotionEvent.ACTION_UP):
                showZoom = false;
                selectedZoom.doShow(false);
                if (!onLongClickListenerWork) {
                    if (Math.abs(bufX - offsetX) < 4) {
                        if (isScroll)
                            nSelectedTouch = (int) ((motionEvent.getX() - bufX - WIDTH_BORDER) / (MIN_WIDTH_BLOCK / nItemInOneMesh));
                        else
                            nSelectedTouch = (int) ((motionEvent.getX() - WIDTH_BORDER - bufX) * nItem / (getWidth() - WIDTH_BORDER * 2));

                    }

                    offsetX = bufX - (bufX2 - motionEvent.getX());
                }
//                if(selectedZoom!=null)selectedZoom.doShow(motionEvent.getX(), motionEvent.getY(), false);
                break;


        }
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;
//        Log.d("Mylog","startTime="+startTime+"endTime="+endTime);
        invalidate();

        return super.onTouchEvent(motionEvent);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (nItem != 0) {
            leftRectSelectedMesh = WIDTH_BORDER + offsetX + widthBlok / nItemInOneMesh * nSelectedTouch;
            rightRectSelectedMesh = WIDTH_BORDER + offsetX + widthBlok / nItemInOneMesh * (nSelectedTouch + 1);

            myDrawBorder(canvas);
            drawMeshAndText(canvas);
            myDrawPunctireLine(canvas, step, nStep1, averageValueData1, 0, canvasWidht, mPaintLinePunctire, true, mPaintFontAllColor);
            canvas.clipRect(borderLeft, borderTop, borderRight, borderBottom, regionREPLACE);
            myDrawSelectedMesh(canvas);
            myDrawPunctireLine(canvas, step, nStep1, averageValueData1, leftRectSelectedMesh, rightRectSelectedMesh, mPaintLinePunctireSelected, false, null);
            canvas.clipRect(borderLeft, 0, borderRight, startGorizontalGraph, regionREPLACE);
            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, false, mPaintItem);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, false, mPaintItemSelected);
            }
            myDrawTriangle(canvas);
            myDrawAverage(canvas, averageValueData1);
        }
        if (twoGraph) {
            myDrawPunctireLine(canvas, -step, nStep2, averageValueData2, 0, canvasWidht, mPaintLinePunctire, true, mPaintFontAllColor);
            canvas.clipRect(borderLeft, startGorizontalGraph, borderRight, canvasHeight, regionREPLACE);
            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, true, mPaintItem2);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, true, mPaintItemSelected2);
            }
            myDrawAverage(canvas, -averageValueData2);
            myDrawDelimeter(canvas);
        }
        if (isScroll) {
            myDrawStrelki(canvas);
        }


    }


    private void invalidatePathStrelka() {
        float buf;
        if (twoGraph) buf = startGorizontalGraph;
        else buf = borderBottom - 13;
        mPathStrelkaRight.reset();
        mPathStrelkaLeft.reset();
        mPathStrelkaRight.moveTo(borderLeft * 2 / 3, buf + 8);
        mPathStrelkaRight.lineTo(borderLeft / 3, buf);
        mPathStrelkaRight.lineTo(borderLeft * 2 / 3, buf - 8);


        mPathStrelkaLeft.moveTo(canvasWidht - borderLeft * 2 / 3, buf + 8);
        mPathStrelkaLeft.lineTo(canvasWidht - borderLeft / 3, buf);
        mPathStrelkaLeft.lineTo(canvasWidht - borderLeft * 2 / 3, buf - 8);
    }

    private void myDrawSelectedMesh(Canvas canvas) {
        if (hasSelected()) {
            if (nSelectedTouch % 2 == 0)
                mPaintSelectedColumn.setColor(colorMeshOne);
            if (nSelectedTouch % 2 == 1)
                mPaintSelectedColumn.setColor(colorMeshTwo);
            canvas.drawRect(leftRectSelectedMesh, borderTop, rightRectSelectedMesh, borderBottom, mPaintSelectedColumn);
        }
    }

    private void myDrawDelimeter(Canvas canvas) {
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight, regionREPLACE);
        myDrawHorizontalLine(canvas, startGorizontalGraph, mPaintCenterDelimeter);
    }

    private void myDrawStrelki(Canvas canvas) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionREPLACE);
        if (offsetX != minX) canvas.drawPath(mPathStrelkaRight, mPaintStrelka);
        if (offsetX != -maxX) canvas.drawPath(mPathStrelkaLeft, mPaintStrelka);
    }

    private void myDrawTriangle(Canvas canvas) {
        if (!hasSelected()) return;
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight, regionREPLACE);
        mPath.reset();
        float lr = (rightRectSelectedMesh - leftRectSelectedMesh) / 3;
        if ((typeView == enumTypeViewGraph.MESH_WEEK_ITEM_DAY) || (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_WEEK))
            lr = 0;
        if (typeView == enumTypeViewGraph.MESH_WEEK_ITEM_WEEK) {
            mPath.moveTo(leftRectSelectedMesh + lr, canvasHeight - 3);
            mPath.lineTo(rightRectSelectedMesh - lr, canvasHeight - 3);
            mPath.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, canvasHeight - WIDTH_BORDER / 3);
        } else {
            mPath.moveTo(leftRectSelectedMesh + lr, canvasHeight - WIDTH_BORDER / 3 + 5);
            mPath.lineTo(rightRectSelectedMesh - lr, canvasHeight - WIDTH_BORDER / 3 + 5);
            mPath.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, canvasHeight - WIDTH_BORDER * 2 / 3 + 5);
        }
        canvas.drawPath(mPath, mPaintTriangle);
        mPath.reset();
        mPath.moveTo(leftRectSelectedMesh + lr, WIDTH_BORDER / 3 + 3);
        mPath.lineTo(rightRectSelectedMesh - lr, WIDTH_BORDER / 3 + 3);
        mPath.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, WIDTH_BORDER * 2 / 3 + 3);

        canvas.drawPath(mPath, mPaintTriangle);


//end  треугольник снизу и сверху выделеного блока

    }

    private void drawMeshAndText(Canvas canvas) {
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight);
        //прямоугольники
        int k4 = 0, k5 = 0;
        for (int i = 0; i < nPuct; i++) {
            {
                if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
                else mPaintMesh.setColor(colorMeshTwo);

                if (typeView == enumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {

                    k5 = k4;

                    k4 = k4 + daysInPunctArrayList.get(i);
                    canvas.drawText(arrayListStolbName.get(i), WIDTH_BORDER + offsetX + widthBlok / 28 * (k5 + (k4 - k5) / 2), borderBottom + 12, mPaintFontAllColor);

                    canvas.drawRect(WIDTH_BORDER + offsetX + widthBlok / 28 * k5, borderTop, WIDTH_BORDER + offsetX + widthBlok / 28 * k4, borderBottom, mPaintMesh);

                } else {
                    canvas.drawRect(borderLeft + offsetX + widthBlok * (i - shiftPuctInValueDay), borderTop, WIDTH_BORDER + offsetX + widthBlok * (i + 1 - shiftPuctInValueDay), borderBottom, mPaintMesh);

                }
            }
        }


        //end прямоугольники
        //  текс снизу


        if (typeView != enumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {
            for (int i = 0; i < nItem; i++) {
                {
                    canvas.drawText(arrayListStolbName.get(i), WIDTH_BORDER + offsetX + widthBlok / nItemInOneMesh * (i + 1.0f / 2.0f - shiftPuctInValueDay), borderBottom + 12, mPaintFontAllColor);
                    if (typeView == enumTypeViewGraph.MESH_WEEK_ITEM_WEEK)
                        canvas.drawText(arrayListTwoName.get(i), WIDTH_BORDER + offsetX + widthBlok * (i + 1.0f / 2.0f), borderBottom + 22, mPaintFontAllColor);
                }
            }
        }


        //end  текс снизу

        //края выделеного прямоугольника
    }


    private void myDrawBorder(Canvas canvas) {
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
    }

    @NonNull
//    возвращает путь прямоугольника с закруглеными верхними углами
    private Path getPathtopRoundRectTop(float left, float top, float right, float bottom, float radius) {
        Path path = new Path();
        path.moveTo(right, bottom);
        path.lineTo(left, bottom);
        path.arcTo(new RectF(left, top, left + radius * 2, top + radius * 2), 180, 90);
        path.arcTo(new RectF(right - radius * 2, top, right, top + radius * 2), 270, 90);
        path.close();
        return path;
    }


    //    возвращает путь прямоугольника с закруглеными верхними углами
    private Path getPathtopRoundRectBottom(float left, float top, float right, float bottom, float radius) {
        Path path = new Path();
        path.moveTo(right, bottom);
        path.lineTo(left, bottom);
        path.arcTo(new RectF(left, top - radius * 2, left + radius * 2, top), 180, -90);
        path.arcTo(new RectF(right - radius * 2, top - radius * 2, right, top), 90, -90);
        path.close();
        return path;
    }

    //возврашает шаг по максимальному значению и колво линий
    int getStep(float nElement, float maxValueData, int step) {
        if ((maxValueData / (step * nElement)) > 5)
            step = getStep(nElement, maxValueData, step * 10);
        else if ((maxValueData / (step * nElement)) > 2)
            step = getStep(nElement, maxValueData, step * 5);

        else if ((maxValueData / (step * nElement)) > 1)
            step = getStep(nElement, maxValueData, step * 2);


        return step;

    }


    private void myDrawHorizontalLine(Canvas canvas, float y, Paint paint) {

        canvas.drawLine(0, y
                , canvasWidht, y, paint);


    }

    private void myDrawPunctireLine(Canvas canvas, int step, float nStep, float average, float left, float right, Paint mPaintLine, boolean showText, Paint mPaintText) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionREPLACE);

        for (int k = 1; k <= nStep; k++) {
            if (Math.abs(Math.abs(step * k) - Math.abs(average)) > Math.abs(step) / 2) {
                canvas.drawLine(left, startGorizontalGraph - workRegionGrafikHeight * step * k

                        , right, startGorizontalGraph - workRegionGrafikHeight * step * k, mPaintLine);
                if (showText)
                    canvas.drawText(step * k + "", WIDTH_BORDER / 2, startGorizontalGraph - workRegionGrafikHeight * step * k - 2, mPaintText);

            }
        }


    }

    private float getYforValue(float value) {
        return startGorizontalGraph - workRegionGrafikHeight * value;
    }

    private void myDrawAverage(Canvas canvas, float average) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionREPLACE);

        myDrawHorizontalLine(canvas, getYforValue(average), mPaintPunctirAverage);

        canvas.drawText("ср", WIDTH_BORDER / 2, getYforValue(average) - 5, mPaintFontAverage);
    }

    private void myDrawItem(Canvas canvas, int i, boolean bottom, Paint paint) {


        if (bottom) {
            canvas.drawPath(getPathtopRoundRectBottom(borderLeft + offsetX + widthBlok / nItemInOneMesh * i + itemBorder,
                    getYforValue(-arrayListStolbValue2.get(i)),
                    borderLeft + offsetX + widthBlok / nItemInOneMesh * (i + 1) - itemBorder,
                    startGorizontalGraph, itemRadius), paint);


        } else {
            canvas.drawPath(getPathtopRoundRectTop(borderLeft + offsetX + widthBlok / nItemInOneMesh * i + itemBorder,
                    getYforValue(arrayListStolbValue1.get(i)),
                    borderLeft + offsetX + widthBlok / nItemInOneMesh * (i + 1) - itemBorder,
                    startGorizontalGraph, itemRadius), paint);
        }


    }

    private int getAverageArrayList(ArrayList<Integer> arrayList) {
        int average = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            average = average + arrayList.get(i);

        }
        average = average / arrayList.size();
        return average;
    }

    private boolean hasSelected() {
//if()
        return (nSelectedTouch >=0 && nSelectedTouch<arrayListStolbValue1.size()) && (arrayListStolbValue1.get(nSelectedTouch) != 0) && (arrayListStolbValue2.get(nSelectedTouch) != 0);
    }


    //ресурсы color
    private int colorBorder;

    public void setColorCenterDelimeter(int colorCenterDelimeter) {
        this.colorCenterDelimeter = colorCenterDelimeter;
        invalidateColor();
    }

    private int colorCenterDelimeter;

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

    public void setColorItemTop2(int colorItemTop2) {
        this.colorItemTop2 = colorItemTop2;
        invalidateColor();
    }

    private int colorItemTop2;
    private int colorItemTop;

    public void setColorItemBottom(int colorItemBottom) {
        this.colorItemBottom = colorItemBottom;
        invalidateColor();
    }

    public void setColorItemBottom2(int colorItemBottom2) {
        this.colorItemBottom2 = colorItemBottom2;
        invalidateColor();
    }

    private int colorItemBottom2;
    private int colorItemBottom;

    public void setColorItemSelectedTop(int colorItemSelectedTop) {
        this.colorItemSelectedTop = colorItemSelectedTop;
        invalidateColor();
    }

    public void setColorItemSelectedTop2(int colorItemSelectedTop2) {
        this.colorItemSelectedTop2 = colorItemSelectedTop2;
        invalidateColor();
    }

    private int colorItemSelectedTop2;
    private int colorItemSelectedTop;

    public void setColorItemSelectedBottom(int colorItemSelectedBottom) {
        this.colorItemSelectedBottom = colorItemSelectedBottom;
        invalidateColor();
    }

    public void setColorItemSelectedBottom2(int colorItemSelectedBottom2) {
        this.colorItemSelectedBottom2 = colorItemSelectedBottom2;
        invalidateColor();
    }

    private int colorItemSelectedBottom2;
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


}
