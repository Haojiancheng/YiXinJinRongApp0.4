package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FaXian_Data;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaXianBasrAdapter extends RecyclerView.Adapter<FaXianBasrAdapter.MyViewHolder> {
    private List<FaXian_Data.ResultBean.GoodsListBean> list=new ArrayList<>();
    private String picpath;


    private OnEveryItemClickListener onEveryItemClickListener;
    public interface OnEveryItemClickListener{
        void onEveryClick(int position);
    }
    
  
    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener){
        this.onEveryItemClickListener=onEveryItemClickListener;
    }

    public FaXianBasrAdapter(List<FaXian_Data.ResultBean.GoodsListBean> list,String picpath) {
        this.list = list;
        this.picpath=picpath;
    }

    @NonNull
    @Override
    public FaXianBasrAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mybaseadapter_shiwu_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaXianBasrAdapter.MyViewHolder holder, final int position) {
        if (list.get(position).getAwardType()==1){
            holder.shiwu_name.setText(list.get(position).getPrizeName());
        }else {
            holder.shiwu_name.setText(list.get(position).getPicUrl());
        }
        
       
        holder.shiwu_money.setText(list.get(position).getExchangeCredits()+"");
        if (list.get(position).getPicUrl().equals("加息券")){
            holder.shiwu_iv.setImageResource(R.drawable.jiaxiquan);
        }else if (list.get(position).getPicUrl().equals("代金券")){
            holder.shiwu_iv.setImageResource(R.drawable.xianjinquan);
        }else {
            x.image().bind(holder.shiwu_iv,picpath+list.get(position).getPicUrl());
        }
        holder.shiwu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener!=null){
                    onEveryItemClickListener.onEveryClick(position);
                }
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView shiwu_iv,xuni_iv;
        private TextView shiwu_name,shiwu_money,xuni_name,xuni_money;

        public MyViewHolder(View itemView) {
            super(itemView);
            shiwu_iv= itemView.findViewById(R.id.im_shiwu);
            xuni_iv = itemView.findViewById(R.id.iv_xuni);
            shiwu_name=itemView.findViewById(R.id.tv_name_shiwu);
            shiwu_money=itemView.findViewById(R.id.tv_money_shiwu);
            xuni_name=itemView.findViewById(R.id.tv_xuni_name);
            xuni_money=itemView.findViewById(R.id.tv_xuni_money);
        }

    }
}