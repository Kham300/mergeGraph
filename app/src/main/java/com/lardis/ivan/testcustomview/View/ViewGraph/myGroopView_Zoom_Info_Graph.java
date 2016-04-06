package com.lardis.ivan.testcustomview.View.ViewGraph;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyInfoView.ModelBlockInfo;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyGraphView.ModelGraph;
import com.lardis.ivan.testcustomview.View.helper.HelperView;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyGraphView.MyGraphView;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyZoomView.MyZoomView;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyInfoView.MyViewGroopInfo;

import java.util.ArrayList;

/**
 * класс реализующий взаимодействия между гравификом, лупой и блоком с информацией
 */
public class myGroopView_Zoom_Info_Graph extends AbsoluteLayout {
    /**
     *   ширина блока info (view)
     */
    public static int INFO_WIDHT;
    /**
     * высота блока info (view)
     */
    public static int INFO_HEIGHT;
    /**
     * радиус лупы
     */
    public int ZOOM_RADIUS;





    public myGroopView_Zoom_Info_Graph(Context context) {
        super(context);
        init();
    }

    public myGroopView_Zoom_Info_Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public myGroopView_Zoom_Info_Graph(Context context, AttributeSet attrs, int defStyleAttr) {
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
    MyViewGroopInfo myViewGroopInfo;
    /**
     * view рисующее график
     */
    MyGraphView myGraphView;

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
//       if((x+ZOOM_RADIUS)>INFO_WIDHT)
        if (arrayListForInfo != null)
            if (nSelectTouch >= 0 && nSelectTouch < arrayListForInfo.size())
                myViewGroopInfo.setInfo(arrayListForInfo.get(nSelectTouch));


        findLocatinInfoView(x, y);

    }

    /**
     * поиск и изменение  места для расположения блока с информацией
     *
     * @param x
     * @param y
     */
    private void findLocatinInfoView(float x, float y) {
        if (x > (30 + INFO_WIDHT + ZOOM_RADIUS)) leftInfo(x, y);
        else if (y > (30 + INFO_HEIGHT + ZOOM_RADIUS)) topInfo(x, y);
        else if ((getWidth() - x) > (30 + INFO_WIDHT + ZOOM_RADIUS))
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
        myViewGroopInfo.locationTriangleRight();
        myViewGroopInfo.invalidate();
        myViewGroopInfo.setX(x - ZOOM_RADIUS - 20 - INFO_WIDHT);
        float k = y - INFO_HEIGHT / 2;

        if (k > (getHeight() - INFO_HEIGHT))
            k = getHeight() - INFO_HEIGHT;
        if (k < (0)) k = 0;
        myViewGroopInfo.setY(k);

    }

    /**
     * перемещение фона справа относительно Лупы
     *
     * @param x
     * @param y
     */
    void rightInfo(float x, float y) {
        myViewGroopInfo.locationTriangleLeft();
        myViewGroopInfo.invalidate();
        myViewGroopInfo.setX(x + 20 + ZOOM_RADIUS);

        float k = y - INFO_HEIGHT / 2;
        if (k > (getHeight() - INFO_HEIGHT))
            k = getHeight() - INFO_HEIGHT;
        if (k < (0)) k = 0;
        myViewGroopInfo.setY(k);

    }

    /**
     * перемещение фона сверху относительно Лупы
     *
     * @param x
     * @param y
     */
    void topInfo(float x, float y) {
        myViewGroopInfo.locationTriangleBottom();
        myViewGroopInfo.invalidate();
        float k = x - INFO_WIDHT / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIDHT))
            k = (getWidth() - INFO_WIDHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y - 20 - ZOOM_RADIUS - INFO_HEIGHT);


    }

    /**
     * перемещение фона снизу относительно Лупы
     *
     * @param x
     * @param y
     */
    void bottomInfo(float x, float y) {
        myViewGroopInfo.locationTriangleTop();
        myViewGroopInfo.invalidate();
        float k = x - INFO_WIDHT / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIDHT))
            k = (getWidth() - INFO_WIDHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y + 20 + ZOOM_RADIUS);
    }

    /**
     * показать блок с информацией
     */
    void showBlockInfo() {
        this.addView(myViewGroopInfo, lpView);
    }

    /**
     * скрыть блок с информацией
     */
    void hideBlockInfo() {
        this.removeView(myViewGroopInfo);
    }


    /**
     * пересыл даных view рисуюшему графики
     *

     */
    public void setDataGraphAndInfo(ModelGraph dataGraph, ArrayList<ModelBlockInfo> arrayListForInfo) {

        this.arrayListForInfo = arrayListForInfo;
        if(arrayListForInfo!=null)
        myGraphView.setWorkFromZoomAndBlockInfo(new MyGraphView.WorkFromZoomAndBlockInfo() {


            @Override
            public void showZoomAndBlockInfo() {
                myZoomView.show();

                showBlockInfo();
            }

            @Override
            public void hideZoomAndBlockInfo() {
                myZoomView.hide();
                hideBlockInfo();
            }

            @Override
            public void setCoordinate(float x, float y, int nTouch) {
                myZoomView.setData(x, y);
                setmyXandYandNTouch(x, y, nTouch);

            }

            @Override
            public void updatePrtScn() {
                myZoomView.updateBitmapPrtScn(HelperView.getBitmapFromView(myGraphView));
            }


        });
        else  myGraphView.setWorkFromZoomAndBlockInfo(null);


           myGraphView.setGraphDataAndCalculate(dataGraph);
        myGraphView.invalidate();

    }





    void init()

    {



        ZOOM_RADIUS = HelperView.convertDpToPixel(50, getContext());
        myGraphView = new MyGraphView(getContext());
        myViewGroopInfo = new MyViewGroopInfo(getContext());
        myZoomView = new MyZoomView(getContext());
        myZoomView.setRadius(ZOOM_RADIUS);

INFO_HEIGHT=getResources().getDimensionPixelSize(R.dimen.layout_info_height);
INFO_WIDHT=getResources().getDimensionPixelSize(R.dimen.layout_info_width);





        lpView = new ViewGroup.LayoutParams(INFO_WIDHT, INFO_HEIGHT);

        this.addView(myGraphView);
        this.addView(myZoomView);
/**
 * связь графика с Лупой и Блоком информации
 */



    }
}
