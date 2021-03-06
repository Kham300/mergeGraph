package com.lardis.ivan.testcustomview.View.Graph;

import android.graphics.Canvas;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;

/**
 * Created by i.larin on 11.04.2016.
 */
public abstract class BaseGraph {
    protected int h;
    protected int w;

    protected abstract void setData(ModelDataGraph modelDataGraph);
    protected abstract void updateOffset(float v);
    protected abstract void click(int n);
    protected abstract void setCallback(CallbackDrawGraph callbackDrawGrapg);
    protected abstract void draw(Canvas canvas);

    void setWH(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getH() {
        return h;
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

