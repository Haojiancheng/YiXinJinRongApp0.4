package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;

import java.util.ArrayList;
import java.util.List;

public class ShouYe_MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> list;
    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;

    public ShouYe_MyBaseAdapter(Context context, List<ShouYe_Gson.ResultBean.BorrowListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        switch (type) {
            case 0:
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.shouyefirst_itme, null);
                    holder1=new ViewHolder1();
                    holder1.first_name = convertView.findViewById(R.id.first_name);
                    holder1.first_lv = convertView.findViewById(R.id.first_lv);
                    holder1.first_jia = convertView.findViewById(R.id.first_jia);
                    holder1.first_fujialv = convertView.findViewById(R.id.first_fujialv);
                    holder1.first_fujiabai = convertView.findViewById(R.id.first_fujiabai);
                    holder1.first_qixian = convertView.findViewById(R.id.first_qixian);
                    holder1.first_chujie = convertView.findViewById(R.id.first_chujie);
                    holder1.first_yue=convertView.findViewById(R.id.first_yue);
                    holder1.view_first=convertView.findViewById(R.id.view_first);

                    convertView.setTag(holder1);
                } else {
                    holder1 = (ViewHolder1) convertView.getTag();
                }

                break;
            case 1:
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.fangchanxiangmu_itme_jia, null);
                    holder2=new ViewHolder2();
                    holder2.xiangmu_name = convertView.findViewById(R.id.xiangmu_name);
                    holder2.xiangmu_code = convertView.findViewById(R.id.xiangmu_code);
                    holder2.years_lilv = convertView.findViewById(R.id.years_lilv);
                    holder2.jiahao = convertView.findViewById(R.id.jiahao);
                    holder2.fujia_lilv = convertView.findViewById(R.id.fujia_lilv);
                    holder2.fujia_jiahao = convertView.findViewById(R.id.fujia_jiahao);
                    holder2.qixian = convertView.findViewById(R.id.qixian);
                    holder2.chujie = convertView.findViewById(R.id.chujie);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }

                break;
        }
        switch (type) {
            case 0:

                holder1.first_name.setText(list.get(position).getBorrowTitle()+"   "+list.get(position).getBorrowCode());
                holder1.first_lv.setText((int) list.get(position).getSubsidies() + ".0");
                holder1.first_yue.setText(list.get(position).getAmount()+" 元");
                if (list.get(position).getSubsidiesRate() == 0) {
                    holder1.first_fujialv.setText("");
                    holder1.first_jia.setText("");
                    holder1.first_fujiabai.setText("");
                } else {
                    holder1.first_fujialv.setText(list.get(position).getSubsidiesRate() + "");
                    holder1.first_fujiabai.setText("%");
                    holder1.first_jia.setText("+");
                }
                holder1.first_qixian.setText(list.get(position).getDeadlineNew());

                holder1.view_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String xiangmu_id = list.get(position).getBorrowRandomId();
                    Log.e("TAG", "+.." + xiangmu_id);
                    Intent it = new Intent(context, XiangMuXiangQing.class);
                    String mortgageType = String.valueOf(list.get(position).getMortgageType()).toString();
                    it.putExtra("xiangmu_id", xiangmu_id);
                    it.putExtra("mortgageType", mortgageType);
                    context.startActivity(it);
                }
            });
                int borrowStatus = list.get(position).getBorrowStatus();
                switch (borrowStatus){
                    case 2://招标中（出借）
                        if (String.valueOf(list.get(position).getTimeFlag()).equals("1")){//预热
                            holder1.first_chujie.setText(list.get(position).getAbleTenderDate());
                            holder1.first_chujie.setTextColor(Color.parseColor("#fe6623"));
                            holder1.first_chujie.setBackgroundResource(R.drawable.bt_biankuang);

                        }else {//可出借
                            holder1.first_chujie.setText(list.get(position).getBorrowStatusStr());
                            holder1.first_chujie.setBackgroundResource(R.drawable.bt_shape);

                        }
                        break;
                    case 3://已满标
                        holder1.first_chujie.setText(list.get(position).getBorrowStatusStr());
                        holder1.first_chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder1.first_chujie.setBackgroundResource(R.drawable.bt_huise);

                        break;
                    case 4://回款中
                        holder1.first_chujie.setText(list.get(position).getBorrowStatusStr());
                        holder1.first_chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder1.first_chujie.setBackgroundResource(R.drawable.bt_huise);

                        break;
                    case 5://回款完成
                        holder1.first_chujie.setText(list.get(position).getBorrowStatusStr());
                        holder1.first_chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder1.first_chujie.setBackgroundResource(R.drawable.bt_shenhuise);

                        break;
                }
                holder1.first_chujie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String xiangmu_id = list.get(position).getBorrowRandomId();
                        Log.e("TAG", "+.." + xiangmu_id);
                        Intent it = new Intent(context, XiangMuXiangQing.class);
                        String mortgageType = String.valueOf(list.get(position).getMortgageType()).toString();
                        it.putExtra("xiangmu_id", xiangmu_id);
                        it.putExtra("mortgageType", mortgageType);
                        context.startActivity(it);
                    }
                });
                break;
            case 1:
                holder2.xiangmu_name.setText(list.get(position).getBorrowTitle());
                holder2.xiangmu_code.setText(list.get(position).getBorrowCode());
                holder2.years_lilv.setText((int) list.get(position).getSubsidies() + ".0");
                if (list.get(position).getSubsidiesRate() == 0) {
                    holder2.fujia_lilv.setText("");
                    holder2.fujia_jiahao.setText("");
                    holder2.jiahao.setText("");
                } else {
                    holder2.fujia_lilv.setText(list.get(position).getSubsidiesRate() + "");
                    holder2.fujia_jiahao.setText("%");
                    holder2.jiahao.setText("+");
                }
                int borrowStatus2 = list.get(position).getBorrowStatus();
                switch (borrowStatus2){
                    case 2://招标中（出借）
                        if (String.valueOf(list.get(position).getTimeFlag()).equals("1")){//预热
                            holder2.chujie.setText(list.get(position).getAbleTenderDate());
                            holder2.chujie.setTextColor(Color.parseColor("#fe6623"));
                            holder2.chujie.setBackgroundResource(R.drawable.bt_biankuang);

                        }else {//可出借
                            holder2.chujie.setText(list.get(position).getBorrowStatusStr());
                            holder2.chujie.setBackgroundResource(R.drawable.bt_shape);

                        }
                        break;
                    case 3://已满标
                        holder2.chujie.setText(list.get(position).getBorrowStatusStr());
                        holder2.chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder2.chujie.setBackgroundResource(R.drawable.bt_huise);

                        break;
                    case 4://回款中
                        holder2.chujie.setText(list.get(position).getBorrowStatusStr());
                        holder2.chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder2.chujie.setBackgroundResource(R.drawable.bt_huise);

                        break;
                    case 5://回款完成
                        holder2.chujie.setText(list.get(position).getBorrowStatusStr());
                        holder2.chujie.setTextColor(Color.parseColor("#ffffff"));
                        holder2.chujie.setBackgroundResource(R.drawable.bt_shenhuise);

                        break;
                }
                holder2.qixian.setText(list.get(position).getDeadlineNew());
                holder2.chujie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String xiangmu_id = list.get(position).getBorrowRandomId();
                        Log.e("TAG", "+.." + xiangmu_id);
                        Intent it = new Intent(context, XiangMuXiangQing.class);
                        String mortgageType =String.valueOf(list.get(position).getMortgageType()).toString() ;
                        it.putExtra("xiangmu_id", xiangmu_id);
                        it.putExtra("mortgageType", mortgageType);
                        context.startActivity(it);
                    }
                });
                break;
        }
        return convertView;
    }

    class ViewHolder1 {
        TextView first_name,first_lv,first_jia,first_fujialv,first_fujiabai,first_qixian,first_yue;
        Button first_chujie;
        View view_first;
        ImageView xiangmu_src;

    }

    class ViewHolder2 {
        TextView xiangmu_name, xiangmu_code, years_lilv, jiahao, fujia_lilv, fujia_jiahao, qixian;
        Button chujie;
        ImageView xiangmu_src;
    }
//    public class ViweHolder{
//        TextView xiangmu_name,xiangmu_code,years_lilv,jiahao,fujia_lilv,fujia_jiahao,qixian;
//        Button chujie;
//    }

    @Override
    public int getItemViewType(int position) {
        //得到某个条目的类型
        if (position == 0) {
            return TYPE_0;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public int getViewTypeCount() {
        //指定你要在这个当前这个ListView显示几种类型的条目
        return 2;
    }
}
