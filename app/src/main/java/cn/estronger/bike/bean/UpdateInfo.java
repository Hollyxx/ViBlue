package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/2/12.
 */

public class UpdateInfo {

    /**
     * errorCode : 0
     * msg : 操作成功
     * data : {"version_name":"1.4","version_code":"5","url":"http://bike.e-stronger.com/ebike/static/app/20170209211836135.apk","description":"押金99元\r\n优化界面\r\n优化用户体验","add_time":"1486646322"}
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
         * version_name : 1.4
         * version_code : 5
         * url : http://bike.e-stronger.com/ebike/static/app/20170209211836135.apk
         * description : 押金99元
         优化界面
         优化用户体验
         * add_time : 1486646322
         */

        private String version_name;
        private int version_code;
        private String url;
        private String description;
        private String add_time;

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
