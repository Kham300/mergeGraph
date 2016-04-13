package com.lardis.ivan.testcustomview.View.Graph.GraphLine;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.Graph.CallbackDrawGraph;
import com.lardis.ivan.testcustomview.View.Graph.BaseGraph;

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
    int mDesiredWidth;
    String[] labels;
    double[] values;
    int mGraphLineColor;


    // Rects
    RectF mHighRectF;

    // Paints
    Paint mGraphPaint;
    Paint mBigCirclePaint;
    Paint mSmallCirclePaint;
    Paint mTrianglePaint;
    Paint mGradPaint;
    Paint mHighlightStripePaint;
    Paint mHighlightPathPaint;

    // Paths
    Path mGraphPath;
    Path mGradPath;
    Path mUpperTrianglePath;
    Path mLowerTrianglePath;
    Path mHighlightPath;
    Point[] lowerTrianglePoints;
    Point[] upperTrianglePoints;
    int stripeId;

    // Layout
    float curX;
    double linesMin;
    double linesMax;

    // Layout arrays
    float[] valuesRealHeight;
    float[] circleCentresX;
    long[] timeAnim;
    float[] originalX;
    float[] originalY;

    // Constants
    public static final float bigCircleRatio = 0.025f;
    public static final float smallCircleRatio = 0.0125f;
    public static int framesPerSecond = 60;
    public static long segmentDuration = 250;



    // Paints
    Paint mErrRectPaint;
    TextPaint mErrTextPaint;
    TextPaint mGoalTextPaint;


    // Layout sizes
    float offset;
    float belowIndent;
    float topIndent;
    double firstLineHeight;


    // Layout data
    int realW;
    int mTextSize;
    float stripeWidth;
    float graphStrokeWidth;


    // Layout arrays
    StaticLayout[] weightsTextLayout;

    // Constants
    public static float leftStripe;
    public static int graphStep = 10;
    public static final int minStripeDp = 50;
    public static final int preferredNumLines = 5;

    // String constants
    public String graphErrorText;
    public String localMeasurementSystem;
    public String goalLineText = "goal";
    String[] weightsText;


    // Saved data
    Context context;

    // Callback to backgroundView
    CallbackDrawGraph callbackToBack;

    public UnoGraphView(Context context, AttributeSet attrs, int mGraphLineColor,
                        int mTextSize) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UnoGraphView,
                0, 0
        );
        mBackColor1 = a.getInteger(R.styleable.UnoGraphView_back_color1, Color.parseColor("#f0f1f2"));
        mBackColor2 = a.getInteger(R.styleable.UnoGraphView_back_color2, Color.parseColor("#e7e9eb"));
        mDesiredWidth = a.getInteger(R.styleable.UnoGraphView_real_width, 0);
        a.recycle();

        this.context = context;
        this.graphErrorText = context.getString(R.string.graphError);
        this.mGraphLineColor = mGraphLineColor;
        this.mTextSize = mTextSize;

        init();
    }


    protected void init() {



        initPaints();


        mHighRectF = new RectF();

        mGraphPath = new Path();
        mGradPath = new Path();
        mHighlightPath = new Path();


        mUpperTrianglePath = new Path();
        mUpperTrianglePath.setFillType(Path.FillType.EVEN_ODD);

        mLowerTrianglePath = new Path();
        mLowerTrianglePath.setFillType(Path.FillType.EVEN_ODD);

        stripeId = -1;
        lowerTrianglePoints = new Point[3];
        for (int i = 0; i < 3; ++i)
            lowerTrianglePoints[i] = new Point();

        upperTrianglePoints = new Point[3];
        for (int i = 0; i < 3; ++i)
            upperTrianglePoints[i] = new Point();

        startTime = System.currentTimeMillis();
    }

    private void initPaints() {
        mErrRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mErrRectPaint.setColor(Color.RED);

        mErrTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mErrTextPaint.setTextSize(30);
        mErrTextPaint.setColor(Color.BLACK);





        mGoalTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(mBackColor2);




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

        mTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTrianglePaint.setColor(mGraphLineColor);
        mTrianglePaint.setStyle(Paint.Style.FILL_AND_STROKE);


        mGoalTextPaint.setColor(mGraphLineColor);
    }


    public void measure(int w, int h) {
        // Assuming that w and h is resolved
        if (w != 0 && h != 0) {

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
                calculateTriangles(h);


            }


        }
    }

    private void calculateTriangles(int h) {
        float lowerTrianglePadding = mTextSize / 2;
        float upperTrianglePadding = mTextSize / 4;
        float lowerTriangleBound = belowIndent / 10;


        if (stripeId != -1 && curTime / segmentDuration >= stripeId) {
            lowerTrianglePoints[0].set((int) (offset + leftStripe + stripeId * stripeWidth + stripeWidth / 2),
                    (int) (labelsUnderY[stripeId] + mTextSize + lowerTrianglePadding));
            lowerTrianglePoints[1].set((int) (offset + leftStripe + stripeId * stripeWidth + 3 * stripeWidth / 4),
                    (int) (h - lowerTriangleBound));
            lowerTrianglePoints[2].set((int) (offset + leftStripe + stripeId * stripeWidth + stripeWidth / 4),
                    (int) (h - lowerTriangleBound));

            float lowerTrHeight = lowerTrianglePoints[1].y - lowerTrianglePoints[0].y;

            upperTrianglePoints[0].set((int) (offset + leftStripe + stripeId * stripeWidth + stripeWidth / 2),
                    (int) (topIndent + upperTrianglePadding + lowerTrHeight));
            upperTrianglePoints[1].set((int) (offset + leftStripe + stripeId * stripeWidth + 3 * stripeWidth / 4),
                    (int) (topIndent + upperTrianglePadding));
            upperTrianglePoints[2].set((int) (offset + leftStripe + stripeId * stripeWidth + stripeWidth / 4),
                    (int) (topIndent + upperTrianglePadding));
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
            float valueX = offset + leftStripe + stripeWidth * ((float) i + 0.5f);
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


        if (stripeId != -1) {
            goalUnderStripes = new StaticLayout(labels[stripeId], mGoalTextPaint,
                    (int) (textRatio * stripeWidth),
                    Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
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
                    (int) (leftStripe), Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
        }
    }

    public float convertValuetoHeight(Double value, float canvasHeight) {
        float indentValue = (headerRatio + borderRatio) * canvasHeight;
        float scaledValue = (float) ((linesMax - value) / (linesMax - linesMin) * graphRatio * canvasHeight);
        return indentValue + scaledValue;
    }

    public void drawGraph(Canvas canvas) {
        if (labels != null && values != null) {
            drawAdditionalBackground(canvas);

            // Measure animation time
            curTime = System.currentTimeMillis() - startTime;
            drawGraphLines(canvas);



            // Hidden feature of scrolling
            if (curTime < animationDuration) {
                callbackToBack.sendPostInvalidate(1000 / framesPerSecond);
                callbackToBack.scrollTo((int) (curX + w / 2));
            }
        }
    }





    protected void drawArrows(Canvas canvas) {
        if (offset != 0)
            drawLeftArrow(canvas, leftStripe);
        if (offset + w != realW)
            drawRightArrow(canvas, w);
    }


    private void drawHighlightedStripe(Canvas canvas) {
        if (stripeId != -1 && curTime / segmentDuration >= stripeId) {
            float xPos1 = offset + leftStripe + stripeWidth * (stripeId - bigCircleRatio);
            float xPos2 = offset + leftStripe + stripeWidth * (stripeId + 1 + bigCircleRatio);

            mHighlightPath.reset();
            mHighlightPath.moveTo(xPos1, topIndent);
            mHighlightPath.lineTo(xPos2, topIndent);
            mHighlightPath.lineTo(xPos2, canvas.getHeight() - belowIndent);
            mHighlightPath.lineTo(xPos1, canvas.getHeight() - belowIndent);
            mHighlightPath.close();

            canvas.drawPath(mHighlightPath, mHighlightPathPaint);

            mHighRectF.set(offset + leftStripe + stripeWidth * stripeId, topIndent,
                    offset + leftStripe + stripeWidth * (stripeId + 1), canvas.getHeight() - belowIndent);
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

                    curX = curPosX;

                    break;
                }
            }

        }

        mGradPath.lineTo(circleCentresX[0], canvas.getHeight() - belowIndent);
        mGradPath.close();

        canvas.drawPath(mGradPath, mGradPaint);
        canvas.drawPath(mGraphPath, mGraphPaint);
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

            buildAndDrawTriangle(canvas, lowerTrianglePoints, mLowerTrianglePath, mTrianglePaint);
            buildAndDrawTriangle(canvas, upperTrianglePoints, mUpperTrianglePath, mTrianglePaint);

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

    private void buildAndDrawTriangle(Canvas canvas, Point[] trianglePoints, Path trianglePath, Paint trianglePaint) {
        trianglePath.reset();

        trianglePath.moveTo(trianglePoints[1].x, trianglePoints[1].y);
        trianglePath.lineTo(trianglePoints[0].x, trianglePoints[0].y);
        trianglePath.lineTo(trianglePoints[2].x, trianglePoints[2].y);
        trianglePath.close();
        canvas.drawPath(trianglePath, trianglePaint);

    }


    public int getColor() {
        return mGraphLineColor;
    }

    public void setColor(int mColor) {
        this.mGraphLineColor = mColor;

        init();
        //invalidate();
        //requestLayout();
    }


    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;

        init();
        //invalidate();
        //requestLayout();
    }

    public int getmDesiredWidth() {
        return mDesiredWidth;
    }

    public void setmDesiredWidth(int mDesiredWidth) {
        this.mDesiredWidth = mDesiredWidth;

        init();
        //invalidate();
        //requestLayout();
    }


    public int getmGraphLineColor() {
        return mGraphLineColor;
    }

    public void setmGraphLineColor(int mGraphLineColor) {
        this.mGraphLineColor = mGraphLineColor;

        init();
        //invalidate();
        //requestLayout();
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
        this.values = new double[modelDataGraph.getArrayListGraph1().size()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = modelDataGraph.getArrayListGraph1().get(i);
        }

        this.labels = new String[modelDataGraph.getLabels1().size()];
        for (int i = 0; i < labels.length; ++i) {
            labels[i] = modelDataGraph.getLabels1().get(i);
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
        measure(w, h);
        callbackToBack = callbackDrawGrapg;
    }

    @Override
    protected void draw(Canvas canvas) {
        measure(w, h);
        drawGraph(canvas);
    }

    @Override
    protected void sendRect(float left, float top, float right, float bottom) {
        belowIndent = bottom;
        topIndent = top;
    }

    @Override
    protected void sendBlockWidth(float blockWidth) {

    }
}