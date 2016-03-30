package com.lardis.ivan.testcustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.lardis.ivan.testcustomview.helper.ViewHelper;
import com.lardis.ivan.testcustomview.myGroopView.MyGraphView;
import com.lardis.ivan.testcustomview.myGroopView.MyZoomView;
import com.lardis.ivan.testcustomview.myGroopView.ViewInfo.MyViewGroopBackgroundInfo;

/**
 * Created by i.larin on 30.03.2016.
 */
public class myAbsoluteLayout extends AbsoluteLayout {
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT ;
    public static int MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT;
    public static int MY_ZOOM_VIEW_RADIUS;

    ViewGroup.LayoutParams lpView;
    public void setmyXandY(float x,float y) {
//       if((x+MY_ZOOM_VIEW_RADIUS)>MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT)

      if(x>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))leftInfo();
      else if(y>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))topInfo();
        else  if((getWidth()-x)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))rightInfo();
      else if((getHeight()-y)>(30+MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT+MY_ZOOM_VIEW_RADIUS))bottomInfo();
    }
    void leftInfo()
    { myViewGroopBackgroundInfo.setX(30);

        myViewGroopBackgroundInfo.setY(getHeight()/2-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2);}
    void rightInfo()
    {

        myViewGroopBackgroundInfo.setX(getWidth()-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT-30);

        myViewGroopBackgroundInfo.setY(getHeight()/2-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT/2);

    }
    void topInfo()
    {
        myViewGroopBackgroundInfo.setX(getWidth()/2-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2);

        myViewGroopBackgroundInfo.setY(0+30);


    }
    void bottomInfo()
    {
        myViewGroopBackgroundInfo.setX(getWidth()/2-MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT/2);

        myViewGroopBackgroundInfo.setY(getHeight()-30-MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT);
    }
    void showmyViewGroopBackgroundInfo()
    {this.addView(myViewGroopBackgroundInfo,lpView);}

    void hidemyViewGroopBackgroundInfo()
    {this.removeView(myViewGroopBackgroundInfo);}


    public void setMyViewGroopBackgroundInfolX(int myViewGroopBackgroundInfolX) {
        this.myViewGroopBackgroundInfolX = myViewGroopBackgroundInfolX;
    }

    public void setMyViewGroopBackgroundInfolY(int myViewGroopBackgroundInfolY) {
        this.myViewGroopBackgroundInfolY = myViewGroopBackgroundInfolY;
    }
    boolean showView=false;
    public void show()
    {showView=true;}
    public void hide()
    {showView=false;}
    int   myViewGroopBackgroundInfolX;
    int myViewGroopBackgroundInfolY;
    public MyZoomView getMyZoomView() {
        return myZoomView;
    }

    public MyGraphView getMyGraphView() {
        return myGraphView;
    }

    MyGraphView myGraphView;


    MyZoomView myZoomView;
    public myAbsoluteLayout(Context context) {
        super(context); init();
    }

    public myAbsoluteLayout(Context context, AttributeSet attrs) {
        super(context, attrs); init();
    }

    public myAbsoluteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public MyViewGroopBackgroundInfo getMyViewGroopBackgroundInfo() {
        return myViewGroopBackgroundInfo;
    }

    MyViewGroopBackgroundInfo myViewGroopBackgroundInfo;
    void init()

    {MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT= ViewHelper.convertDpToPixel(210, getContext());
   MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT= ViewHelper.convertDpToPixel(170, getContext());
        MY_ZOOM_VIEW_RADIUS=ViewHelper.convertDpToPixel(80, getContext());
        myGraphView = new MyGraphView(getContext());
        myViewGroopBackgroundInfo =new MyViewGroopBackgroundInfo(getContext());
        myZoomView=new MyZoomView(getContext());

        lpView = new ViewGroup.LayoutParams(MY_VIEW_GROOP_BACK_GROUND_INFO_WIGHT, MY_VIEW_GROOP_BACK_GROUND_INFO_HEIGHT);

        this.addView(myGraphView);
        this.addView(myZoomView);


    }
}
