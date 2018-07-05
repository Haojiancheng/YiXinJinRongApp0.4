package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuXinXi_Gson {


    /**
     * message : 成功了
     * result : {"car":null,"borrowInfo":"电饭锅","paymentMode":"","bondsman":{"professiond":"","addressd":"","monthIncomed":"","idNod":"341***********7439","tradeTyped":"","sexd":"男","trcityd":"","realNamed":"茅**","birthd":"42","highestEdud":"高中或以下","maritalStatusd":"未婚"},"borrowSum":"10000.00","house":{"houseAddress":"梵蒂冈大哥","referencePrice":"3万","constructionArea":"100","useYears":"3","communityName":"覆盖 ","evaluationPrice":"1万"},"ableTenderDate":"2018-06-27 16:38 ","times":{"times":"23"},"overMoney":{"overMoney":"0.00"},"overTimes":{"overTimes":"0"},"trcity":"保定","idNo":"610***********7626","ProjectName":"143阿斯顿阿斯顿","endTenderDate":"2018-06-29 16:38 ","mortgageType":"房产抵押","trpro":"河北","borrowPurpose":"撒大事","rxx":{"overdueStatus":"阿斯顿","tradeType":"规范化","sex":"女","otherWebStatus":"梵蒂冈","monthIncome":"10000.00","profession":"挂号费","age":"33","highestEdu":"高中","maritalStatus":"未婚","realNames":"钱迎蓉"},"ran":1,"address":"阿斯顿阿斯顿***********","authentication":{"gocar_auth":0,"id":645,"person_auth":0,"house_evaluation":1,"borrowId":2435,"car_auth":0,"buycar_auth":0,"regist_capital_auth":0,"house_right_auth":0,"danbao_auth":0,"address_auth":1,"company_auth":0,"credit_auth":0},"repaysource":"撒旦岸上","realName":"钱**","overMoneys":{"overMoney":"0.00"},"deadline":"5个月"}
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
         * car : null
         * borrowInfo : 电饭锅
         * paymentMode :
         * bondsman : {"professiond":"","addressd":"","monthIncomed":"","idNod":"341***********7439","tradeTyped":"","sexd":"男","trcityd":"","realNamed":"茅**","birthd":"42","highestEdud":"高中或以下","maritalStatusd":"未婚"}
         * borrowSum : 10000.00
         * house : {"houseAddress":"梵蒂冈大哥","referencePrice":"3万","constructionArea":"100","useYears":"3","communityName":"覆盖 ","evaluationPrice":"1万"}
         * ableTenderDate : 2018-06-27 16:38
         * times : {"times":"23"}
         * overMoney : {"overMoney":"0.00"}
         * overTimes : {"overTimes":"0"}
         * trcity : 保定
         * idNo : 610***********7626
         * ProjectName : 143阿斯顿阿斯顿
         * endTenderDate : 2018-06-29 16:38
         * mortgageType : 房产抵押
         * trpro : 河北
         * borrowPurpose : 撒大事
         * rxx : {"overdueStatus":"阿斯顿","tradeType":"规范化","sex":"女","otherWebStatus":"梵蒂冈","monthIncome":"10000.00","profession":"挂号费","age":"33","highestEdu":"高中","maritalStatus":"未婚","realNames":"钱迎蓉"}
         * ran : 1.0
         * address : 阿斯顿阿斯顿***********
         * authentication : {"gocar_auth":0,"id":645,"person_auth":0,"house_evaluation":1,"borrowId":2435,"car_auth":0,"buycar_auth":0,"regist_capital_auth":0,"house_right_auth":0,"danbao_auth":0,"address_auth":1,"company_auth":0,"credit_auth":0}
         * repaysource : 撒旦岸上
         * realName : 钱**
         * overMoneys : {"overMoney":"0.00"}
         * deadline : 5个月
         */

        private Object car;
        private String borrowInfo;
        private String paymentMode;
        private BondsmanBean bondsman;
        private String borrowSum;
        private HouseBean house;
        private String ableTenderDate;
        private TimesBean times;
        private OverMoneyBean overMoney;
        private OverTimesBean overTimes;
        private String trcity;
        private String idNo;
        private String ProjectName;
        private String endTenderDate;
        private String mortgageType;
        private String trpro;
        private String borrowPurpose;
        private RxxBean rxx;
        private double ran;
        private String address;
        private AuthenticationBean authentication;
        private String repaysource;
        private String realName;
        private OverMoneysBean overMoneys;
        private String deadline;

        public Object getCar() {
            return car;
        }

        public void setCar(Object car) {
            this.car = car;
        }

        public String getBorrowInfo() {
            return borrowInfo;
        }

        public void setBorrowInfo(String borrowInfo) {
            this.borrowInfo = borrowInfo;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public BondsmanBean getBondsman() {
            return bondsman;
        }

        public void setBondsman(BondsmanBean bondsman) {
            this.bondsman = bondsman;
        }

        public String getBorrowSum() {
            return borrowSum;
        }

        public void setBorrowSum(String borrowSum) {
            this.borrowSum = borrowSum;
        }

        public HouseBean getHouse() {
            return house;
        }

        public void setHouse(HouseBean house) {
            this.house = house;
        }

        public String getAbleTenderDate() {
            return ableTenderDate;
        }

        public void setAbleTenderDate(String ableTenderDate) {
            this.ableTenderDate = ableTenderDate;
        }

        public TimesBean getTimes() {
            return times;
        }

        public void setTimes(TimesBean times) {
            this.times = times;
        }

        public OverMoneyBean getOverMoney() {
            return overMoney;
        }

        public void setOverMoney(OverMoneyBean overMoney) {
            this.overMoney = overMoney;
        }

        public OverTimesBean getOverTimes() {
            return overTimes;
        }

        public void setOverTimes(OverTimesBean overTimes) {
            this.overTimes = overTimes;
        }

        public String getTrcity() {
            return trcity;
        }

        public void setTrcity(String trcity) {
            this.trcity = trcity;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getEndTenderDate() {
            return endTenderDate;
        }

        public void setEndTenderDate(String endTenderDate) {
            this.endTenderDate = endTenderDate;
        }

        public String getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(String mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getTrpro() {
            return trpro;
        }

        public void setTrpro(String trpro) {
            this.trpro = trpro;
        }

        public String getBorrowPurpose() {
            return borrowPurpose;
        }

        public void setBorrowPurpose(String borrowPurpose) {
            this.borrowPurpose = borrowPurpose;
        }

        public RxxBean getRxx() {
            return rxx;
        }

        public void setRxx(RxxBean rxx) {
            this.rxx = rxx;
        }

        public double getRan() {
            return ran;
        }

        public void setRan(double ran) {
            this.ran = ran;
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

        public String getRepaysource() {
            return repaysource;
        }

        public void setRepaysource(String repaysource) {
            this.repaysource = repaysource;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public OverMoneysBean getOverMoneys() {
            return overMoneys;
        }

        public void setOverMoneys(OverMoneysBean overMoneys) {
            this.overMoneys = overMoneys;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public static class BondsmanBean {
            /**
             * professiond :
             * addressd :
             * monthIncomed :
             * idNod : 341***********7439
             * tradeTyped :
             * sexd : 男
             * trcityd :
             * realNamed : 茅**
             * birthd : 42
             * highestEdud : 高中或以下
             * maritalStatusd : 未婚
             */

            private String professiond;
            private String addressd;
            private String monthIncomed;
            private String idNod;
            private String tradeTyped;
            private String sexd;
            private String trcityd;
            private String realNamed;
            private String birthd;
            private String highestEdud;
            private String maritalStatusd;

            public String getProfessiond() {
                return professiond;
            }

            public void setProfessiond(String professiond) {
                this.professiond = professiond;
            }

            public String getAddressd() {
                return addressd;
            }

            public void setAddressd(String addressd) {
                this.addressd = addressd;
            }

            public String getMonthIncomed() {
                return monthIncomed;
            }

            public void setMonthIncomed(String monthIncomed) {
                this.monthIncomed = monthIncomed;
            }

            public String getIdNod() {
                return idNod;
            }

            public void setIdNod(String idNod) {
                this.idNod = idNod;
            }

            public String getTradeTyped() {
                return tradeTyped;
            }

            public void setTradeTyped(String tradeTyped) {
                this.tradeTyped = tradeTyped;
            }

            public String getSexd() {
                return sexd;
            }

            public void setSexd(String sexd) {
                this.sexd = sexd;
            }

            public String getTrcityd() {
                return trcityd;
            }

            public void setTrcityd(String trcityd) {
                this.trcityd = trcityd;
            }

            public String getRealNamed() {
                return realNamed;
            }

            public void setRealNamed(String realNamed) {
                this.realNamed = realNamed;
            }

            public String getBirthd() {
                return birthd;
            }

            public void setBirthd(String birthd) {
                this.birthd = birthd;
            }

            public String getHighestEdud() {
                return highestEdud;
            }

            public void setHighestEdud(String highestEdud) {
                this.highestEdud = highestEdud;
            }

            public String getMaritalStatusd() {
                return maritalStatusd;
            }

            public void setMaritalStatusd(String maritalStatusd) {
                this.maritalStatusd = maritalStatusd;
            }
        }

        public static class HouseBean {
            /**
             * houseAddress : 梵蒂冈大哥
             * referencePrice : 3万
             * constructionArea : 100
             * useYears : 3
             * communityName : 覆盖
             * evaluationPrice : 1万
             */

            private String houseAddress;
            private String referencePrice;
            private String constructionArea;
            private String useYears;
            private String communityName;
            private String evaluationPrice;

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

            public String getConstructionArea() {
                return constructionArea;
            }

            public void setConstructionArea(String constructionArea) {
                this.constructionArea = constructionArea;
            }

            public String getUseYears() {
                return useYears;
            }

            public void setUseYears(String useYears) {
                this.useYears = useYears;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public String getEvaluationPrice() {
                return evaluationPrice;
            }

            public void setEvaluationPrice(String evaluationPrice) {
                this.evaluationPrice = evaluationPrice;
            }
        }

        public static class TimesBean {
            /**
             * times : 23
             */

            private String times;

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }
        }

        public static class OverMoneyBean {
            /**
             * overMoney : 0.00
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
             * overTimes : 0
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
             * overdueStatus : 阿斯顿
             * tradeType : 规范化
             * sex : 女
             * otherWebStatus : 梵蒂冈
             * monthIncome : 10000.00
             * profession : 挂号费
             * age : 33
             * highestEdu : 高中
             * maritalStatus : 未婚
             * realNames : 钱迎蓉
             */

            private String overdueStatus;
            private String tradeType;
            private String sex;
            private String otherWebStatus;
            private String monthIncome;
            private String profession;
            private String age;
            private String highestEdu;
            private String maritalStatus;
            private String realNames;

            public String getOverdueStatus() {
                return overdueStatus;
            }

            public void setOverdueStatus(String overdueStatus) {
                this.overdueStatus = overdueStatus;
            }

            public String getTradeType() {
                return tradeType;
            }

            public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getOtherWebStatus() {
                return otherWebStatus;
            }

            public void setOtherWebStatus(String otherWebStatus) {
                this.otherWebStatus = otherWebStatus;
            }

            public String getMonthIncome() {
                return monthIncome;
            }

            public void setMonthIncome(String monthIncome) {
                this.monthIncome = monthIncome;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

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

            public String getRealNames() {
                return realNames;
            }

            public void setRealNames(String realNames) {
                this.realNames = realNames;
            }
        }

        public static class AuthenticationBean {
            /**
             * gocar_auth : 0
             * id : 645
             * person_auth : 0
             * house_evaluation : 1
             * borrowId : 2435
             * car_auth : 0
             * buycar_auth : 0
             * regist_capital_auth : 0
             * house_right_auth : 0
             * danbao_auth : 0
             * address_auth : 1
             * company_auth : 0
             * credit_auth : 0
             */

            private int gocar_auth;
            private int id;
            private int person_auth;
            private int house_evaluation;
            private int borrowId;
            private int car_auth;
            private int buycar_auth;
            private int regist_capital_auth;
            private int house_right_auth;
            private int danbao_auth;
            private int address_auth;
            private int company_auth;
            private int credit_auth;

            public int getGocar_auth() {
                return gocar_auth;
            }

            public void setGocar_auth(int gocar_auth) {
                this.gocar_auth = gocar_auth;
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

            public int getHouse_evaluation() {
                return house_evaluation;
            }

            public void setHouse_evaluation(int house_evaluation) {
                this.house_evaluation = house_evaluation;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getCar_auth() {
                return car_auth;
            }

            public void setCar_auth(int car_auth) {
                this.car_auth = car_auth;
            }

            public int getBuycar_auth() {
                return buycar_auth;
            }

            public void setBuycar_auth(int buycar_auth) {
                this.buycar_auth = buycar_auth;
            }

            public int getRegist_capital_auth() {
                return regist_capital_auth;
            }

            public void setRegist_capital_auth(int regist_capital_auth) {
                this.regist_capital_auth = regist_capital_auth;
            }

            public int getHouse_right_auth() {
                return house_right_auth;
            }

            public void setHouse_right_auth(int house_right_auth) {
                this.house_right_auth = house_right_auth;
            }

            public int getDanbao_auth() {
                return danbao_auth;
            }

            public void setDanbao_auth(int danbao_auth) {
                this.danbao_auth = danbao_auth;
            }

            public int getAddress_auth() {
                return address_auth;
            }

            public void setAddress_auth(int address_auth) {
                this.address_auth = address_auth;
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
        }

        public static class OverMoneysBean {
            /**
             * overMoney : 0.00
             */

            private String overMoney;

            public String getOverMoney() {
                return overMoney;
            }

            public void setOverMoney(String overMoney) {
                this.overMoney = overMoney;
            }
        }
    }
}
