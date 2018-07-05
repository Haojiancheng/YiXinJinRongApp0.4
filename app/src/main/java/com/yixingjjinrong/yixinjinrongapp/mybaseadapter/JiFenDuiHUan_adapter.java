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
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiFenDuiHuan_Gson;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class JiFenDuiHUan_adapter extends RecyclerView.Adapter<JiFenDuiHUan_adapter.MyViewHolder> {
    private List<JiFenDuiHuan_Gson.ResultBean.GoodsListBean> list=new ArrayList<>();
    private String picpath;

    public JiFenDuiHUan_adapter(List<JiFenDuiHuan_Gson.ResultBean.GoodsListBean> list, String picpath) {
        this.list = list;
        this.picpath = picpath;
    }

    private OnEveryItemClickListener onEveryItemClickListener;
    public interface OnEveryItemClickListener{
        void onEveryClick(int position);
    }


    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener){
        this.onEveryItemClickListener=onEveryItemClickListener;
    }

    @NonNull
    @Override
    public JiFenDuiHUan_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mybaseadapter_xuni_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JiFenDuiHUan_adapter.MyViewHolder holder, final int position) {
        if (list.get(position).getAwardType()==1){
            holder.xuni_name.setText(list.get(position).getPrizeName());
        }else {
            holder.xuni_name.setText(list.get(position).getPicUrl());
        }


        holder.xuni_money.setText(list.get(position).getExchangeCredits()+"");
        if (list.get(position).getPicUrl().equals("加息券")){
            holder.xuni_iv.setImageResource(R.drawable.jiaxiquan);
        }else if (list.get(position).getPicUrl().equals("代金券")){
            holder.xuni_iv.setImageResource(R.drawable.xianjinquan);
        }else {
            x.image().bind(holder.xuni_iv,picpath+list.get(position).getPicUrl());
        }
        holder.xuni_iv.setOnClickListener(new View.OnClickListener() {
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
        private ImageView xuni_iv;
        private TextView xuni_name,xuni_money;
        public MyViewHolder(View itemView) {
            super(itemView);

            xuni_iv = itemView.findViewById(R.id.iv_xuni);
            xuni_name=itemView.findViewById(R.id.tv_xuni_name);
            xuni_money=itemView.findViewById(R.id.tv_xuni_money);
        }
    }
}
