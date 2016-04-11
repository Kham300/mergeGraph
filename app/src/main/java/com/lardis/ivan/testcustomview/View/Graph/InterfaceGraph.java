package com.lardis.ivan.testcustomview.View.Graph;

import android.graphics.Canvas;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface InterfaceGraph {
    void  setData(ModelDataGraph modelDataGraph);
    void updateOfsset(float v,Canvas canvas);
    void click(int n,Canvas canvas);
    void setCallback(CallbackDrawGraph callbackDrawGrapg);
}

