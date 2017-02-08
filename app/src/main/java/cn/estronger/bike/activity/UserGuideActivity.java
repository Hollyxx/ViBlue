package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.Link;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PopWindowUtil;
import cn.estronger.bike.utils.PrefUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by MrLv on 2016/12/12.
 */

public class UserGuideActivity extends BaseActivity implements View.OnClickListener ,MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.rl_lock)
    RelativeLayout rl_lock;
    @ViewInject(R.id.rl_bike_bug)
    RelativeLayout rl_bike_bug;
    @ViewInject(R.id.rl_top_up_info)
    RelativeLayout rl_top_up_info;
    @ViewInject(R.id.rl_recharge_info)
    RelativeLayout rl_recharge_info;
    @ViewInject(R.id.rl_unfind_bike)
    RelativeLayout rl_unfind_bike;
    @ViewInject(R.id.rl_report_prak)
    RelativeLayout rl_report_prak;
    @ViewInject(R.id.rl_more)
    RelativeLayout rl_more;
    private PopWindowUtil poUtil;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);
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
        if ("".equals(PrefUtils.getString(UserGuideActivity.this, "1",""))){
            Connect.index(UserGuideActivity.this, UserGuideActivity.this);
        }
        tv_title.setText("用户指南");
        iv_back.setOnClickListener(this);
        rl_lock.setOnClickListener(this);
        rl_bike_bug.setOnClickListener(this);
        rl_top_up_info.setOnClickListener(this);
        rl_recharge_info.setOnClickListener(this);
        rl_unfind_bike.setOnClickListener(this);
        rl_report_prak.setOnClickListener(this);
        rl_more.setOnClickListener(this);
        poUtil = new PopWindowUtil(this);
        poUtil.initPopWindow(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_lock:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","开不了锁"));
                break;
            case R.id.rl_bike_bug:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","发现车辆故障"));
                break;
            case R.id.rl_top_up_info:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","押金指南"));
                break;
            case R.id.rl_recharge_info:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","充值说明"));
                break;
            case R.id.rl_unfind_bike:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","找不到车"));
                break;
            case R.id.rl_report_prak:
                startActivity(new Intent(UserGuideActivity.this,WebViewActivity.class).putExtra("title","举报违停"));
                break;
            case R.id.rl_more:
                startActivity(new Intent(UserGuideActivity.this,UserGuideMoreActivity.class));
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
            case Connect.INDEX:
                try {
                    JSONObject data = new JSONObject(result);
                    if ("0".equals(data.getString("errorCode"))) {
                        Link link = new Gson().fromJson(result, Link.class);
                        List<Link.DataBean> links=  link.getData();
                        for (int i = 0; i <links.size() ; i++) {
                            PrefUtils.setString(UserGuideActivity.this,links.get(i).getId()+"",links.get(i).getLink());
                        }
                    }
                } catch (JSONException e) {
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.INDEX:
                break;
        }
    }

}
