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

public class RegAndAccountActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_and_account);
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
        tv_title.setText("注册与账户");
    }

    @Event(value = {R.id.iv_back, R.id.rl_realname_no_pass, R.id.rl_realname_isexist, R.id.rl_about_invitation_code,
            R.id.rl_modifi_phone})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_realname_no_pass:
                startActivity(new Intent(RegAndAccountActivity.this,WebViewActivity.class).putExtra("title","我实名认证无法通过"));
                break;
            case R.id.rl_realname_isexist:
                startActivity(new Intent(RegAndAccountActivity.this,WebViewActivity.class).putExtra("title","实名认证时提示身份信息已存在"));
                break;
            case R.id.rl_about_invitation_code:
                startActivity(new Intent(RegAndAccountActivity.this,WebViewActivity.class).putExtra("title","邀请码是什么"));
                break;
            case R.id.rl_modifi_phone:
                startActivity(new Intent(RegAndAccountActivity.this,WebViewActivity.class).putExtra("title","我需要更换手机号"));
                break;
        }
    }


}
