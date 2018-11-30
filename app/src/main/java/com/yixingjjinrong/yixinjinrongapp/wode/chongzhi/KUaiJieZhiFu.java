package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.QianYue_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) { //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));  //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_kuaijie_zhifu);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getqyid();

        MyLog.e("dsfs",""+ s);
        bt_sqqy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = qy_phone.getText().toString();
                MyLog.e("dsfs",""+ s);
                if (s.equals("")) {
                    ToastUtils.showToast(KUaiJieZhiFu.this, "手机号不能为空");
                } else {
                    if (s.length() < 11||isMobileNo(s)==false) {
                        ToastUtils.showToast(KUaiJieZhiFu.this,  "手机号非法");
                    } else if (s.length() > 11||isMobileNo(s)==false) {
                        ToastUtils.showToast(KUaiJieZhiFu.this,  "手机号非法");
                    } else {
                        getHttp();
                    }
                }

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

        MyLog.e("dsfs",""+ s);
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            js_request.put("bankReservedPhone", s);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            MyLog.e("TAG", "id" + user_id);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("开通快捷支付",""+canshu );

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
                        MyLog.e("开通快捷支付Gson",response );
                        QianYue_gson data = new Gson().fromJson(response, QianYue_gson.class);
                        String html = data.getHtml();
                        Intent itcz = new Intent(KUaiJieZhiFu.this, QianYueOk.class);
                        itcz.putExtra("HTML", html);
                        MyLog.e("HTML!:",""+html.toString() );
                        startActivity(itcz);
                        finish();
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
        qy_phone.addTextChangedListener(new MaxLengthWatcher(11, qy_phone));


    }
    public static boolean isMobileNo(String mobiles) {
        /*
         * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、184、187、188、147
         * 联通号码段:130、131、132、185、186、145、171/176/175
         * 电信号码段:133、153、180、181、189、173、177
         */
        String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
        /**
         * (13[0-9])代表13号段 130-139
         * (14[5|7])代表14号段 145、147
         * (15([0-3]|[5-9]))代表15号段 150-153 155-159
         * (17([1-3][5-8]))代表17号段 171-173 175-179 虚拟运营商170屏蔽
         * (18[0-9]))代表18号段 180-189
         * d{8}代表后面可以是0-9的数字，有8位
         */
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
