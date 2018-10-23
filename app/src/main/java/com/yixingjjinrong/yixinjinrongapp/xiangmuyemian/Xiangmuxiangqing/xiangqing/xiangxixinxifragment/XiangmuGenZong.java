package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DaoHang_GSON;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuGenZong_gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.XiangMuGenZong_adapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
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

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class XiangmuGenZong extends LazyFragment {

    private String borrowRandomId;
    private TextView end_time, yunyong_money, nengli_bianhua;//截止日期,借款资金运用情况,借款人还款能力变化情况
    private TextView shesu_qingkuang, jingying_caiwu, yuqi_qingkuang;//借款人涉诉情况,借款人经营状态及财务状况,借款人逾期情况
    private TextView chufa_qingkuang,shenqing_num,jiekuan_num;//借款人受行政处罚情况,借款人近一个月同业申请数量,借款人近一个月同业借款数量
    private RecyclerView genzong_rview;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private PromptDialog promptDialog;
    private String token1;
    private String loginid;
    private int user_id;
    private List<XiangMuGenZong_gson.ResultBean.ImgListBean> list=new ArrayList<>();
    private MyScrollView xiangmujinduSV;
    private XiangMuGenZong_adapter adapter;
    private XiangMuGenZong_gson data;
    private View xiangmugenzong_kong,xiangmugenzong_have;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.xiangmugenzong);
        initview();
        xiangmugenzong_have.setVisibility(View.GONE);

        String s = String.valueOf(user_id);
        MyLog.e("xianmjingduid",s );
        if (s.equals("0")) {
            ToastUtils.showToast(getActivity(),"未登入" );
        } else {
            gethttp();
        }

    }


    private void gethttp() {
        promptDialog = new PromptDialog(getActivity());
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId", borrowRandomId);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
            js_request.put("userId", user_id);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAG", ">>>>SDEWSFDREREbase加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>()base()加密11111!!--" + base1);
            MyLog.e("TAG", ">>>>()sha1()加密11111!!--" + sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/ProjectTracking.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(getActivity(), "网络异常");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        promptDialog.dismiss();
                        MyLog.e("项目跟踪GSON", "" + response);
                        data = new Gson().fromJson(response, XiangMuGenZong_gson.class);
                        getmessage();
                    }
                });
    }

    private void getmessage() {
        int msg = data.getResult().getMsg();
        switch (msg) {
            case 0:
                xiangmugenzong_kong.setVisibility(View.VISIBLE);
//                ToastUtils.showToast(getActivity(),"未登入" );
                break;
            case 1:
                xiangmugenzong_kong.setVisibility(View.VISIBLE);
//                ToastUtils.showToast(getActivity(),"未还款" );
                break;
            case 2:
                xiangmugenzong_kong.setVisibility(View.GONE);
                xiangmugenzong_have.setVisibility(View.VISIBLE);
                adapter=new XiangMuGenZong_adapter(list,data.getResult().getICIMAGE(),getActivity());
                genzong_rview.setAdapter(adapter);
                end_time.setText(data.getResult().getTracking().getEndTime());//截止日期
                yunyong_money.setText(data.getResult().getTracking().getBorrowMoneyRunCondition());//借款资金运用情况
                nengli_bianhua.setText(data.getResult().getTracking().getBorrowerRepaymentCondition());//借款人还款能力变化情况
                shesu_qingkuang.setText(data.getResult().getTracking().getLawCondition());//借款人涉诉情况
                jingying_caiwu.setText(data.getResult().getTracking().getBorrowerRunCondition());//借款人经营状态及财务状况
                yuqi_qingkuang.setText(data.getResult().getTracking().getOverdueCondition());//借款人逾期情况
                chufa_qingkuang.setText(data.getResult().getTracking().getAdministrativePenalty());//借款人受行政处罚情况
                shenqing_num.setText(data.getResult().getTracking().getPositionnum());//借款人近一个月同业申请数量
                shenqing_num.setText(data.getResult().getTracking().getPositionmonery());//借款人近一个月同业借款数量
                list.addAll(data.getResult().getImgList());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(), "borroFwRandomId", "");
        MyLog.e("项目进度类型", "" + borrowRandomId);
        xiangmugenzong_kong=findViewById(R.id.xiangmugenzong_kong);
        xiangmugenzong_have=findViewById(R.id.xiangmugenzong_have);
        end_time = (TextView) findViewById(R.id.end_time);//截止日期
        yunyong_money = (TextView) findViewById(R.id.yunyong_money);//借款资金运用情况
        nengli_bianhua = (TextView) findViewById(R.id.nengli_bianhua);//借款人还款能力变化情况
        shesu_qingkuang = (TextView) findViewById(R.id.shesu_qingkuang);//借款人涉诉情况
        jingying_caiwu = (TextView) findViewById(R.id.jingying_caiwu);//借款人经营状态及财务状况
        yuqi_qingkuang = (TextView) findViewById(R.id.yuqi_qingkuang);//借款人逾期情况
        chufa_qingkuang = (TextView) findViewById(R.id.chufa_qingkuang);//借款人受行政处罚情况
        shenqing_num= (TextView) findViewById(R.id.shenqing_num);//借款人近一个月同业申请数量
        jiekuan_num= (TextView) findViewById(R.id.jiekuan_num);//借款人近一个月同业借款数量
        genzong_rview = (RecyclerView) findViewById(R.id.genzong_rview);
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        token1 = (String) SPUtils.get(getActivity(), "Token1", "");
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");

        xiangmujinduSV = (MyScrollView) findViewById(R.id.xiangmugengzongScrollView);
        GridLayoutManager gm = new GridLayoutManager(getActivity(),3);
        genzong_rview.setLayoutManager(gm);
        genzong_rview.setNestedScrollingEnabled(false);//解决冲突

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
