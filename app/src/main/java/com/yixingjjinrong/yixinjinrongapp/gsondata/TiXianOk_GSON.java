package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class TiXianOk_GSON {


    /**
     * message : 提现成功！
     * result : {"userId":10985,"money":"200","token":"login:864711326104376","loginId":"login:10985"}
     * state : success
     * html : <html> <head><title>sender</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> <body><form id="editForm" name="editForm" action="http://newpay-dev-test.yxb.com/fuyouwap/transWithDataApp.do" method="post"><input type="hidden" name="amount" value="19950"/><input type="hidden" name="webUrl" value="http://newwei.yxb.com/yxbApp/frontWithdrawUrlApp.do"/><input type="hidden" name="phone" value="13111111111"/><input type="hidden" name="backUrl" value="http://newwei.yxb.com/yxbApp/frontWithdrawBckUrlApp.do"/><input type="hidden" name="userId" value="10985"/><input type="hidden" name="txn_mnss" value="ZJWD20180802162647631609"/><input type="submit" value="提交" style="display:none;"></form><script>document.forms[0].submit();</script> </body></html>
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
