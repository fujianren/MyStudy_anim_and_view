package com.example.zds_t.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

public class AnimatorSetDemoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AnimatorSetDemoActivity";
    private Button mBtn_menu;
    private Button mBtn_item1;
    private Button mBtn_item2;
    private Button mBtn_item3;
    private Button mBtn_item4;
    private Button mBtn_item5;
    private Button mBtn_item6;

    private boolean isMenuOpen = false;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set_demo);
        initView();
    }

    private void initView() {
        mView = findViewById(R.id.view);
        mBtn_menu = (Button) findViewById(R.id.menu);
        mBtn_item1 = (Button) findViewById(R.id.item1);
        mBtn_item2 = (Button) findViewById(R.id.item2);
        mBtn_item3 = (Button) findViewById(R.id.item3);
        mBtn_item4 = (Button) findViewById(R.id.item4);
        mBtn_item5 = (Button) findViewById(R.id.item5);
        mBtn_item6 = (Button) findViewById(R.id.item6);

        mBtn_menu.setOnClickListener(this);
        mBtn_item1.setOnClickListener(this);
        mBtn_item2.setOnClickListener(this);
        mBtn_item3.setOnClickListener(this);
        mBtn_item4.setOnClickListener(this);
        mBtn_item5.setOnClickListener(this);
//        mBtn_item6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtn_menu){
            if (!isMenuOpen){
                isMenuOpen = true;
                ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationY", 0, -300);
                animator.setDuration(1000).start();
                doAnimateOpen(mBtn_item1, 0, 5, 300);
                doAnimateOpen(mBtn_item2, 1, 5, 300);
                doAnimateOpen(mBtn_item3, 2, 5, 300);
                doAnimateOpen(mBtn_item4, 3, 5, 300);
                doAnimateOpen(mBtn_item5, 4, 5, 300);
            } else {
                isMenuOpen = false;
                ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationY", -300, 0);
                animator.setDuration(1000).start();
                doAnimateClose(mBtn_item1, 0, 5, 300);
                doAnimateClose(mBtn_item2, 1, 5, 300);
                doAnimateClose(mBtn_item3, 2, 5, 300);
                doAnimateClose(mBtn_item4, 3, 5, 300);
                doAnimateClose(mBtn_item5, 4, 5, 300);
            }
        } else {
            Toast.makeText(this, "点击了" + v, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭某个item的动画
     * @param view
     * @param index
     * @param total
     * @param radius
     */
    private void doAnimateClose(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI / 2 * index / (total - 1);
        int translationX = (int) (-radius * Math.sin(degree));
        int translationY = (int) (-radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, 0),
                ObjectAnimator.ofFloat(view, "translationY", -300, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),   // 缩小到0以后，存在bug
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        set.setDuration(1 * 500 ).start();
    }

    /**
     * 展开某个item的动画
     * @param btnItem   item所在的控件
     * @param index     item的索引
     * @param totalCount    所有item的个数
     * @param radius    菜单展开的扇形半径
     */
    private void doAnimateOpen(Button btnItem, int index, int totalCount, int radius) {
        if (btnItem.getVisibility() != View.VISIBLE){
            btnItem.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (totalCount - 1) * index;
        int translationX = (int) (-radius * Math.sin(degree));
        int translationY = (int) (-radius * Math.cos(degree));
        Log.d(TAG, "doAnimateClose: " + translationX + "==" + translationY);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(btnItem, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(btnItem, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(btnItem, "scaleX", 0, 1f),
                ObjectAnimator.ofFloat(btnItem, "scaleY", 0, 1f),
                ObjectAnimator.ofFloat(btnItem, "alpha", 0, 1f)
        );
        set.setDuration(1 * 500).start();
    }
}
