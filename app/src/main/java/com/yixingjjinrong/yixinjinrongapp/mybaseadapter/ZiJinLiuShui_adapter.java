package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FaXian_Data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZiJInLiuShu_gson;

import java.util.ArrayList;
import java.util.List;

public class ZiJinLiuShui_adapter extends RecyclerView.Adapter<ZiJinLiuShui_adapter.MyViewHolder> {
    private List<ZiJInLiuShu_gson.FundRecordlistBean> list=new ArrayList<>();

    public ZiJinLiuShui_adapter(List<ZiJInLiuShu_gson.FundRecordlistBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ZiJinLiuShui_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.zijinliushu_itme,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZiJinLiuShui_adapter.MyViewHolder holder, int position) {
       holder.liushutitle.setText(list.get(position).getFundMode());
       holder.liushuitime.setText(list.get(position).getRecordTime());
       holder.liushuimoney.setText(list.get(position).getTrans());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView liushutitle,liushuitime,liushuimoney;
        public MyViewHolder(View itemView) {
            super(itemView);
            liushutitle=itemView.findViewById(R.id.liushutitle);
            liushuitime=itemView.findViewById(R.id.liushuitime);
            liushuimoney=itemView.findViewById(R.id.liushuimoney);
        }
    }
}
