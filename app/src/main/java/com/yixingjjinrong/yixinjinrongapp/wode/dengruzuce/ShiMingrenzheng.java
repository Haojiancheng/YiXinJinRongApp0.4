package com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.A2bigA;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DengruData;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class ShiMingrenzheng extends AutoLayoutActivity {
    private EditText zhen_name,user_idcard;
    private Button renzheng_goin;
    private ImageView sm_fh;
    public static ShiMingrenzheng instance;
    private int user_ird;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private TextView jinggao;//错误消息
    private String loginid;
    private String token;
    private String username;
    private String password;
    private String url;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_mingrenzheng);
        instance=this;
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) { //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));//需要在setContentView()方法后面执行
//        }
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
//        Bundle b = getIntent().getExtras();
//        user_ird = b.getInt("user_ird");
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        getzhen_id();
        getonrenzheng();
        sm_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfanhuiHttp();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getfanhuiHttp();
    }

    private void getfanhuiHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("username", username);
            js_request.put("password", password);
            js_request.put("url", url);
            MyLog.e("实名成功(登入)：", "" + js_request);
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
            JSONObject sign = canshu.put("sign", sha1);
//            Log.e("我的账户：", ""+canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/Applogin.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("TAG", ">>>>GSON" + response);
                        DengruData d_data = new Gson().fromJson(response, DengruData.class);
                        //状态值
                        String dengrufanhuizhi = d_data.getState();
                        String user_token = d_data.getResult().getToken();
                        int user_id = d_data.getResult().getUserid();
                        String loginId = d_data.getResult().getLoginId();
                        if (dengrufanhuizhi.equals("success")) {
                            EventBus.getDefault().post(new User_data(username, dengrufanhuizhi, user_token, user_id, loginId));
                            SPUtils.put(ShiMingrenzheng.this, "isLogin", true);
                            SPUtils.put(ShiMingrenzheng.this, "Loginid", loginId);
                            SPUtils.put(ShiMingrenzheng.this, "userId", user_id);
                            SPUtils.put(ShiMingrenzheng.this, "Token1", user_token);
                            SPUtils.put(ShiMingrenzheng.this, "inviterId", d_data.getResult().getInviterId());
                            ZhuCe_PaGa.zc_instance.finish();
                            YanZheng_PaGa.instance.finish();
                            WoDe_DengRu.instance.finish();
                            finish();
                        }
                    }
                });
    }

    private void getonrenzheng() {
        renzheng_goin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttp();
            }
        });
    }

    private void getHttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        String my_name = zhen_name.getText().toString();
        final String zhen_id = user_idcard.getText().toString();
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
                        ToastUtils.showToast(ShiMingrenzheng.this,"网络连接失败" );
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
                            promptDialog.dismiss();
                            Intent intent=new Intent(ShiMingrenzheng.this,ShiMingRenZhengKO.class);
                            startActivity(intent);
                            finish();
                        }else {
                            promptDialog.dismiss();
                            jinggao.setVisibility(View.VISIBLE);
                            jinggao.setText(message);
                        }
                    }
                });


    }
    public static String stringFilter1(String str) throws PatternSyntaxException {
        //只允许汉字
        String regEx = "[^\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    private void getzhen_id() {
        loginid = (String) SPUtils.get(ShiMingrenzheng.this, "Loginid", "");
        token = (String) SPUtils.get(ShiMingrenzheng.this, "Token1", "");
        username = (String) SPUtils.get(ShiMingrenzheng.this, "username", "");
        password = (String) SPUtils.get(ShiMingrenzheng.this, "password", "");
        url = (String) SPUtils.get(ShiMingrenzheng.this, "url", "");
        user_ird = (int) SPUtils.get(ShiMingrenzheng.this, "userId", 0);
        zhen_name=findViewById(R.id.zhen_name);
        user_idcard=findViewById(R.id.user_idcard);
        renzheng_goin=findViewById(R.id.renzheng_goin);
        jinggao=findViewById(R.id.jinggao11);
        sm_fh=findViewById(R.id.sm_fh);
        zhen_name.addTextChangedListener(new MaxLengthWatcher(6,zhen_name));
        zhen_name.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strs=zhen_name.getText().toString();
                str = stringFilter1(strs.toString());
                if (!strs.equals(str)) {
                    zhen_name.setText(str);
                    zhen_name.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//只能是汉字
        user_idcard.addTextChangedListener(new MaxLengthWatcher(18,user_idcard));
        user_idcard.setTransformationMethod(new A2bigA());//小写转大写

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}
