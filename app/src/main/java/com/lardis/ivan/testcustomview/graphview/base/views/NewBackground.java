package com.lardis.ivan.testcustomview.graphview.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.graphview.base.BaseGraph;
import com.lardis.ivan.testcustomview.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.testcustomview.graphview.base.TypeGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.graphtypes.columng.ColumnGraph;
import com.lardis.ivan.testcustomview.graphview.graphtypes.lineg.UnoGraphView;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperLayoutClass;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by i.larin on 11.04.2016.
 */
public class NewBackground extends View implements CallbackDrawGraph {
    int w;
    int h;
    float leftStripe;
    float itemWidth;
    float topIndent;
    float belowIndent;

    private boolean isdraw = false;

    /**
     * левая граница скрола для расчета скролинга
     */
    private float minX = 0;
    /**
     * правая граница скрола для расчета скролинга
     */
    private float maxX = 50;
    /**
     * временая переменя для расчета касании
     */
    private float bufOffsetX;
    /**
     * временая переменя для расчета касании
     */
    private float bufX2 = 0;

    int itemSelectedTouch = -1;
    int meshSelectedTouch = -1;
    int specialChosen = -1;
    private boolean showZoomAndBlockInfo;
    private boolean viewZoomAndBlockInfo;

    private boolean isTouch() {
        return !(meshSelectedTouch < 0 || (labels != null && meshSelectedTouch >= labels.length));
    }

    float X;
    /**
     * координата У
     */
    float Y;
    /**
     * сдвиг по X
     */
    private float offsetX;
    private Paint mPaintSelectedColumn;
    private Paint mPaintMesh;

    /**
     * цвет пуктов фона 1
     */
    private int colorMeshOne;
    /**
     * цвет пуктов фона 2
     */
    /**
     * цвет тени выделеный блока на сетки фона
     */
    private int colorSelectedItemShadowLayer;
    private int mBackLineColor;
    private int colorMeshTwo;
    private int mTextColor;
    int mGraphLineColor;


    private AttributeSet attributeSet;

    // Rects
    RectF mStripeRectF;
    RectF mLeftRect;

    // Paints
    Paint mStripePaint;
    Paint mRectPaint;
    Paint mLinePaint;
    Paint mArrowPaint;
    TextPaint mTextPaint;
    TextPaint mGoalTextPaint;

    Path mArrowPath;



    // Layout
    float[] labelsUnderX;
    float[] labelsUnderY;
    float[] meshStopLines;
    float[] meshSizes;
    StaticLayout goalUnderStripes;
    StaticLayout[] textUnderStripes;


    BaseGraph graph;
    ModelDataGraph modelDataGraph;

    private boolean neverShowZoomInfo;
    private boolean neverShowZoom;

    // Strings
    private String[] labels;

    // Constants
    public float footerRatio = 0.1f;
    private static String graphErrorText = "You should set data with setDataGraph method";
    public static final float marginRatio = 0.025f;
    public static final float headerRatio = 0.05f;
    public static final float lineRatio = 0.01f;
    public static float textRatio = 0.62f;
    public float textBorder = 0.5f;


    public NewBackground(Context context) {
        super(context);
        init(null, 1);
    }

    public NewBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 1);
    }

    public NewBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        this.w = getWidth();
        this.h = getHeight();
        graph.setWH(w, h, w);
    }


    public void setDataGraph(ModelDataGraph modelDataGraph, TypeGraph typeGraph) {
        // Invalidate user inputs
        offsetX = 0;
        itemSelectedTouch = -1;
        meshSelectedTouch = -1;

        // Save old sizes
        int oldW = graph.getW(), oldH = graph.getH(), realW = graph.getRealW();

        // Set appropriate graph class
        switch (typeGraph) {
            case CIRCLED_UNO:
                graph = new UnoGraphView(getContext(), attributeSet, 50);
                break;

            case GraphPunct:
                graph = new ColumnGraph(getContext(), this, attributeSet);
                break;
        }

        // Check that graph supports current view type
        if (!Arrays.asList(graph.getSupportedGraphTypes()).contains(modelDataGraph.getTypeViewGraph()))
            throw new IllegalArgumentException(typeGraph
                    + " graph type doesn't support " + modelDataGraph.getTypeViewGraph() + " yet.");

        // Check that graph blockInfoData is provided
        if (graph.getUsesBlockInfo() && modelDataGraph.getArrayListForInfo() == null)
            throw new IllegalArgumentException(typeGraph + " requires blockInfoValues to be set.");

        this.neverShowZoom = !graph.getZoomPermission();
        this.neverShowZoomInfo = !graph.getUsesBlockInfo();

        // Set various graph parameters
        graph.setWH(oldW, oldH, realW);
        graph.setData(modelDataGraph);
        graph.updateOffset(0);

        this.modelDataGraph = modelDataGraph;
        ArrayList<String> labels1 = modelDataGraph.getLabels();
        labels = labels1.toArray(new String[labels1.size()]);

        graph.setCallback(this);

        requestLayout();
    }


    void init(AttributeSet attrs, int defStyle) {
        this.attributeSet = attrs;
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Myview, defStyle, 0);

        initColor(a);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        initPaints();

        setLongClickable(true);
        setOnLongClickListener(onLongClickListener);

        graph = new BaseGraph() {
            @Override
            public void setData(ModelDataGraph modelDataGraph) {

            }

            @Override
            public void updateOffset(float v) {

            }

            @Override
            public void click(int n) {

            }

            @Override
            public void setCallback(CallbackDrawGraph callbackDrawGrapg) {

            }

            @Override
            public ViewType[] getSupportedGraphTypes() {
                return new ViewType[0];
            }

            @Override
            public boolean getZoomPermission() {
                return false;
            }

            @Override
            public boolean getUsesBlockInfo() {
                return false;
            }

            @Override
            public void drawGraph(Canvas canvas) {

            }

            @Override
            public void drawOnLeftPanel(Canvas canvas) {

            }

            @Override
            public void measure() {

            }

            @Override
            public void measureWithOffset() {

            }

        };


    }

    private void initPaints() {
        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mPaintMesh = new Paint();
        mStripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(colorMeshTwo);

        mLeftRect = new RectF();
        mStripeRectF = new RectF();


        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mBackLineColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);

        mGoalTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mGoalTextPaint.setColor(mGraphLineColor);

        mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPaint.setColor(Color.BLACK);
        mArrowPaint.setStyle(Paint.Style.STROKE);
        mArrowPath = new Path();

    }

    @Override
    public void sendInvalidate() {
        invalidate();
    }

    @Override
    public void scrollTo(int scrollX) {
        if (scrollX < 0) {
            if (scrollX <= -maxX)
                offsetX = -maxX;
            else
                offsetX = scrollX;
        }
    }

    @Override
    public void updateDrawByQ(float widthBlock, int n, float offsetBorder) {
        itemWidth = widthBlock;
        leftStripe = offsetBorder;

        updateMaxX();


        // Count average text size
        float lengthSum = 0;
        for (String label : labels) {
            lengthSum += label.length();
        }
        float averageLen = lengthSum / labels.length;

        // If minimum length of text is to huge, we will increase textRatio for better layout
        if (averageLen > 10)
            textRatio = 0.9f;

        // Calculate text size for labels
        HelperLayoutClass.calculateOKTextSize(mTextPaint, textRatio * itemWidth, labels,
                belowIndent * textBorder);

        invalidate();
        requestLayout();
    }

    @Override
    public float getTextSize() {
        return mTextPaint.getTextSize();
    }

    private void initColor(TypedArray a) {
        colorMeshOne = a.getColor(
                R.styleable.Myview_meshOneColor,
                ContextCompat.getColor(getContext(), R.color.meshOneColorExample));
        colorMeshTwo = a.getColor(
                R.styleable.Myview_meshTwoColor,
                ContextCompat.getColor(getContext(), R.color.meshTwoColorExample));
        mBackLineColor = a.getInteger(R.styleable.Myview_back_line_color, Color.parseColor("#cdd1d6"));

        colorSelectedItemShadowLayer = a.getColor(
                R.styleable.Myview_selectedColumnShadowLayerColor,
                ContextCompat.getColor(getContext(), R.color.selectedColumnShadowLayerColorExample));

        mTextColor = a.getInteger(R.styleable.UnoGraphView_text_color, Color.parseColor("#2a2a2a"));
        mGraphLineColor = a.getInteger(R.styleable.UnoGraphView_graph_line_color, Color.parseColor("#a58143"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        topIndent = (int) (h * headerRatio);
        belowIndent = (int) (h * footerRatio);

        graph.measure();
        mLeftRect.set(0, 0, leftStripe, h);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private void measureText() {
        if (labels != null) {
            mGoalTextPaint.setTextSize(mTextPaint.getTextSize());

            // Calculating stop lines for mesh selected
            float sum = leftStripe + offsetX;
            if (modelDataGraph.stripesPositions.size() == 0) {
                meshStopLines = new float[labels.length];
                meshSizes = new float[labels.length];
                for (int i = 0; i < meshStopLines.length; i++) {
                    sum += itemWidth;
                    meshSizes[i] = itemWidth;
                    meshStopLines[i] = sum;
                }
            } else {
                meshStopLines = new float[modelDataGraph.stripesPositions.size()];
                meshSizes = new float[modelDataGraph.stripesPositions.size()];
                for (int i = 0; i < meshStopLines.length; i++) {
                    sum += modelDataGraph.stripesPositions.get(i) * itemWidth;
                    meshSizes[i] = modelDataGraph.stripesPositions.get(i) * itemWidth;
                    meshStopLines[i] = sum;
                }
            }

            // Precalculating data for text
            labelsUnderX = new float[labels.length];
            labelsUnderY = new float[labels.length];

            if (modelDataGraph.getTypeViewGraph() != ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH) {
                specialChosen = meshSelectedTouch;
                for (int i = 0; i < labels.length; ++i) {
                    if (meshSizes[i] - mTextPaint.measureText(labels[i]) > 0) {
                        if (i == 0) {
                            labelsUnderX[i] = leftStripe + offsetX
                                    + 0.5f * (meshSizes[i] - mTextPaint.measureText(labels[i]));
                        } else
                            labelsUnderX[i] = meshStopLines[i - 1]
                                    + 0.5f * (meshSizes[i] - mTextPaint.measureText(labels[i]));

                        labelsUnderY[i] = h - belowIndent;
                    } else {
                        labelsUnderX[i] = -1;
                        labelsUnderY[i] = -1;
                    }
                }
            } else {
                specialChosen = itemSelectedTouch;
                for (int i = 0; i < labels.length; ++i) {
                    labelsUnderX[i] = offsetX + leftStripe + itemWidth * i
                            + 0.5f * (itemWidth - mTextPaint.measureText(labels[i]));
                    labelsUnderY[i] = h - belowIndent;
                }
            }

            if (specialChosen != -1) {
                goalUnderStripes = new StaticLayout(labels[specialChosen], mGoalTextPaint,
                        (int) (textRatio * itemWidth),
                        Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
            }

            // Calculating static layouts
            textUnderStripes = new StaticLayout[labels.length];
            for (int i = 0; i < labels.length; ++i)
                textUnderStripes[i] = new StaticLayout(labels[i], mTextPaint,
                        (int) (textRatio * itemWidth),
                        Layout.Alignment.ALIGN_NORMAL, 1, 1, true);


        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (modelDataGraph != null) {
            graph.updateOffset(offsetX);
            measureText();

            // Basic background
            drawMeshAllNoSelected(canvas);
            drawRectsTopAndBelow(canvas);
            drawBorderLines(canvas);

            drawTextLabelsUnderStripes(canvas);

            if (isTouch())
                graph.click(itemSelectedTouch);

            graph.measureWithOffset();
            graph.drawGraph(canvas);
            canvas.drawRect(mLeftRect, mRectPaint);
            graph.drawOnLeftPanel(canvas);
            drawArrows(canvas);
        }

    }

    protected void drawArrows(Canvas canvas) {
        mArrowPaint.setStrokeWidth(h * lineRatio);
        if (offsetX < 0)
            drawLeftArrow(canvas, leftStripe);
        if (-offsetX + graph.getW() - leftStripe < graph.getRealW())
            drawRightArrow(canvas, w);
    }


    private void drawMeshAllNoSelected(Canvas canvas) {
        if (modelDataGraph == null)
            return;

        switch (modelDataGraph.getTypeViewGraph()) {


            case MESH_MONTH_ITEM_WEEK:

            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:

                for (int i = 0; i < modelDataGraph.stripesPositions.size(); i++) {
                    if (i % 2 == 0)
                        mPaintMesh.setColor(colorMeshOne);
                    else
                        mPaintMesh.setColor(colorMeshTwo);

                    drawMesh(canvas, i, mPaintMesh);
                }

                break;

            case MESH_WEEK_ITEM_WEEK:
            case MESH_DAY_ITEM_DAY:
            case MESH_MONTH_ITEM_MONTH:
                for (int i = 0; i < labels.length; i++) {
                    if (i % 2 == 0)
                        mPaintMesh.setColor(colorMeshOne);
                    else
                        mPaintMesh.setColor(colorMeshTwo);

                    drawMesh(canvas, i, mPaintMesh);
                }
                break;


        }

    }

//    private void drawStripes(Canvas canvas) {
//        if (labels != null) {
//            for (int i = 0; i < labels.length; i++) {
//                if (i % 2 == 0)
//                    mPaintMesh.setColor(colorMeshOne);
//                else
//                    mPaintMesh.setColor(colorMeshTwo);
//
//                drawMesh(canvas, i, mPaintMesh);
//            }
//            if (itemSelectedTouch % 2 == 0)
//                mPaintSelectedColumn.setColor(colorMeshOne);
//            if (itemSelectedTouch % 2 == 1)
//                mPaintSelectedColumn.setColor(colorMeshTwo);
//
//            if (isTouch()) drawMesh(canvas, itemSelectedTouch, new Paint());
//            if (isTouch()) drawMesh(canvas, itemSelectedTouch, mPaintSelectedColumn);
//        }
//    }

    private void drawMesh(Canvas canvas, int i, Paint paint) {
        if (i == 0)
            canvas.drawRect(leftStripe + offsetX, 0, meshStopLines[i], getHeight(), paint);
        else
            canvas.drawRect(meshStopLines[i - 1], 0, meshStopLines[i], getHeight(), paint);
    }

    protected void drawRectsTopAndBelow(Canvas canvas) {
        mStripeRectF.set(0, 0, canvas.getWidth(), topIndent);
        mStripePaint.setColor(colorMeshTwo);
        canvas.drawRect(mStripeRectF, mStripePaint);

        mStripeRectF.set(0, canvas.getHeight() - belowIndent, canvas.getWidth(), canvas.getHeight());
        mStripePaint.setColor(colorMeshTwo);
        canvas.drawRect(mStripeRectF, mStripePaint);
    }

    private void drawBorderLines(Canvas canvas) {
        canvas.drawLine(0, topIndent, canvas.getWidth(), topIndent, mLinePaint);
        canvas.drawLine(0, canvas.getHeight() - belowIndent, canvas.getWidth(),
                canvas.getHeight() - belowIndent, mLinePaint);
    }

    private void drawTextLabelsUnderStripes(Canvas canvas) {
        if (modelDataGraph.getLabels() != null) {
            ArrayList<String> labels1 = modelDataGraph.getLabels();
            String[] labels = labels1.toArray(new String[labels1.size()]);

            for (int i = 0; i < labels.length; ++i) {
                if (labelsUnderX[i] != -1) {
                    if (i == specialChosen) {
                        canvas.translate(labelsUnderX[specialChosen], labelsUnderY[specialChosen]);
                        goalUnderStripes.draw(canvas);
                        canvas.translate(-labelsUnderX[specialChosen], -labelsUnderY[specialChosen]);
                    } else {
                        canvas.translate(labelsUnderX[i], labelsUnderY[i]);
                        textUnderStripes[i].draw(canvas);
                        canvas.translate(-labelsUnderX[i], -labelsUnderY[i]);
                    }
                }
            }
        }
    }

    private void drawLeftArrow(Canvas canvas, float globalIndent) {
        float indent = marginRatio * canvas.getHeight();
        float belowIndent = footerRatio * canvas.getHeight();
        mArrowPath.reset();
        mArrowPath.moveTo(globalIndent + 3 * indent, canvas.getHeight() - belowIndent - 3 * indent);
        mArrowPath.lineTo(globalIndent + 2 * indent, canvas.getHeight() - belowIndent - 2 * indent);
        mArrowPath.lineTo(globalIndent + 3 * indent, canvas.getHeight() - belowIndent - indent);
        canvas.drawPath(mArrowPath, mArrowPaint);
    }

    private void drawRightArrow(Canvas canvas, float globalIndent) {
        float indent = marginRatio * canvas.getHeight();
        float belowIndent = footerRatio * canvas.getHeight();
        mArrowPath.reset();
        mArrowPath.moveTo(globalIndent - 3 * indent, canvas.getHeight() - belowIndent - 3 * indent);
        mArrowPath.lineTo(globalIndent - 2 * indent, canvas.getHeight() - belowIndent - 2 * indent);
        mArrowPath.lineTo(globalIndent - 3 * indent, canvas.getHeight() - belowIndent - indent);
        canvas.drawPath(mArrowPath, mArrowPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                X = motionEvent.getX();
                Y = motionEvent.getY();
                viewZoomAndBlockInfo = false;
                showZoomAndBlockInfo = true;
                bufOffsetX = offsetX;
                bufX2 = motionEvent.getX();

                updateSelectedTouch(X);
                break;
            case (MotionEvent.ACTION_MOVE):
                X = motionEvent.getX();
                Y = motionEvent.getY();

                if ((Math.abs(bufOffsetX - offsetX) > 10))
                    showZoomAndBlockInfo = false;


                if (viewZoomAndBlockInfo) {
                    workFromZoomAndBlockInfo.setCoordinate(motionEvent.getX(), motionEvent.getY(), itemSelectedTouch);

                    int nselbuf = itemSelectedTouch;
                    updateSelectedTouch(X);

                    if (nselbuf != itemSelectedTouch)
                        workFromZoomAndBlockInfo.updatePrtScn();
                } else {
                    updateOffsetX();
                }
                invalidate();
                break;
            case (MotionEvent.ACTION_UP):
                if (viewZoomAndBlockInfo) {
                    workFromZoomAndBlockInfo.hideZoomInfo();
                    workFromZoomAndBlockInfo.hideZoom();
                }

                updateOffsetX();

                if (Math.abs(offsetX - bufOffsetX) < 10) {
                    updateSelectedTouch(X);
                    updateMeshTouch(X);
                }

                invalidate();
                break;

        }
        return super.onTouchEvent(motionEvent);
    }

    private void updateOffsetX() {
        if (graph.isAnimationFinished) {
            offsetX = bufOffsetX - (bufX2 - X);
            if (offsetX < -maxX) offsetX = -maxX;
            if (offsetX > minX) offsetX = minX;
        }
    }

    private void updateMaxX() {
        if (modelDataGraph != null)
            maxX = leftStripe * 2 + itemWidth * modelDataGraph.getGraph1values().size() - w;

    }

    private void updateSelectedTouch(Float X) {
        if (graph.isAnimationFinished) {
            if (X < w - leftStripe)
                itemSelectedTouch = (int) ((X - bufOffsetX - leftStripe) / (itemWidth));

        }
    }

    private void updateMeshTouch(Float X) {
        if (graph.isAnimationFinished) {
            for (int i = 0; i < meshStopLines.length; ++i)
                if (X < meshStopLines[i]) {
                    meshSelectedTouch = i;
                    return;
                }
        }
    }


    // Zoom code goes there

    /**
     * интерефейс для работы с Zoom view и Блоком текста из графика
     */
    public interface WorkFromZoomAndBlockInfo {

        void showZoom();

        void showZoomInfo();

        void hideZoom();

        void hideZoomInfo();

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
     * слушатель долгого касания для вызова лупы и блока с текстом
     */
    OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (showZoomAndBlockInfo && workFromZoomAndBlockInfo != null) {
                workFromZoomAndBlockInfo.setCoordinate(X, Y, itemSelectedTouch);
                workFromZoomAndBlockInfo.updatePrtScn();
                viewZoomAndBlockInfo = true;

                if (!neverShowZoom)
                    workFromZoomAndBlockInfo.showZoom();

                if (!neverShowZoomInfo)
                    workFromZoomAndBlockInfo.showZoomInfo();


                updateSelectedTouch(X);
                invalidate();
            }
            return true;
        }
    };

}
