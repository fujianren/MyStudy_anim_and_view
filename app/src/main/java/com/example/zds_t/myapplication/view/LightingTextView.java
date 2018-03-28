package com.example.zds_t.myapplication.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ZDS-T on 2018/2/5.
 * 会闪烁的textView
 */


public class LightingTextView extends android.support.v7.widget.AppCompatTextView{

    private Paint mPaint;
    private float mDx;      // 偏移变量
    private ValueAnimator mAnimator;
    private LinearGradient mLinearGradient;

    public LightingTextView(Context context) {
        this(context, null);
    }

    public LightingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightingTextView(Context context, @Nullable AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = getPaint();    // 要用getPaint()哦
        setText("让我在这里直接改变你要显示的文本");
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mAnimator = ValueAnimator.ofFloat(0, 2 * getMeasuredWidth());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(2000);
        mAnimator.start();
        mLinearGradient = new LinearGradient(-getMeasuredWidth(), 0,
                0, 0,
                new int[]{getCurrentTextColor(), 0xff00ff00, getCurrentTextColor()},
                new float[]{0, 0.5f, 1f},
                Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Matrix matrix = new Matrix();
        matrix.setTranslate(mDx, 0);
        if (mLinearGradient != null){
            mLinearGradient.setLocalMatrix(matrix);
            mPaint.setShader(mLinearGradient);
        }
        super.onDraw(canvas);
//        int[] colors = {0xffff0000,0xff00ff00,0xff0000ff,0xffffff00,0xff00ffff};
//        float[]  pos = {0f,0.2f,0.4f,0.6f,1.0f};
//        LinearGradient multiGradient = new LinearGradient(0,0,getWidth()/2,getHeight()/2,colors,pos, Shader.TileMode.MIRROR);
//        mPaint.setShader(multiGradient);
//        mPaint.setTextSize(50);
//        canvas.drawText("欢迎关注启舰的blog",0,200,mPaint);
    }
}
