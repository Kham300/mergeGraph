package com.lardis.ivan.testcustomview.View.Graph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.View.Graph.GraphLine.HelperLayoutClass;
import com.lardis.ivan.testcustomview.View.Graph.GraphLine.UnoGraphView;
import com.lardis.ivan.testcustomview.View.Graph.GraphPunct.GraphPunct;

import java.util.List;

/**
 * Created by i.larin on 11.04.2016.
 */
public class NewBackground extends View implements CallbackDrawGraph {
    float canvasWidth;
    float canvasHeight;
    float leftStripe;
    int sizeBackgroundPunct;
    float meshWidth;

    public boolean draw() {
        return isdraw;
    }

    public void hide() {
        isdraw = false;
    }

    public void show() {
        isdraw = true;
    }


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

    int nSelectedTouch = -1;

    private boolean isTouch() {
        if (nSelectedTouch < 0 || nSelectedTouch >= sizeBackgroundPunct) return false;
        else return true;


    }

    float X;
    /**
     * координата У
     */
    float Y;
    /**
     * сдвиг по X
     */
    private float offsetX = 0;
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

    private int colorMeshTwo;

    ModelDataGraph dataGraph;


    // Below and left panel variables

    // Additional data
    int mBackLineColor;
    int mTextColor;
    int mGraphLineColor;
    boolean mFillNa;


    // Layout variables
    float topIndent;
    float belowIndent;
    float itemWidth;
    int minStripeDp;
    int mTextSize;
    double mGoal;
    double linesMin;
    double linesMax;
    float goalStart;
    float goalEnd;

    // Layout arrays
    StaticLayout goalUnderStripes;
    StaticLayout[] textUnderStripes;
    float[] labelsMeasured;
    float[] intervals;
    float[] horizontalLinesH;
    float[] labelsUnderX;
    float[] labelsUnderY;


    // Paints
    Paint mLinePaint;
    Paint mStripePaint;
    Paint mGoalPaint;
    TextPaint mTextPaint;
    Paint mArrowPaint;
    Paint mRectPaint;



    // Paths
    Path mGoalPath;
    Path mArrowPath;


    // Rects
    RectF mErrRectF;
    RectF mLeftRect;
    RectF mStripeRectF;



    // Constants
    public float footerRatio = 0.1f;
    public static final float headerRatio = 0.05f;
    public static final float borderRatio = 0.1f;
    public static final float marginRatio = 0.025f;
    public static final float lineRatio = 0.01f;
    public static final float stripLength = 5f;
    public final double graphRatio = (float) 1 - headerRatio - footerRatio - 2 * borderRatio;
    public static final float textRatio = 0.62f;
    public float textBorder = 0.5f;
    public String testText;


    private AttributeSet attributeSet;

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = getWidth();
        canvasHeight = getHeight();
        graph.setWH(w, h);
    }

    BaseGraph graph;

    public void setDataGraph(ModelDataGraph modelDataGraph, TypeGraph typeGraph) {
        int old_w = graph.getW(), old_h = graph.getH();
        switch (typeGraph) {
            case GraphLine:
                graph = new UnoGraphView(getContext(), attributeSet, mGraphLineColor, mTextSize);
                break;

            case GraphPunct:
                graph = new GraphPunct(getContext(), attributeSet);
                break;
        }
        graph.setWH(old_w, old_h);
        graph.setData(modelDataGraph);
        dataGraph = modelDataGraph;

        graph.setCallback(this);


    }


    void init(AttributeSet attrs, int defStyle) {
        this.attributeSet = attrs;
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Myview, defStyle, 0);
        initColor(a);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        initPaintsAndRects();

        graph = new BaseGraph() {
            @Override
            public void setData(ModelDataGraph modelDataGraph) {

            }

            @Override
            protected void updateOffset(float v) {

            }

            @Override
            protected void click(int n) {

            }

            @Override
            public void setCallback(CallbackDrawGraph callbackDrawGrapg) {

            }

            @Override
            protected void draw(Canvas canvas) {

            }

            @Override
            protected void sendRect(float left, float top, float right, float bottom) {

            }

            @Override
            protected void sendBlockWidth(float blockWidth) {

            }

        };


    }

    private void initPaintsAndRects() {
        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintMesh = new Paint();
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mStripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mBackLineColor);

        mStripeRectF = new RectF();
        mErrRectF = new RectF();
        mLeftRect = new RectF();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);

        mGoalPaint = new Paint();
        mGoalPaint.setAntiAlias(false);
        mGoalPaint.setStyle(Paint.Style.STROKE);
        intervals = new float[]{stripLength, stripLength};
        mGoalPaint.setPathEffect(new DashPathEffect(intervals, 0));

        mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPaint.setColor(Color.BLACK);
        mArrowPaint.setStyle(Paint.Style.STROKE);

        mGoalPath = new Path();
        mGoalPath = new Path();
        mArrowPath = new Path();

        mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPaint.setColor(Color.BLACK);
        mArrowPaint.setStyle(Paint.Style.STROKE);

        mGoalPaint.setColor(mGraphLineColor);

    }

    @Override
    public void sendPostInvalidate(long delay) {
        postInvalidateDelayed(delay);
    }

    @Override
    public void scrollTo(int scrollX) {
        offsetX = scrollX;
        invalidate();
    }


    private void initColor(TypedArray a) {
        colorMeshOne = a.getColor(
                R.styleable.Myview_meshOneColor,
                getResources().getColor(R.color.meshOneColorExample));
        colorMeshTwo = a.getColor(
                R.styleable.Myview_meshTwoColor,
                getResources().getColor(R.color.meshTwoColorExample));
        colorSelectedItemShadowLayer = a.getColor(
                R.styleable.Myview_selectedColumnShadowLayerColor,
                getResources().getColor(R.color.selectedColumnShadowLayerColorExample));
        mBackLineColor = a.getInteger(R.styleable.UnoGraphView_back_line_color,
                Color.parseColor("#cdd1d6"));
        minStripeDp = a.getInteger(R.styleable.UnoGraphView_back_line_color, 50);
        mTextColor = a.getInteger(R.styleable.UnoGraphView_text_color, Color.parseColor("#2a2a2a"));
        mFillNa = a.getBoolean(R.styleable.UnoGraphView_fill_na, false);
        mGraphLineColor = a.getInteger(R.styleable.UnoGraphView_graph_line_color, Color.parseColor("#a58143"));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (dataGraph.getLabels1() == null)
        // If data or labels are not provided
        {
            mErrRectF.set(0, 0, canvasWidth, canvasHeight);
        } else {
            // Calculating indents
            topIndent = (int) (canvasHeight * headerRatio);
            belowIndent = (int) (canvasHeight * footerRatio);

            // Counting stripeWidth and indents
            itemWidth = canvasWidth / (dataGraph.getLabels1().size() + 1);

            findMinAndMax(dataGraph);


            // If stripe is too narrow, then
            // we will increase width of graph to required minimum
            if (itemWidth < HelperLayoutClass.dpToPixels(getContext().getResources(), minStripeDp)) {
                itemWidth = (int) HelperLayoutClass.dpToPixels(getContext().getResources(), minStripeDp);
                meshWidth = itemWidth;
                canvasWidth = (int) (itemWidth * (dataGraph.getLabels1().size() + 1));
            }

            // Calculating textSize for labels under stripes labels
            HelperLayoutClass.calculateOKTextSize(mTextPaint, textRatio * meshWidth,
                    (String[]) dataGraph.getLabels1().toArray(), belowIndent * textBorder);
            mTextSize = (int) mTextPaint.getTextSize();
            leftStripe = mTextPaint.measureText(testText) + mTextSize / 2;
            itemWidth = (canvasWidth - leftStripe) / dataGraph.getLabels1().size();
            meshWidth = itemWidth;
            meshWidth = itemWidth;


            mLinePaint.setStrokeWidth(canvasHeight / 400);
            mArrowPaint.setStrokeWidth(canvasHeight * lineRatio);

            // Precalc textSizes
            labelsMeasured = new float[dataGraph.getLabels1().size()];
            for (int i = 0; i < dataGraph.getLabels1().size(); ++i) {
                labelsMeasured[i] = mTextPaint.measureText(dataGraph.getLabels1().get(i));
            }

            // Precalculating data for text
            labelsUnderX = new float[dataGraph.getLabels1().size()];
            labelsUnderY = new float[dataGraph.getLabels1().size()];
            for (int i = 0; i < dataGraph.getLabels1().size(); ++i) {
                labelsUnderX[i] = offsetX + leftStripe + meshWidth * i
                        + 0.5f * (meshWidth
                        - mTextPaint.measureText(dataGraph.getLabels1().get(i)));
                labelsUnderY[i] = canvasHeight - belowIndent;
            }

            // Calculating static layouts
            textUnderStripes = new StaticLayout[dataGraph.getLabels1().size()];
            for (int i = 0; i < dataGraph.getLabels1().size(); ++i)
                textUnderStripes[i] = new StaticLayout(dataGraph.getLabels1().get(i), mTextPaint,
                        (int) (textRatio * meshWidth),
                        Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
            mGoalPaint.setStrokeWidth(canvasHeight / 200);
        }

        // Set left stripe coordinates
        mLeftRect.set(0, 0, leftStripe, canvasHeight);
    }

    void findMinAndMax(ModelDataGraph dataGraph) {
        // Precalculate data for lines
        double localMax = -1;
        double localMin = -1;
        for (int i = 0; i < dataGraph.getArrayListGraph1().size(); ++i) {
            if (localMax == -1 || dataGraph.getArrayListGraph1().get(i) > localMax)
                localMax = dataGraph.getArrayListGraph1().get(i);
            if (localMin == -1 || localMin > dataGraph.getArrayListGraph1().get(i))
                localMin = dataGraph.getArrayListGraph1().get(i);
        }

        if (localMax < mGoal)
            localMax = mGoal;
        if (localMin > mGoal)
            localMin = mGoal;

        linesMax = localMax;

        // If we need to fill zeros, we will recount minimum
        linesMin = mFillNa ? countMinFNa(dataGraph.getArrayListGraph1(), linesMax, mGoal) : localMin;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!draw()) return;
        drawMesh(canvas);
        drawBackground(canvas);
        drawAdditionalBackground(canvas);

        graph.updateOffset(offsetX);
        if (isTouch())
            graph.click(nSelectedTouch);
        graph.draw(canvas);

        canvas.drawRect(mLeftRect, mRectPaint);
        drawHorizontalText(canvas, 0);
        drawGoalText(canvas, 0);
        drawLimitedHorizontalLines(canvas, leftStripe);
        drawGoalLineLimited(canvas, leftStripe);
        drawArrows(canvas);
    }

    private void drawMesh(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
        for (int i = 0; i < sizeBackgroundPunct; i++) {
            if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
            else mPaintMesh.setColor(colorMeshTwo);
            drawMesh(canvas, i, mPaintMesh);
        }
        if (nSelectedTouch % 2 == 0)
            mPaintSelectedColumn.setColor(colorMeshOne);
        if (nSelectedTouch % 2 == 1)
            mPaintSelectedColumn.setColor(colorMeshTwo);
        if (isTouch()) drawMesh(canvas, nSelectedTouch, new Paint());
        if (isTouch()) drawMesh(canvas, nSelectedTouch, mPaintSelectedColumn);
    }

    protected void drawBackground(Canvas canvas) {
        drawBorderLines(canvas);
        drawRectsTopAndBelow(canvas);
        drawTextLabelsUnderStripes(canvas);
    }

    protected void drawAdditionalBackground(Canvas canvas) {
        // drawHighlightedStripe(canvas);
        drawGoalLine(canvas);
        drawHorizontalLines(canvas);
    }

    protected void drawGoalLine(Canvas canvas) {
        if (mGoal != 0) {
            float value = convertValuetoHeight(mGoal, canvas.getHeight());

            // Draw line
            mGoalPath.reset();
            mGoalPath.moveTo(0, value);
            mGoalPath.lineTo(canvas.getWidth(), value);
            canvas.drawPath(mGoalPath, mGoalPaint);

            // Count constraints
            goalStart = value - 3 * mTextSize / 2;
            goalEnd = value;
        }
    }

    protected void drawGoalLineLimited(Canvas canvas, float limit) {
        if (mGoal != 0) {
            float value = convertValuetoHeight(mGoal, canvas.getHeight());

            // Draw line
            mGoalPath.reset();
            mGoalPath.moveTo(limit - leftStripe, value);
            mGoalPath.lineTo(limit, value);
            canvas.drawPath(mGoalPath, mGoalPaint);

            // Count constraints
            goalStart = value - 3 * mTextSize / 2;
            goalEnd = value;
        }
    }

    public void drawHorizontalLines(Canvas canvas) {
        for (int i = 0; i < horizontalLinesH.length; ++i) {
            if (horizontalLinesH[i] < goalStart
                    || horizontalLinesH[i] > goalEnd + 5 * mTextSize / 4) {
                canvas.drawLine(0, horizontalLinesH[i], canvas.getWidth(),
                        horizontalLinesH[i], mLinePaint);

            }
        }
    }


    protected double countMinFNa(List<Integer> valuesAndGoal, double max, double mGoal) {
        double min = max;
        for (int i = 0; i < valuesAndGoal.size(); ++i)
            if (valuesAndGoal.get(i) != 0 && valuesAndGoal.get(i) < min)
                min = valuesAndGoal.get(i);

        if (mGoal < min)
            min = mGoal;
        return min;
    }

    public float convertValuetoHeight(Double value, float canvasHeight) {
        float indentValue = (headerRatio + borderRatio) * canvasHeight;
        float scaledValue = (float) ((linesMax - value) / (linesMax - linesMin) * graphRatio * canvasHeight);
        return indentValue + scaledValue;
    }


    private void drawBorderLines(Canvas canvas) {
        canvas.drawLine(0, topIndent, canvas.getWidth(), topIndent, mLinePaint);
        canvas.drawLine(0, canvas.getHeight() - belowIndent, canvas.getWidth(),
                canvas.getHeight() - belowIndent, mLinePaint);
    }

    protected void drawRectsTopAndBelow(Canvas canvas) {
        mStripeRectF.set(0, 0, canvas.getWidth(), topIndent);
        mStripePaint.setColor(colorMeshTwo);
        canvas.drawRect(mStripeRectF, mStripePaint);

        mStripeRectF.set(0, canvas.getHeight() - belowIndent, canvas.getWidth(), canvas.getHeight());
        mStripePaint.setColor(colorMeshTwo);
        canvas.drawRect(mStripeRectF, mStripePaint);
    }


    protected void drawTextLabelsUnderStripes(Canvas canvas) {
        for (int i = 0; i < dataGraph.getArrayListGraph1().size(); ++i) {
            if (i == nSelectedTouch) {
                canvas.translate(labelsUnderX[nSelectedTouch], labelsUnderY[nSelectedTouch]);
                goalUnderStripes.draw(canvas);
                canvas.translate(-labelsUnderX[nSelectedTouch], -labelsUnderY[nSelectedTouch]);
            } else {
                canvas.translate(labelsUnderX[i], labelsUnderY[i]);
                textUnderStripes[i].draw(canvas);
                canvas.translate(-labelsUnderX[i], -labelsUnderY[i]);
            }
        }
    }


    private void drawMesh(Canvas canvas, int i, Paint paint) {
        canvas.drawRect(leftStripe + offsetX + meshWidth * (i),
                0,
                leftStripe + offsetX + meshWidth * (i + 1),
                getHeight(), paint);
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

                bufOffsetX = offsetX;
                bufX2 = motionEvent.getX();

                break;
            case (MotionEvent.ACTION_MOVE):
                X = motionEvent.getX();
                Y = motionEvent.getY();


                updateOffsetX();

                break;
            case (MotionEvent.ACTION_UP):

                updateOffsetX();
                if (Math.abs(offsetX - bufOffsetX) < 4) updateNSelectedTouch(X);

                break;
        }


        invalidate();
        return true;

    }

    private void updateOffsetX() {
        offsetX = bufOffsetX - (bufX2 - X);
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;

    }

    private void updateMaxX() {
        maxX = leftStripe * 2 + meshWidth * sizeBackgroundPunct - canvasWidth;

    }

    private void updateNSelectedTouch(Float X) {
        nSelectedTouch = (int) ((X - bufOffsetX - leftStripe) / (meshWidth));
    }

}
