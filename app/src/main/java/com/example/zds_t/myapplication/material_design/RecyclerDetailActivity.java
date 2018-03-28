package com.example.zds_t.myapplication.material_design;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.zds_t.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDetailActivity extends AppCompatActivity {

    private RecyclerBean recyclerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_detail);
        getIntentData();

        ImageView imageView = (ImageView) findViewById(R.id.ivImage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        TabLayout slidingTabs = (TabLayout) findViewById(R.id.sliding_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_recycler_detail);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        collapsingToolbarLayout.setTitle(recyclerBean.getTitle());
        imageView.setBackgroundResource(recyclerBean.getImglarge());

        setupViewPager(viewPager);
        slidingTabs.addTab(slidingTabs.newTab().setText("测试"));
        slidingTabs.addTab(slidingTabs.newTab().setText("测试"));
        slidingTabs.addTab(slidingTabs.newTab().setText("测试"));
        slidingTabs.setupWithViewPager(viewPager);      // 联动viewPager
    }

    private void getIntentData() {
        recyclerBean = (RecyclerBean) getIntent().getSerializableExtra("main");
    }

    private void setupViewPager(ViewPager viewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试1");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试2");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试3");
        viewPager.setAdapter(adapter);
    }


    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
