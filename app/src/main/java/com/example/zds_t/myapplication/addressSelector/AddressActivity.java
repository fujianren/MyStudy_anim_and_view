package com.example.zds_t.myapplication.addressSelector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private AddressView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
//        setContentView(new AddressView(this));

        mView = (AddressView) findViewById(R.id.view);
        Button btn_add = (Button) findViewById(R.id.add_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData() {
        List<AddressBean> strings = new ArrayList<>();
        strings.add(new AddressBean(0, "aaaa"));
        strings.add(new AddressBean(1, "bbbb"));
        strings.add(new AddressBean(2, "ccccc"));
        strings.add(new AddressBean(3, "aaadddda"));
        strings.add(new AddressBean(4, "eeee"));
        strings.add(new AddressBean(5, "fff"));
        strings.add(new AddressBean(6, "ddd"));
        strings.add(new AddressBean(7, "aaahhhha"));
        mView.addData(strings);
    }
}
