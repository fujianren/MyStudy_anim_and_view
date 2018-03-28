package com.example.zds_t.myapplication.animation;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anim);
        ListView listView = (ListView) findViewById(R.id.listview);

        List<Drawable> drawableList = new ArrayList<>();

        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_01));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_02));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_03));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_04));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_05));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_06));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_07));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_08));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_09));
        drawableList.add(getResources().getDrawable(R.mipmap.ic_recyclerview_10));

        ListAdapter adapter = new ListAdapter(this, listView, drawableList);
        listView.setAdapter(adapter);
    }
}
