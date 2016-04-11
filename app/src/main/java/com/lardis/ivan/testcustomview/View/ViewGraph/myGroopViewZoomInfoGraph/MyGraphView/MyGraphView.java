package com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyGraphView;

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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.helper.HelperView;
import com.lardis.ivan.testcustomview.merge.EnumTypeViewGraph;
import com.lardis.ivan.testcustomview.merge.ModelDataGraph;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * TODO: document your custom view class.
 */
public class MyGraphView extends View {

    int day;
    int month;
    int year;


    /**
     * ширина холста
     */
    private float canvasWidht;
    /**
     * высота холста
     */
    private float canvasHeight;

    /**
     * показываем view блока текста и  view лупы
     */
    private boolean showZoomAndBlockInfo = false;
    /**
     * координаты Х
     */
    float X;
    /**
     * координата У
     */
    float Y;
    /**
     * сдвиг по X
     */
    private float offsetX;

    /**
     * левая граница скрола для расчета скролинга
     */
    private float minX = 0;
    /**
     * правая граница скрола для расчета скролинга
     */
    private float maxX;
    /**
     * временая переменя для расчета касании
     */
    private float bufOffsetX;
    /**
     * временая переменя для расчета касании
     */
    private float bufX2 = 0;

    /**
     * номер выделеного пункта графика
     */
    private int nSelectedTouch = -1;


    /**
     * толщины бордера (границы)
     */
    private float widthBorder;
    /**
     * коорднаты по X границы слева
     */
    private float borderLeft;
    /**
     * коорднаты по Y границы сверху
     */
    private float borderTop;
    /**
     * коорднаты по Y границы сверху
     */
    private float borderBottom;
    /**
     * коорднаты по X границы справа
     */
    private float borderRight;

    /**
     * МИНИМАЛЬНАЯ ШИРИНА БЛОКА
     */
    private float minWidthBlock;
    /**
     * минимальное растояние между линиями
     */
    private float minHeightBetweenBlock;
    /**
     * ширина блока
     */
    private float widthBlock;

    /**
     * отступ пункта графика от блока
     */
    private float itemBorder;
    /**
     * радиус скруглений пунктов графика
     */
    private float itemRadius;

    /**
     * рабочая область для графиков (в значениях подоваемых листом)
     */
    private float workRegionGrafikHeightInValue;
    /**
     * рабочая область для графиков (в пикселях)
     */
    private float workRegionGrafikHeight;
    /**
     * максимальное растояние между блоками если понадобиться
     */
    float maxWidthBlock = -1;

    /**
     * размер текста
     */
    int textSizeAll;
    /**
     * максимальная ширина текста
     */
    float maxTextWidth;
    /**
     * максимальная высота текста
     */
    float maxTextHeight;


    /**
     * дополнительный сдвиг по оси для расчета графика недели в месяце
     */
    float shiftPuctInValueDay = 0;

    /**
     * число пунктов графика в блоке
     */
    float nItemInOneMesh = 1;
    /**
     * количество блоков
     */
    float nBlock = 0;
    /**
     * координаты по Y разделительной линий если два графика
     */
    float startGorizontalGraph = 0;


    /**
     * колочество пунктов графика
     */
    private int nItem;
    /**
     * левая сторона выделеного блока
     */
    private float leftRectSelectedMesh;
    /**
     * правая сторона выделеного блока
     */
    private float rightRectSelectedMesh;

    /**
     * ноличество графиков если если true то два false один
     */
    boolean twoGraph = false;

    /**
     * нужно скролить или график влазиет в экран
     */
    private boolean isScroll;
    /**
     * рисуем
     */
    private boolean draw = false;
    /**
     * обрабатываем касания
     */
    private boolean touch = false;


    /**
     * проверяет скролим или нет
     *
     * @return
     */
    public boolean hasScroll() {
        return isScroll;
    }


    /**
     * показываеться лупа и блок информации или нет
     */
    private boolean viewZoomAndBlockInfo = false;
    /**
     * для графика
     */

    private int step;

    //максимальное и среднее  значение пунктов
    private float maxValueData1 = 0;
    private float averageValueData1 = 0;
    private float maxValueData2 = 0;
    private float averageValueData2 = 0;

    /**
     * отношение dp к px
     */
    float rateDpToPixel = HelperView.getRateDpToPixel(getContext());


    /**
     * количество шагов для горизонтальных линий информации для 1 графика
     */
    private float nStep1;
    /**
     * количество шагов для горизонтальных линий информации для 2 графика
     */
    private float nStep2;

    /**
     * путь для треугольников
     */
    private Path mPathTriangule;

    /**
     * путь левой стрелки скрола
     */
    private Path mPathStrelkaRight;
    /**
     * путь правой стрелки скрола
     */
    private Path mPathStrelkaLeft;
    /**
     * маркер  для горизхонтальных линий статистики
     */
    private Paint mPaintLinePunctire;
    /**
     * маркер для стрелок скрола
     */
    private Paint mPaintStrelka;
    /**
     * маркер для линий границы
     */
    private Paint mPaintBorderLine;
    /**
     * маркер для линий статистики на выделеном блоке
     */
    private Paint mPaintLinePunctireSelected;
    /**
     * маркер средней линий
     */
    private Paint mPaintPunctirAverage;
    /**
     * маркер блоков фона
     */
    private Paint mPaintMesh;
    /**
     * маркер треугольников
     */
    private Paint mPaintTriangle;
    /**
     * маркер пункта графика
     */
    private Paint mPaintItem;
    /**
     * маркер выделеного пункта графика
     */
    private Paint mPaintItemSelected;
    /**
     * маркер пункта 2 графика
     */
    private Paint mPaintItem2;
    /**
     * маркер выделеного пункта  2графика
     */
    private Paint mPaintItemSelected2;

    /**
     * маркер обводки
     */
    private Paint mPaintBorder;
    /**
     * маркер для выделеного блока
     */
    private Paint mPaintSelectedColumn;
    /**
     * маркер для всего текста кроме средней
     */
    private Paint mPaintFontAllColor;
    /**
     * маркер для текста "средней"
     */
    private Paint mPaintFontAverage;
    /**
     * маркер для линий делещий графики
     */
    private Paint mPaintCenterDelimeter;


    private ArrayList<Integer> daysInPunctArrayList = new ArrayList<Integer>();
    /**
     * лист названий для   графика
     */
    private ArrayList<String> arrayListName = new ArrayList<String>();
    /**
     * лист дополнительных названий для  графика если в графике две строчки названий
     */
    private ArrayList<String> arrayListTwoName;
    /**
     * лист значений для 1 графика
     */
    private ArrayList<Integer> arrayListStolbValue1 = new ArrayList<Integer>();
    /**
     * лист значений для 1 графика для реализаций анимаций
     */
    private ArrayList<Integer> arrayListStolbValueBuf1 = new ArrayList<Integer>();
    /**
     * лист значений для 2 графика
     */
    private ArrayList<Integer> arrayListStolbValue2 = new ArrayList<Integer>();
    /**
     * лист значений для 2 графика для реализаций анимаций
     */
    private ArrayList<Integer> arrayListStolbValueBuf2 = new ArrayList<Integer>();

    /**
     * интерефейс для работы с Zoom view и Блоком текста из графика
     */
    public interface WorkFromZoomAndBlockInfo {

        void showZoomAndBlockInfo();

        void hideZoomAndBlockInfo();

        void setCoordinate(float x, float y, int nTouch);

        void updatePrtScn();


    }

    /**
     * интерефейс для работы с Zoom view и Блоком текста из графика
     */
    public void setWorkFromZoomAndBlockInfo(WorkFromZoomAndBlockInfo workFromZoomAndBlockInfo) {
        this.workFromZoomAndBlockInfo = workFromZoomAndBlockInfo;
    }

    /**
     * интерефейс для работы с Zoom view и Блоком текста из графика
     */
    WorkFromZoomAndBlockInfo workFromZoomAndBlockInfo;

    /**
     * для замены clip в графике
     */
    Region.Op regionReplace = Region.Op.REPLACE;

    private ValueAnimator animator;
    /**
     * массив первоночальных данных
     */
    String[] shortMonthName = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};

    /**
     * календарь для вычислений
     */
    Calendar myCalendar = (Calendar) Calendar.getInstance();


    /**
     * тип графика
     */
    private EnumTypeViewGraph typeGraph;


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


        mPathTriangule = new Path();
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
                arrayListStolbValue1.clear();
                if (twoGraph) arrayListStolbValue2.clear();
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

        mPaintStrelka.setStyle(Paint.Style.STROKE);
        mPaintStrelka.setAntiAlias(true);
        mPaintStrelka.setStrokeWidth(4);


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

        mPaintFontAllColor.setTextAlign(Paint.Align.CENTER);
        mPaintFontAverage = new Paint();
        mPaintFontAverage.setAntiAlias(true);


        mPaintFontAverage.setTextAlign(Paint.Align.CENTER);
        mPaintCenterDelimeter = new Paint();
        mPaintCenterDelimeter.setAntiAlias(true);
        mPaintCenterDelimeter.setStrokeWidth(4);
        mPaintCenterDelimeter.setColor(colorCenterDelimeter);

        invalidateColor();
    }

    /**
     * инициализатор разметки вытос ширины
     */
    public void initLayout() {
        twoGraph = false;

        textSizeAll = (int) getPx(10);

        mPaintFontAllColor.setTextSize(textSizeAll);
        mPaintFontAverage.setTextSize(textSizeAll);
        maxTextHeight = HelperView.getTextHeight(mPaintFontAverage);

        widthBorder = getPx(35);
        minWidthBlock = getPx(50);
        minHeightBetweenBlock = getPx(20);

        borderTop = widthBorder / 3;
        calculateBorderButtom(typeGraph);
        borderLeft = widthBorder;
        borderRight = canvasWidht - widthBorder;
        workRegionGrafikHeight = (borderBottom - borderTop - maxTextHeight * 5);


        calculateNItemInOneMesh();
    }

    /**
     * конвентирует dp в px
     *
     * @param dp
     * @return
     */
    private float getPx(float dp) {
        return rateDpToPixel * dp;
    }

    /**
     * обновления маркеров (вызаваеться при изменение цвета)
     */
    private void invalidateColor() {
        mPaintFontAverage.setColor(colorFontAverage);
        mPaintFontAllColor.setColor(colorFontAll);

        mPaintBorder.setColor(colorBorder);
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mPaintStrelka.setColor(Color.BLACK);

        mPaintItemSelected.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemSelectedTop
                , colorItemSelectedBottom, Shader.TileMode.MIRROR));
        mPaintItem.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemTop
                , colorItemBottom, Shader.TileMode.MIRROR));
        mPaintItemSelected2.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemSelectedTop2
                , colorItemSelectedBottom2, Shader.TileMode.MIRROR));

        mPaintItem2.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                colorItemTop2
                , colorItemBottom2, Shader.TileMode.MIRROR));
mPaintTriangle.setAntiAlias(true);
        mPaintTriangle.setColor(colorTriangle);
        mPaintPunctirAverage.setColor(colorPunctireLineAverage);
        mPaintLinePunctireSelected.setColor(colorPunctireLineSelectedColor);
        mPaintLinePunctire.setColor(colorPunctirLine);
        mPaintBorderLine.setColor(colorBorderLine);


    }

    /**
     * очистка листов при перезагрузке графика
     */
    private void clear() {
        arrayListStolbValue1.clear();
        arrayListStolbValue2.clear();
        daysInPunctArrayList.clear();

        arrayListStolbValueBuf1.clear();
if(arrayListStolbValueBuf2!=null)        arrayListStolbValueBuf2.clear();
        arrayListName.clear();
        offsetX = 0;
        nSelectedTouch = -1;
        draw = true;
        touch = true;
    }

    /**
     * метод принимаюший значения для графика и в зависимости от типа <br>
     * графика
     *

     */
    public void setGraphDataAndCalculate(ModelDataGraph dataGraph) {

        clear();
        initData(dataGraph.getDay(), dataGraph.getMonth(), dataGraph.getYear(), dataGraph.getArrayListGraph1(), dataGraph.getArrayListGraph2(), dataGraph.getTypeViewGraph());

        calculateNameForValue();
    }

    private void initData(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2, EnumTypeViewGraph typeGraph) {
        this.day=day;
        this.month=month;
        this.year=year;
        this.typeGraph = typeGraph;
        this.arrayListStolbValueBuf1 = arrayListMetodDrawGraph1;
        this.arrayListStolbValueBuf2 = arrayListMetodDrawGraph2;
    }

    /**
     *метод расчитывает для значений подписи расчитывает для них подписи с низу и подготовливет данные для onDraw
     */
    private void calculateNameForValue() {

        invalidateColor();
        if (arrayListStolbValueBuf1 != null) {


            initLayout();
            if (arrayListStolbValueBuf2 != null)
                twoGraph = arrayListStolbValueBuf2.size() == arrayListStolbValueBuf1.size();



            myCalendar.set(year, month, day);
            maxValueData1 = Collections.max(arrayListStolbValueBuf1);
            averageValueData1 = getAverageArrayList(arrayListStolbValueBuf1);
            if (twoGraph) {

                maxValueData2 = Collections.max(arrayListStolbValueBuf2);
                averageValueData2 = getAverageArrayList(arrayListStolbValueBuf2);
                startGorizontalGraph = (borderBottom - borderTop) / (maxValueData1 + maxValueData2) * maxValueData1 + borderTop;
            } else {

                startGorizontalGraph = borderBottom;
                maxValueData2 = 0;
                averageValueData2 = 0;
            }

            //            шаг пунктирных линий
            step = getStep(((startGorizontalGraph - widthBorder * 1.5f) / minHeightBetweenBlock), maxValueData1, 1);
            nStep1 = maxValueData1 / step;
            nStep2 = maxValueData2 / step;
            workRegionGrafikHeightInValue = workRegionGrafikHeight / (maxValueData1 + maxValueData2);



            switch (this.typeGraph) {

                case MESH_MONTH_ITEM_WEEK:
                    calculateValueMeshMonthItemWeek(day, month, year, arrayListStolbValueBuf1, arrayListStolbValueBuf2);
                    break;
                case MESH_WEEK_ITEM_DAY:
                    if (myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) == arrayListStolbValueBuf1.size()) {
                        calculateMeshWeekItemDay(month, year, arrayListStolbValueBuf1);
                    } else {
                        draw = false;
                        touch = false;
                    }
                    break;
                case MESH_WEEK_ITEM_WEEK:
                    calculateMeshWeekItemWeek(day, month, year, arrayListStolbValueBuf1);
                    break;
                case MESH_DAY_ITEM_DAY:
                case MESH_MONTH_ITEM_MONTH:
                    calculateMeshDayItemDayAndMeshMonthItemMonth(day, month, year, arrayListStolbValueBuf1);
                    break;
            }
            invalidatePathStrelka();
            nItem = arrayListStolbValueBuf1.size();
            if ((this.typeGraph == EnumTypeViewGraph.MESH_DAY_ITEM_DAY) || (this.typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH) || this.typeGraph == EnumTypeViewGraph.MESH_WEEK_ITEM_WEEK)
            {
                nBlock = nItem;
            }
            calculateBorderScrollAndWidthBlock();
            itemBorder = widthBlock / nItemInOneMesh / 8;
            itemRadius = widthBlock / nItemInOneMesh / 6;

            animator.cancel();
            animator.start();
        }
    }

    /**
     * просчет границ скрола и ширину блока и проверка на скролл
     */
    private void calculateBorderScrollAndWidthBlock() {
        if (nItem != 0) {
            isScroll = !(minWidthBlock < canvasWidht / nItem);
            if (EnumTypeViewGraph.MESH_WEEK_ITEM_DAY == typeGraph) isScroll = false;
            if (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK)
                isScroll = !(minWidthBlock < canvasWidht / arrayListStolbValueBuf1.size() * 4);
            if (hasScroll()) {
                widthBlock = minWidthBlock;

            } else {

                widthBlock = (canvasWidht - widthBorder * 2) * nItemInOneMesh / nItem;
                if ((maxWidthBlock < widthBlock) && (maxWidthBlock > minWidthBlock) && (typeGraph != EnumTypeViewGraph.MESH_WEEK_ITEM_DAY))
                    widthBlock = maxWidthBlock;
                if (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {
                    widthBlock = (canvasWidht - widthBorder * 2) / arrayListStolbValueBuf1.size() * 4;

                }
            }
            maxX = (nItem - canvasWidht / (widthBlock / nItemInOneMesh)) * (widthBlock / nItemInOneMesh) + widthBorder * 2;

        }
    }

    /**
     * просчитывает данные для графиков типа   MeshDayItemDay And MeshMonthItemMonth
     *
     * @param day
     * @param month
     * @param year
     * @param arrayListMetodDrawGraph1
     */
    private void calculateMeshDayItemDayAndMeshMonthItemMonth(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
            if (typeGraph == EnumTypeViewGraph.MESH_DAY_ITEM_DAY) {
                arrayListName.add(day + " " + shortMonthName[month]);

                if (day == max_date) {
                    day = 1;

                    if (month == 11) month = 0;
                    else month++;
                    myCalendar.set(year, month, day);
                    max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                } else day++;
            }
            if (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH) {
                arrayListName.add(shortMonthName[month]);

                if (month == 11) {
                    month = 0;


                } else month++;
            }
        }
    }

    /**
     * просчитывает название и даные для графиков типа   MeshWeekItemWeek
     *
     * @param day
     * @param month
     * @param year
     * @param arrayListMetodDrawGraph1
     */
    private void calculateMeshWeekItemWeek(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {
        int DAY_OF_WEEK;
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
            } else {
                day = day + 7;
            }
        }
    }

    /**
     * просчитывает название и данные для графиков типа MeshWeekItemDay
     *
     * @param month
     * @param year
     * @param arrayListMetodDrawGraph1
     */
    private void calculateMeshWeekItemDay(int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {
        myCalendar.set(year, month, 1);
        int nNepolWeek;
        int monday = (1 - myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7;



        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
            if ((i + 1) % 7 == monday) arrayListName.add(i + 1 + "");
            else arrayListName.add("");
        }

        nNepolWeek = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) / 7;
        if (monday != 1) nNepolWeek++;
        if (myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) % 7 + 1 - monday > 0)
            nNepolWeek++;
        if (monday == 0) monday = 7;
        if (monday == 1) shiftPuctInValueDay = 0;
        else shiftPuctInValueDay = (8f - monday) / 7f;
        nBlock = nNepolWeek;
    }

    /**
     * просчитывает название и данные для графиков типа MeshMonthItemWeek
     *
     * @param day
     * @param month
     * @param year
     * @param arrayListMetodDrawGraph1
     * @param arrayListMetodDrawGraph2
     */
    private void calculateValueMeshMonthItemWeek(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2) {
        int days;
        int DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
        days = arrayListMetodDrawGraph1.size() * 7;
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
        nBlock = daysInPunctArrayList.size();
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
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidht = getWidth();
        canvasHeight = getHeight();
        initLayout();
//        setGraphDataAndCalculate(day);
if(draw)        calculateNameForValue();

    }


    /**
     * слушатель долгого касания для вызова лупы и блока с текстом
     */
    OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (showZoomAndBlockInfo && workFromZoomAndBlockInfo!=null) {
                workFromZoomAndBlockInfo.setCoordinate(X, Y, nSelectedTouch);
                workFromZoomAndBlockInfo.updatePrtScn();
                workFromZoomAndBlockInfo.showZoomAndBlockInfo();
                viewZoomAndBlockInfo = true;
                updateNSelectedTouch(X);
                invalidate();
            }
            return false;
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub
if(!touch)return false;
        switch (motionEvent.getAction()) {
            case (MotionEvent.ACTION_DOWN):

                X = motionEvent.getX();
                Y = motionEvent.getY();
                viewZoomAndBlockInfo = false;
                showZoomAndBlockInfo = true;
                bufOffsetX = offsetX;
                bufX2 = motionEvent.getX();

                break;
            case (MotionEvent.ACTION_MOVE):
                X = motionEvent.getX();
                Y = motionEvent.getY();

                if ((Math.abs(bufOffsetX - offsetX) > 4)) showZoomAndBlockInfo = false;
                if (viewZoomAndBlockInfo) {

                    workFromZoomAndBlockInfo.setCoordinate(motionEvent.getX(), motionEvent.getY(), nSelectedTouch);

                    int nselbuf = nSelectedTouch;
                    updateNSelectedTouch(X);
                    if (nselbuf != nSelectedTouch) workFromZoomAndBlockInfo.updatePrtScn();


                } else {
                    updateOffsetX();
                }
                break;
            case (MotionEvent.ACTION_UP):
                if (viewZoomAndBlockInfo) {

                    workFromZoomAndBlockInfo.hideZoomAndBlockInfo();

                }
                updateOffsetX();
                if (Math.abs(offsetX - bufOffsetX) < 4) updateNSelectedTouch(X);

                break;
        }

        invalidate();
        return super.onTouchEvent(motionEvent);
    }

    /**
     * высчитывает значения отступа по Х (после касания)
     */
    private void updateOffsetX() {
        offsetX = bufOffsetX - (bufX2 - X);
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;

    }

    /**
     * высчитывает значения на какой столбец коснулись
     */
    private void updateNSelectedTouch(Float X) {
        nSelectedTouch = (int) ((X - bufOffsetX - widthBorder) / (widthBlock / nItemInOneMesh));
        if (nSelectedTouch > arrayListStolbValue1.size()) nSelectedTouch = -1;
    }


    @Override
    protected void onDraw(Canvas canvas) {
if(!draw)return;
        if (nItem != 0) {
            leftRectSelectedMesh = widthBorder + offsetX + widthBlock / nItemInOneMesh * nSelectedTouch;
            rightRectSelectedMesh = widthBorder + offsetX + widthBlock / nItemInOneMesh * (nSelectedTouch + 1);

            myDrawBorder(canvas);
            drawMeshAndText(canvas);
            myDrawPunctireLine(canvas, step, nStep1, averageValueData1, 0, canvasWidht, mPaintLinePunctire, true, mPaintFontAllColor);

            myDrawSelectedMesh(canvas);
            myDrawPunctireLine(canvas, step, nStep1, averageValueData1, leftRectSelectedMesh, rightRectSelectedMesh, mPaintLinePunctireSelected, false, null);
            canvas.clipRect(borderLeft, 0, borderRight, startGorizontalGraph, regionReplace);
            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, false,  mPaintItem);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, false, mPaintItemSelected);
            }
            myDrawTriangle(canvas);
            myDrawAverage(canvas, averageValueData1);
        }
        if (twoGraph) {
            myDrawPunctireLine(canvas, -step, nStep2, averageValueData2, 0, canvasWidht, mPaintLinePunctire, true, mPaintFontAllColor);
            canvas.clipRect(borderLeft, startGorizontalGraph, borderRight, canvasHeight, regionReplace);
            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, true,  mPaintItem2);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, true,  mPaintItemSelected2);
            }
            myDrawAverage(canvas, -averageValueData2);
            myDrawDelimeter(canvas);
        }
        if (hasScroll()) {
            myDrawStrelki(canvas);
        }


    }

    /**
     * обновление нахождение стрелок для скрола если графика  два <br> то стрелки находяться между ними <br> если один то снизу
     */
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


    /**
     * рисование выделеного блока
     *
     * @param canvas
     */
    private void myDrawSelectedMesh(Canvas canvas) {
        canvas.clipRect(borderLeft, borderTop, borderRight, borderBottom, regionReplace);
        if (hasSelected()) {
            if (nSelectedTouch % 2 == 0)
                mPaintSelectedColumn.setColor(colorMeshOne);
            if (nSelectedTouch % 2 == 1)
                mPaintSelectedColumn.setColor(colorMeshTwo);
            canvas.drawRect(leftRectSelectedMesh, borderTop, rightRectSelectedMesh, borderBottom, mPaintSelectedColumn);
        }
    }

    /**
     * рисование разделительной полосы если поступило два графика
     *
     * @param canvas
     */

    private void myDrawDelimeter(Canvas canvas) {
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight, regionReplace);
        myDrawHorizontalLine(canvas, startGorizontalGraph, mPaintCenterDelimeter);
    }

    /**
     * рисование стрелок для скрала если скролиться
     *
     * @param canvas
     */
    private void myDrawStrelki(Canvas canvas) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionReplace);
        if (offsetX != minX) canvas.drawPath(mPathStrelkaRight, mPaintStrelka);
        if (offsetX != -maxX) canvas.drawPath(mPathStrelkaLeft, mPaintStrelka);
    }

    /**
     * рисование треугольников
     *
     * @param canvas
     */
    private void myDrawTriangle(Canvas canvas) {
        if (!hasSelected()) return;
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight, regionReplace);
        mPathTriangule.reset();
        float lr = (rightRectSelectedMesh - leftRectSelectedMesh) / 3;
        if ((typeGraph == EnumTypeViewGraph.MESH_WEEK_ITEM_DAY) || (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK))
            lr = 0;
        if (typeGraph == EnumTypeViewGraph.MESH_WEEK_ITEM_WEEK) {
            mPathTriangule.moveTo(leftRectSelectedMesh + lr, borderBottom + 6 * maxTextHeight - 3);
            mPathTriangule.lineTo(rightRectSelectedMesh - lr, borderBottom + 6 * maxTextHeight - 3);
            mPathTriangule.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, borderBottom + maxTextHeight * 4);
        } else {
            mPathTriangule.moveTo(leftRectSelectedMesh + lr, borderBottom + 4 * maxTextHeight);
            mPathTriangule.lineTo(rightRectSelectedMesh - lr, borderBottom + 4 * maxTextHeight);
            mPathTriangule.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, borderBottom + maxTextHeight * 2);
        }
        canvas.drawPath(mPathTriangule, mPaintTriangle);
        mPathTriangule.reset();
        mPathTriangule.moveTo(leftRectSelectedMesh + lr, borderTop + 3);
        mPathTriangule.lineTo(rightRectSelectedMesh - lr, borderTop + 3);
        mPathTriangule.lineTo((leftRectSelectedMesh + rightRectSelectedMesh) / 2, borderTop + maxTextHeight * 2 + 3);

        canvas.drawPath(mPathTriangule, mPaintTriangle);


//end  треугольник снизу и сверху выделеного блока

    }

    /**
     * просчет нижний границы в зависимости от типа графика
     *
     * @param enumTypeViewGraph
     */
    public void calculateBorderButtom(EnumTypeViewGraph enumTypeViewGraph) {
        if (enumTypeViewGraph == enumTypeViewGraph.MESH_WEEK_ITEM_WEEK) {
            borderBottom = canvasHeight - maxTextHeight * 6 - 3;

        } else {
            borderBottom = canvasHeight - maxTextHeight * 4 - 3;


        }


    }

    /**
     * просчте количество пунктов в столбце в зависимости от типа графика
     */
    public void calculateNItemInOneMesh() {
        if (typeGraph != null)
            switch (typeGraph) {
                case MESH_MONTH_ITEM_WEEK:

                    nItemInOneMesh = 4;
                    break;
                case MESH_WEEK_ITEM_DAY:
                    nItemInOneMesh = 7;
                    break;
                case MESH_WEEK_ITEM_WEEK:
                case MESH_MONTH_ITEM_MONTH:
                case MESH_DAY_ITEM_DAY:
                    shiftPuctInValueDay = 0;
                    nItemInOneMesh = 1;
                    break;


            }


        if (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {
            nItemInOneMesh = 4;

        } else if (typeGraph == EnumTypeViewGraph.MESH_WEEK_ITEM_DAY) {
            nItemInOneMesh = 7;
        }


    }

    /**
     * рисование задней сетки и текста снизу
     *
     * @param canvas
     */
    private void drawMeshAndText(Canvas canvas) {
        canvas.clipRect(borderLeft, 0, borderRight, canvasHeight);
        //прямоугольники
        int buf_k = 0, buf_k1 = 0;
        for (int i = 0; i < nBlock; i++) {
            {
                if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
                else mPaintMesh.setColor(colorMeshTwo);

                if (typeGraph == EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {

                    buf_k1 = buf_k;

                    buf_k = buf_k + daysInPunctArrayList.get(i);
                    canvas.drawText(arrayListName.get(i), widthBorder + offsetX + widthBlock / 28 * (buf_k1 + (buf_k - buf_k1) / 2), borderBottom + maxTextHeight * 2, mPaintFontAllColor);

                    canvas.drawRect(widthBorder + offsetX + widthBlock / 28 * buf_k1, borderTop, widthBorder + offsetX + widthBlock / 28 * buf_k, borderBottom, mPaintMesh);

                } else {
                    canvas.drawRect(borderLeft + offsetX + widthBlock * (i - shiftPuctInValueDay),
                            borderTop,
                            widthBorder + offsetX + widthBlock * (i + 1 - shiftPuctInValueDay),
                            borderBottom, mPaintMesh);
                        }
            }
        }


        //end прямоугольники
        //  текс снизу


        if (typeGraph != EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK) {
            for (int i = 0; i < nItem; i++) {
                {
                    Log.d("Mylog", "i=" + i + "   arrayListName=" + arrayListName.get(i));

                    canvas.drawText(arrayListName.get(i), widthBorder + offsetX + widthBlock / nItemInOneMesh * (i + 1.0f / 2.0f - shiftPuctInValueDay), borderBottom + maxTextHeight * 2, mPaintFontAllColor);
                    if (typeGraph == EnumTypeViewGraph.MESH_WEEK_ITEM_WEEK)
                        canvas.drawText(arrayListTwoName.get(i), widthBorder + offsetX + widthBlock * (i + 1.0f / 2.0f), borderBottom + maxTextHeight * 4, mPaintFontAllColor);
                }
            }
        }


        //end  текс снизу

        //края выделеного прямоугольника
    }

    /**
     * рисование границы
     *
     * @param canvas
     */
    private void myDrawBorder(Canvas canvas) {

        canvas.drawRect(borderRight, 0, canvasWidht, canvasHeight, mPaintBorder);


        canvas.drawRect(0, 0, borderLeft, canvasHeight, mPaintBorder);

        canvas.drawRect(0, borderBottom, canvasWidht, canvasHeight, mPaintBorder);

        canvas.drawRect(0, 0, canvasWidht, borderTop, mPaintBorder);

        canvas.drawLine(0, borderTop, canvasWidht, borderTop, mPaintBorderLine);
        canvas.drawLine(0, borderBottom, canvasWidht, borderBottom, mPaintBorderLine);

        canvas.drawLine(0, 0, 0, canvasHeight, mPaintBorderLine);
        canvas.drawLine(canvasWidht, 0, canvasWidht, canvasHeight, mPaintBorderLine);

        canvas.drawLine(borderLeft, borderTop, borderLeft, borderBottom, mPaintBorderLine);
        canvas.drawLine(borderRight, borderTop, borderRight, borderBottom, mPaintBorderLine);
    }


    /**
     * возвращает путь прямоугольника с закруглеными верхними углами
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param radius
     * @return
     */
    private Path getPathtopRoundRectTop(float left, float top, float right, float bottom,
                                        float radius) {
        Path path = new Path();
        path.moveTo(right, bottom);
        path.lineTo(left, bottom);
        path.arcTo(new RectF(left, top, left + radius * 2, top + radius * 2), 180, 90);
        path.arcTo(new RectF(right - radius * 2, top, right, top + radius * 2), 270, 90);
        path.close();
        return path;
    }


    /**
     * возвращает вуть прямоугольника с закруглеными нижними углами
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param radius
     * @return
     */
    private Path getPathtopRoundRectBottom(float left, float top, float right, float bottom,
                                           float radius) {
        Path path = new Path();
        path.moveTo(right, bottom);
        path.lineTo(left, bottom);
        path.arcTo(new RectF(left, top - radius * 2, left + radius * 2, top), 180, -90);
        path.arcTo(new RectF(right - radius * 2, top - radius * 2, right, top), 90, -90);
        path.close();
        return path;
    }


    /**
     * возврашает шаг по максимальному значению и колво линий
     *
     * @param nElement     ково планируемых линий
     * @param maxValueData максимальное значение масива
     * @param step         для реализации рекурсии
     * @return
     */
    int getStep(float nElement, float maxValueData, int step) {
        if ((maxValueData / (step * nElement)) > 5)
            step = getStep(nElement, maxValueData, step * 10);
        else if ((maxValueData / (step * nElement)) > 2)
            step = getStep(nElement, maxValueData, step * 5);

        else if ((maxValueData / (step * nElement)) > 1)
            step = getStep(nElement, maxValueData, step * 2);


        return step;

    }

    /**
     * нарисовать горизонтальную линию зная высоту
     *
     * @param canvas
     * @param y
     * @param paint
     */
    private void myDrawHorizontalLine(Canvas canvas, float y, Paint paint) {

        canvas.drawLine(0, y
                , canvasWidht, y, paint);


    }

    /**
     * рисует пунктирные линии для графика
     *
     * @param canvas
     * @param step       шаг линий
     * @param nStep      количество шагов линий
     * @param average    значение средней чтобы здесь не рисовать линию
     * @param left       начало линий слева
     * @param right      конец линий справа
     * @param mPaintLine маркер линий
     * @param showText   показывать текст рядом с линиеё
     * @param mPaintText маркер линий
     */
    private void myDrawPunctireLine(Canvas canvas, int step, float nStep, float average,
                                    float left, float right, Paint mPaintLine, boolean showText, Paint mPaintText) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionReplace);

        for (int k = 1; k <= nStep; k++) {
            if (Math.abs(Math.abs(step * k) - Math.abs(average)) > Math.abs(step) / 2) {
                canvas.drawLine(left, startGorizontalGraph - workRegionGrafikHeightInValue * step * k

                        , right, startGorizontalGraph - workRegionGrafikHeightInValue * step * k, mPaintLine);
                if (showText)
                    canvas.drawText(step * k + "", borderLeft / 2, startGorizontalGraph - workRegionGrafikHeightInValue * step * k - 2, mPaintText);

            }
        }


    }

    /**
     * находит значение по оси Y по значение
     *
     * @param value
     * @return
     */
    private float getYForValue(float value) {
        return startGorizontalGraph - workRegionGrafikHeightInValue * value;
    }


    /**
     * рисует среднюю линию на графике
     *
     * @param canvas
     * @param average
     */
    private void myDrawAverage(Canvas canvas, float average) {
        canvas.clipRect(0, 0, canvasWidht, canvasHeight, regionReplace);

        myDrawHorizontalLine(canvas, getYForValue(average), mPaintPunctirAverage);

        canvas.drawText("ср", widthBorder / 2, getYForValue(average) - 5, mPaintFontAverage);
    }

    /**
     * рисукт пункту графика
     *
     * @param canvas
     * @param i              номер пункта чтобы вычислить значени
     * @param bottomTwoGraph если второй график рисуеться
     * @param paint
     */
    private void myDrawItem(Canvas canvas, int i, boolean bottomTwoGraph, Paint paint) {

if((borderLeft + offsetX + widthBlock / nItemInOneMesh * i + itemBorder)>canvasWidht)return;
if((borderLeft + offsetX + widthBlock / nItemInOneMesh * (i + 1) - itemBorder)<0)return;



        if (bottomTwoGraph) {
            canvas.drawPath(getPathtopRoundRectBottom(borderLeft + offsetX + widthBlock / nItemInOneMesh * i + itemBorder,
                    getYForValue(-arrayListStolbValue2.get(i)),
                    borderLeft + offsetX + widthBlock / nItemInOneMesh * (i + 1) - itemBorder,
                    startGorizontalGraph, itemRadius), paint);


        } else {
            canvas.drawPath(getPathtopRoundRectTop(borderLeft + offsetX + widthBlock / nItemInOneMesh * i + itemBorder,
                    getYForValue(arrayListStolbValue1.get(i)),
                    borderLeft + offsetX + widthBlock / nItemInOneMesh * (i + 1) - itemBorder,
                    startGorizontalGraph, itemRadius), paint);
        }


    }

    /**
     * возвращает среднее значение в листе
     *
     * @param arrayList
     * @return
     */
    private int getAverageArrayList(ArrayList<Integer> arrayList) {
        int average = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            average = average + arrayList.get(i);

        }
        average = average / arrayList.size();
        return average;
    }

    /**
     * проверяет может выделен блок или нет
     *
     * @return
     */
    private boolean hasSelected() {
        if (arrayListStolbValue1 == null) return false;
        if (!(nSelectedTouch >= 0 && nSelectedTouch < arrayListStolbValue1.size()))
            return false;
        if (twoGraph) {
            if (arrayListStolbValue2 == null) return false;

            return ((arrayListStolbValue1.get(nSelectedTouch) != 0) && (arrayListStolbValue2.get(nSelectedTouch) != 0));

        } else return (arrayListStolbValue1.get(nSelectedTouch) != 0);
    }


    /**
     * цвет отступов
     */
    private int colorBorder;

    public void setColorCenterDelimeter(int colorCenterDelimeter) {
        this.colorCenterDelimeter = colorCenterDelimeter;
        invalidateColor();
    }


    /**
     * цвет центральной линие появлюшейся если два графика
     */
    private int colorCenterDelimeter;

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
        invalidateColor();
    }

    public void setColorBorderLine(int colorBorderLine) {
        this.colorBorderLine = colorBorderLine;
        invalidateColor();
    }


    /**
     * цвет линий обводки (они придуют тень)
     */
    private int colorBorderLine;


    public void setColorItemTop(int colorItemTop) {
        this.colorItemTop = colorItemTop;
        invalidateColor();
    }

    public void setColorItemTop2(int colorItemTop2) {
        this.colorItemTop2 = colorItemTop2;
        invalidateColor();
    }

    /**
     * цвет   пункта графика сверху (так как градиент) (для второго графика если их два)
     */
    private int colorItemTop2;
    /**
     * цвет   пункта графика сверху (так как градиент)
     */
    private int colorItemTop;

    public void setColorItemBottom(int colorItemBottom) {
        this.colorItemBottom = colorItemBottom;
        invalidateColor();
    }

    public void setColorItemBottom2(int colorItemBottom2) {
        this.colorItemBottom2 = colorItemBottom2;
        invalidateColor();
    }

    /**
     * цвет   пункта графика снизу (так как градиент) (для второго графика если их два)
     */
    private int colorItemBottom2;

    /**
     * цвет   пункта графика снизу (так как градиент)
     */
    private int colorItemBottom;

    public void setColorItemSelectedTop(int colorItemSelectedTop) {
        this.colorItemSelectedTop = colorItemSelectedTop;
        invalidateColor();
    }

    public void setColorItemSelectedTop2(int colorItemSelectedTop2) {
        this.colorItemSelectedTop2 = colorItemSelectedTop2;
        invalidateColor();
    }

    /**
     * цвет выделеного пункта графика сверху (так как градиент) (для второго графика если их два)
     */
    private int colorItemSelectedTop2;
    /**
     * цвет выделеного пункта графика сверху (так как градиент)
     */
    private int colorItemSelectedTop;

    public void setColorItemSelectedBottom(int colorItemSelectedBottom) {
        this.colorItemSelectedBottom = colorItemSelectedBottom;
        invalidateColor();
    }

    public void setColorItemSelectedBottom2(int colorItemSelectedBottom2) {
        this.colorItemSelectedBottom2 = colorItemSelectedBottom2;
        invalidateColor();
    }

    /**
     * цвет выделеного пункта графика снизу (так как градиент) (для второго графика если их два)
     */
    private int colorItemSelectedBottom2;
    /**
     * цвет выделеного пункта графика снизу (так как градиент)
     */
    private int colorItemSelectedBottom;

    public void setColorMeshOne(int colorMeshOne) {
        this.colorMeshOne = colorMeshOne;
        invalidateColor();
    }

    /**
     * цвет пуктов фона 2
     */
    private int colorMeshOne;

    public void setColorMeshTwo(int colorMeshTwo) {
        this.colorMeshTwo = colorMeshTwo;
        invalidateColor();
    }

    /**
     * цвет пуктов фона 2
     */

    private int colorMeshTwo;

    public void setColorPunctireLineAverage(int colorPunctireLineAverage) {
        this.colorPunctireLineAverage = colorPunctireLineAverage;
        invalidateColor();
    }

    /**
     * цвет линий середины
     */

    private int colorPunctireLineAverage;

    public void setColorPunctireLineSelectedColor(int colorPunctireLineSelectedColor) {
        this.colorPunctireLineSelectedColor = colorPunctireLineSelectedColor;
        invalidateColor();
    }

    /**
     * цвет горизонтального линий у выделеного блока (отличаеться цветом эфект прозрачности)
     */
    private int colorPunctireLineSelectedColor;

    public void setColorPunctirLine(int colorPunctirLine) {
        this.colorPunctirLine = colorPunctirLine;
        invalidateColor();
    }

    /**
     * цвет линий горизонтальных
     */

    private int colorPunctirLine;

    public void setColorFontAll(int colorFontAll) {
        this.colorFontAll = colorFontAll;
        invalidateColor();
    }

    /**
     * цвет фона
     */

    private int colorFontAll;

    public void setColorFontAverage(int colorFontAverage) {
        this.colorFontAverage = colorFontAverage;
        invalidateColor();
    }

    /**
     * цвет шрифта средней линий
     */

    private int colorFontAverage;


    public void setColorTriangle(int colorTriangle) {
        this.colorTriangle = colorTriangle;
        invalidateColor();
    }

    /**
     * цвет треугольника
     */
    private int colorTriangle;

    public void setColorSelectedItemShadowLayer(int colorSelectedItemShadowLayer) {
        this.colorSelectedItemShadowLayer = colorSelectedItemShadowLayer;
        invalidateColor();
    }

    /**
     * цвет тени выделеный блока на сетки фона
     */
    private int colorSelectedItemShadowLayer;


}
