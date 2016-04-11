package com.lardis.ivan.testcustomview.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lardis.ivan.testcustomview.EnumActivitySpinner1;
import com.lardis.ivan.testcustomview.EnumActivitySpinner2;
import com.lardis.ivan.testcustomview.EnumActivitySpinner3;
import com.lardis.ivan.testcustomview.Presenter.PresenterActivity;
import com.lardis.ivan.testcustomview.R;
import com.lardis.ivan.testcustomview.View.ViewGraph.myGroopView_Zoom_Info_Graph;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InterfaceMainActivity {
    Spinner spinner3,spinner2,spinner1;
    myGroopView_Zoom_Info_Graph absoluteLayout;
    TextView tvDetStatPeriod, tvDetStatSrednee, tvDetStatItogo,
            tvDetStatProideno, tvDetStatInfoMonth, tvDetStatSredneeZnazhenie;
    private Toolbar mToolbar;
    Button btDetStatRazvernut;
    MySpinner3Adapter adapterSpinner3;
    MySpinner1And2Adapter adapterSpinner2,adapterSpinner1;
    PresenterActivity presenterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.det_stat);
        init();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.det_stat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.action_settings1:

                  Toast.makeText(this,"test1",Toast.LENGTH_LONG).show();
                break;

            case R.id.action_settings2:

                Toast.makeText(this,"test2",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings3:

                Toast.makeText(this,"test3",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings4:

                Toast.makeText(this,"test4",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings5:

                Toast.makeText(this,"test5",Toast.LENGTH_LONG).show();
                break;



        }

        return true;




    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Детальная статистика");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        presenterActivity = new PresenterActivity(this);
        absoluteLayout = (myGroopView_Zoom_Info_Graph) findViewById(R.id.mygroop);
        spinner1 = (Spinner) findViewById(R.id.det_stat_spiner1);
        spinner2 = (Spinner) findViewById(R.id.det_stat_spiner2);
        spinner3 = (Spinner) findViewById(R.id.det_stat_spiner3);
        tvDetStatPeriod = (TextView) findViewById(R.id.tvDetStatPeriod);
        tvDetStatSrednee = (TextView) findViewById(R.id.tvDetStatSrednee);
        tvDetStatItogo = (TextView) findViewById(R.id.tvDetStatItogo);
        tvDetStatProideno = (TextView) findViewById(R.id.tvDetStatProideno);
        tvDetStatInfoMonth = (TextView) findViewById(R.id.tvDetStatInfoMonth);
        tvDetStatSredneeZnazhenie = (TextView) findViewById(R.id.tvDetStatSredneeZnazhenie);
        btDetStatRazvernut = (Button) findViewById(R.id.btDetStatRazvernut);
       AdapterView.OnItemSelectedListener onItemSelectedListener= new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                presenterActivity.getData(spinner1.getSelectedItemPosition(), spinner2.getSelectedItemPosition(),spinner3.getSelectedItemPosition());

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner3.setOnItemSelectedListener(onItemSelectedListener);
        spinner1.setOnItemSelectedListener(onItemSelectedListener);
        spinner2.setOnItemSelectedListener(onItemSelectedListener);


        presenterActivity.getTitleSpinner();


    }


    @Override
    public void updateData(ModelObjectActivityWithoutSpiners dataActivity) {

        tvDetStatPeriod.setText(dataActivity.getDetStatPeriod());
        tvDetStatSrednee.setText(dataActivity.getDetStatSrednee());
        tvDetStatItogo.setText(dataActivity.getDetStatItogo());
        tvDetStatProideno.setText(dataActivity.getDetStatProideno());
        tvDetStatInfoMonth.setText(dataActivity.getDetStatInfoMonth());
        tvDetStatSredneeZnazhenie.setText(dataActivity.getDetStatSredneeZnazhenie());

        absoluteLayout.setDataGraphAndInfo(dataActivity.getDataGraph(), dataActivity.getArrayListBlockInfo());


    }

    @Override
    public void updateAdapterSpinner(ModelObjectSpinners modelSpinners) {

        if (adapterSpinner3 == null) {
            adapterSpinner3 = new MySpinner3Adapter(this,
                    R.layout.det_stat_my_spiner3_for_activity, modelSpinners.getSpinner3ArrayList());
            spinner3.setAdapter(adapterSpinner3);
        }
        if (adapterSpinner2 == null) {
            adapterSpinner2 = new MySpinner1And2Adapter(this,
                    R.layout.det_stat_my_spiner1_and2_for_activity, modelSpinners.getSpinner2ArrayList());
            spinner2.setAdapter(adapterSpinner2);
        }
        if (adapterSpinner1 == null) {
            adapterSpinner1 = new MySpinner1And2Adapter(this,
                    R.layout.det_stat_my_spiner1_and2_for_activity, modelSpinners.getSpinner1ArrayList());
            spinner1.setAdapter(adapterSpinner1);
        }

        adapterSpinner3.notifyDataSetChanged();
        adapterSpinner2.notifyDataSetChanged();
        adapterSpinner1.notifyDataSetChanged();
    }


    public class MySpinner3Adapter extends ArrayAdapter<String> {
        ArrayList<EnumActivitySpinner3> arrayListSpinner3;

        public MySpinner3Adapter(Context context, int textViewResourceId, ArrayList<EnumActivitySpinner3> spinner3Set) {
            super(context, textViewResourceId);
            this.arrayListSpinner3 = spinner3Set;

            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.det_stat_my_spiner3_for_activity, parent, false);
            TextView label = (TextView) row.findViewById(R.id.weekofday);
            label.setText(getDataSpinner3(arrayListSpinner3.get(position)).getName());

            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            icon.setImageResource(getDataSpinner3(arrayListSpinner3.get(position)).getResourseImg());
            return row;
        }

        @Override
        public int getCount() {
            return arrayListSpinner3.size();
        }


    }



    public class MySpinner1And2Adapter extends ArrayAdapter<String> {
        ArrayList<?> arrayListSpinner ;


        public MySpinner1And2Adapter(Context context, int textViewResourceId, ArrayList<?> arrayListSpinner ) {
            super(context, textViewResourceId);

            this.arrayListSpinner= arrayListSpinner;

            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.det_stat_my_spiner1_and2_for_activity, parent, false);
            TextView label = (TextView) row.findViewById(R.id.weekofday);

            if(arrayListSpinner!=null)if(arrayListSpinner.size()>0) {
                if (arrayListSpinner.get(0) instanceof EnumActivitySpinner1)
                    label.setText(getDataSpinner1((EnumActivitySpinner1) arrayListSpinner.get(position)));
                if (arrayListSpinner.get(0) instanceof EnumActivitySpinner2)
                    label.setText(getDataSpinner2((EnumActivitySpinner2) arrayListSpinner.get(position)));
            }
            return row;
        }

        @Override
        public int getCount() {
            return arrayListSpinner.size();
        }


    }
    @NonNull
    private ModelObjectSpinner3 getDataSpinner3(EnumActivitySpinner3 enumDataGraph) {
        switch (enumDataGraph) {
            case SLEEP:
                return new ModelObjectSpinner3("Сон", R.drawable.sleep);

            case PRESSURE:
                return new ModelObjectSpinner3("Давление", R.drawable.pressure);

            case CALORIES:
                return new ModelObjectSpinner3("Калорий", R.drawable.calories);

            case WATER:
                return new ModelObjectSpinner3("Вода", R.drawable.water);

            case WEIGHT:
                return new ModelObjectSpinner3("Вес", R.drawable.weight);

            case PULSE:
                return new ModelObjectSpinner3("Пульс", R.drawable.pulse);

            case STEP:
                return new ModelObjectSpinner3("Шаги", R.drawable.step);


            case DISTANCE:
                return new ModelObjectSpinner3("Дистанция", R.drawable.distance);


        }
        return null;
    }

    private String getDataSpinner2(EnumActivitySpinner2 enumDataGraph) {

        switch (enumDataGraph) {
            case BY_DAY:
                return "по дням";

            case BY_MONTH:
                return "по месяцам";


        }
        return "";

    }
    private String getDataSpinner1(EnumActivitySpinner1 enumDataGraph) {

        switch (enumDataGraph) {
            case HALF:
                return "6 месяцев";

            case MONTH:
                return "Месяц";
            case WEEK:
                return "Неделя";

            case NO:
                return "Нет" ;

        }
        return "";

    }

}
