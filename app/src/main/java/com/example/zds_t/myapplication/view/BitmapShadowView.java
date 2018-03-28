package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/2/2.
 */

public class BitmapShadowView extends View {

    private Bitmap mBitmap;     // 原图
    private int mDx;            // 阴影x轴偏移
    private int mDy;            // 阴影Y轴偏移
    private float mRadius;      // 阴影模糊半径
    private int mShadowColor;   // 阴影颜色

    private Bitmap mShadowBitmap;// 原图同款位图
    private Paint mPaint;       // 画笔

    public BitmapShadowView(Context context) throws Exception {
        this(context, null);
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) throws Exception {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) throws Exception {
        setLayerType(LAYER_TYPE_SOFTWARE, null);    // 关闭硬件加速

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.bitmapShadow);
        int bitmapID = typedArray.getResourceId(R.styleable.bitmapShadow_src, -1);
        if (bitmapID == -1){
            throw new Exception("BitmapShadowView must need setSrc, and it must be a drawable");
        }
        mBitmap = BitmapFactory.decodeResource(getResources(), bitmapID);
        mDx = typedArray.getInt(R.styleable.bitmapShadow_shadowDx, 0);
        mDy = typedArray.getInt(R.styleable.bitmapShadow_shadowDy, 0);
        mRadius = typedArray.getFloat(R.styleable.bitmapShadow_shadowRadius, 0);
        mShadowColor = typedArray.getColor(R.styleable.bitmapShadow_shadowColor, Color.BLACK);
        typedArray.recycle();

        mPaint = new Paint();
        mShadowBitmap = mBitmap.extractAlpha(); // 原图同款位图
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        setMeasuredDimension(measureWidthMode == MeasureSpec.EXACTLY ? measureWidth : width,
                measureHeightMode == MeasureSpec.EXACTLY ? measureHeight : height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - mDx;
        int height = width * mBitmap.getHeight() / mBitmap.getWidth();
        // 利用同款位图，绘制阴影
        mPaint.setColor(mShadowColor);
        mPaint.setMaskFilter(new BlurMaskFilter(mRadius, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(mShadowBitmap, null, new Rect(mDx, mDy, width, height), mPaint);
        // 绘制原图,盖在阴影上
        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, width, height), mPaint);
    }
}
