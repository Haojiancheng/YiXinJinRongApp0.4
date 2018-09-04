package com.yixingjjinrong.yixinjinrongapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.gsondata.DaoHang_GSON;
import com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye.ViewPagerAdapter;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class Guide_Pager extends AutoLayoutActivity {

    private DaoHang_GSON data;
    private String picurl;
    private String[] mypic;
    private String picpath;
    private Button skip;
    private ViewPager mVp;
    private List<String> mylist = new ArrayList<>();
    private String tag = "guide";
    private List<ImageView> myimglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide__pager);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();
        //http://newweichat-stg-test.yxb.com/yxbApp/getGuideBanaerImg.do

        gethttp();
        initView();


    }


    private void gethttp() {
//        OkHttpUtils.postString()
//                .url(Urls.BASE_URL + "yxbApp/getGuideBanaerImg.do")
//                .content("")
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("导航页", response);
//                        data = new Gson().fromJson(response, DaoHang_GSON.class);
//                        String paht = data.getResult().getPath();
//                        Log.e("TAG", "Path:" + paht);
//
//                        for (int i = 0; i < data.getResult().getImgList().size(); i++) {
//                            picurl = data.getResult().getImgList().get(i).getPicurl();
//                            Log.e("TAG", "url:" + picurl);
//                            mypic = new String[data.getResult().getImgList().size()];
//                        }
//                        for (int i = 0; i < data.getResult().getImgList().size(); i++) {
//                            //地址
//                            picpath = paht + data.getResult().getImgList().get(i).getPicurl();
//                            Log.e("TAG", "url:" + picpath);
//                            mypic[i] = picpath;
//                        }
//                        for (String s2 : mypic) {
//                            mylist.add(s2);
//                        }
//
//                    }
//                });
        //
        mylist.add("https://wechat.yxb.com/upload/imageManage/20180802/201808021735379441.png");
        mylist.add("https://wechat.yxb.com/upload/imageManage/20180802/201808021736048895.png");
        mylist.add("https://wechat.yxb.com/upload/imageManage/20180802/201808021736298526.png");
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.mVp);
        skip = (Button) findViewById(R.id.mBtn_skip);
        myimglist = new ArrayList<>();
        for (int i = 0; i < mylist.size(); i++) {
            ImageView iv = new ImageView(this);
            Glide.with(this).load(mylist.get(i)).into(iv);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            myimglist.add(iv);
        }

        mVp.setAdapter(new ViewPagerAdapter(myimglist,mVp));
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position==mylist.size()-1){
                    skip.setVisibility(View.VISIBLE);
                }else{
                    skip.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("First", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isFirst", false);
                edit.commit();
                Intent intent = new Intent(Guide_Pager.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
