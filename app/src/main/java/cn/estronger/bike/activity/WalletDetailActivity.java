package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.estronger.bike.R;
import cn.estronger.bike.adapter.MyWalletDetailAdapter;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.WalletDetail;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import static cn.estronger.bike.R.id.iv_back;


/**
 * Created by MrLv on 2016/12/12.
 */

public class WalletDetailActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback{
    @ViewInject(R.id.xrv_list)
    XRecyclerView mRecyclerView;
    @ViewInject(R.id.rl_empty)
    RelativeLayout rl_empty;
    @ViewInject(R.id.rl_view)
    RelativeLayout rl_view;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private MyWalletDetailAdapter mAdapter;
    private ArrayList<WalletDetail.DataBean.ItemsBean> listData;
    private int page=1,total_page,isOnRefresh=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detaill);
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
        rl_view.setVisibility(View.GONE);
        Connect.getWalletDetailPb(this,page+"",this);
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
                Connect.getWalletDetail(WalletDetailActivity.this,"1",WalletDetailActivity.this);
            }

            @Override
            public void onLoadMore() {
                if (total_page-page>0){
                    page++;
                    Connect.getWalletDetail(WalletDetailActivity.this,page+"",WalletDetailActivity.this);
                }else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        });
        listData = new ArrayList<WalletDetail.DataBean.ItemsBean>();
        mAdapter = new MyWalletDetailAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Event(value = {R.id.iv_back,R.id.tv_info})
    private void onEventClick(View v) {
        switch (v.getId()) {
            case iv_back:
                finish();
                break;
            case R.id.tv_info:
                startActivity(new Intent(WalletDetailActivity.this,BaseWebActivity.class).putExtra("title","退款说明"));
                break;
            default:
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
            case Connect.GET_WALLET_DETAIL:
                if (getCode(result) == 0) {
                    rl_view.setVisibility(View.VISIBLE);
                    WalletDetail reditCount= new Gson().fromJson(result, WalletDetail.class);
                    WalletDetail.DataBean data=reditCount.getData();
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
            case Connect.GET_WALLET_DETAIL:
                break;
        }
    }
}
