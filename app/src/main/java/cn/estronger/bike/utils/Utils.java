package cn.estronger.bike.utils;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MrLv on 2016/12/14.
 */

public class Utils {
    /**
     * 设置地图的颜色
     * @param context  上下文
     * @param aMap  地图对象
     */
    public  static void setMapCustomStyleFile(Context context,AMap aMap){
        String styleName = "style_json.json";
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        aMap.setCustomMapStylePath(filePath + "/" + styleName);
    }
    public  static KProgressHUD createHud(Context context){
      return  KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setWindowColor(Color.parseColor("#828282"))
                .setLabel("L O A D I N G")
                .setCancellable(false)
                .show();
    }
    public  static KProgressHUD createAutoHud(Context context){
      return  KProgressHUD.create(context)
              .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
              .setWindowColor(Color.parseColor("#828282"))
              .setAutoDismiss(true)
              .setLabel("L O A D I N G")
              .show();
    }

    public static String getStringFromButton(View view, int textId) {
        Button textView = (Button)view.findViewById(textId);
        String content = textView.getText().toString().trim();
        return content;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * 判断相机权限是否开启
     * @return
     */
    public static boolean isCameraPermission() {
        try {
            Camera.open().release();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
