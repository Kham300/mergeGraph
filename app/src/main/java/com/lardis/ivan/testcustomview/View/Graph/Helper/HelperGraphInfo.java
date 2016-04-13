package com.lardis.ivan.testcustomview.View.Graph.Helper;

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
                break;
            case MESH_WEEK_ITEM_DAY:
                break;
            case MESH_WEEK_ITEM_WEEK:
                return      calculateMeshWeekItemWeek(modelDataGraph);


            case MESH_DAY_ITEM_DAY:

                return calculateMeshDayItemDay( modelDataGraph);


            case MESH_MONTH_ITEM_MONTH:
                return calculateMeshMonthItemMonth( modelDataGraph);

        }
        return null;


    }


    private static ModelDataGraph calculateMeshDayItemDay(ModelDataGraph modelDataGraph) {
        Calendar myCalendar = (Calendar) Calendar.getInstance();
        int day = modelDataGraph.getDay();
        int month = modelDataGraph.getMonth();
        int year = modelDataGraph.getYear();
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            arrayList.add(day + " " + shortMonthName[month]);
            if (day == max_date) {
                day = 1;

                if (month == 11) month = 0;
                else month++;

                myCalendar.set(year, month, day);
                max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } else day++;
        }
        modelDataGraph.setLabels1(arrayList);
        return modelDataGraph;

    }

    private static ModelDataGraph calculateMeshMonthItemMonth(ModelDataGraph modelDataGraph) {
        int month = modelDataGraph.getMonth();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            arrayList.add(shortMonthName[month]);

            if (month == 11) {
                month = 0;


            } else month++;
        }
        modelDataGraph.setLabels1(arrayList);
        return modelDataGraph;
    }

//    private void calculateValueMeshMonthItemWeek(int day, int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1, ArrayList<Integer> arrayListMetodDrawGraph2) {
//        Calendar myCalendar = (Calendar) Calendar.getInstance();
//        int days;
//        int DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
//        days = arrayListMetodDrawGraph1.size() * 7;
//        if (DAY_OF_WEEK != 1) day = day + 2 - DAY_OF_WEEK;
//        else {
//            day = day - 6;
//        }
//        if (day <= 0) {
//            month--;
//            myCalendar.set(2016, month, 1);
//            day = myCalendar.getActualMaximum(Calendar.DATE) + day;
//        }
//        int bufMonth = month;
//        myCalendar.set(year, bufMonth, day);
//        days = days - (myCalendar.getActualMaximum(Calendar.DATE) - day + 1);
//        daysInPunctArrayList.add(myCalendar.getActualMaximum(Calendar.DATE) - day + 1);
//        arrayListName.add(shortMonthName[bufMonth] + "");
//        bufMonth++;
//        while (days > 0) {
//            myCalendar.set(year, bufMonth, day);
//            arrayListName.add(shortMonthName[bufMonth] + "");
//            if (days < myCalendar.getActualMaximum(Calendar.DATE)) {
//                daysInPunctArrayList.add(days);
//                days = 0;
//            } else {
//                daysInPunctArrayList.add(myCalendar.getActualMaximum(Calendar.DATE));
//                days = days - myCalendar.getActualMaximum(Calendar.DATE);
//            }
//            if (bufMonth == 11) {
//                year++;
//                bufMonth = 0;
//            } else
//                bufMonth++;
//        }
//        nBlock = daysInPunctArrayList.size();
//        if (daysInPunctArrayList.get(0) < 14) {
//            daysInPunctArrayList.set(0, daysInPunctArrayList.get(0) + 14);
//            if (twoGraph) {
//                arrayListMetodDrawGraph2.add(0, 0);
//                arrayListMetodDrawGraph2.add(0, 0);
//            }
//            arrayListMetodDrawGraph1.add(0, 0);
//            arrayListMetodDrawGraph1.add(0, 0);
//        }
//        if (daysInPunctArrayList.get(daysInPunctArrayList.size() - 1) < 14) {
//            daysInPunctArrayList.set(daysInPunctArrayList.size() - 1, daysInPunctArrayList.get(daysInPunctArrayList.size() - 1) + 14);
//
//            arrayListMetodDrawGraph1.add(0);
//            arrayListMetodDrawGraph1.add(0);
//            if (twoGraph) {
//                arrayListMetodDrawGraph2.add(0);
//                arrayListMetodDrawGraph2.add(0);
//            }
//        }
//    }

//    private void calculateMeshWeekItemDay(int month, int year, ArrayList<Integer> arrayListMetodDrawGraph1) {
//        Calendar myCalendar = (Calendar) Calendar.getInstance();
//        myCalendar.set(year, month, 1);
//        int nNepolWeek;
//        int monday = (1 - myCalendar.get(Calendar.DAY_OF_WEEK) + 7 + 2) % 7;
//
//
//        for (int i = 0; i < arrayListMetodDrawGraph1.size(); i++) {
//            if ((i + 1) % 7 == monday) arrayListName.add(i + 1 + "");
//            else arrayListName.add("");
//        }
//
//        nNepolWeek = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) / 7;
//        if (monday != 1) nNepolWeek++;
//        if (myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) % 7 + 1 - monday > 0)
//            nNepolWeek++;
//        if (monday == 0) monday = 7;
//        if (monday == 1) shiftPuctInValueDay = 0;
//        else shiftPuctInValueDay = (8f - monday) / 7f;
//        nBlock = nNepolWeek;
//    }

    private static ModelDataGraph calculateMeshWeekItemWeek(ModelDataGraph modelDataGraph) {
        Calendar myCalendar =   Calendar.getInstance();
        int day =modelDataGraph.getDay();
        int month=modelDataGraph.getMonth();
        int year=modelDataGraph.getYear();
        ArrayList<String> arrayListLabel1=new ArrayList<>();
        ArrayList<String> arrayListLabel2=new ArrayList<>();
        int DAY_OF_WEEK;
        DAY_OF_WEEK = myCalendar.get(Calendar.DAY_OF_WEEK);
        if (DAY_OF_WEEK != 1) day = day + 2 - DAY_OF_WEEK;
        else {
            day = day - 6;
        }
        if (day <= 0) {
            month--;
            myCalendar.set(year, month, 1);
            day = myCalendar.getActualMaximum(Calendar.DATE) + day;
        }
        //day понедельник
        int daySunday;
        int monthSunday;

        for (int i = 0; i < modelDataGraph.getArrayListGraph1().size(); i++) {
            myCalendar.set(year, month, day);
            if (day + 6 > myCalendar.getActualMaximum(Calendar.DATE)) {
                daySunday = day + 6 - myCalendar.getActualMaximum(Calendar.DATE);
                if (month == 11) monthSunday = 0;
                else
                    monthSunday = month + 1;
            } else {
                monthSunday = month;
                daySunday = day + 6;
            }
            arrayListLabel1.add(day + shortMonthName[month] + "");
            arrayListLabel2.add(daySunday + shortMonthName[monthSunday] + "");
            if (day + 7 > myCalendar.getActualMaximum(Calendar.DATE)) {
                day = day + 7 - myCalendar.getActualMaximum(Calendar.DATE);

                if (month == 11) {
                    month = 0;
                    year++;
                } else
                    month++;
            } else {
                day = day + 7;
            }
        }
   return modelDataGraph;
    }


}
