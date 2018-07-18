package com.yixingjjinrong.yixinjinrongapp.wode.h5;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {
    //   * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("温馨提示")
                .setMessage(message)
                .setPositiveButton("确定", null);

        // 不需要绑定按键事件
        // 屏蔽keycode等于84之类的按键
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        // 禁止响应按back键的事件
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
        return true;
    }

    /**
     * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
     */
    public boolean onJsConfirm(WebView view, String url, String message,
                               final JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("温馨提示")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                result.cancel();
            }
        });
        // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
        // 禁止响应按back键的事件
        // builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
    }
}
