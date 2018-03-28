package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/29.
 */

public class ColorView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public ColorView(Context context) {
        this(context, null);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_recyclerview_08);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, 300, 300 * mBitmap.getHeight()/mBitmap.getWidth()), mPaint);

        canvas.translate(0, 300);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, 300, 300*mBitmap.getHeight()/mBitmap.getWidth()), mPaint);
    }
}
