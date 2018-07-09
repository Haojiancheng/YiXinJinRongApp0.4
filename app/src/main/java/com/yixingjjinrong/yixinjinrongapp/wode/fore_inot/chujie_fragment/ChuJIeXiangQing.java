package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.OutXX_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ChuJIeXiangQing extends AutoLayoutActivity {
    private String sha1;//SHA1加密
    private String base1;//Base64加密
    private String borrowid;
    private String investid;
    private int  user_id;
    private ImageView out_img;
    private TextView out_title,xq_huankuan,out_money,out_time,out_lv,out_jia,out_fujia,out_qishu,out_timea,out_huikuan,out_jiekuan,out_tishibook,out_hefabook,out_weituobook;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chujie_xiangqing);
        getxpId();
        gethttp();
    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("borrowId", borrowid);
            js_request.put("investId", investid);
            js_request.put("guaranteeType", 0);
            Log.e("我的出借房产表参数：",""+js_request );
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
            Log.e("我的出借房产表加密：",""+canshu );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/getInvestDetails.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("出借详情GSon：",""+result );
                OutXX_gson data = new Gson().fromJson(result, OutXX_gson.class);
                if (type.equals("fang")){
                    Glide.with(ChuJIeXiangQing.this).load(R.drawable.fangchandiya).into(out_img);
                }else {
                    Glide.with(ChuJIeXiangQing.this).load(R.drawable.cheliang).into(out_img);
                }
                out_title.setText(" "+data.getInvestDetails().getBorrowTitle()+data.getInvestDetails().getBorrowCode());
                out_money.setText(" "+data.getInvestDetails().getInvestAmount());
                out_time.setText(" "+data.getInvestDetails().getDeadline());
                out_lv.setText(" "+data.getInvestDetails().getAnnualRate()+"%");
                if (data.getInvestDetails().getInterest().equals("")){
                    out_jia.setVisibility(View.GONE);
                    out_fujia.setVisibility(View.GONE);
                }else {
                    out_fujia.setText(data.getInvestDetails().getInterest()+"%");
                }
                xq_huankuan.setText(" "+data.getInvestDetails().getT_borrow_style());
                out_qishu.setText(" "+data.getInvestDetails().getLine());
                out_timea.setText(" "+data.getInvestDetails().getEndTenderDate());


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

    private void getxpId() {
        user_id = (int) SPUtils.get(this,"userId",0);
        Intent it=getIntent();
        borrowid = it.getStringExtra("borrowid");
        investid = it.getStringExtra("investid");
        type = it.getStringExtra("type");
        out_img=findViewById(R.id.out_img);
        //,,,,,,,,,,,,;
        out_title=findViewById(R.id.out_title);
        out_money=findViewById(R.id.out_money);
        out_time=findViewById(R.id.out_time);
        out_jia=findViewById(R.id.out_jia);
        out_fujia=findViewById(R.id.out_fujia);
        out_qishu=findViewById(R.id.out_qishu);
        out_timea=findViewById(R.id.out_timea);
        out_huikuan=findViewById(R.id.out_huikuan);
        out_lv=findViewById(R.id.out_lv);
        out_jiekuan=findViewById(R.id.out_jiekuan);
        out_tishibook=findViewById(R.id.out_tishibook);
        out_hefabook=findViewById(R.id.out_hefabook);
        out_weituobook=findViewById(R.id.out_weituobook);
        xq_huankuan=findViewById(R.id.xq_huankuan);
    }
}
