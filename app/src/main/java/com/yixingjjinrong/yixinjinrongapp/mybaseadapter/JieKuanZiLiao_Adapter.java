package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JieKuanZiLiao_Gson;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class JieKuanZiLiao_Adapter extends RecyclerView.Adapter<JieKuanZiLiao_Adapter.MyViewHolder> {
    private List<JieKuanZiLiao_Gson.ResultBean.QualificationListBean> list=new ArrayList<>();
    private String urlpath;

    public JieKuanZiLiao_Adapter(List<JieKuanZiLiao_Gson.ResultBean.QualificationListBean> list, String urlpath) {
        this.list = list;
        this.urlpath = urlpath;
    }

    @NonNull
    @Override
    public JieKuanZiLiao_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jiekuan_review_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JieKuanZiLiao_Adapter.MyViewHolder holder, int position) {
        holder.jkzl_tv.setText(list.get(position).getImgName());
        x.image().bind(holder.jkzl_iv,urlpath+list.get(position).getImgUrl());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView jkzl_tv;
        private ImageView jkzl_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            jkzl_tv=itemView.findViewById(R.id.jkzl_tv);
            jkzl_iv=itemView.findViewById(R.id.jkzl_iv);
        }
    }
}
