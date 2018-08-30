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

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinBean;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XianJinJuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class XiangMuXianjinJuan_adapter extends RecyclerView.Adapter<XiangMuXianjinJuan_adapter.MyViewHolder> {
    private List<XianJinBean.xianJuanBean> juanBeanList;
    private Context context;

    public XiangMuXianjinJuan_adapter(List<XianJinBean.xianJuanBean> juanBeanList, Context context) {
        this.juanBeanList = juanBeanList;
        this.context = context;
    }
    private OnEveryItemClickListener onEveryItemClickListener;
    public interface OnEveryItemClickListener{
        void onEveryClick(int position);
    }


    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener){
        this.onEveryItemClickListener=onEveryItemClickListener;
    }

    @NonNull
    @Override
    public XiangMuXianjinJuan_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juan_keshi_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final XiangMuXianjinJuan_adapter.MyViewHolder holder, final int position) {
        if (juanBeanList.get(position).getActivitype()==6) {
            holder.qian.setText("¥" + juanBeanList.get(position).getInfo());
            holder.yaoqu.setText(juanBeanList.get(position).getRemark());
            holder.fanwei.setText(juanBeanList.get(position).getUseRange());
            holder.time_out.setText("有效期至："+juanBeanList.get(position).getEndTime());
            holder.shiyong_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEveryItemClickListener!=null){
                        onEveryItemClickListener.onEveryClick(position);
                    }
                   
                    
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return juanBeanList.size();
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
