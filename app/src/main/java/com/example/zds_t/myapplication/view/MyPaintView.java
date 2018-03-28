package com.example.zds_t.myapplication.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by ZDS-T on 2018/1/26.
 */

public class MyPaintView extends View {

    private Paint paint;
    private Path mPath;
    private Path mStampPath;

    public MyPaintView(Context context) {
        this(context, null);
    }

    public MyPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mPath = getPath();
//        startAnim();

        initStampPath();
    }

    private void initStampPath() {
        mStampPath = new Path();
        mStampPath.moveTo(0 ,20);
        mStampPath.lineTo(10,0);
        mStampPath.lineTo(20, 20);
        mStampPath.close();
        mStampPath.addCircle(0, 0, 3, Path.Direction.CCW);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 62);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                phase = (float) animation.getAnimatedValue() % 62;
//                postInvalidate();
                invalidate();
            }
        });
        animator.setDuration(500).start();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(80);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(254, 154, 56);
//        drawCap(canvas);

//        drawStrokeJoin(canvas);

        drawPathEffect(canvas);
    }

    /* 获取一条随机折线 */
    private Path getPath(){
        Path path = new Path();
        // 定义路径的起点
        path.moveTo(0, 0);

        // 定义路径的各个点
        for (int i = 0; i <= 40; i++) {
            path.lineTo(i*35, (float) (Math.random() * 150));
        }
        return path;
    }

    private float phase = 0;
    /* 路径的样式 */
    private void drawPathEffect(Canvas canvas) {
        paint.setStrokeWidth(4);

        canvas.drawPath(mPath, paint);      // 原生绘制

        canvas.translate(0, 100);
        paint.setPathEffect(new CornerPathEffect(50));    // 半径50的圆形折角
        canvas.drawPath(mPath, paint);

        canvas.translate(0, 100);
        paint.setPathEffect(new DashPathEffect(new float[]{20, 20, 2, 20}, phase));
        canvas.drawPath(mPath, paint);      // 自定义的虚线样式，参数1循环体(实20，空20，实2，空20), 参数2偏移量

        canvas.translate(0, 100);
        // 离散路径效果，曲线变锯齿，参数1为分割单位长度，值小意味着者锯齿越多，参数2为偏差值，值小意味着锯齿幅度越大
        paint.setPathEffect(new DiscretePathEffect(2, 10));
        canvas.drawPath(mPath, paint);

        canvas.translate(0, 100);
//        PathDashPathEffect.Style style = PathDashPathEffect.Style.TRANSLATE;
//        PathDashPathEffect.Style style = PathDashPathEffect.Style.MORPH;
        PathDashPathEffect.Style style = PathDashPathEffect.Style.ROTATE;
        // 参数1循环体的绘制路径，参数2循环体的间隔距离，参数3走向路径的偏移,参数4不规则路线时循环体处理
        paint.setPathEffect(new PathDashPathEffect(mStampPath, 35, 0, style));
        canvas.drawPath(mPath, paint);

        // 上吧，相加合体路径
        canvas.translate(0, 100);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(10);
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(6, 10);
        paint.setStyle(Paint.Style.STROKE);
        SumPathEffect sumPathEffect = new SumPathEffect(cornerPathEffect, discretePathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(mPath, paint);

        // 皮卡丘，相乘的合体路径
        canvas.translate(0, 100);
        ComposePathEffect composePathEffect = new ComposePathEffect(cornerPathEffect, discretePathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(mPath, paint);
    }




    /* 线条结合处样式 */
    private void drawStrokeJoin(Canvas canvas) {
        paint.setStrokeWidth(40);
        canvas.save();
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        canvas.drawPath(path, paint);

        canvas.translate(0, 100);
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        paint.setStrokeJoin(Paint.Join.ROUND);  // 圆角
        canvas.drawPath(path, paint);

        canvas.translate(0, 100);
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        paint.setStrokeJoin(Paint.Join.MITER);  // 尖角
        canvas.drawPath(path, paint);

        canvas.translate(0, 100);
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        paint.setStrokeJoin(Paint.Join.BEVEL);  // 直线转折角
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    /* 带帽子的线条 */
    private void drawCap(Canvas canvas) {
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100,200,400,200, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100,400,400,400, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,600,400,600, paint);

        //垂直画出x=100这条线
        paint.reset();
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        canvas.drawLine(100,50,100,750, paint);
    }
}
