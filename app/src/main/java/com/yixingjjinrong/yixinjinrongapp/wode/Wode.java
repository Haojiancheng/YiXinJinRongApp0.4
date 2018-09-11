package com.yixingjjinrong.yixinjinrongapp.wode;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.authentication.RealName_one;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.User_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.Juan;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.WoDe_ChuJie;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.wodechujie_one.MyChuJie_one;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.zijinliushui.ZiJinliushui;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.My_Content;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.shiming.WoDeShiMing;
import com.yixingjjinrong.yixinjinrongapp.wode.shezhi.WoDe_SheZhi;
import com.yixingjjinrong.yixinjinrongapp.wode.tixian.TiXian;
import com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.WoDe_XiaoXi;
import com.yixingjjinrong.yixinjinrongapp.wode.yaoqing.MyYaoQing;
import com.yixingjjinrong.yixinjinrongapp.wode.zongzichen.ZongziChan;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class Wode extends Fragment {
    private View dengru, weidengru, shimingrenzheng_itme, yinhangcunguan_itme, fengxianpingce_itme;//登入和未登入状态的头部,实名认证，银行存管，风险评测
    private Button yonghudengru, chongzhi, tixian;//用户登入跳转按钮,充值，提现
    private TextView yonghudengji, wozonge, myphone, keyongyue, yaoqing;//等级、总额、可用余额、我的邀请
    private ImageView  wode_xiaoxi, wode_shazhi,  touxiang1;//设置，我的出借,现金券，加息卷
    private int user_id;
    //    private String userToken;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String userToken;
    private boolean isGetData = false;
    private boolean isLogin;
    private String fx;
    private String s_name;
    private String blank;
    private String phone;
    private String keyong;
    private String riskType;
    private ToggleButton wode_togglePwd;
    private String inviteAmount;
    private String totalEarn;
    private String loginid;
    private View lijichujie;
    private View wo_yaoqing;
    private ImageView wd_yj_image;
    private View kefuphone;
    private static final int PERMISSION_REQUESTCODE = 1;
    private View wode_chujie,zjls, xianjin_juan, jiaxi_juan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wode, container, false);
        EventBus.getDefault().register(this);//注册  
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getWodeid();

        yonghudengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//登入页面
                Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                startActivity(intentwode);
            }
        });

        getonclock();
    }

    private void getonclock() {

        shimingrenzheng_itme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    ToastUtils.showToast(getActivity(), "请先登入");
                } else {
                    getshimingHTTp();

//                    RealName_one realName_c = new RealName_one();
//                    realName_c.myrealname(getActivity(), user_id, userToken, loginid);
                }
            }
        });
        yinhangcunguan_itme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    ToastUtils.showToast(getActivity(), "请先登入");
                } else {
                    getchHTTP();
                }
            }
        });
        fengxianpingce_itme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    ToastUtils.showToast(getActivity(), "请先登入");
                } else {
                    Intent intent = new Intent(getActivity(), FengXianPingCe.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_ird", user_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        getoncilink();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myMethod(User_data event) {
        user_id = event.getUser_id();
        userToken = event.getUserToken();
        loginid = event.getOginid();
        Log.e("eventggfdg", "" + loginid);
//        getHttp();
        isLogin = (boolean) SPUtils.get(getActivity(), "isLogin", false);

        SPUtils.put(getActivity(), "isLogin", true);
        if (isLogin == true) {

            getHttp();
        } else {
            SPUtils.remove(getActivity(), "userId");
            SPUtils.remove(getActivity(), "Token1");
            SPUtils.remove(getActivity(), "Loginid");
            getHttp();
        }

    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
//        final String token = userToken;
        try {
            js_request.put("userId", user_id);
            js_request.put("token", userToken);
            js_request.put("loginId", loginid);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            Log.e(">>>>token!!--", "" + js_request);
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>SH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("我的加密：", ":" + canshu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/userIndex.do")
                .content(canshu.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("TAG", ">>>Gson" + result);
                        User_Gson data = new Gson().fromJson(result, User_Gson.class);
                        String message = data.getMessage();

                        if (message.equals("成功了")) {
//                            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                            phone = data.getUserMap().getPhone();
                            myphone.setText(phone);//手机号
                            SPUtils.put(getActivity(), "myphone", phone);
                            //邀请人数
                            inviteAmount = data.getUserMap().getInviteAmount();
                            //邀请总金额
                            totalEarn = data.getUserMap().getTotalEarn();
                            String zhuangtaima = data.getState();
                            //                Log.e("state",zhuangtaima);
                            if (zhuangtaima.equals("success")) {
                                weidengru.setVisibility(View.GONE); //显示布局
                                dengru.setVisibility(View.VISIBLE);//影藏布局
                                wozonge.setText(data.getUserMap().getAccountSum());//总额
                                keyong = data.getUserMap().getUsableAmount();
                                keyongyue.setText(keyong);//可用余额
                                yonghudengji.setText("LV:" + data.getUserMap().getLevelname());//等级
                                yaoqing.setText(data.getUserMap().getInviteAmount());//我的邀请
                                //风险评测
                                fx = data.getUserMap().getRisk();
                                //实名认证
                                s_name = data.getUserMap().getAuth();
                                //银行存管
                                blank = data.getUserMap().getCg();
                                String daijingjuan = data.getUserMap().getDjq();//代金券
                                String jiaxijuan = data.getUserMap().getJxq();//加息劵
                                String my_message = data.getUserMap().getMails();//我的消息
                                String forAmount = data.getUserMap().getForPaySum();//总代收
                                String usableAmount = data.getUserMap().getUsableAmount();//可用余额
                                String freezeAmount = data.getUserMap().getFreezeAmount();//冻结金额
                                String accountSum = data.getUserMap().getAccountSum();//总额
                                SPUtils.put(getActivity(),"forAmount" , forAmount);//总代收
                                SPUtils.put(getActivity(),"usableAmount" , usableAmount);//可用余额
                                SPUtils.put(getActivity(),"freezeAmount" , freezeAmount);//冻结金额
                                SPUtils.put(getActivity(),"accountSum" , accountSum);//冻结金额

                                //测评结果
                                riskType = data.getUserMap().getRiskType();
                                if (s_name.equals("0")) {
                                    shimingrenzheng_itme.setVisibility(View.VISIBLE);
                                    yinhangcunguan_itme.setVisibility(View.GONE);
                                    fengxianpingce_itme.setVisibility(View.GONE);

                                } else {
                                    shimingrenzheng_itme.setVisibility(View.GONE);
                                    if (blank.equals("0")) {
                                        yinhangcunguan_itme.setVisibility(View.VISIBLE);
                                        fengxianpingce_itme.setVisibility(View.GONE);

                                    } else {
                                        shimingrenzheng_itme.setVisibility(View.GONE);
                                        yinhangcunguan_itme.setVisibility(View.GONE);
                                        if (fx.equals("0")) {
                                            fengxianpingce_itme.setVisibility(View.VISIBLE);

                                        } else {
                                            shimingrenzheng_itme.setVisibility(View.GONE);
                                            yinhangcunguan_itme.setVisibility(View.GONE);
                                            fengxianpingce_itme.setVisibility(View.GONE);
                                        }

                                    }
                                }


                            } else {
                            }
//                            SPUtils.put(getActivity(), "userId", user_id);
                            SPUtils.put(getActivity(), "Token1", userToken);

                        } else {
                            weidengru.setVisibility(View.VISIBLE); //显示布局
                            dengru.setVisibility(View.GONE);//影藏布局
                            user_id = 0;
                            SPUtils.remove(getActivity(), "userId");
                            keyongyue.setText("— —");
                            yaoqing.setText("0");

                        }
                    }


                });

    }

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", String.valueOf(user_id));
            js_request.put("token", userToken);
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
            Log.e("TAG", ">>>>加密11111!!--" + canshu);
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
                    public void onResponse(String response, int id) {
                        Log.e("存管GSON:", "" + response);
                        CunGuan_gson data = new Gson().fromJson(response, CunGuan_gson.class);
                        String html = data.getResult().getHtml();
                        Intent it = new Intent(getActivity(), YinHangCunGuan.class);
                        it.putExtra("HTML", html);
                        Log.e("我的页面银行存管HTML:", "" + it);
                        startActivity(it);

                        Log.e("wangy", "" + html);
                    }
                });

    }

    private void getshimingHTTp() {

        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId", user_id);
            js_request.put("token", userToken);
            js_request.put("loginId", loginid);
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
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/queryUserAuthInfo.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.e("是否可实名GSON：", result);
                        ShiFouKeShiMing_gson data = new Gson().fromJson(result, ShiFouKeShiMing_gson.class);
                        String message = data.getMessage().toString();
//                Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                        String jieguo = data.getState().toString();
                        if (jieguo.equals("success")) {
                            Intent it = new Intent(getActivity(), WoDeShiMing.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_ird", user_id);
                            it.putExtras(bundle);
                            startActivity(it);
                        }
                    }
                });

    }

    private void getoncilink() {

        wode_shazhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设置页面
                Intent intentwode_shezhi = new Intent(getActivity(), WoDe_SheZhi.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ird", user_id);
                intentwode_shezhi.putExtras(bundle);
                intentwode_shezhi.putExtra("token", userToken);
                startActivity(intentwode_shezhi);

            }
        });
        wode_chujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                Log.e("出借user_id", "" + s);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent intentwode_chujie = new Intent(getActivity(), MyChuJie_one.class);
                    startActivity(intentwode_chujie);
                    EventBus.getDefault().post(new User_id(user_id));
                }

            }
        });
        xianjin_juan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent it = new Intent(getActivity(), Juan.class);
                    it.putExtra("tage", "1");
                    startActivity(it);
                }
            }
        });
        jiaxi_juan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent it = new Intent(getActivity(), Juan.class);
                    it.putExtra("tage", "0");
                    startActivity(it);
                }
            }
        });
        lijichujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    EventBus.getDefault().post(new LookMore());
                }
            }

        });
        touxiang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//个人中心
//                String fx = data.getUserMap().getRisk();//风险评测
//                String s_name = data.getUserMap().getAuth();//实名认证
//                String blank = data.getUserMap().getCg();//银行存管
                Intent it = new Intent(getActivity(), My_Content.class);
                it.putExtra("fx", fx);
                it.putExtra("s_name", s_name);
                it.putExtra("blank", blank);
                it.putExtra("telephone", phone);
                it.putExtra("riskType", riskType);
                startActivity(it);
            }
        });
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent itcz = new Intent(getActivity(), ChongZhq.class);
                    itcz.putExtra("keyong2", keyong);
                    startActivity(itcz);
                }
            }
        });
        wode_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcxx = new Intent(getActivity(), WoDe_XiaoXi.class);

                startActivity(itcxx);
            }
        });
        zjls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent it = new Intent(getActivity(), ZiJinliushui.class);
                    startActivity(it);
                }
            }
        });
        wode_togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    wozonge.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    wd_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.zhengyan));
                } else {
                    //否则隐藏密码
                    wozonge.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    wd_yj_image.setImageDrawable(getResources().getDrawable(R.drawable.biyan));
                }
            }
        });
        wo_yaoqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent it = new Intent(getActivity(), MyYaoQing.class);
                    it.putExtra("mancount", inviteAmount);
                    it.putExtra("countmoney", totalEarn);
                    startActivity(it);
                }
            }
        });
        wozonge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ZongziChan.class);
                startActivity(i);
            }
        });
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                Log.e("ddddddd", s);
                if (s.equals("0")) {
                    Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(intentwode);
                } else {
                    Intent itcz = new Intent(getActivity(), TiXian.class);
                    itcz.putExtra("keyong2", keyong);
                    startActivity(itcz);
                }
            }
        });
        kefuphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoualertdialog();
            }
        });

    }

    private void shoualertdialog() {
        //自定义AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.krfuphone, null);
        Button btn_qux=view1.findViewById(R.id.btn_qux);
        Button btn_hujiao=view1.findViewById(R.id.btn_hujiao);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view1)
                .show();
//给AlertDialog设置4个圆角
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);
        dialog.setCanceledOnTouchOutside(false);
        btn_qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_hujiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(getActivity(),"hujiao" );
                permission();
                dialog.dismiss();
            }
        });

    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE);
        }else{
            //已经授权
            diallPhone("4001838818");
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //用户点击了同意授权
//                    diallPhone("4001838818");
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + "4001838818");
                    intent.setData(data);
                    startActivity(intent);

                }else{
                    //用户拒绝了授权
                    ToastUtils.showToast(getActivity(), "权限被拒绝");
                }
                break;
            default:
                break;
        }
    }

    private void getWodeid() {
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        loginid = (String) SPUtils.get(getActivity(), "Loginid", "");
        userToken = (String) SPUtils.get(getActivity(), "Token1", "");
        dengru = getActivity().findViewById(R.id.dengru_chenggong);//登入状态
        weidengru = getActivity().findViewById(R.id.weidengru);//未登入状态
        wd_yj_image = getActivity().findViewById(R.id.wd_yj_image);//金额image
        shimingrenzheng_itme = getActivity().findViewById(R.id.shiming_my);//未实名itme
        yinhangcunguan_itme = getActivity().findViewById(R.id.yinhangcunguan_my);//未开通银行存管
        fengxianpingce_itme = getActivity().findViewById(R.id.fangxianpingce_my);//未风险评测
//        head.setVisibility(View.GONE); //显示与影藏布局
//        food.setVisibility(View.VISIBLE);
        kefuphone = getActivity().findViewById(R.id.kefuphone);//客服电话
        yaoqing = getActivity().findViewById(R.id.yaoqing);
        wo_yaoqing = getActivity().findViewById(R.id.wo_yaoqing);//我的邀请
        yonghudengru = getActivity().findViewById(R.id.yonghudengru);//等入按钮
        yonghudengji = getActivity().findViewById(R.id.wodedengji);//等级
        wode_xiaoxi = getActivity().findViewById(R.id.wode_xiaoxi);//消息
        wozonge = getActivity().findViewById(R.id.wode_zonge);//总额
        myphone = getActivity().findViewById(R.id.myphone);//手机号
        keyongyue = getActivity().findViewById(R.id.keyongyue);//可用余额
        wode_shazhi = getActivity().findViewById(R.id.wode_shezhi);//我的设置
        wode_chujie = getActivity().findViewById(R.id.wode_chujie);//我的出借
        xianjin_juan = getActivity().findViewById(R.id.xianjing_juan);//现金券
        jiaxi_juan = getActivity().findViewById(R.id.jiaxi_juan);//加息卷
        lijichujie = getActivity().findViewById(R.id.lijichujie);
        touxiang1 = getActivity().findViewById(R.id.touxiang1);//个人中心
        chongzhi = getActivity().findViewById(R.id.chongzhi);//充值
        tixian = getActivity().findViewById(R.id.tixian);//提现
        zjls = getActivity().findViewById(R.id.zjls);//资金流水
        wode_togglePwd = getActivity().findViewById(R.id.wode_togglePwd);//眼睛
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isLogin = (boolean) SPUtils.get(getActivity(), "isLogin", false);
        if (isLogin == false) {
            userToken = "";
            user_id = 0;
            Log.e("onResume+loginid", "" + loginid);
            shimingrenzheng_itme.setVisibility(View.GONE);
            yinhangcunguan_itme.setVisibility(View.GONE);
            fengxianpingce_itme.setVisibility(View.GONE);
//            getHttp();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "开始执行………………");
        shimingrenzheng_itme.setVisibility(View.GONE);
        getHttp();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {

            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作

            getHttp();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册  
    }

}
