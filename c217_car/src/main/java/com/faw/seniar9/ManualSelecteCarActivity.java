package com.faw.seniar9;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.faw.seniar9.util.FireUtil;
import com.faw.seniar9.util.SharedpreferencesUtil;
import com.wyc.c217_car.R;

/**
 * Created by wyc on 2018/4/19.
 */

public class ManualSelecteCarActivity extends BaseActivity implements View.OnClickListener {
    View spinner, spinner_layout;
    TextView spinner_text;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    @Override
    protected void initData() {
        setContentView(R.layout.activity_select);
        spinner_layout = findViewById(R.id.spinner_layout);
        spinner_text = (TextView) findViewById(R.id.model);
        spinner = findViewById(R.id.spinner);
//        yes_btn = findViewById(R.id.yes_btn);
//        no_btn = findViewById(R.id.no_btn);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {
        if (SharedpreferencesUtil.getIsFirst(this)) {
            FireUtil.isExist(this);
        }
//        no_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_layout.getVisibility() == View.GONE) {
                    spinner_layout.setVisibility(View.VISIBLE);
                } else {
                    spinner_layout.setVisibility(View.GONE);
                }
            }
        });
//        yes_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void initSelect() {
        textView1 = (TextView) findViewById(R.id.text_1);
        textView2 = (TextView) findViewById(R.id.text_2);
        textView3 = (TextView) findViewById(R.id.text_3);
        textView4 = (TextView) findViewById(R.id.text_4);
//        textView5 = (TextView) findViewById(R.id.text_5);
//        textView6 = (TextView) findViewById(R.id.text_6);
//        textView7 = (TextView) findViewById(R.id.text_7);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
//        textView5.setOnClickListener(this);
//        textView6.setOnClickListener(this);
//        textView7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        spinner_layout.setVisibility(View.GONE);
        spinner_text.setText(((TextView) v).getText().toString());
        String str = spinner_text.getText().toString();
        if (str.equals("两驱舒适型-南方版")) {
            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "EV_1");
        } else if (str.equals("两驱舒适型-北方版")) {
            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "EV_2");
        } else if (str.equals("四驱豪华型-南方版")) {
            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "EV_3");
        } else if (str.equals("四驱豪华型-北方版")) {
            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "EV_4");
        }
//        else if (str.equals("自动豪华")) {
//            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "C217_5");
//        } else if (str.equals("自动尊贵")) {
//            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "C217_6");
//        } else if (str.equals("自动旗舰")) {
//            SharedpreferencesUtil.setCarModel(ManualSelecteCarActivity.this, "C217_7");
//        }
        SharedpreferencesUtil.setIsFirst(ManualSelecteCarActivity.this, false);
        startActivity(new Intent(ManualSelecteCarActivity.this, ManualWebActivity.class));
        finish();

    }

}
