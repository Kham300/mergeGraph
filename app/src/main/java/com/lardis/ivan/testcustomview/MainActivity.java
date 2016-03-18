package com.lardis.ivan.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button,button1,button2,button4,button5;
    MyView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);




        view = (MyView) findViewById(R.id.myview);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.setStartMonthArrayMonth(10, testdanStolbValue1());
                view.invalidate();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setStartDayArrayWeekInOneMonth(28, 1, 2016, testdanStolbValue1());
                view.invalidate();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setStarMonthArrayDay(  2, 2016, testdanStolbValueDataInMonth());
                view.invalidate();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setStartDayArrayWeekInPeriod(28, 1, 2016, testdanStolbValue5());
                view.invalidate();
            }
        });

    }

    private ArrayList<String> testdanStolbName() {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Января");
        arrayList.add("Февраль");
        arrayList.add("Март");
        arrayList.add("Апрель");
        arrayList.add("Май");
        arrayList.add("Июнь");
        arrayList.add("Июль");
        arrayList.add("Август");
        arrayList.add("Сентябрь");
        arrayList.add("Октябрь");
        arrayList.add("Ноябрь");
        arrayList.add("Декабрь");

        return arrayList;
    }
    private ArrayList<String> testdanStolbNameyear() {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("2000");
        arrayList.add("2001");
        arrayList.add("2002");
        arrayList.add("2003");
        arrayList.add("2004");
        arrayList.add("2005");
        arrayList.add("2006");
        arrayList.add("2007");
        arrayList.add("2008");


        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValue() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1410);
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
        arrayList.add(777);

        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValueyear() {
        ArrayList<Integer> arrayList=new ArrayList<>();

        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(157);
        arrayList.add(1946);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(557);

        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValue1() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);
        arrayList.add(557);

        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);
        arrayList.add(683);
        arrayList.add(13);
        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValue5() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);
        arrayList.add(557);

        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);
        arrayList.add(683);
        arrayList.add(13);

        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);
        arrayList.add(557);
//
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);
        arrayList.add(683);
        arrayList.add(13);


        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);
        arrayList.add(557);

        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);
        arrayList.add(683);
        arrayList.add(13);
//
//
//        arrayList.add(1224);
//        arrayList.add( 514);
//        arrayList.add(364);
//        arrayList.add(583);
//        arrayList.add(183);
//        arrayList.add(557);
//
//        arrayList.add(888);
//        arrayList.add(444);
//        arrayList.add(777);
//        arrayList.add(946);
//        arrayList.add(683);
//        arrayList.add(13);
//
//        arrayList.add(1224);
//        arrayList.add( 514);
//        arrayList.add(364);
//        arrayList.add(583);
//        arrayList.add(183);
//        arrayList.add(557);
//
//        arrayList.add(888);
//        arrayList.add(444);
//        arrayList.add(777);
//        arrayList.add(946);
//        arrayList.add(683);
//        arrayList.add(13);
        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValueDataInMonth() {
        ArrayList<Integer> arrayList=new ArrayList<>();

        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);

        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);

        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);

        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);

        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(583);
        arrayList.add(183);

        arrayList.add(557);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(946);

        arrayList.add(946);


        return arrayList;
    }

}
