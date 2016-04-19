package com.lardis.ivan.testcustomview.View.Graph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
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

import java.util.ArrayList;

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
        return !(nSelectedTouch < 0 || (labels != null && nSelectedTouch >= labels.length));
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
    TextPaint mTextPaint;
    TextPaint mGoalTextPaint;


    // Layout
    float[] labelsUnderX;
    float[] labelsUnderY;
    StaticLayout goalUnderStripes;
    StaticLayout[] textUnderStripes;


    BaseGraph graph;
    ModelDataGraph modelDataGraph;
    String[] labels;

    // Constants
    public float footerRatio = 0.1f;
    public static final float headerRatio = 0.05f;
    public static final float textRatio = 0.62f;
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
        offsetX = 0;
        int oldW = graph.getW(), oldH = graph.getH(), realW = graph.getRealW();
        switch (typeGraph) {
            case GraphLine:
                graph = new UnoGraphView(getContext(), attributeSet, 50);
                break;

            case GraphPunct:
                graph = new GraphPunct(getContext(), this, attributeSet);
                break;
        }
        graph.setWH(oldW, oldH, realW);
        graph.setData(modelDataGraph);
        graph.updateOffset(0);

        this.modelDataGraph = modelDataGraph;
        ArrayList<String> labels1 = modelDataGraph.getLabels1();
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
            public void setCallback(CallbackDrawGraph callbackDrawGraph) {

            }

            @Override
            protected void drawGraph(Canvas canvas) {

            }

            @Override
            protected void drawTopLines(Canvas canvas) {

            }

            @Override
            protected void measure() {

            }

            @Override
            protected void measureWithOffset() {

            }
        };


    }

    private void initPaints() {
        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintMesh = new Paint();
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        mStripeRectF = new RectF();
        mStripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(colorMeshTwo);

        mLeftRect = new RectF();

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mBackLineColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);

        mGoalTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mGoalTextPaint.setColor(mGraphLineColor);

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

        // Set itemWidth
        HelperLayoutClass.calculateOKTextSize(mTextPaint, textRatio * itemWidth, labels,
                belowIndent * textBorder);

        show();

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

            // Precalculating data for text
            labelsUnderX = new float[labels.length];
            labelsUnderY = new float[labels.length];
            for (int i = 0; i < labels.length; ++i) {
                labelsUnderX[i] = offsetX + leftStripe + itemWidth * i
                        + 0.5f * (itemWidth - mTextPaint.measureText(labels[i]));
                labelsUnderY[i] = h - belowIndent;
            }

            if (nSelectedTouch != -1) {
                goalUnderStripes = new StaticLayout(labels[nSelectedTouch], mGoalTextPaint,
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
        if (!draw()) return;

        drawStripes(canvas);
        drawRectsTopAndBelow(canvas);
        drawBorderLines(canvas);

        graph.updateOffset(offsetX);
        measureText();

        drawTextLabelsUnderStripes(canvas);

        if (isTouch())
            graph.click(nSelectedTouch);

        graph.measureWithOffset();
        graph.drawGraph(canvas);
        canvas.drawRect(mLeftRect, mRectPaint);
        graph.drawTopLines(canvas);

    }

    private void drawStripes(Canvas canvas) {
        if (labels != null) {
            for (int i = 0; i < labels.length; i++) {
                if (i % 2 == 0)
                    mPaintMesh.setColor(colorMeshOne);
                else
                    mPaintMesh.setColor(colorMeshTwo);

                drawMesh(canvas, i, mPaintMesh);
            }
            if (nSelectedTouch % 2 == 0)
                mPaintSelectedColumn.setColor(colorMeshOne);
            if (nSelectedTouch % 2 == 1)
                mPaintSelectedColumn.setColor(colorMeshTwo);

            if (isTouch()) drawMesh(canvas, nSelectedTouch, new Paint());
            if (isTouch()) drawMesh(canvas, nSelectedTouch, mPaintSelectedColumn);
        }
    }

    private void drawMesh(Canvas canvas, int i, Paint paint) {
        canvas.drawRect(leftStripe + offsetX + itemWidth * (i),
                0,
                leftStripe + offsetX + itemWidth * (i + 1),
                getHeight(), paint);
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
        if (modelDataGraph.getLabels1() != null) {
            ArrayList<String> labels1 = modelDataGraph.getLabels1();
            String[] labels = labels1.toArray(new String[labels1.size()]);
            for (int i = 0; i < labels.length; ++i) {
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
                if (Math.abs(offsetX - bufOffsetX) < 10) updateNSelectedTouch(X);

                break;
        }


        invalidate();
        requestLayout();
        return true;

    }

    private void updateOffsetX() {
        if (graph.isAnimationFinished) {
            offsetX = bufOffsetX - (bufX2 - X);
            if (offsetX < -maxX) offsetX = -maxX;
            if (offsetX > minX) offsetX = minX;
        }
    }

    private void updateMaxX() {
        if (labels != null)
            maxX = leftStripe * 2 + itemWidth * labels.length - w;

    }

    private void updateNSelectedTouch(Float X) {
        if (graph.isAnimationFinished)
            nSelectedTouch = (int) ((X - bufOffsetX - leftStripe) / (itemWidth));
    }


}
