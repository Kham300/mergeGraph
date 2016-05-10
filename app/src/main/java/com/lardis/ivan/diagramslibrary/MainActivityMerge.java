package com.lardis.ivan.diagramslibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lardis.ivan.diagramslibrary.graphview.base.TypeGraph;
import com.lardis.ivan.diagramslibrary.graphview.base.ViewType;
import com.lardis.ivan.diagramslibrary.graphview.base.views.GraphViewGroup;
import com.lardis.ivan.diagramslibrary.model.ModelBlockInfo;
import com.lardis.ivan.diagramslibrary.model.ModelDataGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by i.larin on 11.04.2016.
 */
public class MainActivityMerge extends AppCompatActivity {


    GraphViewGroup graphViewGroup;
    Button button1;
    Button button2;
    Button button3;

    int idx1;
    int idx2;
    int idx3;
    ModelDataGraph[] data1;
    ModelDataGraph[] data2;
    ModelDataGraph[] data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main_activity);

        graphViewGroup = (GraphViewGroup) findViewById(R.id.mygroop);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        setData();

        for (ModelDataGraph modelDataGraph : data1) {
            modelDataGraph.setmGoal(50);
            modelDataGraph.setArrayListForInfo(new ArrayList<ModelBlockInfo>() {{
                for (int i = 0; i < 12; i++) {
                    add(new ModelBlockInfo("uno" + i, "due" + i,
                            "tre" + i, "quatro" + i, "cinque" + i));
                }
            }});

        }

        for (ModelDataGraph modelDataGraph : data2) {
            modelDataGraph.setArrayListForInfo(new ArrayList<ModelBlockInfo>() {{
                for (int i = 0; i < 12; i++) {
                    add(new ModelBlockInfo("uno" + i, "due" + i,
                            "tre" + i, "quatro" + i, "cinque" + i));
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
                if (++idx2 >= data2.length)
                    idx2 = 0;
                graphViewGroup.setDataGraphAndInfo(data2[idx2], TypeGraph.COLUMN_VANYA);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++idx3 >= data3.length)
                    idx3 = 0;
                graphViewGroup.setDataGraphAndInfo(data3[idx3], TypeGraph.MULTI);
            }
        });


    }

    private void setData() {
        data1 = new ModelDataGraph[]{new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null,
                ViewType.MESH_DAY_ITEM_DAY),
                new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null,
                        ViewType.MESH_MONTH_ITEM_MONTH), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null,
                ViewType.MESH_WEEK_ITEM_WEEK)};


        data2 = new ModelDataGraph[]{new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), null,
                ViewType.MESH_WEEK_ITEM_DAY_PERIOD_MONTH),
                new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null,
                        ViewType.MESH_MONTH_ITEM_WEEK), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null, null,
                ViewType.MESH_WEEK_ITEM_WEEK), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null, null,
                ViewType.MESH_DAY_ITEM_DAY), new ModelDataGraph(2, 3, 10, new ArrayList<>(Arrays.asList(
                150, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 23, 59, 49, 45, 65, 59, 49, 65, 59,
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), null,
                ViewType.MESH_MONTH_ITEM_MONTH)};


        ArrayList<ArrayList<Integer>> rndVals = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 3; ++i) {
            rndVals.add(new ArrayList<Integer>());
            for (int k = 0; k < 16 * 10; ++k)
                rndVals.get(i).add(rnd.nextBoolean() ? (int) (20 * i + k + 10 * rnd.nextDouble()) : 0);
        }


        data3 = new ModelDataGraph[]{new ModelDataGraph(1, 1, 1, null, null, rndVals,
                ViewType.MESH_MONTH_ITEM_WEEK)};

        data3[0].setColors(new ArrayList<Integer>() {{
            add(Color.parseColor("#009BA1"));
            add(Color.parseColor("#8AD9DB"));
            add(Color.parseColor("#006569"));
        }});
    }
}
