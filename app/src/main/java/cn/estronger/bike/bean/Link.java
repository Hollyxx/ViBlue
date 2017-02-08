package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/9.
 */

public class Link {


    /**
     * errorCode : 0
     * msg : 操作成功
     * data : [{"id":1,"code":"setting-user-agreements","link":"http://bike.e-stronger.com/ebike/static/article/zh/setting-user-agreements.html"},{"id":2,"code":"setting-deposit-instructions","link":"http://bike.e-stronger.com/ebike/static/article/zh/setting-deposit-instructions.html"},"\u2026"]
     */

    private int errorCode;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * code : setting-user-agreements
         * link : http://bike.e-stronger.com/ebike/static/article/zh/setting-user-agreements.html
         */

        private int id;
        private String code;
        private String link;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
