package com.lardis.ivan.testcustomview.View.Graph;

import java.util.ArrayList;

/**
 * Created by i.larin on 11.04.2016.
 */
public interface CallbackDrawGraph {
    void sendPostInvalidate(long delay);

    void scrollTo(int scrollX);

    void updateDrawByQ(float mywidth, int n, float ofssetborder);

    void updateDrawByArrayList(ArrayList<?> arrayList, float ofssetborder);

}

