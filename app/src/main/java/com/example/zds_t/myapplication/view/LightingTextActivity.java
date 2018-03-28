package com.example.zds_t.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

public class LightingTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_text);
//        setContentView(new RadialGradientView(this));
        LightingTextView text = (LightingTextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LightingTextActivity.this, "====", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
