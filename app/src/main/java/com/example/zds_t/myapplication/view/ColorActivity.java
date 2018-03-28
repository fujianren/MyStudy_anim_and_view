package com.example.zds_t.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.zds_t.myapplication.R;

public class ColorActivity extends AppCompatActivity {

    private Bitmap mOrgBitmap;      // 原图
    private Bitmap mTempBitmap;     // 与原图同样大小的位图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new ColorView(this));
        setContentView(R.layout.activity_color);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        mOrgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_recyclerview_08);
        mTempBitmap = Bitmap.createBitmap(mOrgBitmap.getWidth(), mOrgBitmap.getHeight(), Bitmap.Config.ARGB_8888);

//        seekBar.setMax(20);   // 色彩饱和度进度
//        seekBar.setProgress(1);
        seekBar.setMax(360);    // 色轴旋转进度
        seekBar.setProgress(180);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Bitmap bitmap = handleColorMatrixBmp(progress);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /* 根据进度，返回一个对应矩阵变换过的bitmap */
    private Bitmap handleColorMatrixBmp(int progress) {
        Canvas canvas = new Canvas(mTempBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        ColorMatrix colorMatrix = new ColorMatrix();

//        colorMatrix.setSaturation(progress);    // 饱和度
        colorMatrix.setScale(1, 1.3f, 1, 1);    // 色彩缩放
        colorMatrix.setRotate(0 , progress - 180);  // 红色旋转

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        new LightingColorFilter()

        canvas.drawBitmap(mOrgBitmap, 0, 0, paint);
        return mTempBitmap;
    }
}
