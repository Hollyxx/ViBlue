package cn.estronger.bike.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.CountDownTimerUtils;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Validator;
import cn.estronger.bike.widget.MyDialog;


/**
 * Created by MrLv on 2016/12/11.
 */

public class ChangePhoneActivity extends BaseActivity implements TextWatcher,View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.et_id_card)
    EditText et_id_card;
    @ViewInject(R.id.et_code)
    EditText et_code;
    @ViewInject(R.id.rl_get_code)
    RelativeLayout rl_get_code;
    @ViewInject(R.id.tv_get_code)
    TextView tv_get_code;
    @ViewInject(R.id.btn_start)
    Button btn_start;
    private MyDialog myDialog;

    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
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
        tv_title.setText("更换新手机号");
        btn_start.setEnabled(false);
        iv_back.setOnClickListener(this);
        rl_get_code.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        et_phone.addTextChangedListener(this);//增加文字监听   用于判断开始按钮是否可用
        et_code.addTextChangedListener(this);
        et_id_card.addTextChangedListener(this);
        et_name.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ok_btn:
                myDialog.dismiss();
                finish();
                break;
            case R.id.rl_get_code://获取验证码
                //判断手机号是否正确
                if (!Validator.isMobileNO(et_phone.getText().toString().trim())){
                    ToastUtils.showShort(this,"请正确输入手机号");
                    return;
                }
                Connect.getRegCode(this, et_phone.getText().toString().trim(), this);
                break;
            case R.id.btn_start://开始
                Connect.updateModile(this,et_phone.getText().toString().trim(),et_code.getText().toString().trim()
                        ,et_name.getText().toString().trim(),et_id_card.getText().toString().trim(),this);
                break;
        }
    }

    //成功的回调
    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.SEND_REGISTER_CODE:
                if (getCode(result) == 0) {
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(ChangePhoneActivity.this,rl_get_code,tv_get_code, 60000, 1000);
                    mCountDownTimerUtils.start();//在接口返回成功后   开始倒计时
                }
                break;
            case Connect.UPDATE_MODILE:
                if (getCode(result) == 0) {
                    EventBus.getDefault().post("change_phone");//通知上一个页面关闭
                    myDialog = new MyDialog();
                    myDialog.showNoticeDialog(ChangePhoneActivity.this, "恭喜你，手机号修改成功！", ChangePhoneActivity.this,false);
                    myDialog.show();
                }
                break;
        }
    }

    //失败的回调
    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.SEND_REGISTER_CODE:
                ToastUtils.showShort(this, "发送失败，请重试");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (Validator.isMobileNO(et_phone.getText().toString().trim()) && Validator.isCode(et_code.getText().toString().trim())
                &&Validator.IDCardValidate(et_id_card.getText().toString().trim())&&Validator.isChinese(et_name.getText().toString().trim())) {
            btn_start.setEnabled(true);//设置按钮可点击
        } else {
            btn_start.setEnabled(false);//设置按钮不可点击
        }
    }
}
