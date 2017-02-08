package cn.estronger.bike.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

import cn.estronger.bike.R;


/**
 * Created by MrLv on 2016/12/9.
 */

public class OpenLockNumActivity extends BaseActivity {
    @ViewInject(R.id.number_progress_bar)
    NumberProgressBar number_progress_bar;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.tv_state)
    TextView tv_state;
    @ViewInject(R.id.iv_gou)
    ImageView iv_gou;
    private int counter;
    private Timer timer;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lock);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightLinearLayout(view_header);
        } else {
            setHeightLinearLayout1(view_header);
        }
        init();
    }

    private void init() {
        //订阅事件
        EventBus.getDefault().register(this);
        counter = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        number_progress_bar.incrementProgressBy(1);
                        counter++;
                        tv_num.setText(counter + "");
                        if (counter == 100) {
                            counter = 0;
                            timer.cancel();
                            tv_state.setText("开锁成功");
                            tv_num.setText("100");
                            iv_gou.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }, 100, 35);
    }

    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void close(String event) {
        if ("lock_open".equals(event)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //最后别忘了取消定时任务
        if (timer != null) {
            timer.cancel();
        }
        //取消订阅
        EventBus.getDefault().unregister(this);
    }

    //用处就是屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

}
