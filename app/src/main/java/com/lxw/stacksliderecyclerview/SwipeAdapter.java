package com.lxw.stacksliderecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2017/04/18
 *     desc   :
 * </pre>
 */

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder>{
    private Context mContext;
    private List<SwipeCardBean> mDatas;

    public SwipeAdapter(Context context, List<SwipeCardBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_swipe_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       SwipeCardBean swipeCardBean=mDatas.get(position);
        holder.tvName.setText(swipeCardBean.getName());
        Picasso.with(mContext).load(swipeCardBean.getUrl()).into(holder.iv);
        holder.tvPager.setText(swipeCardBean.getPosition()+"/"+"8");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView iv;
        public  TextView tvPager;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvPager= (TextView) itemView.findViewById(R.id.tv_pager);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
