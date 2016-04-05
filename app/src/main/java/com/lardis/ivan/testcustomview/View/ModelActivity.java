package com.lardis.ivan.testcustomview.View;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelActivity {
    ArrayList<ModelBlockInfo> arrayListBlockInfo;

    public ArrayList<ModelBlockInfo> getArrayListBlockInfo() {
        return arrayListBlockInfo;
    }

    public void setArrayListBlockInfo(ArrayList<ModelBlockInfo> arrayListBlockInfo) {
        this.arrayListBlockInfo = arrayListBlockInfo;
    }

    public ModelActivity(String detStatPeriod, String detStatSrednee, String detStatItogo, String detStatProideno, String detStatInfoMonth, String detStatSredneeZnazhenie, ModelGraph dataGraph, ArrayList<ModelBlockInfo> arrayListBlockInfo) {
        this.detStatPeriod = detStatPeriod;
        this.detStatSrednee = detStatSrednee;
        this.detStatItogo = detStatItogo;
        this.detStatProideno = detStatProideno;
        this.detStatInfoMonth = detStatInfoMonth;
        this.detStatSredneeZnazhenie = detStatSredneeZnazhenie;
        this.dataGraph = dataGraph;
        this.arrayListBlockInfo = arrayListBlockInfo;

    }

    public String getDetStatPeriod() {
        return detStatPeriod;
    }

    public void setDetStatPeriod(String detStatPeriod) {
        this.detStatPeriod = detStatPeriod;
    }

    public String getDetStatSrednee() {
        return detStatSrednee;
    }

    public void setDetStatSrednee(String detStatSrednee) {
        this.detStatSrednee = detStatSrednee;
    }

    public String getDetStatItogo() {
        return detStatItogo;
    }

    public void setDetStatItogo(String detStatItogo) {
        this.detStatItogo = detStatItogo;
    }

    public String getDetStatProideno() {
        return detStatProideno;
    }

    public void setDetStatProideno(String detStatProideno) {
        this.detStatProideno = detStatProideno;
    }

    public String getDetStatInfoMonth() {
        return detStatInfoMonth;
    }

    public void setDetStatInfoMonth(String detStatInfoMonth) {
        this.detStatInfoMonth = detStatInfoMonth;
    }

    public String getDetStatSredneeZnazhenie() {
        return detStatSredneeZnazhenie;
    }

    public void setDetStatSredneeZnazhenie(String detStatSredneeZnazhenie) {
        this.detStatSredneeZnazhenie = detStatSredneeZnazhenie;
    }

    public ModelGraph getDataGraph() {
        return dataGraph;
    }

    public void setDataGraph(ModelGraph dataGraph) {
        this.dataGraph = dataGraph;
    }

    private String detStatPeriod;
    private String detStatSrednee;
    private  String detStatItogo;
    private  String detStatProideno;
    private String detStatInfoMonth;
    private  String detStatSredneeZnazhenie;
    private ModelGraph dataGraph;





}
