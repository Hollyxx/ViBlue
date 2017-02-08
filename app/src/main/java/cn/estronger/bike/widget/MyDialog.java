package cn.estronger.bike.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tools.ViewTools;

import cn.estronger.bike.R;


public class MyDialog {
	private Dialog dialog;
	private TextView titleText,contentText;

	/**
	 * 创建一个具有是和否选择的dialog，view为自定义
	 * 
	 * @param activity
	 * @param content
	 *            dialog要显示的内容
	 * @param sureListener
	 *            点击确定时，需要实现的事件监听，id为R.id.sure_btn
	 */
	public void createBooleanDialog(Activity activity, String content,
			String sureBtnText, OnClickListener sureListener,Boolean b) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.dialog_boolean, null);

		dialog = new Dialog(activity, R.style.Theme_CustomDialog);
		dialog.setContentView(view);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(b);

		contentText = (TextView) view.findViewById(R.id.content_text);
		contentText.setText(content);
		Button button = ViewTools.setButtonListener(view, R.id.sure_btn,
				sureListener);
		button.setText(sureBtnText);
		ViewTools.setButtonListener(view, R.id.cancel_btn,
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	}
	public void createBooleanDialogWithCancel(Activity activity, String content,
			String sureBtnText, OnClickListener sureListener,Boolean b,DialogInterface.OnKeyListener listener) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.dialog_boolean, null);

		dialog = new Dialog(activity, R.style.Theme_CustomDialog);
		dialog.setContentView(view);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(b);
		dialog.setOnKeyListener(listener);

		contentText = (TextView) view.findViewById(R.id.content_text);
		contentText.setText(content);
		Button button = ViewTools.setButtonListener(view, R.id.sure_btn,
				sureListener);
		button.setText(sureBtnText);
		ViewTools.setButtonListener(view, R.id.cancel_btn,sureListener);
	}

	/*public void createUpdateDialog(Activity activity, String title,String content,
			String sureBtnText, OnClickListener sureListener) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.dialog_update, null);
		dialog = Mdialog.createMyViewAndStyleDialog(activity,
				R.style.Theme_CustomDialog, view);
		titleText = (TextView) view.findViewById(R.id.title_text);
		titleText.setText(title);
		contentText = (TextView) view.findViewById(R.id.content_text);
		contentText.setText(Html.fromHtml(content));
		Button button = ViewTools.setButtonListener(view, R.id.sure_btn,
				sureListener);
		button.setText(sureBtnText);
		ViewTools.setButtonListener(view, R.id.cancel_btn,
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	}*/

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	public void setMessage(String text) {
		contentText.setText(text);
	}

	public void showNoticeDialog(Activity activity, String content, OnClickListener sureListener,Boolean b) {
		View view = LayoutInflater.from(activity).inflate(
				R.layout.dialog_notice, null);
		dialog = new Dialog(activity, R.style.Theme_CustomDialog);
		dialog.setContentView(view);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(b);
		TextView contentText = (TextView) view.findViewById(R.id.content_text);
		contentText.setText(content);
		dialog.setCanceledOnTouchOutside(b);
		ViewTools.setButtonListener(view, R.id.ok_btn,sureListener);
	}
}
