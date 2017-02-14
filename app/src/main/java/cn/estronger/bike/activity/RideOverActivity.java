package cn.estronger.bike.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.trace.TraceOverlay;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.CurrentOrder;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.constant.NetConstant;
import cn.estronger.bike.utils.JZLocationConverter;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PopWindowSrevice;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.Utils;
import com.google.gson.Gson;
import com.tools.SystemTools;
import com.tools.ViewTools;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.xutils.common.util.MD5;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by MrLv on 2016/12/20.
 */

public class RideOverActivity extends BaseActivity implements View.OnClickListener, MyHttpUtils.MyHttpCallback {
    @ViewInject(R.id.map)
    MapView mMapView;
    @ViewInject(R.id.rl_container)
    RelativeLayout rl_container;
    @ViewInject(R.id.tv_coupon_text)
    TextView tv_coupon_text;
    @ViewInject(R.id.tv_coupon)
    TextView tv_coupon;
    private AMap aMap;
    private PopWindowSrevice popSrevice;
    private TraceOverlay overlay;
    private List<LatLng> list=new ArrayList<LatLng>();
    private CurrentOrder.DataBean data;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_over);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightRelativeLayout(view_header);
        }
        mMapView.onCreate(savedInstanceState);//实现地图生命周期管理
        init();
        initMap();
    }

    private void init() {
        //判断有没有存过  分享需要加密的内容
        if ("null".equals(PrefUtils.getString(this, "encryptCode", "null"))) {
            Connect.getEncryptCode(this, this);
        }
        SysApplication.getInstance().addActivity(this);
        popSrevice = new PopWindowSrevice(this);
        popSrevice.initPopWindow(this);
        ShareSDK.initSDK(this);
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            Utils.setMapCustomStyleFile(this, aMap);
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            if (!"0".equals(PrefUtils.getString(this, "mLat", "0")) && !"0".equals(PrefUtils.getString(this, "mLng", "0"))) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                        (new LatLng(Double.parseDouble(PrefUtils.getString(this, "mLat", "0")),
                                Double.parseDouble(PrefUtils.getString(this, "mLng", "0"))), 10));//进来的时候，地图的默认中心点为上一次的最后退出位置
            }
        }
        UiSettings settings = aMap.getUiSettings();
        settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);
        settings.setZoomControlsEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        aMap.setMapCustomEnable(true);//设置自定义的地图颜色
        Connect.currentOrderPb(RideOverActivity.this, getIntent().getStringExtra("device_id"), getIntent().getStringExtra("order_sn"), RideOverActivity.this);
        overlay=new TraceOverlay(aMap);
    }

    @Event(value = {R.id.rl_detail, R.id.iv_back, R.id.tv_customer_service, R.id.iv_photo, R.id.iv_share, R.id.iv_keep})
    private void onEventClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_detail:
                ViewTools.setVisible(RideOverActivity.this, R.id.ll_detail);
                ViewTools.setGone(RideOverActivity.this, R.id.ll_pay);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_customer_service:
                popSrevice.showPopWindow(rl_container);
                break;
            case R.id.iv_photo://停车拍照
                intent = new Intent(RideOverActivity.this, ParkingPhotoActivity.class);
                intent.putExtra("bike_sn",data.getBicycle_sn());
                startActivity(intent);
                break;
            case R.id.iv_share://分享
                showShare();
                break;
            case R.id.iv_keep://保留用车
                EventBus.getDefault().post("keepUseBike"+data.getLock_sn());
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        popSrevice.hidePopWindow();
        switch (view.getId()) {
            case R.id.btn_report_illegally_park://违停举报
                intent = new Intent(RideOverActivity.this, InformParkActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_find_bug://发现故障
                intent = new Intent(RideOverActivity.this, FindBikeFaultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_help://使用帮助
                intent = new Intent(RideOverActivity.this, UserGuideActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("小强单车行程分享");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(NetConstant.SHARE_NOW+"order_id="+data.getOrder_id()+"&encryptCode="+PrefUtils.getString(this, "encryptCode", "null"));
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在小强单车完成了一次骑行，快来一起骑行吧");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(NetConstant.IMG_URL);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(NetConstant.SHARE_NOW+"order_id="+data.getOrder_id()+"&encryptCode="+PrefUtils.getString(this, "encryptCode", "null"));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("邀请您加入小强单车，共享绿色生活");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("小强单车");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(NetConstant.SHARE_NOW+"order_id="+data.getOrder_id()+"&encryptCode="+PrefUtils.getString(this, "encryptCode", "null"));
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.GET_ORDER_INFO:
                if (getCode(result) == 0) {
                    CurrentOrder currentOrder = new Gson().fromJson(result, CurrentOrder.class);
                    data=currentOrder.getData();
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_cur_price, data.getOrder_amount()+"");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_time, data.getTime().getHours()+"小时"
                            +data.getTime().getMin()+"分钟");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_wallet, data.getAvailable_deposit()+"元");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_length, data.getDistance()+"");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_bike_sn, data.getBicycle_sn());
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_min, data.getTime().getHours()!=0?(data.getTime().getHours()*60+data.getTime().getMin()+""):data.getTime().getMin()+"");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_tan, data.getEmission()+"");
                    ViewTools.setStringToTextView(RideOverActivity.this, R.id.tv_cll, data.getCalorie()+"");
                    tv_coupon_text.setTextColor(getResources().getColor(R.color.gray));
                    tv_coupon.setTextColor(getResources().getColor(R.color.gray));
                    tv_coupon.setText("无");
                    List<CurrentOrder.DataBean.LineDataBean> listBean=data.getLine_data();
                    for (int i = 0; i <listBean.size() ; i++) {
                        JZLocationConverter.LatLng latlng=JZLocationConverter.wgs84ToGcj02(new JZLocationConverter.LatLng(Double.parseDouble(listBean.get(i).getLat()),Double.parseDouble(listBean.get(i).getLng())));
                        list.add(i,new LatLng(latlng.getLatitude(),latlng.getLongitude()));
                    }
                    if (list.size()>1){
                        aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ridden_map_ic_start))).position(list.get(0)));
                        aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ridden_map_ic_end))).position(list.get(list.size()-1)));
                    }
                    overlay.add(list);
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                            (new LatLng(list.get(0).latitude,list.get(0).longitude), 16));
                }
                break;
            case Connect.GET_ENCRYPT_CODE:
                if (getCode(result) == 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject data = jsonObject.getJSONObject("data");
                        PrefUtils.setString(RideOverActivity.this, "encryptCode", data.getString("encrypt_code"));
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.GET_ORDER_INFO:
                break;
            case Connect.GET_ENCRYPT_CODE:
                break;
        }
    }
}
