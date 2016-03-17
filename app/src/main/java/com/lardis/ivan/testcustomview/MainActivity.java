package com.lardis.ivan.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button button,button1,button2;
    MyView view;
    MyView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);




        view = (MyView) findViewById(R.id.myview);
        view1 = (MyView) findViewById(R.id.myview1);


        view.setTwoArrayListNameStringValueInt(null, testdanStolbValue());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTwoArrayListNameStringValueInt(testdanStolbName(), testdanStolbValue());
                view.invalidate();
                        view1.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view1.invalidate();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTwoArrayListNameStringValueInt(null, testdanStolbValue1());
                view.invalidate();
                view1.setStartMonthArayMonth(10, testdanStolbValue1());
                view1.invalidate();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                view.setTwoArrayListNameStringValueInt(testdanStolbNameyear(), testdanStolbValueyear());
                view.invalidate();

                view1.setStartDayArrayWeek(28, 1, 2016, testdanStolbValue1());
                view1.invalidate();
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
        arrayList.add(1000);
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

}
