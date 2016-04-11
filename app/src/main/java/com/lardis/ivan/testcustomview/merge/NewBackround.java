package com.lardis.ivan.testcustomview.merge;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by i.larin on 11.04.2016.
 */
public class NewBackround extends View{
    public NewBackround(Context context) {
        super(context);init();
    }
//public void setDataGraph
    public NewBackround(Context context, AttributeSet attrs) {
        super(context, attrs);init();
    }

    public NewBackround(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }
void init()
{





}

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
