package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMu_Gson;

import java.util.ArrayList;
import java.util.List;

public class XiangMu_Adapter extends RecyclerView.Adapter<XiangMu_Adapter.MyViewHolder> {
    private List<XiangMu_Gson.ResultBean> list=new ArrayList<>();
    private Context context;

    public XiangMu_Adapter(List<XiangMu_Gson.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public XiangMu_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fangchanxiangmu_itme_jia,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XiangMu_Adapter.MyViewHolder holder, final int position) {
        holder.xiangmu_name.setText(list.get(position).getMortgageTypeStr());
        holder.xiangmu_code.setText(list.get(position).getBorrowCode());
        holder.years_lilv.setText(String.valueOf(list.get(position).getSubsidies()));
        holder.qixian.setText(list.get(position).getDeadlineNew());
       int type= list.get(position).getMortgageType();
       if (type==1){
           Glide.with(context).load(R.drawable.fangchandiya).into(holder.xiangmu_src);
       }else {
           Glide.with(context).load(R.drawable.cheliang).into(holder.xiangmu_src);
       }
//        holder.xiangmu_src.setImageDrawable();
        if (list.get(position).getSubsidiesRate()!=0){
            holder.fujia_lilv.setText(String.valueOf(list.get(position).getSubsidiesRate()));

        }else {
            holder.jiahao.setVisibility(View.GONE);
            holder.fujia_jiahao.setVisibility(View.GONE);
            holder.fujia_lilv.setVisibility(View.GONE);

        }
        int borrowStatus = list.get(position).getBorrowStatus();
        switch (borrowStatus){
            case 2://招标中（出借）
                if (String.valueOf(list.get(position).getTimeFlag()).equals("1")){//预热
                    holder.chujie.setText(list.get(position).getBorrowStatusStr());
                    holder.chujie.setTextColor(Color.parseColor("#fe6623"));
                    holder.chujie.setBackgroundResource(R.drawable.bt_biankuang);
                }else {//可出借
                    holder.chujie.setText(list.get(position).getBorrowStatusStr());
                    holder.chujie.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onEveryItemClickListener!=null){
                                onEveryItemClickListener.onEveryClick(position);
                            }
                        }
                    });
                }
                break;
            case 3://已满标
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.bt_huise);
                break;
            case 4://回款中
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.bt_huise);
                break;
            case 5://回款完成
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.bt_shenhuise);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView xiangmu_name,xiangmu_code,years_lilv,jiahao,fujia_lilv,fujia_jiahao,qixian;
        Button chujie;
        ImageView xiangmu_src;
        public MyViewHolder(View itemView) {
            super(itemView);
            xiangmu_name=itemView.findViewById(R.id.xiangmu_name);
            xiangmu_code=itemView.findViewById(R.id.xiangmu_code);
            years_lilv=itemView.findViewById(R.id.years_lilv);
            jiahao=itemView.findViewById(R.id.jiahao);
            fujia_lilv=itemView.findViewById(R.id.fujia_lilv);
            fujia_jiahao=itemView.findViewById(R.id.fujia_jiahao);
            qixian=itemView.findViewById(R.id.qixian);
            chujie=itemView.findViewById(R.id.chujie);
            xiangmu_src=itemView.findViewById(R.id.xiangmu_src);

        }
    }
}
