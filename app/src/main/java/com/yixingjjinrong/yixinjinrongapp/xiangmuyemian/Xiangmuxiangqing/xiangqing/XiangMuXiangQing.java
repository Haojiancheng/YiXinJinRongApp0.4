package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WandaiTishishu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WebView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.ChuJieJiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.HuiKuanJiHua;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.JieKuanZiLiao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJingDu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuXinXi;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XiangMuXiangQing extends AutoLayoutActivity {
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private ChuJieJiLu mChuJieJiLu;               //出借记录 fragment
    private HuiKuanJiHua mHuiKuanJiHua;           //回款计划 fragment
    private JieKuanZiLiao mJieKuanZiLiao;         //借款资料 fragment
    private XiangMuJingDu mXiangMuJingDu;         //项目进度 fragment
    private XiangMuXinXi mXiangMuXinXi;           //项目信息 fragment
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button jianhao, jiahao;//加减用户的输入金额
    private EditText jinge;//用户输入的金额
    private TextView wangdaitishishu,jikuanxieyi,dianziqianzhang,zijinlaiyuan,xiangqing_lilv,xiangqing_jiaohao,xiangqing_fujia_lilv,xiangqing_fujia_bi,shengyuchujie,xiangqing_qixian,xiangqing_zonge,fangshi,keyangyue;
    private String id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView youhuijuan;//优惠券
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        EventBus.getDefault().register(this);//注册 
        setContentView(R.layout.xiaomuxiangqing);
   
        getID();//获取资源ID
        initView();
        getjinge();//获取输入金额
//        wangdaitishishu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(XiangMuXiangQing.this, WandaiTishishu.class);
//                startActivity(intent);
//            }
//        });
        Intent it=getIntent();
        id = it.getStringExtra("xiangmu_id");

        getWebview();//各种提示书

//        EventBus.getDefault().post(new BorrowRandomId_id(id));
        getHttps();
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void gdewsf (User_id event) {
//        user_id = event.getUser_id();
//        Log.e("传来的User_id","id+:"+ user_id);
//
//    }

    private void getWebview() {
        wangdaitishishu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//网贷提示书
                Intent it=new Intent(XiangMuXiangQing.this,WebView.class);
                it.putExtra("url","yxb_mobile/yxbApp/agreement.do");
                it.putExtra("title1","网络借贷风险和禁止行为提示书");
                startActivity(it);
            }
        });
        jikuanxieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//借款协议
                Intent it=new Intent(XiangMuXiangQing.this,WandaiTishishu.class);
                it.putExtra("url","yxb_mobile/yxbApp/borrowmoney.do?");
                it.putExtra("b_id",id);
                it.putExtra("title1","借款协议范本");
                startActivity(it);
            }
        });
        dianziqianzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//电子签章
                Intent it=new Intent(XiangMuXiangQing.this,WebView.class);
                it.putExtra("url","yxb_mobile/yxbApp/promisebook.do");
                it.putExtra("title1","个人电子签章授权委托书");
                startActivity(it);
            }
        });
        zijinlaiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(XiangMuXiangQing.this,WebView.class);
                it.putExtra("url","yxb_mobile/yxbApp/Promptbook.do ");
                it.putExtra("title1","资金来源合法承诺书");
                startActivity(it);
            }
        });
        
    }


    private void getjinge() {
        jianhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(jinge.getText().toString()) <= 100) {//金额不能小于100
                    Toast.makeText(XiangMuXiangQing.this, "起投金额不能小于100元", Toast.LENGTH_SHORT).show();
                } else {
                    jinge.setText(Integer.valueOf(jinge.getText().toString()) - 100 + "");//减后的金额
                }
            }
        });

        jiahao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                jinge.setText(Integer.valueOf(jinge.getText().toString()) + 100 + "");//加后的金额
            }
        });

    }


    private void getID() {
        user_id = (int) SPUtils.get(this,"userId",0);
        Log.e("userid", "id:"+user_id );
        jianhao = findViewById(R.id.bt_jianhao);
        jinge = findViewById(R.id.et_jinge);
        jiahao = findViewById(R.id.bt_jiahao);
        wangdaitishishu=findViewById(R.id.wangdaitishishu);
        keyangyue=findViewById(R.id.keyangyue);//可用余额
        fangshi=findViewById(R.id.fangshi);//还款方式
        xiangqing_zonge=findViewById(R.id.xiangqing_zonge);//总额
        xiangqing_qixian=findViewById(R.id.xiangqing_qixian);//还款期限
        shengyuchujie=findViewById(R.id.shengyuchujie);//剩余出借金额
        xiangqing_fujia_bi=findViewById(R.id.xiangqing_fujia_bi);//"%"
        xiangqing_fujia_lilv=findViewById(R.id.xiangqing_fujia_lilv);//附加利率
        xiangqing_jiaohao=findViewById(R.id.xiangqing_jiaohao);//"+"
        xiangqing_lilv=findViewById(R.id.xiangqing_lilv);//利率
        jikuanxieyi=findViewById(R.id.jiekuanxieyi);//借款协议
        zijinlaiyuan=findViewById(R.id.zijinlaiyuan);//资金来源
        dianziqianzhang=findViewById(R.id.dianziqianzhang);//电子签章
        youhuijuan=findViewById(R.id.youhuijuan);//优惠券
        
    }



    private void getHttps() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId",id);
            SPUtils.put(this,"borrowRandomId",id);
            js_request.put("userId", user_id);
            Log.e("TAG","id"+user_id);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/couponinformation.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "8888888888888888888Gson:" + result);
                XiangMuXiangQing_Gson data=new Gson().fromJson(result,XiangMuXiangQing_Gson.class);
                xiangqing_zonge.setText(data.getResult().getRedList1().getBorrowSum());
                xiangqing_qixian.setText(data.getResult().getRedList1().getDeadline());
                fangshi.setText(data.getResult().getRedList1().getPaymentMode());
                xiangqing_lilv.setText(data.getResult().getRedList1().getRan()+"");
                shengyuchujie.setText(data.getResult().getRedList1().getSurplus());
                keyangyue.setText(data.getResult().getUserMap().getUsableSum());
                if (data.getResult().getRedList1().getRans()==0){
                    xiangqing_fujia_lilv.setText("");
                    xiangqing_jiaohao.setText("");
                    xiangqing_fujia_bi.setText("");

                }else {
                    xiangqing_fujia_lilv.setText(data.getResult().getRedList1().getRans()+"");
                    xiangqing_jiaohao.setText("+");
                    xiangqing_fujia_bi.setText("%");
                }
                if (data.getResult().getJuan()!=null){
                    youhuijuan.setText("未使用");
                    List<String> jiaxilist=new ArrayList<>();
                    for (int i = 0; i <data.getResult().getJuan().size(); i++) {
                        Log.e("卷",""+data.getResult().getJuan().get(i).getActivitype()+"第"+i);

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

    private void initView() {
        mChuJieJiLu = new ChuJieJiLu();
        mHuiKuanJiHua = new HuiKuanJiHua();
        mJieKuanZiLiao = new JieKuanZiLiao();
        mXiangMuJingDu = new XiangMuJingDu();
        mXiangMuXinXi = new XiangMuXinXi();
        list_fragment.add(mXiangMuXinXi);
        list_fragment.add(mHuiKuanJiHua);
        list_fragment.add(mXiangMuJingDu);
        list_fragment.add(mJieKuanZiLiao);
        list_fragment.add(mChuJieJiLu);

        list_title.add("项目信息");
        list_title.add("回款计划");
        list_title.add("项目进度");
        list_title.add("借款资料");
        list_title.add("出借记录");

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);//反注册  
    }
}
