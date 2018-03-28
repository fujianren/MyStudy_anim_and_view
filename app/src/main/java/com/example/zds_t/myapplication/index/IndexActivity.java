package com.example.zds_t.myapplication.index;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zds_t.myapplication.MainActivity;
import com.example.zds_t.myapplication.R;
import com.example.zds_t.myapplication.addressSelector.AddressActivity;
import com.example.zds_t.myapplication.animation.AnimationActivity;
import com.example.zds_t.myapplication.animation.AnimatorActivity;
import com.example.zds_t.myapplication.animation.AnimatorSetActivity;
import com.example.zds_t.myapplication.animation.AnimatorSetDemoActivity;
import com.example.zds_t.myapplication.animation.InterpolatorActivity;
import com.example.zds_t.myapplication.animation.LayoutAnimActivity;
import com.example.zds_t.myapplication.animation.LayoutAnimation2Activity;
import com.example.zds_t.myapplication.animation.ObjectAnimatorActivity;
import com.example.zds_t.myapplication.material_design.DesignActivity;
import com.example.zds_t.myapplication.receiver.MySenderActivity;
import com.example.zds_t.myapplication.view.ViewActivity;
import com.example.zds_t.myapplication.view.ViewIndexActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    private List<String> dataList = new ArrayList<>();
    private List<Class<?>> mClassList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        ListView listView = (ListView) findViewById(R.id.listview);
        initData();

        listView.setAdapter(new IndexAdapter(this, dataList, mClassList));
    }

    private void initData() {
        putData("主界面", MainActivity.class);
        putData("Design界面", DesignActivity.class);
        putData("补间动画", AnimationActivity.class);
        putData("补间动画插值器效果", InterpolatorActivity.class);
        putData("属性动画", AnimatorActivity.class);
        putData("属性动画之对象动画", ObjectAnimatorActivity.class);
        putData("属性动画之联合动画", AnimatorSetActivity.class);
        putData("属性动画之联合动画案例", AnimatorSetDemoActivity.class);
        putData("布局动画之创建时补间动画", LayoutAnimActivity.class);
        putData("布局动画之增删时属性动画", LayoutAnimation2Activity.class);
        putData("绘图学习入口", ViewIndexActivity.class);
        putData("地址选择器", AddressActivity.class);
        putData("广播发送", MySenderActivity.class);

    }

    private void putData(String name, Class<?> compatClass) {
        dataList.add(name);
        mClassList.add(compatClass);
    }


}
