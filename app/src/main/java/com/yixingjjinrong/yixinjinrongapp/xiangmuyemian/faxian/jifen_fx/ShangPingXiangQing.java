package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Goods_xq;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class ShangPingXiangQing extends AutoLayoutActivity {
    private ImageView dh_fh;
    private String awardType;
    private String speid;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private int user_id;
    private ImageView goods_xq_image;
    private TextView goods_xq_fen,goods_xq_ku,goods_xq_describe,goods_xq_name;
    private Button goods_xq_duihuanbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_shang_ping_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getid();
        getonclock();
        gethttp();


    }

    private void gethttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("awardType", awardType);
            js_request.put("speId", speid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>11111!!--" + js_request);
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
                //http://192.168.1.111:8080/yxb_mobile/
                .url(Urls.BASE_URL+"yxbApp/integralExchangeDetail.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("网络错误", ""+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("商品详情gson", response);
                        Goods_xq data = new Gson().fromJson(response, Goods_xq.class);
                        Glide.with(ShangPingXiangQing.this).load(data.getResult().getPath()+data.getResult().getGoodsList().get(0).getPicUrl()).into(goods_xq_image);
                        goods_xq_fen.setText(data.getResult().getGoodsList().get(0).getExchangeCredits()+"");
                        goods_xq_ku.setText(data.getResult().getGoodsList().get(0).getSurplusNum()+"");
                        goods_xq_describe.setText(data.getResult().getGoodsList().get(0).getDescription());
                        goods_xq_name.setText(data.getResult().getGoodsList().get(0).getPrizeName());

                    }
                });

    }

    private void getonclock() {

        dh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getid() {
        user_id = (int) SPUtils.get(ShangPingXiangQing.this,"userId",0);
        dh_fh=findViewById(R.id.dh_fh);
        Intent it=getIntent();
        awardType = it.getStringExtra("awardType");
        speid = it.getStringExtra("speid");
        goods_xq_image=findViewById(R.id.goods_xq_image);
        //,,,;
        goods_xq_fen=findViewById(R.id.goods_xq_fen);
        goods_xq_ku=findViewById(R.id.goods_xq_ku);
        goods_xq_describe=findViewById(R.id.goods_xq_describe);
        goods_xq_duihuanbt=findViewById(R.id.goods_xq_duihuanbt);
        goods_xq_name=findViewById(R.id.goods_xq_name);

    }
}
