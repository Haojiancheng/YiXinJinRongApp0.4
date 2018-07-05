package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.GG_GSON;

import java.util.ArrayList;
import java.util.List;

public class GG_adapter extends RecyclerView.Adapter<GG_adapter.MyViewHoldre> {
    private List<GG_GSON.ResultBean> list=new ArrayList<>();

    public GG_adapter(List<GG_GSON.ResultBean> list) {
        this.list = list;
    }
    private OnEveryItemClickListener onEveryItemClickListener;

    public interface OnEveryItemClickListener {
        void onEveryClick(int position);
    }

    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener) {
        this.onEveryItemClickListener = onEveryItemClickListener;
    }
    @NonNull
    @Override
    public GG_adapter.MyViewHoldre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_itme, parent, false);

        return new MyViewHoldre(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GG_adapter.MyViewHoldre holder, final int position) {
        if (list.get(position).getAdf() == 1) {
            holder.xx_title.setText(list.get(position).getArticle_title());
            holder.xx_time.setText(list.get(position).getArticle_pub_time());
        } else {
            holder.xx_title.setTextColor(Color.parseColor("#999999"));
            holder.xx_title.setText(list.get(position).getArticle_title());
            holder.xx_time.setText(list.get(position).getArticle_pub_time());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener != null) {
                    onEveryItemClickListener.onEveryClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoldre extends RecyclerView.ViewHolder {
        TextView xx_title, xx_time;
        public MyViewHoldre(View itemView) {
            super(itemView);
            xx_time = itemView.findViewById(R.id.xx_time);
            xx_title = itemView.findViewById(R.id.xx_title);
        }
    }
}
