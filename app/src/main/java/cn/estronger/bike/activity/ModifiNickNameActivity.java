package cn.estronger.bike.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Validator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/12.
 */

public class ModifiNickNameActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_save)
    TextView tv_save;
    @ViewInject(R.id.et_nick)
    EditText et_nick;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifi_nick_name);
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
        iv_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        String name=getIntent().getStringExtra("name");
        et_nick.setText(name);
        et_nick.setSelection(name.length());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save://保存
                if (TextUtils.isEmpty(et_nick.getText().toString().trim())){
                    ToastUtils.showShort(this,getResources().getText(R.string.Please_enter_a_nickname));
                    return;
                }
                if (Validator.isEmoji(et_nick.getText().toString().trim())){
                    ToastUtils.showShort(this,getResources().getText(R.string.Nickname_cannot_contain_expression));
                    return;
                }
                if (Validator.compileExChar(et_nick.getText().toString().trim())){
                    ToastUtils.showShort(this,getResources().getText(R.string.Nickname_cannot_contain_symbol));
                    return;
                }
                Connect.updateInfo(ModifiNickNameActivity.this,et_nick.getText().toString().trim(),ModifiNickNameActivity.this);
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
            case Connect.UPDATE_INFO:
                if (getCode(result) == 0) {
                    finish();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.UPDATE_INFO:
                break;
        }
    }
}
