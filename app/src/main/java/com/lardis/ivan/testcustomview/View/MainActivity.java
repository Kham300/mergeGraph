package com.lardis.ivan.testcustomview.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lardis.ivan.testcustomview.Presenter.PresenterActivity;
import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopAbsoluteLayout;

public class MainActivity extends AppCompatActivity implements InterfaceData {
    Button button1, button2, button3, button4, button5;
    Button button6, button7, button8, button9, button10;

    myGroopAbsoluteLayout absoluteLayout;
    TextView tvDetStatPeriod, tvDetStatSrednee, tvDetStatItogo,
            tvDetStatProideno, tvDetStatInfoMonth, tvDetStatSredneeZnazhenie;

    Button btDetStatRazvernut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.det_stat);
        init();
        PresenterActivity presenterActivity=new PresenterActivity(this);
        presenterActivity.getData();

    }

    private void init() {
        absoluteLayout = (myGroopAbsoluteLayout) findViewById(R.id.mygroop);

        tvDetStatPeriod = (TextView) findViewById(R.id.tvDetStatPeriod);
        tvDetStatSrednee = (TextView) findViewById(R.id.tvDetStatSrednee);
        tvDetStatItogo = (TextView) findViewById(R.id.tvDetStatItogo);
        tvDetStatProideno = (TextView) findViewById(R.id.tvDetStatProideno);
        tvDetStatInfoMonth = (TextView) findViewById(R.id.tvDetStatInfoMonth);
        tvDetStatSredneeZnazhenie = (TextView) findViewById(R.id.tvDetStatSredneeZnazhenie);
        btDetStatRazvernut = (Button) findViewById(R.id.btDetStatRazvernut);


    }



    @Override
    public void updateData(DataActivity dataActivity) {

        tvDetStatPeriod.setText(dataActivity.getDetStatPeriod());
        tvDetStatSrednee.setText(dataActivity.getDetStatSrednee());
        tvDetStatItogo.setText(dataActivity.getDetStatItogo());
        tvDetStatProideno.setText(dataActivity.getDetStatProideno());
        tvDetStatInfoMonth.setText(dataActivity.getDetStatInfoMonth());
        tvDetStatSredneeZnazhenie.setText(dataActivity.getDetStatSredneeZnazhenie());

        absoluteLayout.setDataGraphAndInfo(dataActivity.getDataGraph(),dataActivity.getArrayListBlockInfo());


    }

}
