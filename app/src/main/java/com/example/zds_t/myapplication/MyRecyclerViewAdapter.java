package com.example.zds_t.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ZDS-T on 2018/1/10.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<String> dataList ;
    private int random;
    private List<Integer>heightList = new ArrayList<Integer>();
    private MyOnItemClickListener myOnItemClcikListener;
    private MyOnLongClickListener myOnLongClickListener;

    public MyRecyclerViewAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
        //初始化高度
        if(dataList != null&&dataList.size() != 0){
            for (int i = 0; i < dataList.size(); i++) {
                random = new Random().nextInt(200)+100;//[100,300)
                heightList.add(random);
            }
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent,false);
        //将布局传入viewholder并返回给onBindViewHolder
        return new MyViewHolder(inflate);
    }

    /**
     * 该方法类似与listview中的getView方法,每次加载视图的时候都会调用,但是这里给了每个item随机的高度,因此当屏幕复用时,
     * 会不断复用不同的高度,当屏幕滑到顶部时,会因为高度不等产生大片空白,然后回到初始状态,因此,需要给每个item记录高度,
     * 将其存到集合,每次调用该item时,设置高度
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.text.setText(dataList.get(position));
        //设置高度
        int height = heightList.get(position);
        //得到控件的高度
        ViewGroup.LayoutParams layoutParams = holder.text.getLayoutParams();
        //设置高度
        layoutParams.height = height;
        //设置点击事件
        if(myOnItemClcikListener != null && !holder.text.hasOnClickListeners()){
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回调方法,,将view控件和索引传回,索引必须是布局在父布局中的索引,否则索引错乱最终报错
                    myOnItemClcikListener.myOnItemClickListener(holder.getLayoutPosition(),v);
                }
            });
        }
        if(myOnLongClickListener != null ){
            holder.text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    myOnLongClickListener.myOnLongClickListener(holder.getLayoutPosition(),v);
                    return true;
                }
            });
        }


    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void deleteData(int position) {
        //使用notifyItemInserted/notifyItemRemoved会有动画效果
        // 而使用notifyDataSetChanged()则没有
        heightList.remove(position);
        dataList.remove(position);
        notifyItemRemoved(position);
    }
    public void addData(int position,String str){
        dataList.add(position,str);
        int random = new Random().nextInt(200)+100;
        heightList.add(position,random);
        notifyItemInserted(position);
    }

    //注意:该类是公共的,不然适配器设置设置泛型失败
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
    //对外暴露的方法,当设置该方法时,会创建接口的实现类
    public void setOnItemCickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClcikListener = myOnItemClickListener;
    }
    public void setOnLongCickListener(MyOnLongClickListener myOnLongClickListener){
        this.myOnLongClickListener = myOnLongClickListener;
    }
    //自定义点击接口
    public interface MyOnItemClickListener{
        void myOnItemClickListener(int position,View view);
    }
    //自定义长按接口
    public interface MyOnLongClickListener{
        void myOnLongClickListener(int position,View view);
    }

}
