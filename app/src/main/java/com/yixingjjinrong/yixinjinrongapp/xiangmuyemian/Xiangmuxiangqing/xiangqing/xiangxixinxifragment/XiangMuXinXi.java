package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXinXi_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.XiangMuXingXi_Car_gson;
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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XiangMuXinXi extends Fragment {
    private TextView tv_click, show, hidden;
    private View hose_xx, car_xx, car_dbr;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String borr_id;
    private TextView xm_name, jk_jine, jk_qixian, jk_yongtu, hk_fangshi, yd_lilv, hk_lanyuan, bx_cuoshi, start_time, end_time;
    private TextView zt_xingzhi, man_name, man_id, man_sax, man_age, man_suday, man_huji, man_dizhi, man_zhiwei, man_hangye, man_shouru, man_huanhuancishu, man_yuqicishu, lishiyuqi_jinge, dangqian_jine, yuqi_qingkuang, qita_pingtai;
    private TextView fangchan_dizhi, xq_mingcheng, sy_nx, fc_mianji, fc_jiage, xk_jg;
    private TextView sf_rz, sf_wrz, zz_rz, zz_wrz, zxbg_rz, zxbg_wrz, fwcq_rz, fwcq_wrz, fcpg_rz, fcpg_wrz, dbh_rz, dbh_wrz, sh_yj;
    private String borrowRandomId;
    private String mtpye;
    private TextView dbr_name,dbr_idcard,dbr_sax,dbr_age,dbr_hunyin,dbr_xl,dbr_huji,dbr_adriess,dbr_shouru,dbr_zhiye,dbr_hangye;
    private Date mNewDate;
    private TextView car_pp,car_id,car_km,car_time,car_pailiang,car_ck,car_buy,car_money,car_cmoney;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.xiangmuxingxifragment, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        getxmxxid();

        getHttp();
    }

    private void getxmxxid() {

        show = getActivity().findViewById(R.id.main_tv_show);
        hidden = getActivity().findViewById(R.id.main_tv_hidden);
//基本信息
        xm_name = getActivity().findViewById(R.id.xm_name);
        jk_jine = getActivity().findViewById(R.id.jk_jine);
        jk_qixian = getActivity().findViewById(R.id.jk_qixian);
        jk_yongtu = getActivity().findViewById(R.id.jk_yongtu);
        hk_fangshi = getActivity().findViewById(R.id.hk_fangshi);
        hk_lanyuan = getActivity().findViewById(R.id.hk_lanyuan);
        bx_cuoshi = getActivity().findViewById(R.id.bx_cuoshi);
        start_time = getActivity().findViewById(R.id.start_time);
        end_time = getActivity().findViewById(R.id.end_time);
        yd_lilv = getActivity().findViewById(R.id.yd_lilv);

// ,,,,,,,,,个人信息,,,,,,,,,;
        zt_xingzhi = getActivity().findViewById(R.id.zt_xingzhi);
        man_name = getActivity().findViewById(R.id.man_name);
        man_id = getActivity().findViewById(R.id.man_id);
        man_sax = getActivity().findViewById(R.id.man_sax);
        man_age = getActivity().findViewById(R.id.man_age);
        man_suday = getActivity().findViewById(R.id.man_suday);
        man_huji = getActivity().findViewById(R.id.man_huji);
        man_dizhi = getActivity().findViewById(R.id.man_dizhi);
        man_zhiwei = getActivity().findViewById(R.id.man_zhiwei);
        man_hangye = getActivity().findViewById(R.id.man_hangye);
        man_shouru = getActivity().findViewById(R.id.man_shouru);
        man_huanhuancishu = getActivity().findViewById(R.id.man_huanhuancishu);
        man_yuqicishu = getActivity().findViewById(R.id.man_yuqicishu);
        lishiyuqi_jinge = getActivity().findViewById(R.id.lishiyuqi_jinge);
        dangqian_jine = getActivity().findViewById(R.id.dangqian_jine);
        yuqi_qingkuang = getActivity().findViewById(R.id.yuqi_qingkuang);
        qita_pingtai = getActivity().findViewById(R.id.qita_pingtai);
        qita_pingtai = getActivity().findViewById(R.id.qita_pingtai);

//       ,,,房产信息,,;
        fangchan_dizhi = getActivity().findViewById(R.id.fangchan_dizhi);
        xq_mingcheng = getActivity().findViewById(R.id.xq_mingcheng);
        sy_nx = getActivity().findViewById(R.id.sy_nx);
        fc_mianji = getActivity().findViewById(R.id.fc_mianji);
        fc_jiage = getActivity().findViewById(R.id.fc_jiage);
        xk_jg = getActivity().findViewById(R.id.xk_jg);

//,,,,,,,,审查,,,,;        
        sf_rz = getActivity().findViewById(R.id.sf_rz);
        sf_wrz = getActivity().findViewById(R.id.sf_wrz);
        zz_rz = getActivity().findViewById(R.id.zz_rz);
        zz_wrz = getActivity().findViewById(R.id.zz_wrz);
        zxbg_rz = getActivity().findViewById(R.id.zxbg_rz);
        zxbg_wrz = getActivity().findViewById(R.id.zxbg_wrz);
        fwcq_rz = getActivity().findViewById(R.id.fwcq_rz);
        fwcq_wrz = getActivity().findViewById(R.id.fwcq_wrz);
        fcpg_rz = getActivity().findViewById(R.id.fcpg_rz);
        fcpg_wrz = getActivity().findViewById(R.id.fcpg_wrz);
        dbh_rz = getActivity().findViewById(R.id.dbh_rz);
        dbh_wrz = getActivity().findViewById(R.id.dbh_wrz);
        sh_yj = getActivity().findViewById(R.id.sh_yj);
        //车辆、房产
        hose_xx=getActivity().findViewById(R.id.hose_xx);
        car_xx=getActivity().findViewById(R.id.car_xx);
        car_dbr=getActivity().findViewById(R.id.car_danbaoren);
        //担保人信息

        dbr_name=getActivity().findViewById(R.id.dbr_name);
        dbr_idcard=getActivity().findViewById(R.id.dbr_idcard);
        dbr_sax=getActivity().findViewById(R.id.dbr_sax);
        dbr_age=getActivity().findViewById(R.id.dbr_age);
        dbr_hunyin=getActivity().findViewById(R.id.dbr_hunyin);
        dbr_xl=getActivity().findViewById(R.id.dbr_xl);
        dbr_huji=getActivity().findViewById(R.id.dbr_huji);
        dbr_adriess=getActivity().findViewById(R.id.dbr_adriess);
        dbr_shouru=getActivity().findViewById(R.id.dbr_shouru);
        dbr_zhiye=getActivity().findViewById(R.id.dbr_zhiye);
        dbr_hangye=getActivity().findViewById(R.id.dbr_hangye);
        //车辆信息
        //,,,,,,,,car_cmoney;
        car_pp=getActivity().findViewById(R.id.car_pp);
        car_id=getActivity().findViewById(R.id.car_id);
        car_km=getActivity().findViewById(R.id.car_km);
        car_time=getActivity().findViewById(R.id.car_time);
        car_pailiang=getActivity().findViewById(R.id.car_pailiang);
        car_ck=getActivity().findViewById(R.id.car_ck);
        car_buy=getActivity().findViewById(R.id.car_buy);
        car_money=getActivity().findViewById(R.id.car_money);
        car_cmoney=getActivity().findViewById(R.id.car_cmoney);


    }


    private void getHttp() {
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("borrowRandomId", borrowRandomId);
//            js_request.put("mortgageType", mtpye);
            Log.e("项目信息类型", ""+js_request);
            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
            Log.e("TAG", ">>>>GGGGGGGSH!!" + sha1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);
            Log.e("项目信息加密", "" + canshu);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(Urls.BASE_URL+"yxb_mobile/yxbApp/ProjectInformation.do");
        params.setAsJsonContent(true);
        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "XXGSON>>>" + result);
                //房产标GSON
                XiangMuXinXi_Gson data = new Gson().fromJson(result, XiangMuXinXi_Gson.class);
                //车辆标GSON
                XiangMuXingXi_Car_gson cardata = new Gson().fromJson(result, XiangMuXingXi_Car_gson.class);

                //TextView ,,,基本信息,,,,,,
                 Log.e("      项 目信    息type", mtpye);
                if (mtpye.equals("4")){
                    car_xx.setVisibility(View.VISIBLE);
                    car_dbr.setVisibility(View.VISIBLE);
                    hose_xx.setVisibility(View.GONE);
                    dbr_name.setText(cardata.getResult().getBondsman().getRealNamed());
                    dbr_idcard.setText(cardata.getResult().getBondsman().getIdNod());
                    dbr_sax.setText(cardata.getResult().getBondsman().getSexd());
                    dbr_age.setText(cardata.getResult().getBondsman().getBirthd());
                    dbr_hunyin.setText(cardata.getResult().getBondsman().getMaritalStatusd());
                    dbr_xl.setText(cardata.getResult().getBondsman().getHighestEdud());
                    dbr_huji.setText(cardata.getResult().getBondsman().getTrcityd());
                    dbr_adriess.setText(cardata.getResult().getBondsman().getAddressd());
                    dbr_shouru.setText(cardata.getResult().getBondsman().getMonthIncomed()+"元");
                    dbr_zhiye.setText(cardata.getResult().getBondsman().getProfessiond());
                    dbr_hangye.setText(cardata.getResult().getBondsman().getTradeTyped());
                    //车辆信息
                    //,,,,,,,,;
                    car_pp.setText(cardata.getResult().getCar().getCar_style());
                    car_id.setText(cardata.getResult().getCar().getCarcode());
                    car_km.setText(cardata.getResult().getCar().getCar_mileage()+"公里");
                    String car_register = cardata.getResult().getCar().getCar_register();
                    car_time.setText(car_register);
                    car_pailiang.setText(cardata.getResult().getCar().getCar_emission());
                    car_ck.setText(cardata.getResult().getCar().getCar_condition());
                    car_buy.setText(cardata.getResult().getCar().getCarprice());
                    car_money.setText(cardata.getResult().getCar().getCar_offer());
                    car_cmoney.setText(cardata.getResult().getCar().getReferenceprice());
                    //基本信息
                    String s=data.getResult().getProjectName();
                    Log.e("哎呀我去之天龙八部：",""+s);
                    xm_name.setText(data.getResult().getProjectName());
                    jk_jine.setText(data.getResult().getBorrowSum());
                    jk_qixian.setText(data.getResult().getDeadline());
                    jk_yongtu.setText(data.getResult().getBorrowPurpose());
                    hk_fangshi.setText(data.getResult().getPaymentMode());
                    hidden.setText(data.getResult().getBorrowInfo());
                    show.setText(data.getResult().getBorrowInfo());
                    yd_lilv.setText(String.valueOf(data.getResult().getRan()));
                    hk_lanyuan.setText(data.getResult().getRepaysource());
                    bx_cuoshi.setText(data.getResult().getMortgageType());
                    start_time.setText(data.getResult().getAbleTenderDate());
                    end_time.setText(data.getResult().getEndTenderDate());
                    tv_click = getActivity().findViewById(R.id.main_tv_click);
                    tv_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (hidden.getVisibility() == View.VISIBLE) {
                                hidden.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                tv_click.setText("【收起】");
                            } else {
                                hidden.setVisibility(View.VISIBLE);
                                show.setVisibility(View.GONE);
                                tv_click.setText("【展开】");
                            }
                        }
                    });
//  TextView ,,,,,个人信息,,,,,,,,,,,;
                    man_name.setText(data.getResult().getRealName());
                    man_id.setText(data.getResult().getIdNo());
                    man_sax.setText(data.getResult().getRxx().getSex());
                    man_age.setText(data.getResult().getRxx().getAge());
                    man_suday.setText(data.getResult().getRxx().getHighestEdu());
                    man_huji.setText(data.getResult().getTrpro() + data.getResult().getTrcity());
                    man_dizhi.setText(data.getResult().getAddress());
                    man_zhiwei.setText(data.getResult().getRxx().getProfession());
                    man_hangye.setText(data.getResult().getRxx().getTradeType());
                    man_shouru.setText(data.getResult().getRxx().getMonthIncome());
                    man_huanhuancishu.setText(data.getResult().getTimes().getTimes());
                    man_yuqicishu.setText(data.getResult().getOverTimes().getOverTimes());
                    lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney());
                    dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney());
                    yuqi_qingkuang.setText(data.getResult().getRxx().getOverdueStatus());
                    qita_pingtai.setText(data.getResult().getRxx().getOtherWebStatus());
                    if (data.getResult().getAuthentication().getPerson_auth() == 1) {
                        sf_rz.setVisibility(View.VISIBLE);
                        sf_wrz.setVisibility(View.GONE);
                    } else {
                        sf_rz.setVisibility(View.GONE);
                        sf_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getAddress_auth() == 1) {
                        zz_rz.setVisibility(View.VISIBLE);
                        zz_wrz.setVisibility(View.GONE);
                    } else {
                        zz_rz.setVisibility(View.GONE);
                        zz_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getCar_auth() == 0) {
                        zxbg_rz.setVisibility(View.VISIBLE);
                        zxbg_wrz.setVisibility(View.GONE);
                    } else {
                        zxbg_rz.setVisibility(View.GONE);
                        zxbg_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getHouse_right_auth() == 1) {
                        fwcq_rz.setVisibility(View.VISIBLE);
                        fwcq_wrz.setVisibility(View.GONE);
                    } else {
                        fwcq_rz.setVisibility(View.GONE);
                        fwcq_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getHouse_evaluation() == 1) {
                        fcpg_rz.setVisibility(View.VISIBLE);
                        fcpg_wrz.setVisibility(View.GONE);
                    } else {
                        fcpg_rz.setVisibility(View.GONE);
                        fcpg_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getDanbao_auth() == 1) {
                        dbh_rz.setVisibility(View.VISIBLE);
                        dbh_wrz.setVisibility(View.GONE);
                    } else {
                        dbh_rz.setVisibility(View.GONE);
                        dbh_wrz.setVisibility(View.VISIBLE);
                    }
                }else {
                    car_xx.setVisibility(View.GONE);
                    car_dbr.setVisibility(View.GONE);
                    hose_xx.setVisibility(View.VISIBLE);
                    //基本信息
                    String s=data.getResult().getProjectName();
                    Log.e("哎呀我去之天龙八部：",""+s);
                    xm_name.setText(data.getResult().getProjectName());
                    jk_jine.setText(data.getResult().getBorrowSum());
                    jk_qixian.setText(data.getResult().getDeadline());
                    jk_yongtu.setText(data.getResult().getBorrowPurpose());
                    hk_fangshi.setText(data.getResult().getPaymentMode());
                    hidden.setText(data.getResult().getBorrowInfo());
                    show.setText(data.getResult().getBorrowInfo());
                    yd_lilv.setText(String.valueOf(data.getResult().getRan()));
                    hk_lanyuan.setText(data.getResult().getRepaysource());
                    bx_cuoshi.setText(data.getResult().getMortgageType());
                    start_time.setText(data.getResult().getAbleTenderDate());
                    end_time.setText(data.getResult().getEndTenderDate());
                    tv_click = getActivity().findViewById(R.id.main_tv_click);
                    tv_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (hidden.getVisibility() == View.VISIBLE) {
                                hidden.setVisibility(View.GONE);
                                show.setVisibility(View.VISIBLE);
                                tv_click.setText("【收起】");
                            } else {
                                hidden.setVisibility(View.VISIBLE);
                                show.setVisibility(View.GONE);
                                tv_click.setText("【展开】");
                            }
                        }
                    });
//  TextView ,,,,,个人信息,,,,,,,,,,,;
                    man_name.setText(data.getResult().getRealName());
                    man_id.setText(data.getResult().getIdNo());
                    man_sax.setText(data.getResult().getRxx().getSex());
                    man_age.setText(data.getResult().getRxx().getAge());
                    man_suday.setText(data.getResult().getRxx().getHighestEdu());
                    man_huji.setText(data.getResult().getTrpro() + data.getResult().getTrcity());
                    man_dizhi.setText(data.getResult().getAddress());
                    man_zhiwei.setText(data.getResult().getRxx().getProfession());
                    man_hangye.setText(data.getResult().getRxx().getTradeType());
                    man_shouru.setText(data.getResult().getRxx().getMonthIncome());
                    man_huanhuancishu.setText(data.getResult().getTimes().getTimes());
                    man_yuqicishu.setText(data.getResult().getOverTimes().getOverTimes());
                    lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney());
                    dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney());
                    yuqi_qingkuang.setText(data.getResult().getRxx().getOverdueStatus());
                    qita_pingtai.setText(data.getResult().getRxx().getOtherWebStatus());
//  ,,,,,;          //房产信息
                    fangchan_dizhi.setText(data.getResult().getHouse().getHouseAddress());
                    xq_mingcheng.setText(data.getResult().getHouse().getCommunityName());
                    sy_nx.setText(data.getResult().getHouse().getUseYears());
                    fc_mianji.setText(data.getResult().getHouse().getConstructionArea());
                    fc_jiage.setText(data.getResult().getHouse().getEvaluationPrice());
                    xk_jg.setText(data.getResult().getHouse().getReferencePrice());

//   ,,,,,dbh_rz,dbh_wrz,sh_yj;
                    if (data.getResult().getAuthentication().getPerson_auth() == 1) {
                        sf_rz.setVisibility(View.VISIBLE);
                        sf_wrz.setVisibility(View.GONE);
                    } else {
                        sf_rz.setVisibility(View.GONE);
                        sf_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getAddress_auth() == 1) {
                        zz_rz.setVisibility(View.VISIBLE);
                        zz_wrz.setVisibility(View.GONE);
                    } else {
                        zz_rz.setVisibility(View.GONE);
                        zz_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getCar_auth() == 0) {
                        zxbg_rz.setVisibility(View.VISIBLE);
                        zxbg_wrz.setVisibility(View.GONE);
                    } else {
                        zxbg_rz.setVisibility(View.GONE);
                        zxbg_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getHouse_right_auth() == 1) {
                        fwcq_rz.setVisibility(View.VISIBLE);
                        fwcq_wrz.setVisibility(View.GONE);
                    } else {
                        fwcq_rz.setVisibility(View.GONE);
                        fwcq_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getHouse_evaluation() == 1) {
                        fcpg_rz.setVisibility(View.VISIBLE);
                        fcpg_wrz.setVisibility(View.GONE);
                    } else {
                        fcpg_rz.setVisibility(View.GONE);
                        fcpg_wrz.setVisibility(View.VISIBLE);
                    }
                    if (data.getResult().getAuthentication().getDanbao_auth() == 1) {
                        dbh_rz.setVisibility(View.VISIBLE);
                        dbh_wrz.setVisibility(View.GONE);
                    } else {
                        dbh_rz.setVisibility(View.GONE);
                        dbh_wrz.setVisibility(View.VISIBLE);
                    }
                }
//                //,,,,,,,,,,;
//                //担保人
//                dbr_name.setText(cardata.getResult().getBondsman().getRealNamed());
//                dbr_idcard.setText(cardata.getResult().getBondsman().getIdNod());
//                dbr_sax.setText(cardata.getResult().getBondsman().getSexd());
//                dbr_age.setText(cardata.getResult().getBondsman().getBirthd());
//                dbr_hunyin.setText(cardata.getResult().getBondsman().getMaritalStatusd());
//                dbr_xl.setText(cardata.getResult().getBondsman().getHighestEdud());
//                dbr_huji.setText(cardata.getResult().getBondsman().getTrcityd());
//                dbr_adriess.setText(cardata.getResult().getBondsman().getAddressd());
//                dbr_shouru.setText(cardata.getResult().getBondsman().getMonthIncomed()+"元");
//                dbr_zhiye.setText(cardata.getResult().getBondsman().getProfessiond());
//                dbr_hangye.setText(cardata.getResult().getBondsman().getTradeTyped());
//                //车辆信息
//                //,,,,,,,,;
//                car_pp.setText(cardata.getResult().getCar().getCar_style());
//                car_id.setText(cardata.getResult().getCar().getCarcode());
//                car_km.setText(cardata.getResult().getCar().getCar_mileage()+"公里");
//                String car_register = cardata.getResult().getCar().getCar_register();
//                car_time.setText(car_register);
//                car_pailiang.setText(cardata.getResult().getCar().getCar_emission());
//                car_ck.setText(cardata.getResult().getCar().getCar_condition());
//                car_buy.setText(cardata.getResult().getCar().getCarprice());
//                car_money.setText(cardata.getResult().getCar().getCar_offer());
//                car_cmoney.setText(cardata.getResult().getCar().getReferenceprice());




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

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(), "borrowRandomId", "");
        mtpye = (String) SPUtils.get(getActivity(), "mtype1", "");

        MyScrollView xiangmuxingxiSV = getActivity().findViewById(R.id.xiangmuxinxScrollView);
        xiangmuxingxiSV.setScrollListener(new MyScrollView.ScrollListener() {
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
