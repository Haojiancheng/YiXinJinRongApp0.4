package com.yixingjjinrong.yixinjinrongapp.wode.zongzichen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.MyView.DataItem;
import com.yixingjjinrong.yixinjinrongapp.MyView.DiscView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ZongE_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ZongziChan extends AutoLayoutActivity {
//    private DiscView rv;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private TextView zonge_z,zonge_daishou,zonge_keyong,zonge_dongjie;
    private String loginid;
    private String token;
    private ImageView ze_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_zongzi_chan);

        getzzcID();
        gethttp();
//        getquan();
        ze_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getquan() {
//        List<DataItem> items = new ArrayList<>();
//        items.add(new DataItem((int)zong_floor, "总代收", getResources().getColor(R.color.colorRed)));
//        items.add(new DataItem((int)ke_floor, "可用余额", getResources().getColor(R.color.colorPrimaryDark)));
//        items.add(new DataItem((int)dong_floor, "冻结金额", getResources().getColor(R.color.colorTransparent)));
//        rv.setItems(items);
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/userTotalAmount.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的总资产Gson", result);
                ZongE_Gson data = new Gson().fromJson(result, ZongE_Gson.class);
                String accountSum = data.getUserMap().getAccountSum();//总资产
                String forAmount = data.getUserMap().getForAmount();//总代收
                String usableAmount = data.getUserMap().getUsableAmount();//可用余额
                String freezeAmount = data.getUserMap().getFreezeAmount();//冻结金额
                zonge_z.setText(accountSum);
                zonge_daishou.setText(forAmount);
                zonge_keyong.setText(usableAmount);
                zonge_dongjie.setText(freezeAmount);
               float i=Float.parseFloat(forAmount);
               float u=Float.parseFloat(usableAmount);
               float y=Float.parseFloat(freezeAmount);
               float z=Float.parseFloat(accountSum);
//                //总代收%
//                zong = i/z*10;
//                zong_floor = Math.floor(zong);
//                //可用余额%
//                ke = u/z*10;
//                ke_floor = Math.floor(zong);
//                //冻结金额%
//                dong = y/z*10;



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }

        });
    }

    private void getzzcID() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(ZongziChan.this, "Loginid", "");
        token = (String) SPUtils.get(ZongziChan.this, "Token1", "");
        Log.e("loginid+token", "%%"+loginid+"^^"+token);
//        rv = findViewById(R.id.disc);
        zonge_z=findViewById(R.id.zonge_z);
        zonge_daishou=findViewById(R.id.zonge_daishou);
        zonge_keyong=findViewById(R.id.zonge_keyong);
        zonge_dongjie=findViewById(R.id.zonge_dongjie);
        ze_fh=findViewById(R.id.ze_fh);

    }
}
