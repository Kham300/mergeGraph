package com.lardis.ivan.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button,button1,button2;
    MyView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);




        view = (MyView) findViewById(R.id.myview);
        view.setArrayList( "1",1,testdan());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setArrayList("1", 1, testdan());
                view.invalidate();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    view.setArrayList("1",1,testdan1());
                    view.invalidate();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    view.setArrayList("2",2,testdan2());
                    view.invalidate();

            }
        });
    }

    private ArrayList<ItemArrayListData> testdan2() {
        ArrayList<ItemArrayListData> arrayList=new ArrayList<>();
        arrayList.add(new ItemArrayListData("Января", 200));
        arrayList.add(new ItemArrayListData("Февраль ", 565));
        arrayList.add(new ItemArrayListData(" Март", 7405));
        arrayList.add(new ItemArrayListData(" Апрель", 24));
        arrayList.add(new ItemArrayListData(" Май", 74));
        arrayList.add(new ItemArrayListData("Июнь", 374));
        arrayList.add(new ItemArrayListData("Июль ", 625));
        arrayList.add(new ItemArrayListData("Август", 365));
        arrayList.add(new ItemArrayListData("Сентябрь", 447));
        arrayList.add(new ItemArrayListData("Октябрь", 345));
        arrayList.add(new ItemArrayListData("Ноябрь", 153));
        arrayList.add(new ItemArrayListData("Декабрь", 746));
        return arrayList;
    }
  private ArrayList<ItemArrayListData> testdan1() {
        ArrayList<ItemArrayListData> arrayList=new ArrayList<>();
        arrayList.add(new ItemArrayListData("Января", 200));
        arrayList.add(new ItemArrayListData("Февраль ", 565));
        arrayList.add(new ItemArrayListData(" Март", 745));
        arrayList.add(new ItemArrayListData(" Апрель", 24));
        arrayList.add(new ItemArrayListData(" Май", 74));
        arrayList.add(new ItemArrayListData("Июнь", 374));
        arrayList.add(new ItemArrayListData("Июль ", 625));
        arrayList.add(new ItemArrayListData("Август", 365));
        arrayList.add(new ItemArrayListData("Сентябрь", 447));
        arrayList.add(new ItemArrayListData("Октябрь", 345));
        arrayList.add(new ItemArrayListData("Ноябрь", 153));
        arrayList.add(new ItemArrayListData("Декабрь", 746));
        return arrayList;
    }

    private ArrayList<ItemArrayListData> testdan() {
        ArrayList<ItemArrayListData> arrayList=new ArrayList<>();
        arrayList.add(new ItemArrayListData("Января", 1));
        arrayList.add(new ItemArrayListData("Февраль ", 405));
        arrayList.add(new ItemArrayListData(" Март", 75));
        arrayList.add(new ItemArrayListData(" Апрель", 124));
        arrayList.add(new ItemArrayListData(" Май", 724));
        arrayList.add(new ItemArrayListData("Июнь", 34));
        arrayList.add(new ItemArrayListData("Июль ", 325));
        arrayList.add(new ItemArrayListData("Август", 765));
        arrayList.add(new ItemArrayListData("Сентябрь", 147));
        arrayList.add(new ItemArrayListData("Октябрь", 845));
        arrayList.add(new ItemArrayListData("Ноябрь", 453));
        arrayList.add(new ItemArrayListData("Декабрь", 146));
        return arrayList;
    }
}
