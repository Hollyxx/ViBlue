package cn.estronger.bike.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iflytek.thirdparty.C;
import android.app.ActivityManager.RunningAppProcessInfo;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtil;
import cn.estronger.bike.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static cn.estronger.bike.activity.MainActivity.mlocationClient;

/**
 * Created by MrLv on 2016/12/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static int height,width;
    public boolean isForeground = false;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        height=getHeight();
        width=getResources().getDisplayMetrics().widthPixels;
    }
    public int getHeight(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }



    public static void setHeightRelativeLayout(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.view_header);
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        linearParams.height = 0;// 控件的高强制设成0
        linearParams.width = 0;// 控件的宽强制设成0
        linearLayout.setLayoutParams(linearParams);
    }


    public static void setHeightLinearLayout(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.view_header);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linearParams.height = 0;// 控件的高强制设成0
        linearParams.width = 0;// 控件的宽强制设成0
        linearLayout.setLayoutParams(linearParams);
    }
    public static void setHeightLinearLayout1(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.view_header);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linearParams.height = height;//
        linearParams.width = width;//
        linearLayout.setLayoutParams(linearParams);
    }

    /**
     * 显示Json里的消息
     * @param object
     */
    public void showMsg(String object) {
        try {
            JSONObject j=new JSONObject(object);
            ToastUtils.showShort(this,j.getString("msg"));
        } catch (JSONException e) {
        }
    }
    /**
     * 显示Json里的消息
     * @param object
     */
    public int getCode(String object) {
        try {
            JSONObject j=new JSONObject(object);
           return j.getInt("errorCode");
        } catch (JSONException e) {
            return 1;
        }
    }

    /**
     * 显示任意时长的toast
     * @param toast
     * @param cnt
     */
    public void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }
    /**
     * 避免重复登录
    */
    public void exitLogin(Context context,String result) {
        showMsg(result);
        SysApplication.getInstance().exit();//关闭整个程序
        startActivity(new Intent(context,PhoneNumVerifyActivity.class).putExtra("from","relogin"));
        PrefUtils.clear(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isForeground == false) {
            isForeground = true;
            //OnResume()方法中判断App是否进入前台，如果进入前台就做自己想要的操作。
            MainActivity.mlocationClient.startLocation();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (!isAppOnForeground()) {
            isForeground = false;
            //在Activity生命周期中的onPause()中判断是否进入后台
            MainActivity.mlocationClient.stopLocation();
//        }
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isApplicationShowing(String packageName,Context context) {
        boolean result = false;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    int status = runningAppProcessInfo.importance;
                    if (status == RunningAppProcessInfo.IMPORTANCE_VISIBLE
                            || status == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
