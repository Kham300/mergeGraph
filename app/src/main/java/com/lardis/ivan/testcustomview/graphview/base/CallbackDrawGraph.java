package com.lardis.ivan.testcustomview.graphview.base;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface CallbackDrawGraph {
    void sendInvalidate();

    void scrollTo(int scrollX);

    void updateDrawByQ(float mywidth, float ofssetborder);

    float getTextSize();
}

