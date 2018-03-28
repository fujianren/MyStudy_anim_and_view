package com.example.zds_t.myapplication.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.zds_t.myapplication.R;

public class InterpolatorActivity extends AppCompatActivity {

    private View mView;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        mView = findViewById(R.id.view);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_interpolator);
    }

    /* 加速减速 */
    public void doAccelerateDecelerate(View view){
        mAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 加速 */
    public void doAccelerate(View view){
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 后退蓄力 */
    public void doAnticipate(View view){
        mAnimation.setInterpolator(new AnticipateInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 起点后退蓄力，终点越界返回 */
    public void doAnticipateOvershoot(View view){
        mAnimation.setInterpolator(new AnticipateOvershootInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 弹起 */
    public void doBounce(View view){
        mAnimation.setInterpolator(new BounceInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 循环，一个正弦速率变化为一个循环 */
    public void doCycle(View view){
        mAnimation.setInterpolator(new CycleInterpolator(1));
        mView.startAnimation(mAnimation);
    }

    /* 减速 */
    public void doDecelerate(View view){
        mAnimation.setInterpolator(new DecelerateInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 匀速 */
    public void doLinear(View view){
        mAnimation.setInterpolator(new LinearInterpolator());
        mView.startAnimation(mAnimation);
    }

    /* 终点越界返回 */
    public void doOvershoot(View view){
        mAnimation.setInterpolator(new OvershootInterpolator());
        mView.startAnimation(mAnimation);
    }

}
