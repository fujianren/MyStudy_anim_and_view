package com.example.zds_t.myapplication.material_design;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

public class DesignActivity extends AppCompatActivity implements BackHandledFragment.BackHandlerInterface{

    private TextView mTvToolTitle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private BackHandledFragment selectedFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvToolTitle = (TextView) findViewById(R.id.toolbar_title);

        mTvToolTitle.setText("测试");
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setShowHideAnimationEnabled(true);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        Toast.makeText(DesignActivity.this, "===", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 抽屉开关，实现toggle和drawer的联动
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);        // 返回键的旋转动画效果

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_item_main:
                        switchToMain();
                        break;
                    case R.id.navigation_item_tl:
                        switchToExample();
                        break;
                    case R.id.navigation_item_snackbar:
                        switchToBlog();
                        break;
                }
                item.setChecked(true);      // 选中的item高亮显示
                mDrawerLayout.closeDrawers();   // 关闭所有的抽屉
                return true;
            }
        });
    }

    private void switchToMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        mTvToolTitle.setText("主页");
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new TextInputLayoutFragment()).commit();
        mTvToolTitle.setText("MD输入框");
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SnackbarFragment()).commit();
        mTvToolTitle.setText("Snackbar");
    }


    private long exitTime = 0;
    public void doExitApp(){
        if ((System.currentTimeMillis() - exitTime) > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /* 重写，当手机后退键被点击的时候 */
    @Override
    public void onBackPressed() {
        if (selectedFragment == null || !selectedFragment.onBackPressed()){
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else doExitApp();
        }
    }

    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        // 每次指定的fragment被打开时，会触发该方法，我们就把获取到fragment设置为选中的fragment
        this.selectedFragment = backHandledFragment;
    }
}
