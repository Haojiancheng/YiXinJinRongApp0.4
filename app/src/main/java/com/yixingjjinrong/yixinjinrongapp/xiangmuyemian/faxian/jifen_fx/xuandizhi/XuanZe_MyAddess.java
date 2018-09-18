package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.xuandizhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.MyAddass_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.Myaddass_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.addess.AddAddass;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class XuanZe_MyAddess extends AutoLayoutActivity implements XRecyclerView.LoadingListener{
    private XRecyclerView addass_rview;
    private int user_id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private Button addass_instat;
    private ImageView shouhuo_dz_fh;
    int a=1;
    private List<MyAddass_Gson.ResultBean.AddressListBean> list=new ArrayList<>();
    private Myaddass_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {                                  //适配华为手机虚拟键遮挡tab的问题
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));                   //需要在setContentView()方法后面执行
//        }
        setContentView(R.layout.activity_my_addess);

        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        getaddassid();
        getaddAssHTTP();
        addass_instat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(XuanZe_MyAddess.this,AddAddass.class);
                startActivity(it);
            }
        });
        shouhuo_dz_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getaddAssHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("pageNum", a);
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
            MyLog.e("我的消息",""+canshu );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/addressInfoList.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("我的地址GSON", ""+result);
                        MyAddass_Gson data = new Gson().fromJson(result, MyAddass_Gson.class);
                        if (data.getMessage().equals("查询成功")) {

                            list.addAll(data.getResult().getAddressList());

                            adapter = new Myaddass_adapter(list,XuanZe_MyAddess.this, user_id);
                            addass_rview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            adapter.setonEveryItemClickListener(new Myaddass_adapter.OnEveryItemClickListener() {
                                @Override
                                public void onEveryClick(int position) {
                                    String receiverAddress = list.get(position).getReceiverAddress()+list.get(position).getAddressDetail();
                                    String receiverName = list.get(position).getReceiverName();
                                    String receiverId = list.get(position).getReceiverId();
                                    String receiverPhone = list.get(position).getReceiverPhone();
                                    SPUtils.put(XuanZe_MyAddess.this, "mAddress", receiverAddress);
                                    SPUtils.put(XuanZe_MyAddess.this, "receiverName", receiverName);
                                    SPUtils.put(XuanZe_MyAddess.this, "receiverId", receiverId);
                                    SPUtils.put(XuanZe_MyAddess.this, "receiverPhone", receiverPhone);
                                    finish();

                                }
                            });

                        }else {
                            Toast.makeText(XuanZe_MyAddess.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void getaddassid() {
        user_id = (int) SPUtils.get(XuanZe_MyAddess.this,"userId",0);
        addass_rview=findViewById(R.id.addass_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XuanZe_MyAddess.this, LinearLayoutManager.VERTICAL, false);
        addass_rview.setLayoutManager(linearLayoutManager);
        addass_rview.setLoadingListener(this);
        addass_rview.setPullRefreshEnabled(true);
        addass_rview.setLoadingMoreProgressStyle(ProgressStyle.BallPulseRise);
        addass_instat=findViewById(R.id.addass_instat);
        shouhuo_dz_fh=findViewById(R.id.shouhuo_dz_fh);
    }

    @Override
    public void onRefresh() {
        list.clear();
        a=1;
        getaddAssHTTP();
        addass_rview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        a++;
        getaddAssHTTP();

        addass_rview.loadMoreComplete();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        getaddAssHTTP();
//        adapter.notifyDataSetChanged();
    }
}
