package com.lardis.ivan.testcustomview;

import android.graphics.Color;
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
                view.setDrawGraph(28, 1, 2016, testdanStolbValue(), TypeViewGraph.MESH_DAY_ITEM_DAY);
//                view.setColorBorder(Color.GREEN);
//                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), TypeViewGraph.MESH_MONTH_ITEM_MONTH);
//view.setColorBorder(Color.GRAY);
//                view.setStartMonthArrayMonth(10, testdanStolbValue1());
                view.invalidate();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStartDayArrayWeekInOneMonth(28, 1, 2016, testdanStolbValue1());
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), TypeViewGraph.MESH_WEEK_ITEM_WEEK);
//                view.setColorBorder(Color.RED);
                view.invalidate();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStarMonthArrayDay(  2, 2016, testdanStolbValueDataInMonth());
                view.setDrawGraph(1,1,2016,testdanStolbValueDataInMonth(),TypeViewGraph.MESH_WEEK_ITEM_DAY);
                view.invalidate();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(27, 1, 2016, testdanStolbValue5(), TypeViewGraph.MESH_MONTH_ITEM_WEEK);
                view.invalidate();
            }
        });

    }
    private ArrayList<Integer> testdanStolbValue() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(2410);
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

    private ArrayList<Integer> testdanStolbValue1() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1224);
        arrayList.add( 5514);
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
        arrayList.add(13); arrayList.add(1224);
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
        arrayList.add(13); arrayList.add(1224);
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
        arrayList.add( 7514);
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
        arrayList.add(1300);


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

        arrayList.add(10224);
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





        return arrayList;
    }

}
