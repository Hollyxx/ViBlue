package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;

/**
 * Created by MrLv on 2016/12/12.
 */

public class OrderAndOpenLockActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_and_open_lock);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        tv_title.setText("预约与开关锁");
    }

    @Event(value = {R.id.iv_back, R.id.rl_realname_no_pass, R.id.rl_realname_isexist, R.id.rl_about_invitation_code})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_realname_no_pass:
                startActivity(new Intent(OrderAndOpenLockActivity.this,BaseWebActivity.class).putExtra("title","预约能为我保留多久"));
                break;
            case R.id.rl_realname_isexist:
                startActivity(new Intent(OrderAndOpenLockActivity.this,BaseWebActivity.class).putExtra("title","预约后怎么找到单车"));
                break;
            case R.id.rl_about_invitation_code:
                startActivity(new Intent(OrderAndOpenLockActivity.this,BaseWebActivity.class).putExtra("title","如何关锁"));
                break;
        }
    }


}
