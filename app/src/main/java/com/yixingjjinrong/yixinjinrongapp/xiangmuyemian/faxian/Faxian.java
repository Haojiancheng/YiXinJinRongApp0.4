package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.FaXian_Data;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.FaXianBasrAdapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.wode.dengruzuce.WoDe_DengRu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.AnQuanBaoZhang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.PingTaJieShao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.WangDaiKeTang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.XinXiPiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.JiFenDuiHuan;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.ShangPingXiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.banner_h5.ShouYe_HuoDong;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class Faxian extends Fragment {
    private TextView fx_gengduo;
    private View pingtaijieshao12, anquanbaozhang, xingxipilu, wangdaiketang;
    private Banner fanxian_banner;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private String picurl;
    private String picpath;
    private RecyclerView myrecview;
    private FaXianBasrAdapter adapter;
    private List<FaXian_Data.ResultBean.GoodsListBean> list = new ArrayList<>();
    private int user_id;
    private String s;
    private FaXian_Data data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faxian2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getfaxiaid();

        getOnClickq();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
        myrecview.setLayoutManager(layoutManager);

        gethppt();
    }

    private void gethppt() {
        //192.168.1.111
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/discoveryIndex.do")
                .content("")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String result, int id) {
                        MyLog.e("发现", "" + result);
                        data = new Gson().fromJson(result, FaXian_Data.class);
                        String paht = data.getResult().getPath();

                        /**
                         * RecyclerView
                         */
                        list.addAll(data.getResult().getGoodsList());
                        adapter = new FaXianBasrAdapter(list, paht);
                        myrecview.setAdapter(adapter);
                        adapter.setonEveryItemClickListener(new FaXianBasrAdapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {
                                user_id = (int) SPUtils.get(getActivity(), "userId", 0);
                                s = String.valueOf(user_id);
                                MyLog.e("sdasd", "" + s);
                                String awardType = String.valueOf(list.get(position).getAwardType());
                                String speid = String.valueOf(list.get(position).getSpeId());
                                String prizeId = list.get(position).getPrizeId();
                                //跳详情
                                if (s.equals("0")) {
                                    Intent it = new Intent(getActivity(), WoDe_DengRu.class);
                                    startActivity(it);
                                } else {
                                    Intent it = new Intent(getActivity(), ShangPingXiangQing.class);
                                    it.putExtra("awardType", awardType);
                                    it.putExtra("speid", speid);
                                    it.putExtra("prizeId", prizeId);
                                    startActivity(it);
                                }
                            }
                        });

                        MyLog.e("TAG", "Path:" + paht);
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picurl = data.getResult().getBannerList().get(i).getPicurl();
                            MyLog.e("TAG", "url:" + picurl);

                        }
                        String[] mypic = new String[data.getResult().getBannerList().size()];
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picpath = paht + data.getResult().getBannerList().get(i).getPicurl();//图片地址
                            MyLog.e("TAG", "url:" + picpath);

                            mypic[i] = picpath;

                        }
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {


                            MyLog.e("TAG", ">>>URL:" + mypic[i].toString());
                        }

                        List<String> list3 = new ArrayList<>();
                        for (String s2 : mypic) {
                            list3.add(s2);
                        }
                        fanxian_banner.setImageLoader(new GlideImageloader());
                        fanxian_banner.setImages(list3);
                        fanxian_banner.setDelayTime(5000);
                        fanxian_banner.start();
                    }
                });
    }

    private class GlideImageloader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }
    }

    private void getOnClickq() {
        fanxian_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (data.getResult().getBannerList().get(position).getHrefurl().indexOf("userId=") != -1) {
                    String hrefurl = data.getResult().getBannerList().get(position).getHrefurl();
                    Intent it = new Intent(getActivity(), ShouYe_HuoDong.class);
                    it.putExtra("h5", hrefurl + user_id);
                    startActivity(it);
                } else {
                    String hrefurl = data.getResult().getBannerList().get(position).getHrefurl();
                    Intent it = new Intent(getActivity(), ShouYe_HuoDong.class);
                    it.putExtra("h5", hrefurl);
                    startActivity(it);
                }
            }
        });
        pingtaijieshao12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itw = new Intent(getActivity(), PingTaJieShao.class);
                startActivity(itw);
            }
        });
        anquanbaozhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), AnQuanBaoZhang.class);
                startActivity(it);
            }
        });
        xingxipilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), XinXiPiLu.class);
                startActivity(it);
            }
        });
        wangdaiketang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), WangDaiKeTang.class);
                startActivity(it);
            }
        });
        fx_gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(user_id);
                MyLog.e("sdasd", "" + s);
                if (s.equals("0")) {
                    Intent it = new Intent(getActivity(), WoDe_DengRu.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(getActivity(), JiFenDuiHuan.class);
                    startActivity(it);
                }
            }
        });
    }

    private void getfaxiaid() {
        pingtaijieshao12 = getActivity().findViewById(R.id.pintaijieshao1);
        anquanbaozhang = getActivity().findViewById(R.id.anquanbaozhang);
        xingxipilu = getActivity().findViewById(R.id.xinxipilu);
        wangdaiketang = getActivity().findViewById(R.id.wangdaiketang);
        fx_gengduo = getActivity().findViewById(R.id.fx_gengduo);
        myrecview = getActivity().findViewById(R.id.myrecycerview);
        fanxian_banner = getActivity().findViewById(R.id.faxian_banner);
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
    }
}
