package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuGenZong_gson;

import java.util.ArrayList;
import java.util.List;

public class XiangMuGenZong_adapter extends RecyclerView.Adapter<XiangMuGenZong_adapter.MyViewHolder> {
    private List<XiangMuGenZong_gson.ResultBean.ImgListBean> list;
    private String imgurl;
    private Context context;

    public XiangMuGenZong_adapter(List<XiangMuGenZong_gson.ResultBean.ImgListBean> list, String imgurl, Context context) {
        this.list = list;
        this.imgurl = imgurl;
        this.context = context;
    }

    @NonNull
    @Override
    public XiangMuGenZong_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.xiangmugenzong_img_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XiangMuGenZong_adapter.MyViewHolder holder, int position) {
        holder.xmgz_tv.setText(list.get(position).getImgName());
        Glide.with(context).load(imgurl+list.get(position).getImgUrl()).into(holder.xmgz_iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView xmgz_iv;
        private TextView xmgz_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            xmgz_iv=itemView.findViewById(R.id.xmgz_iv);
            xmgz_tv=itemView.findViewById(R.id.xmgz_tv);
        }
    }
}
