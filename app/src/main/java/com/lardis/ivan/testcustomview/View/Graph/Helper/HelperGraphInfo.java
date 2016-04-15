package com.lardis.ivan.testcustomview.View.Graph.Helper;

import android.content.Context;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by i.larin on 11.04.2016.
 */
public class HelperGraphInfo {



    public static ModelDataGraph getLabel(Context context,ModelDataGraph modelDataGraph) {


        switch (modelDataGraph.getTypeViewGraph())

        {


            case MESH_MONTH_ITEM_WEEK:
                return calculateValueMeshMonthItemWeek(context,modelDataGraph);
            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
                return calculateMeshWeekItemDay(modelDataGraph);
            case MESH_WEEK_ITEM_WEEK:
                return calculateMeshWeekItemWeek(context,modelDataGraph);


            case MESH_DAY_ITEM_DAY:

                return calculateMeshDayItemDay(context,modelDataGraph);


            case MESH_MONTH_ITEM_MONTH:
                return calculateMeshMonthItemMonth(context,modelDataGraph);

        }
        return null;


    }


    private static ModelDataGraph calculateMeshDayItemDay(Context context,ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
       String[] shortMonthName = context.getResources().getStringArray(R.array.month);
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            arrayList.add(myCalendar.get(Calendar.DATE) + " " + shortMonthName[myCalendar.get(Calendar.MONTH)]);
            myCalendar.add(Calendar.DATE, 1);
        }
        modelDataGraph.setLabels1(arrayList);
        return modelDataGraph;

    }

    private static ModelDataGraph calculateMeshMonthItemMonth(Context context,ModelDataGraph modelDataGraph) {
        String[] shortMonthName = context.getResources().getStringArray(R.array.month);
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

    private static ModelDataGraph calculateMeshWeekItemWeek(Context context,ModelDataGraph modelDataGraph) {
        String[] shortMonthName = context.getResources().getStringArray(R.array.month);
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

    private static ModelDataGraph calculateValueMeshMonthItemWeek(Context context,ModelDataGraph modelDataGraph) {
        String[] shortMonthName = context.getResources().getStringArray(R.array.month);
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<String> arrayListLabel1 = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));
        arrayListLabel1.add(shortMonthName[myCalendar.get(Calendar.MONTH)]);
        int k = myCalendar.get(Calendar.MONTH);
        for (int i = 1; i < modelDataGraph.getArrayListGraph1().size(); i++) {
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


        ArrayList<String> arrayListLabel = new ArrayList<>();
        int monday = (-myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7 + 1;
        for (int i = 0; i < myCalendar.getActualMaximum(Calendar.DATE); i++)

        {
            if ((i) % 7 + 1 == monday)
                arrayListLabel.add(i + 1 + "");//для каждого понедельника месяца - записываем его число (DAY_OF_MONTH)
            else arrayListLabel.add("");// иначе пустую строку

        }

        modelDataGraph.setLabels1(arrayListLabel);
//        int daysMonth= myCalendar.getActualMaximum(Calendar.DATE);

        return modelDataGraph;
    }

    public static ArrayList<Float> getArrayWidthCoefficientMeshWeekItemDay(ModelDataGraph modelDataGraph) {

        ArrayList<Float> arrayList = new ArrayList<Float>();
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());

        int monday = (-myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7 + 1;
        if (monday > 1) arrayList.add((((float) monday - 1) / 7));
        int n = (myCalendar.getActualMaximum(Calendar.DATE) - (monday - 1)) / 7;
        for (int i = 0; i < n; i++) arrayList.add((float) 1);
        n = (myCalendar.getActualMaximum(Calendar.DATE) - (monday - 1)) % 7;
        arrayList.add((float) n / 7);

        return arrayList;

    }

    public static ArrayList<Float> getArrayWidthCoefficientMeshMonthItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(modelDataGraph.getYear(), modelDataGraph.getMonth(), modelDataGraph.getDay());
        ArrayList<Float> arrayList = new ArrayList<>();

        if (myCalendar.get(Calendar.DAY_OF_WEEK) == 1) myCalendar.add(Calendar.DATE, -6);
        else myCalendar.add(Calendar.DATE, (2 - myCalendar.get(Calendar.DAY_OF_WEEK)));
        arrayList.add((float) myCalendar.getActualMaximum(Calendar.DATE) + 1 - myCalendar.get(Calendar.DATE));
        int k = myCalendar.get(Calendar.MONTH);

        myCalendar.add(Calendar.DATE, 6);
        for (int i = 1; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            myCalendar.add(Calendar.DATE, 7);
            if (k != myCalendar.get(Calendar.MONTH))

            {
                arrayList.add((float) myCalendar.getActualMaximum(Calendar.DATE));
                k = myCalendar.get(Calendar.MONTH);
            }

        }
        arrayList.set(arrayList.size() - 1, (float) myCalendar.get(Calendar.DATE));

        return arrayList;
    }

    public static ArrayList<Float> getArrayWidthCoefficient(ModelDataGraph modelDataGraph) {

        switch (modelDataGraph.getTypeViewGraph())

        {


            case MESH_MONTH_ITEM_WEEK:
                return getArrayWidthCoefficientMeshMonthItemWeek(modelDataGraph);
            case MESH_WEEK_ITEM_DAY_PERIOD_MONTH:
                return getArrayWidthCoefficientMeshWeekItemDay(modelDataGraph);
           default:
               return new ArrayList<>();



        }


    }

}
