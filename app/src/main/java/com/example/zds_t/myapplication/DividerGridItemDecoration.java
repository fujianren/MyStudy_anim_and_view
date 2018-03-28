package com.example.zds_t.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by ZDS-T on 2018/1/9.
 * 自定义的RecyclerView的item分割装饰
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration{
    /* 系统默认的分割线属性 */
    public static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    /* 分割线的填充drawable */
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context){
        TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDivider = array.getDrawable(0);       // 固定取值，获得填充的drawable
        array.recycle();
    }

    /* 重写绘制方法，绘制水平分割线和垂直分割线 */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }


    /**
     * 重写分割线的偏移量获取规则
     * @param outRect       偏移的区域
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        // 最后一行不绘底部
        if (isLastRaw(parent, itemPosition, spanCount, childCount)){
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
        // 最后一列，不绘右边
        else if (isLastColumn(parent, itemPosition, spanCount, childCount)){
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }

        else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }

    /* 绘制水平线 */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            // 每个item的水平分割线(底部靠右)
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            if ((pos + 1) % spanCount == 0){
                return true;
            }
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                if ((pos + 1) % spanCount == 0){
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount){
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isLastRaw(RecyclerView parent, int itemPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (itemPosition >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        }

        else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (itemPosition >= childCount)
                    return true;
            }
            // StaggeredGridLayoutManager 且横向滚动
            else {
                // 如果是最后一行，则不需要绘制底部
                if ((itemPosition + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }



}
