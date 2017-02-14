package cn.estronger.bike.activity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.Utils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by MrLv on 2016/12/26.
 */

public class WebViewActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.wv_web)
    WebView wv_web;
    private Dialog hud;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
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
        tv_title.setText(getIntent().getStringExtra("title"));
        if (wv_web != null) {
            wv_web.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    hud.dismiss();
                }
            });
            switch (getIntent().getStringExtra("title")) {
                case "用户协议":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"1",""));
                    break;
                case "押金说明":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"2",""));
                    break;
                case "充值协议":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"3",""));
                    break;
                case "消费协议":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"4",""));
                    break;
                case "关于我们":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"5",""));
                    break;
                case "开不了锁":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"6",""));
                    break;
                case "发现车辆故障":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"7",""));
                    break;
                case "押金指南":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"8",""));
                    break;
                case "充值说明":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"9",""));
                    break;
                case "找不到车":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"10",""));
                    break;
                case "举报违停":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"11",""));
                    break;
                case "我实名认证无法通过":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"12",""));
                    break;
                case "实名认证时提示身份信息已存在":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"13",""));
                    break;
                case "邀请码是什么":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"14",""));
                    break;
                case "我需要更换手机号":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"15",""));
                    break;
                case "预约能为我保留多久":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"16",""));
                    break;
                case "预约后怎么找到单车":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"17",""));
                    break;
                case "如何关锁":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"18",""));
                    break;
                case "为什么要交押金,押金与车费有什么不同":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"19",""));
                    break;
                case "押金退款已超7个工作日还没到账":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"20",""));
                    break;
                case "假如我骑行费用超过余额,导致余额为负怎么办":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"21",""));
                    break;
                case "使用小强单车怎么收费":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"22",""));
                    break;
                case "在哪还车":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"23",""));
                    break;
                case "服务区域":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"24",""));
                    break;
                case "我住的地方没有公共停车带怎么办":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"25",""));
                    break;
                case "单车限行路段":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"26",""));
                    break;
                case "什么叫保留用车":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"27",""));
                    break;
                case "如何保留用车":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"28",""));
                    break;
                case "保留用车后,如何继续骑行":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"29",""));
                    break;
                case "单车类型介绍":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"30",""));
                    break;
                case "我发现了坏车,怎么通知小强":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"31",""));
                    break;
                case "小强是谁":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"32",""));
                    break;
                case "我想加入小强":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"33",""));
                    break;
                case "做小强单车的初衷是什么":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"34",""));
                    break;
                case "什么是小强信用分":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"35",""));
                    break;
                case "负面记录是什么,如何申诉":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"36",""));
                    break;
                case "信用分太低了会怎么样":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"37",""));
                    break;
                case "退款说明":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"38",""));
                    break;
                case "优惠券使用规则":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"40",""));
                    break;
                case "消息详情":
                    loadUrl(getIntent().getStringExtra("url"));
                    break;
                case "小强单车用户协议":
                    loadUrl(PrefUtils.getString(WebViewActivity.this,"1",""));
                    break;
                case "官方网站":
                    loadUrl("http://bike.estronger.cn/");
                    break;
            }
        }
    }

    public void loadUrl(String url) {
        if (wv_web != null) {
            wv_web.loadUrl(url);
            hud = Utils.createLoadingDialog(this);
            wv_web.reload();
        }
    }

    @Event(value = {R.id.iv_back})
    private void onEventClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
