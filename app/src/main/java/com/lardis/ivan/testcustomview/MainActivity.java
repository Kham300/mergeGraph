package com.lardis.ivan.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3,button4,button5;
    Button button6,button7,button8,button9,button10;
    MyGraphView view;
AbsoluteLayout linearLayout;
AbsoluteLayout linearLayout1;
MyZoomView myZoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myZoomView=new MyZoomView(this);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=(AbsoluteLayout)findViewById(R.id.mygroop);
        linearLayout1=(AbsoluteLayout)findViewById(R.id.mygroop1);
        view = (MyGraphView) findViewById(R.id.myview);

        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(  ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
LayoutInflater layoutInflater= LayoutInflater.from(this);
View view1=layoutInflater.inflate(R.layout.layout_inf,null);
        linearLayout.addView(myZoomView,lpView);
//        linearLayout1.addView(view1);
        linearLayout1.addView(new MyViewGroop(this),lpView);
//linearLayout.addView(view,lpView);
        view.setSelectedZoom(new MyGraphView.SelectedZoom() {


            @Override
            public void doShow(boolean work) {
                if(work)myZoomView.show();
                else myZoomView.hide();

            }

            @Override
            public void setCoordinate(float x, float y) {
                myZoomView.setData(x,y,ViewHelper.getBitmapFromView(view));
            }


        });


        button1 = (Button) findViewById(R.id.button);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);








        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue(), null, enumTypeViewGraph.MESH_DAY_ITEM_DAY);
//                view.setColorBorder(Color.GREEN);
//                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, enumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
//view.setColorBorder(Color.GRAY);
//                view.setStartMonthArrayMonth(10, testdanStolbValue1());
                view.invalidate();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStartDayArrayWeekInOneMonth(28, 1, 2016, testdanStolbValue1());
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, enumTypeViewGraph.MESH_WEEK_ITEM_WEEK);
//                view.setColorBorder(Color.RED);
                view.invalidate();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStarMonthArrayDay(  2, 2016, testdanStolbValueDataInMonth());
                view.setDrawGraph(1,1,2016,testdanStolbValueDataInMonth(),null, enumTypeViewGraph.MESH_WEEK_ITEM_DAY);
                view.invalidate();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(27, 1, 2016, testdanStolbValue5(),null, enumTypeViewGraph.MESH_MONTH_ITEM_WEEK);
                view.invalidate();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                view.setDrawGraph(28, 1, 2016, testdanStolbValue(), testdanStolbValue6(), enumTypeViewGraph.MESH_DAY_ITEM_DAY);
//                view.setColorBorder(Color.GREEN);
//                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(),testdanStolbValue1(), enumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
//view.setColorBorder(Color.GRAY);
//                view.setStartMonthArrayMonth(10, testdanStolbValue1());
           view.invalidate();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), testdanStolbValue1(), enumTypeViewGraph.MESH_WEEK_ITEM_WEEK);
                view.invalidate();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(1,1,2016,testdanStolbValueDataInMonth(),testdanStolbValueDataInMonth(), enumTypeViewGraph.MESH_WEEK_ITEM_DAY);
                view.invalidate();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(1, 1, 2016, testdanStolbValue5(),testdanStolbValue5(), enumTypeViewGraph.MESH_MONTH_ITEM_WEEK);
                view.invalidate();
            }
        });

    }
    private ArrayList<Integer> testdanStolbValue6() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(246);
        arrayList.add(683);
        arrayList.add(13);
        arrayList.add(888);
        arrayList.add(444);
        arrayList.add(777);
        arrayList.add(854);
        arrayList.add(864);
        arrayList.add(383);
        arrayList.add(783);
        arrayList.add(157);
        arrayList.add(54);


        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValue() {
        ArrayList<Integer> arrayList=new ArrayList<>();
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
        arrayList.add(777);

        return arrayList;
    }
    private ArrayList<Integer> testdanStolbValue1() {
        ArrayList<Integer> arrayList=new ArrayList<>();

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
        ArrayList<Integer> arrayList=new ArrayList<>();



        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);


        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);
        arrayList.add(1224);
        arrayList.add( 514);
        arrayList.add(364);

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





        return arrayList;
    }

}
