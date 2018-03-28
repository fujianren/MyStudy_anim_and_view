package com.example.zds_t.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZDS-T on 2018/1/9.
 */

public class NormalFragment extends Fragment {

    protected Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<String> mDatas;
    protected HomeAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycleview);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(mContext, mDatas));

        initStyle();
    }

    protected void initStyle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(        // 系统默认的listdivider，可在style中设置
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + ((char) i));
        }
    }
}
