package cn.estronger.bike.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by MrLv on 2016/12/30.
 */

public class MyCount extends CountDownTimer {
    private TextView text;
    private FinishCallback callback;
    public MyCount(long millisInFuture, long countDownInterval,TextView text,FinishCallback callback) {
        super(millisInFuture, countDownInterval);
        this.text=text;
        this.callback=callback;
    }
    @Override
    public void onFinish() {
        text.setText("00:00");
        callback.onDownTimeFinish("00:00");
    }
    @Override
    public void onTick(long millisUntilFinished) {
        int s=Integer.parseInt(""+millisUntilFinished / 1000);
        s = s%3600;
        int K = s/60;
        String H=K+"";
        if ((K+"").length()==1){
            H="0"+K;
        }
        s = s%60;
        int M = s;
        String N=M+"";
        if ((M+"").length()==1){
            N="0"+M;
        }
        text.setText(H+":"+N);
    }

    public interface FinishCallback {
        void onDownTimeFinish(String result);
    }
}
