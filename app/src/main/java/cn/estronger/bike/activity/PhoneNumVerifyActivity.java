package cn.estronger.bike.activity;

import android.content.Intent;
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
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.MyApp;
import cn.estronger.bike.bean.Login;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.CountDownTimerUtils;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Validator;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/11.
 */

public class PhoneNumVerifyActivity extends BaseActivity implements TextWatcher, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_code)
    EditText et_code;
    @ViewInject(R.id.btn_get_code)
    Button btn_get_code;
    @ViewInject(R.id.btn_start)
    Button btn_start;
    private String type;
    private String state; //  0未交押金，1未实名认证，2未充值，3正常状态
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbone_num_verify);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        tv_title.setText("手机验证");
        btn_start.setEnabled(false);
        et_phone.addTextChangedListener(this);//增加文字监听   用于判断开始按钮是否可用
        et_code.addTextChangedListener(this);
    }

    @Event(value = {R.id.btn_start, R.id.iv_back, R.id.tv_agreement, R.id.btn_get_code})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_agreement://协议
                startActivity(new Intent(PhoneNumVerifyActivity.this, WebViewActivity.class).putExtra("title", "小强单车用户协议"));
                break;
            case R.id.btn_get_code://获取验证码
                //判断手机号是否正确
                if (!Validator.isMobileNO(et_phone.getText().toString().trim())) {
                    ToastUtils.showShort(this, "请正确输入手机号");
                    return;
                }
                Connect.getRegCode(this, et_phone.getText().toString().trim(), this);
                break;
            case R.id.btn_start://开始
                if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(state)) {
                    if ("register".equals(type)) {//没注册的情况
                        Connect.register(this, et_phone.getText().toString().trim(), et_code.getText().toString().trim(), this);
                    } else {//注册过的情况
                        Connect.login(this, et_phone.getText().toString().trim(), et_code.getText().toString().trim(), this);
                    }
                } else {
                    ToastUtils.showShort(this, "你还未获取验证码");
                }
                break;
        }
    }

    //文字改变之后
    @Override
    public void afterTextChanged(Editable editable) {
        if (Validator.isMobileNO(et_phone.getText().toString().trim()) && Validator.isCode(et_code.getText().toString().trim())) {
            btn_start.setEnabled(true);//设置按钮可点击
        } else {
            btn_start.setEnabled(false);//设置按钮不可点击
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }


    //成功的回调
    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        switch (whereRequest) {
            case Connect.SEND_REGISTER_CODE:
                if (getCode(result) == 0) {
                    try {
                        JSONObject data = new JSONObject(result).getJSONObject("data");
                        type = data.getString("type");
                        state = data.getString("state");
                    } catch (JSONException e) {
                    }
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btn_get_code, 60000, 1000);
                    mCountDownTimerUtils.start();//在接口返回成功后   开始倒计时
                }
                break;
            case Connect.REGISTER:
                if (getCode(result) == 0) {
                    Login userInfo = new Gson().fromJson(result, Login.class);
                    Login.DataBean data = userInfo.getData();
                    MyApp.userId = data.getUser_id();
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "user_id", data.getUser_id());
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "phone", et_phone.getText().toString().trim());
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "state", state);
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "avatar", data.getAvatar());
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "credit_point", data.getCredit_point());
                    PrefUtils.setString(PhoneNumVerifyActivity.this, "nickname", data.getNickname());

                    if ("0".equals(state)) {//去充押金页面
                        startActivity(new Intent(PhoneNumVerifyActivity.this, TopUpDepositActivity.class));
                    } else if ("1".equals(state)) {//去实名认证界面
                        startActivity(new Intent(PhoneNumVerifyActivity.this, CertificationActivity.class));
                    } else {
                        startActivity(new Intent(PhoneNumVerifyActivity.this, MainActivity.class));
                    }
                    finish();
                }
                break;
            case Connect.LOGIN:
                if (getCode(result) == 0) {
                    try {
                        JSONObject data = new JSONObject(result).getJSONObject("data");
                        MyApp.userId = data.getString("user_id");
                        PrefUtils.setString(PhoneNumVerifyActivity.this, "user_id", data.getString("user_id"));
                        PrefUtils.setString(PhoneNumVerifyActivity.this, "phone", et_phone.getText().toString().trim());
                        PrefUtils.setString(PhoneNumVerifyActivity.this, "state", state);
                        PrefUtils.setString(PhoneNumVerifyActivity.this, "avatar", state);
                    } catch (JSONException e) {
                    }
                    if ("0".equals(state)) {//去充值页面
                        startActivity(new Intent(PhoneNumVerifyActivity.this, TopUpDepositActivity.class));
                    } else if ("1".equals(state)) {//去实名认证界面
                        startActivity(new Intent(PhoneNumVerifyActivity.this, CertificationActivity.class));
                    }
                    finish();
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
            case Connect.REGISTER:
                break;
            case Connect.LOGIN:
                break;
        }
    }
}
