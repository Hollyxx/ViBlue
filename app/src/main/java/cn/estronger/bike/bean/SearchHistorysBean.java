package cn.estronger.bike.bean;

import java.io.Serializable;

public class SearchHistorysBean implements Serializable {
    public int _id;
    public long updatetime;
    public String name;
    public String address;
    public double latitude;
    public double longitude;

    public SearchHistorysBean() {

    }

    public SearchHistorysBean(String name, String address, double latitude, double longitude, long updatetime) {
        this.name = name;
        this.updatetime = updatetime;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int get_id() {
        return _id;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return latitude;
    }

    public double getLng() {
        return longitude;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLat(double latitude) {
        this.latitude = latitude;
    }

    public void setLng(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SearchHistorysBean{" +
                "_id=" + _id +
                ", updatetime=" + updatetime +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
