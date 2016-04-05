package com.lardis.ivan.testcustomview.Model;

import com.lardis.ivan.testcustomview.Presenter.ModelPresenter;
import com.lardis.ivan.testcustomview.View.ModelActivity;
import com.lardis.ivan.testcustomview.View.ModelBlockInfo;
import com.lardis.ivan.testcustomview.View.ModelGraph;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.EnumTypeViewGraph;

import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class DB {
Otvet otvet;

    public void setOtvet(Otvet otvet) {
        this.otvet = otvet;
    }

    public void getData()
    {if(otvet!=null)

    {
        ModelGraph modelGraph=new ModelGraph(4,11,1993,testdanStolbValue6(),testdanStolbValue6(), EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
      ModelActivity modelActivity=new ModelActivity("Период","Время","Чтото еще","Итд","Итр","кууу",modelGraph,testdanForInfo());


ModelPresenter modelPresenter=new ModelPresenter(null,null,null,modelActivity,null);
otvet.work(modelPresenter);



    }




    }

   public interface Otvet

{  void work(ModelPresenter modelPresenter);}

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
