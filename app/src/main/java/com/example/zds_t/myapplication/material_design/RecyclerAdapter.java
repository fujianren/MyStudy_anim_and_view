package com.example.zds_t.myapplication.material_design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zds_t.myapplication.R;
import com.example.zds_t.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZDS-T on 2018/1/12.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<RecyclerBean> mList = new ArrayList<>();
    private static final int ANIMATED_ITEMS_COUNT = 4;
    private Context context;
    private boolean animateItems = false;   //
    private int lastAnimatedPosition = -1;  // 最近执行动画的item位置

    public RecyclerAdapter(Context context, List<RecyclerBean> list){
//        TypedValue typedValue = new TypedValue();
//        context.getTheme().resolveAttribute(R.attr.)
        mList = updateItems(list, true);
        this.context = context;
    }

    /**
     * 暴露的方法，更新数据重新适配
     * @param books
     * @param animated  是否执行动画
     * @return
     */
    private List<RecyclerBean> updateItems(List<RecyclerBean> books, boolean animated) {
        List<RecyclerBean> list = new ArrayList<>();
        animateItems = animated;
        lastAnimatedPosition = -1;
        list.addAll(books);
        notifyDataSetChanged();
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item2, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        RecyclerBean bean = mList.get(position);
        holder.ivMain.setBackgroundResource(bean.getImg());
    }

    private void runEnterAnimation(View itemView, int position) {
        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            itemView.setTranslationY(Utils.getScreenHeight(context));
            itemView.animate().translationY(0).setStartDelay(100 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700).start();
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivMain;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMain = (ImageView) itemView.findViewById(R.id.ivMain);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // item的点击实现
    ///////////////////////////////////////////////////////////////////////////
    public RecyclerItemClickListener.OnItemClickListener mOnItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            RecyclerBean main = getMain(position);
            Intent intent = new Intent(context, RecyclerDetailActivity.class);
            intent.putExtra("main", main);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (Activity) context,
                    view.findViewById(R.id.ivMain),
                    context.getString(R.string.transition_book_img));
            ActivityCompat.startActivity(context, intent, options.toBundle());
        }
    };

    private RecyclerBean getMain(int position) {
        return mList.get(position);
    }

}
