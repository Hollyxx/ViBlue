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

import cn.estronger.bike.R;


public class PopWindowSrevice implements OnClickListener {
	private PopupWindow pop;
	private LinearLayout ll_popup;
	private Activity activity;

	public PopWindowSrevice(Activity activity) {
		super();
		this.activity = activity;
	}

	public void initPopWindow(OnClickListener photoListener) {
		pop = new PopupWindow(activity);
		View view = LayoutInflater.from(activity).inflate(
				R.layout.layout_pop_service, null);
		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view.findViewById(R.id.btn_report_illegally_park);
		Button bt2 = (Button) view.findViewById(R.id.btn_find_bug);
		Button bt3 = (Button) view.findViewById(R.id.btn_help);
		Button bt4 = (Button) view.findViewById(R.id.btn_cancel);
		parent.setOnClickListener(this);
		bt1.setOnClickListener(photoListener);
		bt2.setOnClickListener(photoListener);
		bt3.setOnClickListener(photoListener);
		bt4.setOnClickListener(this);
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
		case R.id.btn_cancel:
			pop.dismiss();
			ll_popup.clearAnimation();
			break;
		default:
			break;
		}

	}
}
