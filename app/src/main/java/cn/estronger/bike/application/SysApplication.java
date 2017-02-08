package cn.estronger.bike.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cn.estronger.bike.activity.SettingActivity;
import cn.estronger.bike.utils.PrefUtils;

/**
 * 一个类 用来结束所有后台activity
 * Created by MrLv on 2017/2/7.
 */

public class SysApplication{
    private static SysApplication manager;
    private Stack<Activity> activityStack;
    private SysApplication() {
    }
    //单例
    public static  SysApplication getInstance() {

        if (manager == null) {
            synchronized (SysApplication.class) {
                if (manager == null) {
                    manager = new SysApplication();
                }
            }
        }
        return manager;
    }

    /**
     * 添加Activity到堆栈
     */
    // Stack <Activity> activityStack;
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void exit() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                if (i!=activityStack.size()){
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            exit();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
