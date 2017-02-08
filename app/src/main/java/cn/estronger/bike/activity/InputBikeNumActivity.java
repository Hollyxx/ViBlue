package cn.estronger.bike.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import com.jungly.gridpasswordview.GridPasswordView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by MrLv on 2016/12/9.
 */

public class InputBikeNumActivity extends BaseActivity implements View.OnClickListener,MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_open_light)
    ImageView iv_open_light;
    @ViewInject(R.id.gev_edittext)
    GridPasswordView gev_edittext;
    @ViewInject(R.id.btn_ok)
    Button btn_ok;
    private boolean isOpen = false;//手电筒是否打开
    private Camera camera;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bike_num);
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
        gev_edittext.togglePasswordVisibility();
        iv_back.setOnClickListener(this);
        iv_open_light.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (isOpen) {
                    close();
                    iv_open_light.setBackgroundResource(R.drawable.light_ic_seletor);
                }
                finish();
                break;
            case R.id.iv_open_light://打开手电筒
                if (!isOpen) {
                    open();
                    iv_open_light.setBackgroundResource(R.mipmap.light_on);
                    isOpen = true;
                } else {
                    close();
                    iv_open_light.setBackgroundResource(R.drawable.light_ic_seletor);
                    isOpen = false;
                }
                break;
            case R.id.btn_ok://确认
                if (gev_edittext.getPassWord().length() != 6) {
                    Toast.makeText(this, "请完整输入编号", Toast.LENGTH_SHORT).show();
                    return;
                }
                Connect.openLock(this, gev_edittext.getPassWord(),MainActivity.mLat+"",MainActivity.mLng+"", this);//开锁
                break;
            default:
                break;
        }
    }

    /**
     * 开启闪光灯
     */
    private void open() {
        try {
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     *
     * @return
     */
    private void close() {
        try {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.release();
            camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //这个方法里面也要关闭灯光
        if (isOpen) {
            close();//作用  按返回键的时候关闭闪光灯
            iv_open_light.setBackgroundResource(R.drawable.light_ic_seletor);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isOpen) {
                close();//作用  按返回键的时候关闭闪光灯
                iv_open_light.setBackgroundResource(R.drawable.light_ic_seletor);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        showMsg(result);
        if (getCode(result) == 99) {
            exitLogin(this);
            return;
        }
        switch (whereRequest) {
            case Connect.OPEN_LOCK:
                if (getCode(result)==0){
                    startActivity(new Intent().setClass(this, OpenLockNumActivity.class));
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.OPEN_LOCK:
                break;
        }
    }
}
