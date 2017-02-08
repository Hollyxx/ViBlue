package cn.estronger.bike.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 按两次退出程序的工具类
 * @author MrLv
 *
 */
public class DoubleClickExitHelper {  
	    private final Activity mActivity;  
	    private boolean isOnKeyBacking;  
	    private Handler mHandler;  
	    private Toast mBackToast;  
	      
	    public DoubleClickExitHelper(Activity activity) {  
	        mActivity = activity;  
	        mHandler = new Handler(Looper.getMainLooper());  
	    }  
	      
	    /** 
	     * Activity onKeyDown事件 
	     * */  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if(keyCode != KeyEvent.KEYCODE_BACK) {  
	            return false;  
	        }  
	        if(isOnKeyBacking) {  
	            mHandler.removeCallbacks(onBackTimeRunnable);  
	            if(mBackToast != null){  
	                mBackToast.cancel();  
	            }  
	            mActivity.finish();  
	            return true;  
	        } else {  
	            isOnKeyBacking = true;  
	            if(mBackToast == null) {
					mBackToast = Toast.makeText(mActivity, "再按一次退出小强单车", Toast.LENGTH_SHORT);
					mBackToast.setGravity(Gravity.CENTER, 0, 0);
					mBackToast.show();
	            }
	            mBackToast.show();  
				
	            //延迟两秒的时间，把Runable发出去  
	            mHandler.postDelayed(onBackTimeRunnable, 2000);  
	            return true;  
	        }  
	    }  
	      
	    private Runnable onBackTimeRunnable = new Runnable() {  
	          
	        @Override  
	        public void run() {  
	            isOnKeyBacking = false;  
	            if(mBackToast != null){  
	                mBackToast.cancel();  
	            }  
	        }  
	    };  
}

