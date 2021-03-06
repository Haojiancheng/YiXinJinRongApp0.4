package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MyView.KeywordsUtil;
import com.yixingjjinrong.yixinjinrongapp.MyView.MyEditTextView;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.A2bigA;
import com.yixingjjinrong.yixinjinrongapp.application.MaxLengthWatcher;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.LJCJONR_GSon;
import com.yixingjjinrong.yixinjinrongapp.gsondata.LJCJtwo_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiMingRenZengJieGuo_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.Yinhangka_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.HideIMEUtil;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.Shiming_utile;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.FengXianPingCe;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.KUaiJieZhiFu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingRenZhengKO;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.ChuJie_OK;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WandaiTishishu;

import android.webkit.WebView;

import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WebView1;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.chongxinpingce.ChongXinPingCe;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.ChuJieJiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.HuiKuanJiHua;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.JieKuanZiLiao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJingDu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJuan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuXinXi;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangmuGenZong;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng.stringFilter1;

public class XiangMuXiangQing extends AutoLayoutActivity {
    private ArrayList<Fragment> list_fragment = new ArrayList<>();//定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();  //定义要装fragment的列表
    private ChuJieJiLu mChuJieJiLu;               //出借记录 fragment
    private HuiKuanJiHua mHuiKuanJiHua;           //回款计划 fragment
    private JieKuanZiLiao mJieKuanZiLiao;         //借款资料 fragment
    private XiangMuJingDu mXiangMuJingDu;         //项目进度 fragment
    private XiangMuXinXi mXiangMuXinXi;           //项目信息 fragment
    private XiangmuGenZong mxiangmugenzong;       //项目跟踪 fragment
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button jianhao, jiahao, xq_chongzhi;//加减用户的输入金额
    private MyEditTextView jinge;//用户输入的金额
    private TextView wangdaitishishu, jikuanxieyi, dianziqianzhang, zijinlaiyuan, xiangqing_lilv, xiangqing_jiaohao, xiangqing_fujia_lilv, xiangqing_fujia_bi, shengyuchujie, xiangqing_qixian, xiangqing_zonge, fangshi, keyangyue;
    private String id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView youhuijuan;//优惠券,預計收益, yujishouyi
    private int user_id;
    private String mType;
    private XiangMuXiangQing_Gson data;
    private String token1;
    private Button bt_lijichujie;
    private int juan_id;
    private int juan_type;
    private CheckBox cb_fuxuankuang = null;
    private String juanmake;
    private String popname;
    private String popidcard;
    private String loginid;
    private ImageView xiangmu_fh;
    private PopupWindow popview;
    private PromptDialog promptDialog;
    private View detailedinformation, xiala_view;
    private int MAX = 1000000;
    private View huankuanlan;////还款方式栏目框
    private static final int MSG_SEARCH = 1;
    private String juanjinge;
    private PopupWindow popview1;
    private PopupWindow popview2;
    private TextView kaishi_time, jiezhi_time;//开始与截止时间
    private TextView xq_leve;
    private View leve_clock;
    private PopupWindow bookpopWindow;
    private String book_checked;
    private String onfinsh;
    private TextView biaotype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaomuxiangqing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        HideIMEUtil.wrap(this);//键盘管理，点击除editText外区域收起键盘
        getID();//获取资源ID
        initView();
        detailedinformation.setVisibility(View.GONE);
        Intent it = getIntent();
        MyLog.e("立即出借borrid", "" + id);
        mType = it.getStringExtra("mortgageType");

        getjinge();//获取输入金额
        getWebview();//各种提示书

        getonclock();
        getHttps();

    }

    private void getonclock() {

        xq_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {
                    getChongzhiHttp();//充值Http

                }
            }
        });
        bt_lijichujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {

                    getchujieHttp();

                }

            }
        });
        leve_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.getResult().getRedList1().getBorrowSafelevel()) {
                    case "1":
                        gettishipop("低风险", "AAA", "借款人市场竞争能力极强，融资能力和还款能力极强，还款意愿很好。");
                        break;
                    case "2":
                        gettishipop("低风险", "AA", "借款人市场竞争能力很强，融资能力和还款能力很强，还款意愿很好。");
                    case "3":
                        gettishipop("低风险", "A", "借款人市场竞争能力较强，融资能力和还款能力较强，还款意愿良好。");
                        break;
                    case "4":
                        gettishipop("中低风险", "BBB", "借款人市场竞争能力一般，借款人还款能力较强，还款意愿良好。");
                        break;
                    case "5":
                        gettishipop("中低风险", "BB", "借款人市场竞争能力一般，借款人还款能力正常，还款意愿良好。");
                        break;
                    case "6":
                        gettishipop("中低风险", "B", "借款人市场竞争能力一般，借款人还款能力尚可，还款意愿良好。");
                        break;
                    case "7":
                        gettishipop("高风险", "CCC", "出现可能影响借款人还款能力的不利因素，存在较低概率不能按期足额偿还债务本息。");
                        break;
                    case "8":
                        gettishipop("高风险", "CC", "出现影响借款人还款能力的不利因素，存在较高概率不能按期足额偿还债务本息。");
                        break;
                    case "9":
                        gettishipop("高风险", "C", "出现影响借款人还款能力的重大不利因素，存在很高概率不能按期足额偿还债务本息。");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void gettishipop(String cc, String dengji, String message) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        View parent = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.dengjitishi, null);
        TextView b_dengji = popView.findViewById(R.id.b_dengji);
        TextView fx_cengci = popView.findViewById(R.id.fx_cengci);
        TextView b_message = popView.findViewById(R.id.b_messages);
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        b_dengji.setText(dengji);
        b_message.setText(message);
        fx_cengci.setText(cc);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void getChongzhiHttp() {//充值HTTP
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/fuRechgeInitMobileApp.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("充值GSON:", "" + response);
                        Yinhangka_Gson data = new Gson().fromJson(response, Yinhangka_Gson.class);
                        String msg = data.getMsg();
                        if (data.getMessage().equals("用户未登录。")) {
                            ToastUtils.showToast(XiangMuXiangQing.this, data.getMessage());
                        } else {

                            if (msg.equals("")) {
                                Intent itcz = new Intent(XiangMuXiangQing.this, ChongZhq.class);
                                startActivity(itcz);
                            } else {

                                if (msg.equals("auth")) {
//                                    AlertDialog dialog1 = new AlertDialog.Builder(XiangMuXiangQing.this)
//                                            .setTitle("提示")
//                                            .setMessage("您还未实名认证，是否实名认证")
//                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//
//                                                }
//                                            })
//                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
                                    showpopwindow();
//                                                }
//                                            })
//                                            .create();
//                                    dialog1.setCanceledOnTouchOutside(false);
//                                    dialog1.show();
                                } else if (msg.equals("bank_link")) {
//                                    AlertDialog dialog1 = new AlertDialog.Builder(XiangMuXiangQing.this)
//                                            .setTitle("提示")
//                                            .setMessage("您还未开通银行存管，是否开通")
//                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//
//                                                }
//                                            })
//                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
                                    cGpop();
//                                                }
//                                            })
//                                            .create();
//                                    dialog1.setCanceledOnTouchOutside(false);
//                                    dialog1.show();

                                } else if (msg.equals("sign_card")) {
//                        Toast.makeText(ChongZhq.this, "没有签约", Toast.LENGTH_SHORT).show();
                                    AlertDialog dialog3 = new AlertDialog.Builder(XiangMuXiangQing.this)
                                            .setTitle("提示")
                                            .setMessage("您还未没有签约")
                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent it = new Intent(XiangMuXiangQing.this, KUaiJieZhiFu.class);
                                                    startActivity(it);
//                                                    finish();
                                                }
                                            })
                                            .create();
                                    dialog3.setCanceledOnTouchOutside(false);
                                    dialog3.show();

                                }
                            }
                        }
                    }
                });
    }

    private void getWebview() {
        xiangmu_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wangdaitishishu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//三本网贷提示书合并
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {
//                    Intent it = new Intent(XiangMuXiangQing.this, WebView.class);
//                    it.putExtra("url", "yxbApp/agreement.do");
//                    it.putExtra("title1", "网络借贷风险和禁止行为提示书");
//                    startActivity(it);
//                    gettishibookpop();
                    Intent it = new Intent(XiangMuXiangQing.this, WebView1.class);
                    startActivity(it);

                }
            }
        });
        jikuanxieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//借款协议
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {
                    Intent it = new Intent(XiangMuXiangQing.this, WandaiTishishu.class);
                    it.putExtra("url", "yxbApp/borrowmoney.do?");
                    it.putExtra("b_id", id);
                    it.putExtra("title1", "借款协议范本");
                    startActivity(it);
                }
            }
        });
        dianziqianzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//电子签章
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {
                    Intent it = new Intent(XiangMuXiangQing.this, WebView1.class);
                    startActivity(it);
//                    it.putExtra("url", "yxbApp/promisebook.do");
//                    it.putExtra("title1", "个人电子签章授权委托书");
                }
            }
        });
        zijinlaiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
//                    ToastUtils.showToast(XiangMuXiangQing.this, "请先登入");
                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                    startActivity(it);

                } else {
                    Intent it = new Intent(XiangMuXiangQing.this, WebView1.class);
                    startActivity(it);
                }
            }
        });


    }


    private void getjinge() {
        if (Integer.valueOf(jinge.getText().toString().trim()) == 0) {
            jianhao.setEnabled(false);

        } else {
            jianhao.setEnabled(true);
            jianhao.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (jinge.getText().toString().equals("")) {
                        jinge.setText("0");
                    } else {
                        if (Integer.valueOf(jinge.getText().toString().trim()) <= 100) {//金额不能小于100
                            ToastUtils.showToast(XiangMuXiangQing.this, "起投金额不能小于100元");
                        } else {
                            jinge.setText(Integer.valueOf(jinge.getText().toString()) - 100 + "");//减后的金额

                        }
                    }
                }
            });

            jiahao.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (jinge.getText().toString().equals("")) {
                        jinge.setText("100");
                    } else {
                        jinge.setText(Integer.parseInt(jinge.getText().toString().trim()) + 100 + "");//加后的金额
                    }
                }
            });

        }


        jinge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MyLog.e("CharSequence", "" + s);

//                int myjine = Integer.parseInt(jinge.getText().toString());
//                if (myjine>=MAX){
//
//                    jiahao.setEnabled(false);
//                    jianhao.setEnabled(false);
//                    jinge.setFocusable(false);
//                }else {
//                    jiahao.setEnabled(true);
//                    jianhao.setEnabled(true);
//                    jinge.setFocusable(true);
//                }


//                if (myjine.equals("")) {
//                    yujishouyi.setText("0.00");
//                } else {
//                    double b1 = Double.parseDouble(myjine);
//                    MyLog.e("s1", "" + b1);
//
//                    //*Integer.valueOf(data.getResult().getRedList1().getDeadlines())
//                    double myshouyi = b1 * (data.getResult().getRedList1().getRan() / 12 * Integer.valueOf(data.getResult().getRedList1().getDeadlines())) / 100;
////                    MyLog.e("myshouyi", "" + myshouyi);
//                    yujishouyi.setText("" + myshouyi);
//                }
                if (jinge.getText().toString().equals("")) {

                } else {
                    int i1 = Integer.parseInt(jinge.getText().toString());
//                    if (i1 == 1000000) {//金额最大值
//                        jinge.setText("1000000");
//                        jiahao.setBackgroundResource(R.drawable.jiahaohuise);//加号变灰
//                    }
                    if (i1 > 1000000) {//金额最大值
                        jinge.setText("1000000");
                        jiahao.setBackgroundResource(R.drawable.jiahaohuise);//加号变灰
                    } else if (i1 == 1000000) {
                        jiahao.setBackgroundResource(R.drawable.jiahaohuise);//加号变灰
                    } else {
                        jiahao.setBackgroundResource(R.drawable.jiahaochegnse);//加号变橙
                    }
                    if (i1 > 100) {
                        jianhao.setBackgroundResource(R.drawable.jianhaochengse);//减号变橙
                    } else {
                        jianhao.setBackgroundResource(R.drawable.jianhaohuise);//减号变灰
                    }


                    if (mHandler.hasMessages(MSG_SEARCH)) {
                        mHandler.removeMessages(MSG_SEARCH);
                    }
                    //如果为空 直接显示搜索历史
                    if (TextUtils.isEmpty(s)) {
                        //showHistory();
                    } else {//否则延迟500ms开始搜索
                        mHandler.sendEmptyMessageDelayed(MSG_SEARCH, 3000); //自动搜索功能 删除
                    }
                    int i = Integer.parseInt(jinge.getText().toString());

                    if (juanjinge.equals("")) {

                    } else {
                        if (i >= Integer.parseInt(juanjinge)) {

                        } else {
                            youhuijuan.setText("未使用");
                            SPUtils.remove(XiangMuXiangQing.this, "juantype");
                            SPUtils.remove(XiangMuXiangQing.this, "juanId");
                            SPUtils.remove(XiangMuXiangQing.this, "juanmake");
                            SPUtils.remove(XiangMuXiangQing.this, "juanjine");
                        }
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });


        jinge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    double floor = Math.floor(Float.parseFloat(jinge.getText().toString()));
                    jinge.setText("" + floor);
                }
            }
        });


    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (jinge.getText().toString().equals("")) {

            } else {
                int i = Integer.parseInt(jinge.getText().toString());
                if (i < 100) {
                    jinge.setText("100");
                } else {
                    int num = i / 100;
                    jinge.setText(num * 100 + "");
                }
            }
        }
    };


    private void getID() {
        xiangmu_fh = findViewById(R.id.xiangmu_fh);
        user_id = (int) SPUtils.get(this, "userId", 0);
        token1 = (String) SPUtils.get(this, "Token1", "");
        loginid = (String) SPUtils.get(this, "Loginid", "");
        id = (String) SPUtils.get(this, "borroFwRandomId", "");
        juan_id = (int) SPUtils.get(this, "juanId", 0);
        juan_type = (int) SPUtils.get(this, "juantype", 0);
        juanmake = (String) SPUtils.get(this, "juanmake", "");
        juanjinge = (String) SPUtils.get(this, "juanjine", "");
        detailedinformation = findViewById(R.id.detailedinformation);
        xiala_view = findViewById(R.id.xiale_view);
        xq_leve = findViewById(R.id.xq_leve);
        leve_clock = findViewById(R.id.leve_clock);
        MyLog.e("userid", "id:" + user_id);
//        yujishouyi = findViewById(R.id.yujishouyi);
        jianhao = findViewById(R.id.bt_jianhao);
        jinge = findViewById(R.id.et_jinge);
        xq_chongzhi = findViewById(R.id.xq_chongzhi);
        bt_lijichujie = findViewById(R.id.bt_lijichujie);
        jiahao = findViewById(R.id.bt_jiahao);
        wangdaitishishu = findViewById(R.id.wangdaitishishu);
        keyangyue = findViewById(R.id.keyangyue);//可用余额
        fangshi = findViewById(R.id.fangshi);//还款方式
        xiangqing_zonge = findViewById(R.id.xiangqing_zonge);//总额
        xiangqing_qixian = findViewById(R.id.xiangqing_qixian);//还款期限
        shengyuchujie = findViewById(R.id.shengyuchujie);//剩余出借金额
        xiangqing_fujia_bi = findViewById(R.id.xiangqing_fujia_bi);//"%"
        xiangqing_fujia_lilv = findViewById(R.id.xiangqing_fujia_lilv);//附加利率
        xiangqing_jiaohao = findViewById(R.id.xiangqing_jiaohao);//"+"
        xiangqing_lilv = findViewById(R.id.xiangqing_lilv);//利率
        jikuanxieyi = findViewById(R.id.jiekuanxieyi);//借款协议
        zijinlaiyuan = findViewById(R.id.zijinlaiyuan);//资金来源
        dianziqianzhang = findViewById(R.id.dianziqianzhang);//电子签章
        youhuijuan = findViewById(R.id.youhuijuan);//优惠券
        cb_fuxuankuang = findViewById(R.id.cb_fuxuankuang);//复选框
        huankuanlan = findViewById(R.id.huankuanglan);//还款方式栏目框
        kaishi_time = findViewById(R.id.kaishi_time);//开始投标时间
        jiezhi_time = findViewById(R.id.jiezhi_time);//截止投标时间
        biaotype = findViewById(R.id.biaotype);//标的类型
    }


    private void getHttps() {

        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId", id);
//            SPUtils.put(this, "borroFwRandomId", id);
            SPUtils.put(this, "mtype1", mType);
            js_request.put("userId", user_id);
            MyLog.e("TAG", "id" + user_id);
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
            MyLog.e("立即出借首页", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/couponinformation.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(XiangMuXiangQing.this, "网络错误，请稍后再试");

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("8888Gson:", "" + result);

                        data = new Gson().fromJson(result, XiangMuXiangQing_Gson.class);
                        promptDialog.dismiss();
                        detailedinformation.setVisibility(View.VISIBLE);
                        String borrowSafelevel = data.getResult().getRedList1().getBorrowSafelevel();
                        switch (borrowSafelevel) {
                            case "1":
                                xq_leve.setText("AAA");
                                break;
                            case "2":
                                xq_leve.setText("AA");
                            case "3":
                                xq_leve.setText("A");
                                break;
                            case "4":
                                xq_leve.setText("BBB");
                                break;
                            case "5":
                                xq_leve.setText("BB");
                                break;
                            case "6":
                                xq_leve.setText("B");
                                break;
                            case "7":
                                xq_leve.setText("CCC");
                                break;
                            case "8":
                                xq_leve.setText("CC");
                                break;
                            case "9":
                                xq_leve.setText("C");
                                break;
                            default:
                                break;
                        }

                        shengyuchujie.setText(data.getResult().getRedList1().getSurplus());

                        String borrowSum = data.getResult().getRedList1().getBorrowSum();
                        if (borrowSum.indexOf("元") != -1) {
                            xiangqing_zonge.setText(borrowSum);
                        } else {
                            String replace = borrowSum.replace(".00", "");
                            if (replace.indexOf("0") != -1) {
                                if (replace.indexOf(".") != -1) {
                                    String replace1 = replace.replace("0", "");
                                    xiangqing_zonge.setText(replace1);
                                } else {
                                    xiangqing_zonge.setText(replace);
                                }
                            } else {
                                xiangqing_zonge.setText(replace);
                            }
                        }
                        xiangqing_qixian.setText(data.getResult().getRedList1().getDeadline());
                        fangshi.setText(data.getResult().getRedList1().getPaymentMode());//还款方式
                        xiangqing_lilv.setText(data.getResult().getRedList1().getRanaa() + "");
                        kaishi_time.setText(data.getResult().getRedList1().getAbleTenderDates());//开始时间
                        jiezhi_time.setText(data.getResult().getRedList1().getEndTenderDate());//截止时间
                        String mortgageType = data.getResult().getRedList1().getMortgageType();//标的类型
                        String mortgageNature = data.getResult().getRedList1().getMortgageNature();//抵押/质押
                        if (mortgageType.equals("1")) {
                            if (mortgageNature.equals("2")) {
                                biaotype.setText("房产质押");
                            } else {
                                biaotype.setText("房产抵押");
                            }
                        } else {
                            if (mortgageNature.equals("2")) {
                                biaotype.setText("车辆质押");
                            } else {
                                biaotype.setText("车辆抵押");
                            }
                        }
                        int borrowStatus = data.getResult().getRedList1().getBorrowStatus();
                        String s = String.valueOf(user_id);
                        if (s.equals("0")) {//还款方式栏目框
                            huankuanlan.setVisibility(View.GONE);
                        } else {
                            huankuanlan.setVisibility(View.VISIBLE);
                        }
                        switch (borrowStatus) {
                            case 2://招标中（出借）
                                if (String.valueOf(data.getResult().getRedList1().getTimeFlag()).equals("1")) {//预热
                                    bt_lijichujie.setText(data.getResult().getRedList1().getAbleTenderDate());
                                    bt_lijichujie.setTextColor(Color.parseColor("#fe6623"));
                                    bt_lijichujie.setBackgroundResource(R.drawable.bt_biankuang);
                                    bt_lijichujie.setEnabled(false);

                                } else {//可出借
                                    bt_lijichujie.setText(data.getResult().getRedList1().getBorrowStatusStr());
                                    bt_lijichujie.setBackgroundResource(R.drawable.bt_shape);
                                    bt_lijichujie.setEnabled(true);
                                }
                                break;
                            case 3://已满标
                                bt_lijichujie.setText(data.getResult().getRedList1().getBorrowStatusStr());
                                bt_lijichujie.setTextColor(Color.parseColor("#ffffff"));
                                bt_lijichujie.setBackgroundResource(R.drawable.yimanbiao);
                                bt_lijichujie.setEnabled(false);
                                break;
                            case 4://回款中
                                bt_lijichujie.setText(data.getResult().getRedList1().getBorrowStatusStr());
                                bt_lijichujie.setTextColor(Color.parseColor("#ffffff"));
                                bt_lijichujie.setBackgroundResource(R.drawable.bt_huise);
                                bt_lijichujie.setEnabled(false);
                                break;
                            case 5://回款完成
                                bt_lijichujie.setText(data.getResult().getRedList1().getBorrowStatusStr());
                                bt_lijichujie.setTextColor(Color.parseColor("#ffffff"));
                                bt_lijichujie.setBackgroundResource(R.drawable.bt_shenhuise);
                                bt_lijichujie.setEnabled(false);
                                break;

                        }


//                        String s = String.valueOf(user_id);
                        if (s.equals("0")) {
                            keyangyue.setText("0.00");
                        } else {
                            keyangyue.setText(data.getResult().getUserMap().getUsableSum());
                        }
                        if (data.getResult().getRedList1().getRans() <= 0) {
                            xiangqing_fujia_lilv.setText("");
                            xiangqing_jiaohao.setText("");
                            xiangqing_fujia_bi.setText("");

                        } else {
                            xiangqing_fujia_lilv.setText(data.getResult().getRedList1().getRans() + "");
                            xiangqing_jiaohao.setText("+");
                            xiangqing_fujia_bi.setText("%");
                        }

//                        if (s.equals("0")) {
//                            youhuijuan.setText("暂无可用优惠券");
//                        } else {
                        MyLog.e("我的--》juan_type", "" + juan_type);
                        MyLog.e("我的--》juan_id", "" + juan_id);
                        MyLog.e("我的--》juanmake", "" + juanmake);
                        String s1 = String.valueOf(user_id);
                        if (s1.equals("0")) {
                            youhuijuan.setText("登录后查看");
                            youhuijuan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent it = new Intent(XiangMuXiangQing.this, WoDe_DengRu.class);
                                    startActivity(it);
//                                    finish();
                                }
                            });
                        } else {
                            if (data.getResult().getJuan() != null) {
                                if (data.getResult().getJuan().size() != 0) {
                                    youhuijuan.setText("未使用");
                                    youhuijuan.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent ti = new Intent(XiangMuXiangQing.this, XiangMuJuan.class);
                                            ti.putExtra("juan", data.getResult());
                                            SPUtils.put(XiangMuXiangQing.this, "mymoney", jinge.getText().toString());
                                            startActivity(ti);

                                        }
                                    });
                                    if (juan_type != 0) {
                                        youhuijuan.setText(juanmake);
                                    } else {
                                        youhuijuan.setText("未使用");
                                    }

//                                youhuijuan.setText(juanmake);
                                } else {
                                    youhuijuan.setText("暂无可用优惠券");
                                }
                            } else {
                                youhuijuan.setText("暂无可用优惠券");
                            }

//                        }
                        }
                    }
                });


    }

    private void getchujieHttp() {
        promptDialog = new PromptDialog(this);
        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
//            MyLog.e("立即出借Userid:", "" + user_id);
            js_request.put("borrowRandomId", id);
//            MyLog.e("立即出借borrowRandomId:", "" + id);
            js_request.put("investAmount", jinge.getText().toString());
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
//            MyLog.e("TAG", "id" + user_id);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
//            MyLog.e("TAG", ">>>>base加密11111!!--" + base1);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
//            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("LijiChujie_gsonOne加密", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/particulars.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(XiangMuXiangQing.this, "网络异常，请稍后再试");
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("立即出借GSon", response);
                        LJCJONR_GSon data = new Gson().fromJson(response, LJCJONR_GSon.class);
//                        if (data.getMessage().equals()){}
                        if (data.getMessage().equals("用户未登录。")) {
                            promptDialog.dismiss();
                            ToastUtils.showToast(XiangMuXiangQing.this, "请现登入");
                        } else {
                            int aaa = data.getMsg().getAaa();
                            switch (aaa) {
                                case 2:
                                    promptDialog.dismiss();
                                    showpopwindow();
                                    break;
                                case 3:
                                    promptDialog.dismiss();
                                    cGpop();
                                    break;
                                case 5:
                                    promptDialog.dismiss();
                                    AlertDialog dialog2 = new AlertDialog.Builder(XiangMuXiangQing.this)
                                            .setTitle("提示")
                                            .setMessage("您的余额不足请充值")
                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    promptDialog.dismiss();
                                                    getChongzhiHttp();
                                                }
                                            })
                                            .create();
                                    dialog2.setCanceledOnTouchOutside(false);
                                    dialog2.show();
                                    break;
                                case 4:
                                    promptDialog.dismiss();
                                    fxpop();
                                    break;
                                case 0:

                                    if (cb_fuxuankuang.isChecked()) {
                                        getchujieHttpTwo();
                                    } else {
                                        promptDialog.dismiss();
                                        ToastUtils.showToast(XiangMuXiangQing.this, "请先阅读并勾选协议");
                                    }
                                    break;
                                case 9://小于100
                                    promptDialog.dismiss();
                                    ToastUtils.showToast(XiangMuXiangQing.this, "投资金额不能小于100");
                                    break;
                                case 8://不是100的倍数
                                    promptDialog.dismiss();
                                    ToastUtils.showToast(XiangMuXiangQing.this, "投资金额只能是100的倍数");
                                    break;
                                case 11://稳健
                                    promptDialog.dismiss();
                                    String sd = "您当前风险评测等级为稳健型，根据评测结果您只能出借AAA、AA、A、BBB、BB、B级风险的借款项目。继续出借本项目表明您已充分知晓，理解和接受该项目的风险，并愿意自行承担相关。";

                                    tb_fcianpop("稳健型", sd, 11);

                                    break;
                                case 10://保守
                                    promptDialog.dismiss();
                                    String sd1 = "您当前风险评测等级为 保守型，根据评测结果您只能出借AAA、AA、A级风险的借款项目。继续出借本项目表明您已充分知晓，理解和接受该项目的风险，并愿意自行承担相关风险。";

                                    tb_fcianpop("保守型", sd1, 10);
                                    break;
                            }
                        }
                    }
                });


    }

    private void tb_fcianpop(String tit, String msg, int i) {
        final View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        final View popView = View.inflate(this, R.layout.chejie_pop, null);
        TextView tilt = popView.findViewById(R.id.tilt);//等级
        TextView fxian_msg = popView.findViewById(R.id.fxian_msg);//信息
        Button pc_agen = popView.findViewById(R.id.pc_agen);//继续出借
        TextView fxian_close = popView.findViewById(R.id.fxian_close);//再想想
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置同意在外点击消失
        tilt.setText(tit);//等级
        if (i == 11) {
            SpannableString spannableString = KeywordsUtil.matcherSearchTitle(Color.parseColor("#F36934"), msg, "AAA、AA、A、BBB、BB、B");
            fxian_msg.setText(spannableString);//信息
        } else {
            SpannableString spannableString = KeywordsUtil.matcherSearchTitle(Color.parseColor("#F36934"), msg, "AAA、AA、A");
            fxian_msg.setText(spannableString);//信息
        }
        pc_agen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//继续出借
                getchujieHttpTwo();
                popWindow.dismiss();
                promptDialog = new PromptDialog(XiangMuXiangQing.this);
                promptDialog.showLoading("");
//                popWindow.dismiss();
            }
        });
        fxian_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//再想想
                popWindow.dismiss();
            }
        });
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

    }


    private void fxpop() {
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        final View popView = View.inflate(this, R.layout.fangxianpingce_pop, null);
        TextView txt_pc = popView.findViewById(R.id.txt_pc);
        ImageView pc_guanp = popView.findViewById(R.id.pc_guanbi);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        txt_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangMuXiangQing.this, FengXianPingCe.class);
                startActivity(intent);
                popWindow.dismiss();
            }

        });
        pc_guanp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
    
    private void cGpop() {//存管pop
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.yinhangcunguan_pop, null);
        TextView cg_pop = popView.findViewById(R.id.ch_pop);
        ImageView pop_cg_X = popView.findViewById(R.id.pop_cg_X);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        cg_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getchHTTP();

            }
        });
        pop_cg_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

    }

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", String.valueOf(user_id));
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());

            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("TAG", ">>>>加密11111!!--" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/accountReg.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("存管GSON:", "" + result);
                        CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                        String html = data.getResult().getHtml();
                        Intent it = new Intent(XiangMuXiangQing.this, YinHangCunGuan.class);
                        it.putExtra("HTML", html);
                        MyLog.e("我的页面银行存管HTML:", "" + it);
                        startActivity(it);

                        MyLog.e("wangy", "" + html);
                    }
                });

    }

    @SuppressLint("WrongConstant")
    private void showpopwindow() {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(this).inflate(R.layout.shimingrenzheng_pop, null);
        final EditText pop_name = view.findViewById(R.id.pop_myname);
        final EditText pop_idcard = view.findViewById(R.id.pop_myidcard);
        ImageView guanbil = view.findViewById(R.id.guanbil);
        Button popb_t = view.findViewById(R.id.pop_myb_t);
        pop_name.addTextChangedListener(new MaxLengthWatcher(6, pop_name));

        pop_name.addTextChangedListener(new TextWatcher() {
            String str;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strs = pop_name.getText().toString();
                str = stringFilter1(strs.toString());
                if (!strs.equals(str)) {
                    pop_name.setText(str);
                    pop_name.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//只能是汉字
        pop_idcard.addTextChangedListener(new MaxLengthWatcher(18, pop_idcard));
        pop_idcard.setTransformationMethod(new A2bigA());//小写转大写
        popview1 = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popview1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popview1.setFocusable(true);
        // 设置点击其他地方就消失
        popview1.setOutsideTouchable(true);
        popb_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popname = pop_name.getText().toString();
                popidcard = pop_idcard.getText().toString();

                getshimingHTTp();
//                Shiming_utile

            }

        });
        popview1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        guanbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popview1.dismiss();
            }
        });
        popview1.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popview1.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popview1.showAtLocation(parent, Gravity.BOTTOM
                | Gravity.CENTER_VERTICAL, 0, 0);

    }

    private void getshimingHTTp() {
        promptDialog.showLoading("");
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        promptDialog.dismiss();
                        ToastUtils.showToast(XiangMuXiangQing.this, "网络连接失败，请稍后再试");
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
//                        ToastUtils.showToast(XiangMuXiangQing.this, "" + message);
                        String jieguo = data.getState().toString();
                        if (message.equals("可以认证")) {
                            getsmHttp();
                        } else {
                            promptDialog.dismiss();
                            if (message.equals("认证失败！您今日的认证次数已达上限，请明天再进行认证！")) {
                                AlertDialog dialog1 = new AlertDialog.Builder(XiangMuXiangQing.this)
                                        .setTitle("提示")
                                        .setMessage("认证失败！您今日的认证次数已达上限，请明天再进行认证！")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                            }
                                        })
                                        .create();
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1.show();
                            } else {
                                ToastUtils.showToast(XiangMuXiangQing.this, message);
                            }
                        }
                    }
                });
    }

    private void getsmHttp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("idNo", popidcard);
            js_request.put("realName", popname);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/userAuth.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(XiangMuXiangQing.this, "网络连接失败，请稍后再试");
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("实名认证的GSOn", "" + result);
                        ShiMingRenZengJieGuo_gson data = new Gson().fromJson(result, ShiMingRenZengJieGuo_gson.class);
                        String message = data.getMessage().toString();
//                Toast.makeText(ShiMingrenzheng.this, ""+message, Toast.LENGTH_SHORT).show();
                        String zhuangtai = data.getState();
                        MyLog.e("实名认证", zhuangtai);
                        if (zhuangtai.equals("success")) {   //实名认证成功
//                    String realName = data.getResult().getRealName();
//                    String idNo = data.getResult().getIdNo();
                            promptDialog.dismiss();
                            popview1.dismiss();
                            showshimingokpop();//实名认证成功，弹成功pop

//                            Intent intent = new Intent(XiangMuXiangQing.this, ShiMingRenZhengKO.class);
//                            startActivity(intent);
                        } else {
//                        jinggao.setVisibility(View.VISIBLE);
//                        jinggao.setText(message);
                            promptDialog.dismiss();
                            ToastUtils.showToast(XiangMuXiangQing.this, "" + message);
                        }
                    }
                });


    }

    @SuppressLint("WrongConstant")
    private void showshimingokpop() {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(this).inflate(R.layout.shimingrenzhengchenggong_pop, null);
        ImageView smcg_cg_guanbil = view.findViewById(R.id.smcg_cg_guanbil);//关闭按钮
        Button pop_cg_kt = view.findViewById(R.id.pop_cg_kt);//开通存管

        popview2 = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popview2.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popview2.setFocusable(false);
        // 设置点击其他地方就消失
        popview2.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        smcg_cg_guanbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popview2.dismiss();
            }
        });
        pop_cg_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getchHTTP();//开通存管
                popview2.dismiss();
            }
        });
        popview2.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popview2.showAtLocation(parent, Gravity.BOTTOM
                | Gravity.CENTER_VERTICAL, 0, 0);
    }


    private void getchujieHttpTwo() {
//        promptDialog = new PromptDialog(this);
//        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            MyLog.e("立即出借Userid:", "" + user_id);
            js_request.put("borrowRandomId", id);
            MyLog.e("立即出借borrowRandomId:", "" + id);
            js_request.put("investAmount", jinge.getText().toString());
            js_request.put("activitype", juan_type);
            js_request.put("activityId", juan_id);
            js_request.put("token", token1);
            js_request.put("loginId", loginid);
//Yixinjinrong201806
            MyLog.e("TAG", "id" + js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            MyLog.e("TAGjs_request", "" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            MyLog.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            MyLog.e("立即出接two", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/Immediately.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast(XiangMuXiangQing.this, "网络异常，请稍后再试");
                        promptDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("立即出借GSon", result);
                        LJCJtwo_gson data = new Gson().fromJson(result, LJCJtwo_gson.class);
                        String msg = data.getResult().getMsg();

                        if (msg.equals("1")) {
                            String toumoney = data.getResult().getInvestAmount();
                            String toutime = data.getResult().getInvestDate();
                            Intent it = new Intent(XiangMuXiangQing.this, ChuJie_OK.class);
                            it.putExtra("money", toumoney);
                            it.putExtra("time", toutime);
                            it.putExtra("mortgageType", mType);
                            startActivity(it);
                            SPUtils.remove(XiangMuXiangQing.this, "juantype");
                            SPUtils.remove(XiangMuXiangQing.this, "juanId");
                            SPUtils.remove(XiangMuXiangQing.this, "juanmake");
                            SPUtils.remove(XiangMuXiangQing.this, "juanjine");
                            bt_lijichujie.setEnabled(true);
                            promptDialog.dismiss();
                            finish();
                        } else {
                            ToastUtils.showToast(XiangMuXiangQing.this, "" + msg);
                            bt_lijichujie.setEnabled(true);
                            promptDialog.dismiss();
                        }
                    }
                });

    }

    private void initView() {
        mChuJieJiLu = new ChuJieJiLu();
        mHuiKuanJiHua = new HuiKuanJiHua();
        mJieKuanZiLiao = new JieKuanZiLiao();
        mXiangMuJingDu = new XiangMuJingDu();
        mXiangMuXinXi = new XiangMuXinXi();
        mxiangmugenzong = new XiangmuGenZong();
        list_fragment.add(mXiangMuXinXi);
        list_fragment.add(mHuiKuanJiHua);
        list_fragment.add(mXiangMuJingDu);
        list_fragment.add(mxiangmugenzong);
        list_fragment.add(mJieKuanZiLiao);
        list_fragment.add(mChuJieJiLu);

        list_title.add("项目信息");
        list_title.add("回款计划");
        list_title.add("项目进度");
        list_title.add("项目跟踪");
        list_title.add("借款资料");
        list_title.add("出借记录");

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
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
    protected void onResume() {
        super.onResume();

        juan_id = (int) SPUtils.get(this, "juanId", 0);
        juan_type = (int) SPUtils.get(this, "juantype", 0);
        juanmake = (String) SPUtils.get(this, "juanmake", "");
        juanjinge = (String) SPUtils.get(this, "juanjine", "");
        MyLog.e("我的--》juan_type", "" + juan_type);
        MyLog.e("我的--》juan_id", "" + juan_id);
        MyLog.e("我的--》juanmake", "" + juanmake);
        MyLog.e("我的--》juanjine", "" + juanjinge);
//            youhuijuan.setText(juanmake);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        user_id = (int) SPUtils.get(this, "userId", 0);
        token1 = (String) SPUtils.get(this, "Token1", "");
        loginid = (String) SPUtils.get(this, "Loginid", "");
        onfinsh = (String) SPUtils.get(this, "finsh", "");
        book_checked = (String) SPUtils.get(this, "checked", "");
        if (book_checked != null && book_checked.equals("yes")) {
//            cb_fuxuankuang.isChecked();
            cb_fuxuankuang.setChecked(true);
        }
        if (onfinsh != null && onfinsh.equals("nofinsh")) {

        } else {
            getHttps();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtils.remove(XiangMuXiangQing.this, "juantype");
        SPUtils.remove(XiangMuXiangQing.this, "juanId");
        SPUtils.remove(XiangMuXiangQing.this, "juanmake");
        SPUtils.remove(XiangMuXiangQing.this, "juanjine");
        SPUtils.remove(XiangMuXiangQing.this, "checked");
        SPUtils.remove(XiangMuXiangQing.this, "finsh");
        ImmersionBar.with(this).destroy(); //防止内存泄漏
    }
}