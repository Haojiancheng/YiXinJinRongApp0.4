package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanxq_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DuiHuanxq_XUNi_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dui_huan_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();

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
            Log.e("兑换详情", "" + canshu);

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
                        Log.e("兑换详情roon", "" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("兑换详情gson", response);
                        if (prizeId.equals("")) {
                            data = new Gson().fromJson(response, DuiHuanxq_Gson.class);
                            getshuju();
                        } else {
                            data_xn = new Gson().fromJson(response, DuiHuanxq_XUNi_gson.class);
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
            dh_ok_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dh_num.setText("1");
                    getshuwu_duihuan_http();
                }
            });
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
        }
    }

    private void getshuwu_duihuan_http() {
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
            js_request.put("receiverId", data.getResult().getRecInformation().get(0).getReceiverId());
            js_request.put("changeNum", dh_num.getText().toString());//数量
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
            Log.e("兑换详情", "" + canshu);

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
                        Log.e("确认兑换roo",""+e );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("确认兑换Gson",""+response );
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
        dh_num=findViewById(R.id.dh_num);
    }
}
