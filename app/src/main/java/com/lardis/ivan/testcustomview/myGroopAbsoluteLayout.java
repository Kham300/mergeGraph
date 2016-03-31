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
     * высота ширина info
     */
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT ;
    /**
     * высота блока info
     */
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
    /**
     * радиус лупы
     */
    public   int ZOOM_RADIUS;

    public void setArrayListForInfo(ArrayList<String[]> arrayListForInfo) {
        this.arrayListForInfo = arrayListForInfo;
    }

    /**
     * лист с даными для информаций
     *  массив  <br> 0 элемент - длитетельсть <br> 1 элемент -количество подходов <br> 2 элемент- количество раз <br>3 элемент- сожжено <br> 4 элемент пульс
     */
ArrayList<String[]> arrayListForInfo ;
    /**
     * view блок с информацией
     */
    MyViewGroopInfo myViewGroopInfo;
    /**
     * view графика
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
     *
     * @param x Ко
     * @param y
     * @param nTouch
     */
    public void setmyXandYandNTouch(float x,float y,int nTouch) {
//       if((x+ZOOM_RADIUS)>MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT)
        if(arrayListForInfo!=null)
        if(nTouch>=0 && nTouch< arrayListForInfo.size())myViewGroopInfo.setInfo(arrayListForInfo.get(nTouch));


        finfLocatinInfoView(x, y);
//        bottomInfo(x,y);
    }

    /**
     * поиск места для расположения блока с информацией
     * @param x
     * @param y
     */
    private void finfLocatinInfoView(float x, float y) {
        if(x>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+ ZOOM_RADIUS))leftInfo(x,y);
        else if(y>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT+ ZOOM_RADIUS))topInfo(x,y);
          else  if((getWidth()-x)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+ ZOOM_RADIUS))rightInfo(x,y);
        else if((getHeight()-y)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT+ ZOOM_RADIUS))bottomInfo(x,y);
    }

    void leftInfo(float x,float y)
    {
        myViewGroopInfo.locationTriangleRight();
        myViewGroopInfo.invalidate();
        myViewGroopInfo.setX(x - ZOOM_RADIUS -20 - MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
float k=y-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2;

        if(k>(getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT))k=getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
        if(k<(0))k=0;
        myViewGroopInfo.setY(k);

    }
    void rightInfo(float x,float y)
    {
        myViewGroopInfo.locationTriangleLeft();
        myViewGroopInfo.invalidate();
        myViewGroopInfo.setX(x +20+ ZOOM_RADIUS);

        float k=y-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2;
        if(k>(getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT))k=getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
        if(k<(0))k=0;
        myViewGroopInfo.setY(k);

    }
    void topInfo(float x,float y)
    { myViewGroopInfo.locationTriangleBottom();
        myViewGroopInfo.invalidate();
        float k=x-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2;
        if(k<0)k=0;
        if(k>(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT))k=(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y - 20 - ZOOM_RADIUS - MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT);


    }
    void bottomInfo(float x,float y)
    {
        myViewGroopInfo.locationTriangleTop();
        myViewGroopInfo.invalidate();
        float k=x-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2;
        if(k<0)k=0;
        if(k>(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT))k=(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y + 20+ ZOOM_RADIUS);
    }
    void showmyViewGroopBackgroundInfo()
    {this.addView(myViewGroopInfo, lpView);}

    void hidemyViewGroopBackgroundInfo()
    {this.removeView(myViewGroopInfo);}

   void setDrawGraph(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2, enumTypeViewGraph typeViewGraph)
   {myGraphView.setDrawGraph(day, month, year,  arrayListMetodDrawGraph1,  arrayListMetodDrawGraph2,  typeViewGraph);
       myGraphView.invalidate();

   }

    public MyGraphView getMyGraphView() {
        return myGraphView;
    }


    public myGroopAbsoluteLayout(Context context) {
        super(context); init();
    }

    public myGroopAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs); init();
    }

    public myGroopAbsoluteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public MyViewGroopInfo getMyViewGroopInfo() {
        return myViewGroopInfo;
    }


    void init()

    {



        MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT= ViewHelper.convertDpToPixel(210, getContext());
   MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT= ViewHelper.convertDpToPixel(170, getContext());
        ZOOM_RADIUS =ViewHelper.convertDpToPixel(100, getContext());
        myGraphView = new MyGraphView(getContext());
        myViewGroopInfo =new MyViewGroopInfo(getContext());
        myZoomView=new MyZoomView(getContext());
myZoomView.setRadius(ZOOM_RADIUS);
        lpView = new ViewGroup.LayoutParams(MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT, MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT);

        this.addView(myGraphView);
        this.addView(myZoomView);

        myGraphView.setSelectedZoom(new MyGraphView.SelectedZoom() {


            @Override
            public void doShow(boolean work) {
                if (work) {
                    myZoomView.show();

                    showmyViewGroopBackgroundInfo();


                } else {
                    myZoomView.hide();
                    hidemyViewGroopBackgroundInfo();
                }

            }

            @Override
            public void setCoordinate(float x, float y, int nTouch) {
                myZoomView.setData(x, y, ViewHelper.getBitmapFromView(myGraphView));
                 setmyXandYandNTouch(x, y, nTouch);

            }


        });

    }
}
