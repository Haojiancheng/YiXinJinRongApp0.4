package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JieKuanZiLiao_Gson;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;

import org.xutils.x;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class JieKuanZiLiao_Adapter extends RecyclerView.Adapter<JieKuanZiLiao_Adapter.MyViewHolder> {
    private List<JieKuanZiLiao_Gson.ResultBean.QualificationListBean> list;
    private String urlpath;
    Context context;

    public JieKuanZiLiao_Adapter(List<JieKuanZiLiao_Gson.ResultBean.QualificationListBean> list, String urlpath, Context context) {
        this.list = list;
        this.urlpath = urlpath;
        this.context = context;
    }

    @NonNull
    @Override
    public JieKuanZiLiao_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jiekuan_review_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.jkzl_tv.setText(list.get(position).getImgName());
        Glide.with(context).load(urlpath+list.get(position).getImgUrl()).into(holder.jkzl_iv);
        MyLog.e("图片", ""+urlpath+list.get(position).getImgUrl());

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
