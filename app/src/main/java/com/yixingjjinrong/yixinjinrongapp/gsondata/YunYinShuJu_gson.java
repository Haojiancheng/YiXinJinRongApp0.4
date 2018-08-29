package com.yixingjjinrong.yixinjinrongapp.gsondata;

import java.util.List;

public class YunYinShuJu_gson {

    /**
     * message : 成功了
     * result : {"formattedDate":"2018年08月27日","list":[{"xiangmuyuqi":"","renjunchujie":11119.72,"stocking":null,"fenjiyuqi":null,"daichangjine":"97.93%","jiedaiyue":435300,"zuidazhanbi":"34.73%","chujierenSum":71,"dangqianjieSum":15,"leijidaichang":2876.58,"incestreturn":0.01,"buhanSum":"0","moneryyuqi":null,"monerythree":null,"id":154,"times":"2018-08-27","dangqianchuSum":65,"relevance":0,"daichangcishu":20,"jiedaiyuecishu":221,"yuqimonery":0,"jiekuanqixian":0,"stockinglixi":0,"ziranlimit":2.74,"fenbu":null,"jiekuanrenSum":16,"danhuzhanbi":"0.16%","yuqiSum":0,"buhanMonery":0,"xiangmuqi":null,"yuqi":null,"lixiyue":6140.57,"monerytwo":null,"meanwhen":53.33,"jiaoyicishu":323,"monerylv":"","jiekuancost":0.22,"touzilimit":1.11,"renjunjiekuan":49343.75,"renjunlimit":2.9,"jiekuanlilv":"7.05","zong":789500,"relevanceSum":0,"tenchujiezhanbi":"0.67%","farenlimit":0.41,"touzidedaline":379.61,"xiangmuyu":null}]}
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
         * formattedDate : 2018年08月27日
         * list : [{"xiangmuyuqi":"","renjunchujie":11119.72,"stocking":null,"fenjiyuqi":null,"daichangjine":"97.93%","jiedaiyue":435300,"zuidazhanbi":"34.73%","chujierenSum":71,"dangqianjieSum":15,"leijidaichang":2876.58,"incestreturn":0.01,"buhanSum":"0","moneryyuqi":null,"monerythree":null,"id":154,"times":"2018-08-27","dangqianchuSum":65,"relevance":0,"daichangcishu":20,"jiedaiyuecishu":221,"yuqimonery":0,"jiekuanqixian":0,"stockinglixi":0,"ziranlimit":2.74,"fenbu":null,"jiekuanrenSum":16,"danhuzhanbi":"0.16%","yuqiSum":0,"buhanMonery":0,"xiangmuqi":null,"yuqi":null,"lixiyue":6140.57,"monerytwo":null,"meanwhen":53.33,"jiaoyicishu":323,"monerylv":"","jiekuancost":0.22,"touzilimit":1.11,"renjunjiekuan":49343.75,"renjunlimit":2.9,"jiekuanlilv":"7.05","zong":789500,"relevanceSum":0,"tenchujiezhanbi":"0.67%","farenlimit":0.41,"touzidedaline":379.61,"xiangmuyu":null}]
         */

        private String formattedDate;
        private List<ListBean> list;

        public String getFormattedDate() {
            return formattedDate;
        }

        public void setFormattedDate(String formattedDate) {
            this.formattedDate = formattedDate;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * xiangmuyuqi :
             * renjunchujie : 11119.72
             * stocking : null
             * fenjiyuqi : null
             * daichangjine : 97.93%
             * jiedaiyue : 435300.0
             * zuidazhanbi : 34.73%
             * chujierenSum : 71
             * dangqianjieSum : 15
             * leijidaichang : 2876.58
             * incestreturn : 0.01
             * buhanSum : 0
             * moneryyuqi : null
             * monerythree : null
             * id : 154
             * times : 2018-08-27
             * dangqianchuSum : 65
             * relevance : 0.0
             * daichangcishu : 20
             * jiedaiyuecishu : 221
             * yuqimonery : 0.0
             * jiekuanqixian : 0.0
             * stockinglixi : 0.0
             * ziranlimit : 2.74
             * fenbu : null
             * jiekuanrenSum : 16
             * danhuzhanbi : 0.16%
             * yuqiSum : 0
             * buhanMonery : 0
             * xiangmuqi : null
             * yuqi : null
             * lixiyue : 6140.57
             * monerytwo : null
             * meanwhen : 53.33
             * jiaoyicishu : 323
             * monerylv :
             * jiekuancost : 0.22
             * touzilimit : 1.11
             * renjunjiekuan : 49343.75
             * renjunlimit : 2.9
             * jiekuanlilv : 7.05
             * zong : 789500.0
             * relevanceSum : 0
             * tenchujiezhanbi : 0.67%
             * farenlimit : 0.41
             * touzidedaline : 379.61
             * xiangmuyu : null
             */

            private String xiangmuyuqi;
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
            private String buhanSum;
            private Object moneryyuqi;
            private Object monerythree;
            private int id;
            private String times;
            private int dangqianchuSum;
            private double relevance;
            private int daichangcishu;
            private int jiedaiyuecishu;
            private double yuqimonery;
            private double jiekuanqixian;
            private double stockinglixi;
            private double ziranlimit;
            private Object fenbu;
            private int jiekuanrenSum;
            private String danhuzhanbi;
            private int yuqiSum;
            private int buhanMonery;
            private Object xiangmuqi;
            private Object yuqi;
            private double lixiyue;
            private Object monerytwo;
            private double meanwhen;
            private int jiaoyicishu;
            private String monerylv;
            private double jiekuancost;
            private double touzilimit;
            private double renjunjiekuan;
            private double renjunlimit;
            private String jiekuanlilv;
            private double zong;
            private int relevanceSum;
            private String tenchujiezhanbi;
            private double farenlimit;
            private double touzidedaline;
            private Object xiangmuyu;

            public String getXiangmuyuqi() {
                return xiangmuyuqi;
            }

            public void setXiangmuyuqi(String xiangmuyuqi) {
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

            public String getBuhanSum() {
                return buhanSum;
            }

            public void setBuhanSum(String buhanSum) {
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

            public double getRelevance() {
                return relevance;
            }

            public void setRelevance(double relevance) {
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

            public double getYuqimonery() {
                return yuqimonery;
            }

            public void setYuqimonery(double yuqimonery) {
                this.yuqimonery = yuqimonery;
            }

            public double getJiekuanqixian() {
                return jiekuanqixian;
            }

            public void setJiekuanqixian(double jiekuanqixian) {
                this.jiekuanqixian = jiekuanqixian;
            }

            public double getStockinglixi() {
                return stockinglixi;
            }

            public void setStockinglixi(double stockinglixi) {
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

            public int getYuqiSum() {
                return yuqiSum;
            }

            public void setYuqiSum(int yuqiSum) {
                this.yuqiSum = yuqiSum;
            }

            public int getBuhanMonery() {
                return buhanMonery;
            }

            public void setBuhanMonery(int buhanMonery) {
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

            public double getMeanwhen() {
                return meanwhen;
            }

            public void setMeanwhen(double meanwhen) {
                this.meanwhen = meanwhen;
            }

            public int getJiaoyicishu() {
                return jiaoyicishu;
            }

            public void setJiaoyicishu(int jiaoyicishu) {
                this.jiaoyicishu = jiaoyicishu;
            }

            public String getMonerylv() {
                return monerylv;
            }

            public void setMonerylv(String monerylv) {
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

            public int getRelevanceSum() {
                return relevanceSum;
            }

            public void setRelevanceSum(int relevanceSum) {
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

            public double getTouzidedaline() {
                return touzidedaline;
            }

            public void setTouzidedaline(double touzidedaline) {
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
