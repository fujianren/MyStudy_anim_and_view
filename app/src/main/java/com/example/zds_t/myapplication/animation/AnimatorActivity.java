package com.example.zds_t.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

public class AnimatorActivity extends AppCompatActivity {
    private static final String TAG = "AnimatorActivity";
    private TextView mView;
    private Button mBtn;
    private Button mBtn_cancel;
    private ValueAnimator mAnimator;
    private ValueAnimator mClone;
    private MyPointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mView = (TextView) findViewById(R.id.view);
        mPointView = (MyPointView) findViewById(R.id.pointView);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn_cancel = (Button) findViewById(R.id.btn_cancel);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doAnimation();
                mPointView.doPointAnim();
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimatorActivity.this, "clicked me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* view从（0,0）平移到（400， 400） */
    private ValueAnimator getAnimator(){
//        AnimatorSet animatorSet = new AnimatorSet();
//        mAnimator = ValueAnimator.ofInt(0, 400, 50, 300);
//        mAnimator = ValueAnimator.ofInt(0, 400, 200);
//        mAnimator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
        mAnimator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        mAnimator.setDuration(3000);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 实时调整view的位置
//                int curValue = (int) animation.getAnimatedValue();
//                mView.layout((int) mView.getX(), curValue, (int) mView.getX() + mView.getWidth(), curValue + mView.getHeight());
//                mView.setBackgroundColor(curValue);
                char text = (char) animation.getAnimatedValue();
                mView.setText(String.valueOf(text));
            }
        });
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart: =====start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: =====end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 动画被代码取消时触发，下一步会进入animationEnd
                Log.d(TAG, "onAnimationCancel: =====cancel");
                mAnimator.removeListener(this);
//                mAnimator.removeAllListeners();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat: =====repeat");
            }
        });
//        mAnimator.setInterpolator(new MyInterplator());
//        mAnimator.setEvaluator(new MyEvaluator());
//        mAnimator.setRepeatCount(ValueAnimator.INFINITE);

//        mAnimator.setEvaluator(new ArgbEvaluator());
        return mAnimator;
    }

    private void doAnimation() {
//        mClone = getAnimator().clone();
//        mClone.start();
        getAnimator().start();
    }

    public void doCancel(View view){
        if (mAnimator!= null){
//            mClone.cancel();
            mAnimator.cancel();
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // 自定义的动画插值器
    ///////////////////////////////////////////////////////////////////////////
    public class MyInterplator implements TimeInterpolator{

        /**
         *
         * @param input
         * @return
         */
        @Override
        public float getInterpolation(float input) {
            return 1 - input;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 自定义的动画类型求值器
    ///////////////////////////////////////////////////////////////////////////
    public class MyEvaluator implements TypeEvaluator<Integer>{

        /**
         *
         * @param fraction      当前动画时间点所对应的property区间百分比值，在0~1之间，由Interpolator计算出
         * @param startValue    property区间起始值
         * @param endValue      property区间结束值
         * @return              返回当前时间点的属性真实取值
         */
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            Log.d(TAG, "evaluate: ====" + fraction);
            int startInt = startValue;
            return (int)(200 + startInt + fraction * (endValue - startInt));
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 自定义的颜色求值器
    ///////////////////////////////////////////////////////////////////////////
    public class ArgbEvaluator implements TypeEvaluator<Integer>{

        /* 根据Interpolator决定fraction，一般与动画进度比取值相同 */
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            // 颜色取值32位二进制数 aaaa aaaa rrrr rrrr gggg gggg bbbb bbbb
            // 与运算0xff即 1111 1111，运算之后会得到的是被计算数的后8位数
            // 如startR的计算：
            // 右移16位: aaaa aaaa rrrr rrrr
            // 0xff:    0000 0000 1111 1111
            // 与运算结果0000 0000 rrrr rrrr,即rrrr rrrr
            int startInt = startValue;
            int startA = (startInt >> 24);  // 取32位数前8位
            int startR = (startInt >> 16) & 0xff;   // 同最大8位数b11111111的与运算，同1保留，其余皆为0，正好可以去掉前8位，保留后8位
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;
            // 通过或运算拼接在一起
            // aaaa aaaa 0000 0000 0000 0000 0000 0000 : 当前A右移24位
            // 0000 0000 rrrr rrrr 0000 0000 0000 0000 ：当前R右移16位
            // 0000 0000 0000 0000 gggg gggg 0000 0000 ：当前G右移8位
            // 0000 0000 0000 0000 0000 0000 bbbb bbbb ：当前B
            // aaaa aaaa rrrr rrrr gggg gggg bbbb bbbb ：或运算之后，得到新的32位数

            return (int)(startA + fraction * (endA - startA)) << 24 |
                    (int)(startR + fraction * (endR - startR)) << 16 |
                    (int)(startG + fraction * (endG - startG)) << 8 |
                    (int)(startB + fraction * (endB - startB));
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // CharEvaluator
    ///////////////////////////////////////////////////////////////////////////
    public class CharEvaluator implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = startValue;
            int endInt = endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            return (char) curInt;
        }
    }

}
