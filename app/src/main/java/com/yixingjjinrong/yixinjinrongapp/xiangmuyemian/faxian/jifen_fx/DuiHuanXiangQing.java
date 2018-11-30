package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHUan_JieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanxq_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanxq_XUNi_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.xuandizhi.XuanZe_MyAddess;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class DuiHuanXiangQing extends AutoLayoutActivity {

    private int user_id;
    private String awardType;
    private String speid;
    private String prizeId;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView dh_title, dh_suoxu, dh_dangqiankc;
    private DuiHuanxq_Gson data;
    private DuiHuanxq_XUNi_gson data_xn;
    private View duihuanxq_kc, haveadassis, unhaveadassis;
    private TextView dh_sh_name, dh_sh_phone, dh_sh_adassse;
    private Button dh_ok_bt;
    private EditText dh_num;
    private ImageView wupin_jian, wupin_jia;
    private View querenduihuan_quan;
    private TextView querenduihuan_quan_text;
    private ImageView querenduihuan_shuwu;
    private View xuanzhe_adasse;
    private String receiverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dui_huan_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();//获得资源ID
        if (prizeId.equals("")) {
            wupin_jian.setEnabled(false);
            wupin_jia.setEnabled(false);
            //设置不可编辑
            dh_num.setFocusable(false);
            dh_num.setFocusableInTouchMode(false);
            querenduihuan_shuwu.setVisibility(View.VISIBLE);
            querenduihuan_quan.setVisibility(View.GONE);
        } else {
            querenduihuan_shuwu.setVisibility(View.GONE);
            querenduihuan_quan.setVisibility(View.VISIBLE);
            wupin_jian.setEnabled(true);
            wupin_jia.setEnabled(true);
            //设置可以编辑
            dh_num.setFocusableInTouchMode(true);
            dh_num.setFocusable(true);
            dh_num.requestFocus();
        }
        getnum();//获得兑换数量
        getclock();
        gethttp();
    }



    private void getnum() {
        if (Integer.valueOf(dh_num.getText().toString().trim()) == 0) {
            wupin_jian.setEnabled(false);

        } else {
            wupin_jian.setEnabled(true);
            wupin_jian.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (dh_num.getText().toString().equals("")) {
                        dh_num.setText("1");
                    } else {
                        if (Integer.valueOf(dh_num.getText().toString().trim()) <= 1) {//物品不能小于1
                            ToastUtils.showToast(DuiHuanXiangQing.this, "物品不能小于1个");
                        } else {
                            dh_num.setText(Integer.valueOf(dh_num.getText().toString()) - 1 + "");//减后的数量
                        }
                    }
                }
            });

            wupin_jia.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (dh_num.getText().toString().equals("")) {
                        dh_num.setText("2");
                    } else {
                        dh_num.setText(Integer.parseInt(dh_num.getText().toString().trim()) + 1 + "");//加后的数量
                    }
                }
            });

        }

    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("awardType", awardType);
            js_request.put("speId", speid);
            js_request.put("prizeId", prizeId);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("兑换详情", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/getExchangeDetail.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyLog.e("兑换详情roon", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("兑换详情gson", response);
                        if (prizeId.equals("")) {
                            data = new Gson().fromJson(response, DuiHuanxq_Gson.class);
                            String path = data.getResult().getPath();
                            String imageurl = path + data.getResult().getGoodsList().get(0).getPicUrl();
                            Glide.with(DuiHuanXiangQing.this).load(imageurl).into(querenduihuan_shuwu);
                            getshuju();
                        } else {
                            data_xn = new Gson().fromJson(response, DuiHuanxq_XUNi_gson.class);
                            querenduihuan_quan_text.setText(data_xn.getResult().getVoucherList().get(0).getPrizeMark());
                            getshujuxuni();
                        }
                    }
                });
    }


    private void getshuju() {
        duihuanxq_kc.setVisibility(View.VISIBLE);
        dh_title.setText(data.getResult().getGoodsList().get(0).getPrizeName());
        dh_dangqiankc.setText(data.getResult().getGoodsList().get(0).getSurplusNum() + "");//库存
        dh_suoxu.setText(data.getResult().getGoodsList().get(0).getExchangeCredits() + "");//所需积分
        if (data.getResult().getRecInformation().size() == 0) {
            haveadassis.setVisibility(View.GONE);
            unhaveadassis.setVisibility(View.VISIBLE);
        } else {
            haveadassis.setVisibility(View.VISIBLE);
            unhaveadassis.setVisibility(View.GONE);
            dh_sh_name.setText(data.getResult().getRecInformation().get(0).getReceiverName());
            dh_sh_phone.setText(data.getResult().getRecInformation().get(0).getReceiverPhone());
            dh_sh_adassse.setText("收货地址：" + data.getResult().getRecInformation().get(0).getReceiverAddress());
            receiverId = data.getResult().getRecInformation().get(0).getReceiverId();

        }


    }


    private void getshujuxuni() {
        duihuanxq_kc.setVisibility(View.GONE);
        dh_title.setText(data_xn.getResult().getVoucherList().get(0).getPrizeName());
//        dh_dangqiankc.setText(data_xn.getResult().getVoucherList().get(0).getSurplusNum());//库存
        dh_suoxu.setText(data_xn.getResult().getVoucherList().get(0).getNeedPoint() + "");//所需积分
        if (data_xn.getResult().getRecInformation().size() == 0) {
            haveadassis.setVisibility(View.GONE);
            unhaveadassis.setVisibility(View.VISIBLE);
        } else {
            haveadassis.setVisibility(View.VISIBLE);
            unhaveadassis.setVisibility(View.GONE);
            dh_sh_name.setText(data_xn.getResult().getRecInformation().get(0).getReceiverName());
            dh_sh_phone.setText(data_xn.getResult().getRecInformation().get(0).getReceiverPhone());
            dh_sh_adassse.setText("收货地址：" + data_xn.getResult().getRecInformation().get(0).getReceiverAddress());
            receiverId = data_xn.getResult().getRecInformation().get(0).getReceiverId();


        }
    }

    private void getXuni_duihuan_http() {//虚拟兑换
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            //{'speId':'实物奖品id','prizeName':'实物奖品名称',
            // 'receiverName':'收货人','receiverAddress':'收货人地址',
            // 'receiverPhone':'收货人手机号','userId':'用户id',
            // 'phone':'登陆人手机号','awardType':'奖品类型'}
            //
            js_request.put("userId", user_id);
            js_request.put("prizeId", prizeId);
            js_request.put("awardType", awardType);
            js_request.put("speId", "");
            js_request.put("receiverId", receiverId);
            js_request.put("changeNum", dh_num.getText().toString());//数量
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("兑换详情", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/vipIntegralExchange.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyLog.e("确认兑换roo", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("确认兑换Gson", "" + response);
                        DuiHUan_JieGuo_gson data = new Gson().fromJson(response, DuiHUan_JieGuo_gson.class);
                        ToastUtils.showToast(DuiHuanXiangQing.this, data.getMessage());
                    }
                });
    }

    private void getshuwu_duihuan_http() {//实物兑换
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            //{'speId':'实物奖品id','prizeName':'实物奖品名称',
            // 'receiverName':'收货人','receiverAddress':'收货人地址',
            // 'receiverPhone':'收货人手机号','userId':'用户id',
            // 'phone':'登陆人手机号','awardType':'奖品类型'}
            //
            js_request.put("userId", user_id);
            js_request.put("prizeId", prizeId);
            js_request.put("awardType", awardType);
            js_request.put("speId", data.getResult().getGoodsList().get(0).getSpeId());
            js_request.put("receiverId", receiverId);
            js_request.put("changeNum", "1");//数量
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>11111!!--" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("兑换详情", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/vipIntegralExchange.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyLog.e("确认兑换roo", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("确认兑换Gson", "" + response);
                        DuiHUan_JieGuo_gson data = new Gson().fromJson(response, DuiHUan_JieGuo_gson.class);
                        ToastUtils.showToast(DuiHuanXiangQing.this, data.getMessage());

                    }
                });

    }
    private void getclock() {
        xuanzhe_adasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DuiHuanXiangQing.this, XuanZe_MyAddess.class);
                startActivity(it);
            }
        });
        dh_ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (prizeId.equals("")) {
                    dh_num.setText("1");
                    getshuwu_duihuan_http();
                }
                getXuni_duihuan_http();
            }
        });

    }
    private void getid() {
        user_id = (int) SPUtils.get(DuiHuanXiangQing.this, "userId", 0);
        Intent it = getIntent();
        awardType = it.getStringExtra("awardType");
        speid = it.getStringExtra("speid");
        prizeId = it.getStringExtra("prizeId");
        dh_title = findViewById(R.id.dh_title);
        dh_dangqiankc = findViewById(R.id.dh_dangqiankc);
        dh_suoxu = findViewById(R.id.dh_suoxu);
        duihuanxq_kc = findViewById(R.id.duihuanxq_kc);
        haveadassis = findViewById(R.id.haveadassis);
        unhaveadassis = findViewById(R.id.unhaveadassis);
        dh_sh_adassse = findViewById(R.id.dh_sh_adassse);
        dh_sh_phone = findViewById(R.id.dh_sh_phone);
        dh_sh_name = findViewById(R.id.dh_sh_name);
        dh_ok_bt = findViewById(R.id.dh_ok_bt);
        dh_num = findViewById(R.id.dh_num);
        wupin_jian = findViewById(R.id.wupin_jian);
        wupin_jia = findViewById(R.id.wupin_jia);
        querenduihuan_quan = findViewById(R.id.querenduihuan_quan);
        querenduihuan_quan = findViewById(R.id.querenduihuan_quan);
        querenduihuan_shuwu = findViewById(R.id.querenduihuan_shuwu);
        querenduihuan_quan_text = findViewById(R.id.querenduihuan_quan_text);
        xuanzhe_adasse = findViewById(R.id.xuanzhe_adasse);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mAddress =(String)SPUtils.get(DuiHuanXiangQing.this, "mAddress", "");
        String receiverName = (String) SPUtils.get(DuiHuanXiangQing.this, "receiverName", "");
        String mreceiverId = (String) SPUtils.get(DuiHuanXiangQing.this, "receiverId", "");
        String receiverPhone = (String) SPUtils.get(DuiHuanXiangQing.this, "receiverPhone", "");
        if (mreceiverId!=null){
            haveadassis.setVisibility(View.VISIBLE);
            unhaveadassis.setVisibility(View.GONE);
            dh_sh_name.setText(receiverName);
            dh_sh_phone.setText(receiverPhone);
            dh_sh_adassse.setText("收货地址：" + mAddress);
             receiverId=mreceiverId;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}
