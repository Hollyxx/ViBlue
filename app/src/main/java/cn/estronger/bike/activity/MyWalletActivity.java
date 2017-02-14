package cn.estronger.bike.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.adapter.MyCouponAdapter;
import cn.estronger.bike.adapter.MyHistroyCouponAdapter;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.Coupon;
import cn.estronger.bike.bean.CouponHistroy;
import cn.estronger.bike.bean.WalletInfoBean;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.widget.MyDialog;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import static cn.estronger.bike.R.id.parent;

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
    @ViewInject(R.id.tv_history_coupon)
    TextView tv_history_coupon;
    @ViewInject(R.id.ll_use_rule)
    LinearLayout ll_use_rule;
    @ViewInject(R.id.xrv_list)
    XRecyclerView mRecyclerView;
    private int type;
    private String state;
    private MyDialog myDialog;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private int page=1,total_page,isOnRefresh=1;
    private MyCouponAdapter mAdapter;
    private ArrayList<Coupon.DataBean.ItemsBean> listData;

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
        tv_history_coupon.setOnClickListener(this);
        ll_use_rule.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        tv_recharge_or_refund.setOnClickListener(this);
        //优惠券列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setEmptyView(rl_empty);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                isOnRefresh=2;
                Connect.getCouponListNoPb(MyWalletActivity.this,"1",MyWalletActivity.this);
            }

            @Override
            public void onLoadMore() {
                if (total_page-page>0){
                    page++;
                    Connect.getCouponListNoPb(MyWalletActivity.this,page+"",MyWalletActivity.this);
                }else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        });
        listData = new ArrayList<Coupon.DataBean.ItemsBean>();
        mAdapter = new MyCouponAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Connect.getWalletInfo(this,this);
        Connect.getCouponListNoPb(this,page+"",this);
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
            case R.id.tv_history_coupon:
                startActivity(new Intent(this, CouponHistoryActivity.class));
                break;
            case R.id.ll_use_rule://使用规则
                startActivity(new Intent(this,WebViewActivity.class).putExtra("title","优惠券使用规则"));
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
            case Connect.GET_COUPON_LIST:
                if (getCode(result) == 0) {
                    Coupon coupon= new Gson().fromJson(result, Coupon.class);
                    Coupon.DataBean data= coupon.getData();
                    total_page=data.getTotal_pages();
                    if (isOnRefresh==2){
                        listData.clear();
                        isOnRefresh=1;
                    }
                    listData.addAll(data.getItems());
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                    mRecyclerView.refreshComplete();
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
