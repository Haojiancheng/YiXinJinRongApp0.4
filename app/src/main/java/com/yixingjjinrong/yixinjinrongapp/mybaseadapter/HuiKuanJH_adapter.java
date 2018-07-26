package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.HuiKuanJiHua_Gson;

import java.util.ArrayList;
import java.util.List;

public class HuiKuanJH_adapter extends RecyclerView.Adapter<HuiKuanJH_adapter.MyViewHolder> {
    private List<HuiKuanJiHua_Gson.ResultBean.RepaymentListBean> hkjhlist=new ArrayList<>();
    private int s;

    public HuiKuanJH_adapter(List<HuiKuanJiHua_Gson.ResultBean.RepaymentListBean> hkjhlist, int s) {
        this.hkjhlist = hkjhlist;
        this.s = s;
    }

    @NonNull
    @Override
    public HuiKuanJH_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kuikuanjihua_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HuiKuanJH_adapter.MyViewHolder holder, int position) {
        holder.qici.setText(hkjhlist.get(position).getRepayPeriod());
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
        String format = df.format(Double.parseDouble(String.valueOf(hkjhlist.get(position).getStillPrincipal())));
        holder.benjin.setText(format);
        String format1 = df.format(Double.parseDouble(String.valueOf( hkjhlist.get(position).getStillInterest())));
        holder.lixi.setText(format1);
        if (s<=3){
            holder.riqi.setText("未放款");
        }else {
            holder.riqi.setText(hkjhlist.get(position).getRepayDate());
        }

    }

    @Override
    public int getItemCount() {
        return hkjhlist.size();
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
