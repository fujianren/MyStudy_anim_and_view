package com.example.zds_t.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by ZDS-T on 2018/1/9.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<String> mDataList;
    private boolean isVertical = true; // 默认垂直滑动

    public HomeAdapter(Context context, List<String> dataList){

        mContext = context;
        mDataList = dataList;
    }

    public HomeAdapter(Context context, List<String> dataList, boolean isVertical){
        mContext = context;
        mDataList = dataList;
        this.isVertical = isVertical;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext)
                .inflate(isVertical ? R.layout.item_home : R.layout.item_home2,
                        parent, false);
        MyViewHolder holder = new MyViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDataList.get(position));
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        Random random = new Random();
        int height = random.nextInt(300) % (300 - 100) + 100;
        params.height = height;
        holder.tv.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
