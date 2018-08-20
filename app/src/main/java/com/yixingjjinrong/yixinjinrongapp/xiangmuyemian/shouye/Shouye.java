package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

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
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ShouYe_MyBaseAdapter;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.banner_h5.ShouYe_HuoDong;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.NoticeView;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.xiaoxi.ShouYe_GongGao;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class Shouye extends Fragment {

    //公告栏
    private NoticeView noticeView, noticeTime;
    //更多项目的跳转
    private TextView gengduo;
    private Banner bann;
    private String picurl;
    private String picpath;
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private RecyclerView mylistview;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> mylist = new ArrayList<>();
    private List<String> mymasseg=new ArrayList<>();
    private List<String> mymassegtime=new ArrayList<>();
    private ShouYe_MyBaseAdapter adapter;
    private boolean isGetData = false;
    private ShouYe_Gson data;
    private String[] mypic;
    private Button bt_first_chujie;
    private View first_viwe;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye, container, false);
//        ImmersionBar.with(this)
//                .transparentBar()
//                .fullScreen(false)
//                .keyboardEnable(true)
//                .init();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getshouyeid();
        getgengduoxiangmu();

        getHttp();
       

    }


    private void getshouyeid() {
        first_viwe=getActivity().findViewById(R.id.first_viwe);
        bt_first_chujie=getActivity().findViewById(R.id.first_chujie);
        mylistview = getActivity().findViewById(R.id.mylist);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mylistview.setLayoutManager(manager);
        mylistview.setHasFixedSize(true);
        mylistview.setNestedScrollingEnabled(false);
    }

    private void getHttp() {
        OkHttpUtils.postString()
                .url(Urls.BASE_URL + "yxbApp/yxbAppIndex.do")
                .content("")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "" + response);
                        data = new Gson().fromJson(response, ShouYe_Gson.class);
                        String paht = data.getResult().getPath();
                        Log.e("TAG", "Path:" + paht);

                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picurl = data.getResult().getBannerList().get(i).getPicurl();
                            Log.e("TAG", "url:" + picurl);
                            mypic = new String[data.getResult().getBannerList().size()];
                        }
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picpath = paht + data.getResult().getBannerList().get(i).getPicurl();//地址
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
                        bann.setDelayTime(5000);
                        bann.start();
                        bann.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                String hrefurl = data.getResult().getBannerList().get(position).getHrefurl();
                                Intent it=new Intent(getActivity(), ShouYe_HuoDong.class);
                                it.putExtra("h5", hrefurl);
                                startActivity(it);
                            }
                        });

                        mylist.addAll(data.getResult().getBorrowList());
                        adapter = new ShouYe_MyBaseAdapter(getActivity(), mylist);
                        mylistview.setAdapter(adapter);
                        adapter.setonEveryItemClickListener(new ShouYe_MyBaseAdapter.OnEveryItemClickListener() {
                            @Override
                            public void onEveryClick(int position) {
                                String mtype = String.valueOf(mylist.get(position).getMortgageType());
                                String xiangmu_id = mylist.get(position).getBorrowRandomId();
                                Intent intent = new Intent(getActivity(), XiangMuXiangQing.class);
                                intent.putExtra("mortgageType", mtype);
                                intent.putExtra("bt_name", mylist.get(position).getBorrowStatusStr());
                                SPUtils.put(getActivity(), "borroFwRandomId", xiangmu_id);
                                Log.e("TASG","立即出借id:"+xiangmu_id);
                                startActivity(intent);
                            }
                        });

                        adapter.notifyDataSetChanged();
                        mymasseg.clear();
                        mymassegtime.clear();
                        for (int i = 0; i < data.getResult().getPublicMsgList().size(); i++) {
                            mymasseg.add(data.getResult().getPublicMsgList().get(i).getArticle_title());
                        }
                        for (int i = 0; i < data.getResult().getPublicMsgList().size(); i++) {
                            mymassegtime.add(data.getResult().getPublicMsgList().get(i).getArticle_pub_time());
                        }
                        getgonggao();
                    }
                });

    }

    private void getgonggao() {
        //公告栏
        noticeView = getActivity().findViewById(R.id.notice_view);
        noticeTime = getActivity().findViewById(R.id.noticeTime);

        List<String> notices = new ArrayList<>();
        notices.addAll(mymasseg);

        noticeView.addNotice(notices);
        noticeView.startFlipping();

        //时间
        List<String> notTime = new ArrayList<>();
        notTime.addAll(mymassegtime);
        noticeTime.addNotice(notTime);
        noticeTime.startFlipping();
        
        noticeView.setOnNoticeClickListener(new NoticeView.OnNoticeClickListener() {
            @Override
            public void onNotieClick(int position, String notice) {
                int article_link = data.getResult().getPublicMsgList().get(position).getAid();
                Intent it = new Intent(getActivity(), ShouYe_GongGao.class);
                Bundle bundle = new Bundle();
                bundle.putInt("xx_ird", article_link);
                it.putExtra("nottile", data.getResult().getPublicMsgList().get(position).getArticle_title());
                it.putExtras(bundle);
                startActivity(it);

            }
        });

        
    }

    private void getgengduoxiangmu() {
        //更多项目的跳转
        gengduo = getActivity().findViewById(R.id.gengduoxiangmu);
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new LookMore());
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

    @Override
    public void onResume() {
        super.onResume();
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
    public void onPause() {
        super.onPause();
        isGetData = false;
    }
}
