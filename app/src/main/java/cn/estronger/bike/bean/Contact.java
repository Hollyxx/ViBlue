package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/2/9.
 */

public class Contact {

    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"wechat":"小强单车","phone":"4006123272","email":"bike@estronger.cn","web":"bike.estronger.cn"}
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
         * wechat : 小强单车
         * phone : 4006123272
         * email : bike@estronger.cn
         * web : bike.estronger.cn
         */

        private String wechat;
        private String phone;
        private String email;
        private String web;

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }
    }
}
