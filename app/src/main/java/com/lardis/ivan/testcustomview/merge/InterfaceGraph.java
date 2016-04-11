package com.lardis.ivan.testcustomview.merge;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface InterfaceGraph {
    void  setData(ModelDataGraph modelDataGraph);
    void updateOfsset(float v,Canvas canvas);
    void click(int n,Canvas canvas);
    void setCallback(CallbackDrawGrapg callbackDrawGrapg);


}

