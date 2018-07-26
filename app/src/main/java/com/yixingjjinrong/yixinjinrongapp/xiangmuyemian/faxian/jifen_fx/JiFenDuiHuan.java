package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.AndroidWorkaround;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.JiFenDuiHuan_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.FaXianBasrAdapter;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.JiFenDuiHUan_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.twojifen.DuiHuanJILu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.twojifen.JIFenJiLu;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class JiFenDuiHuan extends AutoLayoutActivity {
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView user_jifen;//我的积分
    private RecyclerView jf_goods_rview;
    private List<JiFenDuiHuan_Gson.ResultBean.GoodsListBean> list=new ArrayList<>();
    private JiFenDuiHUan_adapter adapter;
    private TextView jifenjilu,duihuanjilu;
    private ImageView jfdh_fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
        }
        setContentView(R.layout.activity_ji_fen_dui_huan);
        getduihuan_id();
        getduihuanHTTp();

        getoncolincl();
    }

    private void getoncolincl() {
        jifenjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//积分记录
                Intent inte=new Intent(JiFenDuiHuan.this, JIFenJiLu.class);
                startActivity(inte);
            }
        });
        duihuanjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(JiFenDuiHuan.this, DuiHuanJILu.class);
                startActivity(inte);
            }
        });
        jfdh_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getduihuanHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("addressId", "");
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/integralExchangeList.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("积分兑换GSOn:",result );
                JiFenDuiHuan_Gson data = new Gson().fromJson(result, JiFenDuiHuan_Gson.class);
                String paht = data.getResult().getPath();
                user_jifen.setText(String.valueOf(data.getResult().getIntegral()));
                list.addAll(data.getResult().getGoodsList());
                adapter=new JiFenDuiHUan_adapter(list,paht);
                jf_goods_rview.setAdapter(adapter);

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

    private void getduihuan_id() {
        user_id = (int) SPUtils.get(JiFenDuiHuan.this,"userId",0);
        user_jifen=findViewById(R.id.user_jifen);//用户积分
        jf_goods_rview=findViewById(R.id.jf_goods);
        jfdh_fh=findViewById(R.id.jfdh_fh);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
        jf_goods_rview.setLayoutManager(layoutManager);

        jifenjilu=findViewById(R.id.jifenjilu);
        duihuanjilu=findViewById(R.id.duihuanjilu);
    }
}
