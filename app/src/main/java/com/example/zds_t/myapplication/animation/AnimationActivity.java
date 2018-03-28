package com.example.zds_t.myapplication.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.zds_t.myapplication.R;


public class AnimationActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mImageView = (ImageView) findViewById(R.id.iamge);
    }

    public void doTranslate(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        mImageView.startAnimation(animation);
    }

    public void doScale(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        mImageView.startAnimation(animation);
        // 代码写法示例
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2.f, 0, 2.f);
//        scaleAnimation.setDuration(700);
//        scaleAnimation.setRepeatCount(5);
//        scaleAnimation.setRepeatMode(Animation.RESTART);
//        mImageView.startAnimation(scaleAnimation);
    }

    public void doAlpha(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        mImageView.startAnimation(animation);
    }

    public void doRotate(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        mImageView.startAnimation(animation);
    }

    public void doAnimSet(View view) {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_anim);
//        mImageView.startAnimation(animation);

        // 合并套路
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        AnimationSet set = new AnimationSet(true);
        set.setRepeatMode(Animation.REVERSE);   // 无法覆盖子animation的设置
        set.setRepeatCount(5);                  // 无法覆盖子animation的设置
        set.setDuration(3000);                  // 可以覆盖子animation的设置
        set.addAnimation(animation);
        set.addAnimation(animation2);
        mImageView.startAnimation(set);
    }
}
