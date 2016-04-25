package com.lardis.ivan.testcustomview.graphview.helpers;


import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by i.larin on 11.04.2016.
 */
public class HelperGraphInfo {
    private static final String[] months = {"Янв", "Фев", "Мар", "Апр", "Май",
            "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};

    public static boolean isGraph(ModelDataGraph modelDataGraph)

    {
        switch (modelDataGraph.getTypeViewGraph())

        {
            case MESH_MONTH_ITEM_WEEK:
                return true;

            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
                Calendar calendar = Calendar.getInstance();
                calendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
                if (modelDataGraph.getGraph1values().size() != calendar.getActualMaximum(Calendar.DATE))
                    return false;


                return true;
            case MESH_WEEK_ITEM_WEEK:
                return true;


            case MESH_DAY_ITEM_DAY:

                return true;


            case MESH_MONTH_ITEM_MONTH:
                return true;

            default:
                return false;


        }


    }

    public static ModelDataGraph getLabel(ModelDataGraph modelDataGraph) {


        switch (modelDataGraph.getTypeViewGraph())

        {


            case MESH_MONTH_ITEM_WEEK:
                return calculateValueMeshMonthItemWeek(modelDataGraph);
            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
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
        for (int i = 0; i < modelDataGraph.getGraph1values().size(); i++) {
            arrayList.add(myCalendar.get(Calendar.DATE) + " " + months[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.DATE, 1);
        }
        modelDataGraph.setLabels(arrayList);
        return modelDataGraph;

    }

    private static ModelDataGraph calculateMeshMonthItemMonth(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), 1);
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < modelDataGraph.getGraph1values().size(); i++) {

            arrayList.add(months[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.MONTH, 1);

        }
        modelDataGraph.setLabels(arrayList);
        return modelDataGraph;
    }

    private static ModelDataGraph calculateMeshWeekItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayListLabel1 = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));


        for (int i = 0; i < modelDataGraph.getGraph1values().size(); i++)


        {
            String currentLabel = myCalendar.get(Calendar.DATE)
                    + "." + ((myCalendar.get(Calendar.MONTH) < 10)
                    ? "0" + myCalendar.get(Calendar.MONTH) : myCalendar.get(Calendar.MONTH)) + " - ";
            myCalendar.add(Calendar.DATE, 6);

            currentLabel += myCalendar.get(Calendar.DATE)
                    + "." + ((myCalendar.get(Calendar.MONTH) < 10)
                    ? "0" + myCalendar.get(Calendar.MONTH) : myCalendar.get(Calendar.MONTH));

            arrayListLabel1.add(currentLabel);
            myCalendar.add(Calendar.DATE, 1);


        }
        modelDataGraph.setLabels(arrayListLabel1);


        return modelDataGraph;
    }

    private static ModelDataGraph calculateValueMeshMonthItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayListLabel1 = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));
        arrayListLabel1.add(months[myCalendar.get(Calendar.MONTH)]);
        int k = myCalendar.get(Calendar.MONTH);
        for (int i = 1; i < modelDataGraph.getGraph1values().size(); i++) {
            myCalendar.add(Calendar.DATE, 7);
            if (k != myCalendar.get(Calendar.MONTH))

            {
                arrayListLabel1.add(months[myCalendar.get(Calendar.MONTH)]);
                k = myCalendar.get(Calendar.MONTH);
            }

        }
        modelDataGraph.setLabels(arrayListLabel1);
        return modelDataGraph;
    }

    private static ModelDataGraph calculateMeshWeekItemDay(ModelDataGraph modelDataGraph) {

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());


        ArrayList<String> arrayListLabel = new ArrayList<>();
        int monday = (-myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7 + 1;
        for (int i = 0; i < myCalendar.getActualMaximum(Calendar.DATE); i++)

        {
            if ((i) % 7 + 1 == monday)
                arrayListLabel.add(i + 1 + "");//для каждого понедельника месяца - записываем его число (DAY_OF_MONTH)
            else arrayListLabel.add("");// иначе пустую строку

        }

        modelDataGraph.setLabels(arrayListLabel);
//        int daysMonth= myCalendar.getActualMaximum(Calendar.DATE);

        return modelDataGraph;
    }

    public static ArrayList<Float> getArrayWidthCoefficientMeshWeekItemDayPeriodMonth(ModelDataGraph modelDataGraph) {

        ArrayList<Float> arrayList = new ArrayList<Float>();
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());

        int monday = (-myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7 + 1;
        if (monday > 1) arrayList.add((((float) monday - 1)));
        int n = (myCalendar.getActualMaximum(Calendar.DATE) - (monday - 1)) / 7;
        for (int i = 0; i < n; i++) arrayList.add((float) 7);
        n = (myCalendar.getActualMaximum(Calendar.DATE) - (monday - 1)) % 7;
        arrayList.add((float) n);

        return arrayList;

    }

    public static ArrayList<Float> getArrayWidthCoefficientMeshMonthItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<Float> arrayList = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));
        arrayList.add(((float) myCalendar.getActualMaximum(Calendar.DATE) + 1 - myCalendar.get(Calendar.DATE)) / 7);
        int k = myCalendar.get(Calendar.MONTH);

        myCalendar.add(Calendar.DATE, 6);
        for (int i = 1; i < modelDataGraph.getGraph1values().size(); i++) {
            myCalendar.add(Calendar.DATE, 7);
            if (k != myCalendar.get(Calendar.MONTH))

            {
                arrayList.add((float) myCalendar.getActualMaximum(Calendar.DATE) / 7);
                k = myCalendar.get(Calendar.MONTH);
            }

        }
        arrayList.set(arrayList.size() - 1, (float) myCalendar.get(Calendar.DATE) / 7);

        return arrayList;
    }

    public static ArrayList<Float> getArrayWidthCoefficient(ModelDataGraph modelDataGraph) {

        switch (modelDataGraph.getTypeViewGraph())
        {
            case MESH_MONTH_ITEM_WEEK:
                return getArrayWidthCoefficientMeshMonthItemWeek(modelDataGraph);
            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
                return getArrayWidthCoefficientMeshWeekItemDayPeriodMonth(modelDataGraph);
            default:
                return new ArrayList<>();


        }


    }

}