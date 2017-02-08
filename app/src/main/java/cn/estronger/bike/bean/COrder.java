package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/8.
 */

public class COrder {


    /**
     * data : {"current_order":{"add_time":"1483863137","available_deposit":"100.17","bicycle_id":"0","bicycle_sn":"0","calorie":6.22,"distance":0.1,"emission":28,"end_lat":"","end_lng":"","end_time":"0","keep_time":0,"line_data":[{"lat":"23.006183","lng":"113.725185"},{"lat":"23.00619","lng":"113.725126"},{"lat":"23.006275","lng":"113.725128"},{"lat":"23.006278","lng":"113.725123"},{"lat":"23.006286","lng":"113.72512"},{"lat":"23.006296","lng":"113.725115"},{"lat":"23.006301","lng":"113.72513"},{"lat":"23.006293","lng":"113.725143"},{"lat":"23.006288","lng":"113.725151"},{"lat":"23.006283","lng":"113.725141"},{"lat":"23.006253","lng":"113.725113"},{"lat":"23.006241","lng":"113.7251"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006256","lng":"113.7251"},{"lat":"23.006258","lng":"113.725101"},{"lat":"23.006268","lng":"113.725108"},{"lat":"23.006261","lng":"113.725113"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006258","lng":"113.725108"},{"lat":"23.006258","lng":"113.725106"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006241","lng":"113.725088"},{"lat":"23.006236","lng":"113.725076"},{"lat":"23.00623","lng":"113.725073"},{"lat":"23.006226","lng":"113.725078"},{"lat":"23.006233","lng":"113.725081"},{"lat":"23.00624","lng":"113.72509"},{"lat":"23.006248","lng":"113.725093"},{"lat":"23.00625","lng":"113.725093"},{"lat":"23.006253","lng":"113.725095"},{"lat":"23.00626","lng":"113.725108"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006255","lng":"113.725113"},{"lat":"23.006245","lng":"113.725095"},{"lat":"23.006238","lng":"113.725088"},{"lat":"23.006238","lng":"113.72509"},{"lat":"23.006241","lng":"113.725093"},{"lat":"23.006251","lng":"113.725105"},{"lat":"23.006275","lng":"113.725141"},{"lat":"23.006276","lng":"113.725153"},{"lat":"23.006276","lng":"113.725155"},{"lat":"23.006276","lng":"113.725165"},{"lat":"23.00627","lng":"113.725168"},{"lat":"23.006268","lng":"113.725166"},{"lat":"23.006268","lng":"113.725168"},{"lat":"23.006273","lng":"113.72517"},{"lat":"23.006278","lng":"113.725175"},{"lat":"23.00628","lng":"113.72519"},{"lat":"23.006283","lng":"113.7252"},{"lat":"23.00628","lng":"113.72521"},{"lat":"23.006273","lng":"113.725201"},{"lat":"23.006265","lng":"113.725191"},{"lat":"23.006245","lng":"113.725193"},{"lat":"23.006243","lng":"113.725193"},{"lat":"23.006226","lng":"113.725205"},{"lat":"23.00621","lng":"113.725218"},{"lat":"23.006205","lng":"113.725241"},{"lat":"23.006206","lng":"113.725261"},{"lat":"23.006211","lng":"113.725266"},{"lat":"23.006216","lng":"113.725266"},{"lat":"23.00622","lng":"113.725243"},{"lat":"23.006238","lng":"113.725196"},{"lat":"23.006235","lng":"113.7252"},{"lat":"23.006248","lng":"113.725221"},{"lat":"23.006263","lng":"113.725226"},{"lat":"23.006271","lng":"113.725228"},{"lat":"23.006268","lng":"113.725216"},{"lat":"23.006271","lng":"113.72521"},{"lat":"23.006275","lng":"113.725208"},{"lat":"23.006276","lng":"113.725203"},{"lat":"23.006276","lng":"113.725201"},{"lat":"23.006278","lng":"113.725203"}],"lock_sn":"063070627746","order_amount":0.5,"order_id":"439","order_sn":"380537207137778003","order_state":"1","pay_amount":"0.00","pay_method":"deposit","scenic_spot_id":"0","scenic_spot_name":"","start_lat":"23.006183","start_lng":"113.725185","start_time":"1483863171","time":{"hours":0,"min":12},"user_id":"3","user_name":"15071681221"},"has_order":true}
     * errorCode : 0
     * msg : 当前有进行中或预约中的订单
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
         * current_order : {"add_time":"1483863137","available_deposit":"100.17","bicycle_id":"0","bicycle_sn":"0","calorie":6.22,"distance":0.1,"emission":28,"end_lat":"","end_lng":"","end_time":"0","keep_time":0,"line_data":[{"lat":"23.006183","lng":"113.725185"},{"lat":"23.00619","lng":"113.725126"},{"lat":"23.006275","lng":"113.725128"},{"lat":"23.006278","lng":"113.725123"},{"lat":"23.006286","lng":"113.72512"},{"lat":"23.006296","lng":"113.725115"},{"lat":"23.006301","lng":"113.72513"},{"lat":"23.006293","lng":"113.725143"},{"lat":"23.006288","lng":"113.725151"},{"lat":"23.006283","lng":"113.725141"},{"lat":"23.006253","lng":"113.725113"},{"lat":"23.006241","lng":"113.7251"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006256","lng":"113.7251"},{"lat":"23.006258","lng":"113.725101"},{"lat":"23.006268","lng":"113.725108"},{"lat":"23.006261","lng":"113.725113"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006258","lng":"113.725108"},{"lat":"23.006258","lng":"113.725106"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006241","lng":"113.725088"},{"lat":"23.006236","lng":"113.725076"},{"lat":"23.00623","lng":"113.725073"},{"lat":"23.006226","lng":"113.725078"},{"lat":"23.006233","lng":"113.725081"},{"lat":"23.00624","lng":"113.72509"},{"lat":"23.006248","lng":"113.725093"},{"lat":"23.00625","lng":"113.725093"},{"lat":"23.006253","lng":"113.725095"},{"lat":"23.00626","lng":"113.725108"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006255","lng":"113.725113"},{"lat":"23.006245","lng":"113.725095"},{"lat":"23.006238","lng":"113.725088"},{"lat":"23.006238","lng":"113.72509"},{"lat":"23.006241","lng":"113.725093"},{"lat":"23.006251","lng":"113.725105"},{"lat":"23.006275","lng":"113.725141"},{"lat":"23.006276","lng":"113.725153"},{"lat":"23.006276","lng":"113.725155"},{"lat":"23.006276","lng":"113.725165"},{"lat":"23.00627","lng":"113.725168"},{"lat":"23.006268","lng":"113.725166"},{"lat":"23.006268","lng":"113.725168"},{"lat":"23.006273","lng":"113.72517"},{"lat":"23.006278","lng":"113.725175"},{"lat":"23.00628","lng":"113.72519"},{"lat":"23.006283","lng":"113.7252"},{"lat":"23.00628","lng":"113.72521"},{"lat":"23.006273","lng":"113.725201"},{"lat":"23.006265","lng":"113.725191"},{"lat":"23.006245","lng":"113.725193"},{"lat":"23.006243","lng":"113.725193"},{"lat":"23.006226","lng":"113.725205"},{"lat":"23.00621","lng":"113.725218"},{"lat":"23.006205","lng":"113.725241"},{"lat":"23.006206","lng":"113.725261"},{"lat":"23.006211","lng":"113.725266"},{"lat":"23.006216","lng":"113.725266"},{"lat":"23.00622","lng":"113.725243"},{"lat":"23.006238","lng":"113.725196"},{"lat":"23.006235","lng":"113.7252"},{"lat":"23.006248","lng":"113.725221"},{"lat":"23.006263","lng":"113.725226"},{"lat":"23.006271","lng":"113.725228"},{"lat":"23.006268","lng":"113.725216"},{"lat":"23.006271","lng":"113.72521"},{"lat":"23.006275","lng":"113.725208"},{"lat":"23.006276","lng":"113.725203"},{"lat":"23.006276","lng":"113.725201"},{"lat":"23.006278","lng":"113.725203"}],"lock_sn":"063070627746","order_amount":0.5,"order_id":"439","order_sn":"380537207137778003","order_state":"1","pay_amount":"0.00","pay_method":"deposit","scenic_spot_id":"0","scenic_spot_name":"","start_lat":"23.006183","start_lng":"113.725185","start_time":"1483863171","time":{"hours":0,"min":12},"user_id":"3","user_name":"15071681221"}
         * has_order : true
         */

        private CurrentOrderBean current_order;
        private boolean has_order;

        public CurrentOrderBean getCurrent_order() {
            return current_order;
        }

        public void setCurrent_order(CurrentOrderBean current_order) {
            this.current_order = current_order;
        }

        public boolean isHas_order() {
            return has_order;
        }

        public void setHas_order(boolean has_order) {
            this.has_order = has_order;
        }

        public static class CurrentOrderBean {
            /**
             * add_time : 1483863137
             * available_deposit : 100.17
             * bicycle_id : 0
             * bicycle_sn : 0
             * calorie : 6.22
             * distance : 0.1
             * emission : 28
             * end_lat :
             * end_lng :
             * end_time : 0
             * keep_time : 0
             * line_data : [{"lat":"23.006183","lng":"113.725185"},{"lat":"23.00619","lng":"113.725126"},{"lat":"23.006275","lng":"113.725128"},{"lat":"23.006278","lng":"113.725123"},{"lat":"23.006286","lng":"113.72512"},{"lat":"23.006296","lng":"113.725115"},{"lat":"23.006301","lng":"113.72513"},{"lat":"23.006293","lng":"113.725143"},{"lat":"23.006288","lng":"113.725151"},{"lat":"23.006283","lng":"113.725141"},{"lat":"23.006253","lng":"113.725113"},{"lat":"23.006241","lng":"113.7251"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006256","lng":"113.7251"},{"lat":"23.006258","lng":"113.725101"},{"lat":"23.006268","lng":"113.725108"},{"lat":"23.006261","lng":"113.725113"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006258","lng":"113.725108"},{"lat":"23.006258","lng":"113.725106"},{"lat":"23.00625","lng":"113.725098"},{"lat":"23.006241","lng":"113.725088"},{"lat":"23.006236","lng":"113.725076"},{"lat":"23.00623","lng":"113.725073"},{"lat":"23.006226","lng":"113.725078"},{"lat":"23.006233","lng":"113.725081"},{"lat":"23.00624","lng":"113.72509"},{"lat":"23.006248","lng":"113.725093"},{"lat":"23.00625","lng":"113.725093"},{"lat":"23.006253","lng":"113.725095"},{"lat":"23.00626","lng":"113.725108"},{"lat":"23.00626","lng":"113.725116"},{"lat":"23.006255","lng":"113.725113"},{"lat":"23.006245","lng":"113.725095"},{"lat":"23.006238","lng":"113.725088"},{"lat":"23.006238","lng":"113.72509"},{"lat":"23.006241","lng":"113.725093"},{"lat":"23.006251","lng":"113.725105"},{"lat":"23.006275","lng":"113.725141"},{"lat":"23.006276","lng":"113.725153"},{"lat":"23.006276","lng":"113.725155"},{"lat":"23.006276","lng":"113.725165"},{"lat":"23.00627","lng":"113.725168"},{"lat":"23.006268","lng":"113.725166"},{"lat":"23.006268","lng":"113.725168"},{"lat":"23.006273","lng":"113.72517"},{"lat":"23.006278","lng":"113.725175"},{"lat":"23.00628","lng":"113.72519"},{"lat":"23.006283","lng":"113.7252"},{"lat":"23.00628","lng":"113.72521"},{"lat":"23.006273","lng":"113.725201"},{"lat":"23.006265","lng":"113.725191"},{"lat":"23.006245","lng":"113.725193"},{"lat":"23.006243","lng":"113.725193"},{"lat":"23.006226","lng":"113.725205"},{"lat":"23.00621","lng":"113.725218"},{"lat":"23.006205","lng":"113.725241"},{"lat":"23.006206","lng":"113.725261"},{"lat":"23.006211","lng":"113.725266"},{"lat":"23.006216","lng":"113.725266"},{"lat":"23.00622","lng":"113.725243"},{"lat":"23.006238","lng":"113.725196"},{"lat":"23.006235","lng":"113.7252"},{"lat":"23.006248","lng":"113.725221"},{"lat":"23.006263","lng":"113.725226"},{"lat":"23.006271","lng":"113.725228"},{"lat":"23.006268","lng":"113.725216"},{"lat":"23.006271","lng":"113.72521"},{"lat":"23.006275","lng":"113.725208"},{"lat":"23.006276","lng":"113.725203"},{"lat":"23.006276","lng":"113.725201"},{"lat":"23.006278","lng":"113.725203"}]
             * lock_sn : 063070627746
             * order_amount : 0.5
             * order_id : 439
             * order_sn : 380537207137778003
             * order_state : 1
             * pay_amount : 0.00
             * pay_method : deposit
             * scenic_spot_id : 0
             * scenic_spot_name :
             * start_lat : 23.006183
             * start_lng : 113.725185
             * start_time : 1483863171
             * time : {"hours":0,"min":12}
             * user_id : 3
             * user_name : 15071681221
             */

            private String add_time;
            private String available_deposit;
            private String bicycle_id;
            private String bicycle_sn;
            private double calorie;
            private double distance;
            private int emission;
            private String end_lat;
            private String end_lng;
            private String end_time;
            private int keep_time;
            private String lock_sn;
            private double order_amount;
            private String order_id;
            private String order_sn;
            private String order_state;
            private String pay_amount;
            private String pay_method;
            private String scenic_spot_id;
            private String scenic_spot_name;
            private String start_lat;
            private String start_lng;
            private String start_time;
            private TimeBean time;
            private String user_id;
            private String user_name;
            private List<LineDataBean> line_data;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAvailable_deposit() {
                return available_deposit;
            }

            public void setAvailable_deposit(String available_deposit) {
                this.available_deposit = available_deposit;
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

            public double getCalorie() {
                return calorie;
            }

            public void setCalorie(double calorie) {
                this.calorie = calorie;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getEmission() {
                return emission;
            }

            public void setEmission(int emission) {
                this.emission = emission;
            }

            public String getEnd_lat() {
                return end_lat;
            }

            public void setEnd_lat(String end_lat) {
                this.end_lat = end_lat;
            }

            public String getEnd_lng() {
                return end_lng;
            }

            public void setEnd_lng(String end_lng) {
                this.end_lng = end_lng;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getKeep_time() {
                return keep_time;
            }

            public void setKeep_time(int keep_time) {
                this.keep_time = keep_time;
            }

            public String getLock_sn() {
                return lock_sn;
            }

            public void setLock_sn(String lock_sn) {
                this.lock_sn = lock_sn;
            }

            public double getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(double order_amount) {
                this.order_amount = order_amount;
            }

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

            public String getOrder_state() {
                return order_state;
            }

            public void setOrder_state(String order_state) {
                this.order_state = order_state;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
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

            public String getStart_lat() {
                return start_lat;
            }

            public void setStart_lat(String start_lat) {
                this.start_lat = start_lat;
            }

            public String getStart_lng() {
                return start_lng;
            }

            public void setStart_lng(String start_lng) {
                this.start_lng = start_lng;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public TimeBean getTime() {
                return time;
            }

            public void setTime(TimeBean time) {
                this.time = time;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public List<LineDataBean> getLine_data() {
                return line_data;
            }

            public void setLine_data(List<LineDataBean> line_data) {
                this.line_data = line_data;
            }

            public static class TimeBean {
                /**
                 * hours : 0
                 * min : 12
                 */

                private int hours;
                private int min;

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }
            }

            public static class LineDataBean {
                /**
                 * lat : 23.006183
                 * lng : 113.725185
                 */

                private String lat;
                private String lng;

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
            }
        }
    }
}
