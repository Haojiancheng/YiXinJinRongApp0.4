package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuXinXi_Gson {

    /**
     * message : 成功了
     * result : {"ProjectName":"哈哈啊哈哈哈哈哈哈哈哈哈啊哈哈哈哈","ableTenderDate":"2018-05-29 09:46 ","address":"大范甘迪***********","authentication":{"address_auth":1,"borrowId":2270,"buycar_auth":0,"car_auth":0,"company_auth":0,"credit_auth":1,"danbao_auth":1,"gocar_auth":0,"house_evaluation":1,"house_right_auth":1,"id":481,"person_auth":1,"regist_capital_auth":0},"borrowInfo":"哈哈啊哈哈哈哈哈哈哈哈哈啊哈哈哈哈","borrowPurpose":"测试借款协议","borrowSum":"500.00","deadline":"3个月","endTenderDate":"2018-05-31 09:46 ","hquan":{"communityName":"史蒂芬孙","constructionArea":"100","evaluationPrice":"100万","houseAddress":"东方闪电","referencePrice":"100万","useYears":"3"},"idNo":"433***********9017","mortgageType":"房产抵押","overMoney":{"overMoney":"5.00"},"overMoneys":{"overMoney":"2.50"},"overTimes":{"overTimes":"2"},"paymentMode":"按先息后本还款","ran":3,"realName":"黄**","repaysource":"工资","rxx":{"age":"49","highestEdu":"小学","maritalStatus":"未婚","monthIncome":"10000.00","otherWebStatus":"让更充分","overdueStatus":"的非官方的","profession":"大范甘迪","sex":"男","tradeType":"电饭锅"},"times":{"times":"13"},"trcity":"呼和浩特","trpro":"内蒙古"}
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
         * ProjectName : 哈哈啊哈哈哈哈哈哈哈哈哈啊哈哈哈哈
         * ableTenderDate : 2018-05-29 09:46 
         * address : 大范甘迪***********
         * authentication : {"address_auth":1,"borrowId":2270,"buycar_auth":0,"car_auth":0,"company_auth":0,"credit_auth":1,"danbao_auth":1,"gocar_auth":0,"house_evaluation":1,"house_right_auth":1,"id":481,"person_auth":1,"regist_capital_auth":0}
         * borrowInfo : 哈哈啊哈哈哈哈哈哈哈哈哈啊哈哈哈哈
         * borrowPurpose : 测试借款协议
         * borrowSum : 500.00
         * deadline : 3个月
         * endTenderDate : 2018-05-31 09:46 
         * hquan : {"communityName":"史蒂芬孙","constructionArea":"100","evaluationPrice":"100万","houseAddress":"东方闪电","referencePrice":"100万","useYears":"3"}
         * idNo : 433***********9017
         * mortgageType : 房产抵押
         * overMoney : {"overMoney":"5.00"}
         * overMoneys : {"overMoney":"2.50"}
         * overTimes : {"overTimes":"2"}
         * paymentMode : 按先息后本还款
         * ran : 3.0
         * realName : 黄**
         * repaysource : 工资
         * rxx : {"age":"49","highestEdu":"小学","maritalStatus":"未婚","monthIncome":"10000.00","otherWebStatus":"让更充分","overdueStatus":"的非官方的","profession":"大范甘迪","sex":"男","tradeType":"电饭锅"}
         * times : {"times":"13"}
         * trcity : 呼和浩特
         * trpro : 内蒙古
         */

        private String ProjectName;
        private String ableTenderDate;
        private String address;
        private AuthenticationBean authentication;
        private String borrowInfo;
        private String borrowPurpose;
        private String borrowSum;
        private String deadline;
        private String endTenderDate;
        private HquanBean hquan;
        private String idNo;
        private String mortgageType;
        private OverMoneyBean overMoney;
        private OverMoneysBean overMoneys;
        private OverTimesBean overTimes;
        private String paymentMode;
        private double ran;
        private String realName;
        private String repaysource;
        private RxxBean rxx;
        private TimesBean times;
        private String trcity;
        private String trpro;

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getAbleTenderDate() {
            return ableTenderDate;
        }

        public void setAbleTenderDate(String ableTenderDate) {
            this.ableTenderDate = ableTenderDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public AuthenticationBean getAuthentication() {
            return authentication;
        }

        public void setAuthentication(AuthenticationBean authentication) {
            this.authentication = authentication;
        }

        public String getBorrowInfo() {
            return borrowInfo;
        }

        public void setBorrowInfo(String borrowInfo) {
            this.borrowInfo = borrowInfo;
        }

        public String getBorrowPurpose() {
            return borrowPurpose;
        }

        public void setBorrowPurpose(String borrowPurpose) {
            this.borrowPurpose = borrowPurpose;
        }

        public String getBorrowSum() {
            return borrowSum;
        }

        public void setBorrowSum(String borrowSum) {
            this.borrowSum = borrowSum;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getEndTenderDate() {
            return endTenderDate;
        }

        public void setEndTenderDate(String endTenderDate) {
            this.endTenderDate = endTenderDate;
        }

        public HquanBean getHquan() {
            return hquan;
        }

        public void setHquan(HquanBean hquan) {
            this.hquan = hquan;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(String mortgageType) {
            this.mortgageType = mortgageType;
        }

        public OverMoneyBean getOverMoney() {
            return overMoney;
        }

        public void setOverMoney(OverMoneyBean overMoney) {
            this.overMoney = overMoney;
        }

        public OverMoneysBean getOverMoneys() {
            return overMoneys;
        }

        public void setOverMoneys(OverMoneysBean overMoneys) {
            this.overMoneys = overMoneys;
        }

        public OverTimesBean getOverTimes() {
            return overTimes;
        }

        public void setOverTimes(OverTimesBean overTimes) {
            this.overTimes = overTimes;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public double getRan() {
            return ran;
        }

        public void setRan(double ran) {
            this.ran = ran;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRepaysource() {
            return repaysource;
        }

        public void setRepaysource(String repaysource) {
            this.repaysource = repaysource;
        }

        public RxxBean getRxx() {
            return rxx;
        }

        public void setRxx(RxxBean rxx) {
            this.rxx = rxx;
        }

        public TimesBean getTimes() {
            return times;
        }

        public void setTimes(TimesBean times) {
            this.times = times;
        }

        public String getTrcity() {
            return trcity;
        }

        public void setTrcity(String trcity) {
            this.trcity = trcity;
        }

        public String getTrpro() {
            return trpro;
        }

        public void setTrpro(String trpro) {
            this.trpro = trpro;
        }

        public static class AuthenticationBean {
            /**
             * address_auth : 1
             * borrowId : 2270
             * buycar_auth : 0
             * car_auth : 0
             * company_auth : 0
             * credit_auth : 1
             * danbao_auth : 1
             * gocar_auth : 0
             * house_evaluation : 1
             * house_right_auth : 1
             * id : 481
             * person_auth : 1
             * regist_capital_auth : 0
             */

            private int address_auth;
            private int borrowId;
            private int buycar_auth;
            private int car_auth;
            private int company_auth;
            private int credit_auth;
            private int danbao_auth;
            private int gocar_auth;
            private int house_evaluation;
            private int house_right_auth;
            private int id;
            private int person_auth;
            private int regist_capital_auth;

            public int getAddress_auth() {
                return address_auth;
            }

            public void setAddress_auth(int address_auth) {
                this.address_auth = address_auth;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getBuycar_auth() {
                return buycar_auth;
            }

            public void setBuycar_auth(int buycar_auth) {
                this.buycar_auth = buycar_auth;
            }

            public int getCar_auth() {
                return car_auth;
            }

            public void setCar_auth(int car_auth) {
                this.car_auth = car_auth;
            }

            public int getCompany_auth() {
                return company_auth;
            }

            public void setCompany_auth(int company_auth) {
                this.company_auth = company_auth;
            }

            public int getCredit_auth() {
                return credit_auth;
            }

            public void setCredit_auth(int credit_auth) {
                this.credit_auth = credit_auth;
            }

            public int getDanbao_auth() {
                return danbao_auth;
            }

            public void setDanbao_auth(int danbao_auth) {
                this.danbao_auth = danbao_auth;
            }

            public int getGocar_auth() {
                return gocar_auth;
            }

            public void setGocar_auth(int gocar_auth) {
                this.gocar_auth = gocar_auth;
            }

            public int getHouse_evaluation() {
                return house_evaluation;
            }

            public void setHouse_evaluation(int house_evaluation) {
                this.house_evaluation = house_evaluation;
            }

            public int getHouse_right_auth() {
                return house_right_auth;
            }

            public void setHouse_right_auth(int house_right_auth) {
                this.house_right_auth = house_right_auth;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPerson_auth() {
                return person_auth;
            }

            public void setPerson_auth(int person_auth) {
                this.person_auth = person_auth;
            }

            public int getRegist_capital_auth() {
                return regist_capital_auth;
            }

            public void setRegist_capital_auth(int regist_capital_auth) {
                this.regist_capital_auth = regist_capital_auth;
            }
        }

        public static class HquanBean {
            /**
             * communityName : 史蒂芬孙
             * constructionArea : 100
             * evaluationPrice : 100万
             * houseAddress : 东方闪电
             * referencePrice : 100万
             * useYears : 3
             */

            private String communityName;
            private String constructionArea;
            private String evaluationPrice;
            private String houseAddress;
            private String referencePrice;
            private String useYears;

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public String getConstructionArea() {
                return constructionArea;
            }

            public void setConstructionArea(String constructionArea) {
                this.constructionArea = constructionArea;
            }

            public String getEvaluationPrice() {
                return evaluationPrice;
            }

            public void setEvaluationPrice(String evaluationPrice) {
                this.evaluationPrice = evaluationPrice;
            }

            public String getHouseAddress() {
                return houseAddress;
            }

            public void setHouseAddress(String houseAddress) {
                this.houseAddress = houseAddress;
            }

            public String getReferencePrice() {
                return referencePrice;
            }

            public void setReferencePrice(String referencePrice) {
                this.referencePrice = referencePrice;
            }

            public String getUseYears() {
                return useYears;
            }

            public void setUseYears(String useYears) {
                this.useYears = useYears;
            }
        }

        public static class OverMoneyBean {
            /**
             * overMoney : 5.00
             */

            private String overMoney;

            public String getOverMoney() {
                return overMoney;
            }

            public void setOverMoney(String overMoney) {
                this.overMoney = overMoney;
            }
        }

        public static class OverMoneysBean {
            /**
             * overMoney : 2.50
             */

            private String overMoney;

            public String getOverMoney() {
                return overMoney;
            }

            public void setOverMoney(String overMoney) {
                this.overMoney = overMoney;
            }
        }

        public static class OverTimesBean {
            /**
             * overTimes : 2
             */

            private String overTimes;

            public String getOverTimes() {
                return overTimes;
            }

            public void setOverTimes(String overTimes) {
                this.overTimes = overTimes;
            }
        }

        public static class RxxBean {
            /**
             * age : 49
             * highestEdu : 小学
             * maritalStatus : 未婚
             * monthIncome : 10000.00
             * otherWebStatus : 让更充分
             * overdueStatus : 的非官方的
             * profession : 大范甘迪
             * sex : 男
             * tradeType : 电饭锅
             */

            private String age;
            private String highestEdu;
            private String maritalStatus;
            private String monthIncome;
            private String otherWebStatus;
            private String overdueStatus;
            private String profession;
            private String sex;
            private String tradeType;

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getHighestEdu() {
                return highestEdu;
            }

            public void setHighestEdu(String highestEdu) {
                this.highestEdu = highestEdu;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getMonthIncome() {
                return monthIncome;
            }

            public void setMonthIncome(String monthIncome) {
                this.monthIncome = monthIncome;
            }

            public String getOtherWebStatus() {
                return otherWebStatus;
            }

            public void setOtherWebStatus(String otherWebStatus) {
                this.otherWebStatus = otherWebStatus;
            }

            public String getOverdueStatus() {
                return overdueStatus;
            }

            public void setOverdueStatus(String overdueStatus) {
                this.overdueStatus = overdueStatus;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getTradeType() {
                return tradeType;
            }

            public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }
        }

        public static class TimesBean {
            /**
             * times : 13
             */

            private String times;

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }
        }
    }
}
