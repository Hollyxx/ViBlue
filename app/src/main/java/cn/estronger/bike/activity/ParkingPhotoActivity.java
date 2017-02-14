package cn.estronger.bike.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.OpenCameraActivity;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PopWindowUtil;
import cn.estronger.bike.utils.ToastUtil;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.MyDialog;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import com.tools.SystemTools;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import static android.R.id.list;

/**
 * Created by MrLv on 2016/12/12.
 */
@RuntimePermissions
public class ParkingPhotoActivity extends BaseActivity implements View.OnClickListener , MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_bg)
    ImageView iv_bg;
    @ViewInject(R.id.iv_add)
    ImageView iv_add;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_count)
    TextView tv_count;
    @ViewInject(R.id.et_info)
    EditText et_info;
    @ViewInject(R.id.btn_ok)
    Button btn_ok;
    @ViewInject(R.id.ll_contanier)
    LinearLayout ll_contanier;
    private PopWindowUtil poUtil;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private String content = "";
    private boolean hasPic = false,hasPerm=true;
    private MyDialog myDialog;
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_photo);
        x.view().inject(this);
        ParkingPhotoActivityPermissionsDispatcher.showStorageWithCheck(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        tv_title.setText("拍停车照");
        x.image().bind(iv_bg, "assets://ridden_btn_demo.png");
        x.image().bind(iv_add, "assets://ridden_btn_add.png");
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_bg.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        poUtil = new PopWindowUtil(this);
        poUtil.initPopWindow(this);
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
        resetSendMsgRl();
        imageOptions = new ImageOptions.Builder()
                .setUseMemCache(false)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
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
            case R.id.iv_bg:
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
            case R.id.item_popupwindows_Photo://从相册获取图片
                SystemTools.getImageUrlFromPhone(this);
                poUtil.hidePopWindow();
                break;
            case R.id.ok_btn:
                myDialog.dismiss();
                finish();
                break;
            case R.id.btn_ok:
                content=et_info.getText().toString();
               if (hasPic){
                   Connect.addNormalParking(this, MainActivity.mLat + "", MainActivity.mLng + "", getIntent().getStringExtra("bike_sn"), content, hasPic ? SystemTools.HEAD_PATH : "", ParkingPhotoActivity.this);
               }else {
                   ToastUtils.showShort(this,"请拍摄停车照");
                   return;
               }
                break;
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        if (arg1 == RESULT_OK) {
            switch (arg0) {
                case OpenCameraActivity.TAKE_PICTURE_CODE:
                    SystemTools.cropImageUri(this, SystemTools.imageUri, 800, 500,
                            "park.jpg");
                    break;
                case SystemTools.FROM_PHOTO_CODE:
                    if (arg2 != null) {
                        try {
                            Uri selectedImage = arg2.getData();
                            SystemTools.cropImageUri(this, selectedImage, 800, 500,
                                    "park.jpg");
                        } catch (Exception e) {
                        }
                    }
                    break;
                case SystemTools.CROP_IMAGE_CODE:
                    if (arg2 != null) {
                        try {
                            hasPic = true;
                            iv_add.setVisibility(View.INVISIBLE);
                            x.image().bind(iv_bg, new File(SystemTools.HEAD_PATH).toURI().toString(),imageOptions);
                        } catch (Exception e) {
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(arg0, arg1, arg2);
    }

    private void resetSendMsgRl(){

        final ScrollView scrollView=(ScrollView)findViewById(R.id.scrollview);
        final View decorView=getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect=new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                int heightDifference = screenHeight - rect.bottom;//计算软键盘占有的高度  = 屏幕高度 - 视图可见高度
                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) scrollView.getLayoutParams();
                layoutParams.setMargins(0,0,0,heightDifference);//设置rlContext的marginBottom的值为软键盘占有的高度即可
                scrollView.requestLayout();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ParkingPhotoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.ADD_NORMAL_PARKING:
                if (getCode(result) == 0) {
                    myDialog = new MyDialog();
                    myDialog.showNoticeDialog(ParkingPhotoActivity.this, "停车拍照成功，感谢你的上传！", ParkingPhotoActivity.this, false);
                    myDialog.show();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.ADD_NORMAL_PARKING:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ParkingPhotoActivity.this.finish();
        return super.onKeyDown(keyCode, event);
    }
}
