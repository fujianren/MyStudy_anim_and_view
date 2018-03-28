package com.example.zds_t.myapplication.receiver;

import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zds_t.myapplication.R;

public class MySenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sender);

        registerReceiver();     // 动态注册Receiver
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    private void send() {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra("msg", "hello receiver");
        sendBroadcast(intent);
    }

    private static final String TAG = "MySenderActivity";
    private void registerReceiver(){
        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        registerReceiver(receiver, filter);
        String st  = "www.baidu.com";
        String aa = ".";
        String[] split = st.split(".");
        for (String s : split) {
            Log.d(TAG, "registerReceiver: " + s);
        }
        Log.d(TAG, "registerReceiver: =========");
    }
}
