package cn.estronger.bike.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import cn.estronger.bike.R;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Validator;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by MrLv on 2016/12/11.
 */

@RuntimePermissions
public class RegisterOverActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback{
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_over);
        x.view().inject(this);
        RegisterOverActivityPermissionsDispatcher.showContactWithCheck(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        tv_title.setText("注册完成");
    }

    //通过注解  设置点击事件
    @Event(value = {R.id.iv_back, R.id.iv_contact,R.id.rl_skip,R.id.btn_reg_over})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_skip://跳过按钮
                finish();
                break;
            case R.id.iv_contact:
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 2);
                break;
            case R.id.btn_reg_over://注册完成
                if (!Validator.isMobileNO(et_phone.getText().toString().trim())){
                    ToastUtils.showShort(this,"请正确输入推荐人手机号");
                    return;
                }
                Connect.recommend(RegisterOverActivity.this,et_phone.getText().toString().trim(),this);
                break;
        }
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    void showContact() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("contact")
                .commitAllowingStateLoss();
    }

    @OnShowRationale(Manifest.permission.READ_CONTACTS)
    void showRationaleForContact(PermissionRequest request) {
        showRationaleDialog(R.string.permission_contact_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.READ_CONTACTS)
    void onContactNeverAskAgain() {
        Toast toast=Toast.makeText(this,R.string.permission_contact_never_askagain, Toast.LENGTH_LONG);
        showMyToast(toast, 10*1000);
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    //接收返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cursor = managedQuery(contactData, null, null, null,
                        null);
                cursor.moveToFirst();
                String num = this.getContactPhone(cursor).replace(" ", "").replace("-", "");
                if (num.startsWith("+86")){//判断是否包含+86
                    num=num.substring(3,num.length());
                }
                if (num.startsWith("86")){//判断是否包含86
                    num=num.substring(2,num.length());
                }
                if (Validator.isMobileNO(num)){
                    et_phone.setText(num);
                }else {
                    et_phone.setText("");
                    ToastUtils.showLong(RegisterOverActivity.this,"你选择的号码格式不正确，请检查");
                }
            }

        }

    }

    private String getContactPhone(Cursor cursor) {
        int phoneColumn = cursor
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String result = "";
        if (phoneNum > 0) {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人电话的cursor
            Cursor phone = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                            + contactId, null, null);
            if (phone.moveToFirst()) {
                for (; !phone.isAfterLast(); phone.moveToNext()) {
                    int index = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int typeindex = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phone.getInt(typeindex);
                    String phoneNumber = phone.getString(index);
                    result = phoneNumber;
                }
                if (!phone.isClosed()) {
                    phone.close();
                }
            }
        }
        return result;
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        switch (whereRequest) {
            case Connect.SIGN_RECOMMEND:
                if (getCode(result) == 0) {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.SIGN_RECOMMEND:
                break;
            default:
                break;
        }
    }
}
