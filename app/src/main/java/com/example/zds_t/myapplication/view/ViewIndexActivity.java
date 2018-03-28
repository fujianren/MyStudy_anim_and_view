package com.example.zds_t.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zds_t.myapplication.R;
import com.example.zds_t.myapplication.index.IndexAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewIndexActivity extends AppCompatActivity {

    private List<String> dataList = new ArrayList<>();
    private List<Class<?>> mClassList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_index);

        ListView listView = (ListView) findViewById(R.id.listview);
        initData();

        listView.setAdapter(new IndexAdapter(this, dataList, mClassList));
    }

    private void initData() {
        putData("Paint和Canvas", ViewActivity.class);
        putData("RegionView", RegionActivity.class);
        putData("不一样的画笔属性", PaintActivity.class);
        putData("颜色操纵者", ColorActivity.class);
        putData("装逼的Xfermode", XfermodeActivity.class);
        putData("弹性的QQ消息红点", QQActivity.class);
        putData("有阴影的控件", ShadowActivity.class);
        putData("闪烁的文字", LightingTextActivity.class);
        putData("流式标签布局", FlowActivity.class);
        putData("瀑布流图片", WaterFallActivity.class);
    }

    private void putData(String name, Class<?> compatClass) {
        dataList.add(name);
        mClassList.add(compatClass);
    }

}
