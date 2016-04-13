package com.lardis.ivan.testcustomview.View.Graph;

import java.util.ArrayList;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface CallbackDrawGraph {
    void sendPostInvalidate(long delay);
    void updateColorTriangle(int color);
    void updateLineColor(int color);
    void scrollTo(int scrollX);

}

