package com.example.zds_t.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

import java.util.Random;

public class WaterFallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_fall);

        Button btn = (Button) findViewById(R.id.btn);
        final WaterFallLayout fallLayout = (WaterFallLayout) findViewById(R.id.container);
//        final LinearLayout fallLayout = (LinearLayout) findViewById(R.id.container);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(fallLayout);
//                addView2(fallLayout);
            }
        });
    }

    private void addView2(LinearLayout fallLayout) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_recyclerview_01);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fallLayout.addView(imageView, layoutParams);
    }

    private void addView(WaterFallLayout fallLayout) {
        Random random = new Random();
        int num = Math.abs(random.nextInt());

        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.ic_recyclerview_01);
        imageView.setImageResource(R.mipmap.ic_recyclerview_10);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fallLayout.addView(imageView, layoutParams);
        fallLayout.setOnItemClickListener(new WaterFallLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {
                Toast.makeText(WaterFallActivity.this, "item=" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
