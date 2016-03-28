package com.lardis.ivan.testcustomview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3,button4,button5;
    Button button6,button7,button8,button9,button10;
    MyView view;
LinearLayout linearLayout;
MyZoomView myZoomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
linearLayout=(LinearLayout)findViewById(R.id.lineaLayout);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (MyView) findViewById(R.id.myview);
        myZoomView=(MyZoomView)findViewById(R.id.myzoomview);


        view.setSelectedZoom(new MyView.SelectedZoom() {
            @Override
            public void doWork(float x, float y, boolean work) {
                myZoomView.setData(x,y,work,getBitmapFromView(view));


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
                view.setDrawGraph(28, 1, 2016, testdanStolbValue(), null, TypeViewGraph.MESH_DAY_ITEM_DAY);
//                view.setColorBorder(Color.GREEN);
//                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();
                LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                linearLayout.addView(button1,linLayoutParam);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, TypeViewGraph.MESH_MONTH_ITEM_MONTH);
//view.setColorBorder(Color.GRAY);
//                view.setStartMonthArrayMonth(10, testdanStolbValue1());
                view.invalidate();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStartDayArrayWeekInOneMonth(28, 1, 2016, testdanStolbValue1());
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), null, TypeViewGraph.MESH_WEEK_ITEM_WEEK);
//                view.setColorBorder(Color.RED);
                view.invalidate();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


//                view.setStarMonthArrayDay(  2, 2016, testdanStolbValueDataInMonth());
                view.setDrawGraph(1,1,2016,testdanStolbValueDataInMonth(),null,TypeViewGraph.MESH_WEEK_ITEM_DAY);
                view.invalidate();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(27, 1, 2016, testdanStolbValue5(),null, TypeViewGraph.MESH_MONTH_ITEM_WEEK);
                view.invalidate();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                view.setDrawGraph(28, 1, 2016, testdanStolbValue(), testdanStolbValue6(), TypeViewGraph.MESH_DAY_ITEM_DAY);
//                view.setColorBorder(Color.GREEN);
//                        view.setStartDayArayDay(28, 1, 2016, testdanStolbValue());
                view.invalidate();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(),testdanStolbValue1(), TypeViewGraph.MESH_MONTH_ITEM_MONTH);
//view.setColorBorder(Color.GRAY);
//                view.setStartMonthArrayMonth(10, testdanStolbValue1());
           view.invalidate();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                view.setDrawGraph(28, 1, 2016, testdanStolbValue1(), testdanStolbValue1(), TypeViewGraph.MESH_WEEK_ITEM_WEEK);
                view.invalidate();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(1,1,2016,testdanStolbValueDataInMonth(),testdanStolbValueDataInMonth(),TypeViewGraph.MESH_WEEK_ITEM_DAY);
                view.invalidate();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                view.setDrawGraph(1, 1, 2016, testdanStolbValue5(),testdanStolbValue5(), TypeViewGraph.MESH_MONTH_ITEM_WEEK);
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
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
}
