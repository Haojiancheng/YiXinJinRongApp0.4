package com.yixingjjinrong.yixinjinrongapp.wode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.Myuser_id;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_data;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.User_id;
import com.yixingjjinrong.yixinjinrongapp.gsondata.User_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.wode.fore_inot.WoDe_ChuJie;
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
    private Button yonghudengru;//用户登入跳转按钮
    private TextView yonghudengji, wozonge, myphone, keyongyue, yaoqing;//等级、总额、可用余额、我的邀请
    private ImageView wode_shazhi, wode_chujie;//设置，我的出借
    private int user_id;
    //    private String userToken;
    private String sha1;//SHA1加密
    private String base1;//Base64加

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
        getoncilink();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myMethod(User_data event) {
        weidengru.setVisibility(View.GONE); //显示布局
        dengru.setVisibility(View.VISIBLE);//影藏布局

//        }
        user_id = event.getUser_id();
//        userToken = event.getUserToken();
        getHttp();
        
    }

    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("userId",user_id);
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

        final RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/userIndex.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG",">>>Gson"+result);
                User_Gson data=new Gson().fromJson(result,User_Gson.class);
                myphone.setText(data.getUserMap().getPhone());//手机号
                String zhuangtaima=data.getState();
                if (zhuangtaima.equals("success")){
                    wozonge.setText(data.getUserMap().getAccountSum());//总额
                    keyongyue.setText(data.getUserMap().getUsableAmount());//可用余额
                    yonghudengji.setText("LV:"+data.getUserMap().getLevelNo());//等级
                    yaoqing.setText(data.getUserMap().getInviteAmount());//我的邀请
                    String fx=data.getUserMap().getRisk();//风险评测
                    String s_name=data.getUserMap().getAuth();//实名认证
                    String blank=data.getUserMap().getCg();//银行存管
                    String daijingjuan=data.getUserMap().getDjq();//代金券
                    String jiaxijuan=data.getUserMap().getJxq();//加息劵
                    String my_message=data.getUserMap().getMails();//我的消息
                    if(s_name.equals("0")){
                        shimingrenzheng_itme.setVisibility(View.VISIBLE);
                    }else {
                        shimingrenzheng_itme.setVisibility(View.GONE);
                    }
                    if(blank.equals("0")){
                        yinhangcunguan_itme.setVisibility(View.VISIBLE);
                    }else {
                        yinhangcunguan_itme.setVisibility(View.GONE);
                    }
                    if(fx.equals("0")){
                        fengxianpingce_itme.setVisibility(View.VISIBLE);
                    }else {
                        fengxianpingce_itme.setVisibility(View.GONE);
                    }
                    
                    
                }else {
                    Toast.makeText(getActivity(), "请重新登入", Toast.LENGTH_SHORT).show();
                }
                EventBus.getDefault().post(new Myuser_id(user_id));
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
        yonghudengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//登入页面
                Intent intentwode = new Intent(getActivity(), WoDe_DengRu.class);
                startActivity(intentwode);
            }
        });
        wode_shazhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设置页面
                Intent intentwode_shezhi = new Intent(getActivity(), WoDe_SheZhi.class);
                startActivity(intentwode_shezhi);

            }
        });
        wode_chujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentwode_chujie = new Intent(getActivity(), WoDe_ChuJie.class);
                intentwode_chujie.putExtra("userId", user_id);
                startActivity(intentwode_chujie);
                EventBus.getDefault().post(new User_id(user_id));

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

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册  
    }
    
}
