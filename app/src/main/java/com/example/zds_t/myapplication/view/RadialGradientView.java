package com.example.zds_t.myapplication.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Created by ZDS-T on 2018/2/5.
 * 一个酷炫的涟漪效果,只能继承单个控件
 */

public class RadialGradientView extends FrameLayout{

    private Paint mPaint;
    private float radius = 100;
    private RadialGradient mRadialGradient;
    private int mCurRadius;

    public RadialGradientView(Context context) {
        super(context);
        init();
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mCurRadius, mPaint);
    }

    private int mX, mY;
    private ObjectAnimator mAnimator;
    private static final String TAG = "RadialGradientView";

    /**
     * 每个手势都会调用的下发和上传的中转
     * @param event 提供给我们一个触摸事件
     * @return      默认super是走流程，看看自身onTouch，和子控件onTouch的处理结果，然后将结果反馈给父控件
     * true表示向上级反馈已经处理，进入下一个action的处理流程，如果直接写true，则无法进行流程，该控件和子控件的方法都无法被触发
     * false表示向上级反馈无法处理，后续的action也都不会处理，即不会再接收到，如果直接写false，则直接结束
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mX != event.getX() || mY != mY){
            mX = (int) event.getX();
            mY = (int) event.getY();
            setRadius(20);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            View childAt = getChildAt(0);
            Rect rect = new Rect();
            childAt.getLocalVisibleRect(rect);
            if (rect.left < mX && rect.right > mX && rect.top < mY && rect.bottom > mY){
                // 千万强制返回，让其默认向下传递流程，触发子控件的的Touch事件
                // 但是子控件必须要处理啊，不然返回false，就走不到后续的ACTION_UP手势
            } else {
                // 没触摸到子控件，直接返回true，直接告诉父控件处理完了，不然走默认流程，因为子控件不处理会默认返回false
                // 返回false的话，相当于告诉父控件，不要再给我传后面的手势了，这样就收不到ACTION_UP的手势了
                return true;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            initAnimation();
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void initAnimation() {
        if (mAnimator != null && mAnimator.isRunning()){
            mAnimator.cancel();     // 暂停动画
        }
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofInt(this, "radius", 20, getWidth());
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setRadius(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }

        mAnimator.start();
    }


    public void setRadius(int radius){
        mCurRadius = radius;
        if (mCurRadius > 0){
            mRadialGradient = new RadialGradient(mX, mY, mCurRadius,
                    0x00ffffff, 0xff58faac, Shader.TileMode.CLAMP);
            mPaint.setShader(mRadialGradient);
        }
        postInvalidate();

    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }
}
