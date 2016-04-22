package com.lardis.ivan.testcustomview.graphview.graphtypes.columng;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.lardis.ivan.testcustomview.graphview.base.BaseGraph;
import com.lardis.ivan.testcustomview.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;


/**
 * Created by aleksey.ivanov on 11.04.2016.
 */
public class GraphPunct extends BaseGraph {
    public GraphPunct(Context context, CallbackDrawGraph callbackDrawGraph, AttributeSet attrs) {
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {

    }

    @Override
    public void updateOffset(float v) {

    }

    @Override
    public void click(int n) {

    }

    @Override
    public void setCallback(CallbackDrawGraph callbackDrawGrapg) {

    }

    @Override
    public ViewType[] getSupportedGraphTypes() {
        return new ViewType[0];
    }

    @Override
    public boolean getZoomPermission() {
        return false;
    }

    @Override
    public boolean getUsesBlockInfo() {
        return false;
    }

    @Override
    public void drawGraph(Canvas canvas) {

    }

    @Override
    public void drawOnLeftPanel(Canvas canvas) {

    }

    @Override
    public void measure() {

    }

    @Override
    public void measureWithOffset() {

    }
}
