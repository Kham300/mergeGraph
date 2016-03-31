package com.lardis.ivan.testcustomview.myGroopView.ViewInfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lardis.ivan.testcustomview.R;

/**
 * Created by i.larin on 30.03.2016.
 */
public class MyViewGroopInfo extends RelativeLayout {
    public MyViewGroopInfo(Context context) {
        super(context); init();
    }

    public MyViewBackgroundInfo getMyViewBackgroundInfo() {
        return myViewBackgroundInfo;
    }

    MyViewBackgroundInfo myViewBackgroundInfo=new MyViewBackgroundInfo(getContext());

    public void setInfoDlit(String  infoDlit) {
        if( this.infoDlit!=null) this.infoDlit.setText(infoDlit);
    }

    public void setInfoPodxod(String infoPodxod) {
        if( this.infoPodxod!=null) this.infoPodxod.setText(infoPodxod);
    }

    public void setInfoRaz(String infoRaz) {
        if( this.infoRaz!=null) this.infoRaz.setText(infoRaz);
    }

    public void setInfoSozheno(String infoSozheno) {
        if( this.infoSozheno!=null) this.infoSozheno.setText(infoSozheno+"кал");
    }

    public void setInfoPuls(String infoPuls) {
        if( this.infoPuls!=null)  this.infoPuls.setText(infoPuls+"уд/мин");

    }

    public void setInfomyViewGroopInfo(String[] strings)
    {

         setInfoDlit(strings[0]);
        setInfoPodxod(strings[1]);
        setInfoRaz(strings[2]);
        setInfoSozheno(strings[3]);
        setInfoPuls(strings[4]);
    }

    TextView infoDlit;
    TextView infoPodxod;
    TextView infoRaz;
    TextView infoSozheno;
    TextView infoPuls;
    View infoXmlLayout;
    public MyViewGroopInfo(Context context, AttributeSet attrs) {
        super(context, attrs); init();
    }

    public MyViewGroopInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    void init()
    {
        LayoutInflater layoutInflater= LayoutInflater.from(getContext());

          infoXmlLayout =layoutInflater.inflate(R.layout.layout_inf, null);

        this.addView(myViewBackgroundInfo);
        this.addView(infoXmlLayout);
        infoDlit=(TextView)findViewById(R.id.infoDlit);
        infoPodxod=(TextView)findViewById(R.id.infoPodxod);
        infoRaz=(TextView)findViewById(R.id.infoRaz);
        infoSozheno=(TextView)findViewById(R.id.infoSozheno);
        infoPuls=(TextView)findViewById(R.id.infoPuls);

    }






}
