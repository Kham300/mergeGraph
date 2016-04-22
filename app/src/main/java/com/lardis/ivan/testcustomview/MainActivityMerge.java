package com.lardis.ivan.testcustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.lardis.ivan.testcustomview.graphview.base.NewBackground;
import com.lardis.ivan.testcustomview.graphview.base.TypeGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by i.larin on 11.04.2016.
 */
public class MainActivityMerge extends AppCompatActivity {


    NewBackground background;
    Button button1;
    Button button2;

    int idx;
    ModelDataGraph[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main_activity);

        background = (NewBackground) findViewById(R.id.mygroop);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        data = new ModelDataGraph[] { new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_DAY_ITEM_DAY), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_MONTH_ITEM_MONTH),  new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_WEEK_ITEM_WEEK)};

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++idx >= data.length)
                    idx = 0;
                background.setDataGraph(data[idx], TypeGraph.CIRCLED_UNO);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++idx >= data.length)
                    idx = 0;
                background.setDataGraph(data[idx], TypeGraph.GraphPunct);
            }
        });


    }
}
