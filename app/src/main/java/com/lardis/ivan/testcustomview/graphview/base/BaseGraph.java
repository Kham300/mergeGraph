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

    public abstract void setData(ModelDataGraph modelDataGraph);

    public abstract void updateOffset(float v);
    public abstract void click(int n);
    public abstract void setCallback(CallbackDrawGraph callbackDrawGrapg);
    public abstract ViewType[] getSupportedGraphTypes();
    public abstract boolean getZoomPermission();
    public abstract boolean getUsesBlockInfo();
    public abstract void drawGraph(Canvas canvas);
    public abstract void drawOnLeftPanel(Canvas canvas);
    public abstract void measure();
    public abstract void measureWithOffset();

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

