package com.example.zds_t.myapplication.addressSelector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zds_t.myapplication.R;

import java.util.List;

/**
 * Created by ZDS-T on 2018/1/30.
 */

public class AddressAdapter extends BaseAdapter {

    private Context mContext;
    private List<AddressBean> mDataList;

    public AddressAdapter(Context context, List<AddressBean> dataList){

        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(position);

        return convertView;
    }

    public class ViewHolder{
        TextView tv;
        public ViewHolder(View convertView) {
            tv = (TextView) convertView.findViewById(R.id.text);
        }

        public void setData(int position) {
            AddressBean bean = mDataList.get(position);
            tv.setText(bean.getAddress());
        }
    }
}
