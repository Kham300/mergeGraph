package com.lardis.ivan.testcustomview.View.Graph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.View.Graph.GraphLine.UnoGraphView;
import com.lardis.ivan.testcustomview.View.Graph.GraphPunct.GraphPunct;

import java.util.ArrayList;

/**
 * Created by i.larin on 11.04.2016.
 */
public class NewBackground extends View {
    float canvasWidht;
    float canvasHeight;
    float ofssetborderBackround;
    int sizeBackroundPunct;
    float widthBlockBackround;
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
        if (nSelectedTouch < 0 || nSelectedTouch >= sizeBackroundPunct) return false;
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
        canvasWidht = getWidth();
        canvasHeight = getHeight();
        graph.setWH(w, h);
    }

    BaseGraph graph;

    public void setDataGraph(ModelDataGraph modelDataGraph, TypeGraph typeGraph) {
        int old_w = graph.getW(), old_h = graph.getH();
        switch (typeGraph) {
            case GraphLine:
                graph = new UnoGraphView(getContext(), attributeSet, 50);
                break;

            case GraphPunct:
                graph = new GraphPunct(getContext(), attributeSet);
                break;
        }
        graph.setWH(old_w, old_h);
        graph.setData(modelDataGraph);

        graph.setCallback(new CallbackDrawGraph() {
            @Override
            public void updateDrawByQ(float widthBlock, int n, float offsetBorder) {
                widthBlockBackround = widthBlock;
                ofssetborderBackround = offsetBorder;
                sizeBackroundPunct = n;
                invalidate();
            }

            @Override
            public void updateDrawByArrayList(ArrayList<?> arrayList, float ofssetBorder) {
                ofssetborderBackround = ofssetBorder;
                invalidate();
            }
        });


    }


    void init(AttributeSet attrs, int defStyle) {
        this.attributeSet = attrs;
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Myview, defStyle, 0);
        initColor(a);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaintSelectedColumn = new Paint();
        mPaintSelectedColumn.setAntiAlias(true);
        mPaintSelectedColumn.setTextSize(35.0f);
        mPaintSelectedColumn.setStrokeWidth(2.0f);
        mPaintSelectedColumn.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintMesh = new Paint();
        mPaintSelectedColumn.setShadowLayer(10.0f, 0.0f, 0.0f, colorSelectedItemShadowLayer);

        graph = new BaseGraph() {
            @Override
            public void setData(ModelDataGraph modelDataGraph) {

            }

            @Override
            public void updateOfsset(float v, Canvas canvas) {

            }

            @Override
            public void click(int n, Canvas canvas) {

            }

            @Override
            public void setCallback(CallbackDrawGraph callbackDrawGrapg) {

            }
        };

        testdata();
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
    }


    void testdata() {
        ofssetborderBackround = 51;
        sizeBackroundPunct = 52;
        widthBlockBackround = 231;
        updateMaxX();


        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
        for (int i = 0; i < sizeBackroundPunct; i++) {
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

        graph.updateOfsset(offsetX, canvas);
        if (isTouch())
            graph.click(nSelectedTouch, canvas);

    }

    private void drawMesh(Canvas canvas, int i, Paint paint) {
        canvas.drawRect(ofssetborderBackround + offsetX + widthBlockBackround * (i),
                0,
                ofssetborderBackround + offsetX + widthBlockBackround * (i + 1),
                getHeight(), paint);
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
        maxX = ofssetborderBackround * 2 + widthBlockBackround * sizeBackroundPunct - canvasWidht;

    }

    private void updateNSelectedTouch(Float X) {
        nSelectedTouch = (int) ((X - bufOffsetX - ofssetborderBackround) / (widthBlockBackround));


    }

}
