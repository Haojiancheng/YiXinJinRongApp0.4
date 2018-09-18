package com.yixingjjinrong.yixinjinrongapp.xiangmuyemian.faxian.faxianerji.xinxipilu.xinxipilufragment.baogaoPDF;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.yixingjjinrong.yixinjinrongapp.utils.MyLog;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;


public class BaoGaoXiangQing extends AutoLayoutActivity implements OnPageChangeListener {
    private PDFView pdfView;
    private TextView bg_title;
    private ImageView bgxq_fh;
    private String pdfurl;
    private String bgtitle;
    //    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    Integer pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_gao_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getid();
        bgxq_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (bgtitle.equals("2017审计报告")){
            MyLog.e("审计报告","2017" );
            getweb("sj2017.pdf");
        }else {
            MyLog.e("审计报告","2016" );
            getweb("sj2016.pdf");

        }
    }

    private void getweb(String num) {
        pdfView.fromAsset(num) //pdf地址
                .defaultPage(0)//默认页面
                .enableDoubletap(true)
                .swipeHorizontal(true)//是不是横向查看
                .onPageChange(this)
                .enableSwipe(true)
                .load();

    }
//
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//
//    /**
//     * 获取动态权限
//     */
//
//    private void getPermission() {
//        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(BaoGaoXiangQing.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE);
//        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//            if (!ActivityCompat.shouldShowRequestPermissionRationale(BaoGaoXiangQing.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Log.d("yx", "get permission");
//                ActivityCompat.requestPermissions(BaoGaoXiangQing.this,
//                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//            }
//            Log.d("yx", "get permission2");
//            ActivityCompat.requestPermissions(BaoGaoXiangQing.this,
//                    PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//        }
//        Log.d("yx", "wait for PERMISSION_GRANTED");
//        while ((ContextCompat.checkSelfPermission(BaoGaoXiangQing.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
//        }
//        Log.d("yx", "wait for PERMISSION_GRANTED finish");
//    }


    private void getid() {
        pdfView = findViewById(R.id.pdfView);
        bg_title = findViewById(R.id.bg_title);
        bgxq_fh = findViewById(R.id.bgxq_fh);
        Intent it = getIntent();
        pdfurl = it.getStringExtra("pdfurl");
        bgtitle = it.getStringExtra("bgtitle");
        bg_title.setText(bgtitle);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;

    }
}
