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
import com.lardis.ivan.testcustomview.View.Graph.Helper.HelperGraphInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 11.04.2016.
 */
public class NewBackground extends View implements CallbackDrawGraph {
    int canvasWidht;
    int canvasHeight;
    float offsetborderBackround;
    int sizeBackroundPunct;
    float widthBlockBackround;
    ModelDataGraph modelDataGraph;
    ArrayList<Float> arrayList;
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

    int nSelected = -1;

    private boolean isTouch() {
        if (nSelected < 0 || nSelected >= sizeBackroundPunct) return false;
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
if(!HelperGraphInfo.isGraph(modelDataGraph))return;
        switch (typeGraph) {
            case GraphLine:
                graph = new UnoGraphView(getContext(), this, attributeSet, 50);
                break;

            case GraphPunct:
                graph = new GraphPunct(getContext(), this, attributeSet);
                break;
            default:
                hide();
                return;
        }
        arrayList= HelperGraphInfo.getArrayWidthCoefficient(modelDataGraph);
        this.modelDataGraph = modelDataGraph;
        this.sizeBackroundPunct=modelDataGraph.getArrayListGraph1().size();
        graph.setWH(canvasWidht, canvasHeight);
        graph.setData(modelDataGraph);

        graph.setCallback(this);
      testData();
invalidate();

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
        };


    }

    /**
     * удалить потом для тестовых данных
     */
    private void testData() {
        updateWidthBlockandOffsesBorder(100,  35);
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

    @Override
    public void updateDrawByQ(float widthBlock, int n, float offsetBorder) {
        updateWidthBlockandOffsesBorder(widthBlock,  offsetBorder);

    }

    private void updateWidthBlockandOffsesBorder(float widthBlock,  float offsetBorder) {
        widthBlockBackround = widthBlock;
        offsetborderBackround = offsetBorder;

        updateMaxX();
        show();
        invalidate();
    }

    @Override
    public void updateDrawByArrayList(ArrayList<?> arrayList, float offsetBorder) {
        offsetborderBackround = offsetBorder;
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
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!draw()) return;
        drawMeshAllNoSelected(canvas);


        if (nSelected % 2 == 0)
            mPaintSelectedColumn.setColor(colorMeshOne);
        if (nSelected % 2 == 1)
            mPaintSelectedColumn.setColor(colorMeshTwo);
        if (isTouch()) drawMesh(canvas, nSelected, mPaintSelectedColumn, widthBlockBackround,1);


        graph.draw(canvas);

    }

    private void drawMeshAllNoSelected(Canvas canvas) {
if(modelDataGraph==null)return;
        switch (modelDataGraph.getTypeViewGraph()) {


            case MESH_MONTH_ITEM_WEEK:

            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:

                float j=0;
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
                    else mPaintMesh.setColor(colorMeshTwo);
                    drawMesh(canvas, j, mPaintMesh, widthBlockBackround,arrayList.get(i));
                    j=j+arrayList.get(i);
                }

                break;

            case MESH_WEEK_ITEM_WEEK:
            case MESH_DAY_ITEM_DAY:
            case MESH_MONTH_ITEM_MONTH:
                for (int i = 0; i < sizeBackroundPunct; i++) {
                    if (i % 2 == 0) mPaintMesh.setColor(colorMeshOne);
                    else mPaintMesh.setColor(colorMeshTwo);
                    drawMesh(canvas, i, mPaintMesh, widthBlockBackround,1);
                }
                break;


        }

    }

    private void drawMesh(Canvas canvas, float i, Paint paint, float widthBlockBackround,float coficient) {
        canvas.drawRect(offsetborderBackround + offsetX + widthBlockBackround * (i),
                0,
                offsetborderBackround + offsetX + widthBlockBackround * (i +  coficient),
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
                if (Math.abs(offsetX - bufOffsetX) < 4) updateNSelected(X);

                break;
        }


        invalidate();
        return true;

    }

    private void updateOffsetX() {
        offsetX = bufOffsetX - (bufX2 - X);
        if (offsetX < -maxX) offsetX = -maxX;
        if (offsetX > minX) offsetX = minX;
        graph.updateOffset(offsetX);
    }

    private void updateMaxX() {
        maxX = offsetborderBackround * 2 + widthBlockBackround * sizeBackroundPunct - canvasWidht;

    }

    private void updateNSelected(Float X) {
        nSelected = (int) ((X - bufOffsetX - offsetborderBackround) / (widthBlockBackround));
        graph.click(nSelected);
    }

}
