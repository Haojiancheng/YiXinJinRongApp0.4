package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

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

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuJingDu_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.MyScrollView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.myview.PublicStaticClass;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class XiangMuJingDu extends Fragment {
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private ImageView fabuxiangmu, fb_xyb, muji, muji_xyb, fangkuan, fk_xyb, huankuan;
    private TextView fabuxiangmu_time, mujitime, fangkuan_time, yihuan_jine, yehuan_qi, daihuan_jine, daihua_qi;
    private String borrowRandomId;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xiangmujindufragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        gethttpjingde_id();
        gethttp_jingdu();
    }

    private void gethttp_jingdu() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId", borrowRandomId);
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
        RequestParams params = new RequestParams( Urls.BASE_URL+"yxbApp/progress.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("项目进度GSOn", "" + result);
                XiangMuJingDu_Gson data = new Gson().fromJson(result, XiangMuJingDu_Gson.class);
                if (data.getResult().getBorrowStatus().equals("2")) {
                    fabuxiangmu_time.setText("开始时间  " + data.getResult().getPublishTime());
                    yihuan_jine.setText("已还金额  0.00");
                    yehuan_qi.setText("(" + 0 + "期)");
                    daihuan_jine.setText("待还金额 " + data.getResult().getNotYetRepaymentMoneys().getDhje());
                    daihua_qi.setText("(" + data.getResult().getNotYetRepaymentMoneys().getDhqs()+ "期)");


                }
                if (data.getResult().getBorrowStatus().equals("3")){
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
                if (data.getResult().getBorrowStatus().equals("4")){
                    
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
                    fangkuan_time.setText("起息时间  募集完成放款后起息" );
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
                if (data.getResult().getBorrowStatus().equals("5")){
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
                        fangkuan_time.setText("起息时间  募集完成放款后起息" );
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

    private void gethttpjingde_id() {
        //private ImageView ,,,,,,;
        fabuxiangmu = getActivity().findViewById(R.id.fabuxiangmu);
        fb_xyb = getActivity().findViewById(R.id.fb_xyb);
        muji = getActivity().findViewById(R.id.muji);
        muji_xyb = getActivity().findViewById(R.id.muji_xyb);
        fangkuan = getActivity().findViewById(R.id.fangkuan);
        fk_xyb = getActivity().findViewById(R.id.fk_xyb);
        huankuan = getActivity().findViewById(R.id.huankuan);

        // TextView,,,,,,;
        fabuxiangmu_time = getActivity().findViewById(R.id.fabuxiangmu_time);
        mujitime = getActivity().findViewById(R.id.mujitime);
        yihuan_jine = getActivity().findViewById(R.id.yihuan_jine);
        yehuan_qi = getActivity().findViewById(R.id.yehuan_qi);
        daihuan_jine = getActivity().findViewById(R.id.daihuan_jine);
        daihua_qi = getActivity().findViewById(R.id.daihua_qi);
        fangkuan_time = getActivity().findViewById(R.id.fangkuan_time);

    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(),"borroFwRandomId","");
        Log.e("项目进度类型", ""+borrowRandomId);
        MyScrollView xiangmujinduSV = getActivity().findViewById(R.id.xiangmujingduScrollView);
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
}
