package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.YaoQingXiangQing_Gson;

import java.util.ArrayList;
import java.util.List;

public class Yaoqing_adapter extends RecyclerView.Adapter<Yaoqing_adapter.MyViewHolder> {
    private List<YaoQingXiangQing_Gson.QueryAwardListBean> list=new ArrayList<>();

    public Yaoqing_adapter(List<YaoQingXiangQing_Gson.QueryAwardListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Yaoqing_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myfirand, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Yaoqing_adapter.MyViewHolder holder, int position) {
        holder.firand_phone.setText(list.get(position).getCellphone());
        holder.firand_name.setText(list.get(position).getRealName());
        holder.firand_money.setText(""+list.get(position).getInvestAmount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView firand_phone,firand_name,firand_money;
        public MyViewHolder(View itemView) {
            super(itemView);
            firand_phone=itemView.findViewById(R.id.firand_phone);
            firand_name=itemView.findViewById(R.id.firand_name);
            firand_money=itemView.findViewById(R.id.firand_money);
        }
    }
}
