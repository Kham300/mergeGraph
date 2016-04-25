package com.lardis.ivan.testcustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lardis.ivan.testcustomview.graphview.base.TypeGraph;
import com.lardis.ivan.testcustomview.graphview.base.ViewType;
import com.lardis.ivan.testcustomview.graphview.base.views.GraphViewGroup;
import com.lardis.ivan.testcustomview.model.ModelBlockInfo;
import com.lardis.ivan.testcustomview.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by i.larin on 11.04.2016.
 */
public class MainActivityMerge extends AppCompatActivity {


    GraphViewGroup graphViewGroup;
    Button button1;
    Button button2;

    int idx1;
    int idx2;
    ModelDataGraph[] data1;
    ModelDataGraph[] data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main_activity);

        graphViewGroup = (GraphViewGroup) findViewById(R.id.mygroop);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        data1 = new ModelDataGraph[]{new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_DAY_ITEM_DAY),
                new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                        ViewType.MESH_MONTH_ITEM_MONTH), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_WEEK_ITEM_WEEK)};

        data2 = new ModelDataGraph[]{new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH),
                new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                        ViewType.MESH_MONTH_ITEM_WEEK), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)),
                ViewType.MESH_WEEK_ITEM_WEEK)};

        for (ModelDataGraph modelDataGraph : data1) {
            modelDataGraph.setArrayListForInfo(new ArrayList<ModelBlockInfo>() {{
                for (int i = 0; i < 12; i++) {
                    add(new ModelBlockInfo("uno" + i, "due" + i,
                            "tre" + i, "quantro" + i, "cinque" + i));
                }
            }});

        }

        for (ModelDataGraph modelDataGraph : data2) {
            modelDataGraph.setArrayListForInfo(new ArrayList<ModelBlockInfo>() {{
                for (int i = 0; i < 12; i++) {
                    add(new ModelBlockInfo("uno" + i, "due" + i,
                            "tre" + i, "quantro" + i, "cinque" + i));
                }
            }});

        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++idx1 >= data1.length)
                    idx1 = 0;
                graphViewGroup.setDataGraphAndInfo(data1[idx1], TypeGraph.CIRCLED_UNO);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++idx2 >= data1.length)
                    idx2 = 0;
                graphViewGroup.setDataGraphAndInfo(data2[idx2], TypeGraph.GraphPunct);
            }
        });


    }
}
