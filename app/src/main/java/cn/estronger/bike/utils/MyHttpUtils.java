package cn.estronger.bike.utils;

import android.content.Context;
import android.graphics.Color;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;

/**
 * Created by MrLv on 2016/12/15.
 */

public class MyHttpUtils {

    String results = "";

    /**
     * @param whereRequest 请求名字
     * @param url          请求地址
     * @param map          请求参数map
     * @param callback     回调方法
     * @return
     */
    public static void xutilsGet(Context context,final int whereRequest, String url, HashMap<String, String> map, final MyHttpCallback callback) {
        final KProgressHUD hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setWindowColor(Color.parseColor("#828282"))
                .setLabel("L O A D I N G")
                .setCancellable(false)
                .show();
        RequestParams params = new RequestParams(url);
        for (String key : map.keySet()) {
            String value = map.get(key);
            params.addBodyParameter(key, value);
        }
        params.addBodyParameter("token", "token值");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                hud.dismiss();
                callback.onSuccess(result, whereRequest);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hud.dismiss();
                callback.onError("出错了" + "onError", whereRequest);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                hud.dismiss();
                callback.onError("出错了" + "onCancelled", whereRequest);
            }

            @Override
            public void onFinished() {
                hud.dismiss();
            }
        });
    }

    /**
     * @param whereRequest 请求名字
     * @param url          请求地址
     * @param map          请求参数map
     * @param callback     回调方法
     */
    public static void xutilsPost(Context context, final int whereRequest, String url, HashMap<String, String> map, final MyHttpCallback callback) {
        final KProgressHUD hud = KProgressHUD.create(context)//把progressBar写在这里的话  就省去了再activity写很多的麻烦
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setWindowColor(Color.parseColor("#828282"))
                .setLabel("L O A D I N G")
                .setCancellable(false)
                .show();
        RequestParams params = new RequestParams(url);
        for (String key : map.keySet()) {
            String value = map.get(key);
            params.addBodyParameter(key, value);
        }
        params.addBodyParameter("token", "token值");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result, whereRequest);
                hud.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError("出错了" + "onError", whereRequest);
                hud.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onError("出错了" + "onCancelled", whereRequest);
                hud.dismiss();
            }

            @Override
            public void onFinished() {
                hud.dismiss();
            }
        });
    }


    public static void xutilsPostUpload(Context context, final int whereRequest, String url, HashMap<String, String> map,String cname,String filename ,final MyHttpCallback callback) {
        final KProgressHUD hud = KProgressHUD.create(context)//把progressBar写在这里的话  就省去了再activity写很多的麻烦
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setWindowColor(Color.parseColor("#828282"))
                .setLabel("L O A D I N G")
                .setCancellable(false)
                .show();
        RequestParams params = new RequestParams(url);
        for (String key : map.keySet()) {
            String value = map.get(key);
            params.addBodyParameter(key, value);
        }
        params.addBodyParameter(cname, new File(filename));
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result, whereRequest);
                hud.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError("出错了" + "onError", whereRequest);
                hud.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onError("出错了" + "onCancelled", whereRequest);
                hud.dismiss();
            }

            @Override
            public void onFinished() {
                hud.dismiss();
            }
        });
    }
    /**
     * @param whereRequest 请求名字
     * @param url          请求地址
     * @param map          请求参数map
     * @param callback     回调方法
     */
    public static void xutilsPostNoPb(final int whereRequest, String url, HashMap<String, String> map, final MyHttpCallback callback) {
        RequestParams params = new RequestParams(url);
        for (String key : map.keySet()) {
            String value = map.get(key);
            params.addBodyParameter(key, value);
        }
        params.addBodyParameter("token", "token值");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result, whereRequest);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError("出错了" + "onError", whereRequest);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onError("出错了" + "onCancelled", whereRequest);
            }

            @Override
            public void onFinished() {
            }
        });
    }


    public interface MyHttpCallback {
        void onSuccess(String result, int whereRequest);

        void onError(String errorMsg, int whereRequest);
    }

}
