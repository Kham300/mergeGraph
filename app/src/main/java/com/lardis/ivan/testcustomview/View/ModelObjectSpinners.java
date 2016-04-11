package com.lardis.ivan.testcustomview.View;

import com.lardis.ivan.testcustomview.EnumActivitySpinner1;
import com.lardis.ivan.testcustomview.EnumActivitySpinner2;
import com.lardis.ivan.testcustomview.EnumActivitySpinner3;

import java.util.ArrayList;

/**
 * Created by i.larin on 06.04.2016.
 */
public class ModelObjectSpinners {
    public ModelObjectSpinners(ArrayList<EnumActivitySpinner1> spinner1ArrayList, ArrayList<EnumActivitySpinner2> spinner2ArrayList, ArrayList<EnumActivitySpinner3> spinner3ArrayList) {
        this.spinner1ArrayList = spinner1ArrayList;
        this.spinner2ArrayList = spinner2ArrayList;
        this.spinner3ArrayList = spinner3ArrayList;
    }

    ArrayList<EnumActivitySpinner1> spinner1ArrayList;
    ArrayList<EnumActivitySpinner2> spinner2ArrayList;
    ArrayList<EnumActivitySpinner3> spinner3ArrayList;
    public ArrayList<EnumActivitySpinner3> getSpinner3ArrayList() {
        return spinner3ArrayList;
    }

    public void setSpinner3ArrayList(ArrayList<EnumActivitySpinner3> spinner3ArrayList) {
        this.spinner3ArrayList = spinner3ArrayList;
    }

    public ArrayList<EnumActivitySpinner2> getSpinner2ArrayList() {
        return spinner2ArrayList;
    }

    public void setSpinner2ArrayList(ArrayList<EnumActivitySpinner2> spinner2ArrayList) {
        this.spinner2ArrayList = spinner2ArrayList;
    }

    public ArrayList<EnumActivitySpinner1> getSpinner1ArrayList() {
        return spinner1ArrayList;
    }

    public void setSpinner1ArrayList(ArrayList<EnumActivitySpinner1> spinner1ArrayList) {
        this.spinner1ArrayList = spinner1ArrayList;
    }




}
