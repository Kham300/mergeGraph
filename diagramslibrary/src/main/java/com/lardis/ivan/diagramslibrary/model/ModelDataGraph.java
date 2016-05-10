package com.lardis.ivan.diagramslibrary.model;


import com.lardis.ivan.diagramslibrary.graphview.base.ViewType;
import com.lardis.ivan.diagramslibrary.graphview.helpers.HelperGraphInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
/*
    Class for data that is displayed by graphs
 */
public class ModelDataGraph {
    public ModelDataGraph(int day, int month, int year, ArrayList<Integer> graph1values,
                          ArrayList<Integer> graph2values, ArrayList<ArrayList<Integer>> values,
                          ViewType typeViewGraph) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.graph1values = graph1values;
        this.graph2values = graph2values;
        this.typeViewGraph = typeViewGraph;
        this.setValues(values);
        this.labels = HelperGraphInfo.getLabel(this).getLabels();
        this.stripesPositions = HelperGraphInfo.getArrayWidthCoefficient(this);

        if (graph1values != null && graph2values != null) {
            // Trimming if there are too much data for one month
            if (typeViewGraph == ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH
                    && graph1values.size() > labels.size())
                graph1values.subList(labels.size(), graph1values.size()).clear();
            if (typeViewGraph == ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH
                    && (graph2values.size() > labels.size()))
                graph2values.subList(labels.size(), graph2values.size()).clear();
        }

        // Update size of graph according to graph1values AND optional values
        if (graph1values != null)
            datasetSize = graph1values.size();
        else 
            datasetSize = values.get(0).size();
    }

    // First day of the period
    private int day, month, year;

    // Size of data (for background)
    private int datasetSize;

    // Main values for graphs
    private ArrayList<Integer> graph1values;
    // Additional values for graphs
    private ArrayList<Integer> graph2values;

    // Advanced data for multiline graphs
    private ArrayList<ArrayList<Integer>> values;

    // Colors data for multiline graphs
    private ArrayList<Integer> colors;

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

    public ArrayList<ArrayList<Integer>> getValues() {
        return values;
    }

    public void setValues(ArrayList<ArrayList<Integer>> values) {
        if (values != null && typeViewGraph != ViewType.MESH_MONTH_ITEM_WEEK)
            throw new IllegalStateException("Multiline graphs now supports only MESH_MONTH_ITEM_WEEK");
        this.values = values;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Integer> colors) {
        if (typeViewGraph != ViewType.MESH_MONTH_ITEM_WEEK)
            throw new IllegalStateException("Multiline graphs are now supported only for MESH_MONTH_ITEM_WEEK");
        this.colors = colors;
    }


    public int getDatasetSize() {
        return datasetSize;
    }

    public void setDatasetSize(int datasetSize) {
        this.datasetSize = datasetSize;
    }
}
