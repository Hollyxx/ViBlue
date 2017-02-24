package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/2/23.
 */

public class MarkerInfo {

    /**
     * bicycle_id : 1
     * bicycle_sn : 630754
     * type : 1
     * lock_sn : 630071787127
     * lat : 23.005969
     * lng : 113.72523
     * near : 0
     * fee : 1.00
     * scenic_spot_id : 0
     * scenic_spot_name :
     */

    private String bicycle_id;
    private String bicycle_sn;
    private String type;
    private String lock_sn;
    private String lat;
    private String lng;
    private String near;
    private String fee;
    private String scenic_spot_id;
    private String scenic_spot_name;

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

    public String getScenic_spot_id() {
        return scenic_spot_id;
    }

    public void setScenic_spot_id(String scenic_spot_id) {
        this.scenic_spot_id = scenic_spot_id;
    }

    public String getScenic_spot_name() {
        return scenic_spot_name;
    }

    public void setScenic_spot_name(String scenic_spot_name) {
        this.scenic_spot_name = scenic_spot_name;
    }
}
