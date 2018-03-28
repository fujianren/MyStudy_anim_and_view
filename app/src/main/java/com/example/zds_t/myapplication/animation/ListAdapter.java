package com.example.zds_t.myapplication.animation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ZDS-T on 2018/1/23.
 * 一个上拉时给item做动画的适配器
 * 核心：
 * 监听滑动，如果是下滑时，在{@link #getView(int, View, ViewGroup)}中开启动画
 */

public class ListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ListView mListView;
    private final List<Drawable> mDrawableList;
    private final int LENTH = 100;

    private final Animation mAnimation;
    private boolean isScrollDown;   // 是不是向下滑动
    private int mFirstPosition = 0;
    private int mFirstTop = 0;

    public ListAdapter(Context context, ListView listView, List<Drawable> drawableList){

        mContext = context;
        mListView = listView;
        mDrawableList = drawableList;

        mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
        mListView.setOnScrollListener(mOnScrollListener);
    }

    AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstChild = view.getChildAt(0);   // 本次可见的第一个item
            if (firstChild == null) return;
            int top = firstChild.getTop();          // 本次第一个item的顶部
            // 本次item在上次item之后，或者同一个item中顶部上移了，top值会比上次的小，则说明是在下滑
            isScrollDown = firstVisibleItem > mFirstPosition || mFirstTop > top;
            mFirstTop = top;
            mFirstPosition = firstVisibleItem;
        }
    };


    @Override
    public int getCount() {
        return LENTH;
    }

    @Override
    public Object getItem(int position) {
        return mDrawableList.get(position % mDrawableList.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        for (int i = 0; i < mListView.getChildCount(); i++) {
            View child = mListView.getChildAt(i);
            child.clearAnimation();
        }

        if (isScrollDown){
            convertView.startAnimation(mAnimation);
        }
        holder.setData(position);

        return convertView;
    }

    public class ViewHolder{
        public ImageView iv;
        public TextView tv;
        ViewHolder(View convertView){
            iv = (ImageView) convertView.findViewById(R.id.image);
            tv = (TextView) convertView.findViewById(R.id.text);
        }

        public void setData(int position){
            iv.setImageDrawable(mDrawableList.get(position % mDrawableList.size()));
            tv.setText(position + "");
        }
    }
}
