package com.yixingjjinrong.yixinjinrongapp.wode.shezhi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.DataCleanManager;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class WoDe_SheZhi extends AutoLayoutActivity {
    private Button user_tuichu;//退出按钮
    private TextView repassword;//修改密码
    private ImageView fankui;//意见反馈
    private TextView hz;//缓存
    private int user_ird;
    private String token;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_shezhi);

        getSheZhi_Id();
        Bundle b = getIntent().getExtras();
        user_ird = b.getInt("user_ird");

        token = b.getString("token");
        user_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gethttp();

            }
        });
        repassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, XiuGaiMiMa.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ird", user_ird);
                shezhi_tuichu.putExtras(bundle);
                startActivity(shezhi_tuichu);

            }
        });
        try {
            String sizea = DataCleanManager.getTotalCacheSize(context);
            hz.setText(""+sizea);
            Log.e("我的缓存", ""+sizea);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, FanKui.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ird", user_ird);
                shezhi_tuichu.putExtras(bundle);
                startActivity(shezhi_tuichu);
                finish();
            }
        });

    }

    private void gethttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        String myurl= getid(context);
        try {

//            SPUtils.remove(WoDe_SheZhi.this,"userId");
//            SPUtils.remove(WoDe_SheZhi.this,"Token1");
            js_request.put("userid", user_ird);
            js_request.put("token", token);
            js_request.put("url", myurl);
            Log.e("useridddd",user_ird+"" );
            Log.e("token",token+"" );
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/quitLogin.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("退出登入的GSON:", result);
//                Intent shezhi_tuichu=new Intent(WoDe_SheZhi.this, WoDe_DengRu.class);
//                startActivity(shezhi_tuichu);
                SPUtils.put(WoDe_SheZhi.this,"isLogin",false);
//                EventBus.getDefault().post(new UnLogin());
                SPUtils.remove(WoDe_SheZhi.this,"userId");
                finish();
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
    public synchronized String getid(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String ID= TelephonyMgr.getDeviceId();
        return ID;
    }

    private void getSheZhi_Id() {
        user_tuichu=findViewById(R.id.user_tuichu);//退出
        repassword=findViewById(R.id.repassword);//修改密码
        fankui=findViewById(R.id.fankui);//意见反馈
        hz=findViewById(R.id.hz);//缓存
    }
}
