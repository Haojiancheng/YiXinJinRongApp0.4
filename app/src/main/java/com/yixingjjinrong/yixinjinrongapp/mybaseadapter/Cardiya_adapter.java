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
import com.yixingjjinrong.yixinjinrongapp.gsondata.CarDiYa_Gson;

import java.util.ArrayList;
import java.util.List;

public class Cardiya_adapter extends RecyclerView.Adapter<Cardiya_adapter.MyViewHolde> {
    private List<CarDiYa_Gson.InvestListBean> list=new ArrayList<>();
    private Context context;

    public Cardiya_adapter(List<CarDiYa_Gson.InvestListBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public Cardiya_adapter.MyViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wode_chujie_itme,parent,false);

        return new MyViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cardiya_adapter.MyViewHolde holder, final int position) {
        holder.xiangmu_code.setText(list.get(position).getBorrowCode());
        holder.qixian_chujie.setText(list.get(position).getDeadline());
        Glide.with(context).load(R.drawable.cheliang).into(holder.myima);
        holder.jinge_chujie.setText(String.valueOf(list.get(position).getInvestAmount()));
        holder.years_lilv_chujie.setText(String.valueOf(list.get(position).getAnnualRate()));
        if (list.get(position).getInterest()!=null){
            holder.fujia_lilv_chujie.setText(list.get(position).getInterest());
            holder.fujia_lilv_chujie.setMaxEms(3);
        }else {
            holder.jiahao_chujie.setVisibility(View.GONE);
            holder.fujia_baihao_chujie.setVisibility(View.GONE);
            holder.fujia_lilv_chujie.setVisibility(View.GONE);

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

    public class MyViewHolde extends RecyclerView.ViewHolder {
        private TextView xiangmu_code,years_lilv_chujie,jiahao_chujie,fujia_lilv_chujie,fujia_baihao_chujie,qixian_chujie,jinge_chujie;
        private ImageView myima;
        public MyViewHolde(View itemView) {
            super(itemView);
            xiangmu_code=itemView.findViewById(R.id.xiangmu_code);
            years_lilv_chujie=itemView.findViewById(R.id.years_lilv_chujie);
            jiahao_chujie=itemView.findViewById(R.id.jiahao_chujie);
            fujia_lilv_chujie=itemView.findViewById(R.id.fujia_lilv_chujie);
            fujia_baihao_chujie=itemView.findViewById(R.id.fujia_baihao_chujie);
            qixian_chujie=itemView.findViewById(R.id.qixian_chujie);
            jinge_chujie=itemView.findViewById(R.id.jinge_chujie);
            myima=itemView.findViewById(R.id.myima);
        }
    }
}
