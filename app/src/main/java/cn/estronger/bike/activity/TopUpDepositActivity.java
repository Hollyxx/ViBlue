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
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
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
 * Created by MrLv on 2016/12/11.
 */

public class TopUpDepositActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.ll_alipay)
    LinearLayout ll_alipay;
    @ViewInject(R.id.ll_wxpay)
    LinearLayout ll_wxpay;
    @ViewInject(R.id.btn_pay)
    Button btn_pay;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private int pay_way = 1;   //1是支付宝   2是微信
    private IWXAPI wxapi;
    private static final int SDK_PAY_FLAG = 1;
    private KProgressHUD hud;

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
                        Toast.makeText(TopUpDepositActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        PrefUtils.setString(TopUpDepositActivity.this, "state", "1");
                        startActivity(new Intent(TopUpDepositActivity.this, CertificationActivity.class));
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(TopUpDepositActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        hud.dismiss();
                    }
                    break;
                }
            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_deposit);
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
        //订阅事件
        EventBus.getDefault().register(this);
        tv_title.setText("押金充值");
        ll_alipay.setSelected(true);
        wxapi = WXAPIFactory.createWXAPI(this, NetConstant.APPID);
    }

    @Event(value = {R.id.iv_back, R.id.ll_alipay, R.id.ll_wxpay, R.id.btn_pay})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
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
            case R.id.btn_pay:
                btn_pay.setEnabled(false);
                Connect.deposit(this, this);
                break;
        }
    }

    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void close(String event) {
        if ("deposit".equals(event)) {
            PrefUtils.setString(this, "state", "1");
            startActivity(new Intent(this, CertificationActivity.class));
            finish();
        }else if ("recharge_fail".equals(event)||"recharge_cancel".equals(event)){
            hud.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        btn_pay.setEnabled(true);
        showMsg(result);
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.DEPOSIT:
                if (getCode(result) == 0) {
                    try {
                        JSONObject data = new JSONObject(result).getJSONObject("data");
                        //判断是微信支付  还是支付宝支付
                        if (pay_way == 1) {  //支付宝
                            Connect.aliPay(TopUpDepositActivity.this, data.getString("pdr_sn"), this);
                        } else {//微信
                            Connect.wxPay(TopUpDepositActivity.this, data.getString("pdr_sn"), this);
                        }
                        hud = Utils.createAutoHud(TopUpDepositActivity.this);
                    } catch (JSONException e) {
                    }
                }
                break;

            case Connect.WX_PAY_CHARGE_DEPOSIT://微信
                if (getCode(result) == 0) {
                    if (!isWXAppInstalledAndSupported()) {
                        Toast.makeText(TopUpDepositActivity.this, "请安装最新版本的微信端", Toast.LENGTH_SHORT).show();
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
                                PayTask alipay = new PayTask(TopUpDepositActivity.this);
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
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        btn_pay.setEnabled(true);
        switch (whereRequest) {
            case Connect.DEPOSIT:
                ToastUtils.showShort(TopUpDepositActivity.this, "获取订单号失败");
                break;
            case Connect.WX_PAY_CHARGE_DEPOSIT:
                ToastUtils.showShort(TopUpDepositActivity.this, errorMsg);
                break;
            case Connect.ALIPAY_CHARGE_DEPOSIT:
                ToastUtils.showShort(TopUpDepositActivity.this, errorMsg);
                break;
        }
    }

    /**
     * 判断是否可用微信
     *
     * @return  true时可用
     */
    private boolean isWXAppInstalledAndSupported() {
        return wxapi.getWXAppSupportAPI() >= com.tencent.mm.sdk.constants.Build.PAY_SUPPORTED_SDK_INT;
    }
}
