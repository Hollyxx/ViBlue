package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/1/11.
 */

public class Login {

    /**
     * data : {"available_deposit":"99.60","available_state":"1","avatar":"http://bike.e-stronger.com/ebike/static/avatar/201701091636363039.jpg","credit_point":"116","deposit":"0.01","deposit_state":"1","freeze_deposit":"0.00","identification":"421127199001013758","mobile":"15071681221","nickname":"大魔头","real_name":"吕涛","recommend_num":"0","user_id":"64","user_sn":"62666666","verify_state":"1"}
     * errorCode : 0
     * msg : 登录成功
     */

    private DataBean data;
    private int errorCode;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * available_deposit : 99.60
         * available_state : 1
         * avatar : http://bike.e-stronger.com/ebike/static/avatar/201701091636363039.jpg
         * credit_point : 116
         * deposit : 0.01
         * deposit_state : 1
         * freeze_deposit : 0.00
         * identification : 421127199001013758
         * mobile : 15071681221
         * nickname : 大魔头
         * real_name : 吕涛
         * recommend_num : 0
         * user_id : 64
         * user_sn : 62666666
         * verify_state : 1
         */

        private String available_deposit;
        private String available_state;
        private String avatar;
        private String credit_point;
        private String deposit;
        private String deposit_state;
        private String freeze_deposit;
        private String identification;
        private String mobile;
        private String nickname;
        private String real_name;
        private String recommend_num;
        private String user_id;
        private String user_sn;
        private String verify_state;

        public String getAvailable_deposit() {
            return available_deposit;
        }

        public void setAvailable_deposit(String available_deposit) {
            this.available_deposit = available_deposit;
        }

        public String getAvailable_state() {
            return available_state;
        }

        public void setAvailable_state(String available_state) {
            this.available_state = available_state;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(String credit_point) {
            this.credit_point = credit_point;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getDeposit_state() {
            return deposit_state;
        }

        public void setDeposit_state(String deposit_state) {
            this.deposit_state = deposit_state;
        }

        public String getFreeze_deposit() {
            return freeze_deposit;
        }

        public void setFreeze_deposit(String freeze_deposit) {
            this.freeze_deposit = freeze_deposit;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getRecommend_num() {
            return recommend_num;
        }

        public void setRecommend_num(String recommend_num) {
            this.recommend_num = recommend_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_sn() {
            return user_sn;
        }

        public void setUser_sn(String user_sn) {
            this.user_sn = user_sn;
        }

        public String getVerify_state() {
            return verify_state;
        }

        public void setVerify_state(String verify_state) {
            this.verify_state = verify_state;
        }
    }
}
