package com.example.zds_t.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZDS-T on 2018/1/9.
 */

public class StaggeredFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
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

        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(mContext, mDatas, false));

        // 4行水平滑动，注意设置item的宽度，否则会连在一起的
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
//                StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + ((char) i));
        }
    }
}
