package cn.estronger.bike.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.UserInfoBean;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.CircularImage;

import com.OpenCameraActivity;

import cn.estronger.bike.utils.PopWindowUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import com.google.gson.Gson;
import com.tools.SystemTools;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static android.R.attr.data;
import static cn.estronger.bike.R.id.et_phone;
import static cn.estronger.bike.widget.ChString.To;

/**
 * Created by MrLv on 2016/12/12.
 */
@RuntimePermissions
public class UserInfoActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.rl_ci_header)
    RelativeLayout rl_ci_header;
    @ViewInject(R.id.rl_nick_name)
    RelativeLayout rl_nick_name;
    @ViewInject(R.id.rl_certification)
    RelativeLayout rl_certification;
    @ViewInject(R.id.rl_phone)
    RelativeLayout rl_phone;
    @ViewInject(R.id.ll_contanier)
    LinearLayout ll_contanier;
    @ViewInject(R.id.ci_head_img)
    CircularImage ci_head_img;
    @ViewInject(R.id.tv_nick_name)
    TextView tv_nick_name;
    @ViewInject(R.id.tv_real_name)
    TextView tv_real_name;
    @ViewInject(R.id.tv_verify)
    TextView tv_verify;
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    @ViewInject(R.id.iv_certification)
    ImageView iv_certification;
    private PopWindowUtil poUtil;
    ImageOptions imageOptions;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
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
        tv_title.setText(getResources().getString(R.string.personal_information));
        iv_back.setOnClickListener(this);
        rl_ci_header.setOnClickListener(this);
        rl_nick_name.setOnClickListener(this);
        rl_certification.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        poUtil = new PopWindowUtil(this);
        poUtil.initPopWindow(this);
        imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.user_avatar_default)//加载中默认显示图片
                .setFailureDrawableId(R.mipmap.user_avatar_default)//加载失败后默认显示图片
                .build();
        if (!"".equals(PrefUtils.getString(this, "avatar", ""))) {
            x.image().bind(ci_head_img, PrefUtils.getString(this, "avatar", ""));
        }
        tv_nick_name.setText(PrefUtils.getString(this, "nickname", ""));
        tv_phone.setText(PrefUtils.getString(this, "phone", "").replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        if ("1".equals(PrefUtils.getString(this, "verify_state", ""))) {
            tv_verify.setText(getResources().getText(R.string.Certified));
            tv_real_name.setText(PrefUtils.getString(this, "real_name", ""));
            rl_certification.setClickable(false);
            tv_verify.setTextColor(Color.rgb(105, 105, 105));
            iv_certification.setVisibility(View.INVISIBLE);
        } else {
            tv_verify.setText(getResources().getText(R.string.Not_certified));
            tv_real_name.setText(getResources().getText(R.string.Not_certified));
            tv_verify.setTextColor(Color.rgb(255, 69, 0));
            rl_certification.setClickable(true);
            iv_certification.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Connect.info(this, this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_nick_name://昵称
                intent = new Intent();
                intent.putExtra("name", tv_nick_name.getText().toString());
                intent.setClass(this, ModifiNickNameActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_phone://手机号
                intent = new Intent();
                intent.putExtra("phone", tv_phone.getText().toString());
                intent.setClass(this, ModifiPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_certification://认证界面
                intent = new Intent();
                intent.setClass(this, CertificationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_ci_header://显示获取图片的popwindows
                if (Utils.isCameraPermission()) {
                    poUtil.showPopWindow(ll_contanier);
                } else {
                    UserInfoActivityPermissionsDispatcher.showCameraWithCheck(this);
                }
                break;
            case R.id.item_popupwindows_Photo://从相册获取图片
                SystemTools.getImageUrlFromPhone(this);
                poUtil.hidePopWindow();
                break;
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        poUtil.showPopWindow(ll_contanier);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
       ToastUtils.showShort(this,getResources().getText(R.string.Not_carm_p));
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_head, request);
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
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        if (arg1 == RESULT_OK) {
            switch (arg0) {
                case OpenCameraActivity.TAKE_PICTURE_CODE:
                    SystemTools.cropImageUri(this, SystemTools.imageUri, 500, 500,
                            "head.jpg");
                    break;
                case SystemTools.FROM_PHOTO_CODE:
                    if (arg2 != null) {
                        try {
                            Uri selectedImage = arg2.getData();
                            SystemTools.cropImageUri(this, selectedImage, 500, 500,
                                    "head.jpg");
                        } catch (Exception e) {
                        }
                    }
                    break;
                case SystemTools.CROP_IMAGE_CODE:
                    if (arg2 != null) {
                        try {
                            Connect.updateAvatar(UserInfoActivity.this, SystemTools.HEAD_PATH, UserInfoActivity.this);
                        } catch (Exception e) {
                        }
                    }
                    break;
            }
        }
        super.onActivityResult(arg0, arg1, arg2);
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.INFO:
                if (getCode(result) == 0) {
                    UserInfoBean userInfo = new Gson().fromJson(result, UserInfoBean.class);
                    UserInfoBean.DataBean data = userInfo.getData();
                    x.image().bind(ci_head_img, data.getAvatar());
                    PrefUtils.setString(UserInfoActivity.this, "nickname", data.getNickname());
                    PrefUtils.setString(UserInfoActivity.this, "avatar", data.getAvatar());
                    if ("1".equals(data.getVerify_state())){
                        PrefUtils.setString(UserInfoActivity.this, "real_name", data.getReal_name());
                    }
                    PrefUtils.setString(UserInfoActivity.this, "credit_point", data.getCredit_point());
                    PrefUtils.setString(UserInfoActivity.this, "verify_state", data.getVerify_state());
                    PrefUtils.setString(UserInfoActivity.this, "phone", data.getMobile());
                    if ("1".equals(data.getVerify_state())) {
                        tv_verify.setText(getResources().getText(R.string.Certified));
                        tv_real_name.setText(data.getReal_name());
                        rl_certification.setClickable(false);
                        tv_verify.setTextColor(Color.rgb(105, 105, 105));
                        iv_certification.setVisibility(View.INVISIBLE);
                    } else {
                        tv_verify.setText(getResources().getText(R.string.Not_certified));
                        tv_real_name.setText(getResources().getText(R.string.Not_certified));
                        tv_verify.setTextColor(Color.rgb(255, 69, 0));
                        rl_certification.setClickable(true);
                        iv_certification.setVisibility(View.VISIBLE);
                    }
                    tv_nick_name.setText(data.getNickname());
                    tv_phone.setText(data.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                break;
            case Connect.UPDATE_AVATAR:
                showMsg(result);
                if (getCode(result) == 0) {
                    try {
                        JSONObject object = new JSONObject(result);
                        x.image().bind(ci_head_img, object.getJSONObject("data").getString("avatar"), imageOptions);
                        PrefUtils.setString(UserInfoActivity.this, "avatar", object.getJSONObject("data").getString("avatar"));
                    } catch (JSONException e) {
                    }
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.INFO:
                break;
            case Connect.UPDATE_AVATAR:
                break;
        }
    }
}
