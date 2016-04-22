package com.lardis.ivan.testcustomview.graphview.base;

import java.util.ArrayList;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface CallbackDrawGraph {
    void sendInvalidate();

    void scrollTo(int scrollX);

    void updateDrawByQ(float mywidth, int n, float ofssetborder);

    float getTextSize();
}

