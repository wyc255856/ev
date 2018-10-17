package com.faw.seniar9.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.faw.seniar9.ManuaApi;
import com.faw.seniar9.R;

/**
 * Created by wyc on 2018/6/29.
 */

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onClick(View view) {
        ManuaApi.getInstance().openManua(this,"");
    }

}
