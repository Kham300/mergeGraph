package com.lardis.ivan.testcustomview.model;


import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.helpers.HelperGraphInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
/*
    Class for data that is displayed by graphs
 */
public class ModelDataGraph {
    public ModelDataGraph(int day, int month, int year, ArrayList<Integer> graph1values, ArrayList<Integer> arrayListGraph2, ViewType typeViewGraph) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.graph1values = graph1values;
        this.typeViewGraph = typeViewGraph;
        this.labels = HelperGraphInfo.getLabel(this).getLabels();
        this.stripesPositions = HelperGraphInfo.getArrayWidthCoefficient(this);
    }

    // First day of the period
    private int day, month, year;

    // Main values for graphs
    private ArrayList<Integer> graph1values;
    // Additional values for graphs
    private ArrayList<Integer> graph2values;

    // Labels under stripes
    private ArrayList<String> labels;
    // Desired type of the graph
    private ViewType typeViewGraph;
    // Data for detailed block view
    public ArrayList<ModelBlockInfo> blockInfoValues;

    public ArrayList<Float> getStripesPositions() {
        return stripesPositions;
    }

    public void setStripesPositions(ArrayList<Float> stripesPositions) {
        this.stripesPositions = stripesPositions;
    }

    // Array of stripes to be drawn
    public ArrayList<Float> stripesPositions;

    // Goal value
    public int mGoal;

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

    public ArrayList<Integer> getGraph1values() {
        return graph1values;
    }

    public void setGraph1values(ArrayList<Integer> graph1values) {
        this.graph1values = graph1values;
    }

    public ArrayList<Integer> getGraph2values() {
        return graph2values;
    }

    public void setGraph2values(ArrayList<Integer> graph2values) {
        this.graph2values = graph2values;
    }

    public ViewType getTypeViewGraph() {
        return typeViewGraph;
    }

    public void setTypeViewGraph(ViewType typeViewGraph) {
        this.typeViewGraph = typeViewGraph;
    }

    public ArrayList<ModelBlockInfo> getArrayListForInfo() {
        return blockInfoValues;
    }

    public void setArrayListForInfo(ArrayList<ModelBlockInfo> arrayListForInfo) {
        this.blockInfoValues = arrayListForInfo;
    }

    public int getmGoal() {
        return mGoal;
    }

    public void setmGoal(int mGoal) {
        this.mGoal = mGoal;
    }
}
