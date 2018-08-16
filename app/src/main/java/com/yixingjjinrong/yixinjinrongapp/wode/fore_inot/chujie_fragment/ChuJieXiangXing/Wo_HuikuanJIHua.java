package com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.chujie_fragment.ChuJieXiangXing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.HuiKuanJiHua_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Wo_HUiKuanJIHua_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.HuiKuanJH_adapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Wo_HUiKuanJIHUa_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class Wo_HuikuanJIHua extends AutoLayoutActivity {
    private RecyclerView wo_rvoew;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String bid;
    private String investid;
    private int user_id;
    private List<Wo_HUiKuanJIHua_gson.RepayListBean> list=new ArrayList<>();
    private Wo_HUiKuanJIHUa_adapter adapter;
    private ImageView wo_hk_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_wo__huikuan_jihua);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getid();
        getHttp();
        wo_hk_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId",user_id);
            js_request.put("borrowId",bid);
            js_request.put("investId",investid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("TAG", ">>>>()base()加密11111!!--" + base1);
            Log.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/getInvestList.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("回款计划Gason","<><>,>?>?GSOn"+result);
                        Wo_HUiKuanJIHua_gson data=new Gson().fromJson(result,Wo_HUiKuanJIHua_gson.class);
                        list.addAll(data.getRepayList());
                        adapter=new Wo_HUiKuanJIHUa_adapter(list);
                        wo_rvoew.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void getid() {
        Intent it=getIntent();
        bid = it.getStringExtra("bid");
        investid = it.getStringExtra("investid");
        user_id = (int) SPUtils.get(this,"userId",0);
        wo_rvoew=findViewById(R.id.Wo_hkjh_rview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        wo_rvoew.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wo_hk_fh=findViewById(R.id.wo_hk_fh);
    }
}
