package com.lardis.ivan.testcustomview.Model;

import com.lardis.ivan.testcustomview.View.Graph.EnumTypeViewGraph;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelDataGraph {
    public ModelDataGraph(int day, int month, int year, ArrayList<Integer> arrayListGraph1, ArrayList<Integer> arrayListGraph2, EnumTypeViewGraph typeViewGraph) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.arrayListGraph1 = arrayListGraph1;
        this.arrayListGraph2 = arrayListGraph2;

        this.typeViewGraph = typeViewGraph;
    }


    private int day;
    private int month;
    private int year;
    private ArrayList<Integer> arrayListGraph1 = null;
    private ArrayList<Integer> arrayListGraph2 = null;


    private ArrayList<String> labels = null;
    private EnumTypeViewGraph typeViewGraph;

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

    public EnumTypeViewGraph getTypeViewGraph() {
        return typeViewGraph;
    }

    public void setTypeViewGraph(EnumTypeViewGraph typeViewGraph) {
        this.typeViewGraph = typeViewGraph;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }
}
