package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.QianYue_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class KUaiJieZhiFu extends AutoLayoutActivity {
    private TextView zclb;
    private Button bt_sqqy;
    private EditText qy_phone;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    private void getHttp() {
        s = qy_phone.getText().toString();
        Log.e("dsfs",""+ s);
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            js_request.put("bankReservedPhone", s);
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
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/signCardApp.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("开通快捷支付Gson",result );
                QianYue_gson data = new Gson().fromJson(result, QianYue_gson.class);
                String html = data.getHtml();
                Intent itcz = new Intent(KUaiJieZhiFu.this, QianYueOk.class);
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

    private void getqyid() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        zclb=findViewById(R.id.zclb);
        bt_sqqy=findViewById(R.id.bt_sqqy);
        qy_phone=findViewById(R.id.qy_phone);
    }
}
