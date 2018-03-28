package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ZDS-T on 2018/2/6.
 * 流式布局
 */

public class FlowLayout extends ViewGroup{

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    /*================== 重写margin ===================*/

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return super.generateLayoutParams(attrs);
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
//        return super.generateLayoutParams(p);
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
//        return super.generateDefaultLayoutParams();
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;      // 记录当前一行的宽度
        int lineHeight = 0;     // 记录当前一行的高度
        int height = 0;         // 整个FlowLayout所占的高度
        int width = 0;          // 整个FlowLayout所占的宽度

        // 遍历测量每个child，再通过child的大小推测出容器的大小
        int count = getChildCount();
        Log.d(TAG, "onMeasure: " + count);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

            if (lineWidth + childWidth > measureWidth){
                // 这一行安排满了，换行
                width = Math.max(lineWidth, childWidth);    // 容器宽度重设
                height += lineHeight;       // 容器高度加一行
                lineHeight = childHeight;   // 换行了，目前新的行高度，同换行的child
                lineWidth = childWidth;     // 换行了，目前新的行宽度，同换行的child
            } else {    // 安排child时，一行还没满
                lineHeight = Math.max(lineHeight, childHeight); // 当前行高度
                lineWidth += childWidth;            // 当前行宽度变化了，加一个childWidth
            }
            // 安排最后一个child
            if (i == count - 1){
                height += lineHeight;   // 容器高因为不参与第一行，这里补一行
                width = Math.max(width, lineWidth); // 容器宽因为不参与第一行，这里补计算
            }
        }
        // 是时候确定尺寸大小了
        setMeasuredDimension(measureWidthMode == MeasureSpec.EXACTLY ? measureWidth : width,
                measureHeightMode == MeasureSpec.EXACTLY ? measureHeight : height);
    }

    private static final String TAG = "FlowLayout";
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;      // 当前行宽
        int lineHeight = 0;     // 当前行高
        int top = 0, left = 0;  // 当前child的左上坐标
        Log.d(TAG, "onLayout: " + count);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            String text = ((TextView) child).getText().toString();
            Log.d(TAG, "onLayout: 这是第"+ i + "个child" + text);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            Log.d(TAG, "onLayout: 宽：" + childWidth);
            Log.d(TAG, "onLayout: 高：" + childHeight);
            // 排满换行
            if (childWidth + lineWidth > getMeasuredWidth()){
                top += lineHeight;          // y加行，即新行的顶部
                left = 0;                   // x归零,即新行的起点
                lineHeight = childHeight;   // 变成新的一行
                lineWidth = childWidth;     // 变成新的一行
            } else {    // 未满行，行宽因为新安排的child调整
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            // child的最终区域
            int cleft = left + lp.leftMargin;
            int ctop = top + lp.topMargin;
            int cright = cleft + child.getMeasuredWidth();
            int cbottom = ctop + child.getMeasuredHeight();
            child.layout(cleft, ctop, cright, cbottom);
            // 开始下一个起点
            left += childWidth;
        }
    }

    private List<ChildBean> mChildBeanList = new ArrayList<>();
    public void notify(List<ChildBean> beanList){
        mChildBeanList.clear();
        mChildBeanList.addAll(beanList);
        removeAllViews();
        for (int i = 0; i < mChildBeanList.size(); i++) {
            addData(mChildBeanList.get(i));
        }
    }

    /* 增加一个item */
    public void addData(ChildBean childBean){
        addData(-1, childBean);
    }

    public void addData(int index, ChildBean childBean){
        TextView textView = new TextView(getContext());
        MarginLayoutParams lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        textView.setText("猜猜我是谁");
        if (index > -1){
            mChildBeanList.add(index, childBean);
            addView(textView, index);  // 重新requestLayout(),invalidate()
        } else {
            mChildBeanList.add(childBean);
            addView(textView);
        }
        requestLayout();
    }

    /* 拖拽时的item */
    public void changeData(int srcIndex, int dstIndex){
        ChildBean childBean = mChildBeanList.get(srcIndex);
        mChildBeanList.add(dstIndex, childBean);
        addData(dstIndex, childBean);
        if (srcIndex < dstIndex){
            deleteData(srcIndex);
        } else {
            deleteData(srcIndex + 1);
        }
    }

    /* 删除一个item */
    public void deleteData(int index){
        if (mChildBeanList.size() > index){
            mChildBeanList.remove(index);
            removeViewAt(index);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // setter & getter
    ///////////////////////////////////////////////////////////////////////////
    public void setChildBeanList(List<ChildBean> childBeanList){
        notify(childBeanList);
    }

    public List<ChildBean> getChildBeanList(){
        return mChildBeanList;
    }

}
