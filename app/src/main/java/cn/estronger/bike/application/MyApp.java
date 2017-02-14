package cn.estronger.bike.application;

import android.app.Application;
import android.graphics.Color;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.callback.UpdateCheckCB;
import org.lzh.framework.updatepluginlib.callback.UpdateDownloadCB;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.model.UpdateParser;
import org.lzh.framework.updatepluginlib.util.HandlerUtil;
import org.xutils.x;

import java.io.File;

import cn.estronger.bike.bean.LockPosition;
import cn.estronger.bike.bean.UpdateInfo;
import cn.estronger.bike.constant.NetConstant;
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
        update();
    }

    private void update() {
            // 建议在Application中进行配置。
            // UpdateConfig为全局配置。当在其他页面中。使用UpdateBuilder进行检查更新时。
            // 对于没传的参数，会默认使用UpdateConfig中的全局配置
                UpdateConfig.getConfig()
                // url 与 checkEntity方法可任选一种填写，且至少必填一种。
                // 数据更新接口数据，此时默认为使用GET请求
                .url(NetConstant.VERSION)
                // 必填：用于从数据更新接口获取的数据response中。解析出Update实例。以便框架内部处理
                .jsonParser(new UpdateParser() {
                    @Override
                    public Update parse(String response) {
                        // 此处根据上面url接口返回的数据response进行update类组装。框架内部会使用此
                        // 组装的update实例判断是否需要更新以做进一步工作
                        Update update=new Update(response);
                        UpdateInfo update1 = new Gson().fromJson(response, UpdateInfo.class);
                        update.setVersionCode(update1.getData().getVersion_code());
                        // 此apk包的更新时间
                        update.setUpdateTime(System.currentTimeMillis());
                        // 此apk包的下载地址
                        update.setUpdateUrl(update1.getData().getUrl());
                        // 此apk包的版本名称
                        update.setVersionName(update1.getData().getVersion_name());
                        // 此apk包的更新内容
                        update.setUpdateContent(update1.getData().getDescription());
                        return update;
                    }
                }).checkCB(new UpdateCheckCB() {

            @Override
            public void onCheckError(int code, String errorMsg) {
//                Toast.makeText(MyApp.this, "更新失败：code:" + code + ",errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUserCancel() {
                Toast.makeText(MyApp.this, "取消更新", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCheckIgnore(Update update) {
                Toast.makeText(MyApp.this, "用户忽略此版本更新", Toast.LENGTH_SHORT).show();
            }

            public void onCheckStart() {
                // 此方法的回调所处线程异于其他回调。其他回调所处线程为UI线程。
                // 此方法所处线程为你启动更新任务是所在线程
                HandlerUtil.getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApp.this, "启动更新任务", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void hasUpdate(Update update) {
                Toast.makeText(MyApp.this, "检查到有更新", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void noUpdate() {
                Toast.makeText(MyApp.this, "暂无更新", Toast.LENGTH_SHORT).show();
            }
        })
                // apk下载的回调
                .downloadCB(new UpdateDownloadCB(){
                    @Override
                    public void onUpdateStart() {
                        Toast.makeText(MyApp.this, "下载开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateComplete(File file) {
                        Toast.makeText(MyApp.this, "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateProgress(long current, long total) {
                    }

                    @Override
                    public void onUpdateError(int code, String errorMsg) {
                        Toast.makeText(MyApp.this, "下载失败：code:" + code + ",errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
