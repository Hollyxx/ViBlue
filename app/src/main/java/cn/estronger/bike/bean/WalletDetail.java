package cn.estronger.bike.bean;

import java.util.List;

/**
 * Created by MrLv on 2017/1/3.
 */

public class WalletDetail {


    /**
     * data : {"items":[{"deposit_type":"支付成功","pdl_add_time":"1484528983","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：540537872974096064","pdl_freeze_amount":"0.00","pdl_id":"475","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"540537872974096064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484528933","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：440537872924283064","pdl_freeze_amount":"0.00","pdl_id":"474","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"440537872924283064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484528911","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：750537872897288064","pdl_freeze_amount":"0.00","pdl_id":"473","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"750537872897288064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484201926","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：950537545337011064","pdl_freeze_amount":"0.00","pdl_id":"454","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"950537545337011064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484200955","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：640537544716801064","pdl_freeze_amount":"0.00","pdl_id":"453","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"640537544716801064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484199920","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：770537543293945064","pdl_freeze_amount":"0.00","pdl_id":"451","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"770537543293945064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484199230","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：390537543075551064","pdl_freeze_amount":"0.00","pdl_id":"450","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"390537543075551064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484198942","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：780537542388330064","pdl_freeze_amount":"0.00","pdl_id":"449","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"780537542388330064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484037452","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.01","pdl_desc":"单车骑行，扣除支付预存款，订单号：460537381214789064","pdl_freeze_amount":"0.00","pdl_id":"416","pdl_image":"","pdl_payment_code":"","pdl_payment_name":"","pdl_sn":"0","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484037014","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.01","pdl_desc":"单车骑行，扣除支付预存款，订单号：890537380672100064","pdl_freeze_amount":"0.00","pdl_id":"415","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"0","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"}],"total_items_count":"30","total_pages":3}
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
         * items : [{"deposit_type":"支付成功","pdl_add_time":"1484528983","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：540537872974096064","pdl_freeze_amount":"0.00","pdl_id":"475","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"540537872974096064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484528933","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：440537872924283064","pdl_freeze_amount":"0.00","pdl_id":"474","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"440537872924283064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484528911","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：750537872897288064","pdl_freeze_amount":"0.00","pdl_id":"473","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"750537872897288064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484201926","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：950537545337011064","pdl_freeze_amount":"0.00","pdl_id":"454","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"950537545337011064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484200955","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：640537544716801064","pdl_freeze_amount":"0.00","pdl_id":"453","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"640537544716801064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484199920","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：770537543293945064","pdl_freeze_amount":"0.00","pdl_id":"451","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"770537543293945064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484199230","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：390537543075551064","pdl_freeze_amount":"0.00","pdl_id":"450","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"390537543075551064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484198942","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.50","pdl_desc":"单车骑行，扣除支付预存款，订单号：780537542388330064","pdl_freeze_amount":"0.00","pdl_id":"449","pdl_image":"","pdl_payment_code":"余额支付","pdl_payment_name":"","pdl_sn":"780537542388330064","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484037452","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.01","pdl_desc":"单车骑行，扣除支付预存款，订单号：460537381214789064","pdl_freeze_amount":"0.00","pdl_id":"416","pdl_image":"","pdl_payment_code":"","pdl_payment_name":"","pdl_sn":"0","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"},{"deposit_type":"支付成功","pdl_add_time":"1484037014","pdl_admin_id":"0","pdl_admin_name":"","pdl_available_amount":"-0.01","pdl_desc":"单车骑行，扣除支付预存款，订单号：890537380672100064","pdl_freeze_amount":"0.00","pdl_id":"415","pdl_image":"","pdl_payment_code":"deposit","pdl_payment_name":"余额支付","pdl_sn":"0","pdl_type":"order_pay","pdl_user_id":"64","pdl_user_name":"15071681221"}]
         * total_items_count : 30
         * total_pages : 3
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
             * deposit_type : 支付成功
             * pdl_add_time : 1484528983
             * pdl_admin_id : 0
             * pdl_admin_name :
             * pdl_available_amount : -0.50
             * pdl_desc : 单车骑行，扣除支付预存款，订单号：540537872974096064
             * pdl_freeze_amount : 0.00
             * pdl_id : 475
             * pdl_image :
             * pdl_payment_code : deposit
             * pdl_payment_name : 余额支付
             * pdl_sn : 540537872974096064
             * pdl_type : order_pay
             * pdl_user_id : 64
             * pdl_user_name : 15071681221
             */

            private String deposit_type;
            private String pdl_add_time;
            private String pdl_admin_id;
            private String pdl_admin_name;
            private String pdl_available_amount;
            private String pdl_desc;
            private String pdl_freeze_amount;
            private String pdl_id;
            private String pdl_image;
            private String pdl_payment_code;
            private String pdl_payment_name;
            private String pdl_sn;
            private String pdl_type;
            private String pdl_user_id;
            private String pdl_user_name;

            public String getDeposit_type() {
                return deposit_type;
            }

            public void setDeposit_type(String deposit_type) {
                this.deposit_type = deposit_type;
            }

            public String getPdl_add_time() {
                return pdl_add_time;
            }

            public void setPdl_add_time(String pdl_add_time) {
                this.pdl_add_time = pdl_add_time;
            }

            public String getPdl_admin_id() {
                return pdl_admin_id;
            }

            public void setPdl_admin_id(String pdl_admin_id) {
                this.pdl_admin_id = pdl_admin_id;
            }

            public String getPdl_admin_name() {
                return pdl_admin_name;
            }

            public void setPdl_admin_name(String pdl_admin_name) {
                this.pdl_admin_name = pdl_admin_name;
            }

            public String getPdl_available_amount() {
                return pdl_available_amount;
            }

            public void setPdl_available_amount(String pdl_available_amount) {
                this.pdl_available_amount = pdl_available_amount;
            }

            public String getPdl_desc() {
                return pdl_desc;
            }

            public void setPdl_desc(String pdl_desc) {
                this.pdl_desc = pdl_desc;
            }

            public String getPdl_freeze_amount() {
                return pdl_freeze_amount;
            }

            public void setPdl_freeze_amount(String pdl_freeze_amount) {
                this.pdl_freeze_amount = pdl_freeze_amount;
            }

            public String getPdl_id() {
                return pdl_id;
            }

            public void setPdl_id(String pdl_id) {
                this.pdl_id = pdl_id;
            }

            public String getPdl_image() {
                return pdl_image;
            }

            public void setPdl_image(String pdl_image) {
                this.pdl_image = pdl_image;
            }

            public String getPdl_payment_code() {
                return pdl_payment_code;
            }

            public void setPdl_payment_code(String pdl_payment_code) {
                this.pdl_payment_code = pdl_payment_code;
            }

            public String getPdl_payment_name() {
                return pdl_payment_name;
            }

            public void setPdl_payment_name(String pdl_payment_name) {
                this.pdl_payment_name = pdl_payment_name;
            }

            public String getPdl_sn() {
                return pdl_sn;
            }

            public void setPdl_sn(String pdl_sn) {
                this.pdl_sn = pdl_sn;
            }

            public String getPdl_type() {
                return pdl_type;
            }

            public void setPdl_type(String pdl_type) {
                this.pdl_type = pdl_type;
            }

            public String getPdl_user_id() {
                return pdl_user_id;
            }

            public void setPdl_user_id(String pdl_user_id) {
                this.pdl_user_id = pdl_user_id;
            }

            public String getPdl_user_name() {
                return pdl_user_name;
            }

            public void setPdl_user_name(String pdl_user_name) {
                this.pdl_user_name = pdl_user_name;
            }
        }
    }
}
