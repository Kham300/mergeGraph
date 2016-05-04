package com.lardis.ivan.testcustomview.graphview.graphtypes.columng;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.graphview.base.BaseGraph;
import com.lardis.ivan.testcustomview.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperLayoutClass;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aleksey.ivanov on 26.04.2016.
 */
public class ColumnGraph extends BaseGraph {

    int day;
    int month;
    int year;
    /**
     * сдвиг по X
     */
    private float offsetX;

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
     * для замены clip в графике
     */
    Region.Op regionReplace = Region.Op.REPLACE;

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
    float rateDpToPixel;


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
     * маркер  для горизхонтальных линий статистики
     */
    private Paint mPaintLinePunctire;

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

    private ArrayList<Float> layoutCoefs;

    /**
     * тип графика
     */
    private ViewType typeGraph;


    private ValueAnimator animator;

    public ColumnGraph(Context context, AttributeSet attrs) {
        this.isAnimationFinished = false;
        rateDpToPixel = HelperLayoutClass.getRateDpToPixel(context);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ColumnGraph, 0, 0);

        colorBorder = a.getColor(
                R.styleable.ColumnGraph_borderColor,
                ContextCompat.getColor(context, R.color.borderColorExample));
        colorCenterDelimeter = a.getColor(
                R.styleable.ColumnGraph_centerDelimeterColor,
                ContextCompat.getColor(context, R.color.colorCenterDelimeterExample));
        colorBorderLine = a.getColor(
                R.styleable.ColumnGraph_borderLineColor,
                ContextCompat.getColor(context, R.color.borderLineColorExample));


        colorItemTop2 = a.getColor(
                R.styleable.ColumnGraph_itemTopColor2,
                ContextCompat.getColor(context, R.color.itemTopColor2Example));
        colorItemBottom2 = a.getColor(
                R.styleable.ColumnGraph_itemBottomColor,
                ContextCompat.getColor(context, R.color.itemBottomColor2Example));
        colorItemSelectedTop2 = a.getColor(
                R.styleable.ColumnGraph_itemSelectedTopColor,
                ContextCompat.getColor(context, R.color.itemSelectedTopColor2Example));
        colorItemSelectedBottom2 = a.getColor(
                R.styleable.ColumnGraph_itemSelectedBottomColor,
                ContextCompat.getColor(context, R.color.itemSelectedBottomColor2Example));


        colorItemTop = a.getColor(
                R.styleable.ColumnGraph_itemTopColor,
                ContextCompat.getColor(context, R.color.itemTopColorExample));
        colorItemBottom = a.getColor(
                R.styleable.ColumnGraph_itemBottomColor,
                ContextCompat.getColor(context, R.color.itemBottomColorExample));
        colorItemSelectedTop = a.getColor(
                R.styleable.ColumnGraph_itemSelectedTopColor,
                ContextCompat.getColor(context, R.color.itemSelectedTopColorExample));
        colorItemSelectedBottom = a.getColor(
                R.styleable.ColumnGraph_itemSelectedBottomColor,
                ContextCompat.getColor(context, R.color.itemSelectedBottomColorExample));
        colorPunctireLineAverage = a.getColor(
                R.styleable.ColumnGraph_punctireLineAverageColor,
                ContextCompat.getColor(context, R.color.punctireLineAverageColorExample));
        colorPunctireLineSelectedColor = a.getColor(
                R.styleable.ColumnGraph_punctireLineSelectedColor,
                ContextCompat.getColor(context, R.color.punctireLineSelectedColorExample));
        colorPunctirLine = a.getColor(
                R.styleable.ColumnGraph_punctirLineColor,
                ContextCompat.getColor(context, R.color.punctirLineColorIntExample));

        colorFontAll = a.getColor(
                R.styleable.ColumnGraph_fontAllColor,
                ContextCompat.getColor(context, R.color.fontAllColorExample));
        colorFontAverage = a.getColor(
                R.styleable.ColumnGraph_fontAverageColor,
                ContextCompat.getColor(context, R.color.fontAverageColorExample));

        colorTriangle = a.getColor(
                R.styleable.ColumnGraph_triangleColor,
                ContextCompat.getColor(context, R.color.triangleColorExample));


        mPathTriangule = new Path();


        animator = ValueAnimator.ofFloat(0, 1);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (Float) (animation.getAnimatedValue());
                arrayListStolbValue1.clear();
                if (twoGraph) arrayListStolbValue2.clear();
                for (int i = 0; i < nItem; i++) {
                    arrayListStolbValue1.add(i, (int) (value * arrayListStolbValueBuf1.get(i)));
                    if (twoGraph)
                        arrayListStolbValue2.add(i, (int) (value * arrayListStolbValueBuf2.get(i)));
                }

                if (callbackToBack != null)
                    callbackToBack.sendInvalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimationFinished = true;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


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
        a.recycle();
    }


    /**
     * инициализатор разметки вытос ширины
     */
    public void initLayout() {
        twoGraph = false;

        textSizeAll = (int) getPx(10);

        mPaintFontAllColor.setTextSize(textSizeAll);
        mPaintFontAverage.setTextSize(textSizeAll);
        maxTextHeight = HelperLayoutClass.getTextHeight(mPaintFontAverage);

        widthBorder = getPx(35);
        minWidthBlock = getPx(50);
        minHeightBetweenBlock = getPx(20);

        borderTop = headerHeight;
        calculateBorderBottom(typeGraph);
        borderLeft = widthBorder;
        borderRight = w - widthBorder;
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
     * очистка листов при перезагрузке графика
     */
    private void clear() {
        arrayListStolbValue1.clear();
        arrayListStolbValue2.clear();
        daysInPunctArrayList.clear();

        arrayListStolbValueBuf1.clear();
        if (arrayListStolbValueBuf2 != null)
            arrayListStolbValueBuf2.clear();
        arrayListName.clear();
        offsetX = 0;
        nSelectedTouch = -1;
    }


    /**
     * обновления маркеров (вызаваеться при изменение цвета)
     */
    private void invalidateColor() {
        mPaintFontAverage.setColor(colorFontAverage);
        mPaintFontAllColor.setColor(colorFontAll);

        mPaintBorder.setColor(colorBorder);

        mPaintItemSelected.setShader(new LinearGradient(0, 0, 0, h,
                colorItemSelectedTop
                , colorItemSelectedBottom, Shader.TileMode.MIRROR));
        mPaintItem.setShader(new LinearGradient(0, 0, 0, h,
                colorItemTop
                , colorItemBottom, Shader.TileMode.MIRROR));
        mPaintItemSelected2.setShader(new LinearGradient(0, 0, 0, h,
                colorItemSelectedTop2
                , colorItemSelectedBottom2, Shader.TileMode.MIRROR));

        mPaintItem2.setShader(new LinearGradient(0, 0, 0, h,
                colorItemTop2
                , colorItemBottom2, Shader.TileMode.MIRROR));
        mPaintTriangle.setAntiAlias(true);
        mPaintTriangle.setColor(colorTriangle);
        mPaintPunctirAverage.setColor(colorPunctireLineAverage);
        mPaintLinePunctireSelected.setColor(colorPunctireLineSelectedColor);
        mPaintLinePunctire.setColor(colorPunctirLine);
        mPaintBorderLine.setColor(colorBorderLine);
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
        clear();
        initData(modelDataGraph.getDay(), modelDataGraph.getMonth(), modelDataGraph.getYear(),
                modelDataGraph.getGraph1values(), modelDataGraph.getGraph2values(),
                modelDataGraph.getTypeViewGraph(), modelDataGraph.getLabels());

        calculateNameForValue();
    }

    private void initData(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1,
                          ArrayList<Integer> arrayListMetodDrawGraph2, ViewType typeGraph,
                          ArrayList<String> labels) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.typeGraph = typeGraph;
        this.arrayListName = labels;
        this.arrayListStolbValueBuf1 = arrayListMetodDrawGraph1;
        this.arrayListStolbValueBuf2 = arrayListMetodDrawGraph2;
    }


    /**
     * метод расчитывает для значений подписи расчитывает для них подписи с низу и подготовливет данные для onDraw
     */
    private void calculateNameForValue() {

        invalidateColor();
        if (arrayListStolbValueBuf1 != null) {


            initLayout();
            if (arrayListStolbValueBuf2 != null)
                twoGraph = arrayListStolbValueBuf2.size() == arrayListStolbValueBuf1.size();


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


            nItem = arrayListStolbValueBuf1.size();
            if ((this.typeGraph == ViewType.MESH_DAY_ITEM_DAY)
                    || (this.typeGraph == ViewType.MESH_MONTH_ITEM_MONTH)
                    || this.typeGraph == ViewType.MESH_WEEK_ITEM_WEEK) {
                nBlock = nItem;
            } else
                nBlock = arrayListName.size();

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
            isScroll = !(minWidthBlock < w / nItem);
            if (ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH == typeGraph) isScroll = false;
            if (typeGraph == ViewType.MESH_MONTH_ITEM_WEEK)
                isScroll = !(minWidthBlock < w / arrayListStolbValueBuf1.size() * 4);
            if (hasScroll()) {
                widthBlock = minWidthBlock;

            } else {

                widthBlock = (w - widthBorder * 2) * nItemInOneMesh / nItem;
                if ((maxWidthBlock < widthBlock) && (maxWidthBlock > minWidthBlock)
                        && (typeGraph != ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH))
                    widthBlock = maxWidthBlock;
                if (typeGraph == ViewType.MESH_MONTH_ITEM_WEEK) {
                    widthBlock = (w - widthBorder * 2) / arrayListStolbValueBuf1.size() * 4;
                }
            }

            realW = (int) (widthBlock / nItemInOneMesh) * nItem;
        }
    }


    /**
     * просчет нижний границы в зависимости от типа графика
     *
     * @param enumTypeViewGraph
     */
    public void calculateBorderBottom(ViewType enumTypeViewGraph) {
        borderBottom = h - footerHeight;
    }

    @Override
    public void updateOffset(float v) {
        offsetX = v;
    }

    @Override
    public void click(int n) {
        nSelectedTouch = n;
    }

    @Override
    public ViewType[] getSupportedViewTypes() {
        return ViewType.getAllTypes();
    }

    @Override
    public boolean getZoomPermission() {
        return true;
    }

    @Override
    public boolean getUsesBlockInfo() {
        return true;
    }

    @Override
    public void drawGraph(Canvas canvas) {
        if (nItem != 0) {
            leftRectSelectedMesh = widthBorder + offsetX +
                    widthBlock / nItemInOneMesh * nSelectedTouch;
            rightRectSelectedMesh = widthBorder + offsetX +
                    widthBlock / nItemInOneMesh * (nSelectedTouch + 1);

            myDrawPunctireLine(canvas, step, nStep1, averageValueData1,
                    0, w, mPaintLinePunctire, true, mPaintFontAllColor);

            myDrawPunctireLine(canvas, step, nStep1, averageValueData1, leftRectSelectedMesh,
                    rightRectSelectedMesh, mPaintLinePunctireSelected, false, null);

            canvas.clipRect(borderLeft, 0, w, startGorizontalGraph, regionReplace);

            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, false, mPaintItem);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, false, mPaintItemSelected);
            }

            myDrawTriangle(canvas);
        }
        if (twoGraph) {
            myDrawPunctireLine(canvas, -step, nStep2, averageValueData2, 0,
                    w, mPaintLinePunctire, true, mPaintFontAllColor);
            canvas.clipRect(borderLeft, startGorizontalGraph,
                    w, h, regionReplace);

            for (int i = 0; i < nItem; i++) {
                myDrawItem(canvas, i, true, mPaintItem2);
            }
            if (hasSelected()) {
                myDrawItem(canvas, nSelectedTouch, true, mPaintItemSelected2);
            }
        }
        canvas.clipRect(0, 0, w, h, Region.Op.UNION);
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
        canvas.clipRect(0, 0, w, h, regionReplace);

        for (int k = 1; k <= nStep; k++) {
            if (Math.abs(Math.abs(step * k) - Math.abs(average)) > Math.abs(step) / 2) {
                canvas.drawLine(left, startGorizontalGraph - workRegionGrafikHeightInValue * step * k,
                        right, startGorizontalGraph - workRegionGrafikHeightInValue * step * k, mPaintLine);
            }
        }
    }
    public void myDrawLimitedLineAndText(Canvas canvas, int step, float nStep, float average,
                                         float left, float right, Paint mPaintLine, boolean showText, Paint mPaintText) {
        canvas.clipRect(0, 0, w, h, regionReplace);

        for (int k = 1; k <= nStep; k++) {
            if (Math.abs(Math.abs(step * k) - Math.abs(average)) > Math.abs(step) / 2) {
                canvas.drawLine(left, startGorizontalGraph - workRegionGrafikHeightInValue * step * k,
                        widthBorder, startGorizontalGraph - workRegionGrafikHeightInValue * step * k, mPaintLine);
                if (showText)
                    canvas.drawText(step * k + "", borderLeft / 2,
                            startGorizontalGraph - workRegionGrafikHeightInValue * step * k - 2, mPaintText);
            }
        }
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

        if ((borderLeft + offsetX + widthBlock / nItemInOneMesh * i + itemBorder) > w) return;
        if ((borderLeft + offsetX + widthBlock / nItemInOneMesh * (i + 1) - itemBorder) < 0) return;


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
     * рисование треугольников
     *
     * @param canvas
     */
    private void myDrawTriangle(Canvas canvas) {
        if (!hasSelected()) return;
        canvas.clipRect(borderLeft, 0, w, h, regionReplace);
        mPathTriangule.reset();
        float lr = (rightRectSelectedMesh - leftRectSelectedMesh) / 3;
        if ((typeGraph == ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH)
                || (typeGraph == ViewType.MESH_MONTH_ITEM_WEEK))
            lr = 0;
        if (typeGraph == ViewType.MESH_WEEK_ITEM_WEEK) {
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
     * рисует среднюю линию на графике
     *
     * @param canvas
     * @param average
     */
    private void myDrawAverage(Canvas canvas, float average) {
        canvas.clipRect(0, 0, w, h, regionReplace);

        myDrawHorizontalLine(canvas, getYForValue(average), mPaintPunctirAverage);

        canvas.drawText("ср", widthBorder / 2, getYForValue(average) - 5, mPaintFontAverage);
    }

    /**
     * нарисовать горизонтальную линию зная высоту
     *
     * @param canvas
     * @param y
     * @param paint
     */
    private void myDrawHorizontalLine(Canvas canvas, float y, Paint paint) {
        canvas.drawLine(0, y, w, y, paint);
    }

    /**
     * рисование разделительной полосы если поступило два графика
     *
     * @param canvas
     */

    private void myDrawDelimeter(Canvas canvas) {
        canvas.clipRect(borderLeft, 0, w, h, regionReplace);
        myDrawHorizontalLine(canvas, startGorizontalGraph, mPaintCenterDelimeter);
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
     * находит значение по оси Y по значение
     *
     * @param value
     * @return
     */
    private float getYForValue(float value) {
        return startGorizontalGraph - workRegionGrafikHeightInValue * value;
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

    @Override
    public void drawOnLeftPanel(Canvas canvas) {
        if (nItem != 0) {
            myDrawAverage(canvas, averageValueData1);
            myDrawLimitedLineAndText(canvas, step, nStep1, averageValueData1,
                    0, w, mPaintLinePunctire, true, mPaintFontAllColor);

            if (twoGraph) {
                myDrawAverage(canvas, -averageValueData2);
                myDrawDelimeter(canvas);
            }
        }
    }

    @Override
    public void measure() {
        // No measure at that case
    }

    @Override
    public void measureWithOffset() {
        // No measure with offset at that case
    }

    @Override
    public void setCallback(CallbackDrawGraph callbackToBack) {
        super.setCallback(callbackToBack);
        callbackToBack.updateDrawByQ(widthBlock / nItemInOneMesh, widthBorder);
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

                case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
                    nItemInOneMesh = 7;
                    break;

                case MESH_WEEK_ITEM_WEEK:
                case MESH_MONTH_ITEM_MONTH:
                case MESH_DAY_ITEM_DAY:
                    shiftPuctInValueDay = 0;
                    nItemInOneMesh = 1;
                    break;


            }


        if (typeGraph == ViewType.MESH_MONTH_ITEM_WEEK) {
            nItemInOneMesh = 4;

        } else if (typeGraph == ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH) {
            nItemInOneMesh = 7;
        }


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

    // Colors go there
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

}
