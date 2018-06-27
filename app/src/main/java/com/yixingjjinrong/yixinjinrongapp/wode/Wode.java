package com.yixingjjinrong.yixinjinrongapp.wode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.Myuser_id;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.CunGuan_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShiFouKeShiMing_gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.User_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.chongzhi.ChongZhq;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ChengGongZhuCe;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.ShiMingrenzheng;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.YinHangCunGuan;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.Juan;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.WoDe_ChuJie;
import com.yixingjjinrong.yixinjinrongapp.wode.mycontent.My_Content;
import com.yixingjjinrong.yixinjinrongapp.wode.shezhi.WoDe_SheZhi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Wode extends Fragment {
    private View dengru, weidengru, shimingrenzheng_itme, yinhangcunguan_itme, fengxianpingce_itme;//登入和未登入状态的头部,实名认证，银行存管，风险评测
    private Button yonghudengru, chongzhi, tixian;//用户登入跳转按钮,充值，提现
    private TextView yonghudengji, wozonge, myphone, keyongyue, yaoqing, lijichujie;//等级、总额、可用余额、我的邀请
    private ImageView wode_shazhi, wode_chujie, xianjin_juan, jiaxi_juan, touxiang1;//设置，我的出借,现金券，加息卷
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

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myMethod(User_data event) {
        user_id = event.getUser_id();

        userToken = event.getUserToken();
//        getHttp();
        isLogin = (boolean) SPUtils.get(getActivity(), "isLogin", false);
        SPUtils.put(getActivity(), "isLogin", true);
        if (isLogin == true) {

            getHttp();
        } else {
            SPUtils.remove(getActivity(), "userId");
            SPUtils.remove(getActivity(), "Token1");
            getHttp();
        }

    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        final String token = userToken;
        try {
            js_request.put("userId", user_id);
            js_request.put("Token", token);
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

        final RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/userIndex.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", ">>>Gson" + result);
                User_Gson data = new Gson().fromJson(result, User_Gson.class);
                phone = data.getUserMap().getPhone();
                myphone.setText(phone);//手机号
                String zhuangtaima = data.getState();
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
                    //测评结果
                    riskType = data.getUserMap().getRiskType();
                    if (s_name.equals("0")) {
                        shimingrenzheng_itme.setVisibility(View.VISIBLE);
                        yinhangcunguan_itme.setVisibility(View.GONE);
                        fengxianpingce_itme.setVisibility(View.GONE);
                        shimingrenzheng_itme.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getshimingHTTp();
                            }
                        });
                    } else {
                        shimingrenzheng_itme.setVisibility(View.GONE);
                        if (blank.equals("0")) {
                            yinhangcunguan_itme.setVisibility(View.VISIBLE);
                            fengxianpingce_itme.setVisibility(View.GONE);
                            yinhangcunguan_itme.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), "点击了银行存管", Toast.LENGTH_SHORT).show();
                                    getchHTTP();
                                }
                            });
                        } else {
                            if (fx.equals("0")) {
                                fengxianpingce_itme.setVisibility(View.VISIBLE);
                                fengxianpingce_itme.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), FengXianPingCe.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("user_ird", user_id);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }
                    }


                } else {
                    weidengru.setVisibility(View.VISIBLE); //显示布局
                    dengru.setVisibility(View.GONE);//影藏布局
                    Toast.makeText(getActivity(), "请重新登入", Toast.LENGTH_SHORT).show();
                }
//                EventBus.getDefault().post(new Myuser_id(user_id));
                SPUtils.put(getActivity(), "userId", user_id);
                SPUtils.put(getActivity(), "Token1", token);
                getoncilink();
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

    private void getchHTTP() {
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userid", String.valueOf(user_id));
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

        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/accountReg.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        Log.e("TAG", ">>>>网址" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("存管GSON:",""+result );
                CunGuan_gson data = new Gson().fromJson(result, CunGuan_gson.class);
                String html = data.getResult().getHtml();
                Intent it=new Intent(getActivity(), YinHangCunGuan.class);
                it.putExtra("HTML",html );
                startActivity(it);

               Log.e("wangy",""+html );
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
                Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                String jieguo = data.getState().toString();
                if (jieguo.equals("success")) {
                    Intent it = new Intent(getActivity(), ShiMingrenzheng.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_ird", user_id);
                    it.putExtras(bundle);
                    startActivity(it);
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
                Intent intentwode_chujie = new Intent(getActivity(), WoDe_ChuJie.class);
                startActivity(intentwode_chujie);
                EventBus.getDefault().post(new User_id(user_id));

            }
        });
        xianjin_juan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), Juan.class);
                startActivity(it);
            }
        });
        jiaxi_juan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), Juan.class);
                startActivity(it);
            }
        });
        lijichujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new LookMore());
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
                Intent itcz = new Intent(getActivity(), ChongZhq.class);
                itcz.putExtra("keyong2", keyong);
                startActivity(itcz);
            }
        });

    }


    private void getWodeid() {
        dengru = getActivity().findViewById(R.id.dengru_chenggong);//登入状态
        weidengru = getActivity().findViewById(R.id.weidengru);//未登入状态
        shimingrenzheng_itme = getActivity().findViewById(R.id.shiming_my);//未实名itme
        yinhangcunguan_itme = getActivity().findViewById(R.id.yinhangcunguan_my);//未开通银行存管
        fengxianpingce_itme = getActivity().findViewById(R.id.fangxianpingce_my);//未风险评测
//        head.setVisibility(View.GONE); //显示与影藏布局
//        food.setVisibility(View.VISIBLE);
        yonghudengru = getActivity().findViewById(R.id.yonghudengru);//等入按钮
        yonghudengji = getActivity().findViewById(R.id.wodedengji);//等级

        wozonge = getActivity().findViewById(R.id.wode_zonge);//总额
        myphone = getActivity().findViewById(R.id.myphone);//手机号
        keyongyue = getActivity().findViewById(R.id.keyongyue);//可用余额
        yaoqing = getActivity().findViewById(R.id.yaoqing);//我的邀请
        wode_shazhi = getActivity().findViewById(R.id.wode_shezhi);//我的设置
        wode_chujie = getActivity().findViewById(R.id.wode_chujie);//我的出借
        xianjin_juan = getActivity().findViewById(R.id.xianjing_juan);//现金券
        jiaxi_juan = getActivity().findViewById(R.id.jiaxi_juan);//加息卷
        lijichujie = getActivity().findViewById(R.id.lijichujie);
        touxiang1 = getActivity().findViewById(R.id.touxiang1);//个人中心
        chongzhi = getActivity().findViewById(R.id.chongzhi);//充值
        tixian = getActivity().findViewById(R.id.tixian);//提现

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {

            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
//            GetData();
//            adapter.notifyDataSetChanged();
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
