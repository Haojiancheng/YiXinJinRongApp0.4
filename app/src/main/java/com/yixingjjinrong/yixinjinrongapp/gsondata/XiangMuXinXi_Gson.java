package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuXinXi_Gson {


    /**
     * message : 成功了
     * result : {"car":null,"addressd":"","borrowInfo":"发斯蒂芬是在是v是","companyAddress":"西大望路***********","regCode":"912***********535R","companyBusiness":"互联网","bondsman":{"professiond":"","addressd":"","monthIncomed":"","idNod":"","tradeTyped":"","sexd":"","trcityd":"","realNamed":"","birthd":"","highestEdud":"","maritalStatusd":""},"borrowSum":"90000.00元","trcityd":"","house":{"houseAddress":"北京市朝阳区***********","referencePrice":"10,000元","constructionArea":"80平方米","useYears":"90年","communityName":"幸福","evaluationPrice":"10,000元"},"Addresss":"","registeredCapital":"100000.00","cmonthIncome":"10000000.00","c_name":"北京***********担保公司","ableTenderDate":"2018-06-14 13:54 ","times":{"times":"3"},"refund":{"paymentMode":"按月付息，到期还本","paymentModezhi":"2","paymentModezi":"计算公式：每月应付利息=借款本金总金额×约定年化利率/12，最后一期（月）应付金额=当月应付利息+借款本金总金额。"},"overMoney":{"overMoney":"0.00"},"buildTime":"2018-06-12","overTimes":{"overTimes":"0"},"trcity":"石嘴山","auditOpinion":"史蒂芬孙分","idNo":"422***********3270","houseAddress":"北京市朝阳区***********","businessScope":"啊","endTenderDate":"2018-06-16 13:54 ","ProjectName":"预热标","registAddress":"北京市朝阳区******","mortgageType":"1","newRealName":"孙**","trpro":"宁夏","carcode":"","companyName":"广州****有限公司","borrowPurpose":"大多数","companyOwner":"梁*","organizationCode":"914****HX57","guaranteeType":"0","rxx":{"overdueStatus":"ad","tradeType":"asd","sex":"男","otherWebStatus":"asd","monthIncome":"1111.00元","profession":"asd ","age":"47","highestEdu":"初中","maritalStatus":"未婚"},"ran":6,"RealNameq":"","address":"das ***********","authentication":{"gocar_auth":0,"id":490,"person_auth":1,"house_evaluation":1,"borrowId":2280,"car_auth":0,"buycar_auth":0,"regist_capital_auth":1,"house_right_auth":1,"danbao_auth":1,"address_auth":0,"company_auth":1,"credit_auth":1},"borrowFrom":"2","realName":"梁***","repaysource":"热汤发鬼地方个混","newAddresss":"北京市远洋国******","overMoneys":{"overMoney":"0.00"},"deadline":"3个月"}
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
         * addressd :
         * borrowInfo : 发斯蒂芬是在是v是
         * companyAddress : 西大望路***********
         * regCode : 912***********535R
         * companyBusiness : 互联网
         * bondsman : {"professiond":"","addressd":"","monthIncomed":"","idNod":"","tradeTyped":"","sexd":"","trcityd":"","realNamed":"","birthd":"","highestEdud":"","maritalStatusd":""}
         * borrowSum : 90000.00元
         * trcityd :
         * house : {"houseAddress":"北京市朝阳区***********","referencePrice":"10,000元","constructionArea":"80平方米","useYears":"90年","communityName":"幸福","evaluationPrice":"10,000元"}
         * Addresss :
         * registeredCapital : 100000.00
         * cmonthIncome : 10000000.00
         * c_name : 北京***********担保公司
         * ableTenderDate : 2018-06-14 13:54
         * times : {"times":"3"}
         * refund : {"paymentMode":"按月付息，到期还本","paymentModezhi":"2","paymentModezi":"计算公式：每月应付利息=借款本金总金额×约定年化利率/12，最后一期（月）应付金额=当月应付利息+借款本金总金额。"}
         * overMoney : {"overMoney":"0.00"}
         * buildTime : 2018-06-12
         * overTimes : {"overTimes":"0"}
         * trcity : 石嘴山
         * auditOpinion : 史蒂芬孙分
         * idNo : 422***********3270
         * houseAddress : 北京市朝阳区***********
         * businessScope : 啊
         * endTenderDate : 2018-06-16 13:54
         * ProjectName : 预热标
         * registAddress : 北京市朝阳区******
         * mortgageType : 1
         * newRealName : 孙**
         * trpro : 宁夏
         * carcode :
         * companyName : 广州****有限公司
         * borrowPurpose : 大多数
         * companyOwner : 梁*
         * organizationCode : 914****HX57
         * guaranteeType : 0
         * rxx : {"overdueStatus":"ad","tradeType":"asd","sex":"男","otherWebStatus":"asd","monthIncome":"1111.00元","profession":"asd ","age":"47","highestEdu":"初中","maritalStatus":"未婚"}
         * ran : 6.0
         * RealNameq :
         * address : das ***********
         * authentication : {"gocar_auth":0,"id":490,"person_auth":1,"house_evaluation":1,"borrowId":2280,"car_auth":0,"buycar_auth":0,"regist_capital_auth":1,"house_right_auth":1,"danbao_auth":1,"address_auth":0,"company_auth":1,"credit_auth":1}
         * borrowFrom : 2
         * realName : 梁***
         * repaysource : 热汤发鬼地方个混
         * newAddresss : 北京市远洋国******
         * overMoneys : {"overMoney":"0.00"}
         * deadline : 3个月
         */

        private Object car;
        private String addressd;
        private String borrowInfo;
        private String companyAddress;
        private String regCode;
        private String companyBusiness;
        private BondsmanBean bondsman;
        private String borrowSum;
        private String trcityd;
        private HouseBean house;
        private String Addresss;
        private String registeredCapital;
        private String cmonthIncome;
        private String c_name;
        private String ableTenderDate;
        private TimesBean times;
        private RefundBean refund;
        private OverMoneyBean overMoney;
        private String buildTime;
        private OverTimesBean overTimes;
        private String trcity;
        private String auditOpinion;
        private String idNo;
        private String houseAddress;
        private String businessScope;
        private String endTenderDate;
        private String ProjectName;
        private String registAddress;
        private String mortgageType;
        private String newRealName;
        private String trpro;
        private String carcode;
        private String companyName;
        private String borrowPurpose;
        private String companyOwner;
        private String organizationCode;
        private String guaranteeType;
        private RxxBean rxx;
        private double ran;
        private String RealNameq;
        private String address;
        private AuthenticationBean authentication;
        private String borrowFrom;
        private String realName;
        private String repaysource;
        private String newAddresss;
        private OverMoneysBean overMoneys;
        private String deadline;

        public Object getCar() {
            return car;
        }

        public void setCar(Object car) {
            this.car = car;
        }

        public String getAddressd() {
            return addressd;
        }

        public void setAddressd(String addressd) {
            this.addressd = addressd;
        }

        public String getBorrowInfo() {
            return borrowInfo;
        }

        public void setBorrowInfo(String borrowInfo) {
            this.borrowInfo = borrowInfo;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getRegCode() {
            return regCode;
        }

        public void setRegCode(String regCode) {
            this.regCode = regCode;
        }

        public String getCompanyBusiness() {
            return companyBusiness;
        }

        public void setCompanyBusiness(String companyBusiness) {
            this.companyBusiness = companyBusiness;
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

        public String getTrcityd() {
            return trcityd;
        }

        public void setTrcityd(String trcityd) {
            this.trcityd = trcityd;
        }

        public HouseBean getHouse() {
            return house;
        }

        public void setHouse(HouseBean house) {
            this.house = house;
        }

        public String getAddresss() {
            return Addresss;
        }

        public void setAddresss(String Addresss) {
            this.Addresss = Addresss;
        }

        public String getRegisteredCapital() {
            return registeredCapital;
        }

        public void setRegisteredCapital(String registeredCapital) {
            this.registeredCapital = registeredCapital;
        }

        public String getCmonthIncome() {
            return cmonthIncome;
        }

        public void setCmonthIncome(String cmonthIncome) {
            this.cmonthIncome = cmonthIncome;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
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

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public OverMoneyBean getOverMoney() {
            return overMoney;
        }

        public void setOverMoney(OverMoneyBean overMoney) {
            this.overMoney = overMoney;
        }

        public String getBuildTime() {
            return buildTime;
        }

        public void setBuildTime(String buildTime) {
            this.buildTime = buildTime;
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

        public String getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(String auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getHouseAddress() {
            return houseAddress;
        }

        public void setHouseAddress(String houseAddress) {
            this.houseAddress = houseAddress;
        }

        public String getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(String businessScope) {
            this.businessScope = businessScope;
        }

        public String getEndTenderDate() {
            return endTenderDate;
        }

        public void setEndTenderDate(String endTenderDate) {
            this.endTenderDate = endTenderDate;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getRegistAddress() {
            return registAddress;
        }

        public void setRegistAddress(String registAddress) {
            this.registAddress = registAddress;
        }

        public String getMortgageType() {
            return mortgageType;
        }

        public void setMortgageType(String mortgageType) {
            this.mortgageType = mortgageType;
        }

        public String getNewRealName() {
            return newRealName;
        }

        public void setNewRealName(String newRealName) {
            this.newRealName = newRealName;
        }

        public String getTrpro() {
            return trpro;
        }

        public void setTrpro(String trpro) {
            this.trpro = trpro;
        }

        public String getCarcode() {
            return carcode;
        }

        public void setCarcode(String carcode) {
            this.carcode = carcode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getBorrowPurpose() {
            return borrowPurpose;
        }

        public void setBorrowPurpose(String borrowPurpose) {
            this.borrowPurpose = borrowPurpose;
        }

        public String getCompanyOwner() {
            return companyOwner;
        }

        public void setCompanyOwner(String companyOwner) {
            this.companyOwner = companyOwner;
        }

        public String getOrganizationCode() {
            return organizationCode;
        }

        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        public String getGuaranteeType() {
            return guaranteeType;
        }

        public void setGuaranteeType(String guaranteeType) {
            this.guaranteeType = guaranteeType;
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

        public String getRealNameq() {
            return RealNameq;
        }

        public void setRealNameq(String RealNameq) {
            this.RealNameq = RealNameq;
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

        public String getBorrowFrom() {
            return borrowFrom;
        }

        public void setBorrowFrom(String borrowFrom) {
            this.borrowFrom = borrowFrom;
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

        public String getNewAddresss() {
            return newAddresss;
        }

        public void setNewAddresss(String newAddresss) {
            this.newAddresss = newAddresss;
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
             * idNod :
             * tradeTyped :
             * sexd :
             * trcityd :
             * realNamed :
             * birthd :
             * highestEdud :
             * maritalStatusd :
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
             * houseAddress : 北京市朝阳区***********
             * referencePrice : 10,000元
             * constructionArea : 80平方米
             * useYears : 90年
             * communityName : 幸福
             * evaluationPrice : 10,000元
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
             * times : 3
             */

            private String times;

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }
        }

        public static class RefundBean {
            /**
             * paymentMode : 按月付息，到期还本
             * paymentModezhi : 2
             * paymentModezi : 计算公式：每月应付利息=借款本金总金额×约定年化利率/12，最后一期（月）应付金额=当月应付利息+借款本金总金额。
             */

            private String paymentMode;
            private String paymentModezhi;
            private String paymentModezi;

            public String getPaymentMode() {
                return paymentMode;
            }

            public void setPaymentMode(String paymentMode) {
                this.paymentMode = paymentMode;
            }

            public String getPaymentModezhi() {
                return paymentModezhi;
            }

            public void setPaymentModezhi(String paymentModezhi) {
                this.paymentModezhi = paymentModezhi;
            }

            public String getPaymentModezi() {
                return paymentModezi;
            }

            public void setPaymentModezi(String paymentModezi) {
                this.paymentModezi = paymentModezi;
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
             * overdueStatus : ad
             * tradeType : asd
             * sex : 男
             * otherWebStatus : asd
             * monthIncome : 1111.00元
             * profession : asd
             * age : 47
             * highestEdu : 初中
             * maritalStatus : 未婚
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
        }

        public static class AuthenticationBean {
            /**
             * gocar_auth : 0
             * id : 490
             * person_auth : 1
             * house_evaluation : 1
             * borrowId : 2280
             * car_auth : 0
             * buycar_auth : 0
             * regist_capital_auth : 1
             * house_right_auth : 1
             * danbao_auth : 1
             * address_auth : 0
             * company_auth : 1
             * credit_auth : 1
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
