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
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

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
    private TextView jfgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_ji_fen_dui_huan);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getduihuan_id();
        getduihuanHTTp();

        getoncolincl();
    }

    private void getoncolincl() {

        jfgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent it = new Intent(JiFenDuiHuan.this, JiFenGongLv.class);
                    startActivity(it);

            }
        });

        jifenjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//积分记录
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Toast.makeText(JiFenDuiHuan.this, "请先登入再查看", Toast.LENGTH_SHORT).show();
                }else {
                    Intent inte = new Intent(JiFenDuiHuan.this, JIFenJiLu.class);
                    startActivity(inte);
                }
            }
        });
        duihuanjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Toast.makeText(JiFenDuiHuan.this, "请先登入再查看", Toast.LENGTH_SHORT).show();
                }else {
                    Intent inte = new Intent(JiFenDuiHuan.this, DuiHuanJILu.class);
                    startActivity(inte);
                }
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
            Log.e("TAG", ">>>>1111!!--" + js_request);
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
                .url(Urls.BASE_URL+"yxbApp/integralExchangeList.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("积分兑换GSOn:",result );
                        JiFenDuiHuan_Gson data = new Gson().fromJson(result, JiFenDuiHuan_Gson.class);
                        if (data.getMessage().equals("成功了")) {

                            String paht = data.getResult().getPath();
                            user_jifen.setText(String.valueOf(data.getResult().getIntegral()));
                            list.addAll(data.getResult().getGoodsList());
                            adapter = new JiFenDuiHUan_adapter(list, paht);
                            jf_goods_rview.setAdapter(adapter);
                            adapter.setonEveryItemClickListener(new JiFenDuiHUan_adapter.OnEveryItemClickListener() {
                                @Override
                                public void onEveryClick(int position) {
                                    String awardType = String.valueOf(list.get(position).getAwardType());
                                    String speid = String.valueOf(list.get(position).getSpeId());
                                    String prizeId = list.get(position).getPrizeId();
                                    //跳详情
                                    Intent it=new Intent(JiFenDuiHuan.this, ShangPingXiangQing.class);
                                    it.putExtra("awardType", awardType);
                                    it.putExtra("speid", speid);
                                    it.putExtra("speid", speid);
                                    it.putExtra("prizeId", prizeId);
                                    startActivity(it);
                                }
                            });
                        }else {
                            Toast.makeText(JiFenDuiHuan.this, ""+data.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getduihuan_id() {
        user_id = (int) SPUtils.get(JiFenDuiHuan.this,"userId",0);
        user_jifen=findViewById(R.id.user_jifen);//用户积分
        jf_goods_rview=findViewById(R.id.jf_goods);
        jfdh_fh=findViewById(R.id.jfdh_fh);
        jfgl=findViewById(R.id.jfgl);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
        jf_goods_rview.setLayoutManager(layoutManager);

        jifenjilu=findViewById(R.id.jifenjilu);
        duihuanjilu=findViewById(R.id.duihuanjilu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
