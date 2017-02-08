package cn.estronger.bike.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.OpenCameraActivity;
import com.tools.SystemTools;
import com.tools.UsualTools;

import cn.estronger.bike.R;

public class PopWindowUtil implements OnClickListener {
	private PopupWindow pop;
	private LinearLayout ll_popup;
	private Activity activity;

	public PopWindowUtil(Activity activity) {
		super();
		this.activity = activity;
	}

	public void initPopWindow(OnClickListener photoListener) {
		pop = new PopupWindow(activity);
		View view = LayoutInflater.from(activity).inflate(
				R.layout.layout_popupwindows, null);
		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
		parent.setOnClickListener(this);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(photoListener);
		bt3.setOnClickListener(this);
	}

	public void showPopWindow(View parentView) {
		pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
	}

	public void hidePopWindow() {
		pop.dismiss();
		ll_popup.clearAnimation();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.parent:
			pop.dismiss();
			ll_popup.clearAnimation();
			break;
		case R.id.item_popupwindows_camera:
			SystemTools.TAKE_PICTURE = true;
			UsualTools.jumpActivityForResult(activity,
					OpenCameraActivity.class,
					OpenCameraActivity.TAKE_PICTURE_CODE);
			pop.dismiss();
			ll_popup.clearAnimation();
			break;

		case R.id.item_popupwindows_cancel:
			pop.dismiss();
			ll_popup.clearAnimation();
			break;
		default:
			break;
		}

	}
}
