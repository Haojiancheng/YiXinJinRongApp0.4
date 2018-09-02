package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuJingDu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.fragmentuits.LazyFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class XiangMuJingDu extends LazyFragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private ImageView fabuxiangmu, fb_xyb, muji, muji_xyb, fangkuan, fk_xyb, huankuan;
    private TextView fabuxiangmu_time, mujitime, fangkuan_time, yihuan_jine, yehuan_qi, daihuan_jine, daihua_qi;
    private String borrowRandomId;
    private String token1;
    private String loginid;
    private int user_id;
    private View jindu_view,jd_dengruchakan,w_dongru_chakan;
    private PromptDialog promptDialog;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.xiangmujindufragment);
        initview();
        gethttpjingde_id();
        String s = String.valueOf(user_id);
        Log.e("xianmjingduid",s );
        if (s.equals("0")) {
            jd_dengruchakan.setVisibility(View.VISIBLE);
            jd_dengruchakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(it);
                }
            });
        } else {
            jd_dengruchakan.setVisibility(View.GONE);
            gethttp_jingdu();

        }
    }


   

    private void gethttp_jingdu() {
        promptDialog = new PromptDialog(getActivity());
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId", borrowRandomId);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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
                .url(Urls.BASE_URL + "yxbApp/progress.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                       promptDialog.dismiss();
                        ToastUtils.showToast(getActivity(),"网络异常" );
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        promptDialog.dismiss();
                        Log.e("项目进度GSOn", "" + result);
                        XiangMuJingDu_Gson data = new Gson().fromJson(result, XiangMuJingDu_Gson.class);
                        String message = data.getMessage();
                        if (message.equals("用户未登录。")) {
                            Toast.makeText(getActivity(), "请先登入再查看", Toast.LENGTH_SHORT).show();
                        } else {
                            if (data.getResult().getBorrowStatus().equals("2")) {
                                fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                yihuan_jine.setText("已还金额  0.00");
                                yehuan_qi.setText("(" + 0 + "期)");
                                daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneys().getDhje());
                                daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneys().getDhqs() + "期)");


                            }
                            if (data.getResult().getBorrowStatus().equals("3")) {
                                if (data.getResult().getAuditFullTime() != "") {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    mujitime.setText("完成时间  " + data.getResult().getAuditFullTime().toString());
                                    yihuan_jine.setText("已还金额  0.00");
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                    daihua_qi.setText("(" + data.getResult().getDeadline() + "期)");
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneys().getDhje());
                                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneys().getDhqs() + "期)");

                                } else {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
//                    mujitime.setText("完成时间  "+"正在募集中...");
                                    yihuan_jine.setText("已还金额  0.00");
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                }
                            }
                            if (data.getResult().getBorrowStatus().equals("4")) {

                                if (data.getResult().getAuditTime() != "") {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    mujitime.setText("开始时间  " + data.getResult().getAuditFullTime());
                                    fangkuan_time.setText("起息时间  " + data.getResult().getAuditTime());
                                    fangkuan.setImageDrawable(getResources().getDrawable(R.drawable.fangkuanwancheng));
                                    fk_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    huankuan.setImageDrawable(getResources().getDrawable(R.drawable.huankuanwancheng));
                                    yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    if (data.getResult().getHadRepaymentMoneyMap().getYhje() == "" && data.getResult().getHadRepaymentMoneyMap().getYhqs() == "") {
                                        yihuan_jine.setText("已还金额 0.00");
                                        yehuan_qi.setText("(" + 0 + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                        daihua_qi.setText("(" + data.getResult().getDeadline() + "期)");
                                    } else {
                                        yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                        yehuan_qi.setText("(" + data.getResult().getHadRepaymentMoneyMap().getYhqs() + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                        daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    }

                                } else {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    mujitime.setText("开始时间  " + data.getResult().getAuditFullTime());
                                    fangkuan_time.setText("起息时间  募集完成放款后起息");
                                    fangkuan.setImageDrawable(getResources().getDrawable(R.drawable.fangkuanwancheng));
                                    fk_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    huankuan.setImageDrawable(getResources().getDrawable(R.drawable.huankuanwancheng));
                                    if (data.getResult().getHadRepaymentMoneyMap().getYhje() == "" && data.getResult().getHadRepaymentMoneyMap().getYhqs() == "") {
                                        yihuan_jine.setText("已还金额 0.00");
                                        yehuan_qi.setText("(" + 0 + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                        daihua_qi.setText("(" + data.getResult().getDeadline() + "期)");
                                    } else {
                                        yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                        yehuan_qi.setText("(" + data.getResult().getHadRepaymentMoneyMap().getYhqs() + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                        daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    }
                                }
                            }
                            if (data.getResult().getBorrowStatus().equals("5")) {
                                if (data.getResult().getAuditTime() != "") {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    mujitime.setText("开始时间  " + data.getResult().getAuditFullTime());
                                    fangkuan_time.setText("起息时间  " + data.getResult().getAuditTime());
                                    fangkuan.setImageDrawable(getResources().getDrawable(R.drawable.fangkuanwancheng));
                                    fk_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    huankuan.setImageDrawable(getResources().getDrawable(R.drawable.huankuanwancheng));
                                    yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                    yehuan_qi.setText("(" + 0 + "期)");
                                    daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    if (data.getResult().getHadRepaymentMoneyMap().getYhje() == "" && data.getResult().getHadRepaymentMoneyMap().getYhqs() == "") {
                                        yihuan_jine.setText("已还金额 0.00");
                                        yehuan_qi.setText("(" + 0 + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                        daihua_qi.setText("(" + data.getResult().getDeadline() + "期)");
                                    } else {
                                        yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                        yehuan_qi.setText("(" + data.getResult().getHadRepaymentMoneyMap().getYhqs() + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                        daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    }

                                } else {
                                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                                    muji.setImageDrawable(getResources().getDrawable(R.drawable.mujiwancheng));
                                    muji_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    mujitime.setText("开始时间  " + data.getResult().getAuditFullTime());
                                    fangkuan_time.setText("起息时间  募集完成放款后起息");
                                    fangkuan.setImageDrawable(getResources().getDrawable(R.drawable.fangkuanwancheng));
                                    fk_xyb.setImageDrawable(getResources().getDrawable(R.drawable.jinxingwancheng));
                                    huankuan.setImageDrawable(getResources().getDrawable(R.drawable.huankuanwancheng));
                                    if (data.getResult().getHadRepaymentMoneyMap().getYhje() == "" && data.getResult().getHadRepaymentMoneyMap().getYhqs() == "") {
                                        yihuan_jine.setText("已还金额 0.00");
                                        yehuan_qi.setText("(" + 0 + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getAmount());
                                        daihua_qi.setText("(" + data.getResult().getDeadline() + "期)");
                                    } else {
                                        yihuan_jine.setText("已还金额 " + data.getResult().getHadRepaymentMoneyMap().getYhje());
                                        yehuan_qi.setText("(" + data.getResult().getHadRepaymentMoneyMap().getYhqs() + "期)");
                                        daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneyMap().getDhje());
                                        daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneyMap().getDhqs() + "期)");
                                    }
                                }
                            }
                        }
                    }
                });

    }

    private void gethttpjingde_id() {
        //private ImageView ,,,,,,;
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        token1 = (String) SPUtils.get(getActivity(), "Token1", "");
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        fabuxiangmu = (ImageView) findViewById(R.id.fabuxiangmu);
        fb_xyb = (ImageView) findViewById(R.id.fb_xyb);
        muji = (ImageView) findViewById(R.id.muji);
        muji_xyb = (ImageView) findViewById(R.id.muji_xyb);
        fangkuan = (ImageView) findViewById(R.id.fangkuan);
        fk_xyb = (ImageView) findViewById(R.id.fk_xyb);
        huankuan = (ImageView) findViewById(R.id.huankuan);
        // TextView,,,,,,;
        fabuxiangmu_time = (TextView) findViewById(R.id.fabuxiangmu_time);
        mujitime = (TextView) findViewById(R.id.mujitime);
        yihuan_jine = (TextView) findViewById(R.id.yihuan_jine);
        yehuan_qi = (TextView) findViewById(R.id.yehuan_qi);
        daihuan_jine = (TextView) findViewById(R.id.daihuan_jine);
        daihua_qi = (TextView) findViewById(R.id.daihua_qi);
        fangkuan_time = (TextView) findViewById(R.id.fangkuan_time);
        jd_dengruchakan=findViewById(R.id.jd_dengruchakan);
        w_dongru_chakan=findViewById(R.id.w_dongru_chakan);

    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(), "borroFwRandomId", "");
        Log.e("项目进度类型", "" + borrowRandomId);
        MyScrollView xiangmujinduSV= (MyScrollView) findViewById(R.id.xiangmujingduScrollView);
        xiangmujinduSV.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    PublicStaticClass.IsTop = true;
                } else {
                    PublicStaticClass.IsTop = false;
                }
            }

            @Override
            public void notBottom() {

            }
        });
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
    }
}
