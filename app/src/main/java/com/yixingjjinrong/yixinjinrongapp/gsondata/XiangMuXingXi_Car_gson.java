package com.yixingjjinrong.yixinjinrongapp.gsondata;

public class XiangMuXingXi_Car_gson {

    /**
     * message : 成功了
     * result : {"car":{"carprice":"50万","car_style":"奥迪a6","referenceprice":"50万","car_register":"2018-06-04 00:00:00","carcode":"浙****A","car_offer":"50万","car_emission":"3.5","car_condition":"优","car_mileage":"10"},"borrowInfo":"gdfgfd","paymentMode":"","bondsman":{"professiond":"计算机","addressd":"北京市海淀区","monthIncomed":"10000.00","idNod":"420***********4059","tradeTyped":"计算机","sexd":"男","trcityd":"银川","realNamed":"朱**","birthd":"56","highestEdud":"高中或以下","maritalStatusd":"未婚"},"borrowSum":"1000.00","house":null,"ableTenderDate":"2018-06-23 14:13 ","times":{"times":"7"},"overMoney":{"overMoney":"0.00"},"overTimes":{"overTimes":"0"},"trcity":"北京","idNo":"210***********1825","ProjectName":"微信_一次性还款","endTenderDate":"2018-06-29 14:13 ","mortgageType":"车辆抵押","trpro":"北京","borrowPurpose":"一次性还款","rxx":{"overdueStatus":"发鬼地方","tradeType":"教育","sex":"女","otherWebStatus":"大范甘迪","monthIncome":"10000.00","profession":"教师","age":"46","highestEdu":"初中","maritalStatus":"未婚"},"ran":6,"address":"大法官大人个***********","authentication":{"gocar_auth":1,"id":600,"person_auth":1,"house_evaluation":0,"borrowId":2390,"car_auth":1,"buycar_auth":1,"regist_capital_auth":0,"house_right_auth":0,"danbao_auth":1,"address_auth":1,"company_auth":0,"credit_auth":1},"repaysource":"工资","realName":"张**","overMoneys":{"overMoney":"0.00"},"deadline":"3个月"}
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
         * car : {"carprice":"50万","car_style":"奥迪a6","referenceprice":"50万","car_register":"2018-06-04 00:00:00","carcode":"浙****A","car_offer":"50万","car_emission":"3.5","car_condition":"优","car_mileage":"10"}
         * borrowInfo : gdfgfd
         * paymentMode :
         * bondsman : {"professiond":"计算机","addressd":"北京市海淀区","monthIncomed":"10000.00","idNod":"420***********4059","tradeTyped":"计算机","sexd":"男","trcityd":"银川","realNamed":"朱**","birthd":"56","highestEdud":"高中或以下","maritalStatusd":"未婚"}
         * borrowSum : 1000.00
         * house : null
         * ableTenderDate : 2018-06-23 14:13
         * times : {"times":"7"}
         * overMoney : {"overMoney":"0.00"}
         * overTimes : {"overTimes":"0"}
         * trcity : 北京
         * idNo : 210***********1825
         * ProjectName : 微信_一次性还款
         * endTenderDate : 2018-06-29 14:13
         * mortgageType : 车辆抵押
         * trpro : 北京
         * borrowPurpose : 一次性还款
         * rxx : {"overdueStatus":"发鬼地方","tradeType":"教育","sex":"女","otherWebStatus":"大范甘迪","monthIncome":"10000.00","profession":"教师","age":"46","highestEdu":"初中","maritalStatus":"未婚"}
         * ran : 6.0
         * address : 大法官大人个***********
         * authentication : {"gocar_auth":1,"id":600,"person_auth":1,"house_evaluation":0,"borrowId":2390,"car_auth":1,"buycar_auth":1,"regist_capital_auth":0,"house_right_auth":0,"danbao_auth":1,"address_auth":1,"company_auth":0,"credit_auth":1}
         * repaysource : 工资
         * realName : 张**
         * overMoneys : {"overMoney":"0.00"}
         * deadline : 3个月
         */

        private CarBean car;
        private String borrowInfo;
        private String paymentMode;
        private BondsmanBean bondsman;
        private String borrowSum;
        private Object house;
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

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
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

        public Object getHouse() {
            return house;
        }

        public void setHouse(Object house) {
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

        public static class CarBean {
            /**
             * carprice : 50万
             * car_style : 奥迪a6
             * referenceprice : 50万
             * car_register : 2018-06-04 00:00:00
             * carcode : 浙****A
             * car_offer : 50万
             * car_emission : 3.5
             * car_condition : 优
             * car_mileage : 10
             */

            private String carprice;
            private String car_style;
            private String referenceprice;
            private String car_register;
            private String carcode;
            private String car_offer;
            private String car_emission;
            private String car_condition;
            private String car_mileage;

            public String getCarprice() {
                return carprice;
            }

            public void setCarprice(String carprice) {
                this.carprice = carprice;
            }

            public String getCar_style() {
                return car_style;
            }

            public void setCar_style(String car_style) {
                this.car_style = car_style;
            }

            public String getReferenceprice() {
                return referenceprice;
            }

            public void setReferenceprice(String referenceprice) {
                this.referenceprice = referenceprice;
            }

            public String getCar_register() {
                return car_register;
            }

            public void setCar_register(String car_register) {
                this.car_register = car_register;
            }

            public String getCarcode() {
                return carcode;
            }

            public void setCarcode(String carcode) {
                this.carcode = carcode;
            }

            public String getCar_offer() {
                return car_offer;
            }

            public void setCar_offer(String car_offer) {
                this.car_offer = car_offer;
            }

            public String getCar_emission() {
                return car_emission;
            }

            public void setCar_emission(String car_emission) {
                this.car_emission = car_emission;
            }

            public String getCar_condition() {
                return car_condition;
            }

            public void setCar_condition(String car_condition) {
                this.car_condition = car_condition;
            }

            public String getCar_mileage() {
                return car_mileage;
            }

            public void setCar_mileage(String car_mileage) {
                this.car_mileage = car_mileage;
            }
        }

        public static class BondsmanBean {
            /**
             * professiond : 计算机
             * addressd : 北京市海淀区
             * monthIncomed : 10000.00
             * idNod : 420***********4059
             * tradeTyped : 计算机
             * sexd : 男
             * trcityd : 银川
             * realNamed : 朱**
             * birthd : 56
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

        public static class TimesBean {
            /**
             * times : 7
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
             * overdueStatus : 发鬼地方
             * tradeType : 教育
             * sex : 女
             * otherWebStatus : 大范甘迪
             * monthIncome : 10000.00
             * profession : 教师
             * age : 46
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
             * gocar_auth : 1
             * id : 600
             * person_auth : 1
             * house_evaluation : 0
             * borrowId : 2390
             * car_auth : 1
             * buycar_auth : 1
             * regist_capital_auth : 0
             * house_right_auth : 0
             * danbao_auth : 1
             * address_auth : 1
             * company_auth : 0
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
