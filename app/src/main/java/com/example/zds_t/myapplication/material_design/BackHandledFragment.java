package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by ZDS-T on 2018/1/11.
 */

public abstract class BackHandledFragment extends Fragment {
    public interface BackHandlerInterface{
        // 标记fragment为选中
        void setSelectedFragment(BackHandledFragment backHandledFragment);
    }

    protected BackHandlerInterface mBackHandlerInterface;

    public abstract boolean onBackPressed();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandlerInterface)){
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            mBackHandlerInterface = ((BackHandlerInterface) getActivity());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 该fragment及其子类，只要被打开，就是选中的fragment
        mBackHandlerInterface.setSelectedFragment(this);
    }
}
