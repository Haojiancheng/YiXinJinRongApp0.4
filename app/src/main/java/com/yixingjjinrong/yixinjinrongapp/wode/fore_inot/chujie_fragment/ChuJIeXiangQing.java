package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.OutXX_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.ChuJieXiangXing.Wo_HuikuanJIHua;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class ChuJIeXiangQing extends AutoLayoutActivity {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String borrowid;
    private String investid;
    private int user_id;
    private ImageView out_img;
    private TextView out_title, xq_huankuan, out_money, out_time, out_lv, out_jia, out_fujia, out_qishu, out_timea, out_huikuan, out_jiekuan, out_tishibook, out_hefabook, out_weituobook;
    private String type;
    private ImageView wo_cjxq_fh, iv43;
    private OutXX_gson data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_chujie_xiangqing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getxpId();
        gethttp();
        wo_cjxq_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoupopwindow();
            }
        });
        out_tishibook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getweb("WL");
            }
        });
        out_hefabook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getweb("ZJ");
            }
        });
        out_weituobook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getweb("GR");
            }
        });
        out_jiekuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=Urls.BASE_URL + "yxbApp/readContractApp.do?"+"userId"+"="+user_id+"&"+"borrowId"+"="+borrowid+"&"+"investId"+"="+investid;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    private void getweb(String book) {
//        Log.e("我的出借房产表参数：", "" + Urls.BASE_URL + "yxbApp/readAgreementApp.do?" + "userId" + "=" + user_id + "&" + "borrowId" + "=" + borrowid + "&" + "investId" + "=" + investid + "&" + "agreementType" + "=" + book);
         String url=Urls.BASE_URL + "yxbApp/readAgreementApp.do?"+"userId"+"="+user_id+"&"+"borrowId"+"="+borrowid+"&"+"investId"+"="+investid+"&"+"agreementType"+"="+book;
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        startActivity(intent);

    }

    private void shoupopwindow() {

        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(ChuJIeXiangQing.this, R.layout.kuankuan_pop, null);
        TextView text_pop = popView.findViewById(R.id.text_pop);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow;
        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        text_pop.setText(data.getInvestDetails().getPaymentModezi());
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowId", borrowid);
            js_request.put("investId", investid);
            js_request.put("guaranteeType", 0);
            MyLog.e("我的出借房产表参数：", "" + js_request);
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
            MyLog.e("出借详情ddd：", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/getInvestDetails.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("出借详情GSon：", "" + result);
                        data = new Gson().fromJson(result, OutXX_gson.class);

                        out_title.setText(" " + data.getInvestDetails().getBorrowTitle() );
                        out_money.setText(" " + data.getInvestDetails().getInvestAmount());
                        out_time.setText(" " + data.getInvestDetails().getDeadline());
                        out_lv.setText(" " + data.getInvestDetails().getAnnualRate() + "%");
                        if (data.getInvestDetails().getInterest().equals("")) {
                            out_jia.setVisibility(View.GONE);
                            out_fujia.setVisibility(View.GONE);
                        } else {
                            out_fujia.setText(data.getInvestDetails().getInterest() + "%");
                        }
                        xq_huankuan.setText(" " + data.getInvestDetails().getT_borrow_style());
                        out_qishu.setText(" " + data.getInvestDetails().getLine());
                        out_timea.setText(" " + data.getInvestDetails().getEndTenderDate());
                        out_huikuan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(ChuJIeXiangQing.this, Wo_HuikuanJIHua.class);
                                it.putExtra("bid", data.getInvestDetails().getBorrowId());
                                it.putExtra("investid", data.getInvestDetails().getInvestid());
                                startActivity(it);
                            }
                        });
                    }
                });


    }

    private void getxpId() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        String token1 = (String) SPUtils.get(this, "Token1", "");
        String loginid = (String) SPUtils.get(this, "Loginid", "");
        iv43 = findViewById(R.id.iv43);
        Intent it = getIntent();
        borrowid = it.getStringExtra("borrowid");
        investid = it.getStringExtra("investid");
        type = it.getStringExtra("type");
        out_img = findViewById(R.id.out_img);
        wo_cjxq_fh = findViewById(R.id.wo_cjxq_fh);
        //,,,,,,,,,,,,;
        out_title = findViewById(R.id.out_title);
        out_money = findViewById(R.id.out_money);
        out_time = findViewById(R.id.out_time);
        out_jia = findViewById(R.id.out_jia);
        out_fujia = findViewById(R.id.out_fujia);
        out_qishu = findViewById(R.id.out_qishu);
        out_timea = findViewById(R.id.out_timea);
        out_huikuan = findViewById(R.id.out_huikuan);
        out_lv = findViewById(R.id.out_lv);
        out_jiekuan = findViewById(R.id.out_jiekuan);
        out_tishibook = findViewById(R.id.out_tishibook);
        out_hefabook = findViewById(R.id.out_hefabook);
        out_weituobook = findViewById(R.id.out_weituobook);
        xq_huankuan = findViewById(R.id.xq_huankuan);
        if (type.equals("1")) {
            Glide.with(ChuJIeXiangQing.this).load(R.drawable.fangchandiya).into(out_img);
        } else {
            Glide.with(ChuJIeXiangQing.this).load(R.drawable.cheliang).into(out_img);
        }
    }
}
