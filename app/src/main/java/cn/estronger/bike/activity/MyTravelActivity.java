package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.estronger.bike.R;
import cn.estronger.bike.adapter.MyTravelAdapter;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.MyTravel;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


/**
 * Created by MrLv on 2016/12/12.
 */

public class MyTravelActivity extends BaseActivity implements View.OnClickListener ,MyHttpUtils.MyHttpCallback{
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.rl_empty)
    RelativeLayout rl_empty;
    @ViewInject(R.id.xrv_list)
    XRecyclerView mRecyclerView;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    private MyTravelAdapter mAdapter;
    private ArrayList<MyTravel.DataBean.ItemsBean> listData;
    private int page=1,total_page,isOnRefresh=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travel);
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
        Connect.getOrdersPb(this,page+"",this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setEmptyView(rl_empty);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                isOnRefresh=2;
                Connect.getOrders(MyTravelActivity.this,"1",MyTravelActivity.this);
            }

            @Override
            public void onLoadMore() {
                if (total_page-page>0){
                    page++;
                    Connect.getOrders(MyTravelActivity.this,page+"",MyTravelActivity.this);
                }else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        });
        listData = new ArrayList<MyTravel.DataBean.ItemsBean>();
        mAdapter = new MyTravelAdapter(listData,listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MyTravelActivity.this,TravelDetailActivity.class).putExtra("order_id",listData.get(view.getId()).getOrder_id()));
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
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
            case Connect.GET_ORDERS:
                if (getCode(result) == 0) {
                    MyTravel reditCount= new Gson().fromJson(result, MyTravel.class);
                    MyTravel.DataBean data=reditCount.getData();
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
            case Connect.GET_ORDERS:
                break;
        }
    }
}
