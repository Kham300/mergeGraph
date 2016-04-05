package com.lardis.ivan.testcustomview.Presenter;

import com.lardis.ivan.testcustomview.Model.DB;
import com.lardis.ivan.testcustomview.View.InterfaceData;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.EnumTypeViewGraph;

import java.lang.ref.WeakReference;

/**
 * Created by i.larin on 05.04.2016.
 */
public class PresenterActivity {
    DB modelActivity;
    WeakReference<InterfaceData> interfaceActivity;
    public PresenterActivity( InterfaceData interfaceData1) {
        this.interfaceActivity = new  WeakReference<InterfaceData> (interfaceData1);

          modelActivity=new DB();
modelActivity.setOtvet(new DB.Otvet() {
    @Override
    public void work(ModelPresenter modelPresenter) {
        interfaceActivity.get().updateData(modelPresenter.getModelActivity(EnumTypeViewGraph.MESH_MONTH_ITEM_MONTH));
    }


});




    }



public void getData()
{
    modelActivity.getData();
}



}
