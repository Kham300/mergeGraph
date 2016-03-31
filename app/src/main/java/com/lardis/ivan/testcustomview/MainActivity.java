package com.lardis.ivan.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lardis.ivan.testcustomview.helper.ViewHelper;
import com.lardis.ivan.testcustomview.myEnum.enumTypeViewGraph;
import com.lardis.ivan.testcustomview.myGroopView.MyGraphView;
import com.lardis.ivan.testcustomview.myGroopView.MyZoomView;
import com.lardis.ivan.testcustomview.myGroopView.ViewInfo.MyViewGroopInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3,button4,button5;
    Button button6,button7,button8,button9,button10;
    MyGraphView myGraphView;
    myGroopAbsoluteLayout absoluteLayout;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        absoluteLayout=(myGroopAbsoluteLayout)findViewById(R.id.mygroop);
        myGraphView=absoluteLayout.getMyGraphView();
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
                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue(), null, enumTypeViewGraph.MESH_DAY_ITEM_DAY);
                 myGraphView.invalidate();


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, enumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
//myGraphView.setColorBorder(Color.GRAY);
//                myGraphView.setStartMonthArrayMonth(10, testdanStolbValue1());
                myGraphView.invalidate();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                myGraphView.setStartDayArrayWeekInOneMonth(28, 1, 2016, testdanStolbValue1());
                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, enumTypeViewGraph.MESH_WEEK_ITEM_WEEK);
//                myGraphView.setColorBorder(Color.RED);
                myGraphView.invalidate();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                myGraphView.setStarMonthArrayDay(  2, 2016, testdanStolbValueDataInMonth());
                myGraphView.setDrawGraph(1, 1, 2016, testdanStolbValueDataInMonth(), null, enumTypeViewGraph.MESH_WEEK_ITEM_DAY);
                myGraphView.invalidate();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                myGraphView.setDrawGraph(27, 1, 2016, testdanStolbValue5(), null, enumTypeViewGraph.MESH_MONTH_ITEM_WEEK);
                myGraphView.invalidate();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue(), testdanStolbValue6(), enumTypeViewGraph.MESH_DAY_ITEM_DAY);
//                myGraphView.setColorBorder(Color.GREEN);
//                        myGraphView.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                myGraphView.invalidate();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue1(), testdanStolbValue1(), enumTypeViewGraph.MESH_MONTH_ITEM_MONTH);
//myGraphView.setColorBorder(Color.GRAY);
//                myGraphView.setStartMonthArrayMonth(10, testdanStolbValue1());
           myGraphView.invalidate();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                myGraphView.setDrawGraph(28, 1, 2016, testdanStolbValue1(), testdanStolbValue1(), enumTypeViewGraph.MESH_WEEK_ITEM_WEEK);
                myGraphView.invalidate();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                myGraphView.setDrawGraph(1, 1, 2016, testdanStolbValueDataInMonth(), testdanStolbValueDataInMonth(), enumTypeViewGraph.MESH_WEEK_ITEM_DAY);
                myGraphView.invalidate();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                myGraphView.setDrawGraph(1, 1, 2016, testdanStolbValue5(), testdanStolbValue5(), enumTypeViewGraph.MESH_MONTH_ITEM_WEEK);
                myGraphView.invalidate();
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
    public static ArrayList<String[]> testdanForInfo() {
        ArrayList<String[]> arrayList=new ArrayList<>();
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});

        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});     arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});
        arrayList.add(new String[]{"12:32","12","53","23","12"});
        arrayList.add(new String[]{"11:43","43","23","23","54"});
        arrayList.add(new String[]{"6:56","45","23","98","54"});
        arrayList.add(new String[]{"65:12","43","65","12","76"});
        arrayList.add(new String[]{"1:32","12","65","12","65"});
        arrayList.add(new String[]{"65:12","34","56","54","12"});
        arrayList.add(new String[]{"56:12","76","12","54","87"});
        arrayList.add(new String[]{"87:54","23","65","23","65"});
        arrayList.add(new String[]{"54:54","23","98","54","67"});
        arrayList.add(new String[]{"45:78","23","56","12","43"});


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
