package cn.estronger.bike.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.fragment.NoCameraPermissionFragment;
import cn.estronger.bike.utils.CodeUtilsCopy;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.MyDialog;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static cn.estronger.bike.activity.MainActivity.mLat;
import static cn.estronger.bike.activity.MainActivity.mLng;

/**
 * 定制化显示扫描界面
 */

@RuntimePermissions
public class ZxingActivity extends BaseActivity implements View.OnClickListener {

    public static final int INPUT = 0;
    private CaptureFragment captureFragment;
    private NoCameraPermissionFragment nop;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.ll_open_light)
    LinearLayout ll_open_light;
    @ViewInject(R.id.ll_input_num)
    LinearLayout ll_input_num;
    @ViewInject(R.id.iv_light)
    ImageView iv_light;
    @ViewInject(R.id.tv_light)
    TextView tv_light;
    @ViewInject(R.id.activity_second)
    FrameLayout activity_second;
    private boolean isOpen = false;//手电筒是否打开
    private MyDialog myDialog;
    private String state;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        x.view().inject(this);
        ZxingActivityPermissionsDispatcher.showCameraWithCheck(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        // 为二维码扫描界面设置定制化界面
        captureFragment = new CaptureFragment();
        nop = new NoCameraPermissionFragment();
        x.view().inject(this);
        init();
        if(Utils.isCameraPermission()){
            CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        }else {
            activity_second.setBackgroundColor(Color.parseColor("#333333"));
            CodeUtilsCopy.setFragmentArgs(nop, R.layout.no_camera_permission);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, nop).commit();
        }
        SysApplication.getInstance().addActivity(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("camera")
                .commitAllowingStateLoss();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        Toast toast=Toast.makeText(this,R.string.permission_camera_never_askagain, Toast.LENGTH_LONG);
        showMyToast(toast, 10*1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZxingActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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

    private void init() {
        iv_back.setOnClickListener(this);
        ll_open_light.setOnClickListener(this);
        ll_input_num.setOnClickListener(this);
        //判断需不需要进入指导页面
        if (!PrefUtils.getBooleanWithName(this, "first", "is_intro_showed_zxing", false)) {
            startActivity(new Intent(ZxingActivity.this, IntroActivity.class).putExtra("from", "z"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        state = PrefUtils.getString(this, "state", "-1");
        if ("4".equals(state) && myDialog == null) {//押金又退掉的情况
            myDialog = new MyDialog();
            myDialog.createBooleanDialogWithCancel(this, "你还没有充值押金，请充值押金后再尝试开锁吧", "充押金", this, false, listener);
            myDialog.show();
        } else if ("3".equals(state)) {
            captureFragment.setAnalyzeCallback(analyzeCallback);//设置回调
        } else if ("2".equals(state) && myDialog == null) {
            myDialog = new MyDialog();
            myDialog.createBooleanDialogWithCancel(this, "你的钱包余额不足，请充值后再尝试开锁吧", "充值", this, false, listener);
            myDialog.show();
        }
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingActivity.this.finish();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.sure_btn://充值或者认证
                myDialog.dismiss();
                myDialog = null;
                if ("2".equals(state)) {
                    startActivity(new Intent().setClass(this, WalletRechargeActivity.class));
                } else if ("4".equals(state)) {
                    startActivity(new Intent().setClass(this, TopUpRechargeActivity.class));
                }
                break;
            case R.id.cancel_btn://不充值就关闭本页面
                myDialog.dismiss();
                finish();
                break;
            case R.id.ll_open_light://打开手电筒
                if (!isOpen) {
                    CodeUtils.isLightEnable(true);
                    iv_light.setBackgroundResource(R.mipmap.light_on);
                    isOpen = true;
                    tv_light.setText("关闭手电筒");
                } else {
                    CodeUtils.isLightEnable(false);
                    iv_light.setBackgroundResource(R.drawable.light_ic_seletor);
                    isOpen = false;
                    tv_light.setText("打开手电筒");
                }
                break;
            case R.id.ll_input_num://手动输入开锁
                isOpen = false;//防止灯的状态不对
                startActivityForResult(new Intent().setClass(this, InputBikeNumActivity.class), INPUT);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INPUT:
                if (resultCode == RESULT_OK) {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        iv_light.setBackgroundResource(R.drawable.light_ic_seletor);
        tv_light.setText("打开手电筒");
    }

    private DialogInterface.OnKeyListener listener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                myDialog.dismiss();
                ZxingActivity.this.finish();
            }
            return false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ZxingActivity.this.finish();
        return super.onKeyDown(keyCode, event);
    }
}
