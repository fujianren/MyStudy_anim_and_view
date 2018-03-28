package com.example.zds_t.myapplication.animation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zds_t.myapplication.R;

public class LayoutAnimation2Activity extends AppCompatActivity {

    private LinearLayout mContainer;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation2);

        mContainer = (LinearLayout) findViewById(R.id.container);

        addAnimate();
    }

    public void doAdd(View view){
        i++;
        Button button = new Button(this);
        button.setText("button" + i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        mContainer.addView(button, 0);
    }

    public void doRemove(View view){
        if (i > 0){
            mContainer.removeViewAt(0);
        }
        i--;
    }

    /* 添加或删除时动画 */
    private void addAnimate(){
        LayoutTransition transitioner = new LayoutTransition();

        Animator animator = ObjectAnimator.ofFloat(null, "rotationY", 0, 360, 0);
        transitioner.setAnimator(LayoutTransition.APPEARING, animator);

        // 出场动画
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        transitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);

        // 进场其他控件的动画
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 9f, 1f);
        PropertyValuesHolder left = PropertyValuesHolder.ofInt("left", 0, 100, 0);
        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", 1, 1);
        ValueAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mContainer, left, top, scaleX);
        transitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, animator1);

        // 出厂其他控件的动画
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4,
                frame5, frame6, frame7, frame8, frame9, frame10
        );
        PropertyValuesHolder outLeft = PropertyValuesHolder.ofInt("left",0,0);
        PropertyValuesHolder outTop = PropertyValuesHolder.ofInt("top",0,0);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(mContainer, rotation, outLeft, outTop);
        transitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, animator2);

        transitioner.setDuration(2000); // 设置动画时间
        mContainer.setLayoutTransition(transitioner);
    }
}
