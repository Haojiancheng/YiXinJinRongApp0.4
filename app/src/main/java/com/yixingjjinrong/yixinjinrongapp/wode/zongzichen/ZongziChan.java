package com.yixingjjinrong.yixinjinrongapp.wode.zongzichen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MyView.DataItem;
import com.yixingjjinrong.yixinjinrongapp.MyView.DiscView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZongE_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class ZongziChan extends AutoLayoutActivity {
    private DiscView rv;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private TextView zonge_z, zonge_daishou, zonge_keyong, zonge_dongjie;
    private String loginid;
    private String token;
    private ImageView ze_fh;
    private TextView hose_daishou, car_daishou;
    private double zong_floor;
    private double ke_floor;
    private double dong_floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zongzi_chan);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();


        getzzcID();
        getquan();


        gethttp();
        ze_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("我的加密：", ":" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/userTotalAmount.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("我的总资产Gson", response);
                        ZongE_Gson data = new Gson().fromJson(response, ZongE_Gson.class);
                        if (data.getMessage().equals("成功了")) {
                            String accountSum = data.getUserMap().getAccountSum();//总资产
                            String forAmount = data.getUserMap().getForAmount();//总代收
                            String usableAmount = data.getUserMap().getUsableAmount();//可用余额
                            String freezeAmount = data.getUserMap().getFreezeAmount();//冻结金额

                            zonge_z.setText(accountSum);
                            zonge_daishou.setText(forAmount);
                            zonge_keyong.setText(usableAmount);
                            zonge_dongjie.setText(freezeAmount);
                            for (int i = 0; i < data.getListType().size(); i++) {
                                int type = data.getListType().get(i).getMortgageType();
                                if (type == 1) {
                                    hose_daishou.setText(data.getListType().get(i).getForPaySum());
                                } else if (type == 4) {
                                    car_daishou.setText(data.getListType().get(i).getForPaySum());
                                }

                                Log.e("gcfr", "" + data.getListType().get(i).getMortgageType());
                            }

//                zonge_dongjie.setText(data.getUserMap().);
//                car_daishou.setText(data.getUserMap());

                        } else {
                            ToastUtils.showToast(ZongziChan.this, data.getMessage());
                        }
                    }
                });


    }

    private void getquan() {
        String forAmount = (String)SPUtils.get(this, "forAmount", "");//总代收
        String usableAmount = (String)SPUtils.get(this, "usableAmount", "");//可用余额
        String freezeAmount =(String) SPUtils.get(this, "freezeAmount", "");//冻结金额
        String accountSum =(String) SPUtils.get(this, "accountSum", "");//总额
        if (accountSum.equals("0.00")) {
            List<DataItem> items = new ArrayList<>();
            Log.e("<可用余额", "" + ke_floor);
            Log.e("<冻结金额", "" + dong_floor);
            Log.e("<总代收", "" + zong_floor);
            items.add(new DataItem((int) 4, "", getResources().getColor(R.color.seaShell)));
            items.add(new DataItem((int) 1, "", getResources().getColor(R.color.seaShell)));
            items.add(new DataItem((int) 5, "", getResources().getColor(R.color.seaShell)));
            rv.setItems(items);
        }else {
            float i = Float.parseFloat(forAmount);
            float u = Float.parseFloat(usableAmount);
            float y = Float.parseFloat(freezeAmount);
            float z = Float.parseFloat(accountSum);
            //总代收%

            //可用余额%
            double ke = u / z * 10;
            ke_floor = Math.floor(ke);
            //冻结金额%
            double dong = y / z * 10;
            dong_floor = Math.floor(dong);
            double zong = i / z * 10;
            zong_floor = 10.0 - dong_floor - ke_floor;
            Log.e("可用余额", "" + ke_floor);
            Log.e("冻结金额", "" + dong_floor);
            Log.e("总代收", "" + zong_floor + "代收:" + Math.floor(zong));

            List<DataItem> items = new ArrayList<>();
            Log.e("<可用余额", "" + ke_floor);
            Log.e("<冻结金额", "" + dong_floor);
            Log.e("<总代收", "" + zong_floor);
            items.add(new DataItem((int) zong_floor, "", getResources().getColor(R.color.slateblue)));
            items.add(new DataItem((int) ke_floor, "", getResources().getColor(R.color.chocolate)));
            items.add(new DataItem((int) dong_floor, "", getResources().getColor(R.color.palegreen)));
            rv.setItems(items);
        }
    }

    private void getzzcID() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(ZongziChan.this, "Loginid", "");
        token = (String) SPUtils.get(ZongziChan.this, "Token1", "");
        Log.e("loginid+token", "%%" + loginid + "^^" + token);
        rv = findViewById(R.id.disc);
        zonge_z = findViewById(R.id.zonge_z);
        zonge_daishou = findViewById(R.id.zonge_daishou);
        zonge_keyong = findViewById(R.id.zonge_keyong);
        zonge_dongjie = findViewById(R.id.zonge_dongjie);
        ze_fh = findViewById(R.id.ze_fh);
        hose_daishou = findViewById(R.id.hose_daishou);
        car_daishou = findViewById(R.id.cae_daishou);


    }
}
