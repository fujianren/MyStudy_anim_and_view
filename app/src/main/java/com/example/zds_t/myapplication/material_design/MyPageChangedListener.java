package com.example.zds_t.myapplication.material_design;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zds_t.myapplication.R;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by ZDS-T on 2018/1/12.
 * viewPage换页时的监听实现
 */

public class MyPageChangedListener implements AutoScrollViewPager.OnPageChangeListener {

    private ImageView[] mImageViews;
    public MyPageChangedListener(ImageView[] imageViews){
        this.mImageViews = imageViews;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /* 每次新页码被选定时，重新绘制所有页码点 */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mImageViews.length; i++) {
            if (i == position){
                mImageViews[i].setBackgroundResource(R.drawable.color_circle);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.gray_circle);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     *
     * @param context
     * @param llTopPoint    页码点的容器
     * @param list          页集合
     * @return              返回一个默认页码点的数组
     */
    public static ImageView[] setImageView(Context context, LinearLayout llTopPoint, List list){
        ImageView[] imageViews = new ImageView[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(layoutParams);
            imageViews[i] = imageView;
            if (i == 0){        // 默认选中第一个
                imageViews[i].setBackgroundResource(R.drawable.color_circle);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.gray_circle);
            }
            llTopPoint.addView(imageViews[i]);
        }
        return imageViews;
    }
}
