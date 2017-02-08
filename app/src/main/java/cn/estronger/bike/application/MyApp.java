package cn.estronger.bike.application;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by MrLv on 2016/12/5.
 */

public class MyApp extends Application {
    public static String userId = "";
//    public static String state = "";//  0未交押金，1未实名认证，2未充值，3正常状态
    public static boolean isLogout = false;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        ZXingLibrary.initDisplayOpinion(this);//初始化Zxing  扫码
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
    }
}
