package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.xiangxixinxifragment;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.MediaType;

public class XiangMuXinXi extends Fragment {
    private TextView tv_click, show, hidden;
    private View hose_xx, car_xx, car_dbr, car_dbgs, qy;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String borr_id;
    private TextView xm_name, jk_jine, jk_qixian, jk_yongtu, hk_fangshi, yd_lilv, hk_lanyuan, bx_cuoshi, start_time, end_time;
    private TextView zt_xingzhi, man_name, man_id, man_sax, man_age, man_suday, man_hunyin, man_huji, man_dizhi, man_zhiwei, man_hangye, man_shouru, man_huanhuancishu, man_yuqicishu, lishiyuqi_jinge, dangqian_jine, yuqi_qingkuang, qita_pingtai;
    private TextView fangchan_dizhi, xq_mingcheng, sy_nx, fc_mianji, fc_jiage, xk_jg;
    private TextView sf_rz, car_jia, sf_wrz, zz_rz, zz_wrz, zxbg_rz, zxbg_wrz, fwcq_rz, fwcq_wrz, fcpg_rz, fcpg_wrz, dbh_rz, dbh_wrz, sh_yj;
    private String borrowRandomId;
    private String mtpye;
    private TextView dbr_name, dbr_idcard, dbr_sax, dbr_age, dbr_hunyin, dbr_xl, dbr_huji, dbr_adriess, dbr_shouru, dbr_zhiye, dbr_hangye;
    private Date mNewDate;
    private TextView car_pp, car_id, car_km, car_time, car_pailiang, car_ck, car_buy, car_money, car_cmoney;
    private TextView dbgs_name, dbgs_daima, dbgs_daibiaoman, dbgs_addass;
    private XiangMuXinXi_Gson data;
    private XiangMuXingXi_Car_gson cardata;
    private TextView sx_pg, sc_zhuzhi, sc_cq;
    private TextView qyrz, qy_rz, qywrz;
    private View geren, qiye, shencha;
    private TextView qy_name, qy_dm, qy_time, qy_zb, qy_hy, qy_dizhi, qy_bg, qy_fd, qy_shouru, qy_huanhuancishu, qy_yuqicishu, qy_lishiyuqi_jinge, qy_dangqian_jine, qy_6yue, qy_qita, qy_fanwei;
    private int user_id;
    private View xx_view, xiale_view;
    private PromptDialog promptDialog;

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
//        xx_view.setVisibility(View.GONE);
//        promptDialog = new PromptDialog(getActivity());
//        promptDialog.showLoading("");
        getHttp();
    }

    private void getxmxxid() {
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        xx_view = getActivity().findViewById(R.id.xx_view);
//        xiale_view = getActivity().findViewById(R.id.xiale_view);
        //企业、个人
        tv_click = getActivity().findViewById(R.id.main_tv_click);
        qiye = getActivity().findViewById(R.id.qiye);
        geren = getActivity().findViewById(R.id.geren);
        // 审查信息
//        sx_pg,sc_zhuzhi,sc_cq;
        qy = getActivity().findViewById(R.id.qy);
        sx_pg = getActivity().findViewById(R.id.sx_pg);
        sc_zhuzhi = getActivity().findViewById(R.id.sc_zhuzhi);
        sc_cq = getActivity().findViewById(R.id.sc_cq);

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
        man_hunyin = getActivity().findViewById(R.id.man_hunyin);
        //法人信息,,,,,,,,,,,,,,;
        qy_fanwei = getActivity().findViewById(R.id.qy_fanwei);
        qy_name = getActivity().findViewById(R.id.qy_name);
        qy_dm = getActivity().findViewById(R.id.qy_dm);
        qy_time = getActivity().findViewById(R.id.qy_time);
        qy_zb = getActivity().findViewById(R.id.qy_zb);
        qy_hy = getActivity().findViewById(R.id.qy_hy);
        qy_dizhi = getActivity().findViewById(R.id.qy_dizhi);
        qy_bg = getActivity().findViewById(R.id.qy_bg);
        qy_fd = getActivity().findViewById(R.id.qy_fd);
        qy_shouru = getActivity().findViewById(R.id.qy_shouru);
        qy_huanhuancishu = getActivity().findViewById(R.id.qy_huanhuancishu);
        qy_yuqicishu = getActivity().findViewById(R.id.qy_yuqicishu);
        qy_lishiyuqi_jinge = getActivity().findViewById(R.id.qy_lishiyuqi_jinge);
        qy_dangqian_jine = getActivity().findViewById(R.id.qy_dangqian_jine);
        qy_6yue = getActivity().findViewById(R.id.qy_6yue);
        qy_qita = getActivity().findViewById(R.id.qy_qita);
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
        tv_click = getActivity().findViewById(R.id.main_tv_click);
//       ,,,房产信息,,;
        fangchan_dizhi = getActivity().findViewById(R.id.fangchan_dizhi);
        xq_mingcheng = getActivity().findViewById(R.id.xq_mingcheng);
        sy_nx = getActivity().findViewById(R.id.sy_nx);
        fc_mianji = getActivity().findViewById(R.id.fc_mianji);
        fc_jiage = getActivity().findViewById(R.id.fc_jiage);
        xk_jg = getActivity().findViewById(R.id.xk_jg);

//,,,,,,,,审查,,,,;
        shencha = getActivity().findViewById(R.id.shencha);
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
        //企业认证
        qyrz = getActivity().findViewById(R.id.qyrz);
        qy_rz = getActivity().findViewById(R.id.qy_rz);
        qywrz = getActivity().findViewById(R.id.qy_wrz);

        //车辆、房产
        hose_xx = getActivity().findViewById(R.id.hose_xx);
        car_xx = getActivity().findViewById(R.id.car_xx);
        car_dbr = getActivity().findViewById(R.id.car_danbaoren);
        car_jia = getActivity().findViewById(R.id.car_jia);
        //担保人信息
        dbr_name = getActivity().findViewById(R.id.dbr_name);
        dbr_idcard = getActivity().findViewById(R.id.dbr_idcard);
        dbr_sax = getActivity().findViewById(R.id.dbr_sax);
        dbr_age = getActivity().findViewById(R.id.dbr_age);
        dbr_hunyin = getActivity().findViewById(R.id.dbr_hunyin);
        dbr_xl = getActivity().findViewById(R.id.dbr_xl);
        dbr_huji = getActivity().findViewById(R.id.dbr_huji);
        dbr_adriess = getActivity().findViewById(R.id.dbr_adriess);
        dbr_shouru = getActivity().findViewById(R.id.dbr_shouru);
        dbr_zhiye = getActivity().findViewById(R.id.dbr_zhiye);
        dbr_hangye = getActivity().findViewById(R.id.dbr_hangye);
        //担保公司
        car_dbgs = getActivity().findViewById(R.id.car_danbaogs);
        dbgs_name = getActivity().findViewById(R.id.dbgs_name);
        dbgs_daima = getActivity().findViewById(R.id.dbgs_daima);
        dbgs_daibiaoman = getActivity().findViewById(R.id.dbgs_daibiaoman);
        dbgs_addass = getActivity().findViewById(R.id.dbgs_addass);

        //车辆信息
        //,,,,,,,,car_cmoney;
        car_pp = getActivity().findViewById(R.id.car_pp);
        car_id = getActivity().findViewById(R.id.car_id);
        car_km = getActivity().findViewById(R.id.car_km);
        car_time = getActivity().findViewById(R.id.car_time);
        car_pailiang = getActivity().findViewById(R.id.car_pailiang);
        car_ck = getActivity().findViewById(R.id.car_ck);
        car_buy = getActivity().findViewById(R.id.car_buy);
        car_money = getActivity().findViewById(R.id.car_money);
        car_cmoney = getActivity().findViewById(R.id.car_cmoney);


    }


    private void getHttp() {
//        promptDialog = new PromptDialog(getActivity());
//        promptDialog.showLoading("");
        final JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            js_request.put("borrowRandomId", borrowRandomId);

            base1 = Base64JiaMI.AES_Encode(js_request.toString());
            sha1 = SHA1jiami.Encrypt(js_request.toString(), "SHA-1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject canshu = new JSONObject();
        try {
            canshu.put("param", base1);
            canshu.put("sign", sha1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(Urls.BASE_URL+"yxbApp/ProjectInformation.do")
                .content(canshu.toString())

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        promptDialog.dismiss();

                    }

                    @Override
                    public void onResponse(String result, int id) {
//                        promptDialog.dismiss();
//                        xx_view.setVisibility(View.VISIBLE);
                        String s1 = String.valueOf(user_id);
                        Log.e("项目星系user_id", "" + s1);
                        if (s1.equals("0")) {
                            shencha.setVisibility(View.GONE);
                        } else {
                            shencha.setVisibility(View.VISIBLE);
                        }
                        Log.e("TAG", "XXGSON>>>" + result);
                        //房产标GSON
                        data = new Gson().fromJson(result, XiangMuXinXi_Gson.class);
                        //车辆标GSON
                        cardata = new Gson().fromJson(result, XiangMuXingXi_Car_gson.class);

                        //TextView ,,,基本信息,,,,,,
                        if (mtpye.equals("4")) {
                            car_xx.setVisibility(View.VISIBLE);

                            hose_xx.setVisibility(View.GONE);
                            String guaranteeType = cardata.getResult().getGuaranteeType();
                            if (guaranteeType.equals("0")) {
                                car_dbr.setVisibility(View.GONE);
                                car_dbgs.setVisibility(View.VISIBLE);
                            } else {
                                car_dbr.setVisibility(View.VISIBLE);
                                car_dbgs.setVisibility(View.GONE);
                            }
                            //担保公司
                            dbgs_name.setText(cardata.getResult().getC_name());
                            dbgs_daima.setText(cardata.getResult().getRegCode());
                            dbgs_daibiaoman.setText(cardata.getResult().getNewRealName());
                            dbgs_addass.setText(cardata.getResult().getNewAddresss());

                            //审查信息
//                    sx_pg,sc_zhuzhi,sc_cq;
                            //住址————》行驶证
                            sc_zhuzhi.setText("行驶证认证");
                            //产权————》购车合同
                            sc_cq.setText("购车合同");
                            //评估--》车辆证明
                            sx_pg.setText("车辆证明");

                            //担保人
                            dbr_name.setText(cardata.getResult().getBondsman().getRealNamed());
                            dbr_idcard.setText(cardata.getResult().getBondsman().getIdNod());
                            dbr_sax.setText(cardata.getResult().getBondsman().getSexd());
                            dbr_age.setText(cardata.getResult().getBondsman().getBirthd());
                            dbr_hunyin.setText(cardata.getResult().getBondsman().getMaritalStatusd());
                            dbr_xl.setText(cardata.getResult().getBondsman().getHighestEdud());
                            dbr_huji.setText(cardata.getResult().getTrpro() + cardata.getResult().getTrcityd());
                            dbr_adriess.setText(cardata.getResult().getAddressd());
                            dbr_shouru.setText(cardata.getResult().getBondsman().getMonthIncomed() + "元");
                            dbr_zhiye.setText(cardata.getResult().getBondsman().getProfessiond());
                            dbr_hangye.setText(cardata.getResult().getBondsman().getTradeTyped());
                            //车辆信息
                            //,,,,,,,,;
                            car_pp.setText(cardata.getResult().getCar().getCar_style());
                            car_id.setText(cardata.getResult().getCarcode());
                            car_km.setText(cardata.getResult().getCar().getCar_mileage() + "公里");
                            String car_register = cardata.getResult().getCar().getCar_register();
                            car_time.setText(car_register);
                            car_pailiang.setText(cardata.getResult().getCar().getCar_emission());
                            car_ck.setText(cardata.getResult().getCar().getCar_condition());
                            car_buy.setText(cardata.getResult().getCar().getCarprice());
                            car_money.setText(cardata.getResult().getCar().getCar_offer());
                            car_cmoney.setText(cardata.getResult().getCar().getReferenceprice());
                            car_jia.setText(cardata.getResult().getCar().getCarshelf());
                            //基本信息
                            String s = data.getResult().getProjectName();
                            xm_name.setText(data.getResult().getProjectName());
                            jk_jine.setText(data.getResult().getBorrowSum());
                            jk_qixian.setText(data.getResult().getDeadline());
                            jk_yongtu.setText(data.getResult().getBorrowPurpose());
                            hk_fangshi.setText(data.getResult().getRefund().getPaymentMode());
                            hidden.setText(data.getResult().getBorrowInfo());
                            show.setText(data.getResult().getBorrowInfo());
                            man_hunyin.setText(data.getResult().getRxx().getMaritalStatus());
                            yd_lilv.setText(String.valueOf(data.getResult().getRan()) + "%");
                            hk_lanyuan.setText(data.getResult().getRepaysource());
                            hk_fangshi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    shoupopwindow();

                                }
                            });
                            if (data.getResult().getMortgageType().equals("4")) {
                                bx_cuoshi.setText("车俩抵押");
                            } else {
                                bx_cuoshi.setText("房产抵押");
                            }

                            start_time.setText(data.getResult().getAbleTenderDate());
                            end_time.setText(data.getResult().getEndTenderDate());
                            hidden.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {
                                    hidden.getViewTreeObserver().removeOnPreDrawListener(this);
//                                    Log.e("行数", ""+hidden.getLineCount());
                                    if (hidden.getLineCount() <= 3) {
                                        tv_click.setVisibility(View.GONE);
                                    } else {
                                        tv_click.setVisibility(View.VISIBLE);
                                    }
                                    return false;
                                }
                            });
                            if (tv_click!=null) {

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
                            }else {
                                tv_click.setVisibility(View.GONE);
                            }
                            if (data.getResult().getBorrowFrom().equals("2")) {
                                geren.setVisibility(View.GONE);
                                qiye.setVisibility(View.VISIBLE);
                            } else {
                                geren.setVisibility(View.VISIBLE);
                                qiye.setVisibility(View.GONE);
                            }
                            //法人信息,,,,,,,,,,,,,,;
                            //,,,,,,,,,,,,,,;
                            qy_name.setText(data.getResult().getCompanyName());
                            qy_dm.setText(data.getResult().getOrganizationCode());
                            qy_time.setText(data.getResult().getBuildTime());
                            qy_zb.setText(data.getResult().getRegisteredCapital());
                            qy_hy.setText(data.getResult().getCompanyBusiness());
                            qy_dizhi.setText(data.getResult().getRegistAddress());
                            qy_bg.setText(data.getResult().getRegistAddress());
                            qy_fd.setText(data.getResult().getCompanyOwner());
                            qy_shouru.setText(data.getResult().getCmonthIncome());
                            qy_fd.setText(data.getResult().getCompanyOwner());
                            qy_huanhuancishu.setText(data.getResult().getPeriods().getPeriods() + "期");
                            qy_yuqicishu.setText(data.getResult().getOverTimess().getOverTimes() + "期");
                            qy_lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney() + "元");
                            qy_dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney() + "元");
                            qy_6yue.setText(data.getResult().getRxx().getOverdueStatus());
                            qy_qita.setText(data.getResult().getRxx().getOtherWebStatus());
                            qy_fanwei.setText(data.getResult().getBusinessScope());
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
                            man_huanhuancishu.setText(data.getResult().getPeriods().getPeriods() + "期");
                            man_yuqicishu.setText(data.getResult().getOverTimess().getOverTimes() + "期");
                            lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney() + "元");
                            dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney() + "元");
                            yuqi_qingkuang.setText(data.getResult().getRxx().getOverdueStatus());
                            qita_pingtai.setText(data.getResult().getRxx().getOtherWebStatus());

                            if (data.getResult().getBorrowFrom().equals("2")) {
                                qy.setVisibility(View.VISIBLE);
                                if (data.getResult().getAuthentication().getCompany_auth() == 1) {
                                    qy_rz.setVisibility(View.VISIBLE);
                                    qywrz.setVisibility(View.GONE);
                                } else {
                                    qy_rz.setVisibility(View.GONE);
                                    qywrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getPerson_auth() == 1) {
                                    sf_rz.setVisibility(View.VISIBLE);
                                    sf_wrz.setVisibility(View.GONE);
                                } else {
                                    sf_rz.setVisibility(View.GONE);
                                    sf_wrz.setVisibility(View.VISIBLE);
                                }
                                sc_zhuzhi.setText("注册资本");
                                if (data.getResult().getAuthentication().getRegist_capital_auth() == 1) {
                                    zz_rz.setVisibility(View.VISIBLE);
                                    zz_wrz.setVisibility(View.GONE);
                                } else {
                                    zz_rz.setVisibility(View.GONE);
                                    zz_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCredit_auth() == 1) {
                                    zxbg_rz.setVisibility(View.VISIBLE);
                                    zxbg_wrz.setVisibility(View.GONE);
                                } else {
                                    zxbg_rz.setVisibility(View.GONE);
                                    zxbg_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getBuycar_auth() == 1) {
                                    fwcq_rz.setVisibility(View.VISIBLE);
                                    fwcq_wrz.setVisibility(View.GONE);
                                } else {
                                    fwcq_rz.setVisibility(View.GONE);
                                    fwcq_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCar_auth() == 1) {
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
                            } else {

                                if (data.getResult().getAuthentication().getPerson_auth() == 1) {
                                    sf_rz.setVisibility(View.VISIBLE);
                                    sf_wrz.setVisibility(View.GONE);
                                } else {
                                    sf_rz.setVisibility(View.GONE);
                                    sf_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getGocar_auth() == 1) {
                                    zz_rz.setVisibility(View.VISIBLE);
                                    zz_wrz.setVisibility(View.GONE);
                                } else {
                                    zz_rz.setVisibility(View.GONE);
                                    zz_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCredit_auth() == 1) {
                                    zxbg_rz.setVisibility(View.VISIBLE);
                                    zxbg_wrz.setVisibility(View.GONE);
                                } else {
                                    zxbg_rz.setVisibility(View.GONE);
                                    zxbg_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getBuycar_auth() == 1) {
                                    fwcq_rz.setVisibility(View.VISIBLE);
                                    fwcq_wrz.setVisibility(View.GONE);
                                } else {
                                    fwcq_rz.setVisibility(View.GONE);
                                    fwcq_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCar_auth() == 1) {
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
                        } else {
                            car_xx.setVisibility(View.GONE);
                            car_dbr.setVisibility(View.GONE);
                            hose_xx.setVisibility(View.VISIBLE);
                            //基本信息
                            String s = data.getResult().getProjectName();
                            xm_name.setText(data.getResult().getProjectName());
                            jk_jine.setText(data.getResult().getBorrowSum());
                            jk_qixian.setText(data.getResult().getDeadline());
                            jk_yongtu.setText(data.getResult().getBorrowPurpose());
                            hk_fangshi.setText(data.getResult().getRefund().getPaymentMode());
                            hk_fangshi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    shoupopwindow();

                                }
                            });
                            hidden.setText(data.getResult().getBorrowInfo());
                            show.setText(data.getResult().getBorrowInfo());
                            yd_lilv.setText(String.valueOf(data.getResult().getRan()) + "%");
                            hk_lanyuan.setText(data.getResult().getRepaysource());
                            if (data.getResult().getMortgageType().equals("4")) {
                                bx_cuoshi.setText("车俩抵押");
                            } else {
                                bx_cuoshi.setText("房产抵押");
                            }
                            start_time.setText(data.getResult().getAbleTenderDate());
                            end_time.setText(data.getResult().getEndTenderDate());

                            hidden.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {
                                    hidden.getViewTreeObserver().removeOnPreDrawListener(this);
                                    Log.e("行数", "" + hidden.getLineCount());
                                    if (hidden.getLineCount() <= 3) {
                                        tv_click.setVisibility(View.GONE);
                                    } else {
                                        tv_click.setVisibility(View.VISIBLE);
                                    }
                                    return false;
                                }
                            });

                            if (tv_click!=null) {

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
                            }else {
                                tv_click.setVisibility(View.GONE);
                            }
                            if (data.getResult().getBorrowFrom().equals("2")) {
                                geren.setVisibility(View.GONE);
                                qiye.setVisibility(View.VISIBLE);
                            } else {
                                geren.setVisibility(View.VISIBLE);
                                qiye.setVisibility(View.GONE);
                            }
                            //法人信息,,,,,,,,,,,,,,;
                            //,,,,,,,,,,,,,,;
                            qy_name.setText(data.getResult().getCompanyName());
                            qy_dm.setText(data.getResult().getOrganizationCode());
                            qy_time.setText(data.getResult().getBuildTime());
                            qy_zb.setText(data.getResult().getRegisteredCapital());
                            qy_hy.setText(data.getResult().getCompanyBusiness());
                            qy_dizhi.setText(data.getResult().getRegistAddress());
                            qy_bg.setText(data.getResult().getCompanyAddress());
                            qy_fd.setText(data.getResult().getCompanyOwner());
                            qy_shouru.setText(data.getResult().getCmonthIncome() + "元");
                            qy_fd.setText(data.getResult().getCompanyOwner());
                            qy_huanhuancishu.setText(data.getResult().getPeriods().getPeriods() + "期");
                            qy_yuqicishu.setText(data.getResult().getOverTimess().getOverTimes() + "期");
                            qy_lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney() + "元");
                            qy_dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney() + "元");
                            qy_6yue.setText(data.getResult().getRxx().getOverdueStatus());
                            qy_qita.setText(data.getResult().getRxx().getOtherWebStatus());
                            qy_fanwei.setText(data.getResult().getBusinessScope());
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
                            man_hunyin.setText(data.getResult().getRxx().getMaritalStatus());
                            man_huanhuancishu.setText(data.getResult().getPeriods().getPeriods() + "期");
                            man_yuqicishu.setText(data.getResult().getOverTimess().getOverTimes() + "期");
                            lishiyuqi_jinge.setText(data.getResult().getOverMoney().getOverMoney() + "元");
                            dangqian_jine.setText(data.getResult().getOverMoneys().getOverMoney() + "元");
                            yuqi_qingkuang.setText(data.getResult().getRxx().getOverdueStatus());
                            qita_pingtai.setText(data.getResult().getRxx().getOtherWebStatus());
                            //房产信息
                            fangchan_dizhi.setText(data.getResult().getHouse().getHouseAddress());
                            xq_mingcheng.setText(data.getResult().getHouse().getCommunityName());
                            sy_nx.setText(data.getResult().getHouse().getUseYears());
                            fc_mianji.setText(data.getResult().getHouse().getConstructionArea());
                            fc_jiage.setText(data.getResult().getHouse().getEvaluationPrice());
                            xk_jg.setText(data.getResult().getHouse().getReferencePrice());

//   ,,,,,dbh_rz,dbh_wrz,sh_yj;
                            if (data.getResult().getBorrowFrom().equals("2")) {
                                qy.setVisibility(View.VISIBLE);
                                if (data.getResult().getAuthentication().getCompany_auth() == 1) {
                                    qy_rz.setVisibility(View.VISIBLE);
                                    qywrz.setVisibility(View.GONE);
                                } else {
                                    qy_rz.setVisibility(View.GONE);
                                    qywrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getPerson_auth() == 1) {
                                    sf_rz.setVisibility(View.VISIBLE);
                                    sf_wrz.setVisibility(View.GONE);
                                } else {
                                    sf_rz.setVisibility(View.GONE);
                                    sf_wrz.setVisibility(View.VISIBLE);
                                }
                                sc_zhuzhi.setText("注册资本");
                                if (data.getResult().getAuthentication().getRegist_capital_auth() == 1) {
                                    zz_rz.setVisibility(View.VISIBLE);
                                    zz_wrz.setVisibility(View.GONE);
                                } else {
                                    zz_rz.setVisibility(View.GONE);
                                    zz_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCredit_auth() == 1) {
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
                            } else {

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
                                if (data.getResult().getAuthentication().getCredit_auth() == 1) {
                                    zxbg_rz.setVisibility(View.VISIBLE);
                                    zxbg_wrz.setVisibility(View.GONE);
                                } else {
                                    zxbg_rz.setVisibility(View.GONE);
                                    zxbg_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getBuycar_auth() == 1) {
                                    fwcq_rz.setVisibility(View.VISIBLE);
                                    fwcq_wrz.setVisibility(View.GONE);
                                } else {
                                    fwcq_rz.setVisibility(View.GONE);
                                    fwcq_wrz.setVisibility(View.VISIBLE);
                                }
                                if (data.getResult().getAuthentication().getCar_auth() == 1) {
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
                        }
                    }
                });

    }

    private void shoupopwindow() {

        View parent = ((ViewGroup) this.getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.kuankuan_pop, null);
        TextView text_pop = popView.findViewById(R.id.text_pop);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow;
        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        text_pop.setText(data.getResult().getRefund().getPaymentModezi());
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

    }

    private void initview() {
        borrowRandomId = (String) SPUtils.get(getActivity(), "borroFwRandomId", "");
        mtpye = (String) SPUtils.get(getActivity(), "mtype1", "");

        MyScrollView xiangmuxingxiSV = getActivity().findViewById(R.id.xiangmuxinxScrollView);
        xiangmuxingxiSV.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {
//                ToastUtils.showToast(getActivity(), "ff");
//                xiale_view.setVisibility(View.VISIBLE);
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
