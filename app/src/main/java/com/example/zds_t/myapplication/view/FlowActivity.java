package com.example.zds_t.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zds_t.myapplication.R;

public class FlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        FlowLayout flowLayout = (FlowLayout) findViewById(R.id.container);

        flowLayout.addData(new ChildBean());
        flowLayout.addData(new ChildBean());

    }

    private void test(){
        Fragment fragment = new Fragment();
        fragment.getView();
    }
}
