package cn.estronger.bike.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.OpenCameraActivity;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PopWindowUtil;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.utils.Validator;
import cn.estronger.bike.widget.MyDialog;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import com.tools.SystemTools;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import static android.R.id.list;

/**
 * Created by MrLv on 2016/12/12.
 */
@RuntimePermissions
public class InformParkActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.et_info)
    EditText et_info;
    @ViewInject(R.id.tv_count)
    TextView tv_count;
    @ViewInject(R.id.tv_code)
    TextView tv_code;
    @ViewInject(R.id.iv_photo)
    ImageView iv_photo;
    @ViewInject(R.id.ll_contanier)
    LinearLayout ll_contanier;
    private PopWindowUtil poUtil;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private String  fault_content = "", bicycle_sn;
    private boolean hasPic = false,hasPerm=true;
    private MyDialog myDialog;
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_park);
        x.view().inject(this);
        InformParkActivityPermissionsDispatcher.showStorageWithCheck(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        tv_title.setText("违停举报");
        et_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                tv_count.setText(et_info.getText().toString().length()+"");
            }
        });
        poUtil = new PopWindowUtil(this);
        poUtil.initPopWindow(this);
        EventBus.getDefault().register(this);
        imageOptions = new ImageOptions.Builder()
                .setUseMemCache(false)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_popupwindows_Photo://从相册获取图片
                SystemTools.getImageUrlFromPhone(this);
                poUtil.hidePopWindow();
                break;
            case R.id.ok_btn:
                myDialog.dismiss();
                finish();
                break;
        }
    }

    @Event(value = {R.id.iv_back, R.id.rl_zxing, R.id.iv_photo,R.id.btn_submit})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_zxing:
                if(Utils.isCameraPermission()){
                    startActivityForResult(new Intent(InformParkActivity.this, ZxingOtherActivity.class), Connect.ZXING_CODE);
                }else {
                    ToastUtils.showShort(this,"没有获得相机权限,请到设置里面打开");
                }
                break;
            case R.id.iv_photo:
                if(Utils.isCameraPermission()){
                    if (hasPerm){
                        poUtil.showPopWindow(ll_contanier);
                    }else {
                        ToastUtils.showShort(this,"没有获得读取内存卡的权限，上传图片功能不可用");
                    }
                }else {
                    ToastUtils.showShort(this,"没有获得相机权限,请到设置里面打开");
                }
                break;
            case R.id.btn_submit:
                bicycle_sn = tv_code.getText().toString();
                fault_content = et_info.getText().toString().trim();
                if ("扫描或输入违停车辆编号".equals(bicycle_sn)) {
                    ToastUtils.showShort(this, "请扫描或输入违停车辆编号");
                    return;
                }
                Connect.addIllegalParking(this, MainActivity.mLat + "", MainActivity.mLng + "", bicycle_sn,fault_content, hasPic ? SystemTools.HEAD_PATH : "","1" ,InformParkActivity.this);
                break;
        }
    }


    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void close(String event) {
        if (event.contains("bikesn")) {
            tv_code.setText(event.replace("bikesn",""));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        InformParkActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showStorage() {
        getSupportFragmentManager().beginTransaction().addToBackStack("storage").commitAllowingStateLoss();
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleForStorage(PermissionRequest request) {
        showRationaleDialog(R.string.permission_storage_rationale, request);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onStorageNeverAskAgain() {
        hasPerm=false;
        Toast toast=Toast.makeText(this,R.string.permission_storage_never_askagain, Toast.LENGTH_LONG);
        showMyToast(toast, 5*1000);
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 处理二维码扫描结果
            case Connect.ZXING_CODE://扫码
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        //这里还要加上二维码的判断    不是所有二维码 都可以往服务器发送
                        if (!Validator.isLockCode(result)) {
                            Toast toast = Toast.makeText(this, "     请扫描车锁二维码     ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return;
                        }
                        tv_code.setText(result);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        ToastUtils.showShort(InformParkActivity.this, "解析二维码失败");
                    }
                }
                break;
            case OpenCameraActivity.TAKE_PICTURE_CODE:
                SystemTools.cropImageUri(this, SystemTools.imageUri, 500, 500,
                        "file.jpg");
                break;
            case SystemTools.FROM_PHOTO_CODE:
                if (data != null) {
                    try {
                        Uri selectedImage = data.getData();
                        SystemTools.cropImageUri(this, selectedImage, 500, 500,
                                "file.jpg");
                    } catch (Exception e) {
                    }
                }
                break;
            case SystemTools.CROP_IMAGE_CODE:
                if (data != null) {
                    try {
                        hasPic = true;
                        x.image().bind(iv_photo, new File(SystemTools.HEAD_PATH).toURI().toString(),imageOptions);
                    } catch (Exception e) {
                    }
                }
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
            case Connect.ADD_ILLEGAL_PARKING:
                if (getCode(result) == 0) {
                    myDialog = new MyDialog();
                    myDialog.showNoticeDialog(InformParkActivity.this, "违停上报成功，感谢你的反馈！", InformParkActivity.this, false);
                    myDialog.show();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.ADD_ILLEGAL_PARKING:
                break;
        }
    }

}
