package com.lardis.ivan.testcustomview.graphview.base.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.graphview.base.TypeGraph;
import com.lardis.ivan.testcustomview.graphview.base.views.blockview.MyViewGroupInfo;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperLayoutClass;
import com.lardis.ivan.testcustomview.model.ModelBlockInfo;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;

/**
 * Created by aleksey.ivanov on 22.04.2016.
 */
public class GraphViewGroup extends RelativeLayout {
    /**
     * ширина блока info (view)
     */
    public static int INFO_WIDTH;
    /**
     * высота блока info (view)
     */
    public static int INFO_HEIGHT;
    /**
     * радиус лупы
     */
    public static int ZOOM_RADIUS;
    public static int FINGER_RADIUS;


    public GraphViewGroup(Context context) {
        super(context);
        init();
    }

    public GraphViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * /**
     * лист с даными для информаций
     * массив  <br> 0 элемент - длитетельсть <br> 1 элемент -количество подходов <br> 2 элемент- количество раз <br>3 элемент- сожжено <br> 4 элемент пульс
     */
    ArrayList<ModelBlockInfo> arrayListForInfo;
    /**
     * view блок с информацией
     */
    MyViewGroupInfo myViewGroupInfo;
    /**
     * view рисующее график
     */
    NewBackground myGraphView;

    /**
     * view лупы
     */
    MyZoomView myZoomView;

    /**
     * параметры блока с информацией
     */
    ViewGroup.LayoutParams lpView;

    /**
     * @param x            Ко
     * @param y
     * @param nSelectTouch
     */

    public void setmyXandYandNTouch(float x, float y, int nSelectTouch) {
        if (arrayListForInfo != null)
            if (nSelectTouch >= 0 && nSelectTouch < arrayListForInfo.size())
                myViewGroupInfo.setInfo(arrayListForInfo.get(nSelectTouch));


        findLocatinInfoView(x, y);

    }

    /**
     * поиск и изменение  места для расположения блока с информацией
     *
     * @param x
     * @param y
     */
    private void findLocatinInfoView(float x, float y) {
        if (x > (30 + INFO_WIDTH + ZOOM_RADIUS)) leftInfo(x, y);
        else if (y > (30 + INFO_HEIGHT + ZOOM_RADIUS)) topInfo(x, y);
        else if ((getWidth() - x) > (30 + INFO_WIDTH + ZOOM_RADIUS))
            rightInfo(x, y);
        else if ((getHeight() - y) > (30 + INFO_HEIGHT + ZOOM_RADIUS))
            bottomInfo(x, y);
    }

    /**
     * перемещение фона слева относительно Лупы
     *
     * @param x
     * @param y
     */
    void leftInfo(float x, float y) {
        myViewGroupInfo.locationTriangleRight();
        myViewGroupInfo.invalidate();
        myViewGroupInfo.setX(x - ZOOM_RADIUS - 20 - INFO_WIDTH);
        float k = y - INFO_HEIGHT / 2;

        if (k > (getHeight() - INFO_HEIGHT))
            k = getHeight() - INFO_HEIGHT;
        if (k < (0)) k = 0;
        myViewGroupInfo.setY(k);

    }

    /**
     * перемещение фона справа относительно Лупы
     *
     * @param x
     * @param y
     */
    void rightInfo(float x, float y) {
        myViewGroupInfo.locationTriangleLeft();
        myViewGroupInfo.invalidate();
        myViewGroupInfo.setX(x + 20 + ZOOM_RADIUS);

        float k = y - INFO_HEIGHT / 2;
        if (k > (getHeight() - INFO_HEIGHT))
            k = getHeight() - INFO_HEIGHT;
        if (k < (0)) k = 0;
        myViewGroupInfo.setY(k);

    }

    /**
     * перемещение фона сверху относительно Лупы
     *
     * @param x
     * @param y
     */
    void topInfo(float x, float y) {
        myViewGroupInfo.locationTriangleBottom();
        myViewGroupInfo.invalidate();
        float k = x - INFO_WIDTH / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIDTH))
            k = (getWidth() - INFO_WIDTH);
        myViewGroupInfo.setX(k);

        myViewGroupInfo.setY(y - 20 - ZOOM_RADIUS - INFO_HEIGHT);


    }

    /**
     * перемещение фона снизу относительно Лупы
     *
     * @param x
     * @param y
     */
    void bottomInfo(float x, float y) {
        myViewGroupInfo.locationTriangleTop();
        myViewGroupInfo.invalidate();
        float k = x - INFO_WIDTH / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIDTH))
            k = (getWidth() - INFO_WIDTH);
        myViewGroupInfo.setX(k);

        myViewGroupInfo.setY(y + 20 + ZOOM_RADIUS);
    }

    /**
     * показать блок с информацией
     */
    void showBlockInfo() {
        this.addView(myViewGroupInfo, lpView);
    }

    /**
     * скрыть блок с информацией
     */
    void hideBlockInfo() {
        this.removeView(myViewGroupInfo);
    }


    /**
     * пересыл даных view рисуюшему графики
     */
    public void setDataGraphAndInfo(ModelDataGraph dataGraph, TypeGraph typeGraph) {
        this.arrayListForInfo = dataGraph.getArrayListForInfo();
        if (arrayListForInfo != null)
            myGraphView.setWorkFromZoomAndBlockInfo(new NewBackground.WorkFromZoomAndBlockInfo() {


                @Override
                public void showZoom() {
                    myZoomView.show();
                }

                @Override
                public void showZoomInfo() {
                    showBlockInfo();
                    if (!myZoomView.isShown())
                        ZOOM_RADIUS = FINGER_RADIUS;
                }

                @Override
                public void hideZoom() {
                    myZoomView.hide();
                }

                @Override
                public void hideZoomInfo() {
                    hideBlockInfo();
                }

                @Override
                public void setCoordinate(float x, float y, int nTouch) {
                    setmyXandYandNTouch(x, y, nTouch);
                    myZoomView.setData(x, y);

                }

                @Override
                public void updatePrtScn() {
                    myZoomView.updateBitmapPrtScn(HelperLayoutClass.getBitmapFromView(myGraphView));
                }


            });
        else myGraphView.setWorkFromZoomAndBlockInfo(null);


        myGraphView.setDataGraph(dataGraph, typeGraph);
        myGraphView.invalidate();

    }


    void init() {
        ZOOM_RADIUS = (int) HelperLayoutClass.dpToPixels(getResources(), 50);
        FINGER_RADIUS = (int) HelperLayoutClass.dpToPixels(getResources(), 25);
        myGraphView = new NewBackground(getContext());
        myViewGroupInfo = new MyViewGroupInfo(getContext());
        myZoomView = new MyZoomView(getContext());
        myZoomView.setRadius(ZOOM_RADIUS);

        INFO_HEIGHT = getResources().getDimensionPixelSize(R.dimen.layout_info_height);
        INFO_WIDTH = getResources().getDimensionPixelSize(R.dimen.layout_info_width);


        lpView = new ViewGroup.LayoutParams(INFO_WIDTH, INFO_HEIGHT);

        this.addView(myGraphView);
        this.addView(myZoomView);
    }
}
