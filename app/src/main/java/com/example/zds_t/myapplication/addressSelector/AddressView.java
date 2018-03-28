package com.example.zds_t.myapplication.addressSelector;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ZDS-T on 2018/1/30.
 * 一个地址选择器
 * 主要由横向的tab，底部线条line，listview三个部分显示
 * 显示刷新顺序，外部传入数据listview获取，刷新显示；listview点击，刷新tab
 * 1.已有最新一级tab下的数据，但未选择listView，则最新一级的tab用“请选择”显示），涉及情况，新建地址，修改地址
 * 2.原本有详细地址进入，直接显示，因为最后一级是确定的就不要“请选择”显示
 * 3.从后面的级不论前往哪个上级修改时，必须是选择了listview才有效，
 */

public class AddressView extends LinearLayout {

    private Context mContext;
    /* tab行容器 */
    private LinearLayout mTabContainer;
    /* 红色线条 */
    private View mLine;
    /* listview */
    private ListView mListView;

    /* 当前所处的地址级别，默认为最高级别0 */
    private int mCurLevel = 0;

    /* 集合存储 */
    private List<String> tabTitles = new ArrayList<>();     // 存储各级标题地址
    private Map<Integer, List<AddressBean>> mAdressMap = new HashMap<>();    // 存储请求到的各级地址表
    private List<String> mIndexList = new ArrayList<>();    // 存储各级tab确定时的分别是选择哪几条

    private List<AddressBean> mCurDataList = new ArrayList<>();   // 当前ListView展示的数据
    private AddressAdapter mAdapter;    // listview的适配器


    public AddressView(Context context) {
        this(context, null);
    }

    public AddressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        initView();
        this.setBackgroundColor(Color.RED);
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View root = inflater.inflate(R.layout.view_address_select, null);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addView(root, lp);


        mTabContainer = (LinearLayout) root.findViewById(R.id.ll_tabContainer);
        mListView = (ListView) root.findViewById(R.id.listview);
//        mLine = (View) root.findViewById(R.id.line);


        mAdapter = new AddressAdapter(mContext, mCurDataList);
        mListView.setAdapter(mAdapter);
    }


    /* 添加一个Tab */
    private void addTab(final String text, final int curLevel){
        int childCount = mTabContainer.getChildCount();
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 10, 10, 10);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "我要更换tab了" + curLevel, Toast.LENGTH_SHORT).show();
//                textView.getX();

//                startTransteAnim((int) textView.getX(), textView.getWidth());
                // 刷新listview显示
                if (mAdressMap.containsKey(curLevel)) {
                    mCurDataList.clear();
                    mCurDataList.addAll(mAdressMap.get(curLevel));
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, "对应的地址表错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        if (childCount >= 1) {
//            mTabContainer.addView(textView, childCount -1, lp);
//        } else {
            mTabContainer.addView(textView, lp);
//        }

    }


    private void startTransteAnim(int endX, final int width){
        ValueAnimator animator = ValueAnimator.ofFloat(mLine.getX(), endX);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (int) animation.getAnimatedValue();
                mLine.layout(x, mLine.getTop(), x + width, mLine.getTop());
            }
        });
        animator.setDuration(700).start();
    }

    /* 每添加一个标题，都要伴随添加对应的 */
    private void addMapData(){

    }


    /* 因为上级选择改变，清除所有下级tab */
    private void removeTab(){

    }

    ///////////////////////////////////////////////////////////////////////////
    // 暴露的公共接口
    ///////////////////////////////////////////////////////////////////////////
    /* 刷新整个view */
    public void notifyChange(){
        int size = mAdressMap.size();

        mCurDataList.clear();
        mCurDataList.addAll(mAdressMap.get(size - 1));
        mAdapter.notifyDataSetChanged();

        mTabContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            addTab("=====" + i, mCurLevel);
        }

    }

    /* 当完整地址，整个view刷新 */
    public void notifyMap(Map<Integer, List<AddressBean>> newAddressMap){
        mAdressMap.clear();
        mAdressMap.putAll(newAddressMap);
        notifyChange();
    }

    /* 添加新级别的数据 */
    public void addData(List<AddressBean> strings) {
        // listview刷新
        mCurDataList.clear();
        mCurDataList.addAll(strings);
        mAdapter.notifyDataSetInvalidated();

        mCurLevel ++;

        mAdressMap.put(mCurLevel, strings);

        addTab("tab" + mCurLevel, mCurLevel);
    }
}
