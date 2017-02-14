package cn.estronger.bike.connect;

import android.content.Context;

import cn.estronger.bike.constant.NetConstant;
import cn.estronger.bike.utils.JZLocationConverter;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;

import com.tools.SystemTools;

import org.xutils.common.util.MD5;

import java.util.HashMap;

/**
 * Created by MrLv on 2016/12/14.
 */

public class Connect {

    public static final int ZXING_CODE = 0;
    public static final int OPEN_LOCK = 1;
    public static final int SEND_REGISTER_CODE = 2;
    public static final int REGISTER = 3;
    public static final int LOGIN = 4;
    public static final int DEPOSIT = 5;
    public static final int SEACH = 6;
    public static final int WX_PAY_CHARGE_DEPOSIT = 7;
    public static final int ALIPAY_CHARGE_DEPOSIT = 8;
    public static final int IDENTITY = 9;
    public static final int BEEP_LOCK = 10;
    public static final int CHARGING = 11;
    public static final int PERM = 12;
    public static final int GET_BICYCLE_LOCATION = 13;
    public static final int SIGN_RECOMMEND = 14;
    public static final int BOOK = 15;
    public static final int GET_ORDER_INFO = 16;
    public static final int CANCEL_ORDER = 17;
    public static final int INFO = 18;
    public static final int UPDATE_INFO = 19;
    public static final int UPDATE_MODILE = 20;
    public static final int GET_CREDIT_LOG_PB = 21;
    public static final int GET_WALLET_INFO = 22;
    public static final int GET_WALLET_DETAIL = 23;
    public static final int GET_ORDERS = 24;
    public static final int GET_ORDER_DETAIL = 25;
    public static final int GET_MESSAGES = 26;
    public static final int LOCK_POSITION = 27;
    public static final int LOG_OUT = 28;
    public static final int UPDATE_AVATAR = 28;
    public static final int CURRENT = 29;
    public static final int INDEX = 30;
    public static final int REQUEST_PERMISSION_SETTING = 31;
    public static final int CASH_APPLY = 32;
    public static final int ADD_FAULT = 33;
    public static final int ADD_ILLEGAL_PARKING = 34;
    public static final int ADD_NORMAL_PARKING = 35;
    public static final int CONTACT = 36;
    public static final int VERSION = 37;
    public static final int GET_EXPIRED_LIST = 38;
    public static final int GET_COUPON_LIST = 39;
    public static final int GET_ENCRYPT_CODE = 40;

    /**
     * 开锁指令
     *
     * @param device_id 设备ID
     * @param callback  回调
     */
    public static void openLock(Context context, String device_id, String lat, String lng, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("device_id", device_id);
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context, OPEN_LOCK, NetConstant.OPEN_LOCK, map, callback);
    }

    /**
     * 获取注册的验证码
     *
     * @param mobile   手机号
     * @param callback 回调
     */
    public static void getRegCode(Context context, String mobile, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("mobile", mobile);
        MyHttpUtils.xutilsPost(context, SEND_REGISTER_CODE, NetConstant.SEND_REGISTER_CODE, map, callback);
    }

    /**
     * 注册
     *
     * @param mobile   手机号
     * @param code     验证码
     * @param callback 回调
     */
    public static void register(Context context, String mobile, String code, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("code", code);
        map.put("uuid", SystemTools.getPhoneId());
        MyHttpUtils.xutilsPost(context, REGISTER, NetConstant.REGISTER, map, callback);
    }

    /**
     * 登录
     *
     * @param context
     * @param mobile
     * @param code
     * @param callback
     */
    public static void login(Context context, String mobile, String code, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("mobile", mobile);
        map.put("code", code);
        map.put("uuid", SystemTools.getPhoneId());
        MyHttpUtils.xutilsPost(context, LOGIN, NetConstant.LOGIN, map, callback);
    }

    /**
     * 充值押金生成押金订单
     *
     * @param context
     * @param callback
     */
    public static void deposit(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context, DEPOSIT, NetConstant.DEPOSIT, map, callback);
    }

    /**
     * 微信支付
     *
     * @param context
     * @param pdr_sn
     * @param callback
     */
    public static void wxPay(Context context, String pdr_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("pdr_sn", pdr_sn);
        MyHttpUtils.xutilsPost(context, WX_PAY_CHARGE_DEPOSIT, NetConstant.WX_PAY_CHARGE_DEPOSIT, map, callback);
    }

    /**
     * 支付宝支付
     *
     * @param context
     * @param pdr_sn
     * @param callback
     */
    public static void aliPay(Context context, String pdr_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("pdr_sn", pdr_sn);
        MyHttpUtils.xutilsPost(context, ALIPAY_CHARGE_DEPOSIT, NetConstant.ALIPAY_CHARGE_DEPOSIT, map, callback);
    }

    /**
     * 实名认证
     *
     * @param context
     * @param real_name 姓名
     * @param identity  身份证
     * @param callback
     */
    public static void identity(Context context, String real_name, String identity, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("real_name", real_name);
        map.put("identity", identity);
        MyHttpUtils.xutilsPost(context, IDENTITY, NetConstant.IDENTITY, map, callback);
    }

    /**
     * 寻车响铃
     *
     * @param context
     * @param device_id
     * @param callback
     */
    public static void beepLock(Context context, String device_id, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("device_id", device_id);
        MyHttpUtils.xutilsPostNoPb(BEEP_LOCK, NetConstant.BEEP_LOCK, map, callback);
    }

    /**
     * 生成充值订单
     *
     * @param context
     * @param amount   金额
     * @param callback
     */
    public static void charging(Context context, String amount, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("amount", amount);
        MyHttpUtils.xutilsPostNoPb(CHARGING, NetConstant.CHARGING, map, callback);
    }


    /**
     * 获取附近的车辆 marker
     *
     * @param lat      纬度
     * @param lng      经度
     * @param zoom     缩放级别
     * @param type     单车类型  0全部 1单人  2双人 3家庭
     * @param callback
     */
    public static void getMarker(String lat, String lng, String zoom, String type, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        JZLocationConverter.LatLng latlng = JZLocationConverter.gcj02ToWgs84(new JZLocationConverter.LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));
        map.put("lat", latlng.getLatitude() + "");
        map.put("lng", latlng.getLongitude() + "");
        map.put("zoom", zoom);
        map.put("type", type);
        MyHttpUtils.xutilsPostNoPb(GET_BICYCLE_LOCATION, NetConstant.GET_BICYCLE_LOCATION, map, callback);
    }


    /**
     * 获取当前订单
     *
     * @param context   上下文
     * @param device_id 设备id
     * @param order_sn  订单id
     * @param callback  回调
     */
    public static void currentOrder(Context context, String device_id, String order_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("device_id", device_id);
        map.put("order_sn", order_sn);
        MyHttpUtils.xutilsPostNoPb(GET_ORDER_INFO, NetConstant.GET_ORDER_INFO, map, callback);
    }

    /**
     * 获取当前订单
     *
     * @param context   上下文
     * @param device_id 设备id
     * @param order_sn  订单id
     * @param callback  回调
     */
    public static void currentOrderPb(Context context, String device_id, String order_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("device_id", device_id);
        map.put("order_sn", order_sn);
        MyHttpUtils.xutilsPost(context, GET_ORDER_INFO, NetConstant.GET_ORDER_INFO, map, callback);
    }

    /**
     * 推荐注册联系人
     *
     * @param context
     * @param mobile   推荐人的手机号
     * @param callback
     */
    public static void recommend(Context context, String mobile, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("mobile", mobile);
        MyHttpUtils.xutilsPost(context, SIGN_RECOMMEND, NetConstant.SIGN_RECOMMEND, map, callback);
    }


    /**
     * 单车预约
     *
     * @param context
     * @param lat
     * @param lng
     * @param bicycle_sn
     * @param callback
     */
    public static void book(Context context, String lat, String lng, String bicycle_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        JZLocationConverter.LatLng latlng = JZLocationConverter.gcj02ToWgs84(new JZLocationConverter.LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("device_id", bicycle_sn);
        map.put("lat", latlng.getLatitude() + "");
        map.put("lng", latlng.getLongitude() + "");
        MyHttpUtils.xutilsPost(context, BOOK, NetConstant.BOOK, map, callback);
    }


    /**
     * 取消预约
     *
     * @param context
     * @param order_sn
     * @param callback
     */
    public static void cancelBook(Context context, String order_sn, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("order_sn", order_sn);
        MyHttpUtils.xutilsPost(context, CANCEL_ORDER, NetConstant.CANCEL_ORDER, map, callback);
    }

    /**
     * 获取用户个人信息
     *
     * @param context
     * @param callback
     */
    public static void info(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPostNoPb(INFO, NetConstant.INFO, map, callback);
    }

    /**
     * 修改个人信息
     *
     * @param context
     * @param nickname
     * @param callback
     */
    public static void updateInfo(Context context, String nickname, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("nickname", nickname);
        MyHttpUtils.xutilsPost(context, UPDATE_INFO, NetConstant.UPDATE_INFO, map, callback);
    }


    /**
     * 更新手机号
     *
     * @param context
     * @param mobile
     * @param code
     * @param real_name
     * @param identification
     * @param callback
     */
    public static void updateModile(Context context, String mobile, String code, String real_name, String identification, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("mobile", mobile);
        map.put("code", code);
        map.put("real_name", real_name);
        map.put("identification", identification);
        MyHttpUtils.xutilsPost(context, UPDATE_MODILE, NetConstant.UPDATE_MODILE, map, callback);
    }

    /**
     * 获取信用记录
     *
     * @param context
     * @param page     页数
     * @param callback
     */
    public static void getCreditLogPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_CREDIT_LOG_PB, NetConstant.GET_CREDIT_LOG, map, callback);
    }

    public static void getCreditLog(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_CREDIT_LOG_PB, NetConstant.GET_CREDIT_LOG, map, callback);
    }


    /**
     * 获取钱包信息
     *
     * @param context
     * @param callback
     */
    public static void getWalletInfo(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context, GET_WALLET_INFO, NetConstant.GET_WALLET_INFO, map, callback);
    }

    /**
     * 获取钱包明细
     *
     * @param context
     * @param page
     * @param callback
     */
    public static void getWalletDetailPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_WALLET_DETAIL, NetConstant.GET_WALLET_DETAIL, map, callback);
    }

    public static void getWalletDetail(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_WALLET_DETAIL, NetConstant.GET_WALLET_DETAIL, map, callback);
    }


    /**
     * 获取行程列表
     *
     * @param context
     * @param page
     * @param callback
     */
    public static void getOrdersPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_ORDERS, NetConstant.GET_ORDERS, map, callback);
    }

    public static void getOrders(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_ORDERS, NetConstant.GET_ORDERS, map, callback);
    }

    /**
     * 获取行程详情
     *
     * @param context
     * @param order_id 订单id
     * @param callback
     */
    public static void getOrdersDetail(Context context, String order_id, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("order_id", order_id);
        MyHttpUtils.xutilsPost(context, GET_ORDER_DETAIL, NetConstant.GET_ORDER_DETAIL, map, callback);
    }

    /**
     * 获取消息列表
     *
     * @param context
     * @param page
     * @param callback
     */
    public static void getMessagesPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_MESSAGES, NetConstant.GET_MESSAGES, map, callback);
    }

    public static void getMessages(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_MESSAGES, NetConstant.GET_MESSAGES, map, callback);
    }

    /**
     * 查找锁位置
     *
     * @param context
     * @param device_id 锁id
     * @param callback
     */
    public static void lockPosition(Context context, String device_id, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("device_id", device_id);
        MyHttpUtils.xutilsPost(context, LOCK_POSITION, NetConstant.LOCK_POSITION, map, callback);
    }


    /**
     * 退出登录
     *
     * @param context
     * @param callback
     */
    public static void logout(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context, LOG_OUT, NetConstant.LOG_OUT, map, callback);
    }


    /**
     * 更新个人头像
     *
     * @param context
     * @param upfile
     * @param callback
     */
    public static void updateAvatar(Context context, String upfile, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPostUpload(context, UPDATE_AVATAR, NetConstant.UPDATE_AVATAR, map, "avatar", upfile, callback);
    }


    /**
     * 获取当前订单
     *
     * @param context
     * @param callback
     */
    public static void current(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPostNoPb(CURRENT, NetConstant.CURRENT, map, callback);
    }


    /**
     * 获取用户协议等链接地址
     *
     * @param context
     * @param callback
     */
    public static void index(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("language", "");
        MyHttpUtils.xutilsPostNoPb(INDEX, NetConstant.INDEX, map, callback);
    }

    /**
     * 退押金
     *
     * @param context
     * @param callback
     */
    public static void cashApply(Context context, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context, CASH_APPLY, NetConstant.CASH_APPLY, map, callback);
    }

    /**
     * 故障上报
     *
     * @param context
     * @param lat           经度
     * @param lng           维度
     * @param fault_type    故障类型
     * @param fault_content 故障内容
     * @param upfile        文件名
     * @param callback      回调
     */
    public static void addFault(Context context, String lat, String lng, String bicycle_sn, String fault_type, String fault_content, String upfile, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("bicycle_sn", bicycle_sn);
        map.put("fault_type", fault_type);
        map.put("fault_content", fault_content);
        if ("".equals(upfile)) {
            MyHttpUtils.xutilsPost(context, ADD_FAULT, NetConstant.ADD_FAULT, map, callback);
        } else {
            MyHttpUtils.xutilsPostUpload(context, ADD_FAULT, NetConstant.ADD_FAULT, map, "fault_image", upfile, callback);
        }
    }


    /**
     * 违停举报
     *
     * @param context
     * @param lat
     * @param lng
     * @param bicycle_sn
     * @param content
     * @param upfile
     * @param callback
     * @param type   默认违停1，其他为2
     */
    public static void addIllegalParking(Context context, String lat, String lng, String bicycle_sn, String content, String upfile, String type,MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("bicycle_sn", bicycle_sn);
        map.put("content", content);
        map.put("type", type);
        if ("".equals(upfile)) {
            MyHttpUtils.xutilsPost(context, ADD_ILLEGAL_PARKING, NetConstant.ADD_ILLEGAL_PARKING, map, callback);
        } else {
            MyHttpUtils.xutilsPostUpload(context, ADD_ILLEGAL_PARKING, NetConstant.ADD_ILLEGAL_PARKING, map, "file_image", upfile, callback);
        }
    }


    /**
     * 停车拍照
     * @param context
     * @param lat
     * @param lng
     * @param bicycle_sn
     * @param content
     * @param upfile
     * @param callback
     */
    public static void addNormalParking(Context context, String lat, String lng, String bicycle_sn, String content, String upfile, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("bicycle_sn", bicycle_sn);
        map.put("content", content);
        MyHttpUtils.xutilsPostUpload(context, ADD_NORMAL_PARKING, NetConstant.ADD_NORMAL_PARKING, map, "parking_image", upfile, callback);
    }
    /**
     * 获取联系方式
     * @param context
     * @param callback
     */
    public static void getContact(Context context,MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        MyHttpUtils.xutilsPost(context, CONTACT, NetConstant.CONTACT, map, callback);
    }

    public static void getExpiredList(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_EXPIRED_LIST, NetConstant.GET_EXPIRED_LIST, map, callback);
    }

    public static void getExpiredListNoPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_EXPIRED_LIST, NetConstant.GET_EXPIRED_LIST, map, callback);
    }

    public static void getCouponList(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPost(context, GET_COUPON_LIST, NetConstant.GET_COUPON_LIST, map, callback);
    }

    /**
     * 获取优惠券列表
     * @param context
     * @param page
     * @param callback
     */
    public static void getCouponListNoPb(Context context, String page, MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        map.put("page", page);
        MyHttpUtils.xutilsPostNoPb(GET_COUPON_LIST, NetConstant.GET_COUPON_LIST, map, callback);
    }

    public static void getEncryptCode(Context context,  MyHttpUtils.MyHttpCallback callback) {
        HashMap map = new HashMap<String, String>();
        map.put("user_id", PrefUtils.getString(context, "user_id", "-1"));
        map.put("sign", MD5.md5(PrefUtils.getString(context, "user_id", "-1") + SystemTools.getPhoneId()));
        MyHttpUtils.xutilsPost(context,GET_ENCRYPT_CODE, NetConstant.GET_ENCRYPT_CODE, map, callback);
    }

}
