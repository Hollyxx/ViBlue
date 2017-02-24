package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/2/16.
 */

public class LockInfo {

    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"order_sn":"800536164479847003","cmd":"open","user_id":"3","device_id":"630071787127"}
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
         * order_sn : 800536164479847003
         * cmd : open
         * user_id : 3
         * device_id : 630071787127
         */

        private String order_sn;
        private String cmd;
        private String user_id;
        private String device_id;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }
    }
}
