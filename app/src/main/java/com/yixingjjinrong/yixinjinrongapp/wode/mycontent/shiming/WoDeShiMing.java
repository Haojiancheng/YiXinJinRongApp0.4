package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.A2bigA;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingRenZhengKO;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class WoDeShiMing extends AutoLayoutActivity {
    private ImageView mycontent_shiming_fanhui;
    private EditText my_zhen_name,my_user_idcard;
    private Button my_renzheng_goin;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private TextView jinggao;//错误消息
    private String loginid;
    private String token;
    private String username;
    private String password;
    private String url;
    private int user_ird;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_shi_ming);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        getid();

        getonclock();
    }

    private void getonclock() {
        mycontent_shiming_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_renzheng_goin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gethttp();
            }
        });
    }

    private void gethttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        String my_name = my_zhen_name.getText().toString();
        final String zhen_id = my_user_idcard.getText().toString();
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_ird);
            js_request.put("idNo", zhen_id);
            js_request.put("realName", my_name);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            MyLog.e("实名认证", ""+js_request);
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
            MyLog.e("实名认证加密", ""+canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/userAuth.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(WoDeShiMing.this,"网络连接失败" );
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("实名认证的GSOn", ""+result);
                        ShiMingRenZengJieGuo_gson data = new Gson().fromJson(result, ShiMingRenZengJieGuo_gson.class);
                        String message = data.getMessage().toString();
//                Toast.makeText(ShiMingrenzheng.this, ""+message, Toast.LENGTH_SHORT).show();

                        String zhuangtai = data.getState();
                        MyLog.e("实名认证", zhuangtai);
                        if (zhuangtai.equals("success")){
//                    String realName = data.getResult().getRealName();
//                    String idNo = data.getResult().getIdNo();
//                            Intent intent=new Intent(WoDeShiMing.this,ShiMingRenZhengKO.class);
//                            startActivity(intent);
                            promptDialog.dismiss();
                            ToastUtils.showToast(WoDeShiMing.this,"认证成功" );
                            finish();
                        }else {
                            promptDialog.dismiss();
                            jinggao.setVisibility(View.VISIBLE);
                            jinggao.setText(message);
                        }
                    }
                });

    }

    private void getid() {
        my_renzheng_goin=findViewById(R.id.my_renzheng_goin);
        my_zhen_name=findViewById(R.id.my_zhen_name);
        my_user_idcard=findViewById(R.id.my_user_idcard);
        mycontent_shiming_fanhui=findViewById(R.id.mycontent_shiming_fanhui);
        loginid = (String) SPUtils.get(WoDeShiMing.this, "Loginid", "");
        token = (String) SPUtils.get(WoDeShiMing.this, "Token1", "");
        username = (String) SPUtils.get(WoDeShiMing.this, "username", "");
        password = (String) SPUtils.get(WoDeShiMing.this, "password", "");
        url = (String) SPUtils.get(WoDeShiMing.this, "url", "");
        user_ird = (int) SPUtils.get(WoDeShiMing.this, "userId", 0);
        jinggao=findViewById(R.id.my_jinggao11);

        my_zhen_name.addTextChangedListener(new MaxLengthWatcher(6,my_zhen_name));
        my_zhen_name.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strs=my_zhen_name.getText().toString();
                str = stringFilter1(strs.toString());
                if (!strs.equals(str)) {
                    my_zhen_name.setText(str);
                    my_zhen_name.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//只能是汉字
        my_user_idcard.addTextChangedListener(new MaxLengthWatcher(18,my_user_idcard));
        my_user_idcard.setTransformationMethod(new A2bigA());//小写转大写

    }

    public static String stringFilter1(String str) throws PatternSyntaxException {
        //只允许汉字
        String regEx = "[^\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
