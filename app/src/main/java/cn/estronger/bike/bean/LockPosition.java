package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/1/5.
 */

public class LockPosition {

    /**
     * errorCode : 0
     * msg : 查找成功
     * data : {"record_id":"9660","cmd":"normal","device_id":"630071787127","battery":"-79","location_type":"0","lock_status":"1","lng":"113.725235","lat":"23.006404","gx":"391","gy":"0","gz":"2400","time":"1482460738","serialnum":"-1","user_sn":""}
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
         * record_id : 9660
         * cmd : normal
         * device_id : 630071787127
         * battery : -79
         * location_type : 0
         * lock_status : 1
         * lng : 113.725235
         * lat : 23.006404
         * gx : 391
         * gy : 0
         * gz : 2400
         * time : 1482460738
         * serialnum : -1
         * user_sn :
         */

        private String record_id;
        private String cmd;
        private String device_id;
        private String battery;
        private String location_type;
        private String lock_status;
        private String lng;
        private String lat;
        private String gx;
        private String gy;
        private String gz;
        private String time;
        private String serialnum;
        private String user_sn;

        public String getRecord_id() {
            return record_id;
        }

        public void setRecord_id(String record_id) {
            this.record_id = record_id;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getBattery() {
            return battery;
        }

        public void setBattery(String battery) {
            this.battery = battery;
        }

        public String getLocation_type() {
            return location_type;
        }

        public void setLocation_type(String location_type) {
            this.location_type = location_type;
        }

        public String getLock_status() {
            return lock_status;
        }

        public void setLock_status(String lock_status) {
            this.lock_status = lock_status;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getGx() {
            return gx;
        }

        public void setGx(String gx) {
            this.gx = gx;
        }

        public String getGy() {
            return gy;
        }

        public void setGy(String gy) {
            this.gy = gy;
        }

        public String getGz() {
            return gz;
        }

        public void setGz(String gz) {
            this.gz = gz;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSerialnum() {
            return serialnum;
        }

        public void setSerialnum(String serialnum) {
            this.serialnum = serialnum;
        }

        public String getUser_sn() {
            return user_sn;
        }

        public void setUser_sn(String user_sn) {
            this.user_sn = user_sn;
        }
    }
}
