package com.lardis.ivan.diagramslibrary.graphview.base;

/**
 * Shows how graphs could communicate with view
 */
public interface CallbackDrawGraph {
    void sendInvalidate();

    void scrollTo(int scrollX);

    void updateDrawByQ(float mywidth, float ofssetborder);

    float getTextSize();


}

