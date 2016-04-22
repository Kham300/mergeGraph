package com.lardis.ivan.testcustomview.model;


import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperGraphInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelDataGraph {
    public ModelDataGraph(int day, int month, int year, ArrayList<Integer> arrayListGraph1, ArrayList<Integer> arrayListGraph2, ViewType typeViewGraph) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.arrayListGraph1 = arrayListGraph1;
        this.typeViewGraph = typeViewGraph;
        labels = HelperGraphInfo.getLabel(this).getLabels();

    }

    private int day;
    private int month;
    private int year;
    private ArrayList<Integer> arrayListGraph1;
    private ArrayList<Integer> arrayListGraph2;
    private ArrayList<String> labels;
    private ViewType typeViewGraph;
    public ArrayList<ModelBlockInfo> arrayListForInfo;

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Integer> getArrayListGraph1() {
        return arrayListGraph1;
    }

    public void setArrayListGraph1(ArrayList<Integer> arrayListGraph1) {
        this.arrayListGraph1 = arrayListGraph1;
    }

    public ArrayList<Integer> getArrayListGraph2() {
        return arrayListGraph2;
    }

    public void setArrayListGraph2(ArrayList<Integer> arrayListGraph2) {
        this.arrayListGraph2 = arrayListGraph2;
    }

    public ViewType getTypeViewGraph() {
        return typeViewGraph;
    }

    public void setTypeViewGraph(ViewType typeViewGraph) {
        this.typeViewGraph = typeViewGraph;
    }

    public ArrayList<ModelBlockInfo> getArrayListForInfo() {
        return arrayListForInfo;
    }

    public void setArrayListForInfo(ArrayList<ModelBlockInfo> arrayListForInfo) {
        this.arrayListForInfo = arrayListForInfo;
    }

}
