package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/2/2.
 * 没啥卵用的望远镜效果
 */

public class TeleScopesView extends View {

    private Paint mPaint;       // 画笔
    private Bitmap mBitmap;     // 背景图
    private Bitmap mBitmapBG;
    private int mDx;            // 望远镜中心坐标
    private int mDy;            // 望远镜中心坐标

    public TeleScopesView(Context context) {
        this(context, null);
    }

    public TeleScopesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TeleScopesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.place_map_guide01);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapBG == null){
            mBitmapBG = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvasBG = new Canvas(mBitmapBG);
            canvasBG.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }
        if (mDx != -1 && mDy != -1){
            // 给画笔设置一个循环图打印，这样画笔绘制的时候会根据坐标自己选取对应的循环图内容绘制
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            // 在画布上，跟随手势绘制出一个圆
            canvas.drawCircle(mDx, mDy, 100, mPaint);
        }
    }
}
