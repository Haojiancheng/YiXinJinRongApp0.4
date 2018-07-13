package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class ChongZhiOk_GSon {

    /**
     * state : success
     * html : <html> <head><title>sender</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> <body><form id="editForm" name="editForm" action="http://newpay-dev-test.yxb.com/fuyouwap/transRegDataQuApp.do" method="post"><input type="hidden" name="amount" value="23000"/><input type="hidden" name="webUrl" value="http://192.168.1.79:8080/yxb_mobile/yxbApp/frontFuonlineUrlApp.do"/><input type="hidden" name="phone" value="17720181111"/><input type="hidden" name="backUrl" value="http://192.168.1.79:8080/yxb_mobile/yxbApp/bckFuonlineUrlApp.do"/><input type="hidden" name="userId" value="11208"/><input type="hidden" name="formUrl" value="http://newpay-dev-test.yxb.com/fuyouwap/transRegDataQuApp.do"/><input type="hidden" name="txn_mnss" value="ZJFR20180711133106360S1402"/><input type="submit" value="提交" style="display:none;"></form><script>document.forms[0].submit();</script> </body></html>
     */

    private String state;
    private String html;

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
