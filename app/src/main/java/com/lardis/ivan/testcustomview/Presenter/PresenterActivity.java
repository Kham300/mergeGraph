package com.lardis.ivan.testcustomview.Presenter;

import android.util.Log;

import com.lardis.ivan.testcustomview.EnumActivitySpinner1;
import com.lardis.ivan.testcustomview.EnumActivitySpinner2;
import com.lardis.ivan.testcustomview.EnumActivitySpinner3;
import com.lardis.ivan.testcustomview.Model.DB;
import com.lardis.ivan.testcustomview.View.ModelActivity;
import com.lardis.ivan.testcustomview.View.InterfaceMainActivity;
import com.lardis.ivan.testcustomview.View.ModelActivityWithoutSpiners;
import com.lardis.ivan.testcustomview.View.ModelSpinners;
import com.lardis.ivan.testcustomview.View.ViewGraph.myEnum.EnumTypeViewGraph;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by i.larin on 05.04.2016.
 */
public class PresenterActivity {
    DB DB;
    ModelActivity modelActivity;
    WeakReference<InterfaceMainActivity> interfaceActivity;

    public PresenterActivity(InterfaceMainActivity interfaceData1) {
        this.interfaceActivity = new WeakReference<InterfaceMainActivity>(interfaceData1);
        modelActivity = new ModelActivity();
        DB = new DB();
        DB.setOtvet(new DB.Otvet() {
            @Override
            public void setDataModelActivityWithoutSpiners(ModelActivityWithoutSpiners modelActivityWithoutSpiners) {
                modelActivity.setModelActivityWithoutSpiners(modelActivityWithoutSpiners);
                interfaceActivity.get().updateData(modelActivityWithoutSpiners);
            }

            @Override
            public void setTitleSpinner(ModelSpinners modelSpinners) {
                modelActivity.setModelSpinners(modelSpinners);
                interfaceActivity.get().updateAdapterSpinner(modelSpinners);
            }


        });


    }


    public void getData(int idvaluespinner1, int idvaluespinner2, int idvaluespinner3) {
        Log.d("Mylog1", "idvaluespinner1=" + idvaluespinner1 + "idvaluespinner2=" + idvaluespinner2 + "idvaluespinner3=" + idvaluespinner3);


    DB.getData(modelActivity.getModelSpinners().getSpinner1ArrayList().get(idvaluespinner1), modelActivity.getModelSpinners().getSpinner2ArrayList().get(idvaluespinner2),modelActivity.getModelSpinners().getSpinner3ArrayList().get(idvaluespinner3));
    }

    public void getTitleSpinner() {
        DB.getTitleSpinner();


    }


}
