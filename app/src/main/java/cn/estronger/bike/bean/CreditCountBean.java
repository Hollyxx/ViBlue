package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2016/12/31.
 */

public class CreditCountBean {
    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"credit_point":"0","total_items_count":"2","total_pages":1,"items":[{"point_id":"12","points":"100","user_id":"13","add_time":"1482888454","point_desc":"注册完成","admin_id":"0","admin_name":""},{"point_id":"25","points":"10","user_id":"13","add_time":"1482832884","point_desc":"通过实名验证","admin_id":"0","admin_name":""}]}
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
         * credit_point : 0
         * total_items_count : 2
         * total_pages : 1
         * items : [{"point_id":"12","points":"100","user_id":"13","add_time":"1482888454","point_desc":"注册完成","admin_id":"0","admin_name":""},{"point_id":"25","points":"10","user_id":"13","add_time":"1482832884","point_desc":"通过实名验证","admin_id":"0","admin_name":""}]
         */

        private String credit_point;
        private String total_items_count;
        private int total_pages;
        private List<ItemsBean> items;

        public String getCredit_point() {
            return credit_point;
        }

        public void setCredit_point(String credit_point) {
            this.credit_point = credit_point;
        }

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
             * point_id : 12
             * points : 100
             * user_id : 13
             * add_time : 1482888454
             * point_desc : 注册完成
             * admin_id : 0
             * admin_name :
             */

            private String point_id;
            private String points;
            private String user_id;
            private String add_time;
            private String point_desc;
            private String admin_id;
            private String admin_name;

            public String getPoint_id() {
                return point_id;
            }

            public void setPoint_id(String point_id) {
                this.point_id = point_id;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPoint_desc() {
                return point_desc;
            }

            public void setPoint_desc(String point_desc) {
                this.point_desc = point_desc;
            }

            public String getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(String admin_id) {
                this.admin_id = admin_id;
            }

            public String getAdmin_name() {
                return admin_name;
            }

            public void setAdmin_name(String admin_name) {
                this.admin_name = admin_name;
            }
        }
    }
}
