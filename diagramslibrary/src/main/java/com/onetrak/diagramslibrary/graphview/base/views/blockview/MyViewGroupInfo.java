package com.onetrak.diagramslibrary.graphview.base.views.blockview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.diagramslibrary.R;
import com.onetrak.diagramslibrary.model.ModelBlockInfo;

/**
 * Задний фон блока и информация о блоке (связь 2 view)
 */
public class MyViewGroupInfo extends RelativeLayout {
    public MyViewGroupInfo(Context context) {
        super(context);
        init();
    }

    public MyViewBackgroundInfo getMyViewBackgroundInfo() {
        return myViewBackgroundInfo;
    }

    MyViewBackgroundInfo myViewBackgroundInfo;
    /**
     * view для отображения длины
     */
    TextView infoDlit;
    /**
     * view для отображения количества подходов
     */
    TextView infoPodxod;
    /**
     * view для отображения колоичества раз
     */
    TextView infoRaz;
    /**
     * view для отображения соженных калорий
     */
    TextView infoSozheno;
    /**
     * view для отображения пульса
     */
    TextView infoPuls;

    /**
     * view нарисованное в xml для блока
     */
    View infoXmlLayout;

    /**
     * вставка длины в textview
     *
     * @param infoDlit
     */
    public void setInfoDlit(String infoDlit) {
        if (this.infoDlit != null) this.infoDlit.setText(infoDlit);
    }

    /**
     * вставка количества подходов в textview
     *
     * @param infoPodxod
     */
    private void setInfoPodxod(String infoPodxod) {
        if (this.infoPodxod != null) this.infoPodxod.setText(infoPodxod);
    }

    /**
     * вставка количества раз в textview
     *
     * @param infoRaz
     */
    private void setInfoRaz(String infoRaz) {
        if (this.infoRaz != null) this.infoRaz.setText(infoRaz);
    }

    /**
     * вставка соженных калорий в textview
     *
     * @param infoSozheno
     */
    private void setInfoSozheno(String infoSozheno) {
        if (this.infoSozheno != null) this.infoSozheno.setText(infoSozheno + "кал");
    }

    /**
     * вставка пульса в textview
     *
     * @param infoPuls
     */
    private void setInfoPuls(String infoPuls) {
        if (this.infoPuls != null) this.infoPuls.setText(infoPuls + "уд/мин");

    }

    /**
     * Информация для рисования,
     *
     */
    public void setInfo(ModelBlockInfo dataBlockInfo) {

        setInfoDlit(dataBlockInfo.getInfoDlit());
        setInfoPodxod(dataBlockInfo.getInfoPodxod());
        setInfoRaz(dataBlockInfo.getInfoRaz());
        setInfoSozheno(dataBlockInfo.getInfoSozheno());
        setInfoPuls(dataBlockInfo.getInfoPuls());
    }

    /**
     * переместить треугольник слева относительно блока
     */
    public void locationTriangleLeft()

    {
        myViewBackgroundInfo.locationTriangleLeft();

    }

    /**
     * переместить треугольник справа относительно блока
     */
    public void locationTriangleRight()

    {
        myViewBackgroundInfo.locationTriangleRight();

    }

    /**
     * переместить треугольник снизу относительно блока
     */
    public void locationTriangleBottom()

    {
        myViewBackgroundInfo.locationTriangleBottom();
    }


    /**
     * переместить треугольник сверху относительно блока
     */
    public void locationTriangleTop()

    {
        myViewBackgroundInfo.locationTriangleTop();

    }



    public MyViewGroupInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroupInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        myViewBackgroundInfo.invalidate();
    }

    void init() {
        myViewBackgroundInfo = new MyViewBackgroundInfo(getContext());
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        infoXmlLayout = layoutInflater.inflate(R.layout.view_viewgraf_mygroopview_myinfoview, null);

        this.addView(myViewBackgroundInfo);
        this.addView(infoXmlLayout);
        infoDlit = (TextView) findViewById(R.id.infodlit);
        infoPodxod = (TextView) findViewById(R.id.infoPodxod);
        infoRaz = (TextView) findViewById(R.id.infoRaz);
        infoSozheno = (TextView) findViewById(R.id.infoSozheno);
        infoPuls = (TextView) findViewById(R.id.infoPuls);

    }


}
