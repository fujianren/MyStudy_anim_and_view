package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/2/2.
 * 大头贴效果
 */

public class PhotoClipView extends View {

    private Bitmap mBitmap;     // 要被处理的图片
    private int mRadius;        // 矩形的圆角半径
    private int mEnumFormat;    // 剪裁的类型：0表示圆形，1表示矩形
    private Paint mPaint;       // 画笔
    private BitmapShader mBitmapShader; //  给画笔设置的位图模板

    public PhotoClipView(Context context, @Nullable AttributeSet attrs) throws Exception {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) throws Exception {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.photoClip);
        int bitmapID = typedArray.getResourceId(R.styleable.photoClip_mysrc, -1);
        if (bitmapID == -1){
            throw new Exception("bitmap can not be null");
        }
        mBitmap = BitmapFactory.decodeResource(getResources(), bitmapID);
        mEnumFormat = typedArray.getInt(R.styleable.photoClip_format, 0);
        if (mEnumFormat == 1){
            mRadius = typedArray.getInt(R.styleable.photoClip_myradius, 20);
        }
        typedArray.recycle();
        mPaint = new Paint();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
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
        Matrix matrix = new Matrix();
        float scale = getWidth() / mBitmap.getWidth();
        matrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);

        float half = getWidth() / 2;
        if (mEnumFormat == 0){
            canvas.drawCircle(half, half, getWidth() /2 , mPaint);
        } else if (mEnumFormat == 1){
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mRadius, mRadius, mPaint);
        }
    }
}
