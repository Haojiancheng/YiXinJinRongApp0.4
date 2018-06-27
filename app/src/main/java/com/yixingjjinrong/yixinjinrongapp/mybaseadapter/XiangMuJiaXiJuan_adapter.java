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
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiaXiJuan_Gson;

import java.util.ArrayList;
import java.util.List;


public class XiangMuJiaXiJuan_adapter extends RecyclerView.Adapter<XiangMuJiaXiJuan_adapter.MyViewHolder> {
    private List<JiaXiJuan_Gson.QueryVouchersListBean> mlist=new ArrayList<>();

    private OnEveryItemClickListener onEveryItemClickListener;
    public interface OnEveryItemClickListener{
        void onEveryClick(int position);
    }
    public XiangMuJiaXiJuan_adapter(List<JiaXiJuan_Gson.QueryVouchersListBean> mlist) {
        this.mlist = mlist;
    }
    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener){
        this.onEveryItemClickListener=onEveryItemClickListener;
    }



    @NonNull
    @Override
    public XiangMuJiaXiJuan_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juan_keshi_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XiangMuJiaXiJuan_adapter.MyViewHolder holder, final int position) {
        holder.qian.setText(mlist.get(position).getInfo() + "%");
        holder.yaoqu.setText(mlist.get(position).getRemark());
        holder.fanwei.setText(mlist.get(position).getUseRange());
        holder.time_out.setText(mlist.get(position).getEndTime());
        holder.shiyong_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了","jjjjjjbbbbbb");
                if (onEveryItemClickListener!=null){
                    onEveryItemClickListener.onEveryClick(position);
                }

            }
        });
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
