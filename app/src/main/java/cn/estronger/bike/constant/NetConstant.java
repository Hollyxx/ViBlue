package cn.estronger.bike.constant;

/**
 * Created by MrLv on 2016/12/5.
 */

public interface NetConstant {
    /**
     * 服务器地址
     */
    String SERVER_URL="http://120.76.98.150/bike/";
    /**
     * 微信AppId
     */
    String APPID="wx8429d4b54752d9a0";
    /**
     * 支付宝回调地址
     */
    String ALI_CALL_BACK="wx2cecbd1d08ac4511";
    /**
     * logo Url
     */
    String IMG_URL="http://bike.e-stronger.com/bike/static/images/logo_120.png";
    /**
     * 开锁
     */
    String OPEN_LOCK=SERVER_URL+"api/index.php?route=operator/operator/openLock";
    /**
     * 获取验证码
     */
    String SEND_REGISTER_CODE=SERVER_URL+"api/index.php?route=account/account/sendRegisterCode";
    /**
     * 注册
     */
    String REGISTER=SERVER_URL+"api/index.php?route=account/account/register";
    /**
     * 登录
     */
    String LOGIN=SERVER_URL+"api/index.php?route=account/account/login";
    /**
     * 充值押金生成押金订单
     */
    String DEPOSIT=SERVER_URL+"api/index.php?route=account/account/deposit";
    /**
     * 微信统一下单
     */
    String WX_PAY_CHARGE_DEPOSIT=SERVER_URL+"api/index.php?route=account/deposit/wxPayChargeDeposit";
    /**
     * 支付宝统一下单
     */
    String ALIPAY_CHARGE_DEPOSIT=SERVER_URL+"api/index.php?route=account/deposit/aliPayChargeDeposit";
    /**
     * 实名认证
     */
    String IDENTITY=SERVER_URL+"api/index.php?route=account/account/identity";
    /**
     * 寻车响铃
     */
    String BEEP_LOCK=SERVER_URL+"api/index.php?route=operator/operator/beepLock";
    /**
     * 生成充值订单
     */
    String CHARGING=SERVER_URL+"api/index.php?route=account/account/charging";
    /**
     *获取附近的车联  marker
     */
    String GET_BICYCLE_LOCATION=SERVER_URL+"api/index.php?route=location/location/getBicycleLocation";
    /**
     *推荐注册人
     */
    String SIGN_RECOMMEND=SERVER_URL+"api/index.php?route=account/account/signRecommend";
    /**
     *单车预约
     */
    String BOOK=SERVER_URL+"api/index.php?route=account/order/book";
    /**
     *订单详情
     */
    String GET_ORDER_INFO=SERVER_URL+"api/index.php?route=account/order/getOrderInfo";
    /**
     *取消预约
     */
    String CANCEL_ORDER=SERVER_URL+"api/index.php?route=account/order/cancelOrder";
    /**
     *获取用户个人信息
     */
    String INFO=SERVER_URL+"api/index.php?route=account/account/info";
    /**
     *修改个人信息
     */
    String UPDATE_INFO=SERVER_URL+"api/index.php?route=account/account/updateInfo";
    /**
     *更新手机号
     */
    String UPDATE_MODILE=SERVER_URL+"api/index.php?route=account/account/updateMobile";
    /**
     *获取信用记录
     */
    String GET_CREDIT_LOG=SERVER_URL+"api/index.php?route=account/account/getCreditLog";
    /**
     *获取钱包信息
     */
    String GET_WALLET_INFO=SERVER_URL+"api/index.php?route=account/account/getWalletInfo";
    /**
     *获取钱包明细
     */
    String GET_WALLET_DETAIL=SERVER_URL+"api/index.php?route=account/account/getWalletDetail";
    /**
     *获取我的行程列表
     */
    String GET_ORDERS=SERVER_URL+"api/index.php?route=account/account/getOrders";
    /**
     *获取行程详情
     */
    String GET_ORDER_DETAIL=SERVER_URL+"api/index.php?route=account/account/getOrderDetail";
    /**
     *获取我的消息列表
     */
    String GET_MESSAGES=SERVER_URL+"api/index.php?route=account/account/getMessages";
    /**
     *查找锁位置
     */
    String LOCK_POSITION=SERVER_URL+"api/index.php?route=operator/operator/lockPosition";
    /**
     *退出登录
     */
    String LOG_OUT=SERVER_URL+"api/index.php?route=account/account/logout";
    /**
     *更新头像
     */
    String UPDATE_AVATAR=SERVER_URL+"api/index.php?route=account/account/updateAvatar";
    /**
     *获取当前订单状态
     */
    String CURRENT=SERVER_URL+"api/index.php?route=account/order/current";
    /**
     *获取协议、用户指南等文档链接地址
     */
    String INDEX=SERVER_URL+"api/index.php?route=article/index";
    /**
     *退押金
     */
    String CASH_APPLY=SERVER_URL+"api/index.php?route=account/account/cashApply";
    /**
     *故障上报
     */
    String ADD_FAULT=SERVER_URL+"api/index.php?route=fault/fault/addFault";
    /**
     *违停上报
     */
    String ADD_ILLEGAL_PARKING=SERVER_URL+"api/index.php?route=fault/fault/addIllegalParking";
    /**
     *停车拍照
     */
    String ADD_NORMAL_PARKING=SERVER_URL+"api/index.php?route=feedback/feedback/addNormalParking";
    /**
     *骑行完成
     */
    String SHARE_NOW="http://bike.e-stronger.com/bike/wechat/myTripShare.html?";
    /**
     *行程分享
     */
    String SHARE="http://bike.e-stronger.com/bike/wechat/myTripShare.html?";
    /**
     *停车拍照
     */
    String CONTACT=SERVER_URL+"api/index.php?route=system/common/contact";
    /**
     *获取最新的安卓版本信息
     */
    String VERSION=SERVER_URL+"api/index.php?route=system/common/version";
    /**
     *获取历史优惠券列表
     */
    String GET_EXPIRED_LIST=SERVER_URL+"api/index.php?route=account/coupon/getExpiredList";
    /**
     *获取可用史优惠券列表
     */
    String GET_COUPON_LIST=SERVER_URL+"api/index.php?route=account/coupon/getCouponList";
    /**
     *获取分享的加密内容
     */
    String GET_ENCRYPT_CODE=SERVER_URL+"api/index.php?route=account/account/getEncryptCode";

 }
