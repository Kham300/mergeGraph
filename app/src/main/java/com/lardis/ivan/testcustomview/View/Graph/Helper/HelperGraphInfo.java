package com.lardis.ivan.testcustomview.View.Graph.Helper;

import android.util.Log;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by i.larin on 11.04.2016.
 */
public class HelperGraphInfo {
    static String[] shortMonthName = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};

    public static ModelDataGraph getLabel(ModelDataGraph modelDataGraph) {


        switch (modelDataGraph.getTypeViewGraph())

        {


            case MESH_MONTH_ITEM_WEEK:
                return calculateValueMeshMonthItemWeek(modelDataGraph);
            case MESH_WEEK_ITEM_DAY:
                return calculateMeshWeekItemDay(modelDataGraph);
            case MESH_WEEK_ITEM_WEEK:
                return calculateMeshWeekItemWeek(modelDataGraph);


            case MESH_DAY_ITEM_DAY:

                return calculateMeshDayItemDay(modelDataGraph);


            case MESH_MONTH_ITEM_MONTH:
                return calculateMeshMonthItemMonth(modelDataGraph);

        }
        return null;


    }


    private static ModelDataGraph calculateMeshDayItemDay(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            arrayList.add(myCalendar.get(Calendar.DATE) + " " + shortMonthName[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.DATE, 1);
        }
        modelDataGraph.setLabels1(arrayList);
        return modelDataGraph;

    }

    private static ModelDataGraph calculateMeshMonthItemMonth(ModelDataGraph modelDataGraph) {

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), 1);
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {

            arrayList.add(shortMonthName[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.MONTH, 1);

        }
        modelDataGraph.setLabels1(arrayList);
        return modelDataGraph;
    }

    private static ModelDataGraph calculateMeshWeekItemWeek(ModelDataGraph modelDataGraph) {

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayListLabel1 = new ArrayList<>();
        ArrayList<String> arrayListLabel2 = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));


        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++)


        {
            arrayListLabel1.add(myCalendar.get(Calendar.DATE) + " " + shortMonthName[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.DATE, 6);

            arrayListLabel2.add(myCalendar.get(Calendar.DATE) + " " + shortMonthName[myCalendar.get(Calendar.MONTH)]);

            myCalendar.add(Calendar.DATE, 1);


        }
        modelDataGraph.setLabels1(arrayListLabel1);
        modelDataGraph.setLabels2(arrayListLabel2);


        return modelDataGraph;
    }

    private static ModelDataGraph calculateValueMeshMonthItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayListLabel1 = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));
        arrayListLabel1.add(shortMonthName[myCalendar.get(Calendar.MONTH)]);
        int k = myCalendar.get(Calendar.MONTH);
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            myCalendar.add(Calendar.DATE, 7);
            if (k != myCalendar.get(Calendar.MONTH))

            {
                arrayListLabel1.add(shortMonthName[myCalendar.get(Calendar.MONTH)]);
                k = myCalendar.get(Calendar.MONTH);
            }

        }
        modelDataGraph.setLabels1(arrayListLabel1);
        return modelDataGraph;
    }

    private static ModelDataGraph calculateMeshWeekItemDay(ModelDataGraph modelDataGraph) {

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        if (myCalendar.getActualMaximum(Calendar.DATE) != modelDataGraph.getArrayListGraph1().size())
            return null;

        ArrayList<String> arrayListLabel = new ArrayList<>();
        int monday = (-myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7 + 1;
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++)

        {
            if ((i + 1) % 7 == monday)
                arrayListLabel.add(i + 1 + "");//для каждого понедельника месяца - записываем его число (DAY_OF_MONTH)
            else arrayListLabel.add("");// иначе пустую строку

        }

        modelDataGraph.setLabels1(arrayListLabel);
//        int daysMonth= myCalendar.getActualMaximum(Calendar.DATE);

        return modelDataGraph;
    }


}
