package com.example.zds_t.myapplication;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by ZDS-T on 2018/1/9.
 */

public class GridFragment extends NormalFragment {

    @Override
    protected void initStyle() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
    }
}
