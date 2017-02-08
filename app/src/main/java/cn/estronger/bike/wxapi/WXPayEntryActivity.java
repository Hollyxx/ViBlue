package cn.estronger.bike.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.estronger.bike.constant.NetConstant;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tools.UsualTools;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, NetConstant.APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                UsualTools.showShortToast(this, "支付成功");
                EventBus.getDefault().post("deposit");//用于通知充押金界面的关闭
                EventBus.getDefault().post("recharge_deposit");//用于已实名后的充押金页面的关闭
                EventBus.getDefault().post("recharge_succeed");//用于钱包充值界面
                break;
            case -1:
                UsualTools.showShortToast(this, "支付失败");
                EventBus.getDefault().post("recharge_fail");
                break;
            case -2:
                UsualTools.showShortToast(this, "取消支付");
                EventBus.getDefault().post("recharge_cancel");
                break;
            default:
                break;
        }
        finish();
    }
}