package com.lardis.ivan.testcustomview.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lardis.ivan.testcustomview.Model.ModelDataGraph;
import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.Graph.EnumTypeViewGraph;
import com.lardis.ivan.testcustomview.View.Graph.Helper.HelperGraphInfo;
import com.lardis.ivan.testcustomview.View.Graph.NewBackground;
import com.lardis.ivan.testcustomview.View.Graph.TypeGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by i.larin on 11.04.2016.
 */
public class MainActivityMerge extends AppCompatActivity {


    NewBackground backround;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main_activity);

        backround = (NewBackground) findViewById(R.id.mygroop);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        final ModelDataGraph modelDataGraph = new ModelDataGraph(1, 1, 1, new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), new ArrayList<>(Arrays.asList(
                95, 86, 70, 65, 59, 49, 45, 65, 59, 49, 65, 59)), EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backround.setDataGraph(modelDataGraph, TypeGraph.GraphLine);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelDataGraph modelDataGraph = new ModelDataGraph(1, 3, 2016, new ArrayList<>(Arrays.asList(
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49,
                        95, 86, 70, 65, 59, 49, 45, 65, 59, 49,
                        95, 86, 70, 65, 59, 49, 45, 65, 59,
                        65)),
                        null, EnumTypeViewGraph.MESH_WEEK_ITEM_DAY_PERIOD_MONTH);

                ModelDataGraph modelDataGraph1 = HelperGraphInfo.getLabel(getApplicationContext(),modelDataGraph);
               ArrayList<Float> arrayList= HelperGraphInfo.getArrayWidthCoefficient(modelDataGraph1);
                for (int i = 0; i <arrayList.size(); i++) {
                    Log.d("Mylog", "arrayList=" + arrayList.get(i) + " "  );

                }
                for (int i = 0; i < modelDataGraph1.getLabels1().size(); i++) {
                    Log.d("Mylog", "date=" + modelDataGraph1.getLabels1().get(i) + " "  );

                }
                backround.setDataGraph(modelDataGraph, TypeGraph.GraphPunct);
            }


        });






    }
}
