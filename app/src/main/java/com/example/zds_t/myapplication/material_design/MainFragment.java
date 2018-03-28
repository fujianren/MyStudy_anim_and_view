package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by ZDS-T on 2018/1/11.
 */

public class MainFragment extends Fragment {



    private List<RecyclerBean> mList = new ArrayList<>();
    private String[] title = {"测试文字_01", "测试文字_02", "测试文字_03", "测试文字_04", "测试文字_05", "测试文字_06", "测试文字_07", "测试文字_08", "测试文字_09", "测试文字_10"};
    private int[] imgPath = {R.mipmap.ic_recyclerview_01, R.mipmap.ic_recyclerview_02, R.mipmap.ic_recyclerview_03, R.mipmap.ic_recyclerview_04, R.mipmap.ic_recyclerview_05,
            R.mipmap.ic_recyclerview_06, R.mipmap.ic_recyclerview_07, R.mipmap.ic_recyclerview_08, R.mipmap.ic_recyclerview_09, R.mipmap.ic_recyclerview_10};
    private int[] vpImgPath = {R.mipmap.ic_viewpager_01, R.mipmap.ic_viewpager_02, R.mipmap.ic_viewpager_03};
    private RecyclerView mRecyclerView;
    private AutoScrollViewPager mVpBooks;
    private LinearLayout mLlPoint;
    private RecyclerViewHeader mHeader;
    private RecyclerView mRecyclerViewGird;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mVpBooks = (AutoScrollViewPager) view.findViewById(R.id.vp_books);
        mLlPoint = (LinearLayout) view.findViewById(R.id.ll_point);
        mHeader = (RecyclerViewHeader) view.findViewById(R.id.header);
        mRecyclerViewGird = (RecyclerView) view.findViewById(R.id.recyclerView_gird);

        mRecyclerView.setHasFixedSize(true);    // 确定item不会被增删改插时，可直接设置为true，表示宽高不在改变
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        // 轮播图的设置
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < vpImgPath.length; i++) {
            RecyclerVpFragment recyclerVpFragment = RecyclerVpFragment.newInstance(vpImgPath[i]);
            fragmentList.add(recyclerVpFragment);
        }
        RecyclerVpAdapter adapter = new RecyclerVpAdapter(getChildFragmentManager(), fragmentList);
        mVpBooks.setAdapter(adapter);

        // 轮播图的索引点
        mVpBooks.addOnPageChangeListener(new MyPageChangedListener(MyPageChangedListener
                .setImageView(getContext(), mLlPoint, fragmentList)));
        mVpBooks.setCurrentItem(0);
        mVpBooks.startAutoScroll();
        mVpBooks.setInterval(5000);
        mVpBooks.setVisibility(View.VISIBLE);

        mHeader.attachTo(mRecyclerView, true);

        setData();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), mList);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerAdapter.mOnItemClickListener));
        mRecyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setData() {
        mList.clear();
        for (int i = 0; i < title.length; i++) {
            RecyclerBean bean = new RecyclerBean();
            bean.setImg(imgPath[i]);
            bean.setInfo(title[i]);
            bean.setTitle(title[i]);
            bean.setCatalog(title[i]);
            bean.setAuthor_intro(title[i]);
            bean.setSummary(title[i]);
            bean.setImglarge(imgPath[i]);
            mList.add(bean);
        }
    }
}
