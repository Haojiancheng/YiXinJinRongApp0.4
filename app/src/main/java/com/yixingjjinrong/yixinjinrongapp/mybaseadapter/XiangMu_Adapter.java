package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMu_Gson;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XiangMu_Adapter extends RecyclerView.Adapter<XiangMu_Adapter.MyViewHolder> {
    private List<XiangMu_Gson.ResultBean> list = new ArrayList<>();
    private Context context;

    public XiangMu_Adapter(List<XiangMu_Gson.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    ///**
    private OnEveryItemClickListener onEveryItemClickListener;

    public interface OnEveryItemClickListener {
        void onEveryClick(int position);
    }


    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener) {
        this.onEveryItemClickListener = onEveryItemClickListener;
    }
    ///**
    private OnEveryItemClickListener onEveryItemClickListener2;

    public interface OnEveryItemClickListener2 {
        void onEveryClick(int position);
    }

    public void setOnEveryItemClickListener2(OnEveryItemClickListener onEveryItemClickListener2) {
        this.onEveryItemClickListener2 = onEveryItemClickListener2;
    }

    ///**
    @NonNull
    @Override
    public XiangMu_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fangchanxiangmu_itme_jia, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final XiangMu_Adapter.MyViewHolder holder, final int position) {
        holder.xiangmu_name.setText(list.get(position).getBorrowTitle());
        holder.xiangmu_code.setText("");
        holder.years_lilv.setText(String.valueOf(list.get(position).getSubsidies()));
        holder.qixian.setText(list.get(position).getDeadlineNew());
        switch (list.get(position).getBorrowSafelevel()){
            case 1:
                holder.leve.setText("AAA");
                break;
            case 2:
                holder.leve.setText("AA");
                break;
            case 3:
                holder.leve.setText("A");
                break;
            case 4:
                holder.leve.setText("BBB");
                break;
            case 5:
                holder.leve.setText("BB");
                break;
            case 6:
                holder.leve.setText("B");
                break;
            case 7:
                holder.leve.setText("CCC");
                break;
            case 8:
                holder.leve.setText("CC");
                break;
            case 9:
                holder.leve.setText("C");
                break;
            default:
                break;
        }
        int type = list.get(position).getMortgageType();
        if (type == 1) {
            Glide.with(context).load(R.drawable.fangchandiya).into(holder.xiangmu_src);
        } else {
            Glide.with(context).load(R.drawable.cheliang).into(holder.xiangmu_src);
        }
//        holder.xiangmu_src.setImageDrawable();
        if (list.get(position).getSubsidiesRate() > 0) {
            holder.fujia_lilv.setText(String.valueOf(list.get(position).getSubsidiesRate()));

        } else {
            holder.jiahao.setVisibility(View.GONE);
            holder.fujia_jiahao.setVisibility(View.GONE);
            holder.fujia_lilv.setVisibility(View.GONE);

        }
        int borrowStatus = list.get(position).getBorrowStatus();
        holder.chujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener != null) {
                    onEveryItemClickListener.onEveryClick(position);
                }
            }
        });
        holder.leve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener2 != null) {
                    onEveryItemClickListener2.onEveryClick(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener != null) {
                    onEveryItemClickListener.onEveryClick(position);
                }
            }
        });
        switch (borrowStatus) {
            case 2://招标中（出借）
                if (String.valueOf(list.get(position).getTimeFlag()).equals("1")) {//预热
                    String ableTenderDate = list.get(position).getAbleTenderDate();
                    holder.chujie.setText(list.get(position).getAbleTenderDate());
                    holder.chujie.setTextColor(Color.parseColor("#fe6623"));
                    holder.chujie.setBackgroundResource(R.drawable.bt_biankuang);
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String date = sDateFormat.format(new java.util.Date());

                    MyLog.e("我的时间", "" + ableTenderDate);
                    MyLog.e("系统时间", "" + date);
//                    getTimeString(ableTenderDate, date);

                } else {//可出借
                    holder.chujie.setText(list.get(position).getBorrowStatusStr());
                    holder.chujie.setBackgroundResource(R.drawable.bt_shape);
                    holder.chujie.setTextColor(Color.parseColor("#ffffff"));

                }
                break;
            case 3://已满标
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.yimanbiao);

                break;
            case 4://回款中
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.bt_huise);

                break;
            case 5://回款完成
                holder.chujie.setText(list.get(position).getBorrowStatusStr());
                holder.chujie.setTextColor(Color.parseColor("#ffffff"));
                holder.chujie.setBackgroundResource(R.drawable.bt_shenhuise);
                break;
            default:
                break;
        }


    }

//    private String getTimeString(String endTime, String expendTime) {
//        //传入字串类型 end:2016/06/28 08:30 expend: 03:25
//        long longEnd = getTimeMillis(endTime);
//        String[] expendTimes = expendTime.split(":");   //截取出小时数和分钟数
//        long longExpend = Long.parseLong(expendTimes[0]) * 60 * 60 * 1000 + Long.parseLong(expendTimes[1]) * 60 * 1000;
//        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//        return sdfTime.format(new Date(longEnd - longExpend));
//    }

//    private long getTimeMillis(String strTime) {
//        long returnMillis = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        Date d = null;
//        try {
//            d = sdf.parse(strTime);
//            returnMillis = d.getTime();
//        } catch (ParseException e) {
//        }
//        return returnMillis;
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView xiangmu_name, xiangmu_code, years_lilv, jiahao, fujia_lilv, fujia_jiahao, qixian,leve;
        Button chujie;
        ImageView xiangmu_src;
        View shouye_two;


        public MyViewHolder(View itemView) {
            super(itemView);
            xiangmu_name = itemView.findViewById(R.id.xiangmu_name1);
            xiangmu_code = itemView.findViewById(R.id.xiangmu_code);
            years_lilv = itemView.findViewById(R.id.years_lilv);
            jiahao = itemView.findViewById(R.id.jiahao);
            fujia_lilv = itemView.findViewById(R.id.fujia_lilv);
            fujia_jiahao = itemView.findViewById(R.id.fujia_jiahao);
            qixian = itemView.findViewById(R.id.qixian);
            chujie = itemView.findViewById(R.id.chujie);
            xiangmu_src = itemView.findViewById(R.id.xiangmu_src);
            shouye_two = itemView.findViewById(R.id.shouye_two);
            leve=itemView.findViewById(R.id.leve);

        }
    }
}
