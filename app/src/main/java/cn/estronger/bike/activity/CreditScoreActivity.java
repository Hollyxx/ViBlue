package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.adapter.CreditAdapter;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.CreditCountBean;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/12.
 */

public class CreditScoreActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_info)
    ImageView iv_info;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.xrv_list)
    XRecyclerView mRecyclerView;
    private int page = 1, isOnRefresh = 1;
    private int total_page;
    private ArrayList<CreditCountBean.DataBean.ItemsBean> listData;
    private CreditAdapter mAdapter;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_score);
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
        iv_info.setOnClickListener(this);
        Connect.getCreditLogPb(this, page + "", this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isOnRefresh = 2;
                Connect.getCreditLog(CreditScoreActivity.this, "1", CreditScoreActivity.this);
            }

            @Override
            public void onLoadMore() {
                if (total_page - page > 0) {
                    page++;
                    Connect.getCreditLog(CreditScoreActivity.this, page + "", CreditScoreActivity.this);
                } else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        });
        listData = new ArrayList<CreditCountBean.DataBean.ItemsBean>();
        mAdapter = new CreditAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_info:
                startActivity(new Intent(CreditScoreActivity.this, WebViewActivity.class).putExtra("title", "什么是小强信用分"));
                break;

        }
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this);
            return;
        }
        switch (whereRequest) {
            case Connect.GET_CREDIT_LOG_PB:
                if (getCode(result) == 0) {
                    CreditCountBean reditCount = new Gson().fromJson(result, CreditCountBean.class);
                    CreditCountBean.DataBean data = reditCount.getData();
                    total_page = data.getTotal_pages();
                    tv_num.setText(data.getCredit_point());
                    if (isOnRefresh == 2) {
                        listData.clear();
                        isOnRefresh = 1;
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
            case Connect.GET_CREDIT_LOG_PB:
                break;
        }
    }
}
