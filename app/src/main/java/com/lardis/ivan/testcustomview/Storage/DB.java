package com.lardis.ivan.testcustomview.Storage;

import android.util.Log;

import com.lardis.ivan.testcustomview.EnumActivitySpinner1;
import com.lardis.ivan.testcustomview.EnumActivitySpinner2;
import com.lardis.ivan.testcustomview.EnumActivitySpinner3;
import com.lardis.ivan.testcustomview.View.ModelObjectActivityWithoutSpiners;

import com.lardis.ivan.testcustomview.View.ModelObjectSpinners;
import com.lardis.ivan.testcustomview.merge.ModelDataGraph;
import com.lardis.ivan.testcustomview.merge.EnumTypeViewGraph;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopViewZoomInfoGraph.MyInfoView.ModelBlockInfo;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class DB {
    Otvet otvet;
    ModelDataGraph modelGraph;  ModelObjectActivityWithoutSpiners modelActivityWithoutSpiners;
    public void setOtvet(Otvet otvet) {
        this.otvet = otvet;
    }

    public void getData(EnumActivitySpinner1 enumActivitySpinner1,EnumActivitySpinner2 enumActivitySpinner2,EnumActivitySpinner3 enumActivitySpinner3) {

        Log.d("Mylog1", "enumActivitySpinner1=" + enumActivitySpinner1 + "enumActivitySpinner2=" + enumActivitySpinner2 + "enumActivitySpinner3=" + enumActivitySpinner3);


        if (otvet != null)

        {


            switch (enumActivitySpinner3)
            {
                case SLEEP:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue5(),  null, EnumTypeViewGraph.MESH_DAY_ITEM_DAY);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("SLEEP", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case PRESSURE:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue6(), testdanStolbValue6(), EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("PRESSURE", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case CALORIES:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue6(), null , EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("CALORIES", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case WATER:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue222(),null, EnumTypeViewGraph.MESH_WEEK_ITEM_DAY);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("WATER", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case WEIGHT:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue222(), testdanStolbValue222(), EnumTypeViewGraph.MESH_WEEK_ITEM_WEEK);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("WEIGHT", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case PULSE:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue6(), testdanStolbValue6(), EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("PULSE", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

                case STEP:
                    modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue222(), null, EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
                    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("STEP", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;


                case DISTANCE:
                      modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue6(), null, EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
                      modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("DISTANCE", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

                    break;

default:

    modelGraph = new ModelDataGraph(4, 11, 1993,  testdanStolbValue222(),null, EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
    modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("Default", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

            }


            otvet.setDataModelActivityWithoutSpiners(modelActivityWithoutSpiners);


        }


    }
    public void getTitleSpinner()
    {
        ArrayList<EnumActivitySpinner3> arrayList3=new ArrayList<>();
        ArrayList<EnumActivitySpinner1> arrayList1=new ArrayList<>();
        ArrayList<EnumActivitySpinner2> arrayList2=new ArrayList<>();
        arrayList3.add(EnumActivitySpinner3.CALORIES);
        arrayList3.add(EnumActivitySpinner3.PULSE);
        arrayList3.add(EnumActivitySpinner3.PRESSURE);
        arrayList3.add(EnumActivitySpinner3.SLEEP);
        arrayList3.add(EnumActivitySpinner3.DISTANCE );
        arrayList3.add(EnumActivitySpinner3.STEP );
        arrayList3.add(EnumActivitySpinner3.WATER );
        arrayList3.add(EnumActivitySpinner3.WEIGHT );

        arrayList1.add(EnumActivitySpinner1.HALF);
        arrayList1.add(EnumActivitySpinner1.MONTH);
        arrayList1.add(EnumActivitySpinner1.WEEK);
        arrayList2.add(EnumActivitySpinner2.BY_DAY);
        arrayList2.add(EnumActivitySpinner2.BY_MONTH);
        if(otvet!=null) {
            otvet.setTitleSpinner(new ModelObjectSpinners(arrayList1, arrayList2, arrayList3));
            modelGraph = new ModelDataGraph(4, 11, 1993, testdanStolbValue5(),  null, EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
            modelActivityWithoutSpiners = new ModelObjectActivityWithoutSpiners("SLEEP", "Время", "Чтото еще", "Итд", "Итр", "кууу", modelGraph, testdanForInfo());

            otvet.setDataModelActivityWithoutSpiners(modelActivityWithoutSpiners);
        }
    }

    public interface Otvet

    {
        void setDataModelActivityWithoutSpiners(ModelObjectActivityWithoutSpiners ModelActivityWithoutSpiners);
        void setTitleSpinner(ModelObjectSpinners modelSpinners);
    }

//    String detStatPeriod, String detStatSrednee, String detStatItogo, String detStatProideno, String detStatInfoMonth, String detStatSredneeZnazhenie, int day, int month, int year, ArrayList<Integer> arrayListGraph1, ArrayList<Integer> arrayListGraph2, EnumTypeViewGraph typeViewGraph,ArrayList<ModelBlockInfo> blockInfoArrayList


    private ArrayList<Integer> testdanStolbValue6() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(246);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);

        arrayList.add(383);

        arrayList.add(157);
        arrayList.add(54);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);

        arrayList.add(157);
        arrayList.add(54);
        arrayList.add(13);

        arrayList.add(157);
        arrayList.add(54);
        return arrayList;
    }

    public ArrayList<ModelBlockInfo> testdanForInfo() {
        ArrayList<ModelBlockInfo> arrayList = new ArrayList<>();
        arrayList.add(new ModelBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new ModelBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new ModelBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new ModelBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new ModelBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new ModelBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new ModelBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new ModelBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new ModelBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new ModelBlockInfo("45:78", "23", "56", "12", "43"));

        arrayList.add(new ModelBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new ModelBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new ModelBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new ModelBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new ModelBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new ModelBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new ModelBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new ModelBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new ModelBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new ModelBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new ModelBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new ModelBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new ModelBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new ModelBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new ModelBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new ModelBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new ModelBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new ModelBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new ModelBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new ModelBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new ModelBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new ModelBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new ModelBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new ModelBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new ModelBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new ModelBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new ModelBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new ModelBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new ModelBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new ModelBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new ModelBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new ModelBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new ModelBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new ModelBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new ModelBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new ModelBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new ModelBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new ModelBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new ModelBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new ModelBlockInfo("45:78", "23", "56", "12", "43"));


        return arrayList;
    }

    private ArrayList<Integer> testdanStolbValue() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(54);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(1777);
        arrayList.add(10);
        arrayList.add(154);
        arrayList.add(864);
        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(157);

        arrayList.add(54);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(1777);
        arrayList.add(10);
        arrayList.add(154);
        arrayList.add(864);
        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(157);
        return arrayList;
    }

    private ArrayList<Integer> testdanStolbValue222() {
        ArrayList<Integer> arrayList = new ArrayList<>();


        arrayList.add(1777);
        arrayList.add(10);
        arrayList.add(154);
        arrayList.add(864);
        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(54);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(1777);
        arrayList.add(10);
        arrayList.add(154);
        arrayList.add(864);
        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(157);
        arrayList.add(157);
        arrayList.add(54);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        return arrayList;
    }

    private ArrayList<Integer> testdanStolbValue1() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(424);
        arrayList.add(224);
        arrayList.add(124);
        arrayList.add(224);
        arrayList.add(424);
        arrayList.add(124);

        arrayList.add(424);
        arrayList.add(224);
        arrayList.add(124);
        arrayList.add(224);
        arrayList.add(424);
        arrayList.add(124);

        arrayList.add(424);
        arrayList.add(224);
        arrayList.add(124);
        arrayList.add(224);
        arrayList.add(424);
        arrayList.add(124);


        return arrayList;
    }

    private ArrayList<Integer> testdanStolbValue5() {
        ArrayList<Integer> arrayList = new ArrayList<>();


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        return arrayList;
    }

    private ArrayList<Integer> testdanStolbValueDataInMonth() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(364);
        arrayList.add(364);


        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);

        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);

        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);

        arrayList.add(1224);
        arrayList.add(514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);

        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);


        arrayList.add(444);
        arrayList.add(777);


        return arrayList;
    }
}
