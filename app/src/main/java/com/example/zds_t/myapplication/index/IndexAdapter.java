package com.example.zds_t.myapplication.index;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

import java.util.List;

/**
 * Created by ZDS-T on 2018/1/23.
 * 适配标题及点击的Intent
 */

public class IndexAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mDataList;
    private final List<Class<?>> mClassList;

    public IndexAdapter(Context context, List<String> dataList, List<Class<?>> classList){

        mContext = context;
        mDataList = dataList;
        mClassList = classList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /* xml生成View，IO流操作，耗时 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){       // if创建，可以减少convertView的创建
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_index, null);
            holder = new ViewHolder(convertView);       // 把容器操作的东西保存到holder中
            convertView.setTag(holder);                 // 建立标签
        } else {
            holder = (ViewHolder) convertView.getTag(); // 直接通过标签取出holder，即该item要用到的所有控件对象
        }
        holder.setData(position);                       //
        return convertView;
    }

    public class ViewHolder{

        private final TextView mTv;
        /* holder中find，消除耗时的查找 */
        public ViewHolder(View view) {
            mTv = (TextView) view.findViewById(R.id.text);
        }

        public void setData(final int position) {
            mTv.setText((position + 1) +  "、" + mDataList.get(position));
            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, mClassList.get(position));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
