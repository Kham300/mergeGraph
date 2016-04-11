package com.lardis.ivan.testcustomview.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.Graph.EnumTypeViewGraph;
import com.lardis.ivan.testcustomview.View.Graph.NewBackround;
import com.lardis.ivan.testcustomview.View.Graph.TypeGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by i.larin on 11.04.2016.
 */
public class MainActivityMerge extends AppCompatActivity {


    NewBackround backround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main_activity);

        backround = (NewBackround) findViewById(R.id.mygroop);

        ModelDataGraph modelDataGraph = new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);

        backround.setDataGraph(modelDataGraph, TypeGraph.GraphLine);
    }
}
