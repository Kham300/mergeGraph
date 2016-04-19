package com.lardis.ivan.testcustomview.Model;

import com.lardis.ivan.testcustomview.View.Graph.EnumTypeViewGraph;
import com.lardis.ivan.testcustomview.View.Graph.Helper.HelperGraphInfo;

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
        labels1 = HelperGraphInfo.getLabel(this).getLabels1();

    }


    private int day;
    private int month;
    private int year;
    private ArrayList<Integer> arrayListGraph1;
    private ArrayList<Integer> arrayListGraph2;
    private ArrayList<String> labels1;
    private ArrayList<String> labels2;
    private EnumTypeViewGraph typeViewGraph;

    public ArrayList<String> getLabels1() {
        return labels1;
    }

    public void setLabels1(ArrayList<String> labels1) {
        this.labels1 = labels1;
    }

    public ArrayList<String> getLabels2() {
        return labels2;
    }

    public void setLabels2(ArrayList<String> labels2) {
        this.labels2 = labels2;
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

    public EnumTypeViewGraph getTypeViewGraph() {
        return typeViewGraph;
    }

    public void setTypeViewGraph(EnumTypeViewGraph typeViewGraph) {
        this.typeViewGraph = typeViewGraph;
    }

}
