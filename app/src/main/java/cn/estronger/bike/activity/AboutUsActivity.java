package cn.estronger.bike.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.thirdparty.C;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.Contact;
import cn.estronger.bike.bean.TravelDetail;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.widget.MyDialog;

import static cn.estronger.bike.R.id.rl_phone;
import static org.xutils.x.app;

/**
 * Created by MrLv on 2016/12/12.
 */

public class AboutUsActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_ver_name)
    TextView tv_ver_name;
    @ViewInject(R.id.tv_phone_num)
    TextView tv_phone_num;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_email)
    TextView tv_email;
    @ViewInject(R.id.tv_web)
    TextView tv_web;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private MyDialog myDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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
        tv_title.setText("关于我们");
        try {
            tv_ver_name.setText("V"+x.app().getPackageManager().getPackageInfo(AboutUsActivity.this.getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
        }
        Connect.getContact(this,this);
    }

    @Event(value = {R.id.iv_back,R.id.rl_phone,R.id.rl_web})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_phone:
                myDialog = new MyDialog();
                myDialog.createBooleanDialog(this, "确认要拨打"+tv_phone_num.getText().toString()+"？", "拨打", this, false);
                myDialog.show();
                break;
            case R.id.rl_web:
//                startActivity(new Intent(AboutUsActivity.this,BaseWebActivity.class).putExtra("title","官方网站"));
                startActivity(new Intent(AboutUsActivity.this,BaseWebActivity.class).putExtra("title","官方网站"));
                break;
        }
    }


    @Override
    public void onClick(View view) {
        myDialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tv_phone_num.getText().toString());
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        switch (whereRequest) {
            case Connect.CONTACT:
                if (getCode(result) == 0) {
                    Contact contact = new Gson().fromJson(result, Contact.class);
                    tv_name.setText(contact.getData().getWechat());
                    tv_phone_num.setText(contact.getData().getPhone());
                    tv_email.setText(contact.getData().getEmail());
                    tv_web.setText(contact.getData().getWeb());
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.CONTACT:
                break;
        }
    }
}
