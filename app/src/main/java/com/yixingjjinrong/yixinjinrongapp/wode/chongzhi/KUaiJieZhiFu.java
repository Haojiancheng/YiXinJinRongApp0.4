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

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.QianYue_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;
import okhttp3.MediaType;

public class KUaiJieZhiFu extends AutoLayoutActivity {
    private TextView zclb;
    private Button bt_sqqy;
    private EditText qy_phone;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String s;
    private String loginid;
    private String token;
    private ImageView kj_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_kuaijie_zhifu);
        getqyid();

        bt_sqqy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttp();
            }
        });
        zclb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(KUaiJieZhiFu.this,ZhiChiBanK.class);
                startActivity(it);
            }
        });
        kj_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getHttp() {
        s = qy_phone.getText().toString();
        Log.e("dsfs",""+ s);
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            js_request.put("bankReservedPhone", s);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            Log.e("TAG", "id" + user_id);
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
            Log.e("开通快捷支付",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/signCardApp.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("开通快捷支付Gson",response );
                        QianYue_gson data = new Gson().fromJson(response, QianYue_gson.class);
                        String html = data.getHtml();
                        Intent itcz = new Intent(KUaiJieZhiFu.this, QianYueOk.class);
                        itcz.putExtra("HTML", html);
                        Log.e("HTML!:",""+html.toString() );
                        startActivity(itcz);
                    }
                });
    }

    private void getqyid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(KUaiJieZhiFu.this, "Loginid", "");
        token = (String) SPUtils.get(KUaiJieZhiFu.this, "Token1", "");
        zclb=findViewById(R.id.zclb);
        bt_sqqy=findViewById(R.id.bt_sqqy);
        qy_phone=findViewById(R.id.qy_phone);
        kj_fh=findViewById(R.id.kj_fh);
    }
}
