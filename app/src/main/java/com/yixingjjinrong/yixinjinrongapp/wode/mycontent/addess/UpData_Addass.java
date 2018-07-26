package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess;

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
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.UpData_addass_gson;
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

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

public class UpData_Addass extends AutoLayoutActivity implements CityPickerListener {
    private EditText sh_name, sh_phone, sh_mainaddass;
    private TextView sh_addass;
    private Button addass_ok;
    private String addass_name;
    private String addass_phone;
    private String addass_addass;
    private String addass_id;
    private CityPicker cityPicker;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private ImageView bj_dz_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_updata_addass);
        cityPicker = new CityPicker(UpData_Addass.this, this);
        bj_dz_fh=findViewById(R.id.bj_dz_fh);
        getupdatainitview();
        sh_name.setText(addass_name);
        sh_phone.setText(addass_phone);
        sh_addass.setText(addass_addass);
        sh_mainaddass.setText(addass_addass);
        sh_addass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });
        addass_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttp();
            }
        });
        bj_dz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("phone", sh_phone.getText().toString());
            js_request.put("name", sh_name.getText().toString());
            js_request.put("addressId", addass_id);
            js_request.put("address", sh_addass.getText().toString()+sh_mainaddass.getText().toString());
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
            Log.e("我的消息",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/updateAddressInfo.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("更改收货地址GSON", ""+result);
                UpData_addass_gson data = new Gson().fromJson(result, UpData_addass_gson.class);
                String message = data.getMessage();
                if (message.equals("更新地址成功")){
                    Toast.makeText(UpData_Addass.this, ""+message, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(UpData_Addass.this, ""+message, Toast.LENGTH_SHORT).show();
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

    private void getupdatainitview() {
        user_id = (int) SPUtils.get(this,"userId",0);
        Intent intent = getIntent();
        addass_name = intent.getStringExtra("addass_name");
        addass_phone = intent.getStringExtra("addass_phone");
        addass_addass = intent.getStringExtra("addass_addass");
        addass_id = intent.getStringExtra("addass_id");
        sh_name = findViewById(R.id.sh_name);
        sh_phone = findViewById(R.id.sh_phone);
        sh_addass = findViewById(R.id.sh_addass);
        sh_mainaddass = findViewById(R.id.sh_mainaddadd);
        addass_ok=findViewById(R.id.addass_ok);
    }

    @Override
    public void getCity(String s) {
        sh_addass.setText(s);
    }

    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }
}
