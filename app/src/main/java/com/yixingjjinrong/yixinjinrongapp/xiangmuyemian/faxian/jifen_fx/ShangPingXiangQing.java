package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Goods_xq;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XuNiShangPing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class ShangPingXiangQing extends AutoLayoutActivity {
    private ImageView dh_fh;
    private String awardType;
    private String speid;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private ImageView goods_xq_image;
    private TextView goods_xq_fen, goods_xq_ku, goods_xq_describe, goods_xq_name, m_kuc;
    private Button goods_xq_duihuanbt;
    private String prizeId;
    private Goods_xq data;
    private XuNiShangPing_Gson data_xuni;
    private View dh_da_quan;
    private TextView dh_quan_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_shang_ping_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        if (prizeId.equals("")){
            goods_xq_image.setVisibility(View.VISIBLE);
            dh_da_quan.setVisibility(View.GONE);
        }else {
            goods_xq_image.setVisibility(View.GONE);
            dh_da_quan.setVisibility(View.VISIBLE);
        }
        getonclock();
        gethttp();


    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("awardType", awardType);
            js_request.put("speId", speid);
            js_request.put("prizeId", prizeId);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("我的消息", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                //http://192.168.1.111:8080/yxb_mobile/
                .url(Urls.BASE_URL+"yxbApp/integralExchangeDetail.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("网络错误", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("商品详情gson", response);
                        if (prizeId.equals("")) {
                            data = new Gson().fromJson(response, Goods_xq.class);
                            if (data.getMessage().equals("用户未登录")) {
                                ToastUtils.showToast(ShangPingXiangQing.this, "qingdengru");
                            } else {
                                Glide.with(ShangPingXiangQing.this).load(data.getResult().getPath() + data.getResult().getGoodsList().get(0).getPicUrl()).into(goods_xq_image);
                                goods_xq_fen.setText(data.getResult().getGoodsList().get(0).getExchangeCredits() + "");
                                goods_xq_ku.setText(data.getResult().getGoodsList().get(0).getSurplusNum() + "");
                                goods_xq_describe.setText(data.getResult().getGoodsList().get(0).getDescription());
                                goods_xq_name.setText(data.getResult().getGoodsList().get(0).getPrizeName());
                                m_kuc.setVisibility(View.VISIBLE);
                                int myIntegral = Integer.parseInt(data.getResult().getMyIntegral());//个人积分
                                if (data.getResult().getGoodsList().get(0).getSurplusNum() < 1) {//如果库存小于1button不可点击
                                    goods_xq_duihuanbt.setEnabled(false);
                                    goods_xq_duihuanbt.setText("库存不足");
                                } else {
//                                goods_xq_duihuanbt.setEnabled(true);
//                                goods_xq_duihuanbt.setText("立即兑换");
                                    if (myIntegral < data.getResult().getGoodsList().get(0).getExchangeCredits()) { //如果用户积分小于所需积分button不可点击

                                        goods_xq_duihuanbt.setEnabled(false);
                                        goods_xq_duihuanbt.setText("积分不足");
                                    } else {
                                        goods_xq_duihuanbt.setEnabled(true);
                                        goods_xq_duihuanbt.setText("立即兑换");
                                        goods_xq_duihuanbt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent it=new Intent(ShangPingXiangQing.this,DuiHuanXiangQing.class);
                                                it.putExtra("awardType", awardType);
                                                it.putExtra("speid", speid);
                                                it.putExtra("prizeId", prizeId);
                                                startActivity(it);
                                            }
                                        });
                                    }
                                }
                            }

                        } else {

                            data_xuni = new Gson().fromJson(response, XuNiShangPing_Gson.class);
                            if (data_xuni.getMessage().equals("用户未登录")) {
                                ToastUtils.showToast(ShangPingXiangQing.this, "qingdengru");
                            } else {
                                dh_quan_text.setText(data_xuni.getResult().getVoucherList().get(0).getPrizeMark());
                                goods_xq_name.setText(data_xuni.getResult().getVoucherList().get(0).getPrizeName());
                                goods_xq_fen.setText(data_xuni.getResult().getVoucherList().get(0).getNeedPoint() + "");
                                goods_xq_describe.setText(data_xuni.getResult().getVoucherList().get(0).getPrizeMark());
                                m_kuc.setVisibility(View.GONE);
                                int myIntegral_xn = Integer.parseInt(data_xuni.getResult().getMyIntegral());//个人积分
                                if (myIntegral_xn < data_xuni.getResult().getVoucherList().get(0).getNeedPoint()) { //如果用户积分小于所需积分button不可点击
                                    goods_xq_duihuanbt.setEnabled(false);
                                    goods_xq_duihuanbt.setText("积分不足");
                                } else {
                                    goods_xq_duihuanbt.setEnabled(true);
                                    goods_xq_duihuanbt.setText("立即兑换");
                                    goods_xq_duihuanbt.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent it=new Intent(ShangPingXiangQing.this,DuiHuanXiangQing.class);
                                            it.putExtra("awardType", awardType);
                                            it.putExtra("speid", speid);
                                            it.putExtra("prizeId", prizeId);
                                            startActivity(it);
                                        }
                                    });
                                }

                            }
                        }
                    }
                });

    }

    private void getonclock() {

        dh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        user_id = (int) SPUtils.get(ShangPingXiangQing.this, "userId", 0);
        dh_fh = findViewById(R.id.dh_fh);
        Intent it = getIntent();
        awardType = it.getStringExtra("awardType");
        speid = it.getStringExtra("speid");
        prizeId = it.getStringExtra("prizeId");
        goods_xq_image = findViewById(R.id.goods_xq_image);
        //,,,;
        m_kuc = findViewById(R.id.m_kuc);
        goods_xq_fen = findViewById(R.id.goods_xq_fen);
        goods_xq_ku = findViewById(R.id.goods_xq_ku);
        goods_xq_describe = findViewById(R.id.goods_xq_describe);
        goods_xq_duihuanbt = findViewById(R.id.goods_xq_duihuanbt);
        goods_xq_name = findViewById(R.id.goods_xq_name);
        dh_da_quan=findViewById(R.id.dh_da_quan);
        dh_quan_text=findViewById(R.id.dh_quan_text);

    }
}
