package com.lardis.ivan.testcustomview.graphview.base;
import android.graphics.Canvas;

import com.lardis.ivan.testcustomview.model.ModelDataGraph;


/**
 * Created by i.larin on 11.04.2016.
 */
public abstract class BaseGraph {
    public int h;
    public int w;
    public int realW;
    public boolean isAnimationFinished;

    // Callback to backgroundView
    protected CallbackDrawGraph callbackToBack;

    // Sets data to your graph
    public abstract void setData(ModelDataGraph modelDataGraph);

    // Updates the offset if scrolling happens
    public abstract void updateOffset(float v);

    // Handling click by item number
    public abstract void click(int n);

    // You should return desired graph types here
    public abstract ViewType[] getSupportedGraphTypes();

    // Return true if want to enable zoom
    public abstract boolean getZoomPermission();

    // Return true if want to enable block info
    public abstract boolean getUsesBlockInfo();

    // Main drawing happens there
    public abstract void drawGraph(Canvas canvas);

    // Drawing on left panel happends there
    public abstract void drawOnLeftPanel(Canvas canvas);

    // Initial measuring happens here
    public abstract void measure();

    // If you need to measure some values with current offset
    // (called in onDraw)
    public abstract void measureWithOffset();

    public void setCallback(CallbackDrawGraph callbackDrawGrapg) {
        callbackToBack = callbackDrawGrapg;
    }

    public void setWH(int w, int h, int realW) {
        this.w = w;
        this.h = h;
        this.realW = realW;
    }

    public int getH() {
        return h;
    }

    public int getRealW() {
        return realW;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}

