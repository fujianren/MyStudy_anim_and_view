package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/2/1.
 * 一个模仿QQ信息提示红点
 */

public class QQRedPointView extends FrameLayout {

    private PointF mStartPoint;
    private PointF mCurPoint;
    private Paint mPaint;
    private Path mPath;
    private final float DEFAULT_RADIUS = 20;
    private float mRadius = DEFAULT_RADIUS;      // 红点半径
    private boolean mTouch;     // 是否触摸
    private TextView mTvTip;
    private ImageView mIvExplored;
    private boolean isStartAnim = false;

    public QQRedPointView(@NonNull Context context) {
        this(context, null);
    }

    public QQRedPointView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mStartPoint = new PointF(100, 100);
        mCurPoint = new PointF();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();

        // 数字哟
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTvTip = new TextView(getContext());
        mTvTip.setLayoutParams(params);
        mTvTip.setPadding(10, 10, 10, 10);
        mTvTip.setBackgroundResource(R.drawable.color_circle);
        mTvTip.setTextColor(Color.GREEN);
        mTvTip.setText("99+");
        addView(mTvTip);

        // 可爱的爆炸
        mIvExplored = new ImageView(getContext());
        mIvExplored.setLayoutParams(params);
        mIvExplored.setImageResource(R.mipmap.ic_launcher_round);
        mIvExplored.setVisibility(INVISIBLE);
        addView(mIvExplored);
    }

    /* 继承GroupView，当然是用骚气的子控件绘制啦 */

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);
        if (!mTouch || isStartAnim){
            mTvTip.setX(mStartPoint.x - mTvTip.getWidth() / 2);
            mTvTip.setY(mStartPoint.y - mTvTip.getHeight() / 2);
        } else {    // 若用户正在触摸状态，就再画一个对应手势位置的圆
            calculatePath();
            canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius, mPaint);

            mTvTip.setX(mCurPoint.x - mTvTip.getWidth() / 2);
            mTvTip.setY(mCurPoint.y - mTvTip.getHeight() / 2);
        }
        canvas.restore();
        super.dispatchDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Rect rect = new Rect();
                int[] location = new int[2];
                mTvTip.getLocationOnScreen(location);
                rect.left = location[0];
                rect.top = location[1];
                rect.right = mTvTip.getWidth() + location[0];
                rect.bottom = mTvTip.getHeight() + location[1];
                // 用的是屏幕坐标的
                if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    mTouch = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouch = false;
                break;
        }
        mCurPoint.set(event.getX(), event.getY());
        postInvalidate();       // 让UI线程画一下啦
        return true;
    }

    /* 计算赛贝尔曲线 */
    private void calculatePath(){
        float x = mCurPoint.x;
        float y = mCurPoint.y;
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;
        float dx = x - startX;
        float dy = y - startY;

        double atan = Math.atan(dy / dx);   // 两圆中心形成的矢量角度
        float offsetX = (float) (mRadius * Math.sin(atan));     // 红点中心到切线的偏移量
        float offsetY = (float) (mRadius * Math.cos(atan));
        // 距离越远，圆点变的越小
        float distance = (float) Math.sqrt(Math.pow(y - startY, 2) +  Math.pow(x - startX, 2));
        mRadius = DEFAULT_RADIUS - distance / 15;
        if (mRadius < 9) {
            isStartAnim = true;
            mIvExplored.setX(mCurPoint.x - mTvTip.getWidth() / 2);
            mIvExplored.setY(mCurPoint.y - mTvTip.getHeight() / 2);
            mIvExplored.setVisibility(VISIBLE);
            mTvTip.setVisibility(GONE);
        }

        // 4个切点的坐标
        float x1 = startX + offsetX;
        float y1 = startY - offsetY;
        float x2 = x + offsetX;
        float y2 = y - offsetY;
        float x3 = x - offsetX;
        float y3 = y + offsetY;
        float x4 = startX - offsetX;
        float y4 = startY + offsetY;
        // 贝塞尔曲线控制点
        float anchorX = (startX + x) / 2;
        float anchorY = (startY + y) / 2;

        mPath.reset();
        mPath.moveTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x3, y3);
        mPath.quadTo(anchorX, anchorY, x4, y4);
        mPath.lineTo(x1, y1);
    }
}
