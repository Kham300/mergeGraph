package com.lardis.ivan.testcustomview.myGroopView.ViewInfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lardis.ivan.testcustomview.R;

/**
 * Created by i.larin on 30.03.2016.
 */
public class MyViewGroopBackgroundInfo extends RelativeLayout {
    public MyViewGroopBackgroundInfo(Context context) {
        super(context); init();
    }

    public MyViewGroopBackgroundInfo(Context context, AttributeSet attrs) {
        super(context, attrs); init();
    }

    public MyViewGroopBackgroundInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    void init()
    {
        LayoutInflater layoutInflater= LayoutInflater.from(getContext());

        View view1=layoutInflater.inflate(R.layout.layout_inf,null);

        this.addView(new MyViewBackgroundInfo(getContext()));
        this.addView(view1);

    }






}
