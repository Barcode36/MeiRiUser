package com.example.meiriuser.model;

/**
 * Created by admin on 2019/6/10.
 */

public class UserInfoModel{

    /**
     * result : 1
     * code : 0
     * data : {"user_id":209,"email":null,"username":"13414725836","question":"","answer":"","sex":0,"birthday":0,"user_money":"0.00","frozen_money":"0.00","pay_points":0,"rank_points":0,"address_id":0,"reg_time":"2019-07-18 14:25","last_login":"2019-07-19 11:14","last_ip":"120.197.37.32","parent_id":0,"alias":"","weixin":"","qq":"","office_phone":"","home_phone":"","mobile_phone":"13414725836","is_validated":0,"credit_line":"0.00","groupid":1,"banknum":"**** 2","lat":"0.0000000","lng":"0.0000000","note":null,"token":"836096fafd06cb9ae332f56b7b43178b","edittime":"","loginNum":1,"status":1,"headImg":"https://i.loli.net/2018/05/01/5ae8781326d70.jpg","nickName":"13414725836","bankType":"2","idcard":null,"attestation":null,"attestation_status":-1,"bank_name":"1"}
     */

    private int result;
    private int code;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 209
         * email : null
         * username : 13414725836
         * question :
         * answer :
         * sex : 0
         * birthday : 0
         * user_money : 0.00
         * frozen_money : 0.00
         * pay_points : 0
         * rank_points : 0
         * address_id : 0
         * reg_time : 2019-07-18 14:25
         * last_login : 2019-07-19 11:14
         * last_ip : 120.197.37.32
         * parent_id : 0
         * alias :
         * weixin :
         * qq :
         * office_phone :
         * home_phone :
         * mobile_phone : 13414725836
         * is_validated : 0
         * credit_line : 0.00
         * groupid : 1
         * banknum : **** 2
         * lat : 0.0000000
         * lng : 0.0000000
         * note : null
         * token : 836096fafd06cb9ae332f56b7b43178b
         * edittime :
         * loginNum : 1
         * status : 1
         * headImg : https://i.loli.net/2018/05/01/5ae8781326d70.jpg
         * nickName : 13414725836
         * bankType : 2
         * idcard : null
         * attestation : null
         * attestation_status : -1
         * bank_name : 1
         */

        private int user_id;
        private Object email;
        private String username;
        private String question;
        private String answer;
        private int sex;
        private int birthday;
        private String user_money;
        private String frozen_money;
        private int pay_points;
        private int rank_points;
        private int address_id;
        private String reg_time;
        private String last_login;
        private String last_ip;
        private int parent_id;
        private String alias;
        private String weixin;
        private String qq;
        private String office_phone;
        private String home_phone;
        private String mobile_phone;
        private int is_validated;
        private String credit_line;
        private int groupid;
        private String banknum;
        private String lat;
        private String lng;
        private Object note;
        private String token;
        private String edittime;
        private int loginNum;
        private int status;
        private String headImg;
        private String nickName;
        private String bankType;
        private Object idcard;
        private Object attestation;
        private int attestation_status;
        private String bank_name;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public int getPay_points() {
            return pay_points;
        }

        public void setPay_points(int pay_points) {
            this.pay_points = pay_points;
        }

        public int getRank_points() {
            return rank_points;
        }

        public void setRank_points(int rank_points) {
            this.rank_points = rank_points;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public String getHome_phone() {
            return home_phone;
        }

        public void setHome_phone(String home_phone) {
            this.home_phone = home_phone;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public int getIs_validated() {
            return is_validated;
        }

        public void setIs_validated(int is_validated) {
            this.is_validated = is_validated;
        }

        public String getCredit_line() {
            return credit_line;
        }

        public void setCredit_line(String credit_line) {
            this.credit_line = credit_line;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getBanknum() {
            return banknum;
        }

        public void setBanknum(String banknum) {
            this.banknum = banknum;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public int getLoginNum() {
            return loginNum;
        }

        public void setLoginNum(int loginNum) {
            this.loginNum = loginNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
        }

        public Object getAttestation() {
            return attestation;
        }

        public void setAttestation(Object attestation) {
            this.attestation = attestation;
        }

        public int getAttestation_status() {
            return attestation_status;
        }

        public void setAttestation_status(int attestation_status) {
            this.attestation_status = attestation_status;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}
