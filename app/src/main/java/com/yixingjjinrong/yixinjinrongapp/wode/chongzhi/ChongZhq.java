package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

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
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChongZhiOk_GSon;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Yinhangka_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ChongZhq extends AutoLayoutActivity {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView yh_name, yh_number, cz_keyong;
    private ImageView yh_img;
    private EditText cz_money;
    private String keyong;
    private int user_id;
    private Button cz_ok;
    private View yhcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhq);
        getczid();
        cz_keyong.setText("可用余额:  " + keyong + "元");
        getczHTTp();


    }

    private void getokHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("money", cz_money.getText().toString());

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

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/postOnlineCZFu.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ok充值：", result);
                ChongZhiOk_GSon data = new Gson().fromJson(result, ChongZhiOk_GSon.class);
                String html = data.getHtml();
                Intent itcz = new Intent(ChongZhq.this, ChongZhiOK.class);
                itcz.putExtra("HTML", html);
                Log.e("HTML!:",""+html.toString() );
                startActivity(itcz);
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

    private void getczHTTp() {
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

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/fuRechgeInitMobileApp.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("充值GSON:", "" + result);
                Yinhangka_Gson data = new Gson().fromJson(result, Yinhangka_Gson.class);
                String msg = data.getMsg();
                if (msg.equals("")) {
                    yh_name.setText(data.getBankName());
                    yh_number.setText(data.getCardNum());
                    x.image().bind(yh_img, Urls.BASE_URL + "yxb_mobile/" + data.getBankImage());
                    cz_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getokHTTp();
                        }
                    });
                } else {


                    yhcard.setVisibility(View.GONE);//影藏布局
                    if (msg.equals("auth")) {
                        Toast.makeText(ChongZhq.this, "没有实名认证", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    if (msg.equals("bank_link")) {
                        Toast.makeText(ChongZhq.this, "没有富友开户", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    if (msg.equals("sign_card")) {
                        Toast.makeText(ChongZhq.this, "没有签约", Toast.LENGTH_SHORT).show();
                        finish();
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

    private void getczid() {
        Intent itzc = getIntent();
        user_id = (int) SPUtils.get(this, "userId", 0);
        keyong = itzc.getStringExtra("keyong2");
        Log.e("ddddd+*9*+*", "" + keyong);
        yh_name = findViewById(R.id.yh_name);
        yh_number = findViewById(R.id.yh_number);
        cz_keyong = findViewById(R.id.cz_keyong);
        cz_money = findViewById(R.id.cz_money);
        yh_img = findViewById(R.id.yh_img);
        cz_ok = findViewById(R.id.cz_ok);
        yhcard = findViewById(R.id.yhcard);

    }

}
