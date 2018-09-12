package com.yixingjjinrong.yixinjinrongapp.mybaseadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MoRenAddass_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyAddass_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShanChuDiZhi_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess.UpData_Addass;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class Myaddass_adapter extends RecyclerView.Adapter<Myaddass_adapter.MyViewHolder> {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private List<MyAddass_Gson.ResultBean.AddressListBean> list = new ArrayList<>();
    private Context context;
    private String id;
    private String message;
    private int user_id;

    public Myaddass_adapter(List<MyAddass_Gson.ResultBean.AddressListBean> list, Context context, int user_id) {
        this.list = list;
        this.context = context;
        this.user_id = user_id;
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
    public Myaddass_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myaddass_itme, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myaddass_adapter.MyViewHolder holder, final int position) {
        holder.addass_name.setText(list.get(position).getReceiverName());
        holder.addass_phone.setText(list.get(position).getReceiverPhone());
        holder.addass_addass.setText(list.get(position).getReceiverAddress() + list.get(position).getAddressDetail());
        id = list.get(position).getReceiverId();
        int isDefault = list.get(position).getIsDefault();
        if (isDefault == 1) {
            Glide.with(context).load(R.drawable.gouxuan).into(holder.yh2);
        }
        holder.moren_dizhi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
                try {
                    js_request.put("addressId", id);
                    js_request.put("userId", user_id);
                    base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + id);
                    sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
//            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject canshu = new JSONObject();
                try {
                    canshu.put("param", base1);
                    canshu.put("sign", sha1);
                    Log.e("TAG", ">>>>()base()加密11111!!--" + canshu);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkHttpUtils.postString()
                        .url(Urls.BASE_URL + "yxbApp/setDefault.do")
                        .content(canshu.toString())
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String result, int id) {
                                Log.e("设置为默认地址GSON:", result);
                                MoRenAddass_gson mrdata = new Gson().fromJson(result, MoRenAddass_gson.class);
                                String message = mrdata.getMessage();
                                if (message.equals("设置默认地址成功")) {
                                    Glide.with(context).load(R.drawable.gouxuan).into(holder.yh2);
                                    ToastUtils.showToast(context, "设置默认地址成功");
                                    notifyDataSetChanged();

                                } else {
                                    ToastUtils.showToast(context, "" + message );
                                }
                            }
                        });
            }
        });
        holder.adess_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener != null) {
                    onEveryItemClickListener.onEveryClick(position);
                }

            }
        });
        holder.bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, UpData_Addass.class);
                it.putExtra("addass_name", list.get(position).getReceiverName());
                it.putExtra("addass_phone", list.get(position).getReceiverPhone());
                it.putExtra("addass_addass", list.get(position).getReceiverAddress());
                it.putExtra("main_addass", list.get(position).getAddressDetail());
                it.putExtra("addass_id", id);
                context.startActivity(it);

            }
        });
        holder.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
                try {

                    js_request.put("addressId", id);
                    base1 = Base64JiaMI.AES_Encode(js_request.toString());
                    sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject canshu = new JSONObject();
                try {
                    canshu.put("param", base1);
                    canshu.put("sign", sha1);
                    Log.e("TAG", ">>>>()base()加密11111!!--" + canshu);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkHttpUtils.postString()
                        .url(Urls.BASE_URL + "yxbApp/deleteAddressInfo.do")
                        .content(canshu.toString())

                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String result, int id) {
                                Log.e("删除收货地址GSON:", result);
                                ShanChuDiZhi_gson data = new Gson().fromJson(result, ShanChuDiZhi_gson.class);
                                message = data.getMessage();
                                if (message.equals("删除地址成功")) {
                                    ToastUtils.showToast(context, "" + message);
                                    removeData(position);
                                } else {
                                    ToastUtils.showToast(context, "" + message);
                                }
                            }
                        });

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addass_name, addass_phone, addass_addass, bianji, shanchu;
        TextView moren_dizhi1;
        ImageView yh2;
        private View adess_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            addass_addass = itemView.findViewById(R.id.addass_addess);
            addass_name = itemView.findViewById(R.id.addass_name);
            addass_phone = itemView.findViewById(R.id.addass_phone);
            moren_dizhi1 = itemView.findViewById(R.id.moren_dizhi1);
            bianji = itemView.findViewById(R.id.g4);
            shanchu = itemView.findViewById(R.id.g6);
            yh2=itemView.findViewById(R.id.yh2);
            adess_view=itemView.findViewById(R.id.adess_view);
        }
    }
}
