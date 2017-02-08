package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/4.
 */

public class TravelDetail {


    /**
     * data : {"locations":[{"add_time":"1484201341","id":"32504","lat":"23.012941","lng":"113.729845","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201507","id":"32506","lat":"23.012196","lng":"113.731388","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201518","id":"32507","lat":"23.011998","lng":"113.731755","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201527","id":"32509","lat":"23.01155","lng":"113.731806","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201537","id":"32510","lat":"23.011163","lng":"113.731743","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201547","id":"32511","lat":"23.010825","lng":"113.731555","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201557","id":"32512","lat":"23.01047","lng":"113.731376","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201567","id":"32513","lat":"23.010065","lng":"113.731188","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201577","id":"32514","lat":"23.00969","lng":"113.730983","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201587","id":"32515","lat":"23.00932","lng":"113.730838","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201597","id":"32516","lat":"23.009033","lng":"113.730593","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201607","id":"32517","lat":"23.008645","lng":"113.730448","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201617","id":"32518","lat":"23.008286","lng":"113.73029","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201627","id":"32519","lat":"23.00793","lng":"113.730038","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201637","id":"32521","lat":"23.007601","lng":"113.729823","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201647","id":"32522","lat":"23.007351","lng":"113.729518","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201657","id":"32523","lat":"23.00709","lng":"113.729326","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201667","id":"32524","lat":"23.006858","lng":"113.729188","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201677","id":"32525","lat":"23.006721","lng":"113.729086","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201687","id":"32526","lat":"23.006611","lng":"113.728963","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201697","id":"32527","lat":"23.006596","lng":"113.728671","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201711","id":"32528","lat":"23.00636","lng":"113.728446","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201717","id":"32529","lat":"23.006188","lng":"113.728335","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201727","id":"32530","lat":"23.00577","lng":"113.728048","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201736","id":"32532","lat":"23.00569","lng":"113.727563","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201746","id":"32533","lat":"23.005686","lng":"113.727211","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201756","id":"32534","lat":"23.005705","lng":"113.726881","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201766","id":"32535","lat":"23.005705","lng":"113.726503","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201776","id":"32536","lat":"23.005733","lng":"113.726116","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201786","id":"32537","lat":"23.005766","lng":"113.725661","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201796","id":"32538","lat":"23.005796","lng":"113.725276","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201806","id":"32539","lat":"23.005905","lng":"113.724921","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201816","id":"32540","lat":"23.006128","lng":"113.72486","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201826","id":"32541","lat":"23.006208","lng":"113.724858","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201836","id":"32543","lat":"23.006356","lng":"113.724671","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201846","id":"32544","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201856","id":"32545","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201866","id":"32546","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201876","id":"32547","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201886","id":"32548","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201896","id":"32549","lat":"23.006128","lng":"113.725183","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201906","id":"32550","lat":"23.00614","lng":"113.725186","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201916","id":"32551","lat":"23.006145","lng":"113.725176","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201926","id":"32552","lat":"23.00616","lng":"113.725178","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201926","id":"32553","lat":"23.006163","lng":"113.72519","order_id":"651","status":"1","user_id":"64"}],"order_info":{"add_time":"1484201337","bicycle_id":"0","bicycle_sn":"0","calorie":92.62,"distance":1.49,"duration":10,"emission":410,"end_lat":"","end_lng":"","end_time":"1484201926","lock_sn":"063070627746","order_amount":"0.50","order_id":"651","order_sn":"950537545337011064","order_state":"2","pay_amount":"0.50","pay_method":"deposit","scenic_spot_id":"0","scenic_spot_name":"","start_lat":"23.012941","start_lng":"113.729845","start_time":"1484201341","user_id":"64","user_name":"15071681221"},"user_info":{"add_time":"1483945526","available_deposit":"97.10","available_state":"1","avatar":"http://bike.e-stronger.com/ebike/static/avatar/201701111427133759.jpg","cert_time":"1483946868","credit_point":"121","deposit":"0.01","deposit_state":"1","freeze_deposit":"0.00","identification":"421127199001013758","ip":"121.13.197.22","last_update_mobile_time":"0","login_time":"1484114303","mobile":"15071681221","nickname":"15071681221","qq_open_id":"","real_name":"吕涛","recommend_num":"0","update_time":"1484201926","user_id":"64","user_sn":"62666666","uuid":"3DN4C16411037243","verify_sn":"","verify_state":"1","wx_open_id":""}}
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
         * locations : [{"add_time":"1484201341","id":"32504","lat":"23.012941","lng":"113.729845","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201507","id":"32506","lat":"23.012196","lng":"113.731388","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201518","id":"32507","lat":"23.011998","lng":"113.731755","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201527","id":"32509","lat":"23.01155","lng":"113.731806","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201537","id":"32510","lat":"23.011163","lng":"113.731743","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201547","id":"32511","lat":"23.010825","lng":"113.731555","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201557","id":"32512","lat":"23.01047","lng":"113.731376","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201567","id":"32513","lat":"23.010065","lng":"113.731188","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201577","id":"32514","lat":"23.00969","lng":"113.730983","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201587","id":"32515","lat":"23.00932","lng":"113.730838","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201597","id":"32516","lat":"23.009033","lng":"113.730593","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201607","id":"32517","lat":"23.008645","lng":"113.730448","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201617","id":"32518","lat":"23.008286","lng":"113.73029","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201627","id":"32519","lat":"23.00793","lng":"113.730038","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201637","id":"32521","lat":"23.007601","lng":"113.729823","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201647","id":"32522","lat":"23.007351","lng":"113.729518","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201657","id":"32523","lat":"23.00709","lng":"113.729326","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201667","id":"32524","lat":"23.006858","lng":"113.729188","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201677","id":"32525","lat":"23.006721","lng":"113.729086","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201687","id":"32526","lat":"23.006611","lng":"113.728963","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201697","id":"32527","lat":"23.006596","lng":"113.728671","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201711","id":"32528","lat":"23.00636","lng":"113.728446","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201717","id":"32529","lat":"23.006188","lng":"113.728335","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201727","id":"32530","lat":"23.00577","lng":"113.728048","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201736","id":"32532","lat":"23.00569","lng":"113.727563","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201746","id":"32533","lat":"23.005686","lng":"113.727211","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201756","id":"32534","lat":"23.005705","lng":"113.726881","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201766","id":"32535","lat":"23.005705","lng":"113.726503","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201776","id":"32536","lat":"23.005733","lng":"113.726116","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201786","id":"32537","lat":"23.005766","lng":"113.725661","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201796","id":"32538","lat":"23.005796","lng":"113.725276","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201806","id":"32539","lat":"23.005905","lng":"113.724921","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201816","id":"32540","lat":"23.006128","lng":"113.72486","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201826","id":"32541","lat":"23.006208","lng":"113.724858","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201836","id":"32543","lat":"23.006356","lng":"113.724671","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201846","id":"32544","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201856","id":"32545","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201866","id":"32546","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201876","id":"32547","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201886","id":"32548","lat":"23.006361","lng":"113.724653","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201896","id":"32549","lat":"23.006128","lng":"113.725183","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201906","id":"32550","lat":"23.00614","lng":"113.725186","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201916","id":"32551","lat":"23.006145","lng":"113.725176","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201926","id":"32552","lat":"23.00616","lng":"113.725178","order_id":"651","status":"1","user_id":"64"},{"add_time":"1484201926","id":"32553","lat":"23.006163","lng":"113.72519","order_id":"651","status":"1","user_id":"64"}]
         * order_info : {"add_time":"1484201337","bicycle_id":"0","bicycle_sn":"0","calorie":92.62,"distance":1.49,"duration":10,"emission":410,"end_lat":"","end_lng":"","end_time":"1484201926","lock_sn":"063070627746","order_amount":"0.50","order_id":"651","order_sn":"950537545337011064","order_state":"2","pay_amount":"0.50","pay_method":"deposit","scenic_spot_id":"0","scenic_spot_name":"","start_lat":"23.012941","start_lng":"113.729845","start_time":"1484201341","user_id":"64","user_name":"15071681221"}
         * user_info : {"add_time":"1483945526","available_deposit":"97.10","available_state":"1","avatar":"http://bike.e-stronger.com/ebike/static/avatar/201701111427133759.jpg","cert_time":"1483946868","credit_point":"121","deposit":"0.01","deposit_state":"1","freeze_deposit":"0.00","identification":"421127199001013758","ip":"121.13.197.22","last_update_mobile_time":"0","login_time":"1484114303","mobile":"15071681221","nickname":"15071681221","qq_open_id":"","real_name":"吕涛","recommend_num":"0","update_time":"1484201926","user_id":"64","user_sn":"62666666","uuid":"3DN4C16411037243","verify_sn":"","verify_state":"1","wx_open_id":""}
         */

        private OrderInfoBean order_info;
        private UserInfoBean user_info;
        private List<LocationsBean> locations;

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public List<LocationsBean> getLocations() {
            return locations;
        }

        public void setLocations(List<LocationsBean> locations) {
            this.locations = locations;
        }

        public static class OrderInfoBean {
            /**
             * add_time : 1484201337
             * bicycle_id : 0
             * bicycle_sn : 0
             * calorie : 92.62
             * distance : 1.49
             * duration : 10
             * emission : 410
             * end_lat :
             * end_lng :
             * end_time : 1484201926
             * lock_sn : 063070627746
             * order_amount : 0.50
             * order_id : 651
             * order_sn : 950537545337011064
             * order_state : 2
             * pay_amount : 0.50
             * pay_method : deposit
             * scenic_spot_id : 0
             * scenic_spot_name :
             * start_lat : 23.012941
             * start_lng : 113.729845
             * start_time : 1484201341
             * user_id : 64
             * user_name : 15071681221
             */

            private String add_time;
            private String bicycle_id;
            private String bicycle_sn;
            private double calorie;
            private double distance;
            private int duration;
            private int emission;
            private String end_lat;
            private String end_lng;
            private String end_time;
            private String lock_sn;
            private String order_amount;
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
            private String user_id;
            private String user_name;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
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

            public String getLock_sn() {
                return lock_sn;
            }

            public void setLock_sn(String lock_sn) {
                this.lock_sn = lock_sn;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
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
        }

        public static class UserInfoBean {
            /**
             * add_time : 1483945526
             * available_deposit : 97.10
             * available_state : 1
             * avatar : http://bike.e-stronger.com/ebike/static/avatar/201701111427133759.jpg
             * cert_time : 1483946868
             * credit_point : 121
             * deposit : 0.01
             * deposit_state : 1
             * freeze_deposit : 0.00
             * identification : 421127199001013758
             * ip : 121.13.197.22
             * last_update_mobile_time : 0
             * login_time : 1484114303
             * mobile : 15071681221
             * nickname : 15071681221
             * qq_open_id :
             * real_name : 吕涛
             * recommend_num : 0
             * update_time : 1484201926
             * user_id : 64
             * user_sn : 62666666
             * uuid : 3DN4C16411037243
             * verify_sn :
             * verify_state : 1
             * wx_open_id :
             */

            private String add_time;
            private String available_deposit;
            private String available_state;
            private String avatar;
            private String cert_time;
            private String credit_point;
            private String deposit;
            private String deposit_state;
            private String freeze_deposit;
            private String identification;
            private String ip;
            private String last_update_mobile_time;
            private String login_time;
            private String mobile;
            private String nickname;
            private String qq_open_id;
            private String real_name;
            private String recommend_num;
            private String update_time;
            private String user_id;
            private String user_sn;
            private String uuid;
            private String verify_sn;
            private String verify_state;
            private String wx_open_id;

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

            public String getAvailable_state() {
                return available_state;
            }

            public void setAvailable_state(String available_state) {
                this.available_state = available_state;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCert_time() {
                return cert_time;
            }

            public void setCert_time(String cert_time) {
                this.cert_time = cert_time;
            }

            public String getCredit_point() {
                return credit_point;
            }

            public void setCredit_point(String credit_point) {
                this.credit_point = credit_point;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public String getDeposit_state() {
                return deposit_state;
            }

            public void setDeposit_state(String deposit_state) {
                this.deposit_state = deposit_state;
            }

            public String getFreeze_deposit() {
                return freeze_deposit;
            }

            public void setFreeze_deposit(String freeze_deposit) {
                this.freeze_deposit = freeze_deposit;
            }

            public String getIdentification() {
                return identification;
            }

            public void setIdentification(String identification) {
                this.identification = identification;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getLast_update_mobile_time() {
                return last_update_mobile_time;
            }

            public void setLast_update_mobile_time(String last_update_mobile_time) {
                this.last_update_mobile_time = last_update_mobile_time;
            }

            public String getLogin_time() {
                return login_time;
            }

            public void setLogin_time(String login_time) {
                this.login_time = login_time;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getQq_open_id() {
                return qq_open_id;
            }

            public void setQq_open_id(String qq_open_id) {
                this.qq_open_id = qq_open_id;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getRecommend_num() {
                return recommend_num;
            }

            public void setRecommend_num(String recommend_num) {
                this.recommend_num = recommend_num;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_sn() {
                return user_sn;
            }

            public void setUser_sn(String user_sn) {
                this.user_sn = user_sn;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getVerify_sn() {
                return verify_sn;
            }

            public void setVerify_sn(String verify_sn) {
                this.verify_sn = verify_sn;
            }

            public String getVerify_state() {
                return verify_state;
            }

            public void setVerify_state(String verify_state) {
                this.verify_state = verify_state;
            }

            public String getWx_open_id() {
                return wx_open_id;
            }

            public void setWx_open_id(String wx_open_id) {
                this.wx_open_id = wx_open_id;
            }
        }

        public static class LocationsBean {
            /**
             * add_time : 1484201341
             * id : 32504
             * lat : 23.012941
             * lng : 113.729845
             * order_id : 651
             * status : 1
             * user_id : 64
             */

            private String add_time;
            private String id;
            private String lat;
            private String lng;
            private String order_id;
            private String status;
            private String user_id;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
