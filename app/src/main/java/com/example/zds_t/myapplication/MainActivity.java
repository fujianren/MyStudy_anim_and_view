package com.example.zds_t.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (FrameLayout) findViewById(R.id.container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new NormalFragment())
                .commit();

        startSMS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.normal:
                manager.beginTransaction().replace(R.id.container, new NormalFragment()).commit();
                return true;
            case R.id.liner:
                manager.beginTransaction().replace(R.id.container, new GridFragment()).commit();
                return true;
            case R.id.grider:
                manager.beginTransaction().replace(R.id.container, new StaggeredFragment()).commit();
                return true;
            case R.id.staggeredGrid:
                manager.beginTransaction().replace(R.id.container, new GridFragment()).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* 隐式意图启动系统短信编辑 */
    private void startSMS(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);         // 发消息的动作
        intent.addCategory(Intent.CATEGORY_DEFAULT);    // 附加信息
        intent.setData(Uri.parse("sms:10086"));
        startActivity(intent);
    }
}
