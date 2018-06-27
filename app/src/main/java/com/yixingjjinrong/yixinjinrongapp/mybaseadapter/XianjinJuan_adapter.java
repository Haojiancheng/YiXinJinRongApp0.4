package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import java.util.ArrayList;
import java.util.List;

public class XianjinJuan_adapter extends RecyclerView.Adapter<XianjinJuan_adapter.MyViewHolder> {
    private List<XianJinJuan_gson.QueryVouchersListBean> list=new ArrayList<>();

    public XianjinJuan_adapter(List<XianJinJuan_gson.QueryVouchersListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public XianjinJuan_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juan_keshi_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XianjinJuan_adapter.MyViewHolder holder, int position) {
        holder.qian.setText("¥"+list.get(position).getInfo());
        holder.yaoqu.setText(list.get(position).getRemark());
        holder.fanwei.setText(list.get(position).getUseRange());
        holder.time_out.setText(list.get(position).getEndTime());
        holder.shiyong_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了","jjjjjjbbbbbb");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
