package com.lardis.ivan.testcustomview.Presenter;

import android.util.Log;

import com.lardis.ivan.testcustomview.Storage.DB;
import com.lardis.ivan.testcustomview.View.ModelObjectActivity;
import com.lardis.ivan.testcustomview.View.InterfaceMainActivity;
import com.lardis.ivan.testcustomview.View.ModelObjectActivityWithoutSpiners;
import com.lardis.ivan.testcustomview.View.ModelObjectSpinners;

import java.lang.ref.WeakReference;

/**
 * Created by i.larin on 05.04.2016.
 */
public class PresenterActivity {
    DB DB;
    ModelObjectActivity modelActivity;
    WeakReference<InterfaceMainActivity> interfaceActivity;

    public PresenterActivity(InterfaceMainActivity interfaceData1) {
        this.interfaceActivity = new WeakReference<InterfaceMainActivity>(interfaceData1);
        modelActivity = new ModelObjectActivity();
        DB = new DB();
        DB.setOtvet(new DB.Otvet() {
            @Override
            public void setDataModelActivityWithoutSpiners(ModelObjectActivityWithoutSpiners modelActivityWithoutSpiners) {
                modelActivity.setModelActivityWithoutSpiners(modelActivityWithoutSpiners);
                interfaceActivity.get().updateData(modelActivityWithoutSpiners);
            }

            @Override
            public void setTitleSpinner(ModelObjectSpinners modelSpinners) {
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
