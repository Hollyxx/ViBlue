package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/3.
 */

public class MyTravel {

    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"total_items_count":"83","total_pages":9,"items":[{"order_id":"126","order_sn":"320536411039444003","user_id":"3","lock_sn":"630071787127","bicycle_id":"0","bicycle_sn":"0","user_name":"18681150579","add_time":"1483067039","scenic_spot_id":"0","scenic_spot_name":"","start_time":"1483067048","start_lng":"113.724712","start_lat":"23.006988","end_time":"1483067095","end_lng":"","end_lat":"","order_amount":"0.01","pay_method":"deposit","pay_amount":"0.01","order_state":"2","duration":1},"\u2026"]}
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
         * total_items_count : 83
         * total_pages : 9
         * items : [{"order_id":"126","order_sn":"320536411039444003","user_id":"3","lock_sn":"630071787127","bicycle_id":"0","bicycle_sn":"0","user_name":"18681150579","add_time":"1483067039","scenic_spot_id":"0","scenic_spot_name":"","start_time":"1483067048","start_lng":"113.724712","start_lat":"23.006988","end_time":"1483067095","end_lng":"","end_lat":"","order_amount":"0.01","pay_method":"deposit","pay_amount":"0.01","order_state":"2","duration":1},"\u2026"]
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
             * order_id : 126
             * order_sn : 320536411039444003
             * user_id : 3
             * lock_sn : 630071787127
             * bicycle_id : 0
             * bicycle_sn : 0
             * user_name : 18681150579
             * add_time : 1483067039
             * scenic_spot_id : 0
             * scenic_spot_name :
             * start_time : 1483067048
             * start_lng : 113.724712
             * start_lat : 23.006988
             * end_time : 1483067095
             * end_lng :
             * end_lat :
             * order_amount : 0.01
             * pay_method : deposit
             * pay_amount : 0.01
             * order_state : 2
             * duration : 1
             */

            private String order_id;
            private String order_sn;
            private String user_id;
            private String lock_sn;
            private String bicycle_id;
            private String bicycle_sn;
            private String user_name;
            private String add_time;
            private String scenic_spot_id;
            private String scenic_spot_name;
            private String start_time;
            private String start_lng;
            private String start_lat;
            private String end_time;
            private String end_lng;
            private String end_lat;
            private String order_amount;
            private String pay_method;
            private String pay_amount;
            private String order_state;
            private int duration;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getLock_sn() {
                return lock_sn;
            }

            public void setLock_sn(String lock_sn) {
                this.lock_sn = lock_sn;
            }

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

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getStart_lng() {
                return start_lng;
            }

            public void setStart_lng(String start_lng) {
                this.start_lng = start_lng;
            }

            public String getStart_lat() {
                return start_lat;
            }

            public void setStart_lat(String start_lat) {
                this.start_lat = start_lat;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getEnd_lng() {
                return end_lng;
            }

            public void setEnd_lng(String end_lng) {
                this.end_lng = end_lng;
            }

            public String getEnd_lat() {
                return end_lat;
            }

            public void setEnd_lat(String end_lat) {
                this.end_lat = end_lat;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getOrder_state() {
                return order_state;
            }

            public void setOrder_state(String order_state) {
                this.order_state = order_state;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }
        }
    }
}
