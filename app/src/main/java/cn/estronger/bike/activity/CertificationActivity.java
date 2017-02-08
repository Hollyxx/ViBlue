package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Validator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/11.
 */

public class CertificationActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.btn_certification)
    Button btn_certification;
    @ViewInject(R.id.et_real_name)
    EditText et_real_name;
    @ViewInject(R.id.et_card)
    EditText et_card;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
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
        tv_title.setText("实名认证");
        iv_back.setOnClickListener(this);
        btn_certification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_certification:
                String name = et_real_name.getText().toString().trim();
                String card = et_card.getText().toString().trim();
                if (TextUtils.isEmpty(name)|| Validator.isEmoji(name)||Validator.compileExChar(name)){
                    ToastUtils.showShort(this,"请正确输入真实姓名");
                    return;
                }
                if (TextUtils.isEmpty(card)|| !Validator.IDCardValidate(card)){
                    ToastUtils.showShort(this,"请正确输入身份证号");
                    return;
                }
                Connect.identity(this,name,card,this);
                break;
        }

    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.IDENTITY:
                if (getCode(result) == 0) {
                    PrefUtils.setString(this,"state","2");
                    startActivity(new Intent(this,RegisterOverActivity.class));
                    finish();
                }else if (getCode(result) == 99){
                    SysApplication.getInstance().exit();//关闭整个程序
                    startActivity(new Intent(this,PhoneNumVerifyActivity.class));
                    PrefUtils.clear(this);
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.IDENTITY:
                break;
        }
    }
}
