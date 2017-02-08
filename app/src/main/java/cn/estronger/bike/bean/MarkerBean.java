package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2016/12/28.
 */

public class MarkerBean {
    /**
     * errorCode : 0
     * msg : 操作成功
     * data : [{"bicycle_id":"5","bicycle_sn":"876451","type":"1","lock_sn":"063070652280","lat":"23.248358","lng":"113.436473"},{"bicycle_id":"1","bicycle_sn":"630754","type":"1","lock_sn":"630071787127","lat":"23.007052","lng":"113.724912"}]
     */

    public int errorCode;
    public String msg;
    public List<DataBean> data;

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
        @Override
        public String toString() {
            return "{" + "bicycle_id:" + '"' + bicycle_id + '"' + "," + "bicycle_sn:" + '"' + bicycle_sn + '"' + "," +
                    "type:" + '"' + type + '"' + "," + "lock_sn:" + '"' + lock_sn + '"'
                    + ","+ "lat:" + '"' + lat + '"' + "," + "lng:" + '"' + lng + '"' +
                    ","+ "near:" + '"' + near + '"' + "," + "fee:" + '"' + fee + '"' +
                    ","+ "scenic_spot_id:" + '"' + scenic_spot_id + '"' + "," + "scenic_spot_name:" + '"' + scenic_spot_name + '"' +"}";
        }

        /**
         * bicycle_id : 5
         * bicycle_sn : 876451
         * type : 1
         * lock_sn : 063070652280
         * lat : 23.248358
         * lng : 113.436473
         */

        public String bicycle_id;
        public String bicycle_sn;
        public String type;
        public String lock_sn;
        public String lat;
        public String lng;
        public String near;
        public String fee;
        public String scenic_spot_id;
        public String scenic_spot_name;

        public String getBicycle_id() {
            return bicycle_id;
        }

        public void setBicycle_id(String bicycle_id) {
            this.bicycle_id = bicycle_id;
        }

        public String getBicycle_sn() {
            return bicycle_sn;
        }

        public void setBicycle_sn(String bicycle_sn) {
            this.bicycle_sn = bicycle_sn;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLock_sn() {
            return lock_sn;
        }

        public void setLock_sn(String lock_sn) {
            this.lock_sn = lock_sn;
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

        public String getNear() {
            return near;
        }
        public void setNear(String near) {
            this.near = near;
        }
        public String getFee() {
            return fee;
        }
        public void setFee(String fee) {
            this.fee = fee;
        }
        public String getScenicSpotId() {
            return scenic_spot_id;
        }
        public void setScenicSpotId(String scenic_spot_id) {
            this.scenic_spot_id = scenic_spot_id;
        }
        public String getScenicSpotName() {
            return fee;
        }
        public void setScenicSpotName(String scenic_spot_name) {
            this.scenic_spot_name = scenic_spot_name;
        }
    }
}
