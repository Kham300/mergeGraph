package com.lardis.ivan.testcustomview.Presenter;

import com.lardis.ivan.testcustomview.Model.ModelActivity;
import com.lardis.ivan.testcustomview.View.DataActivity;
import com.lardis.ivan.testcustomview.View.DataBlockInfo;
import com.lardis.ivan.testcustomview.View.DataGraph;
import com.lardis.ivan.testcustomview.View.InterfaceData;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.enumTypeViewGraph;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class PresenterActivity {
    ModelActivity modelActivity;
    WeakReference<InterfaceData> interfaceData;
    public PresenterActivity(final InterfaceData interfaceData) {
        this.interfaceData = new  WeakReference<InterfaceData> (interfaceData);

          modelActivity=new ModelActivity();
modelActivity.setOtvet(new ModelActivity.Otvet() {
    @Override
    public void work(String detStatPeriod, String detStatSrednee, String detStatItogo, String detStatProideno, String detStatInfoMonth, String detStatSredneeZnazhenie, int day, int month, int year, ArrayList<Integer> arrayListGraph1, ArrayList<Integer> arrayListGraph2, enumTypeViewGraph typeViewGraph,ArrayList<DataBlockInfo> blockInfoArrayList) {
        DataGraph dataGraph=new DataGraph(  day,   month,   year,   arrayListGraph1,  arrayListGraph2,   typeViewGraph);
        DataActivity dataActivity=new DataActivity(  detStatPeriod,   detStatSrednee,   detStatItogo,   detStatProideno,   detStatInfoMonth,   detStatSredneeZnazhenie,dataGraph,blockInfoArrayList);
        interfaceData.updateData(dataActivity);
    }
});




    }



public void getData()
{
    modelActivity.getData();
}



}
