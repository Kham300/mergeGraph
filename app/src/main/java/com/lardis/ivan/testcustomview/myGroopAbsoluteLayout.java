package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.lardis.ivan.testcustomview.helper.ViewHelper;
import com.lardis.ivan.testcustomview.myGroopView.MyGraphView;
import com.lardis.ivan.testcustomview.myGroopView.MyZoomView;
import com.lardis.ivan.testcustomview.myGroopView.ViewInfo.MyViewGroopInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 30.03.2016.
 */
public class myGroopAbsoluteLayout extends AbsoluteLayout {
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT ;
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
    public static int MY_ZOOM_VIEW_RADIUS;
ArrayList<String[]> arrayList=MainActivity.testdanForInfo();

    MyViewGroopInfo myViewGroopInfo;
    MyGraphView myGraphView;
    MyZoomView myZoomView;

    ViewGroup.LayoutParams lpView;



    public void setmyXandYandNTouch(float x,float y,int nTouch) {
//       if((x+MY_ZOOM_VIEW_RADIUS)>MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT)
        if(nTouch>=0 && nTouch<arrayList.size())myViewGroopInfo.setInfomyViewGroopInfo(arrayList.get(nTouch));
      if(x>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))leftInfo(x,y);
      else if(y>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT+MY_ZOOM_VIEW_RADIUS))topInfo(x,y);
        else  if((getWidth()-x)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))rightInfo(x,y);
      else if((getHeight()-y)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT+MY_ZOOM_VIEW_RADIUS))bottomInfo(x,y);
//        bottomInfo(x,y);
    }
    void leftInfo(float x,float y)
    {
        myViewGroopInfo.getMyViewBackgroundInfo().setLeftTringulePath();
        myViewGroopInfo.getMyViewBackgroundInfo().invalidate();
        myViewGroopInfo.setX(x - MY_ZOOM_VIEW_RADIUS-20 - MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
float k=y-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2;

        if(k>(getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT))k=getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
        if(k<(0))k=0;
        myViewGroopInfo.setY(k);

    }
    void rightInfo(float x,float y)
    {
        myViewGroopInfo.getMyViewBackgroundInfo().setRightTringulePath();
        myViewGroopInfo.getMyViewBackgroundInfo().invalidate();
        myViewGroopInfo.setX(x +20+ MY_ZOOM_VIEW_RADIUS);

        float k=y-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2;
        if(k>(getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT))k=getHeight()-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
        if(k<(0))k=0;
        myViewGroopInfo.setY(k);

    }
    void topInfo(float x,float y)
    { myViewGroopInfo.getMyViewBackgroundInfo().setTopTringulePath();
        myViewGroopInfo.getMyViewBackgroundInfo().invalidate();
        float k=x-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2;
        if(k<0)k=0;
        if(k>(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT))k=(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y - 20-MY_ZOOM_VIEW_RADIUS - MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT);


    }
    void bottomInfo(float x,float y)
    {
        myViewGroopInfo.getMyViewBackgroundInfo().setBattomTringulePath();
        myViewGroopInfo.getMyViewBackgroundInfo().invalidate();
        float k=x-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2;
        if(k<0)k=0;
        if(k>(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT))k=(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT);
        myViewGroopInfo.setX(k);

        myViewGroopInfo.setY(y + 20+MY_ZOOM_VIEW_RADIUS);
    }
    void showmyViewGroopBackgroundInfo()
    {this.addView(myViewGroopInfo,lpView);}

    void hidemyViewGroopBackgroundInfo()
    {this.removeView(myViewGroopInfo);}



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
        MY_ZOOM_VIEW_RADIUS=ViewHelper.convertDpToPixel(100, getContext());
        myGraphView = new MyGraphView(getContext());
        myViewGroopInfo =new MyViewGroopInfo(getContext());
        myZoomView=new MyZoomView(getContext());

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
