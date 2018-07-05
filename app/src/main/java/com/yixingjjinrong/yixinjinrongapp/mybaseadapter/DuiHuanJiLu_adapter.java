package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanJiLu_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiFenJiLu_gson;

import java.util.ArrayList;
import java.util.List;

public class DuiHuanJiLu_adapter extends RecyclerView.Adapter<DuiHuanJiLu_adapter.MyViewHolder>{
    List<DuiHuanJiLu_gson.ResultBean.ListBean> list=new ArrayList<>();

    public DuiHuanJiLu_adapter(List<DuiHuanJiLu_gson.ResultBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DuiHuanJiLu_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jifenjilu_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuiHuanJiLu_adapter.MyViewHolder holder, int position) {
        holder.jilu_name.setText(list.get(position).getMemo());
        holder.jilu_time.setText(list.get(position).getCreateTime());
        holder.jilu_fen.setText("+ "+String.valueOf(list.get(position).getCut_score()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jilu_name,jilu_time,jilu_fen;
        public MyViewHolder(View itemView) {
            super(itemView);
            jilu_name=itemView.findViewById(R.id.jilu_name);
            jilu_time=itemView.findViewById(R.id.jilu_time);
            jilu_fen=itemView.findViewById(R.id.jilu_fen);
        }
    }
}
