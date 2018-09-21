package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.shouye;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.application.MyApplication;
import com.yixingjjinrong.yixinjinrongapp.application.Urls;
import com.yixingjjinrong.yixinjinrongapp.eventbus_data.LookMore;
import com.yixingjjinrong.yixinjinrongapp.gsondata.ShouYe_Gson;
import com.yixingjjinrong.yixinjinrongapp.mybaseadapter.ShouYe_MyBaseAdapter;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.yixingjjinrong.yixinjinrongapp.utils.SPUtils;
import com.yixingjjinrong.yixinjinrongapp.utils.ToastUtils;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private int user_id;


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
                        ToastUtils.showToast(getActivity(),"无网络，请稍后再试" );
                        MyLog.e("我的ROON",""+e );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyLog.e("TAG", "" + response);
                        data = new Gson().fromJson(response, ShouYe_Gson.class);
                        String paht = data.getResult().getPath();
                        MyLog.e("TAG", "Path:" + paht);

                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picurl = data.getResult().getBannerList().get(i).getPicurl();
                            MyLog.e("TAG", "url:" + picurl);
                            mypic = new String[data.getResult().getBannerList().size()];
                        }
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            picpath = paht + data.getResult().getBannerList().get(i).getPicurl();//地址
                            MyLog.e("TAG", "url:" + picpath);
                            mypic[i] = picpath;
                        }
                        for (int i = 0; i < data.getResult().getBannerList().size(); i++) {
                            MyLog.e("TAG", ">>>URL:" + mypic[i].toString());
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
                                String hrefurl1 = data.getResult().getBannerList().get(position).getHrefurl();
                                if (hrefurl1.indexOf("userId=")!=-1){
                                    String hrefurl = data.getResult().getBannerList().get(position).getHrefurl();
                                    Intent it=new Intent(getActivity(), ShouYe_HuoDong.class);
                                    it.putExtra("h5", hrefurl+user_id);
                                    startActivity(it);
                                }else {
                                    String hrefurl = data.getResult().getBannerList().get(position).getHrefurl();
                                    Intent it=new Intent(getActivity(), ShouYe_HuoDong.class);
                                    it.putExtra("h5", hrefurl);
                                    startActivity(it);
                                }

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
                                MyLog.e("TASG","立即出借id:"+xiangmu_id);
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
        user_id = (int) SPUtils.get(getActivity(), "userId", 0);
        MyLog.e("我的Idd", ""+user_id);
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

    /**
     * 检查服务器端版本号
     */
//    private class CheckServerVersion implements Runnable {
//        @Override
//        public void run() {
//            OkHttpUtils.get()
//                    .url(getString(R.string.base_url) + "statistic/user/getVersion.json")
//                    .build()
//                    .execute(new StringCallback() {
//                        private double vercode;
//
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            Log.i("返回版本数据",response);
//                            versionDataBean = JsonUtil.json2Bean(response, VersionDataBean.class);
//                            vercode = MyApplication.version;//获取版本号
//                            try {
//                                double serverVersionCode = Double.valueOf(versionDataBean.getApp_version());
//                                Log.i("本地版本号:", vercode +"");
//                                Log.i("服务器版本号:", serverVersionCode +"");
//                                if (vercode < serverVersionCode) {
//                                    Message showUpdateDialog = Message.obtain();
//                                    showUpdateDialog.what = SHOW_UPDATE_DIALOG;
//                                    handler.sendMessage(showUpdateDialog);
//                                } else {
//
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//        }
//    }
//
//    /**
//     * 弹出提示更新的dialog
//     */
//    private void showUpdateDialog() {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setCancelable(false);
//        dialog.setTitle("版本更新提示");
//        dialog.setMessage(SharedPreferencesUtil.getString(MainActivity.this,"version_mark",""));
////        dialog.setNegativeButton("暫不更新", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                //跳转到登录界面
//////                finish();
////            }
////        });
//        dialog.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //从服务器端下载最新apk
//                downloadApk();
//            }
//        });
//        dialog.show();
//    }
//
//    /**
//     * 从服务器端下载最新apk
//     */
//    private void downloadApk() {
//        //显示下载进度
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        dialog.setCancelable(false);
//        dialog.show();
//
//        //访问网络下载apk
//        new Thread(new DownloadApk(dialog)).start();
//    }
//
//    /**
//     * 访问网络下载apk
//     */
//    private class DownloadApk implements Runnable {
//        private ProgressDialog dialog;
//        InputStream is;
//        FileOutputStream fos;
//
//        public DownloadApk(ProgressDialog dialog) {
//            this.dialog = dialog;
//        }
//
//        @Override
//        public void run() {
//            OkHttpClient client = new OkHttpClient();
////                String url = versionDataBean.VERSION_URL;
//            Request request = new Request.Builder().get().url(versionDataBean.getApp_file()).build();
//            try {
//                Log.i("这里的apk是什么?:",versionDataBean.getApp_file());
//                Response response = client.newCall(request).execute();
//                if (response.isSuccessful()) {
//                    Log.i("数租长度",response.body().contentLength()+"");
//                    //获取内容总长度
//                    long contentLength = response.body().contentLength();
//                    //设置最大值
//                    int length = (int)contentLength/(1024 * 2);
//                    dialog.setMax(length);
//                    //保存到sd卡
//                    File apkFile = new File(Environment.getExternalStorageDirectory(), getString(R.string.app_name) + ".apk");
//                    fos = new FileOutputStream(apkFile);
//                    //获得输入流
//                    is = response.body().byteStream();
//                    //定义缓冲区大小
//                    byte[] bys = new byte[1024];
//                    int progress = 0;
//                    int len = -1;
//                    while ((len = is.read(bys)) != -1) {
//                        try {
//                            Thread.sleep(1);
//                            fos.write(bys, 0, len);
//                            fos.flush();
//                            progress += len;
//                            //设置进度
//                            int progress1 = progress/(1024 * 2);
//                            dialog.setProgress(progress1);
//                        } catch (Exception e) {
//                            Message msg = Message.obtain();
//                            msg.what = SHOW_ERROR;
//                            msg.obj = "ERROR:10002";
//                            handler.sendMessage(msg);
//                        }
//                    }
//                    //下载完成,提示用户安装
//                    installApk(apkFile);
//                }
//            } catch (Exception e) {
//                Message msg = Message.obtain();
//                msg.what = SHOW_ERROR;
//                msg.obj = "网络异常";
//                handler.sendMessage(msg);
//            } finally {
//                //关闭io流
//                if (is != null) {
//                    try {
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    is = null;
//                }
//                if (fos != null) {
//                    try {
//                        fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    fos = null;
//                }
//            }
//            dialog.dismiss();
//        }
//    }
//
//    /**
//     * 下载完成,提示用户安装
//     */
//    private void installApk(File file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri data;
//        // 判断版本大于等于7.0
//        // 判断版本大于等于7.0
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
//            data = FileProvider.getUriForFile(getActivity(), "com.yixingjjinrong.yixinjinrongapp.fileprovider", file);
//            // 给目标应用一个临时授权
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        } else {
//            data = Uri.fromFile(file);
//        }
//        intent.setDataAndType(data, "application/vnd.android.package-archive");
//        this.startActivity(intent);
//    }

}
