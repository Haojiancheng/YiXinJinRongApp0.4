package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class CunGuan_gson {

    /**
     * message : 存管账号开通成功
     * result : {"html":"<html> <head><title>sender<\/title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><\/head> <body><form id=\"editForm\" name=\"editForm\" action=\"http://newpay-dev-test.yxb.com/fuyouwap/appWebReg.do         \" method=\"post\"><input type=\"hidden\" name=\"idNo\" value=\"140225199106013724\"/><input type=\"hidden\" name=\"webUrl\" value=\"http://192.168.1.84:8080/yxb_mobile/yxbApp/fuRegUserUrl.do\"/><input type=\"hidden\" name=\"phone\" value=\"18888888881\"/><input type=\"hidden\" name=\"backUrl\" value=\"http://192.168.1.84:8080/yxb_mobile/yxbApp/fuRegUserBck.do\"/><input type=\"hidden\" name=\"userId\" value=\"11736\"/><input type=\"hidden\" name=\"realname\" value=\"尚泽慧\"/><input type=\"hidden\" name=\"txn_mnss\" value=\"ZJREG201806271106320500\"/><input type=\"submit\" value=\"提交\" style=\"display:none;\"><\/form><script>document.forms[0].submit();<\/script> <\/body><\/html>"}
     * state : success
     */

    private String message;
    private ResultBean result;
    private String state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class ResultBean {
        /**
         * html : <html> <head><title>sender</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> <body><form id="editForm" name="editForm" action="http://newpay-dev-test.yxb.com/fuyouwap/appWebReg.do         " method="post"><input type="hidden" name="idNo" value="140225199106013724"/><input type="hidden" name="webUrl" value="http://192.168.1.84:8080/yxb_mobile/yxbApp/fuRegUserUrl.do"/><input type="hidden" name="phone" value="18888888881"/><input type="hidden" name="backUrl" value="http://192.168.1.84:8080/yxb_mobile/yxbApp/fuRegUserBck.do"/><input type="hidden" name="userId" value="11736"/><input type="hidden" name="realname" value="尚泽慧"/><input type="hidden" name="txn_mnss" value="ZJREG201806271106320500"/><input type="submit" value="提交" style="display:none;"></form><script>document.forms[0].submit();</script> </body></html>
         */

        private String html;

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
