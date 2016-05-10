package com.lardis.ivan.diagramslibrary.graphview.graphtypes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.lardis.ivan.diagramslibrary.graphview.base.BaseGraph;
import com.lardis.ivan.diagramslibrary.graphview.base.CallbackDrawGraph;
import com.lardis.ivan.diagramslibrary.graphview.base.ViewType;
import com.lardis.ivan.diagramslibrary.model.ModelDataGraph;


/**
 * Created by aleksey.ivanov on 11.04.2016.
 */
public class ExampleGraph extends BaseGraph {
    ModelDataGraph data;

    public ExampleGraph(Context context,  AttributeSet attrs) {
        this.isAnimationFinished = true;
    }


    @Override
    public void setData(ModelDataGraph modelDataGraph) {
        data = modelDataGraph;
    }

    @Override
    public void updateOffset(float v) {

    }

    @Override
    public void click(int n) {

    }

    @Override
    public void setCallback(CallbackDrawGraph callbackDrawGrapg) {
        super.setCallback(callbackDrawGrapg);
        measure();
    }

    @Override
    public ViewType[] getSupportedViewTypes() {
        return new ViewType[]{ViewType.MESH_DAY_ITEM_DAY,
                ViewType.MESH_WEEK_ITEM_WEEK,
                ViewType.MESH_MONTH_ITEM_MONTH,
                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH,
                ViewType.MESH_MONTH_ITEM_WEEK};
    }

    @Override
    public boolean getZoomPermission() {
        return true;
    }

    @Override
    public boolean getUsesBlockInfo() {
        return false;
    }

    @Override
    public boolean requestsUpperSelection() {
        return false;
    }

    @Override
    public boolean requestsLeftAndTopPanel() {
        return true;
    }

    @Override
    public float getArrowsYPosition() {
        return (float) (footerHeight + 0.02 * h);
    }

    @Override
    public void drawGraph(Canvas canvas) {

    }

    @Override
    public void drawOnLeftPanel(Canvas canvas) {

    }

    @Override
    public void measure() {
        if (w != 0 && h != 0 && callbackToBack != null) {
            callbackToBack.updateDrawByQ(150, 50);

            realW = 150 * data.getDatasetSize();
        }
    }

    @Override
    public void measureWithOffset() {

    }
}
