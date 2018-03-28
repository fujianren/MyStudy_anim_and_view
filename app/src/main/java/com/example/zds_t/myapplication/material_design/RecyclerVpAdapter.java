package com.example.zds_t.myapplication.material_design;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ZDS-T on 2018/1/12.
 */

public class RecyclerVpAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list;
    FragmentManager fm;
    public RecyclerVpAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
