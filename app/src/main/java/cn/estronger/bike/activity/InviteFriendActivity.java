package cn.estronger.bike.activity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.constant.NetConstant;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import static cn.estronger.bike.constant.NetConstant.INVITE;

/**
 * Created by MrLv on 2016/12/12.
 */

public class InviteFriendActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_bg)
    ImageView iv_bg;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    Dialog hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        //判断有没有存过  分享需要加密的内容
        if ("null".equals(PrefUtils.getString(this, "encryptCode", "null"))) {
            Connect.getEncryptCode(this, this);
        }
        SysApplication.getInstance().addActivity(this);
        tv_title.setText(getResources().getText(R.string.invite_friends));
        x.image().bind(iv_bg, "assets://user_share_bg.jpg");
        ShareSDK.initSDK(this);
        hud = Utils.createLoadingDialog(this);
    }

    @Event(value = {R.id.iv_back, R.id.iv_qq, R.id.iv_qzone, R.id.iv_sina, R.id.iv_wechat, R.id.iv_moment})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_qq:
                hud.show();
                QQ.ShareParams spQQ = new QQ.ShareParams();
                spQQ.setTitle(getResources().getText(R.string.app_name).toString());
                spQQ.setTitleUrl(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null")); // 标题的超链接
                spQQ.setText(getResources().getText(R.string.InviteComment).toString());
                spQQ.setImageUrl(NetConstant.IMG_URL);
                spQQ.setSite(getResources().getText(R.string.app_name).toString());
                spQQ.setSiteUrl(NetConstant.INVITE+PrefUtils.getString(this, "encryptCode", "null"));
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                qq.setPlatformActionListener(paListener);
                // 执行图文分享
                qq.share(spQQ);
                break;
            case R.id.iv_qzone:
                hud.show();
                QZone.ShareParams sp = new QZone.ShareParams();
                sp.setTitle(getResources().getText(R.string.app_name).toString());
                sp.setTitleUrl(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null")); // 标题的超链接
                sp.setText(getResources().getText(R.string.InviteComment).toString());
                sp.setImageUrl(NetConstant.IMG_URL);
                sp.setSite(getResources().getText(R.string.app_name).toString());
                sp.setSiteUrl(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null"));
                Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                qzone.setPlatformActionListener(paListener);
                // 执行图文分享
                qzone.share(sp);
                break;
            case R.id.iv_sina:
                hud.show();
                SinaWeibo.ShareParams spSina = new SinaWeibo.ShareParams();
                spSina.setText(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null"));
                spSina.setImageUrl(NetConstant.IMG_URL);
                Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener); // 设置分享事件回调
                // 执行图文分享
                weibo.share(spSina);
                break;
            case R.id.iv_wechat:
//                hud = Utils.createAutoHud(this);
                hud = Utils.createLoadingDialog(this);
                Wechat.ShareParams spWx = new Wechat.ShareParams();
                spWx.setShareType(Platform.SHARE_WEBPAGE);
                spWx.setTitle(getResources().getText(R.string.app_name).toString());
                spWx.setText(getResources().getText(R.string.InviteText).toString());
                spWx.setImageUrl(NetConstant.IMG_URL);
                spWx.setUrl(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null"));
                Platform wx = ShareSDK.getPlatform(Wechat.NAME);
                wx.setPlatformActionListener(paListener); // 设置分享事件回调
                // 执行图文分享
                wx.share(spWx);
                break;
            case R.id.iv_moment:
                hud.show();
                WechatMoments.ShareParams spWxM = new WechatMoments.ShareParams();
                spWxM.setShareType(Platform.SHARE_WEBPAGE);
                spWxM.setTitle(getResources().getText(R.string.app_name).toString());
                spWxM.setText(getResources().getText(R.string.InviteText).toString());
                spWxM.setImageUrl(NetConstant.IMG_URL);
                spWxM.setUrl(NetConstant.INVITE +PrefUtils.getString(this, "encryptCode", "null"));
                Platform wxM = ShareSDK.getPlatform(WechatMoments.NAME);
                wxM.setPlatformActionListener(paListener); // 设置分享事件回调
                // 执行图文分享
                wxM.share(spWxM);
                break;
        }
    }

    PlatformActionListener paListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {
                        hud.dismiss();
                    }
                    //分享成功的回调
                    ToastUtils.showShort(InviteFriendActivity.this, "分享成功");
                }
            });
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {
                        hud.dismiss();
                    }
                    //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                    ToastUtils.showShort(InviteFriendActivity.this, "分享失败");
                }
            });
        }

        @Override
        public void onCancel(Platform platform, int i) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (hud != null && hud.isShowing()) {
                        hud.dismiss();
                    }
                    //取消分享的回调
                    ToastUtils.showShort(InviteFriendActivity.this, "分享取消");
                }
            });
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
        }
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        switch (whereRequest) {
            case Connect.GET_ENCRYPT_CODE:
                if (getCode(result) == 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject data = jsonObject.getJSONObject("data");
                        PrefUtils.setString(InviteFriendActivity.this, "encryptCode", data.getString("encrypt_code"));
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.GET_ENCRYPT_CODE:
                break;
        }
    }
}
