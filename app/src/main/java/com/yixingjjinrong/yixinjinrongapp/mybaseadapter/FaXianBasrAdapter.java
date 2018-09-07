package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class
FaXianBasrAdapter extends RecyclerView.Adapter<FaXianBasrAdapter.MyViewHolder> {
    private List<FaXian_Data.ResultBean.GoodsListBean> list;
    private String paht;


    private OnEveryItemClickListener onEveryItemClickListener;
    public interface OnEveryItemClickListener{
        void onEveryClick(int position);
    }
    
  
    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener){
        this.onEveryItemClickListener=onEveryItemClickListener;
    }

    public FaXianBasrAdapter(List<FaXian_Data.ResultBean.GoodsListBean> list,String picpath) {
        this.list = list;
        this.paht=picpath;
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

            holder.quan.setVisibility(View.VISIBLE);
            holder.shiwu_iv.setVisibility(View.GONE);
            holder.quan_text.setText(list.get(position).getPrizeMark());
        }else if (list.get(position).getPicUrl().equals("代金券")){
            holder.quan.setVisibility(View.VISIBLE);
            holder.shiwu_iv.setVisibility(View.GONE);
            holder.quan_text.setText(list.get(position).getPrizeMark());
        }else {
            holder.quan.setVisibility(View.GONE);
            holder.shiwu_iv.setVisibility(View.VISIBLE);
            x.image().bind(holder.shiwu_iv,paht+list.get(position).getPicUrl());
            Log.e("faxian实物图",""+paht+list.get(position).getPicUrl() );
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
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView shiwu_iv,xuni_iv;
        private TextView shiwu_name,shiwu_money,xuni_name,xuni_money,quan_text;
        private View quan;

        public MyViewHolder(View itemView) {
            super(itemView);
            shiwu_iv= itemView.findViewById(R.id.im_shiwu);
            xuni_iv = itemView.findViewById(R.id.iv_xuni);
            shiwu_name=itemView.findViewById(R.id.tv_name_shiwu);
            shiwu_money=itemView.findViewById(R.id.tv_money_shiwu);
            xuni_name=itemView.findViewById(R.id.tv_xuni_name);
            xuni_money=itemView.findViewById(R.id.tv_xuni_money);
            quan=itemView.findViewById(R.id.quan);
            quan_text=itemView.findViewById(R.id.quan_text);
        }

    }
}