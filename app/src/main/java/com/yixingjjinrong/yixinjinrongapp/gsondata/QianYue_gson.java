package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class QianYue_gson {


    /**
     * message : 成功了
     * result : {"userId":11733,"bankReservedPhone":"13858688638","token":"login:869381039260413","loginId":"login:11733"}
     * state : success
     * html : <html> <head><title>sender</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> <body><form id="editForm" name="editForm" action="http://int-dev-test-newpay.yxb.com/fuyouwap/signCardApp.do" method="post"><input type="hidden" name="webUrl" value="http://newwei.yxb.com/yxbApp/appSignCardNoticeUrls.do"/><input type="hidden" name="phone" value="17338108873"/><input type="hidden" name="bankReservedPhone" value="13858688638"/><input type="hidden" name="userId" value="11733"/><input type="hidden" name="txn_mnss" value="ZJASC20180731094508690"/><input type="submit" value="提交" style="display:none;"></form><script>document.forms[0].submit();</script> </body></html>
     */

    private String message;
    private String result;
    private String state;
    private String html;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
