package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/4.
 */

public class MsgBean {
    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"total_items_count":"1","total_pages":1,"items":[{"msg_id":"1","msg_time":"1483438393","msg_image":"http://img.chuguoqu.com/g1/M00/01/D2/eSl1sVTv7QyAOFTkAAEyGxSMxrU754.jpg","msg_title":"小强单车松山湖区域上线啦","msg_abstract":"松山湖区域正式上线，只要在风景区内，任意一个地方看到小强单车，下载APP即可马上骑行，扫码开锁，随用随停。","msg_content":"<p>this is the message content<\/p>","msg_link":"http://bike.e-stronger.com/ebike/wechat/myNews.html"}]}
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
         * total_items_count : 1
         * total_pages : 1
         * items : [{"msg_id":"1","msg_time":"1483438393","msg_image":"http://img.chuguoqu.com/g1/M00/01/D2/eSl1sVTv7QyAOFTkAAEyGxSMxrU754.jpg","msg_title":"小强单车松山湖区域上线啦","msg_abstract":"松山湖区域正式上线，只要在风景区内，任意一个地方看到小强单车，下载APP即可马上骑行，扫码开锁，随用随停。","msg_content":"<p>this is the message content<\/p>","msg_link":"http://bike.e-stronger.com/ebike/wechat/myNews.html"}]
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
             * msg_id : 1
             * msg_time : 1483438393
             * msg_image : http://img.chuguoqu.com/g1/M00/01/D2/eSl1sVTv7QyAOFTkAAEyGxSMxrU754.jpg
             * msg_title : 小强单车松山湖区域上线啦
             * msg_abstract : 松山湖区域正式上线，只要在风景区内，任意一个地方看到小强单车，下载APP即可马上骑行，扫码开锁，随用随停。
             * msg_content : <p>this is the message content</p>
             * msg_link : http://bike.e-stronger.com/ebike/wechat/myNews.html
             */

            private String msg_id;
            private String msg_time;
            private String msg_image;
            private String msg_title;
            private String msg_abstract;
            private String msg_content;
            private String msg_link;

            public String getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(String msg_id) {
                this.msg_id = msg_id;
            }

            public String getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(String msg_time) {
                this.msg_time = msg_time;
            }

            public String getMsg_image() {
                return msg_image;
            }

            public void setMsg_image(String msg_image) {
                this.msg_image = msg_image;
            }

            public String getMsg_title() {
                return msg_title;
            }

            public void setMsg_title(String msg_title) {
                this.msg_title = msg_title;
            }

            public String getMsg_abstract() {
                return msg_abstract;
            }

            public void setMsg_abstract(String msg_abstract) {
                this.msg_abstract = msg_abstract;
            }

            public String getMsg_content() {
                return msg_content;
            }

            public void setMsg_content(String msg_content) {
                this.msg_content = msg_content;
            }

            public String getMsg_link() {
                return msg_link;
            }

            public void setMsg_link(String msg_link) {
                this.msg_link = msg_link;
            }
        }
    }
}
