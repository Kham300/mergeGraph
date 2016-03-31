package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.lardis.ivan.testcustomview.helper.ViewHelper;
import com.lardis.ivan.testcustomview.myEnum.enumTypeViewGraph;
import com.lardis.ivan.testcustomview.myGroopView.MyGraphView;
import com.lardis.ivan.testcustomview.myGroopView.MyZoomView;
import com.lardis.ivan.testcustomview.myGroopView.MyInfoView.MyViewGroopInfo;

import java.util.ArrayList;

/**
 * класс реализующий взаимодействия между гравификом, лупой и блоком с информацией
 */
public class myGroopAbsoluteLayout extends AbsoluteLayout {
    /**
     *   ширина блока info (view)
     */
    public static int INFO_WIGHT;
    /**
     * высота блока info (view)
     */
    public static int INFO_HEIGHT;
    /**
     * радиус лупы
     */
    public int ZOOM_RADIUS;

    public void setArrayListForInfo(ArrayList<String[]> arrayListForInfo) {
        this.arrayListForInfo = arrayListForInfo;
    }

    public myGroopAbsoluteLayout(Context context) {
        super(context);
        init();
    }

    public myGroopAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public myGroopAbsoluteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * /**
     * лист с даными для информаций
     * массив  <br> 0 элемент - длитетельсть <br> 1 элемент -количество подходов <br> 2 элемент- количество раз <br>3 элемент- сожжено <br> 4 элемент пульс
     */
    ArrayList<String[]> arrayListForInfo;
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
//       if((x+ZOOM_RADIUS)>INFO_WIGHT)
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
        if (x > (30 + INFO_WIGHT + ZOOM_RADIUS)) leftInfo(x, y);
        else if (y > (30 + INFO_HEIGHT + ZOOM_RADIUS)) topInfo(x, y);
        else if ((getWidth() - x) > (30 + INFO_WIGHT + ZOOM_RADIUS))
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
        myViewGroopInfo.setX(x - ZOOM_RADIUS - 20 - INFO_WIGHT);
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
        float k = x - INFO_WIGHT / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIGHT))
            k = (getWidth() - INFO_WIGHT);
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
        float k = x - INFO_WIGHT / 2;
        if (k < 0) k = 0;
        if (k > (getWidth() - INFO_WIGHT))
            k = (getWidth() - INFO_WIGHT);
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
     * @param day
     * @param month
     * @param year
     * @param arrayListMetodDrawGraph1 масив  значений для столбиков
     * @param arrayListMetodDrawGraph2 2 массив значиний если два графика
     * @param typeViewGraph            тип вывода данных графика
     */
    void setDrawGraph(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2, enumTypeViewGraph typeViewGraph) {
        myGraphView.setDrawGraph(day, month, year, arrayListMetodDrawGraph1, arrayListMetodDrawGraph2, typeViewGraph);
        myGraphView.invalidate();

    }





    void init()

    {


        INFO_WIGHT = ViewHelper.convertDpToPixel(210, getContext());
        INFO_HEIGHT = ViewHelper.convertDpToPixel(170, getContext());
        ZOOM_RADIUS = ViewHelper.convertDpToPixel(100, getContext());
        myGraphView = new MyGraphView(getContext());
        myViewGroopInfo = new MyViewGroopInfo(getContext());
        myZoomView = new MyZoomView(getContext());
        myZoomView.setRadius(ZOOM_RADIUS);
        lpView = new ViewGroup.LayoutParams(INFO_WIGHT, INFO_HEIGHT);

        this.addView(myGraphView);
        this.addView(myZoomView);
/**
 * связь графика с Лупой и Блоком информации
 */
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
                myZoomView.updateBitmapPrtScn(ViewHelper.getBitmapFromView(myGraphView));
            }


        });

    }
}
