package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ShouYe_MyBaseAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    Context context;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> mylist;
    public ShouYe_MyBaseAdapter(Context context, List<ShouYe_Gson.ResultBean.BorrowListBean> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    private OnEveryItemClickListener onEveryItemClickListener;

    public interface OnEveryItemClickListener {
        void onEveryClick(int position);
    }


    public void setonEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener) {
        this.onEveryItemClickListener = onEveryItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.shouyefirst_itme, parent, false);
            // View view = View.inflate(ShowDataActivity.this, R.layout.item, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else {
            View view1 = LayoutInflater.from(context).inflate(R.layout.fangchanxiangmu_itme_jia, parent, false);
            //   View view1 = View.inflate(ShowDataActivity.this, R.layout.item2, null);
            MyViewHolder2 myViewHolder2 = new MyViewHolder2(view1);

            return myViewHolder2;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).first_name.setText(mylist.get(position).getBorrowTitle() );
            ((MyViewHolder) holder).first_lv.setText(String.valueOf(mylist.get(position).getSubsidies()));
            ((MyViewHolder) holder).first_yue.setText(mylist.get(position).getAmount() + " 元");
            if (mylist.get(position).getSubsidiesRate().equals("0.0")) {
                ((MyViewHolder) holder).first_fujialv.setText("");
                ((MyViewHolder) holder).first_jia.setText("");
                ((MyViewHolder) holder).first_fujiabai.setText("");
            } else {
                ((MyViewHolder) holder).first_fujialv.setText(mylist.get(position).getSubsidiesRate() + "");
                ((MyViewHolder) holder).first_fujiabai.setText("%");
                ((MyViewHolder) holder).first_jia.setText("+");
            }
            ((MyViewHolder) holder).first_qixian.setText(mylist.get(position).getDeadlineNew());
            int borrowStatus = mylist.get(position).getBorrowStatus();
            int type = mylist.get(position).getMortgageType();
            if (type == 1) {
                Glide.with(context).load(R.drawable.fangchandiya).into(((MyViewHolder) holder).xiangmu_src);
            } else {
                Glide.with(context).load(R.drawable.cheliang).into(((MyViewHolder) holder).xiangmu_src);
            }
            switch (borrowStatus) {
                case 2://招标中（出借）
                    if (String.valueOf(mylist.get(position).getTimeFlag()).equals("1")) {//预热
                        ((MyViewHolder) holder).first_chujie.setText(mylist.get(position).getAbleTenderDate());
                        ((MyViewHolder) holder).first_chujie.setTextColor(Color.parseColor("#fe6623"));
                        ((MyViewHolder) holder).first_chujie.setBackgroundResource(R.drawable.bt_biankuang);

                    } else {//可出借
                        ((MyViewHolder) holder).first_chujie.setText(mylist.get(position).getBorrowStatusStr());
                        ((MyViewHolder) holder).first_chujie.setBackgroundResource(R.drawable.bt_shape);

                    }
                    break;
                case 3://已满标
                    ((MyViewHolder) holder).first_chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder) holder).first_chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder) holder).first_chujie.setBackgroundResource(R.drawable.bt_huise);

                    break;
                case 4://回款中
                    ((MyViewHolder) holder).first_chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder) holder).first_chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder) holder).first_chujie.setBackgroundResource(R.drawable.bt_huise);

                    break;
                case 5://回款完成
                    ((MyViewHolder) holder).first_chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder) holder).first_chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder) holder).first_chujie.setBackgroundResource(R.drawable.bt_shenhuise);

                    break;
            }
            ((MyViewHolder) holder).first_chujie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEveryItemClickListener != null) {
                        onEveryItemClickListener.onEveryClick(position);
                    }
                }
            });
            ((MyViewHolder) holder).view_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEveryItemClickListener != null) {
                        onEveryItemClickListener.onEveryClick(position);
                    }
                }
            });


        }
        if (holder instanceof MyViewHolder2) {
            ((MyViewHolder2) holder).xiangmu_name.setText(mylist.get(position).getBorrowTitle());
            ((MyViewHolder2) holder).xiangmu_code.setText("");
            ((MyViewHolder2) holder).years_lilv.setText(mylist.get(position).getSubsidies());
            if (mylist.get(position).getSubsidiesRate().equals("0.0")) {
                ((MyViewHolder2) holder).fujia_lilv.setText("");
                ((MyViewHolder2) holder).fujia_jiahao.setText("");
                ((MyViewHolder2) holder).jiahao.setText("");
            } else {
                ((MyViewHolder2) holder).fujia_lilv.setText(mylist.get(position).getSubsidiesRate() + "");
                ((MyViewHolder2) holder).fujia_jiahao.setText("%");
                ((MyViewHolder2) holder).jiahao.setText("+");
            }
            int borrowStatus2 = mylist.get(position).getBorrowStatus();
            switch (borrowStatus2) {
                case 2://招标中（出借）
                    if (String.valueOf(mylist.get(position).getTimeFlag()).equals("1")) {//预热
                        ((MyViewHolder2) holder).chujie.setText(mylist.get(position).getAbleTenderDate());
                        ((MyViewHolder2) holder).chujie.setTextColor(Color.parseColor("#fe6623"));
                        ((MyViewHolder2) holder).chujie.setBackgroundResource(R.drawable.bt_biankuang);

                    } else {//可出借
                        ((MyViewHolder2) holder).chujie.setText(mylist.get(position).getBorrowStatusStr());
                        ((MyViewHolder2) holder).chujie.setBackgroundResource(R.drawable.bt_shape);

                    }
                    break;
                case 3://已满标
                    ((MyViewHolder2) holder).chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder2) holder).chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder2) holder).chujie.setBackgroundResource(R.drawable.bt_huise);

                    break;
                case 4://回款中
                    ((MyViewHolder2) holder).chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder2) holder).chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder2) holder).chujie.setBackgroundResource(R.drawable.bt_huise);

                    break;
                case 5://回款完成
                    ((MyViewHolder2) holder).chujie.setText(mylist.get(position).getBorrowStatusStr());
                    ((MyViewHolder2) holder).chujie.setTextColor(Color.parseColor("#ffffff"));
                    ((MyViewHolder2) holder).chujie.setBackgroundResource(R.drawable.bt_shenhuise);
                    break;
            }
            ((MyViewHolder2) holder).qixian.setText(mylist.get(position).getDeadlineNew());
            int type = mylist.get(position).getMortgageType();
            if (type == 1) {
                Glide.with(context).load(R.drawable.fangchandiya).into(((MyViewHolder2) holder).xiangmu_srctwo);
            } else {
                Glide.with(context).load(R.drawable.cheliang).into(((MyViewHolder2) holder).xiangmu_srctwo);
            }
            ((MyViewHolder2) holder).chujie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEveryItemClickListener != null) {
                        onEveryItemClickListener.onEveryClick(position);
                    }
                }
            });
            ((MyViewHolder2) holder).view_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEveryItemClickListener != null) {
                        onEveryItemClickListener.onEveryClick(position);
                    }
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if (mylist.size()==0){
            return mylist.size();
        }else if (mylist.size()!=0&&mylist.size()>=3){
            return 3; 
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView first_name, first_lv, first_jia, first_fujialv, first_fujiabai, first_qixian, first_yue;
        Button first_chujie;
        View view_first;
        ImageView xiangmu_src;

        public MyViewHolder(View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.first_name);
            first_lv = itemView.findViewById(R.id.first_lv);
            first_jia = itemView.findViewById(R.id.first_jia);
            first_fujialv = itemView.findViewById(R.id.first_fujialv);
            first_fujiabai = itemView.findViewById(R.id.first_fujiabai);
            first_qixian = itemView.findViewById(R.id.first_qixian);
            first_chujie = itemView.findViewById(R.id.first_chujie);
            first_yue = itemView.findViewById(R.id.first_yue);
            xiangmu_src = itemView.findViewById(R.id.first_src);
            view_first = itemView.findViewById(R.id.view_first);

        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView xiangmu_name, xiangmu_code, years_lilv, jiahao, fujia_lilv, fujia_jiahao, qixian;
        Button chujie;
        ImageView xiangmu_srctwo;
        View view_two;

        public MyViewHolder2(View itemView) {
            super(itemView);
            xiangmu_name = itemView.findViewById(R.id.xiangmu_name1);
            xiangmu_code = itemView.findViewById(R.id.xiangmu_code);
            years_lilv = itemView.findViewById(R.id.years_lilv);
            jiahao = itemView.findViewById(R.id.jiahao);
            fujia_lilv = itemView.findViewById(R.id.fujia_lilv);
            fujia_jiahao = itemView.findViewById(R.id.fujia_jiahao);
            qixian = itemView.findViewById(R.id.qixian);
            chujie = itemView.findViewById(R.id.chujie);
            xiangmu_srctwo = itemView.findViewById(R.id.xiangmu_src);
            view_two=itemView.findViewById(R.id.shouye_two);
        }
    }


}
