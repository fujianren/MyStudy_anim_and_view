package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/11.
 */

public class TextInputLayoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textinputlayout, container, false);
        return view;
    }
}
