package com.lardis.ivan.testcustomview.View.Graph;

import android.graphics.Canvas;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;

/**
 * Created by i.larin on 11.04.2016.
 */
public abstract class BaseGraph {
    protected int h;
    protected int w;
    protected int realW;
    protected boolean isAnimationFinished;

    protected abstract void setData(ModelDataGraph modelDataGraph);


    protected abstract void updateOffset(float v);
    protected abstract void click(int n);
    protected abstract void setCallback(CallbackDrawGraph callbackDrawGrapg);
    protected abstract void drawGraph(Canvas canvas);
    protected abstract void drawTopLines(Canvas canvas);
    protected abstract void measure();
    protected abstract void measureWithOffset();
    void setWH(int w, int h, int realW) {
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

