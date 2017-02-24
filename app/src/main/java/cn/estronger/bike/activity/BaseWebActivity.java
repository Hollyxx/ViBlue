package cn.estronger.bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.widget.ProgressWebView;

import static cn.estronger.bike.R.id.iv_back;
import static cn.estronger.bike.R.id.wv_web;

/**
 * Created by MrLv on 2017/2/16.
 */

public class BaseWebActivity extends BaseActivity {

    // private View mLoadingView;
    protected ProgressWebView mWebView;
    private ProgressBar web_progressbar;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseweb);
        x.view().inject(this);
        // mLoadingView = findViewById(R.id.baseweb_loading_indicator);
        mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        initData();
    }

    @Event(value = {R.id.iv_back,R.id.tv_info})
    private void onEventClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void initData() {
        SysApplication.getInstance().addActivity(this);
        tv_title.setText(getIntent().getStringExtra("title"));
        if (mWebView != null) {
            switch (getIntent().getStringExtra("title")) {
                case "用户协议":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"1",""));
                    break;
                case "押金说明":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"2",""));
                    break;
                case "充值协议":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"3",""));
                    break;
                case "消费协议":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"4",""));
                    break;
                case "关于我们":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"5",""));
                    break;
                case "开不了锁":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"6",""));
                    break;
                case "发现车辆故障":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"7",""));
                    break;
                case "押金指南":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"8",""));
                    break;
                case "充值说明":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"9",""));
                    break;
                case "找不到车":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"10",""));
                    break;
                case "举报违停":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"11",""));
                    break;
                case "我实名认证无法通过":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"12",""));
                    break;
                case "实名认证时提示身份信息已存在":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"13",""));
                    break;
                case "邀请码是什么":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"14",""));
                    break;
                case "我需要更换手机号":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"15",""));
                    break;
                case "预约能为我保留多久":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"16",""));
                    break;
                case "预约后怎么找到单车":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"17",""));
                    break;
                case "如何关锁":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"18",""));
                    break;
                case "为什么要交押金,押金与车费有什么不同":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"19",""));
                    break;
                case "押金退款已超7个工作日还没到账":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"20",""));
                    break;
                case "假如我骑行费用超过余额,导致余额为负怎么办":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"21",""));
                    break;
                case "使用小强单车怎么收费":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"22",""));
                    break;
                case "在哪还车":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"23",""));
                    break;
                case "服务区域":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"24",""));
                    break;
                case "我住的地方没有公共停车带怎么办":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"25",""));
                    break;
                case "单车限行路段":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"26",""));
                    break;
                case "什么叫保留用车":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"27",""));
                    break;
                case "如何保留用车":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"28",""));
                    break;
                case "保留用车后,如何继续骑行":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"29",""));
                    break;
                case "单车类型介绍":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"30",""));
                    break;
                case "我发现了坏车,怎么通知小强":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"31",""));
                    break;
                case "小强是谁":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"32",""));
                    break;
                case "我想加入小强":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"33",""));
                    break;
                case "做小强单车的初衷是什么":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"34",""));
                    break;
                case "什么是小强信用分":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"35",""));
                    break;
                case "负面记录是什么,如何申诉":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"36",""));
                    break;
                case "信用分太低了会怎么样":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"37",""));
                    break;
                case "退款说明":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"38",""));
                    break;
                case "优惠券使用规则":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"40",""));
                    break;
                case "消息详情":
                    loadUrl(getIntent().getStringExtra("url"));
                    break;
                case "小强单车用户协议":
                    loadUrl(PrefUtils.getString(BaseWebActivity.this,"1",""));
                    break;
                case "官方网站":
                    loadUrl("http://bike.estronger.cn/");
                    break;
            }
        }
    }

    public void loadUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

}
