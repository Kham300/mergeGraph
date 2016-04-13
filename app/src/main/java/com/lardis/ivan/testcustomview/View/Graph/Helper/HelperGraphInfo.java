package com.lardis.ivan.testcustomview.View.Graph.Helper;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by i.larin on 11.04.2016.
 */
public class HelperGraphInfo {
    public static Calendar myCalendar = (Calendar) Calendar.getInstance();
    static   String[] shortMonthName = {"Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"};

    public static ArrayList<String> getLabel(ModelDataGraph modelDataGraph) {
        int day = modelDataGraph.getDay();
        int month = modelDataGraph.getMonth();
        int year = modelDataGraph.getYear();
        ArrayList<Integer> arrayList = modelDataGraph.getArrayListGraph1();

        switch (modelDataGraph.getTypeViewGraph())

        {




            case MESH_DAY_ITEM_DAY:

                return calculateMeshDayItemDay(day, month, year, arrayList);



            case MESH_MONTH_ITEM_MONTH:
                return calculateMeshMonthItemMonth(day, month, year, arrayList);

        }
        return null;


    }


    private  static ArrayList<String> calculateMeshDayItemDay(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {


        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {

            arrayList.add(day + " " + shortMonthName[month]);

            if (day == max_date) {
                day = 1;

                if (month == 11) month = 0;
                else month++;
                myCalendar.set(year, month, day);
                max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } else day++;


        }
        return arrayList;

    }

    private static ArrayList<String> calculateMeshMonthItemMonth(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {
        ArrayList<String> arrayList = new ArrayList<>();

        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {


            arrayList.add(shortMonthName[month]);

            if (month == 11) {
                month = 0;


            } else month++;
        }
        return arrayList;
    }


}
