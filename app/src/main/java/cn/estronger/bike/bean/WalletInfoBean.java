package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2016/12/31.
 */

public class WalletInfoBean {
    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"deposit":"199.00","deposit_state":"1","available_deposit":"9.14","freeze_deposit":"0.30"}
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
         * deposit : 199.00
         * deposit_state : 1
         * available_deposit : 9.14
         * freeze_deposit : 0.30
         */

        private String deposit;
        private String deposit_state;
        private String available_deposit;
        private String freeze_deposit;

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

        public String getAvailable_deposit() {
            return available_deposit;
        }

        public void setAvailable_deposit(String available_deposit) {
            this.available_deposit = available_deposit;
        }

        public String getFreeze_deposit() {
            return freeze_deposit;
        }

        public void setFreeze_deposit(String freeze_deposit) {
            this.freeze_deposit = freeze_deposit;
        }
    }
}
