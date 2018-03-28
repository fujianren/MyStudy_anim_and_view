package com.example.zds_t.myapplication.material_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;

/**
 * Created by ZDS-T on 2018/1/11.
 */

public class SnackbarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snackbar, container, false);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.flb_black);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "提示文本", Snackbar.LENGTH_LONG)
                        .setAction("按钮", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "snackbar OK clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCallback(new Snackbar.Callback(){
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                switch (event){
                                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                        Toast.makeText(getContext(), "Snackbar通过Action的点击事件退出", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                                        Toast.makeText(getContext(), "Snackbar由于新的Snackbar显示而退出", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                                        // 代码中使用snackbar.dismiss()退出，会触发该逻辑
                                        Toast.makeText(getContext(), "Snackbar通过调用dismiss()方法退出", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                        // 该方法只要父控件是coordinateLayout时才会被触发
                                        Toast.makeText(getContext(), "Snackbar右划退出", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                        Toast.makeText(getContext(), "Snackbar自然退出", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }

                            @Override
                            public void onShown(Snackbar sb) {
                                super.onShown(sb);
                                Toast.makeText(getContext(), "Snackbar显示", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        return view;
    }
}
