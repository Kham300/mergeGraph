
package com.lardis.ivan.diagramslibrary.graphview.graphtypes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.lardis.ivan.diagramslibrary.graphview.base.BaseGraph;
import com.lardis.ivan.diagramslibrary.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.diagramslibrary.graphview.base.ViewType;
import com.lardis.ivan.diagramslibrary.graphview.helpers.HelperLayoutClass;
import com.lardis.ivan.diagramslibrary.model.ModelDataGraph;
import com.onetrak.diagramslibrary.R;

/**
 * Created by aleksey.ivanov on 21.03.2016.
 */
public class UnoGraphView extends BaseGraph {

    // Animation
    long startTime;
    long animationDuration;
    long curTime;

    // Public data
    int mBackColor1;
    int mBackColor2;
    int mTextColor;
    int mBackLineColor;
    int mGraphLineColor;
    int mDesiredWidth;
    boolean mFillNa;
    double mGoal;
    String[] labels;
    double[] values;

    // Rects
    RectF mHighRectF;

    // Paints
    Paint mGraphPaint;
    Paint mBigCirclePaint;
    Paint mSmallCirclePaint;
    Paint mGradPaint;
    Paint mHighlightStripePaint;
    Paint mHighlightPathPaint;

    // Paths
    Path mGraphPath;
    Path mGradPath;
    Path mHighlightPath;
    int stripeId;

    // Layout
    public float curX;
    public float oldCurX;

    // Layout arrays
    float[] valuesRealHeight;
    float[] circleCentresX;
    long[] timeAnim;
    float[] originalX;
    float[] originalY;

    // Constants
    public static final float bigCircleRatio = 0.025f;
    public static final float smallCircleRatio = 0.0125f;
    public static long segmentDuration = 250;

    // Paints
    Paint mStripePaint;
    TextPaint mTextPaint;
    Paint mLinePaint;
    Paint mGoalPaint;
    TextPaint mGoalTextPaint;

    float[] intervals;

    // Paths
    Path mGoalPath;


    // Layout sizes
    float goalStart;
    float goalEnd;
    float offset;
    double firstLineHeight;


    // Layout data
    float topIndent;
    float belowIndent;
    float stripeWidth;
    int mTextSize;
    float graphStrokeWidth;
    double linesMin;
    double linesMax;

    // Layout arrays
    float[] monthsMeasured;

    float[] horizontalLinesH;
    StaticLayout[] weightsTextLayout;

    // Constants
    public float footerRatio = 0.1f;
    public static float cornerStripe;
    public static final float marginRatio = 0.025f;
    public static final float headerRatio = 0.05f;
    public static final float borderRatio = 0.1f;
    public static final float stripLength = 5f;
    public static int graphStep = 10;
    public final double graphRatio = (float) 1 - headerRatio - footerRatio - 2 * borderRatio;
    public static final int minStripeDp = 50;
    public static final int preferredNumLines = 5;
    public static final float textRatio = 0.62f;

    public String testText;

    // String constants
    public String localMeasurementSystem;
    public String goalLineText = "goal";
    String[] weightsText;


    // Saved data
    Context context;

    public UnoGraphView(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UnoGraphView,
                0, 0
        );

        mBackColor1 = a.getInteger(R.styleable.UnoGraphView_back_color1, Color.parseColor("#f0f1f2"));
        mBackColor2 = a.getInteger(R.styleable.UnoGraphView_back_color2, Color.parseColor("#e7e9eb"));
        mBackLineColor = a.getInteger(R.styleable.UnoGraphView_hor_line_color, Color.parseColor("#cdd1d6"));
        mTextColor = a.getInteger(R.styleable.UnoGraphView_text_color_graph, Color.parseColor("#2a2a2a"));
        mGraphLineColor = a.getInteger(R.styleable.UnoGraphView_graph_color, Color.parseColor("#a58143"));
        mDesiredWidth = a.getInteger(R.styleable.UnoGraphView_real_width, 0);
        mFillNa = a.getBoolean(R.styleable.UnoGraphView_fill_na, false);
        a.recycle();

        this.localMeasurementSystem = context.getString(R.string.localMeasurementSystem);
        this.testText = "705 " + localMeasurementSystem;

        this.context = context;

        init();
        isAnimationFinished = false;
    }

    protected void init() {

        mGoalPath = new Path();

        initPaints();

        mHighRectF = new RectF();

        mGraphPath = new Path();
        mGoalPath = new Path();
        mGradPath = new Path();
        mHighlightPath = new Path();

        stripeId = -1;

        startTime = System.currentTimeMillis();
    }

    private void initPaints() {
        mStripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mBackLineColor);

        mGoalPaint = new Paint();
        mGoalPaint.setAntiAlias(false);
        mGoalPaint.setStyle(Paint.Style.STROKE);
        intervals = new float[]{stripLength, stripLength};
        mGoalPaint.setPathEffect(new DashPathEffect(intervals, 0));

        mGoalTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);


        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGraphPaint.setColor(mGraphLineColor);
        mGraphPaint.setDither(true);
        mGraphPaint.setStyle(Paint.Style.STROKE);
        mGraphPaint.setStrokeJoin(Paint.Join.ROUND);
        mGraphPaint.setStrokeCap(Paint.Cap.ROUND);
        mGraphPaint.setAntiAlias(true);

        mGradPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBigCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBigCirclePaint.setColor(mGraphLineColor);
        mBigCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mSmallCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSmallCirclePaint.setColor(Color.WHITE);
        mSmallCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mHighlightStripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightStripePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mHighlightPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightPathPaint.setColor(mBackLineColor);
        mHighlightPathPaint.setStyle(Paint.Style.STROKE);
        mHighlightPathPaint.setShadowLayer(10f, 0.0f, 0.0f, Color.BLACK);

        mGoalPaint.setColor(mGraphLineColor);
        mGoalTextPaint.setColor(mGraphLineColor);
    }


    public void measure() {
        // Assuming that w and h is resolved
        if (w != 0 && h != 0) {
            if (labels == null || callbackToBack == null)
            // If data or labels are not provided
            {

            } else {
                // Calculating indents
                topIndent = (int) (h * headerRatio);
                belowIndent = (int) (h * footerRatio);

                // Counting stripeWidth and indents
                stripeWidth = w / (labels.length + 1);

                findMinAndMax();


                // If stripe is too narrow, then
                // we will increase width of graph to required minimum
                if (stripeWidth < HelperLayoutClass.dpToPixels(context.getResources(), minStripeDp)) {
                    stripeWidth = (int) HelperLayoutClass.dpToPixels(context.getResources(), minStripeDp);
                    realW = (int) (stripeWidth * (labels.length + 1));
                }

                // Calculating textSize for labels under stripes labels

                mTextSize = (int) callbackToBack.getTextSize();
                mTextPaint.setTextSize(mTextSize);
                cornerStripe = mTextPaint.measureText(testText) + mTextSize / 2;
                stripeWidth = (realW - cornerStripe) / labels.length;

                // Updating realW
                realW = (int) stripeWidth * labels.length;

                // Setting line widths
                graphStrokeWidth = h / 100;
                mLinePaint.setStrokeWidth(graphStrokeWidth / 4);
                mGoalPaint.setStrokeWidth(graphStrokeWidth / 2);

                callbackToBack.updateDrawByQ(stripeWidth, cornerStripe);

                // Precalc textSizes
                monthsMeasured = new float[labels.length];
                for (int i = 0; i < labels.length; ++i) {
                    monthsMeasured[i] = mTextPaint.measureText(labels[i]);
                }
            }
        }
    }

    @Override
    public void measureWithOffset() {
        // Changing width of lines with corrections after measurement
        if (labels != null && values != null) {
            mGraphPaint.setStrokeWidth(graphStrokeWidth);
            mGradPaint.setStrokeWidth(graphStrokeWidth);

            mGradPaint.setShader(new LinearGradient(0, 0, 0, h,
                    Color.argb(160, Color.red(mGraphLineColor), Color.green(mGraphLineColor),
                            Color.blue(mGraphLineColor)),
                    Color.argb(8, Color.red(mGraphLineColor), Color.green(mGraphLineColor),
                            Color.blue(mGraphLineColor)), Shader.TileMode.MIRROR));

            // Animation
            animationDuration = segmentDuration * (labels.length - 1);

            precalculateLayoutArrays(h);
        }
    }

    private void precalculateLayoutArrays(int h) {
        // Precalculating data for circles
        // TODO refactoring needed
        float[] valuesRealHeightCount = new float[labels.length];
        float[] circleCentresXCount = new float[labels.length];
        long[] tempAnim = new long[labels.length];

        int last = 0;

        originalX = new float[labels.length];
        originalY = new float[labels.length];

        long curAnimDur = 0;
        for (int i = 0; i < values.length; ++i) {
            float valueX = offset + cornerStripe + stripeWidth * ((float) i + 0.5f);
            float valueY = convertValuetoHeight(values[i], h);

            if ((mFillNa && values[i] != 0) || !mFillNa) {
                tempAnim[last] = curAnimDur;
                circleCentresXCount[last] = valueX;
                valuesRealHeightCount[last] = valueY;
                ++last;
            }

            curAnimDur += segmentDuration;

            originalX[i] = valueX;
            originalY[i] = valueY;
        }

        valuesRealHeight = new float[last];
        circleCentresX = new float[last];
        timeAnim = new long[last];

        for (int i = 0; i < last; ++i) {
            valuesRealHeight[i] = valuesRealHeightCount[i];
            circleCentresX[i] = circleCentresXCount[i];
            timeAnim[i] = tempAnim[i];
        }


        calculateLinesHeights(h);

    }

    protected void calculateLinesHeights(int h) {
        graphStep = (int) (linesMax - linesMin) / preferredNumLines;
        firstLineHeight = (int) (linesMin + linesMin % graphStep);


        // Sorry for that shitty piece of code, I'm just lazy to make it right
        int actualNumberOfLines = 0;
        for (double curHeight = firstLineHeight; curHeight < linesMax; curHeight += graphStep)
            ++actualNumberOfLines;

        horizontalLinesH = new float[actualNumberOfLines];
        weightsText = new String[actualNumberOfLines];
        weightsTextLayout = new StaticLayout[actualNumberOfLines];


        int i = 0;
        for (double curHeight = firstLineHeight; curHeight < linesMax; curHeight += graphStep, i++) {
            horizontalLinesH[i] = convertValuetoHeight(curHeight, h);
            weightsText[i] = Integer.toString((int) curHeight) + " "
                    + localMeasurementSystem;
            weightsTextLayout[i] = new StaticLayout(weightsText[i], mTextPaint,
                    (int) (cornerStripe), Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
        }
    }

    public float convertValuetoHeight(Double value, float canvasHeight) {
        float indentValue = (headerRatio + borderRatio) * canvasHeight;
        float scaledValue = (float) ((linesMax - value) / (linesMax - linesMin) * graphRatio * canvasHeight);
        return indentValue + scaledValue;
    }


    protected void findMinAndMax() {
        // Precalculate data for lines
        double localMax = -1;
        double localMin = -1;
        for (int i = 0; i < values.length; ++i) {
            if (localMax == -1 || values[i] > localMax)
                localMax = values[i];
            if (localMin == -1 || localMin > values[i])
                localMin = values[i];
        }

        if (localMax < mGoal && mGoal != 0)
            localMax = mGoal;
        if (localMin > mGoal && mGoal != 0)
            localMin = mGoal;

        linesMax = localMax;

        // If we need to fill zeros, we will recount minimum
        linesMin = mFillNa ? countMinFNa(values, linesMax, mGoal) : localMin;
    }


    protected double countMinFNa(double[] valuesAndGoal, double max, double mGoal) {
        double min = max;
        for (int i = 0; i < valuesAndGoal.length; ++i)
            if (valuesAndGoal[i] != 0 && valuesAndGoal[i] < min)
                min = valuesAndGoal[i];

        if (mGoal < min && mGoal != 0)
            min = mGoal;
        return min;
    }

    @Override
    public void drawGraph(Canvas canvas) {
        if (labels != null && values != null) {
            drawBackground(canvas);
            drawAdditionalBackground(canvas);

            // Measure animation time
            curTime = System.currentTimeMillis() - startTime;
            drawGraphLines(canvas);
        }
    }

    @Override
    public void drawOnLeftPanel(Canvas canvas) {
        drawHorizontalText(canvas, 0);
        drawGoalText(canvas, 0);
        drawLimitedHorizontalLines(canvas, cornerStripe);
        drawGoalLineLimited(canvas, cornerStripe);

        // Hidden feature of scrolling
        if (curTime < animationDuration) {
            offset = -(realW - w / 2 - cornerStripe) * ((float) curTime / animationDuration);
            callbackToBack.scrollTo((int) offset);
            callbackToBack.sendInvalidate();
        } else
            isAnimationFinished = true;
    }


    protected void drawBackground(Canvas canvas) {
        drawGoalText(canvas);
    }



    protected void drawAdditionalBackground(Canvas canvas) {
        drawGoalLine(canvas);
        drawHorizontalLines(canvas);
    }

    protected void drawGoalText(Canvas canvas) {

    }

    private void drawHighlightedStripe(Canvas canvas) {
        if (stripeId != -1 && curTime / segmentDuration >= stripeId) {
            float xPos1 = offset + cornerStripe + stripeWidth * (stripeId - bigCircleRatio);
            float xPos2 = offset + cornerStripe + stripeWidth * (stripeId + 1 + bigCircleRatio);

            mHighlightPath.reset();
            mHighlightPath.moveTo(xPos1, topIndent);
            mHighlightPath.lineTo(xPos2, topIndent);
            mHighlightPath.lineTo(xPos2, canvas.getHeight() - belowIndent);
            mHighlightPath.lineTo(xPos1, canvas.getHeight() - belowIndent);
            mHighlightPath.close();

            canvas.drawPath(mHighlightPath, mHighlightPathPaint);

            mHighRectF.set(offset + cornerStripe + stripeWidth * stripeId, topIndent,
                    offset + cornerStripe + stripeWidth * (stripeId + 1), canvas.getHeight() - belowIndent);
            mHighlightStripePaint.setColor(Color.WHITE);

            canvas.drawRect(mHighRectF, mHighlightStripePaint);

        }
    }

    private void drawGraphLines(final Canvas canvas) {
        drawAnimatedLines(canvas);
        drawAnimatedCircles(canvas);
        drawHighligthedCirclesAndTriangles(canvas);
    }

    private void drawAnimatedLines(Canvas canvas) {
        mGraphPath.reset();
        mGradPath.reset();


        // draw lines
        for (int i = 0; i < circleCentresX.length; ++i) {
            if (i == 0) {
                mGraphPath.moveTo(circleCentresX[i], valuesRealHeight[i]);
                mGradPath.moveTo(circleCentresX[i], valuesRealHeight[i]);
            } else {
                if (curTime > timeAnim[i]) {
                    mGraphPath.lineTo(circleCentresX[i], valuesRealHeight[i]);
                    mGradPath.lineTo(circleCentresX[i], valuesRealHeight[i]);

                    if (i == circleCentresX.length - 1)
                        mGradPath.lineTo(circleCentresX[i], canvas.getHeight() - belowIndent);
                } else if (curTime > timeAnim[i - 1]) {
                    long localSegDur = timeAnim[i] - timeAnim[i - 1];
                    float curPosX = circleCentresX[i - 1] + (circleCentresX[i] - circleCentresX[i - 1])
                            * ((float) (curTime - timeAnim[i - 1]) / localSegDur);
                    float curPosY = valuesRealHeight[i - 1] + (valuesRealHeight[i] - valuesRealHeight[i - 1])
                            * ((float) (curTime - timeAnim[i - 1]) / localSegDur);

                    mGradPath.lineTo(curPosX, curPosY);
                    mGradPath.lineTo(curPosX, canvas.getHeight() - belowIndent);
                    mGraphPath.lineTo(curPosX, curPosY);

                    break;
                }
            }

        }

        mGradPath.lineTo(circleCentresX[0], canvas.getHeight() - belowIndent);
        mGradPath.close();

        canvas.drawPath(mGradPath, mGradPaint);
        canvas.drawPath(mGraphPath, mGraphPaint);
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
            mGoalPath.moveTo(limit - cornerStripe, value);
            mGoalPath.lineTo(limit, value);
            canvas.drawPath(mGoalPath, mGoalPaint);

            // Count constraints
            goalStart = value - 3 * mTextSize / 2;
            goalEnd = value;
        }
    }

    private void drawAnimatedCircles(Canvas canvas) {
        // TODO do not draw is value is null

        // draw fist one
        canvas.drawCircle(circleCentresX[0], valuesRealHeight[0],
                bigCircleRatio * canvas.getHeight(), mBigCirclePaint);
        canvas.drawCircle(circleCentresX[0], valuesRealHeight[0],
                smallCircleRatio * canvas.getHeight(), mSmallCirclePaint);

        curTime -= segmentDuration;
        for (int i = 1; i < circleCentresX.length; ++i) {
            if (curTime > timeAnim[i]) {
                canvas.drawCircle(circleCentresX[i], valuesRealHeight[i],
                        bigCircleRatio * canvas.getHeight(), mBigCirclePaint);
                canvas.drawCircle(circleCentresX[i], valuesRealHeight[i],
                        smallCircleRatio * canvas.getHeight(), mSmallCirclePaint);
            } else if (curTime > timeAnim[i - 1]) {
                long localSegDur = timeAnim[i] - timeAnim[i - 1];
                float value = ((float) (curTime - timeAnim[i - 1]) / localSegDur)
                        * smallCircleRatio * canvas.getHeight();
                canvas.drawCircle(circleCentresX[i], valuesRealHeight[i],
                        2 * value, mBigCirclePaint);
                canvas.drawCircle(circleCentresX[i], valuesRealHeight[i],
                        value, mSmallCirclePaint);
                break;
            }
        }
    }

    private void drawHighligthedCirclesAndTriangles(Canvas canvas) {
        if (stripeId != -1 && curTime / segmentDuration >= stripeId) {
            // Big circles
            canvas.drawCircle(originalX[stripeId], originalY[stripeId],
                    2 * bigCircleRatio * canvas.getHeight(), mBigCirclePaint);
            canvas.drawCircle(originalX[stripeId], originalY[stripeId],
                    2 * smallCircleRatio * canvas.getHeight(), mSmallCirclePaint);

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

    public void drawLimitedHorizontalLines(Canvas canvas, float limit) {
        for (int i = 0; i < horizontalLinesH.length; ++i) {
            if (horizontalLinesH[i] < goalStart
                    || horizontalLinesH[i] > goalEnd + 5 * mTextSize / 4) {
                canvas.drawLine(0, horizontalLinesH[i], limit,
                        horizontalLinesH[i], mLinePaint);

            }
        }
    }

    protected void drawGoalText(Canvas canvas, float indent) {
        if (mGoal != 0) {
            float value = convertValuetoHeight(mGoal, canvas.getHeight());

            // Draw text
            mGoalTextPaint.setTextSize(mTextSize);
            canvas.drawText(goalLineText,
                    indent + mTextSize / 2, value - mTextSize / 2, mGoalTextPaint);
        }
    }


    public void drawHorizontalText(Canvas canvas, float indent) {
        for (int i = 0; i < horizontalLinesH.length; ++i) {
            if (horizontalLinesH[i] < goalStart
                    || horizontalLinesH[i] > goalEnd + 5 * mTextSize / 4) {
                float xPos = indent + mTextSize / 4;
                float yPos = horizontalLinesH[i] - 5 * mTextSize / 4;
                canvas.translate(xPos, yPos);
                weightsTextLayout[i].draw(canvas);
                canvas.translate(-xPos, -yPos);
            }
        }
    }


    public int getColor() {
        return mGraphLineColor;
    }

    public void setColor(int mColor) {
        this.mGraphLineColor = mColor;

        init();
    }


    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;

        init();
    }

    public int getmDesiredWidth() {
        return mDesiredWidth;
    }

    public void setmDesiredWidth(int mDesiredWidth) {
        this.mDesiredWidth = mDesiredWidth;

        init();
    }


    public int getmGraphLineColor() {
        return mGraphLineColor;
    }

    public void setmGraphLineColor(int mGraphLineColor) {
        this.mGraphLineColor = mGraphLineColor;

        init();
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
        this.mGoal = modelDataGraph.getmGoal();

        this.values = new double[modelDataGraph.getGraph1values().size()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = modelDataGraph.getGraph1values().get(i);
        }

        this.labels = new String[modelDataGraph.getLabels().size()];
        for (int i = 0; i < labels.length; ++i) {
            labels[i] = modelDataGraph.getLabels().get(i);
        }

    }

    @Override
    public void updateOffset(float v) {
        offset = v;
    }

    @Override
    public void click(int n) {
        stripeId = n;
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
                ViewType.MESH_MONTH_ITEM_MONTH};
    }

    @Override
    public boolean getZoomPermission() {
        return false;
    }

    @Override
    public boolean getUsesBlockInfo() {
        return true;
    }

    @Override
    public boolean requestsUpperSelection() {
        return true;
    }

    @Override
    public boolean requestsLeftAndTopPanel() {
        return true;
    }

    @Override
    public float getArrowsYPosition() {
        return footerRatio * h + marginRatio * h;
    }


}