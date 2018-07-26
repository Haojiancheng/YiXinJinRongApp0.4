package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Wo_HUiKuanJIHua_gson;

import java.util.ArrayList;
import java.util.List;

public class Wo_HUiKuanJIHUa_adapter extends RecyclerView.Adapter<Wo_HUiKuanJIHUa_adapter.MyViewHolder> {
    private List<Wo_HUiKuanJIHua_gson.RepayListBean> list;


    public Wo_HUiKuanJIHUa_adapter(List<Wo_HUiKuanJIHua_gson.RepayListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Wo_HUiKuanJIHUa_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kuikuanjihua_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Wo_HUiKuanJIHUa_adapter.MyViewHolder holder, int position) {
        holder.qici.setText(list.get(position).getRepayPeriod());
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
        String format = df.format(Double.parseDouble(String.valueOf(list.get(position).getRecivedPrincipal())));
        holder.benjin.setText(format);
        String format1 = df.format(Double.parseDouble(String.valueOf( list.get(position).getRecivedInterest())));
        holder.lixi.setText(format1);
//        if (s<=3){
//            holder.riqi.setText("未放款");
//        }else {
            holder.riqi.setText(list.get(position).getRepayDate());
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView qici,benjin,lixi,riqi;
        public MyViewHolder(View itemView) {
            super(itemView);
            qici=itemView.findViewById(R.id.qici);
            benjin=itemView.findViewById(R.id.benjin);
            lixi=itemView.findViewById(R.id.lixi);
            riqi=itemView.findViewById(R.id.riqi);
        }
    }
}
