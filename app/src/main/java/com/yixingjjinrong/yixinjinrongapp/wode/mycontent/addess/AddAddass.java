package com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Add_addass_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

public class AddAddass extends AutoLayoutActivity implements CityPickerListener{
    private EditText add_name, add_phone, add_mainaddass;
    private TextView add_addass;
    private Button add_ok;

    private CityPicker cityPicker;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addass);
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
    }

    private void getaddHttP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("phone", add_phone.getText().toString());
            js_request.put("name", add_name.getText().toString());
            js_request.put("address", add_addass.getText().toString()+add_mainaddass.getText().toString());
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/addAddressInfo.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    private void getadd_id() {
        user_id = (int) SPUtils.get(this,"userId",0);
        add_name = findViewById(R.id.add_name);
        add_phone = findViewById(R.id.add_phone);
        add_addass = findViewById(R.id.add_addass);
        add_mainaddass = findViewById(R.id.add_mainaddadd);
        add_ok=findViewById(R.id.add_ok);
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
