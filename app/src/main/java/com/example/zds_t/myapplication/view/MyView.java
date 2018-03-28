package com.example.zds_t.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZDS-T on 2018/1/24.
 */

public class MyView extends View {

    private String string = "苟利国家生死以，岂因祸福避趋之";
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        baseDraw(canvas);
        textDraw(canvas);
    }

    private void baseDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setShadowLayer(50, 50, 50, Color.GREEN);

        canvas.drawRGB(255, 255, 255);
        canvas.drawCircle(140, 200, 100, paint);
        canvas.drawText("德玛西亚", 10, 10, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(140, 400, 100, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(140, 200, 100, paint);


        float[] pts = {10, 10, 50, 50, 100, 100, 200, 200, 250, 250, 300, 300, 200};
        canvas.drawLines(pts, paint);
        paint.setColor(Color.BLACK);
        canvas.drawLines(pts, 1, 12, paint);
        canvas.drawPoints(pts, paint);
        canvas.drawPoints(pts, 3, 4, paint);
    }

    private void textDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(45);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.addCircle(200, 200, 150, Path.Direction.CCW);
        canvas.drawPath(path, paint);

        paint.setColor(Color.GREEN);
        canvas.drawTextOnPath(string, path, 0, 0, paint);
//        canvas.drawTextOnPath(string, path, 80, 30, paint);
    }
}
