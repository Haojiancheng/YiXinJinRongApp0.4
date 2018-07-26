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
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.AnQuanBaoZhang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.PingTaJieShao;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.WangDaiKeTang;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.XinXiPiLu;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.jifen_fx.JiFenDuiHuan;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Faxian extends Fragment {
    private TextView pingtaijieshao12, anquanbaozhang, xingxipilu, wangdaiketang, fx_gengduo;
    private Banner fanxian_banner;
    private String sha1;//SHA1加密
    private String base1;//Base64加

    private String picurl;
    private String picpath;
    private RecyclerView myrecview;
    private FaXianBasrAdapter adapter;
    private List<FaXian_Data.ResultBean.GoodsListBean> list = new ArrayList<>();


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

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        myrecview.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        gethppt();
    }

    private void gethppt() {
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/discoveryIndex.do");
        params.setAsJsonContent(true);
//        params.setBodyContent(canshu.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "" + result);
                FaXian_Data data = new Gson().fromJson(result, FaXian_Data.class);
                String paht = data.getResult().getPath();

                /**
                 * RecyclerView
                 */
                list.addAll(data.getResult().getGoodsList());
                adapter = new FaXianBasrAdapter(list, picpath);
                myrecview.setAdapter(adapter);

                adapter.setonEveryItemClickListener(new FaXianBasrAdapter.OnEveryItemClickListener() {
                    @Override
                    public void onEveryClick(int position) {
                        String awardType = String.valueOf(list.get(position).getAwardType());
                        //跳实物


                        //跳兑换券


                    }
                });


                Log.e("TAG", "Path:" + paht);
                for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                    picurl = data.getResult().getBannerList().get(i).getPicurl();
                    Log.e("TAG", "url:" + picurl);

                }
                String[] mypic = new String[data.getResult().getBannerList().size()];
                for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                    picpath = paht + picurl;//地址
                    Log.e("TAG", "url:" + picpath);

                    mypic[i] = picpath;

                }
                for (int i = 0; i < data.getResult().getBannerList().size(); i++) {


                    Log.e("TAG", ">>>URL:" + mypic[i].toString());
                }
                fanxian_banner = getActivity().findViewById(R.id.faxian_banner);
                List<String> list3 = new ArrayList<>();
                for (String s2 : mypic) {
                    list3.add(s2);
                }
                fanxian_banner.setImageLoader(new GlideImageloader());
                fanxian_banner.setImages(list3);
                fanxian_banner.start();


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

    private class GlideImageloader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }
    }

    private void getOnClickq() {
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
                Intent it = new Intent(getActivity(), JiFenDuiHuan.class);
                startActivity(it);
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
    }


}
