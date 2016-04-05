package com.lardis.ivan.testcustomview.Model;

import com.lardis.ivan.testcustomview.View.DataBlockInfo;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.enumTypeViewGraph;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelActivity {
Otvet otvet;

    public void setOtvet(Otvet otvet) {
        this.otvet = otvet;
    }

    public void getData()
    {if(otvet!=null)

    {

otvet.work("Период","Время","Чтото еще","Итд","Итр","кууу",4,11,1993,testdanStolbValue6(),testdanStolbValue6(),enumTypeViewGraph.MESH_MONTH_ITEM_MONTH,testdanForInfo());
//otvet.work("Период","Время","Чтото еще","Итд","Итр","кууу",4,11,1993,testdanStolbValue6(),testdanStolbValue6(),enumTypeViewGraph.MESH_MONTH_ITEM_MONTH,null);




    }




    }

   public interface Otvet

{  void work(String detStatPeriod, String detStatSrednee, String detStatItogo, String detStatProideno, String detStatInfoMonth, String detStatSredneeZnazhenie, int day, int month, int year, ArrayList<Integer> arrayListGraph1, ArrayList<Integer> arrayListGraph2, enumTypeViewGraph typeViewGraph,ArrayList<DataBlockInfo> blockInfoArrayList);}







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

    public ArrayList<DataBlockInfo> testdanForInfo() {
        ArrayList<DataBlockInfo> arrayList = new ArrayList<>();
        arrayList.add(new DataBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new DataBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new DataBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new DataBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new DataBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new DataBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new DataBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new DataBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new DataBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new DataBlockInfo("45:78", "23", "56", "12", "43"));

        arrayList.add(new DataBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new DataBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new DataBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new DataBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new DataBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new DataBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new DataBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new DataBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new DataBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new DataBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new DataBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new DataBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new DataBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new DataBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new DataBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new DataBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new DataBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new DataBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new DataBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new DataBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new DataBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new DataBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new DataBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new DataBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new DataBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new DataBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new DataBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new DataBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new DataBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new DataBlockInfo("45:78", "23", "56", "12", "43"));
        arrayList.add(new DataBlockInfo("12:32", "12", "53", "23", "12"));
        arrayList.add(new DataBlockInfo("11:43", "43", "23", "23", "54"));
        arrayList.add(new DataBlockInfo("6:56", "45", "23", "98", "54"));
        arrayList.add(new DataBlockInfo("65:12", "43", "65", "12", "76"));
        arrayList.add(new DataBlockInfo("1:32", "12", "65", "12", "65"));
        arrayList.add(new DataBlockInfo("65:12", "34", "56", "54", "12"));
        arrayList.add(new DataBlockInfo("56:12", "76", "12", "54", "87"));
        arrayList.add(new DataBlockInfo("87:54", "23", "65", "23", "65"));
        arrayList.add(new DataBlockInfo("54:54", "23", "98", "54", "67"));
        arrayList.add(new DataBlockInfo("45:78", "23", "56", "12", "43"));


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
