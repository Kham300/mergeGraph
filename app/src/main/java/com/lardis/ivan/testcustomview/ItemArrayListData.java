package com.lardis.ivan.testcustomview;

/**
 * Created by ivan on 14.03.2016.
 */
public class ItemArrayListData {
    public void setI(float i) {
        this.i = i;
    }

    float i;

    String name;

    public float getI() {
        return i;
    }
    public ItemArrayListData(String name, int i) {
        this.i = i;
        this.name = name;
    }
}
