package cn.estronger.bike.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.tools.MJsonUtil;

public class MyReceiver extends BroadcastReceiver {
	public static final String MESSAGE_RECEIVED_ACTION = "jpush.message";
	public static final String NOTIFY_RECEIVED_ACTION = "JPush.notify";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		Log.d("MM","[MyReceiver]"
				+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			processCustomMessage(context, bundle);
		}

		if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction())) {
			int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
					
			String content = bundle.getString(JPushInterface.EXTRA_ALERT);
			Intent inte = new Intent();
			inte.setAction(NOTIFY_RECEIVED_ACTION);
			inte.putExtra("msg", content);
			context.sendBroadcast(inte);
			Log.d("MM", "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
		}
		
		if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
				.getAction())) {
			// Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
			// 打开自定义的Activity
//			Intent i = new Intent(context, MessageActivity.class);
//			i.putExtras(bundle);
//			// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//					| Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			context.startActivity(i);
		}
	}

	// send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {

		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//		if (isLoginOut(context, message) == false) {
			Intent msgIntent = new Intent(MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra("message", message);
			context.sendBroadcast(msgIntent);
//		}
		Log.i("MM", "message" + message);
		// String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
	}

	private boolean isLoginOut(Context context, String message) {
		try {
			JSONObject object = new JSONObject(message);
			int code = MJsonUtil.getInt(object, "code");
			String device_id = MJsonUtil.getString(object, "device_id");
//			if (code == 100 && !device_id.equals(SystemTools.getPhoneId())) {
//				Utils.clearMyImf(context);
//				Intent intent = new Intent(context, MainActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
//						| Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(intent);
//				UsualTools.showShortToast(context, "你的账户在别的手机登录，请注意账号安全");
//				return true;
//			}
			return false;
		} catch (JSONException e) {
			return false;
		}

	}
}
