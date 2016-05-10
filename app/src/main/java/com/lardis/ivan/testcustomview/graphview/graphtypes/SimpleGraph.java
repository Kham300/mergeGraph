package com.lardis.ivan.testcustomview.graphview.graphtypes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.graphview.base.BaseGraph;
import com.lardis.ivan.testcustomview.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperLayoutClass;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;

/**
 * Created by aleksey.ivanov on 05.05.2016.
 */
public class SimpleGraph extends BaseGraph {
    // Animation
    long startTime;
    long animationDuration;
    long curTime;

    // Public data
    ArrayList<ArrayList<Integer>> values;
    ArrayList<Integer> colors;
    ArrayList<String> months;
    boolean mFillNa;


    // Paints
    Paint[] graphsPaints;
    Paint linePaint;
    Paint smallCirclePaint;
    TextPaint mBlackPaint;
    Paint[] bigCirclePaint;

    // Current state
    int microId;
    int stripeId;
    float offsetX;
    float curX;

    // Layout
    public float itemWidth;
    public float microInterval;
    public float belowIndent;
    public static float leftStripe;
    double linesMin;
    double linesMax;

    // Layout arrays
    float[][] convertedX;
    float[][] convertedY;
    float[][] convertedYDraw;
    StaticLayout highlightedText;

    // Paths
    Path[] graphPaths;

    // Constants
    public float footerRatio = 0.1f;
    public static final float headerRatio = 0f;
    public static final float borderRatio = 0.1f;
    public static final float bigCircleRatio = 0.025f;
    public static final float smallCircleRatio = 0.025f / 2;
    public final double graphRatio = (float) 1 - headerRatio - footerRatio - borderRatio;
    public static long segmentDuration = 50;
    public static int framesPerSecond = 60;
    public static final int minStripeDp = 50;

    Context mContext;

    public SimpleGraph(Context context, AttributeSet attrs) {
        this.isAnimationFinished = true;
        this.mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UnoGraphView,
                0, 0
        );

        mFillNa = a.getBoolean(R.styleable.UnoGraphView_fill_na, false);

        microId = -1;
        stripeId = -1;
        startTime = System.currentTimeMillis();
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
        // Data saving logic
        this.values = modelDataGraph.getValues();
        this.colors = modelDataGraph.getColors();
        if (values == null || colors == null)
            throw new IllegalStateException("Advanced values or colors are not set");

        this.months = modelDataGraph.getLabels();
    }

    @Override
    public void updateOffset(float v) {
        // Update your offset
        offsetX = v;
    }

    @Override
    public void click(int n) {
        // Handle click on item]
        microId = n;
    }

    @Override
    public void setCallback(CallbackDrawGraph callbackDrawGrapg) {
        super.setCallback(callbackDrawGrapg);
        measure();
    }

    @Override
    public ViewType[] getSupportedViewTypes() {
        return new ViewType[]{ViewType.MESH_DAY_ITEM_DAY,
                ViewType.MESH_WEEK_ITEM_WEEK,
                ViewType.MESH_MONTH_ITEM_MONTH,
                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
                ViewType.MESH_MONTH_ITEM_WEEK};
    }

    @Override
    public boolean getZoomPermission() {
        return true;
    }

    @Override
    public boolean getUsesBlockInfo() {
        return false;
    }

    @Override
    public boolean requestsUpperSelection() {
        return false;
    }

    @Override
    public boolean requestsLeftAndTopPanel() {
        return false;
    }

    @Override
    public float getArrowsYPosition() {
        return 0;
    }

    @Override
    public void drawGraph(Canvas canvas) {
        // Do main part of your drawings
        curTime = System.currentTimeMillis() - startTime;

        if (microId != -1) {
            drawVertLine(canvas);
        }

        drawMultiGraph(canvas);


        if (microId != -1) {
            drawCircles(canvas);
        }

        if (curTime < animationDuration) {
            callbackToBack.scrollTo((int) (-curX + w / 2));
            callbackToBack.sendInvalidate();
        } else
            isAnimationFinished = true;
    }

    private void drawMultiGraph(Canvas canvas) {
        for (int i = 0; i < values.size(); ++i)
            graphPaths[i].reset();

        for (int i = 0; i < convertedX.length; ++i) {
            for (int k = 0; k < convertedX[i].length; ++k) {
                if (k == 0)
                    graphPaths[i].moveTo(convertedX[i][k], convertedYDraw[i][k]);
                else {
                    if (curTime / segmentDuration > k - 1)
                        graphPaths[i].lineTo(convertedX[i][k], convertedYDraw[i][k]);
                    else if (curTime / segmentDuration == k - 1) {
                        float curPosX = convertedX[i][k - 1] + (convertedX[i][k] - convertedX[i][k - 1])
                                * ((float) (curTime - (k - 1) * segmentDuration) / segmentDuration);
                        float curPosY = convertedYDraw[i][k - 1] + (convertedYDraw[i][k] - convertedYDraw[i][k - 1])
                                * ((float) (curTime - (k - 1) * segmentDuration) / segmentDuration);

                        graphPaths[i].lineTo(curPosX, curPosY);

                        curX = curPosX;
                        break;
                    }
                }
            }
            canvas.drawPath(graphPaths[i], graphsPaints[i]);
        }
    }

    private void drawCircles(Canvas canvas) {
        for (int i = 0; i < values.size(); ++i) {
            canvas.drawCircle(convertedX[i][microId], convertedYDraw[i][microId], bigCircleRatio * h,
                    bigCirclePaint[i]);
            canvas.drawCircle(convertedX[i][microId], convertedYDraw[i][microId], smallCircleRatio * h,
                    smallCirclePaint);
        }
    }

    private void drawVertLine(Canvas canvas) {
        float xPos = leftStripe + microInterval + 2 * microInterval * microId;
        canvas.drawLine(xPos, 0, xPos, canvas.getHeight() - belowIndent, linePaint);
    }

    @Override
    public void drawOnLeftPanel(Canvas canvas) {
        // Draw on the left panel, which is pinned,
        // so you don't need to use offset
    }

    @Override
    public void measure() {
        if (w != 0 && h != 0 && callbackToBack != null) {
            // Your measurements go there

            // Calculating indents
            belowIndent = (int) (h * footerRatio);

            // Counting itemWidth and indents
            itemWidth = w / values.get(0).size();

            findMinAndMax();


            // If stripe is too narrow, then
            // we will increase width of graph to required minimum
            if (itemWidth < HelperLayoutClass.dpToPixels(mContext.getResources(), minStripeDp)) {
                itemWidth = (int) HelperLayoutClass.dpToPixels(mContext.getResources(), minStripeDp);
                realW = (int) (itemWidth * (months.size() + 1));
            }
            callbackToBack.updateDrawByQ(itemWidth, 0);


            if (months != null && values != null && colors != null) {

                animationDuration = segmentDuration * values.size();
                calcXAndYValues();
                calculateDrawValues();

                initPaintsAndPaths();
            }
        }
    }

    private void initPaintsAndPaths() {
        graphsPaints = new Paint[colors.size()];
        graphPaths = new Path[colors.size()];

        for (int i = 0; i < graphsPaints.length; ++i) {
            graphsPaints[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            graphsPaints[i].setColor(colors.get(i));
            graphsPaints[i].setStrokeWidth(h / 100 / 1.5f);
            graphsPaints[i].setStyle(Paint.Style.STROKE);
            graphsPaints[i].setStrokeJoin(Paint.Join.ROUND);
            graphsPaints[i].setStrokeCap(Paint.Cap.ROUND);
            graphsPaints[i].setPathEffect(new CornerPathEffect(h / 100 * 2));

            graphPaths[i] = new Path();
        }

        smallCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        smallCirclePaint.setColor(Color.WHITE);

        bigCirclePaint = new Paint[colors.size()];
        for (int i = 0; i < bigCirclePaint.length; ++i) {
            bigCirclePaint[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            bigCirclePaint[i].setColor(colors.get(i));
        }

    }

    private void calculateDrawValues() {
        if (!mFillNa) {
            convertedYDraw = convertedY;
        } else {
            convertedYDraw = new float[values.size()][];

            for (int i = 0; i < values.size(); ++i) {
                convertedYDraw[i] = new float[values.get(i).size()];

                for (int k = 0; k < values.get(i).size(); ++k) {
                    if (values.get(i).get(k) == 0) {
                        int cur_k = k;
                        if (k == 0) {
                            // find next given value
                            while (cur_k < values.get(i).size() && values.get(i).get(cur_k) == 0)
                                ++cur_k;

                            convertedYDraw[i][k] = convertedY[i][cur_k];
                        } else if (k == values.get(i).size() - 1) {
                            // find prev given value
                            while (cur_k > 0 && values.get(i).get(cur_k) == 0)
                                --cur_k;

                            convertedYDraw[i][k] = convertedY[i][cur_k];
                        } else {
                            // find next given value

                            while (cur_k < values.get(i).size() && values.get(i).get(cur_k) == 0)
                                ++cur_k;

                            if (cur_k == values.get(i).size()) {
                                convertedYDraw[i][k] = convertedYDraw[i][k - 1];
                            } else {
                                convertedYDraw[i][k] = convertedYDraw[i][k - 1]
                                        + ((convertedY[i][cur_k] - convertedYDraw[i][k - 1])
                                        / (cur_k - k + 1));

                            }
                        }
                    } else {
                        convertedYDraw[i][k] = convertedY[i][k];
                    }
                }

            }
        }
    }

    private void calcXAndYValues() {
        convertedX = new float[values.size()][];
        convertedY = new float[values.size()][];

        for (int i = 0; i < values.size(); ++i) {
            convertedX[i] = new float[values.get(i).size()];
            convertedY[i] = new float[values.get(i).size()];

            for (int k = 0; k < values.get(i).size(); ++k) {
                convertedX[i][k] = k * itemWidth;
                convertedY[i][k] = convertValuetoHeight(values.get(i).get(k), h);
            }
        }
    }

    protected void findMinAndMax() {
        if (values != null) {
            // Precalculate data for lines
            double localMax = -1;
            double localMin = -1;
            for (int i = 0; i < values.size(); ++i)
                for (int k = 0; k < values.get(i).size(); ++k) {
                    if (values.get(i).get(k) > localMax || localMax == -1)
                        localMax = values.get(i).get(k);
                    if (values.get(i).get(k) < localMin || localMin == -1)
                        localMin = values.get(i).get(k);
                }

            linesMax = localMax;

            // If we need to fill zeros, we will recount minimum
            linesMin = mFillNa ? countMinFNa(values, linesMax) : localMin;
        }
    }

    protected double countMinFNa(ArrayList<ArrayList<Integer>> valuesAndGoal, double max) {
        double min = max;
        for (int i = 0; i < valuesAndGoal.size(); ++i)
            for (int k = 0; k < valuesAndGoal.get(i).size(); ++k)
                if (valuesAndGoal.get(i).get(k) != 0 && valuesAndGoal.get(i).get(k) < min)
                    min = valuesAndGoal.get(i).get(k);

        return min;
    }

    public float convertValuetoHeight(Integer value, float canvasHeight) {
        float indentValue = (headerRatio + borderRatio) * canvasHeight;
        float scaledValue = (float) ((linesMax - value) / (linesMax - linesMin) * graphRatio * canvasHeight);
        return indentValue + scaledValue;
    }


    @Override
    public void measureWithOffset() {
        // Do your measuring with offset
        // Warning - this method is called in onDraw
    }
}
