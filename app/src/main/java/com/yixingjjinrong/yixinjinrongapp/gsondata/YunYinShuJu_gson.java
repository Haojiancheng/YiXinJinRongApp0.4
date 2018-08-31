package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class YunYinShuJu_gson {

    /**
     * message : 成功了
     * result : {"formattedDate":"2018年07月31日","list":[{"xiangmuyuqi":null,"renjunchujie":10292.45,"stocking":null,"fenjiyuqi":null,"daichangjine":"95.29%","jiedaiyue":227400,"zuidazhanbi":"24.05%","chujierenSum":53,"dangqianjieSum":13,"leijidaichang":42999.53,"incestreturn":0,"buhanSum":null,"moneryyuqi":null,"monerythree":null,"id":46,"times":"2018-07-31","dangqianchuSum":34,"relevance":null,"daichangcishu":44,"jiedaiyuecishu":289,"yuqimonery":null,"jiekuanqixian":908.85,"stockinglixi":null,"ziranlimit":1.14,"fenbu":null,"jiekuanrenSum":15,"danhuzhanbi":"0.17%","yuqiSum":null,"buhanMonery":null,"xiangmuqi":null,"yuqi":null,"lixiyue":2161.19,"monerytwo":null,"meanwhen":15,"jiaoyicishu":661,"monerylv":null,"jiekuancost":0.08,"touzilimit":1.03,"renjunjiekuan":36366.67,"renjunlimit":1.75,"jiekuanlilv":"5.27","zong":545500,"relevanceSum":null,"tenchujiezhanbi":"0.76%","farenlimit":1.99,"touzidedaline":327,"xiangmuyu":null}],"buhanSum":"0"}
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
         * formattedDate : 2018年07月31日
         * list : [{"xiangmuyuqi":null,"renjunchujie":10292.45,"stocking":null,"fenjiyuqi":null,"daichangjine":"95.29%","jiedaiyue":227400,"zuidazhanbi":"24.05%","chujierenSum":53,"dangqianjieSum":13,"leijidaichang":42999.53,"incestreturn":0,"buhanSum":null,"moneryyuqi":null,"monerythree":null,"id":46,"times":"2018-07-31","dangqianchuSum":34,"relevance":null,"daichangcishu":44,"jiedaiyuecishu":289,"yuqimonery":null,"jiekuanqixian":908.85,"stockinglixi":null,"ziranlimit":1.14,"fenbu":null,"jiekuanrenSum":15,"danhuzhanbi":"0.17%","yuqiSum":null,"buhanMonery":null,"xiangmuqi":null,"yuqi":null,"lixiyue":2161.19,"monerytwo":null,"meanwhen":15,"jiaoyicishu":661,"monerylv":null,"jiekuancost":0.08,"touzilimit":1.03,"renjunjiekuan":36366.67,"renjunlimit":1.75,"jiekuanlilv":"5.27","zong":545500,"relevanceSum":null,"tenchujiezhanbi":"0.76%","farenlimit":1.99,"touzidedaline":327,"xiangmuyu":null}]
         * buhanSum : 0
         */

        private String formattedDate;
        private String buhanSum;
        private List<ListBean> list;

        public String getFormattedDate() {
            return formattedDate;
        }

        public void setFormattedDate(String formattedDate) {
            this.formattedDate = formattedDate;
        }

        public String getBuhanSum() {
            return buhanSum;
        }

        public void setBuhanSum(String buhanSum) {
            this.buhanSum = buhanSum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * xiangmuyuqi : null
             * renjunchujie : 10292.45
             * stocking : null
             * fenjiyuqi : null
             * daichangjine : 95.29%
             * jiedaiyue : 227400.0
             * zuidazhanbi : 24.05%
             * chujierenSum : 53
             * dangqianjieSum : 13
             * leijidaichang : 42999.53
             * incestreturn : 0.0
             * buhanSum : null
             * moneryyuqi : null
             * monerythree : null
             * id : 46
             * times : 2018-07-31
             * dangqianchuSum : 34
             * relevance : null
             * daichangcishu : 44
             * jiedaiyuecishu : 289
             * yuqimonery : null
             * jiekuanqixian : 908.85
             * stockinglixi : null
             * ziranlimit : 1.14
             * fenbu : null
             * jiekuanrenSum : 15
             * danhuzhanbi : 0.17%
             * yuqiSum : null
             * buhanMonery : null
             * xiangmuqi : null
             * yuqi : null
             * lixiyue : 2161.19
             * monerytwo : null
             * meanwhen : 15
             * jiaoyicishu : 661
             * monerylv : null
             * jiekuancost : 0.08
             * touzilimit : 1.03
             * renjunjiekuan : 36366.67
             * renjunlimit : 1.75
             * jiekuanlilv : 5.27
             * zong : 545500.0
             * relevanceSum : null
             * tenchujiezhanbi : 0.76%
             * farenlimit : 1.99
             * touzidedaline : 327
             * xiangmuyu : null
             */

            private Object xiangmuyuqi;
            private double renjunchujie;
            private Object stocking;
            private Object fenjiyuqi;
            private String daichangjine;
            private double jiedaiyue;
            private String zuidazhanbi;
            private int chujierenSum;
            private int dangqianjieSum;
            private double leijidaichang;
            private double incestreturn;
            private Object buhanSum;
            private Object moneryyuqi;
            private Object monerythree;
            private int id;
            private String times;
            private int dangqianchuSum;
            private Object relevance;
            private int daichangcishu;
            private int jiedaiyuecishu;
            private Object yuqimonery;
            private double jiekuanqixian;
            private Object stockinglixi;
            private double ziranlimit;
            private Object fenbu;
            private int jiekuanrenSum;
            private String danhuzhanbi;
            private Object yuqiSum;
            private Object buhanMonery;
            private Object xiangmuqi;
            private Object yuqi;
            private double lixiyue;
            private Object monerytwo;
            private int meanwhen;
            private int jiaoyicishu;
            private Object monerylv;
            private double jiekuancost;
            private double touzilimit;
            private double renjunjiekuan;
            private double renjunlimit;
            private String jiekuanlilv;
            private double zong;
            private Object relevanceSum;
            private String tenchujiezhanbi;
            private double farenlimit;
            private int touzidedaline;
            private Object xiangmuyu;

            public Object getXiangmuyuqi() {
                return xiangmuyuqi;
            }

            public void setXiangmuyuqi(Object xiangmuyuqi) {
                this.xiangmuyuqi = xiangmuyuqi;
            }

            public double getRenjunchujie() {
                return renjunchujie;
            }

            public void setRenjunchujie(double renjunchujie) {
                this.renjunchujie = renjunchujie;
            }

            public Object getStocking() {
                return stocking;
            }

            public void setStocking(Object stocking) {
                this.stocking = stocking;
            }

            public Object getFenjiyuqi() {
                return fenjiyuqi;
            }

            public void setFenjiyuqi(Object fenjiyuqi) {
                this.fenjiyuqi = fenjiyuqi;
            }

            public String getDaichangjine() {
                return daichangjine;
            }

            public void setDaichangjine(String daichangjine) {
                this.daichangjine = daichangjine;
            }

            public double getJiedaiyue() {
                return jiedaiyue;
            }

            public void setJiedaiyue(double jiedaiyue) {
                this.jiedaiyue = jiedaiyue;
            }

            public String getZuidazhanbi() {
                return zuidazhanbi;
            }

            public void setZuidazhanbi(String zuidazhanbi) {
                this.zuidazhanbi = zuidazhanbi;
            }

            public int getChujierenSum() {
                return chujierenSum;
            }

            public void setChujierenSum(int chujierenSum) {
                this.chujierenSum = chujierenSum;
            }

            public int getDangqianjieSum() {
                return dangqianjieSum;
            }

            public void setDangqianjieSum(int dangqianjieSum) {
                this.dangqianjieSum = dangqianjieSum;
            }

            public double getLeijidaichang() {
                return leijidaichang;
            }

            public void setLeijidaichang(double leijidaichang) {
                this.leijidaichang = leijidaichang;
            }

            public double getIncestreturn() {
                return incestreturn;
            }

            public void setIncestreturn(double incestreturn) {
                this.incestreturn = incestreturn;
            }

            public Object getBuhanSum() {
                return buhanSum;
            }

            public void setBuhanSum(Object buhanSum) {
                this.buhanSum = buhanSum;
            }

            public Object getMoneryyuqi() {
                return moneryyuqi;
            }

            public void setMoneryyuqi(Object moneryyuqi) {
                this.moneryyuqi = moneryyuqi;
            }

            public Object getMonerythree() {
                return monerythree;
            }

            public void setMonerythree(Object monerythree) {
                this.monerythree = monerythree;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public int getDangqianchuSum() {
                return dangqianchuSum;
            }

            public void setDangqianchuSum(int dangqianchuSum) {
                this.dangqianchuSum = dangqianchuSum;
            }

            public Object getRelevance() {
                return relevance;
            }

            public void setRelevance(Object relevance) {
                this.relevance = relevance;
            }

            public int getDaichangcishu() {
                return daichangcishu;
            }

            public void setDaichangcishu(int daichangcishu) {
                this.daichangcishu = daichangcishu;
            }

            public int getJiedaiyuecishu() {
                return jiedaiyuecishu;
            }

            public void setJiedaiyuecishu(int jiedaiyuecishu) {
                this.jiedaiyuecishu = jiedaiyuecishu;
            }

            public Object getYuqimonery() {
                return yuqimonery;
            }

            public void setYuqimonery(Object yuqimonery) {
                this.yuqimonery = yuqimonery;
            }

            public double getJiekuanqixian() {
                return jiekuanqixian;
            }

            public void setJiekuanqixian(double jiekuanqixian) {
                this.jiekuanqixian = jiekuanqixian;
            }

            public Object getStockinglixi() {
                return stockinglixi;
            }

            public void setStockinglixi(Object stockinglixi) {
                this.stockinglixi = stockinglixi;
            }

            public double getZiranlimit() {
                return ziranlimit;
            }

            public void setZiranlimit(double ziranlimit) {
                this.ziranlimit = ziranlimit;
            }

            public Object getFenbu() {
                return fenbu;
            }

            public void setFenbu(Object fenbu) {
                this.fenbu = fenbu;
            }

            public int getJiekuanrenSum() {
                return jiekuanrenSum;
            }

            public void setJiekuanrenSum(int jiekuanrenSum) {
                this.jiekuanrenSum = jiekuanrenSum;
            }

            public String getDanhuzhanbi() {
                return danhuzhanbi;
            }

            public void setDanhuzhanbi(String danhuzhanbi) {
                this.danhuzhanbi = danhuzhanbi;
            }

            public Object getYuqiSum() {
                return yuqiSum;
            }

            public void setYuqiSum(Object yuqiSum) {
                this.yuqiSum = yuqiSum;
            }

            public Object getBuhanMonery() {
                return buhanMonery;
            }

            public void setBuhanMonery(Object buhanMonery) {
                this.buhanMonery = buhanMonery;
            }

            public Object getXiangmuqi() {
                return xiangmuqi;
            }

            public void setXiangmuqi(Object xiangmuqi) {
                this.xiangmuqi = xiangmuqi;
            }

            public Object getYuqi() {
                return yuqi;
            }

            public void setYuqi(Object yuqi) {
                this.yuqi = yuqi;
            }

            public double getLixiyue() {
                return lixiyue;
            }

            public void setLixiyue(double lixiyue) {
                this.lixiyue = lixiyue;
            }

            public Object getMonerytwo() {
                return monerytwo;
            }

            public void setMonerytwo(Object monerytwo) {
                this.monerytwo = monerytwo;
            }

            public int getMeanwhen() {
                return meanwhen;
            }

            public void setMeanwhen(int meanwhen) {
                this.meanwhen = meanwhen;
            }

            public int getJiaoyicishu() {
                return jiaoyicishu;
            }

            public void setJiaoyicishu(int jiaoyicishu) {
                this.jiaoyicishu = jiaoyicishu;
            }

            public Object getMonerylv() {
                return monerylv;
            }

            public void setMonerylv(Object monerylv) {
                this.monerylv = monerylv;
            }

            public double getJiekuancost() {
                return jiekuancost;
            }

            public void setJiekuancost(double jiekuancost) {
                this.jiekuancost = jiekuancost;
            }

            public double getTouzilimit() {
                return touzilimit;
            }

            public void setTouzilimit(double touzilimit) {
                this.touzilimit = touzilimit;
            }

            public double getRenjunjiekuan() {
                return renjunjiekuan;
            }

            public void setRenjunjiekuan(double renjunjiekuan) {
                this.renjunjiekuan = renjunjiekuan;
            }

            public double getRenjunlimit() {
                return renjunlimit;
            }

            public void setRenjunlimit(double renjunlimit) {
                this.renjunlimit = renjunlimit;
            }

            public String getJiekuanlilv() {
                return jiekuanlilv;
            }

            public void setJiekuanlilv(String jiekuanlilv) {
                this.jiekuanlilv = jiekuanlilv;
            }

            public double getZong() {
                return zong;
            }

            public void setZong(double zong) {
                this.zong = zong;
            }

            public Object getRelevanceSum() {
                return relevanceSum;
            }

            public void setRelevanceSum(Object relevanceSum) {
                this.relevanceSum = relevanceSum;
            }

            public String getTenchujiezhanbi() {
                return tenchujiezhanbi;
            }

            public void setTenchujiezhanbi(String tenchujiezhanbi) {
                this.tenchujiezhanbi = tenchujiezhanbi;
            }

            public double getFarenlimit() {
                return farenlimit;
            }

            public void setFarenlimit(double farenlimit) {
                this.farenlimit = farenlimit;
            }

            public int getTouzidedaline() {
                return touzidedaline;
            }

            public void setTouzidedaline(int touzidedaline) {
                this.touzidedaline = touzidedaline;
            }

            public Object getXiangmuyu() {
                return xiangmuyu;
            }

            public void setXiangmuyu(Object xiangmuyu) {
                this.xiangmuyu = xiangmuyu;
            }
        }
    }
}
