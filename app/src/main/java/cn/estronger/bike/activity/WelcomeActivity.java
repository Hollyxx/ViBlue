package cn.estronger.bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.COrder;
import cn.estronger.bike.bean.Link;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.MyDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by MrLv on 2016/12/5.
 */

public class WelcomeActivity extends Activity implements MyHttpUtils.MyHttpCallback ,View.OnClickListener{

    @ViewInject(R.id.iv_splash)
    ImageView iv_splash;
    private boolean hasorder = false;
    private COrder cOrder;
    private long time,time1;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        time=System.currentTimeMillis();
        setContentView(R.layout.activity_welcome);
        x.view().inject(this);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //显示图片
        x.image().bind(iv_splash, "assets://splash.png");
        if (!Utils.isNetworkAvailable(WelcomeActivity.this)){//判断当前网络是否可用
            myDialog = new MyDialog();
            myDialog.showNoticeDialog(WelcomeActivity.this, "当前网络不可用，请检查手机网络！", WelcomeActivity.this,false);
            myDialog.show();
        }else {
            if (!"-1".equals(PrefUtils.getString(this, "user_id", "-1"))) {
                Connect.current(this, this);
            } else {
                startAnim(2000);
            }
        }
        if ("".equals(PrefUtils.getString(WelcomeActivity.this, "1",""))){
            Connect.index(WelcomeActivity.this, WelcomeActivity.this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                myDialog.dismiss();
                startAnim(1);
                break;
        }
    }

    /**
     * 开启动画
     */
    private void startAnim(long d) {
        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(1f, 1);
        alpha.setDuration(d);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态

        // 设置动画监听
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        iv_splash.startAnimation(alpha);
    }

    /**
     * 跳转下一个页面
     */
    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
//        boolean userGuide = PrefUtils.getBooleanWithName(this, "first", "is_user_guide_showed", false);
//        if (!userGuide) {
//            // 跳转到新手引导页
//            startActivity(new Intent(this, GuideActivity.class));
//        } else {
            if (hasorder) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("order", new Gson().toJson(cOrder));
                startActivity(intent);
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
//        }
        finish();
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        switch (whereRequest) {
            case Connect.CURRENT:
                time1=System.currentTimeMillis();
                try {
                    JSONObject data = new JSONObject(result);
                    if ("0".equals(data.getString("errorCode"))) {
                        if (data.getJSONObject("data").getBoolean("has_order")) {
                            hasorder = true;
                            cOrder = new Gson().fromJson(result, COrder.class);
                        } else {
                            hasorder = false;
                        }
                    }
                } catch (JSONException e) {
                }
                if ((time1-time)<2000){
                    startAnim(2000-(time1-time));
                }else {
                    startAnim(1);
                }
                break;
            case Connect.INDEX:
                try {
                    JSONObject data = new JSONObject(result);
                    if ("0".equals(data.getString("errorCode"))) {
                        Link link = new Gson().fromJson(result, Link.class);
                      List<Link.DataBean> links=  link.getData();
                        for (int i = 0; i <links.size() ; i++) {
                            PrefUtils.setString(WelcomeActivity.this,links.get(i).getId()+"",links.get(i).getLink());
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
            case Connect.CURRENT:
                myDialog = new MyDialog();
                myDialog.showNoticeDialog(WelcomeActivity.this, "当前网络差，请检查手机网络！", WelcomeActivity.this,false);
                myDialog.show();
                break;
            case Connect.INDEX:
                break;
        }
    }
}
