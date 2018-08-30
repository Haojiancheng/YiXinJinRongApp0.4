package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess;

import android.os.Bundle;
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
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Add_addass_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;
import okhttp3.Call;
import okhttp3.MediaType;

public class AddAddass extends AutoLayoutActivity implements CityPickerListener{
    private EditText add_name, add_phone, add_mainaddass;
    private TextView add_addass;
    private Button add_ok;
    private ImageView tj_dz_fh;
    private CityPicker cityPicker;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_add_addass);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        cityPicker = new CityPicker(AddAddass.this, this);
        getadd_id();
        add_addass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });
        add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getaddHttP();
            }
        });
        tj_dz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
    }

    private void getaddHttP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("phone", add_phone.getText().toString());
            js_request.put("name", add_name.getText().toString());
            js_request.put("addressDetail",add_mainaddass.getText().toString());
            js_request.put("address", add_addass.getText().toString());
            Log.e("添加：name", ""+js_request);
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
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/addAddressInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("添加收货地址GSOn：",""+result );
                        Add_addass_gson add = new Gson().fromJson(result, Add_addass_gson.class);
                        String message = add.getMessage();
                        if (message.equals("添加地址成功")){
                            Toast.makeText(AddAddass.this, ""+message, Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(AddAddass.this, ""+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getadd_id() {
        user_id = (int) SPUtils.get(this,"userId",0);
        add_name = findViewById(R.id.add_name);
        add_phone = findViewById(R.id.add_phone);
        add_addass = findViewById(R.id.add_addass);
        add_mainaddass = findViewById(R.id.add_mainaddadd);
        add_ok=findViewById(R.id.add_ok);
        tj_dz_fh=findViewById(R.id.tj_dz_fh);
    }

    @Override
    public void getCity(String s) {
      add_addass.setText(s);
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
