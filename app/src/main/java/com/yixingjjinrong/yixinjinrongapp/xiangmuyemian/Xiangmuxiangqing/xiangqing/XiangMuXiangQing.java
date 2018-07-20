package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.LJCJONR_GSon;
import com.yixingjjinrong.yixinjinrongapp.gsondata.LJCJtwo_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXiangQing_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.ChuJie_OK;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WandaiTishishu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.tishi_book.WebView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.adapter.SimpleFragmentPagerAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.ChuJieJiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.HuiKuanJiHua;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.JieKuanZiLiao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJingDu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuJuan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment.XiangMuXinXi;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;
import java.util.ArrayList;

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
    private Button jianhao, jiahao, xq_chongzhi;//加减用户的输入金额
    private EditText jinge;//用户输入的金额
    private TextView wangdaitishishu, jikuanxieyi, dianziqianzhang, zijinlaiyuan, xiangqing_lilv, xiangqing_jiaohao, xiangqing_fujia_lilv, xiangqing_fujia_bi, shengyuchujie, xiangqing_qixian, xiangqing_zonge, fangshi, keyangyue;
    private String id;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private TextView youhuijuan;//优惠券
    private int user_id;
    private String mType;
    private XiangMuXiangQing_Gson data;
    private String token1;
    private Button bt_lijichujie;
    private int juan_id;
    private int juan_type;
    private CheckBox cb_fuxuankuang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        EventBus.getDefault().register(this);//注册
        setContentView(R.layout.xiaomuxiangqing);

        getID();//获取资源ID
        initView();
        getjinge();//获取输入金额

        Intent it = getIntent();
        id = it.getStringExtra("xiangmu_id");
        Log.e("立即出借borrid", "" + id);
        mType = it.getStringExtra("mortgageType");

        getWebview();//各种提示书

        getHttps();


    }

    private void getWebview() {
        wangdaitishishu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//网贷提示书
                Intent it = new Intent(XiangMuXiangQing.this, WebView.class);
                it.putExtra("url", "yxb_mobile/yxbApp/agreement.do");
                it.putExtra("title1", "网络借贷风险和禁止行为提示书");
                startActivity(it);
            }
        });
        jikuanxieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//借款协议
                Intent it = new Intent(XiangMuXiangQing.this, WandaiTishishu.class);
                it.putExtra("url", "yxb_mobile/yxbApp/borrowmoney.do?");
                it.putExtra("b_id", id);
                it.putExtra("title1", "借款协议范本");
                startActivity(it);
            }
        });
        dianziqianzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//电子签章
                Intent it = new Intent(XiangMuXiangQing.this, WebView.class);
                it.putExtra("url", "yxb_mobile/yxbApp/promisebook.do");
                it.putExtra("title1", "个人电子签章授权委托书");
                startActivity(it);
            }
        });
        zijinlaiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(XiangMuXiangQing.this, WebView.class);
                it.putExtra("url", "yxb_mobile/yxbApp/Promptbook.do ");
                it.putExtra("title1", "资金来源合法承诺书");
                startActivity(it);
            }
        });


    }


    private void getjinge() {
        if (Integer.valueOf(jinge.getText().toString().trim()) == 0) {
            jianhao.setEnabled(false);
            jiahao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jinge.setText("100");
                }
            });
        } else {
            jianhao.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(jinge.getText().toString().trim()) <= 100) {//金额不能小于100
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
                    jinge.setText(Integer.valueOf(jinge.getText().toString().trim()) + 100 + "");//加后的金额
                }
            });

        }

    }


    private void getID() {
        user_id = (int) SPUtils.get(this, "userId", 0);
        token1 = (String) SPUtils.get(this, "Token1", "");

        Log.e("我的--》项目详情Token", "" + token1);
//        token1 = (int) SPUtils.get(this, "Token1", 0);

//        juan = (String) SPUtils.get(this, "juan", 0);
        Log.e("userid", "id:" + user_id);

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

    }


    private void getHttps() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("borrowRandomId", id);
            SPUtils.put(this, "borroFwRandomId", id);
            SPUtils.put(this, "mtype1", mType);
            js_request.put("userId", user_id);
            Log.e("TAG", "id" + user_id);
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
            Log.e("立即出借首页", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/couponinformation.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("8888Gson:", "" + result);
                data = new Gson().fromJson(result, XiangMuXiangQing_Gson.class);
                xiangqing_zonge.setText(data.getResult().getRedList1().getBorrowSum());
                xiangqing_qixian.setText(data.getResult().getRedList1().getDeadline());
                fangshi.setText(data.getResult().getRedList1().getPaymentMode());
                xiangqing_lilv.setText(data.getResult().getRedList1().getRanaa() + "");
                int borrowStatus = data.getResult().getRedList1().getBorrowStatus();
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
                        bt_lijichujie.setBackgroundResource(R.drawable.bt_huise);
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


                shengyuchujie.setText(data.getResult().getRedList1().getSurplus());
                keyangyue.setText(data.getResult().getUserMap().getUsableSum());
                xq_chongzhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent itcz = new Intent(XiangMuXiangQing.this, ChongZhq.class);
                        itcz.putExtra("keyong2", data.getResult().getUserMap().getUsableSum());
                        startActivity(itcz);
                    }
                });
                if (data.getResult().getRedList1().getRans() == 0) {
                    xiangqing_fujia_lilv.setText("");
                    xiangqing_jiaohao.setText("");
                    xiangqing_fujia_bi.setText("");

                } else {
                    xiangqing_fujia_lilv.setText(data.getResult().getRedList1().getRans() + "");
                    xiangqing_jiaohao.setText("+");
                    xiangqing_fujia_bi.setText("%");
                }
                xq_chongzhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((Integer) user_id == null) {
                            Toast.makeText(XiangMuXiangQing.this, "请先登入", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent itcz = new Intent(XiangMuXiangQing.this, ChongZhq.class);
                            itcz.putExtra("keyong2", data.getResult().getRedList1().getBorrowSum());
                            startActivity(itcz);
                        }
                    }
                });


                cb_fuxuankuang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                        if (isChecked) {
                            bt_lijichujie.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isChecked) {
                                        getchujieHttp();
                                    } else {
                                        Toast.makeText(XiangMuXiangQing.this, "请先阅读", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    }
                });

                if (data.getResult().getJuan() != null) {
                    youhuijuan.setText("未使用");
                    youhuijuan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent ti = new Intent(XiangMuXiangQing.this, XiangMuJuan.class);
                            startActivity(ti);
                        }
                    });
                } else {
                    youhuijuan.setText("暂无可用优惠券");
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

    private void getchujieHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            Log.e("立即出借Userid:", "" + user_id);
            js_request.put("borrowRandomId", id);
            Log.e("立即出借borrowRandomId:", "" + id);
            js_request.put("investAmount", jinge.getText().toString());
            Log.e("TAG", "id" + user_id);
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
            Log.e("LijiChujie_gsonOne加密", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/particulars.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("立即出借GSon", result);
                LJCJONR_GSon data = new Gson().fromJson(result, LJCJONR_GSon.class);
                int aaa = data.getMsg().getAaa();

                switch (aaa) {
                    case 2:
                        Toast.makeText(XiangMuXiangQing.this, "未实名认证", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog = new AlertDialog.Builder(XiangMuXiangQing.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未实名认证，是否认证")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getshimingHTTp();
                                        Toast.makeText(XiangMuXiangQing.this, "请实名", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        dialog.show();
                        break;
                    case 3:
                        Toast.makeText(XiangMuXiangQing.this, "未开通银行存管", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog1 = new AlertDialog.Builder(XiangMuXiangQing.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未开通银行存管，是否开通")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(XiangMuXiangQing.this, "请开通", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        dialog1.show();
                        break;
                    case 5:
                        Toast.makeText(XiangMuXiangQing.this, "未签约", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog2 = new AlertDialog.Builder(XiangMuXiangQing.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未未签约，是否签约")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(XiangMuXiangQing.this, "请签约", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        dialog2.show();
                        break;
                    case 4:
                        Toast.makeText(XiangMuXiangQing.this, "未风险评测", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog3 = new AlertDialog.Builder(XiangMuXiangQing.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                                .setTitle("提示")
                                .setMessage("您还未风险评测，是否风险评测")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(XiangMuXiangQing.this, "请风险评测", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        dialog3.show();
                        break;
                    case 0:
                        Toast.makeText(XiangMuXiangQing.this, "出借第一步【过】", Toast.LENGTH_SHORT).show();
                        getchujieHttpTwo();

                        break;
                    case 11:
                        shouAlertDialog("当前项目风险等级高于您的风险评测等级，请问确认出借吗？");
                        Toast.makeText(XiangMuXiangQing.this, "当前项目风险等级高于您的风险评测等级，请问确认出借吗？", Toast.LENGTH_SHORT).show();
                        break;
                    case 10:
                        shouAlertDialog("当前项目风险等级高于您的风险评测等级，请问确认出借吗？");
                        Toast.makeText(XiangMuXiangQing.this, "当前项目风险等级高于您的风险评测等级，请问确认出借吗？", Toast.LENGTH_SHORT).show();
                        break;
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

    private void getshimingHTTp() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
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

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/queryUserAuthInfo.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("是否可实名GSON：", result);
                ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                String message = data.getMessage().toString();
                Toast.makeText(XiangMuXiangQing.this, "" + message, Toast.LENGTH_SHORT).show();
                String jieguo = data.getState().toString();
                if (jieguo.equals("success")) {
//                    Intent it = new Intent(XiangMuXiangQing.this, ShiMingrenzheng.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("user_ird", user_id);
//                    it.putExtras(bundle);
//                    startActivity(it);
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

    private void shouAlertDialog(String msj) {
        AlertDialog alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle("提示")
                .setMessage(msj)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getchujieHttpTwo();
                    }
                })
                .create();
        alertDialog.show();

    }

    private void getchujieHttpTwo() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {

            js_request.put("userId", user_id);
            Log.e("立即出借Userid:", "" + user_id);
            js_request.put("borrowRandomId", id);
            Log.e("立即出借borrowRandomId:", "" + id);
            js_request.put("investAmount", jinge.getText().toString());
            js_request.put("activitype", juan_type);
            js_request.put("activityId", juan_id);

            Log.e("TAG", "id" + js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e("TAGjs_request", "" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("立即出接two", "" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/Immediately.do");
        params.setAsJsonContent(true);
        params.setCacheMaxAge(1000 * 70);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("立即出借GSon", result);
                LJCJtwo_gson data = new Gson().fromJson(result, LJCJtwo_gson.class);
                String msg = data.getResult().getMsg();
                if (msg.equals("1")) {
                    Toast.makeText(XiangMuXiangQing.this, "我擦", Toast.LENGTH_SHORT).show();
                    String toumoney = data.getResult().getInvestAmount();
                    String toutime = data.getResult().getInvestDate();
                    Intent it = new Intent(XiangMuXiangQing.this, ChuJie_OK.class);
                    it.putExtra("money", toumoney);
                    it.putExtra("time", toutime);
                    startActivity(it);

                } else {
                    Toast.makeText(XiangMuXiangQing.this, "" + msg, Toast.LENGTH_SHORT).show();
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

    }
}
