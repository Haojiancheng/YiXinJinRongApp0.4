package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;

import java.util.ArrayList;
import java.util.List;


public class JiaXiJuan_adapter extends RecyclerView.Adapter<JiaXiJuan_adapter.MyViewHolder> {
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();

    public JiaXiJuan_adapter(List<JiaXiJuan_Gson.QueryVouchersListBean> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public JiaXiJuan_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juan_keshi_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JiaXiJuan_adapter.MyViewHolder holder, int position) {
        holder.qian.setText(mlist.get(position).getInfo() + "%");
        holder.yaoqu.setText(mlist.get(position).getRemark());
        holder.fanwei.setText(mlist.get(position).getUseRange());
        holder.time_out.setText(mlist.get(position).getEndTime());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView qian, yaoqu, fanwei, time_out;
        private Button shiyong_bt;
        public MyViewHolder(View itemView) {
            super(itemView);
            qian = itemView.findViewById(R.id.qian);
            yaoqu = itemView.findViewById(R.id.yaoqu);
            fanwei = itemView.findViewById(R.id.fanwei);
            time_out = itemView.findViewById(R.id.time_out);
            shiyong_bt=itemView.findViewById(R.id.shiyong_bt);
        }
    }
}
