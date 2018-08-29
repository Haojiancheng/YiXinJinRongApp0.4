package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FangChanDiYa_Gson;

import java.util.ArrayList;
import java.util.List;

public class Fangchandiya_adapter extends RecyclerView.Adapter<Fangchandiya_adapter.MyViewHolder> {
    private List<FangChanDiYa_Gson.InvestListBean> list=new ArrayList<>();

    public Fangchandiya_adapter(List<FangChanDiYa_Gson.InvestListBean> list) {
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
    public Fangchandiya_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wode_chujie_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Fangchandiya_adapter.MyViewHolder holder, final int position) {
        holder.xiangmu_code.setText(list.get(position).getBorrowCode());
        holder.qixian_chujie.setText(list.get(position).getDeadline());
        holder.xiangmu_name.setText(list.get(position).getBorrowTitle());
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView  xiangmu_name,xiangmu_code,years_lilv_chujie,jiahao_chujie,fujia_lilv_chujie,fujia_baihao_chujie,qixian_chujie,jinge_chujie;
        public MyViewHolder(View itemView) {
            super(itemView);
            xiangmu_name=itemView.findViewById(R.id.xiangmu_name);
            xiangmu_code=itemView.findViewById(R.id.xiangmu_code);
            years_lilv_chujie=itemView.findViewById(R.id.years_lilv_chujie);
            jiahao_chujie=itemView.findViewById(R.id.jiahao_chujie);
            fujia_lilv_chujie=itemView.findViewById(R.id.fujia_lilv_chujie);
            fujia_baihao_chujie=itemView.findViewById(R.id.fujia_baihao_chujie);
            qixian_chujie=itemView.findViewById(R.id.qixian_chujie);
            jinge_chujie=itemView.findViewById(R.id.jinge_chujie);

        }
    }
}
