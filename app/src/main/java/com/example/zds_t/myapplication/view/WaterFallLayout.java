package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZDS-T on 2018/2/8.
 * 自定义的瀑布流
 */

public class WaterFallLayout extends ViewGroup {

    private static final String TAG = "WaterFallLayout";
    private int columns = 3;    // 当前的列数
    private int hSpace = 20;    // 水平间隔
    private int vSpace = 20;    // 垂直间隔
    private int childWidth = 0; // 每个child所占的宽度，确定了列数可计算
    private int top[];          // 记录各列的盛载高度

    public WaterFallLayout(Context context) {
        this(context, null);
    }

    public WaterFallLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        top = new int[columns];
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        childWidth = (widthMeasure - (columns - 1) * hSpace) / columns;

        // 盛载宽度
        int wrapWidth;
        int childCount = getChildCount();
        if (childCount < columns){
            wrapWidth = childCount * childWidth + (childCount - 1) * hSpace;
        } else {
            wrapWidth = widthMeasure;
        }
        // 盛载高度
        clearTop();         // 重写测量时清空上次记录的高度
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            int minColumn = getMinHeightColumn();      // 最小的是哪一列
            top[minColumn] = top[minColumn]  + vSpace + childHeight;    // child就拼在哪一列后面
        }
        int wrapHeight;
        wrapHeight = getMaxHeight();        // 筛选出我们哪一列最高，作为盛载高度
        // 告诉系统我最终的容器设定值，高度必须用wrapHeight，不然嵌套在scrollView中因为固定无法滚动
        setMeasuredDimension(widthMeasureMode == MeasureSpec.AT_MOST ? wrapWidth : widthMeasureSpec,
                wrapHeight);
    }

    private int getMaxHeight() {
        int maxHeight = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] > maxHeight){
                maxHeight = top[i];
            }
        }
        return maxHeight;
    }

    private void clearTop() {
        for (int i = 0; i < columns; i++) {
            top[i] = 0;
        }
    }

    private int getMinHeightColumn() {
        int minColumn = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] < top[minColumn]){
                minColumn = i;
            }
        }
        return minColumn;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        clearTop();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            int minColumn = getMinHeightColumn();
            int cLeft = minColumn * (childWidth + hSpace);
            int cTop = top[minColumn];
            int cRight = cLeft + childWidth;
            int cBottom = cTop + childHeight;
            top[minColumn] += (vSpace + childHeight);
            child.layout(cLeft, cTop, cRight, cBottom);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int index);
    }

    public void setOnItemClickListener(final OnItemClickListener listener){
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, index);
                }
            });
        }
    }
}
