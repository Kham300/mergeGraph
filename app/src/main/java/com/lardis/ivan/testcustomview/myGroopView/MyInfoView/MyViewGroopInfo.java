package com.lardis.ivan.testcustomview.myGroopView.MyInfoView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lardis.ivan.testcustomview.R;

/**
 * Задний фон блока и информация о блоке (связь 2 view)
 */
public class MyViewGroopInfo extends RelativeLayout {
    public MyViewGroopInfo(Context context) {
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
     * @param strings массив  <br> 0 элемент - длитетельсть <br> 1 элемент -количество подходов <br> 2 элемент- количество раз <br>3 элемент- сожжено <br> 4 элемент пульс
     */
    public void setInfo(String[] strings) {

        setInfoDlit(strings[0]);
        setInfoPodxod(strings[1]);
        setInfoRaz(strings[2]);
        setInfoSozheno(strings[3]);
        setInfoPuls(strings[4]);
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



    public MyViewGroopInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroopInfo(Context context, AttributeSet attrs, int defStyleAttr) {
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

        infoXmlLayout = layoutInflater.inflate(R.layout.layout_info, null);

        this.addView(myViewBackgroundInfo);
        this.addView(infoXmlLayout);
        infoDlit = (TextView) findViewById(R.id.infodlit);
        infoPodxod = (TextView) findViewById(R.id.infoPodxod);
        infoRaz = (TextView) findViewById(R.id.infoRaz);
        infoSozheno = (TextView) findViewById(R.id.infoSozheno);
        infoPuls = (TextView) findViewById(R.id.infoPuls);

    }


}
