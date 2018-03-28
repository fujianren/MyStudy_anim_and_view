package com.example.zds_t.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by ZDS-T on 2018/1/25.
 */

public class MyRegionView extends View {

    private Path mWavePath;
    private Paint mPaint;
    private Path mPath;
    private float mPreX;
    private float mPreY;

    public MyRegionView(Context context) {
        this(context, null);
    }

    public MyRegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mPath = new Path();
        mWavePath = new Path();
        startAnim();
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLenth);
        animator.setDuration(700);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Region region = new Region(10, 10, 100, 100);
        Path path = new Path();
        RectF rectF = new RectF(50, 50, 200, 500);
        path.addOval(rectF, Path.Direction.CCW);

        // 交集区域
        Region region = new Region();
        region.setPath(path, new Region(50, 50, 200, 200));
        drawRegion(canvas, region, mPaint);

        canvas.save();
        canvas.translate(200, 0);
        canvas.drawRect(50, 50, 200, 200, mPaint);
        canvas.restore();

        drawOp(canvas);
        canvas.drawPath(mPath, mPaint);

        drawWave(canvas);
    }
    private int mWaveLenth = 400;
    private int dx = 0;
    private void drawWave(Canvas canvas) {
        mWavePath.reset();
        mPaint.setStyle(Paint.Style.FILL);
        int originY = 300;
        int halfWaveLen = mWaveLenth / 2;
        mWavePath.moveTo(-mWaveLenth + dx, originY);
        // 画个sin
        for (int i = -mWaveLenth; i < getWidth() + mWaveLenth; i+=mWaveLenth) {
            mWavePath.rQuadTo(halfWaveLen / 2, -50, halfWaveLen, 0);
            mWavePath.rQuadTo(halfWaveLen / 2, 50, halfWaveLen, 0);
        }
        // 闭合一下下
        mWavePath.lineTo(getWidth(), getHeight());
        mWavePath.lineTo(0, getHeight());
        mWavePath.close();

        canvas.drawPath(mWavePath, mPaint);
    }

    private void drawOp(Canvas canvas) {
        Rect rect1 = new Rect(100, 400, 400, 500);
        Rect rect2 = new Rect(200, 300, 300, 600);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect1, mPaint);
        canvas.drawRect(rect2, mPaint);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);

        region1.op(region2, Region.Op.INTERSECT);   // 取交集
        Paint paint_fill = new Paint();
        paint_fill.setColor(Color.GREEN);
        paint_fill.setStyle(Paint.Style.FILL);
        drawRegion(canvas, region1, paint_fill);
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (iterator.next(rect)){
            canvas.drawRect(rect, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPreX = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPreX, mPreY);
                return true;
            case MotionEvent.ACTION_MOVE:
                // 假设滑动时手势连续读取到的3个点为A，B，C,现在手势进行到C点上
                // 则从B点到C点绘制的实际曲线为
                // 起点 = AB中点，终点 = BC中点，控制点 = B
                // 的二阶赛贝尔曲线
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
        }
        return super.onTouchEvent(event);
    }


}
