package com.lardis.ivan.testcustomview.Presenter;

import com.lardis.ivan.testcustomview.View.ModelActivity;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.EnumTypeViewGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by i.larin on 05.04.2016.
 */
public class ModelPresenter {


    Map<EnumTypeViewGraph,ModelActivity> modelActivityMap;

    public ModelPresenter(  ModelActivity MESH_WEEK_ITEM_DAY,ModelActivity MESH_WEEK_ITEM_WEEK,ModelActivity MESH_DAY_ITEM_DAY,ModelActivity MESH_MONTH_ITEM_MONTH,ModelActivity MESH_MONTH_ITEM_WEEK) {
       modelActivityMap=new HashMap<>();
        modelActivityMap.put(EnumTypeViewGraph.MESH_DAY_ITEM_DAY,MESH_WEEK_ITEM_DAY);
        modelActivityMap.put(EnumTypeViewGraph.MESH_WEEK_ITEM_WEEK,MESH_WEEK_ITEM_WEEK);
        modelActivityMap.put(EnumTypeViewGraph.MESH_DAY_ITEM_DAY,MESH_DAY_ITEM_DAY);
        modelActivityMap.put(EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH,MESH_MONTH_ITEM_MONTH);
        modelActivityMap.put(EnumTypeViewGraph.MESH_MONTH_ITEM_WEEK,MESH_MONTH_ITEM_WEEK);
            }
    public ModelActivity getModelActivity(EnumTypeViewGraph enumTypeViewGraph)
    {


        return modelActivityMap.get(enumTypeViewGraph);


    }




}
