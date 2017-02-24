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

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 定制化显示扫描界面
 */
@RuntimePermissions
public class ZxingOtherActivity extends BaseActivity implements View.OnClickListener {

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
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_other);
        x.view().inject(this);
        ZxingOtherActivityPermissionsDispatcher.showCameraWithCheck(this);
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
        if(isCameraPermission()){
            CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
            captureFragment.setAnalyzeCallback(analyzeCallback);//设置回调
        }else {
            activity_second.setBackgroundColor(Color.parseColor("#333333"));
            CodeUtilsCopy.setFragmentArgs(nop, R.layout.no_camera_permission);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, nop).commit();
        }
        SysApplication.getInstance().addActivity(this);
    }

    private void init() {
        iv_back.setOnClickListener(this);
        ll_open_light.setOnClickListener(this);
        ll_input_num.setOnClickListener(this);
        EventBus.getDefault().register(this);
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
        ZxingOtherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(getResources().getText(R.string.allow), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(getResources().getText(R.string.refuse), new DialogInterface.OnClickListener() {
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
    protected void onResume() {
        super.onResume();
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
            ZxingOtherActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingOtherActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ZxingOtherActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingOtherActivity.this.finish();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
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
                startActivityForResult(new Intent().setClass(this, InputBikeNumOtherActivity.class), INPUT);
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

    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void close(String event) {
        if (event.contains("bikesn")) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        iv_light.setBackgroundResource(R.drawable.light_ic_seletor);
        tv_light.setText("打开手电筒");
    }

    /**
     * 判断相机权限是否开启
     * @return
     */
    public boolean isCameraPermission() {
        try {
            Camera.open().release();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ZxingOtherActivity.this.finish();
        return super.onKeyDown(keyCode, event);
    }
}
