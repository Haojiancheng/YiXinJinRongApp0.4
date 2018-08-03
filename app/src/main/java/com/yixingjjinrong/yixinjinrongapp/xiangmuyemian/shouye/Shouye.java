package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.ImageResizerUtils;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYeMassage_Gson;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.jiami.Base64JiaMI;
import com.yixingjjinrong.yixinjinrongapp.jiami.SHA1jiami;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ShouYe_MyBaseAdapter;
import com.yixingjjinrong.yixinjinrongapp.wode.xiaoxi.XiaoXi_XiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.Xiangmuxiangqing.xiangqing.XiangMuXiangQing;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.myView.NoticeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.apache.http.params.HttpParams;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ObjectInputStream;
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
    private String sha1;//SHA1加密
    private String base1;//Base64加
    private ListView mylistview;
    private List<ShouYe_Gson.ResultBean.BorrowListBean> mylist = new ArrayList<>();
    private List<String> mymasseg=new ArrayList<>();
    private List<String> mymassegtime=new ArrayList<>();
    private ShouYe_MyBaseAdapter adapter;
    private boolean isGetData = false;
    private ShouYe_Gson data;
    private String[] mypic;


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
        getgengduoxiangmu();

        getHttp();
        

    }
  

    private void getshouyeid() {
        mylistview = getActivity().findViewById(R.id.mylist);
    }

    private void getHttp() {
        RequestParams params = new RequestParams(Urls.BASE_URL + "yxbApp/yxbAppIndex.do");

        params.setAsJsonContent(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("请稍候...");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "" + result);
                data = new Gson().fromJson(result, ShouYe_Gson.class);
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
                bann.start();

                mylist.addAll(data.getResult().getBorrowList());
                adapter = new ShouYe_MyBaseAdapter(getActivity(), mylist);
                mylistview.setAdapter(adapter);
                mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String xiangmu_id = mylist.get(position).getBorrowRandomId();
                        Log.e("TAG", "+.." + xiangmu_id);
                        Intent it = new Intent(getActivity(), XiangMuXiangQing.class);
                        String mortgageType = String.valueOf(mylist.get(position).getMortgageType()).toString();
                        it.putExtra("xiangmu_id", xiangmu_id);
                        it.putExtra("mortgageType", mortgageType);
                        startActivity(it);
                    }
                });
                adapter.notifyDataSetChanged();
                for (int i = 0; i < data.getResult().getPublicMsgList().size(); i++) {
                    mymasseg.add(data.getResult().getPublicMsgList().get(i).getArticle_title());
                }
                for (int i = 0; i < data.getResult().getPublicMsgList().size(); i++) {
                    mymassegtime.add(data.getResult().getPublicMsgList().get(i).getArticle_pub_time());
                }
                getgonggao();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("抛出异常:\n" + ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
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
        noticeView.setOnNoticeClickListener(new NoticeView.OnNoticeClickListener() {
            @Override
            public void onNotieClick(int position, String notice) {
                int article_link = data.getResult().getPublicMsgList().get(position).getAid();
                Intent it = new Intent(getActivity(), XiaoXi_XiangQing.class);
                Bundle bundle = new Bundle();
                bundle.putInt("xx_ird", article_link);
                it.putExtras(bundle);
                startActivity(it);

            }
        });

        //时间
        List<String> notTime = new ArrayList<>();
        notTime.addAll(mymassegtime);
        noticeTime.addNotice(notTime);
        noticeTime.startFlipping();
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
