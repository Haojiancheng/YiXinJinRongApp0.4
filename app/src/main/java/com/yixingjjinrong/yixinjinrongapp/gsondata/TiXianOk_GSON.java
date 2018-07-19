package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class TiXianOk_GSON {

    /**
     * result : {"userId":11298,"money":"2354"}
     * state : success
     * html : <html> <head><title>sender</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> <body><form id="editForm" name="editForm" action="http://newpay-dev-test.yxb.com/fuyouwap/transWithDataApp.do" method="post"><input type="hidden" name="amount" value="235400"/><input type="hidden" name="webUrl" value="http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawUrlApp.do"/><input type="hidden" name="phone" value="18620180301"/><input type="hidden" name="backUrl" value="http://localhost:8080/yxb_mobile/yxbApp/frontWithdrawBckUrlApp.do"/><input type="hidden" name="userId" value="11298"/><input type="hidden" name="txn_mnss" value="ZJWD20180718100638674449"/><input type="submit" value="提交" style="display:none;"></form><script>document.forms[0].submit();</script> </body></html>
     */

    private String result;
    private String state;
    private String html;

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
