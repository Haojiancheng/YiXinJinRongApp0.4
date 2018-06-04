package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ShouYe_MyBaseAdapter;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.NoticeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Shouye extends Fragment {

    //公告栏
    private NoticeView noticeView, noticeTime;
    //更多项目的跳转
    private TextView gengduo;
    private Banner bann;
    //    private String[] images = {};
    private String picurl;
    private String picpath;
    private ListView mylistview;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> mylist = new ArrayList<>();
    private ShouYe_MyBaseAdapter adapter;
    private boolean isGetData = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye, container, false);
        
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getshouyeid();
        //公告栏
        getgonggao();
        //更多项目的跳转
        getgengduoxiangmu();

        getHttp();
        

    }
  

    private void getshouyeid() {
        mylistview = getActivity().findViewById(R.id.mylist);
    }

    private void getHttp() {
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxb_mobile/yxbApp/yxbAppIndex.do");
        params.setAsJsonContent(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "" + result);
                final ShouYe_Gson data = new Gson().fromJson(result, ShouYe_Gson.class);
                String paht = data.getResult().getPath();
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
                bann = getActivity().findViewById(R.id.shouyetobbanner);
                List<String> list3 = new ArrayList<>();
                for (String s2 : mypic) {
                    list3.add(s2);
                }
                bann.setImageLoader(new GlideImageloader());
                bann.setImages(list3);
                bann.start();

                mylist.addAll(data.getResult().getBorrowList());
                adapter = new ShouYe_MyBaseAdapter(getActivity(), mylist);
                mylistview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

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


    private void getgonggao() {
        //公告栏
        noticeView = getActivity().findViewById(R.id.notice_view);
        noticeTime = getActivity().findViewById(R.id.noticeTime);
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿12564");
        notices.add("家电五折团，抢十亿无门槛现金红包dddddd");
        notices.add("星球大战剃须刀首发送200元代金券ddddd");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
        //时间
        List<String> notTime = new ArrayList<>();
        notTime.add("2018-09-25");
        notTime.add("2013-06-08");
        notTime.add("2015-05-25");
        noticeTime.addNotice(notTime);
        noticeTime.startFlipping();
    }

    private void getgengduoxiangmu() {
        //更多项目的跳转
        gengduo = getActivity().findViewById(R.id.gengduoxiangmu);
    }

    private class GlideImageloader extends ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }
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
    public void onPause() {
        super.onPause();
        isGetData = false;
    }
}
