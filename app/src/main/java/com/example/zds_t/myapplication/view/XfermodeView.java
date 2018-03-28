package com.example.zds_t.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/31.
 * Xfermode的案例演示
 */

public class XfermodeView extends View {

    private int width = 300;
    private int height = 300;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;


    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dstBmp = makeDst(width, height);
        srcBmp = makeSrc(width, height);
        mPaint = new Paint();
    }

    private Bitmap makeSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xffffcc44);
        canvas.drawOval(new RectF(0, 0, width, height), paint);
        return bitmap;
    }

    private Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);   // bitmap不能直接被绘制，盖上一张画布就可以了
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xff66aaff);
        canvas.drawRect(0, 0, width, height, paint);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        doPorterDuff(canvas);
//        clipCanvas(canvas);
    }

    @SuppressLint("WrongConstant")
    private void clipCanvas(Canvas canvas) {
        canvas.drawColor(Color.RED);
//        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.clipRect(100, 0, 200, 100);  // 将我们能够操作的范围限制了
//        canvas.restore();
        canvas.drawColor(Color.YELLOW);
        canvas.clipRect(100, 0, 150, 50);
        canvas.drawColor(Color.GREEN);
    }

    private void doPorterDuff(Canvas canvas) {
        canvas.drawARGB(240, 128, 128, 128);
        // 1. 新建一个那么大的图层
        int layerID = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        // 2. 开始Xfermode绘制
        canvas.drawBitmap(dstBmp, 0, 0, mPaint);    // 画上dst图,先画的是底

//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  // 给画笔挂载Xfermode
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  // 给画笔挂载Xfermode
        canvas.drawBitmap(srcBmp, width / 2, height / 2, mPaint);   // 画上src图
//        canvas.drawBitmap(srcBmp, 0, 0, mPaint);   // 画上src图
        mPaint.setXfermode(null);

        // 3. 还原
        canvas.restoreToCount(layerID);
    }


}
