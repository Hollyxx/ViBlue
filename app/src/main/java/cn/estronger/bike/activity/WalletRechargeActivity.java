package cn.estronger.bike.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import cn.estronger.bike.R;
import cn.estronger.bike.alipay.PayResult;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.constant.NetConstant;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.MyDialog;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

/**
 * Created by MrLv on 2016/12/12.
 */

public class WalletRechargeActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback ,View.OnClickListener{
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.ll_10yuan)
    LinearLayout ll_10yuan;
    @ViewInject(R.id.ll_20yuan)
    LinearLayout ll_20yuan;
    @ViewInject(R.id.ll_50yuan)
    LinearLayout ll_50yuan;
    @ViewInject(R.id.ll_100yuan)
    LinearLayout ll_100yuan;
    @ViewInject(R.id.ll_alipay)
    LinearLayout ll_alipay;
    @ViewInject(R.id.ll_wxpay)
    LinearLayout ll_wxpay;
    @ViewInject(R.id.btn_pay)
    Button btn_pay;
    private int pay_way = 1;   //1是支付宝   2是微信
    private String amount = "0.01";   //10元    20元   50元  100元
    private IWXAPI wxapi;
    private static final int SDK_PAY_FLAG = 1;
    private MyDialog myDialog;
    private KProgressHUD hud;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_recharge);
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
        tv_title.setText("钱包充值");
        ll_10yuan.setSelected(true);//默认选择第一个
        ll_alipay.setSelected(true);//默认选择第一个
        wxapi = WXAPIFactory.createWXAPI(this, NetConstant.APPID);
        //订阅事件
        EventBus.getDefault().register(this);
    }

    @Event(value = {R.id.iv_back, R.id.ll_alipay, R.id.ll_wxpay, R.id.btn_pay, R.id.ll_10yuan, R.id.ll_20yuan, R.id.ll_50yuan, R.id.ll_100yuan,
            R.id.tv_agreement})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_10yuan:
                ll_10yuan.setSelected(true);
                ll_20yuan.setSelected(false);
                ll_50yuan.setSelected(false);
                ll_100yuan.setSelected(false);
                amount = "0.01";
                break;
            case R.id.ll_20yuan:
                ll_10yuan.setSelected(false);
                ll_20yuan.setSelected(true);
                ll_50yuan.setSelected(false);
                ll_100yuan.setSelected(false);
                amount = "20";
                break;
            case R.id.ll_50yuan:
                ll_10yuan.setSelected(false);
                ll_20yuan.setSelected(false);
                ll_50yuan.setSelected(true);
                ll_100yuan.setSelected(false);
                amount = "50";
                break;
            case R.id.ll_100yuan:
                ll_10yuan.setSelected(false);
                ll_20yuan.setSelected(false);
                ll_50yuan.setSelected(false);
                ll_100yuan.setSelected(true);
                amount = "100";
                break;
            case R.id.ll_alipay:
                ll_alipay.setSelected(true);
                ll_wxpay.setSelected(false);
                pay_way = 1;
                break;
            case R.id.ll_wxpay:
                ll_alipay.setSelected(false);
                ll_wxpay.setSelected(true);
                pay_way = 2;
                break;
            case R.id.tv_agreement://充值协议
                startActivity(new Intent(WalletRechargeActivity.this,WebViewActivity.class).putExtra("title","充值协议"));
                break;
            case R.id.btn_pay://充值
                btn_pay.setEnabled(false);
                Connect.charging(this, amount, this);
                break;
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(WalletRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        PrefUtils.setString(WalletRechargeActivity.this, "state", "3");
                        myDialog = new MyDialog();
                        myDialog.showNoticeDialog(WalletRechargeActivity.this, "恭喜你，充值成功！", WalletRechargeActivity.this,false);
                        myDialog.show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(WalletRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        hud.dismiss();
                    }
                    break;
                }
            }
        }

        ;
    };

    /**
     * 判断是否可用微信
     *
     * @return
     */
    private boolean isWXAppInstalledAndSupported() {
        return wxapi.getWXAppSupportAPI() >= com.tencent.mm.sdk.constants.Build.PAY_SUPPORTED_SDK_INT;
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this);
            return;
        }
        btn_pay.setEnabled(true);
        switch (whereRequest) {
            case Connect.CHARGING:
                if (getCode(result) == 0) {
                    try {
                        JSONObject data = new JSONObject(result).getJSONObject("data");
                        //判断是微信支付  还是支付宝支付
                        if (pay_way == 1) {  //支付宝
                            Connect.aliPay(WalletRechargeActivity.this, data.getString("pdr_sn"), this);
                        } else {//微信
                            Connect.wxPay(WalletRechargeActivity.this, data.getString("pdr_sn"), this);
                        }
                        hud = Utils.createAutoHud(WalletRechargeActivity.this);
                    } catch (JSONException e) {
                    }
                }
                break;
            case Connect.WX_PAY_CHARGE_DEPOSIT://微信
                if (getCode(result) == 0) {
                    if (!isWXAppInstalledAndSupported()) {
                        Toast.makeText(WalletRechargeActivity.this, "请安装最新版本的微信端", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        JSONObject wxpay = new JSONObject(result).getJSONObject("data");
                        PayReq req = new PayReq();
                        req.appId = wxpay.getString("appid");
                        req.partnerId = wxpay.getString("partnerid");
                        req.prepayId = wxpay.getString("prepayid");
                        req.nonceStr = wxpay.getString("noncestr");
                        req.timeStamp = wxpay.getString("timestamp");
                        req.packageValue = wxpay.getString("package");
                        req.sign = wxpay.getString("sign");
                        req.extData = "app data"; // optional
                        wxapi.sendReq(req);
                    } catch (JSONException e) {
                    }
                }
                break;
            case Connect.ALIPAY_CHARGE_DEPOSIT://支付宝
                if (getCode(result) == 0) {
                    try {
                        JSONObject alipay = new JSONObject(result).getJSONObject("data");
                        final String orderInfo = alipay.getString("statement");
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(WalletRechargeActivity.this);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    } catch (JSONException e) {
                    }
                }
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        btn_pay.setEnabled(true);
        switch (whereRequest) {
            case Connect.CHARGING:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                myDialog.dismiss();
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }
    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void close(String event) {
        if ("recharge_succeed".equals(event)) {
            PrefUtils.setString(WalletRechargeActivity.this, "state", "3");
            myDialog = new MyDialog();
            myDialog.showNoticeDialog(WalletRechargeActivity.this, "恭喜你，充值成功！", WalletRechargeActivity.this,false);
            myDialog.show();
        }else if ("recharge_fail".equals(event)||"recharge_cancel".equals(event)){
            hud.dismiss();
        }
    }
}
