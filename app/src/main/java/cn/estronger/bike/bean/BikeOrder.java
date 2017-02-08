package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2016/12/30.
 */

public class BikeOrder {

    /**
     * errorCode : 0
     * msg : 预约成功
     * data : {"bicycle_sn":"0","lock_sn":"630071787127","order_sn":"800536496900333003","keep_time":"1230"}
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
         * bicycle_sn : 0
         * lock_sn : 630071787127
         * order_sn : 800536496900333003
         * keep_time : 1230
         */

        private String bicycle_sn;
        private String lock_sn;
        private String order_sn;
        private String keep_time;

        public String getBicycle_sn() {
            return bicycle_sn;
        }

        public void setBicycle_sn(String bicycle_sn) {
            this.bicycle_sn = bicycle_sn;
        }

        public String getLock_sn() {
            return lock_sn;
        }

        public void setLock_sn(String lock_sn) {
            this.lock_sn = lock_sn;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getKeep_time() {
            return keep_time;
        }

        public void setKeep_time(String keep_time) {
            this.keep_time = keep_time;
        }
    }
}
