package com.example.zds_t.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

public class ObjectAnimatorActivity extends AppCompatActivity {

    private TextView mView;
    private MyPointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        mView = (TextView) findViewById(R.id.view);
        mPointView = (MyPointView) findViewById(R.id.pointView);
    }

    public void doStart(View view){
        // 以当前控件所在中心点位置为动画原点
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "alpha", 1, 0, 1);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "rotation", 0, 180, 0);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "rotationX", 0, 180);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "rotationY", 0, 180, 0);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationY", 0, 180, 0);
//        ObjectAnimator animator = ObjectAnimator.ofInt(mPointView, "pointRadius", 0, 300, 200);
//        ObjectAnimator animator = ObjectAnimator.ofInt(mPointView, "pointRadius", 200);
//        ObjectAnimator animator = ObjectAnimator.ofInt(mView, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
//        animator.setEvaluator(new ArgbEvaluator()); // 背景色变化求值器
//        animator.setDuration(2000);
//        animator.start();
        getAnimator().start();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationY", -180, 0);
//        animator.setDuration(1000).start();
    }

    public void doCancel(View view){

    }

    private Animator getAnimator(){
        PropertyValuesHolder ratationHolder = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, 20f, -20f);
        PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mView, ratationHolder, colorHolder);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(3000);
        return animator;
    }

}
