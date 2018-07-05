package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;

import java.util.ArrayList;
import java.util.List;

public class ShouYe_MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> list=new ArrayList<>();

    public ShouYe_MyBaseAdapter(Context context, List<ShouYe_Gson.ResultBean.BorrowListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViweHolder holder=null;
        if (convertView==null){
            holder=new ViweHolder();
            convertView=View.inflate(context, R.layout.fangchanxiangmu_itme_jia,null);
            holder.xiangmu_name=convertView.findViewById(R.id.xiangmu_name);
            holder.xiangmu_code=convertView.findViewById(R.id.xiangmu_code);
            holder.years_lilv=convertView.findViewById(R.id.years_lilv);
            holder.jiahao=convertView.findViewById(R.id.jiahao);
            holder.fujia_lilv=convertView.findViewById(R.id.fujia_lilv);
            holder.fujia_jiahao=convertView.findViewById(R.id.fujia_jiahao);
            holder.qixian=convertView.findViewById(R.id.qixian);
            holder.chujie=convertView.findViewById(R.id.chujie);
            convertView.setTag(holder);
        }else {
            holder= (ViweHolder) convertView.getTag();
        }
        holder.xiangmu_name.setText(list.get(position).getBorrowTitle());
        holder.xiangmu_code.setText(list.get(position).getBorrowCode());
        holder.years_lilv.setText((int) list.get(position).getSubsidies()+"");
        if(list.get(position).getSubsidiesRate()==0){
            holder.fujia_lilv.setText("");
            holder.fujia_jiahao.setText("");
            holder.jiahao.setText("");
        }else {
            holder.fujia_lilv.setText(list.get(position).getSubsidiesRate()+"");
            holder.fujia_jiahao.setText("%");
            holder.jiahao.setText("+");
        }
        holder.qixian.setText(list.get(position).getDeadlineNew());
        holder.chujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xiangmu_id = list.get(position).getBorrowRandomId();
                Log.e("TAG","+.."+xiangmu_id);
                Intent it = new Intent(context, XiangMuXiangQing.class);
                String mortgageType = list.get(position).getMortgageType();
                it.putExtra("xiangmu_id", xiangmu_id);
                it.putExtra("mortgageType", mortgageType);
                context.startActivity(it);
            }
        });
        
        return convertView;
    }
    public class ViweHolder{
        TextView xiangmu_name,xiangmu_code,years_lilv,jiahao,fujia_lilv,fujia_jiahao,qixian;
        Button chujie;
    }
}
