package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/12.
 */

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private String prama1;
    private TextView mTvInfo;

    public static DetailFragment newInstance(String prama1){
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prama1", prama1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            prama1 = getArguments().getString("prama1");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mTvInfo = (TextView) view.findViewById(R.id.tvInfo);
        mTvInfo.setText(prama1 + "我是内容！！");
        return view;
    }
}
