package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.utils.DateFormaterUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static cn.estronger.bike.R.id.rl_bike_bug;


/**
 * Created by MrLv on 2016/12/12.
 */

public class NeedHelpActivity extends BaseActivity{
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_time)
    TextView tv_time;
    @ViewInject(R.id.tv_bike_num)
    TextView tv_bike_num;
    @ViewInject(R.id.tv_duration)
    TextView tv_duration;
    @ViewInject(R.id.tv_price)
    TextView tv_price;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);
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
        tv_title.setText("需要帮助");
        tv_time.setText(DateFormaterUtils.times2(getIntent().getStringExtra("time")));
        tv_bike_num.setText(getIntent().getStringExtra("bike_sn"));
        tv_duration.setText(getIntent().getIntExtra("duration",0)+"分钟");
        tv_price.setText(getIntent().getStringExtra("order_amount")+"元");
    }

    @Event(value = {R.id.iv_back, rl_bike_bug, R.id.rl_other})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case rl_bike_bug:
                startActivity(new Intent(NeedHelpActivity.this,FindBikeFaultActivity.class).putExtra("bike_sn",getIntent().getStringExtra("bike_sn")));
                break;
            case R.id.rl_other:
                startActivity(new Intent(NeedHelpActivity.this,OtherActivity.class).putExtra("bike_sn",getIntent().getStringExtra("bike_sn")));
                break;
        }
    }


}
