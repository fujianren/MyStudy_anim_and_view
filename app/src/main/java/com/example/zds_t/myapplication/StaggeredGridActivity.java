package com.example.zds_t.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);
        //找到控件
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //初始化数据
        initData();
        //设置瀑布流的布局方式,参数一:3列 参数二:垂直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //为recyclerView设置布局管理器
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //创建适配器
        final MyRecyclerViewAdapter myRecyclerViewAdapter =new MyRecyclerViewAdapter(this, dataList);
        //设置适配器
        recyclerView.setAdapter(myRecyclerViewAdapter);
        //自定义监听
        myRecyclerViewAdapter.setOnItemCickListener(new MyRecyclerViewAdapter.MyOnItemClickListener() {
            @Override
            public void myOnItemClickListener(int position, View view) {
                Toast.makeText(StaggeredGridActivity.this, "点击了第"+position+"条", Toast.LENGTH_SHORT).show();
                myRecyclerViewAdapter.deleteData(position);
            }
        });
        myRecyclerViewAdapter.setOnLongCickListener(new MyRecyclerViewAdapter.MyOnLongClickListener() {
            @Override
            public void myOnLongClickListener(int position, View view) {
                myRecyclerViewAdapter.addData(position,"我是新来的");
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            dataList.add(i+"");
        }
    }
}
