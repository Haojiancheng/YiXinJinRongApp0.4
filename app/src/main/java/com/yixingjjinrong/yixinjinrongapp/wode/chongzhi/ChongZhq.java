package com.yixingjjinrong.yixinjinrongapp.wode.chongzhi;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ChongZhiOk_GSon;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Yinhangka_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ChongZhq extends AutoLayoutActivity {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView yh_name, yh_number, cz_keyong;
    private ImageView yh_img;
    private EditText cz_money;
    private String keyong;
    private int user_id;
    private Button cz_ok;
    private View yhcard;
    private String loginid;
    private String token;
    private ImageView cz_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_chong_zhq);
        getczid();
        cz_keyong.setText("可用余额:  " + keyong + "元");
        getczHTTp();
        cz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void getczHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/fuRechgeInitMobileApp.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("充值GSON:", "" + result);
                Yinhangka_Gson data = new Gson().fromJson(result, Yinhangka_Gson.class);
                String msg = data.getMsg();
                if (msg.equals("")) {
                    yh_name.setText(data.getBankName());
                    yh_number.setText(data.getCardNum());
                    x.image().bind(yh_img,  data.getBankImage());
                    cz_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getokHTTp();
                        }
                    });
                } else {


                    yhcard.setVisibility(View.GONE);//影藏布局
                    if (msg.equals("auth")) {
                        Toast.makeText(ChongZhq.this, "没有实名认证", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog1 = new AlertDialog.Builder(ChongZhq.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未实名认证，是否实名认证")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getshimingHTTp();
                                        finish();
                                    }
                                })
                                .create();
                        dialog1.show();
                    }
                    if (msg.equals("bank_link")) {
                        Toast.makeText(ChongZhq.this, "没有富友开户", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog1 = new AlertDialog.Builder(ChongZhq.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未开通银行存管，是否开通")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getchHTTP();
                                        Toast.makeText(ChongZhq.this, "请开通", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                })
                                .create();
                        dialog1.show();

                    }
                    if (msg.equals("sign_card")) {
                        Toast.makeText(ChongZhq.this, "没有签约", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog3 = new AlertDialog.Builder(ChongZhq.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未没有签约")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(ChongZhq.this, "请签约", Toast.LENGTH_SHORT).show();
                                        Intent it = new Intent(ChongZhq.this, KUaiJieZhiFu.class);
                                        startActivity(it);
                                        finish();
                                    }
                                })
                                .create();
                        dialog3.show();

                    }


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

    private void getshimingHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token);
            js_request.put("loginId", loginid);
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

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("是否可实名GSON：", result);
                ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                String message = data.getMessage().toString();
                Toast.makeText(ChongZhq.this, "" + message, Toast.LENGTH_SHORT).show();
                String jieguo = data.getState().toString();
                if (jieguo.equals("success")) {
                    Intent it = new Intent(ChongZhq.this, ShiMingrenzheng.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_ird", user_id);
                    it.putExtras(bundle);
                    startActivity(it);
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

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", String.valueOf(user_id));
            js_request.put("token", token);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/accountReg.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("存管GSON:",""+result );
                CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                String html = data.getResult().getHtml();
                Intent it=new Intent(ChongZhq.this, YinHangCunGuan.class);
                it.putExtra("HTML",html );
                Log.e("我的页面银行存管HTML:",""+it);
                startActivity(it);

                Log.e("wangy",""+html );
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

    private void getokHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("money", cz_money.getText().toString());
            js_request.put("token", token);
            js_request.put("loginId", loginid);

            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("js_",""+js_request );
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/postOnlineCZFu.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ok充值：", result);
                ChongZhiOk_GSon data = new Gson().fromJson(result, ChongZhiOk_GSon.class);
                String html = data.getHtml();
                Intent itcz = new Intent(ChongZhq.this, ChongZhiOK.class);
                itcz.putExtra("HTML", html);
                Log.e("HTMLone:", "" + html.toString());
                startActivity(itcz);
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

    private void getczid() {
        Intent itzc = getIntent();
        user_id = (int) SPUtils.get(this, "userId", 0);
        loginid = (String) SPUtils.get(ChongZhq.this, "Loginid", "");
        token = (String) SPUtils.get(ChongZhq.this, "Token1", "");
        keyong = itzc.getStringExtra("keyong2");
        yh_name = findViewById(R.id.yh_name);
        yh_number = findViewById(R.id.yh_number);
        cz_keyong = findViewById(R.id.cz_keyong);
        cz_money = findViewById(R.id.cz_money);
        yh_img = findViewById(R.id.yh_img);
        cz_ok = findViewById(R.id.cz_ok);
        yhcard = findViewById(R.id.yhcard);
        cz_fh=findViewById(R.id.cz_fh);
    }

}
