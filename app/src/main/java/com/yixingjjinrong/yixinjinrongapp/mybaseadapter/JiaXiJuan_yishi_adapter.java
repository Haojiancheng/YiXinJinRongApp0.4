package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;

import java.util.ArrayList;
import java.util.List;


public class JiaXiJuan_yishi_adapter extends RecyclerView.Adapter<JiaXiJuan_yishi_adapter.MyViewHolder> {
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();

    public JiaXiJuan_yishi_adapter(List<JiaXiJuan_Gson.QueryVouchersListBean> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public JiaXiJuan_yishi_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juan_yishi_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JiaXiJuan_yishi_adapter.MyViewHolder holder, int position) {
        holder.qian.setText(mlist.get(position).getInfo() + "%");
        holder.yaoqu.setText(mlist.get(position).getRemark());
        holder.fanwei.setText(mlist.get(position).getUseRange());
        holder.time_out.setText("有效期至："+mlist.get(position).getEndTime());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView qian, yaoqu, fanwei, time_out;

        public MyViewHolder(View itemView) {
            super(itemView);
            qian = itemView.findViewById(R.id.yishi_qian);
            yaoqu = itemView.findViewById(R.id.yishi_yaoqu);
            fanwei = itemView.findViewById(R.id.yishi_fanwei);
            time_out = itemView.findViewById(R.id.yishi_time_out);

        }
    }
}
