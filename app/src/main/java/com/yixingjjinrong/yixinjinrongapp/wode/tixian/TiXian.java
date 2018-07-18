package com.yixingjjinrong.yixinjinrongapp.wode.tixian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.TiXian_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.KUaiJieZhiFu;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TiXian extends AutoLayoutActivity {
    private ImageView t_yh_img;
    private TextView t_yh_name,t_yh_number,t_cz_keyong;
    private EditText t_cz_money;
    private Button cz_ok;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private String keyong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian);
        getID();
        gethttp();
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/withdrawInitMobilefApp.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("提现GSon",result );
                TiXian_Gson data = new Gson().fromJson(result, TiXian_Gson.class);
                String msg = data.getMsg();
                if (msg.equals("")) {
                    t_yh_name.setText(data.getBankName());
                    t_yh_number.setText(data.getCardNum());
                    x.image().bind(t_yh_img, data.getImage());
                    cz_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//可以提现，下一步
                            getokHTTp();
                        }
                    });
                } else {


//                    t_yhcard.setVisibility(View.GONE);//影藏布局
                    if (msg.equals("auth")) {
                        Toast.makeText(TiXian.this, "没有实名认证", Toast.LENGTH_SHORT).show();

                    }
                    if (msg.equals("bank_link")) {
                        Toast.makeText(TiXian.this, "没有富友开户", Toast.LENGTH_SHORT).show();

                    }
                    if (msg.equals("sign_card")) {
                        Toast.makeText(TiXian.this, "没有签约", Toast.LENGTH_SHORT).show();
                        Intent it=new Intent(TiXian.this,KUaiJieZhiFu.class);
                        startActivity(it);

                    }


                }

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

    private void getokHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("money", t_cz_money.getText().toString());
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/addWithdrawInfoMobilef.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ok提现：", result);
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

    private void getID() {
        Intent itzc = getIntent();
        user_id = (int) SPUtils.get(this, "userId", 0);
        keyong = itzc.getStringExtra("keyong2");
        Log.e("充值----》", "" + keyong);
        t_yh_img=findViewById(R.id.t_yh_img);
        t_yh_name=findViewById(R.id.t_yh_name);
        t_yh_number=findViewById(R.id.t_yh_number);
        t_cz_keyong=findViewById(R.id.t_cz_keyong);
        t_cz_money=findViewById(R.id.t_cz_money);
        cz_ok=findViewById(R.id.cz_ok);

        t_cz_keyong.setText(keyong);
    }
}
