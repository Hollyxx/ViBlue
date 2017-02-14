package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/2/12.
 */

public class CouponHistroy {


    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"total_items_count":"2","total_pages":1,"items":[{"coupon_id":"2","user_id":"1","description":"","number":"1","coupon_type":"2","coupon_code":"ZyY0ejpSMCJNxYLC17zaUus3tH6HLzZJ","effective_time":"1486742400","failure_time":"2017-03-11","add_time":"1486782431","used":false,"used_time":"0","obtain":"0","expired":false,"unit":"次"},{"coupon_id":"1","user_id":"1","description":"","number":0.5,"coupon_type":"1","coupon_code":"PrDhArWuxr9yqeI2wneDCUQBfEW0f5pb","effective_time":"1486742400","failure_time":"2017-03-11","add_time":"1486782178","used":false,"used_time":"0","obtain":"0","expired":false,"unit":"小时"}]}
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
         * total_items_count : 2
         * total_pages : 1
         * items : [{"coupon_id":"2","user_id":"1","description":"","number":"1","coupon_type":"2","coupon_code":"ZyY0ejpSMCJNxYLC17zaUus3tH6HLzZJ","effective_time":"1486742400","failure_time":"2017-03-11","add_time":"1486782431","used":false,"used_time":"0","obtain":"0","expired":false,"unit":"次"},{"coupon_id":"1","user_id":"1","description":"","number":0.5,"coupon_type":"1","coupon_code":"PrDhArWuxr9yqeI2wneDCUQBfEW0f5pb","effective_time":"1486742400","failure_time":"2017-03-11","add_time":"1486782178","used":false,"used_time":"0","obtain":"0","expired":false,"unit":"小时"}]
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
             * coupon_id : 2
             * user_id : 1
             * description :
             * number : 1
             * coupon_type : 2
             * coupon_code : ZyY0ejpSMCJNxYLC17zaUus3tH6HLzZJ
             * effective_time : 1486742400
             * failure_time : 2017-03-11
             * add_time : 1486782431
             * used : false
             * used_time : 0
             * obtain : 0
             * expired : false
             * unit : 次
             */

            private String coupon_id;
            private String user_id;
            private String description;
            private String number;
            private String coupon_type;
            private String coupon_code;
            private String effective_time;
            private String failure_time;
            private String add_time;
            private boolean used;
            private String used_time;
            private String obtain;
            private boolean expired;
            private String unit;

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(String coupon_type) {
                this.coupon_type = coupon_type;
            }

            public String getCoupon_code() {
                return coupon_code;
            }

            public void setCoupon_code(String coupon_code) {
                this.coupon_code = coupon_code;
            }

            public String getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(String effective_time) {
                this.effective_time = effective_time;
            }

            public String getFailure_time() {
                return failure_time;
            }

            public void setFailure_time(String failure_time) {
                this.failure_time = failure_time;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public String getObtain() {
                return obtain;
            }

            public void setObtain(String obtain) {
                this.obtain = obtain;
            }

            public boolean isExpired() {
                return expired;
            }

            public void setExpired(boolean expired) {
                this.expired = expired;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
