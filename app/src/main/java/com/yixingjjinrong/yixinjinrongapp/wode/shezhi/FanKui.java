package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

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
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FanKui_ok_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.FengXianPingCe;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class FanKui extends AutoLayoutActivity {
    private EditText et_content = null, fk_phone;
    private TextView text_count = null;
    private Button btn_submit;
    private static final int MAX_COUNT = 200;
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private int user_ird;
    private ImageView fanhui_yijianfk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_fan_kui);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .statusBarDarkFont(true)
                .init();
        getid();
        fanhui_yijianfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text_count.setText(String.valueOf(MAX_COUNT - s.length()));
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gethttp();
            }
        });
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("counts", et_content.getText().toString());
            js_request.put("userid", user_ird);
            Log.e("....ID", "" + user_ird);
            Log.e("....counts", "" + et_content.getText().toString());
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/contre.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("意见反馈GSON", "" + result);
                        FanKui_ok_gson data = new Gson().fromJson(result, FanKui_ok_gson.class);
                        String message = data.getMessage();
                        ToastUtils.showToast(FanKui.this, ""+message);
                    }
                });

    }

    private void getid() {
        et_content = findViewById(R.id.et_content);
        fk_phone = findViewById(R.id.fk_phone);
        text_count = findViewById(R.id.text_count);
        btn_submit = findViewById(R.id.btn_submit);
        fanhui_yijianfk=findViewById(R.id.fanhui_yijianfk);
    }
}
