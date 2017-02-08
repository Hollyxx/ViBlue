package cn.estronger.bike.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.WalletInfoBean;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.widget.MyDialog;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/12.
 */

public class MyWalletActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_detail)
    TextView tv_detail;
    @ViewInject(R.id.tv_recharge_or_refund)
    TextView tv_recharge_or_refund;
    @ViewInject(R.id.tv_balance)
    TextView tv_balance;
    @ViewInject(R.id.tv_yajin)
    TextView tv_yajin;
    @ViewInject(R.id.btn_pay)
    Button btn_pay;
    @ViewInject(R.id.rl_content)
    RelativeLayout rl_content;
    @ViewInject(R.id.rl_empty)
    RelativeLayout rl_empty;
    @ViewInject(R.id.xrv_list)
    XRecyclerView mRecyclerView;
    private int type;
    private String state;
    private MyDialog myDialog;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
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
        iv_back.setOnClickListener(this);
        tv_detail.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        tv_recharge_or_refund.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Connect.getWalletInfo(this,this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_detail://明细
                startActivity(new Intent(this, WalletDetailActivity.class));
                break;
            case R.id.tv_recharge_or_refund://充值or退款
                if (type==1&&"1".equals(state)){
                    myDialog = new MyDialog();
                    myDialog.createBooleanDialog(this, "押金退还时间为2-7个工作日，退款后将无法用车，确认退款？", "确认", this, false);
                    myDialog.show();
                }else {
                    startActivity(new Intent(this, TopUpRechargeActivity.class));//充值
                }
                break;
            case R.id.btn_pay://充值
                startActivity(new Intent(this, WalletRechargeActivity.class));
                break;
            case R.id.sure_btn:
                myDialog.dismiss();
                Connect.cashApply(this,this);
                break;

        }
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.GET_WALLET_INFO:
                if (getCode(result) == 0) {
                    rl_content.setVisibility(View.VISIBLE);
                    WalletInfoBean walletInfo= new Gson().fromJson(result, WalletInfoBean.class);
                    tv_balance.setText(walletInfo.getData().getAvailable_deposit());
                    tv_yajin.setText("押金 "+walletInfo.getData().getDeposit()+" 元");
                    if ("1".equals(walletInfo.getData().getDeposit_state())){
                        tv_recharge_or_refund.setText("押金退款");
                        tv_recharge_or_refund.setTextColor(Color.rgb(0,255,0));
                        type=1;
                    }else {
                        tv_recharge_or_refund.setText("充押金");
                        tv_recharge_or_refund.setTextColor(Color.rgb(255,69,0));
                        type=2;
                    }
                   state= walletInfo.getData().getDeposit_state();
                }
                break;
            case Connect.CASH_APPLY:
                showMsg(result);
                if (getCode(result) == 0) {
                    startActivity(new Intent(this, RefundDepositActivity.class));//退押金
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.GET_WALLET_INFO:
                break;
        }
    }
}
