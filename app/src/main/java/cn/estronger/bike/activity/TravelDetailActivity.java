package cn.estronger.bike.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.xutils.common.util.MD5;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceOverlay;

import cn.estronger.bike.R;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.TravelDetail;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.constant.NetConstant;
import cn.estronger.bike.utils.JZLocationConverter;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.widget.CircularImage;
import com.google.gson.Gson;
import com.tools.SystemTools;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static cn.estronger.bike.constant.NetConstant.IMG_URL;

/**
 * Created by MrLv on 2016/12/20.
 */

public class TravelDetailActivity extends BaseActivity implements MyHttpUtils.MyHttpCallback, TraceListener {
    @ViewInject(R.id.map)
    MapView mMapView;
    @ViewInject(R.id.rl_container)
    RelativeLayout rl_container;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_length)
    TextView tv_length;
    @ViewInject(R.id.tv_bike_sn)
    TextView tv_bike_sn;
    @ViewInject(R.id.tv_ride_time)
    TextView tv_ride_time;
    @ViewInject(R.id.tv_tan)
    TextView tv_tan;
    @ViewInject(R.id.tv_kll)
    TextView tv_kll;
    @ViewInject(R.id.ci_header)
    CircularImage ci_header;
    private AMap aMap;
    private TraceOverlay overlay;
    private LBSTraceClient mTraceClient;
    private List<LatLng> list = new ArrayList<LatLng>();
    private List<TraceLocation> list1 = new ArrayList<TraceLocation>();
    @ViewInject(R.id.view_header)
    LinearLayout view_header;
    TravelDetail.DataBean datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightRelativeLayout(view_header);
        }
        mMapView.onCreate(savedInstanceState);//实现地图生命周期管理
        initMap();
        init();
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        ShareSDK.initSDK(this);
        Connect.getOrdersDetail(this, getIntent().getStringExtra("order_id"), this);
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
        overlay = new TraceOverlay(aMap);
        mTraceClient = LBSTraceClient.getInstance(this);
    }

    @Event(value = {R.id.iv_back, R.id.iv_share, R.id.btn_help, R.id.btn_share})
    private void onEventClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share://分享
                showShare();
                break;
            case R.id.btn_share://分享
                showShare();
                break;
            case R.id.btn_help://帮助
                startActivity(new Intent(TravelDetailActivity.this, NeedHelpActivity.class).putExtra("time",datas.getOrder_info().getStart_time())
                        .putExtra("bike_sn",datas.getOrder_info().getBicycle_sn()).putExtra("duration",datas.getOrder_info().getDuration())
                        .putExtra("order_amount",datas.getOrder_info().getOrder_amount()));
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

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("小强单车行程分享");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(NetConstant.SHARE+"order_id="+datas.getOrder_info().getOrder_id()+"&user_id="+PrefUtils.getString(
                TravelDetailActivity.this, "user_id", "-1")+"&sign="+MD5.md5(PrefUtils.getString(TravelDetailActivity.this, "user_id", "-1") + SystemTools.getPhoneId()));
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在小强单车完成了一次骑行，快来一起骑行吧");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(NetConstant.IMG_URL);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(NetConstant.SHARE+"order_id="+datas.getOrder_info().getOrder_id()+"&user_id="+PrefUtils.getString(
                TravelDetailActivity.this, "user_id", "-1")+"&sign="+MD5.md5(PrefUtils.getString(TravelDetailActivity.this, "user_id", "-1") + SystemTools.getPhoneId()));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("扫描骑行，低碳生活");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("小强单车");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(NetConstant.SHARE+"order_id="+datas.getOrder_info().getOrder_id()+"&user_id="+PrefUtils.getString(
                TravelDetailActivity.this, "user_id", "-1")+"&sign="+MD5.md5(PrefUtils.getString(TravelDetailActivity.this, "user_id", "-1") + SystemTools.getPhoneId()));
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
            case Connect.GET_ORDER_DETAIL:
                if (getCode(result) == 0) {
                    TravelDetail reditCount = new Gson().fromJson(result, TravelDetail.class);
                    datas= reditCount.getData();
                    TravelDetail.DataBean.OrderInfoBean data = reditCount.getData().getOrder_info();
                    tv_name.setText(reditCount.getData().getUser_info().getNickname());
                    tv_length.setText(data.getDistance() + "");
                    tv_bike_sn.setText(data.getBicycle_sn());
                    tv_ride_time.setText(data.getDuration() + "");
                    tv_tan.setText(data.getEmission() + "");
                    tv_kll.setText(data.getCalorie() + "");
                    x.image().bind(ci_header, "".equals(reditCount.getData().getUser_info().getAvatar()) ? "assets://user_avatar_default.png" : reditCount.getData().getUser_info().getAvatar());//头像
                    List<TravelDetail.DataBean.LocationsBean> listBean = reditCount.getData().getLocations();
                    for (int i = 0; i < listBean.size(); i++) {
                        JZLocationConverter.LatLng latlng = JZLocationConverter.wgs84ToGcj02(new JZLocationConverter.LatLng(Double.parseDouble(listBean.get(i).getLat()), Double.parseDouble(listBean.get(i).getLng())));
                        list.add(i, new LatLng(latlng.getLatitude(), latlng.getLongitude()));
                        list1.add(i, new TraceLocation(latlng.getLatitude(), latlng.getLongitude(), 0, 0, 0));
                    }
                    if (list.size() > 1) {
                        aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ridden_map_ic_start))).position(list.get(0)));
                        aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ridden_map_ic_end))).position(list.get(list.size() - 1)));
                    }
                    mTraceClient.queryProcessedTrace(1, list1,
                            LBSTraceClient.TYPE_AMAP, TravelDetailActivity.this);
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                            (new LatLng(list.get(0).latitude, list.get(0).longitude), 16));
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.GET_ORDER_DETAIL:
                break;
        }
    }

    @Override
    public void onRequestFailed(int i, String s) {

    }

    @Override
    public void onTraceProcessing(int i, int i1, List<LatLng> list) {

    }

    @Override
    public void onFinished(int i, List<LatLng> list2, int i1, int i2) {
        overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_FINISH);
        list2.add(0, list.get(0));
        list2.add(list2.size(), list.get(list.size() - 1));
        overlay.add(list2);
    }
}
