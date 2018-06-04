package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.IChuJieJiLu_Gson;

import java.util.ArrayList;
import java.util.List;

public class ChuJieJiLu_Adapter extends RecyclerView.Adapter<ChuJieJiLu_Adapter.MyViewHolder> {

    private List<IChuJieJiLu_Gson.ResultBean.InvestListBean> list=new ArrayList<>();

    public ChuJieJiLu_Adapter(List<IChuJieJiLu_Gson.ResultBean.InvestListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChuJieJiLu_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chujiejilu_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuJieJiLu_Adapter.MyViewHolder holder, int position) {
        holder.phone_jl.setText(list.get(position).getUsername());
        holder.money_jl.setText(list.get(position).getInvestAmount());
        holder.date_jl.setText(list.get(position).getInvestTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView phone_jl,money_jl,date_jl;
        public MyViewHolder(View itemView) {
            super(itemView);
            phone_jl=itemView.findViewById(R.id.phone_jl);
            money_jl=itemView.findViewById(R.id.money_jl);
            date_jl=itemView.findViewById(R.id.date_jl);
        }
    }
}
