package com.lardis.ivan.testcustomview.merge;

import android.graphics.Canvas;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface InterfaceGraph {
    void  setData(ModelDataGraph modelDataGraph);
    void updateOfsset(float v,Canvas canvas);
    void click(int n,Canvas canvas);
    void setCallback(CallbackDrawGraph callbackDrawGrapg);
}

