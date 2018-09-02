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
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yixingjjinrong.yixinjinrongapp.MainActivity;
import com.yixingjjinrong.yixinjinrongapp.R;
import com.zhy.autolayout.AutoLayoutActivity;


public class BaoGaoXiangQing extends AutoLayoutActivity {
    private WebView wb;
    private TextView bg_title;
    private ImageView bgxq_fh;
    private String pdfurl;
    private String bgtitle;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_gao_xiang_qing);
        ImmersionBar.with(this)
                .transparentBar()
                .fullScreen(false)
                .init();

        getid();
        getPermission();

//        pdfView.fromFile(new File(pdfurl)).load();
        getweb();
    }

    private void getweb() {
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
        wb.loadUrl(pdfurl);

    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 获取动态权限
     */

    private void getPermission() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(BaoGaoXiangQing.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(BaoGaoXiangQing.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("yx", "get permission");
                ActivityCompat.requestPermissions(BaoGaoXiangQing.this,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
            Log.d("yx", "get permission2");
            ActivityCompat.requestPermissions(BaoGaoXiangQing.this,
                    PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        Log.d("yx", "wait for PERMISSION_GRANTED");
        while ((ContextCompat.checkSelfPermission(BaoGaoXiangQing.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
        }
        Log.d("yx", "wait for PERMISSION_GRANTED finish");
    }



    private void getid() {
        wb = findViewById(R.id.wv);
        bg_title = findViewById(R.id.bg_title);
        bgxq_fh = findViewById(R.id.bgxq_fh);
        Intent it = getIntent();
        pdfurl = it.getStringExtra("pdfurl");
        bgtitle = it.getStringExtra("bgtitle");
        bg_title.setText(bgtitle);
    }


}
