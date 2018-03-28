package com.example.zds_t.myapplication.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zds_t.myapplication.R;

public class AnimatorSetActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        mView = findViewById(R.id.view);
    }

    public void doStart(View view){
//        loadAnimator();
//        loadAnimator2();
        loadAnimator3();
    }

    private void loadAnimator3() {
        AnimatorSet animator =
                (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
        animator.setTarget(mView);
        animator.start();
    }

    private void loadAnimator2() {
        ObjectAnimator animator =
//                (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
                (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_color);
        animator.setTarget(mView);
        animator.start();
    }

    private void loadAnimator() {
        ValueAnimator valueAnimator =
                (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int offset = (int) animation.getAnimatedValue();
                mView.layout(offset, offset, mView.getWidth() + offset, mView.getHeight() + offset);
            }
        });
        valueAnimator.start();
    }

    public void doCancel(View view){

    }
}
