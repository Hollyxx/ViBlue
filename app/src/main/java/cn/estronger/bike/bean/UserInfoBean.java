package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2016/12/31.
 */

public class UserInfoBean {
    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"user_id":"4","mobile":"15071681221","nickname":"15071681221","avatar":"http://120.76.98.150/ebike/static/avatar/201612301433072321.jpg","deposit":"0.01","deposit_state":"1","available_deposit":"0.00","freeze_deposit":"0.00","credit_point":"100","real_name":"张三","identification":"421127199001013758","verify_state":"1","available_state":"0","recommend_num":"0"}
     */

    private int errorCode;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 4
         * mobile : 15071681221
         * nickname : 15071681221
         * avatar : http://120.76.98.150/ebike/static/avatar/201612301433072321.jpg
         * deposit : 0.01
         * deposit_state : 1
         * available_deposit : 0.00
         * freeze_deposit : 0.00
         * credit_point : 100
         * real_name : 张三
         * identification : 421127199001013758
         * verify_state : 1
         * available_state : 0
         * recommend_num : 0
         */

        private String user_id;
        private String mobile;
        private String nickname;
        private String avatar;
        private String deposit;
        private String deposit_state;
        private String available_deposit;
        private String freeze_deposit;
        private String credit_point;
        private String real_name;
        private String identification;
        private String verify_state;
        private String available_state;
        private String recommend_num;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getAvailable_deposit() {
            return available_deposit;
        }

        public void setAvailable_deposit(String available_deposit) {
            this.available_deposit = available_deposit;
        }

        public String getFreeze_deposit() {
            return freeze_deposit;
        }

        public void setFreeze_deposit(String freeze_deposit) {
            this.freeze_deposit = freeze_deposit;
        }

        public String getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(String credit_point) {
            this.credit_point = credit_point;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getVerify_state() {
            return verify_state;
        }

        public void setVerify_state(String verify_state) {
            this.verify_state = verify_state;
        }

        public String getAvailable_state() {
            return available_state;
        }

        public void setAvailable_state(String available_state) {
            this.available_state = available_state;
        }

        public String getRecommend_num() {
            return recommend_num;
        }

        public void setRecommend_num(String recommend_num) {
            this.recommend_num = recommend_num;
        }
    }
}
