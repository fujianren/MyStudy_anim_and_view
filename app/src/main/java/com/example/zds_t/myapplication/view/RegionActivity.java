package com.example.zds_t.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zds_t.myapplication.R;

public class RegionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyRegionView(this));
//        setContentView(R.layout.activity_region);
    }
}
