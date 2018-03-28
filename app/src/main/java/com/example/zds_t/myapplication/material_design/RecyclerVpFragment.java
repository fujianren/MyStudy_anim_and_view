package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/12.
 */

public class RecyclerVpFragment extends Fragment {

    public static RecyclerVpFragment newInstance(int param1){
        RecyclerVpFragment fragment = new RecyclerVpFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("imgpath", param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_vp, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_vp);
        Bundle bundle = getArguments();
        if (bundle != null){
            imageView.setBackgroundResource(bundle.getInt("imgpath"));
        }
        return view;
    }
}
