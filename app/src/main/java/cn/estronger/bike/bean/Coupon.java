package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/2/12.
 */

public class Coupon {

    /**
     * data : {"items":[{"add_time":"1486869493","coupon_code":"CbVUB6zn1PTQpkg8LdGzFs72IibQx1jm","coupon_id":"4","coupon_type":"1","description":"1小时用车券","effective_time":"1486869493","expired":false,"failure_time":"2017-03-14","number":1,"obtain":"0","unit":"分钟","used":false,"used_time":"0","user_id":"13"}],"total_items_count":"83","total_pages":9}
     * errorCode : 0
     * msg : 操作成功
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
         * items : [{"add_time":"1486869493","coupon_code":"CbVUB6zn1PTQpkg8LdGzFs72IibQx1jm","coupon_id":"4","coupon_type":"1","description":"1小时用车券","effective_time":"1486869493","expired":false,"failure_time":"2017-03-14","number":1,"obtain":"0","unit":"分钟","used":false,"used_time":"0","user_id":"13"}]
         * total_items_count : 83
         * total_pages : 9
         */

        private String total_items_count;
        private int total_pages;
        private List<ItemsBean> items;

        public String getTotal_items_count() {
            return total_items_count;
        }

        public void setTotal_items_count(String total_items_count) {
            this.total_items_count = total_items_count;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * add_time : 1486869493
             * coupon_code : CbVUB6zn1PTQpkg8LdGzFs72IibQx1jm
             * coupon_id : 4
             * coupon_type : 1
             * description : 1小时用车券
             * effective_time : 1486869493
             * expired : false
             * failure_time : 2017-03-14
             * number : 1
             * obtain : 0
             * unit : 分钟
             * used : false
             * used_time : 0
             * user_id : 13
             */

            private String add_time;
            private String coupon_code;
            private String coupon_id;
            private String coupon_type;
            private String description;
            private String effective_time;
            private boolean expired;
            private String failure_time;
            private int number;
            private String obtain;
            private String unit;
            private boolean used;
            private String used_time;
            private String user_id;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getCoupon_code() {
                return coupon_code;
            }

            public void setCoupon_code(String coupon_code) {
                this.coupon_code = coupon_code;
            }

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(String coupon_type) {
                this.coupon_type = coupon_type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(String effective_time) {
                this.effective_time = effective_time;
            }

            public boolean isExpired() {
                return expired;
            }

            public void setExpired(boolean expired) {
                this.expired = expired;
            }

            public String getFailure_time() {
                return failure_time;
            }

            public void setFailure_time(String failure_time) {
                this.failure_time = failure_time;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getObtain() {
                return obtain;
            }

            public void setObtain(String obtain) {
                this.obtain = obtain;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getUsed_time() {
                return used_time;
            }

            public void setUsed_time(String used_time) {
                this.used_time = used_time;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
