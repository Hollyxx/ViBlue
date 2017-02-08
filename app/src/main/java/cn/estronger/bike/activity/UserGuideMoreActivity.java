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

public class UserGuideMoreActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide_more);
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
        tv_title.setText("用户指南");
    }

    @Event(value = {R.id.iv_back, R.id.rl_reg_and_login, R.id.rl_order_and_openlock, R.id.rl_price_and_yajin,
            R.id.rl_about_return_bike, R.id.rl_save_bike, R.id.rl_about_bike, R.id.rl_about_ebike,
            R.id.rl_about_jifen})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_reg_and_login:
                startActivity(new Intent(UserGuideMoreActivity.this,RegAndAccountActivity.class));
                break;
            case R.id.rl_order_and_openlock:
                startActivity(new Intent(UserGuideMoreActivity.this,OrderAndOpenLockActivity.class));
                break;
            case R.id.rl_price_and_yajin:
                startActivity(new Intent(UserGuideMoreActivity.this,PriceAndDepositActivity.class));
                break;
            case R.id.rl_about_return_bike:
                startActivity(new Intent(UserGuideMoreActivity.this,RepayBikeActivity.class));
                break;
            case R.id.rl_save_bike:
                startActivity(new Intent(UserGuideMoreActivity.this,KeepUseBikeActivity.class));
                break;
            case R.id.rl_about_bike://关于单车
                startActivity(new Intent(UserGuideMoreActivity.this,AboutBikeActivity.class));
                break;
            case R.id.rl_about_ebike://关于小强
                startActivity(new Intent(UserGuideMoreActivity.this,AboutEbikeActivity.class));
                break;
            case R.id.rl_about_jifen://关于积分
                startActivity(new Intent(UserGuideMoreActivity.this,AboutScoreActivity.class));
                break;
        }
    }


}
