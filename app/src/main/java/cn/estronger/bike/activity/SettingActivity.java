package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.widget.MyDialog;
import cn.estronger.bike.utils.PopWindowUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/12.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.rl_user_argument)
    RelativeLayout rl_user_argument;
    @ViewInject(R.id.rl_top_up_info)
    RelativeLayout rl_top_up_info;
    @ViewInject(R.id.rl_recharge_argument)
    RelativeLayout rl_recharge_argument;
    @ViewInject(R.id.rl_expense_argument)
    RelativeLayout rl_expense_argument;
    @ViewInject(R.id.rl_update)
    RelativeLayout rl_update;
    @ViewInject(R.id.rl_about_us)
    RelativeLayout rl_about_us;
    @ViewInject(R.id.rl_exit)
    RelativeLayout rl_exit;
    private PopWindowUtil poUtil;
    private MyDialog myDialog;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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
        tv_title.setText("设置");
        iv_back.setOnClickListener(this);
        rl_user_argument.setOnClickListener(this);
        rl_top_up_info.setOnClickListener(this);
        rl_recharge_argument.setOnClickListener(this);
        rl_expense_argument.setOnClickListener(this);
        rl_update.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
        poUtil = new PopWindowUtil(this);
        poUtil.initPopWindow(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_user_argument:
                startActivity(new Intent(SettingActivity.this,WebViewActivity.class).putExtra("title","用户协议"));
                break;
            case R.id.rl_top_up_info:
                startActivity(new Intent(SettingActivity.this,WebViewActivity.class).putExtra("title","押金说明"));
                break;
            case R.id.rl_recharge_argument:
                startActivity(new Intent(SettingActivity.this,WebViewActivity.class).putExtra("title","充值协议"));
                break;
            case R.id.rl_expense_argument:
                startActivity(new Intent(SettingActivity.this,WebViewActivity.class).putExtra("title","消费协议"));
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_about_us:
                startActivity(new Intent(SettingActivity.this,AboutUsActivity.class));
                break;
            case R.id.rl_exit:
                myDialog=new MyDialog();
                myDialog.createBooleanDialog(this,"你确定要退出登录吗？","确定",this,false);
                myDialog.show();
                break;
            case R.id.sure_btn://退出登录的确定按钮
                myDialog.dismiss();
                Connect.logout(this,this);
                break;
        }
    }


    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        if (getCode(result) == 99) {
            exitLogin(this);
            return;
        }
        switch (whereRequest) {
            case Connect.LOG_OUT:
                if (getCode(result) == 0) {
                    PrefUtils.clear(SettingActivity.this);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.LOG_OUT:
                break;
        }
    }
}
