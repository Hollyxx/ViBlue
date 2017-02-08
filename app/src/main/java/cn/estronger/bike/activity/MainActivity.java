package cn.estronger.bike.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tools.ViewTools;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import cn.estronger.bike.R;
import cn.estronger.bike.application.MyApp;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.BikeOrder;
import cn.estronger.bike.bean.COrder;
import cn.estronger.bike.bean.CurrentOrder;
import cn.estronger.bike.bean.LockPosition;
import cn.estronger.bike.bean.MarkLength;
import cn.estronger.bike.bean.MarkerBean;
import cn.estronger.bike.bean.SearchHistorysBean;
import cn.estronger.bike.bean.UserInfoBean;
import cn.estronger.bike.connect.Connect;
import cn.estronger.bike.utils.CoordinateUtil;
import cn.estronger.bike.utils.DoubleClickExitHelper;
import cn.estronger.bike.utils.JZLocationConverter;
import cn.estronger.bike.utils.MyCount;
import cn.estronger.bike.utils.MyHttpUtils;
import cn.estronger.bike.utils.MyReceiver;
import cn.estronger.bike.utils.PopWindowSrevice;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.utils.ToastUtils;
import cn.estronger.bike.utils.Utils;
import cn.estronger.bike.utils.Validator;
import cn.estronger.bike.widget.AMapUtil;
import cn.estronger.bike.widget.CircularImage;
import cn.estronger.bike.widget.MyDialog;
import cn.estronger.bike.widget.SensorEventHelper;
import cn.estronger.bike.widget.WalkRouteOverlay;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static cn.estronger.bike.connect.Connect.REQUEST_PERMISSION_SETTING;
import static cn.estronger.bike.connect.Connect.SEACH;
import static cn.estronger.bike.connect.Connect.ZXING_CODE;

@RuntimePermissions
public class MainActivity extends BaseActivity implements LocationSource, View.OnClickListener, AMapLocationListener, MyHttpUtils.MyHttpCallback, AMap.OnCameraChangeListener, AMap.OnMarkerClickListener, RouteSearch.OnRouteSearchListener, AMap.OnMapClickListener, MyCount.FinishCallback, AMap.InfoWindowAdapter {
    @ViewInject(R.id.rl_all)
    RelativeLayout rl_all;
    @ViewInject(R.id.rl_single)
    RelativeLayout rl_single;
    @ViewInject(R.id.rl_double)
    RelativeLayout rl_double;
    @ViewInject(R.id.rl_family)
    RelativeLayout rl_family;
    @ViewInject(R.id.iv_home_head)
    ImageView iv_home_head;
    @ViewInject(R.id.iv_home_seach)
    ImageView iv_home_seach;
    @ViewInject(R.id.index_drawerlayout)
    DrawerLayout mDrawerLayout;
    @ViewInject(R.id.ci_head_img)
    CircularImage ci_head_img;
    @ViewInject(R.id.map)
    MapView mMapView;
    @ViewInject(R.id.ib_zxing)
    ImageButton ib_zxing;
    @ViewInject(R.id.ib_service)
    ImageButton ib_service;
    @ViewInject(R.id.btn_logion_or_integral)
    Button btn_logion_or_integral;
    @ViewInject(R.id.index_drawerlayout)
    DrawerLayout index_drawerlayout;
    @ViewInject(R.id.iv_pb)
    ImageView iv_pb;
    @ViewInject(R.id.tv_count_down_time)
    TextView tv_count_down_time;
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    private AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private PopWindowSrevice popSrevice;
    private boolean isFirstLoc = false, registed = false;
    private SensorEventHelper mSensorHelper;
    private Marker mLocMarker, marker123;
    public static double mLat, mLng, clat, clng, blat, blng;//写出public  是为了其他类里面可以直接使用
    private float zoom;
    private int type, mid, status = 1;//status 1是初始状态  2点了marker还没有预约   3已预约   4正在骑行中
    private String addr, bicycle_sn, order_sn, device_id, scenic_spot_name, user_id, state, markerId;//
    private MessageReceiver mMessageReceiver;
    private MyDialog myDialog;
    private List<RelativeLayout> rlList;
    private Animation mRefreshAnim;
    private RouteSearch routeSearch;
    private WalkRouteResult mWalkRouteResult;
    private WalkRouteOverlay walkRouteOverlay;
    private KProgressHUD hud;
    private Timer timer;
    private TimerTask task;
    private Handler handler;
    private MyCount myCount;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        MainActivityPermissionsDispatcher.showLocationWithCheck(this);
        MainActivityPermissionsDispatcher.showSensorWithCheck(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightRelativeLayout(view_header);
        }
        mMapView.onCreate(savedInstanceState);//实现地图生命周期管理
        initMap();
        init();
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            Utils.setMapCustomStyleFile(this, aMap);
            aMap.setLocationSource(this);// 设置定位监听
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            if (!"0".equals(PrefUtils.getString(this, "mLat", "0")) && !"0".equals(PrefUtils.getString(this, "mLng", "0"))) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                        (new LatLng(Double.parseDouble(PrefUtils.getString(this, "mLat", "0")),
                                Double.parseDouble(PrefUtils.getString(this, "mLng", "0"))), 10));//进来的时候，地图的默认中心点为上一次的最后退出位置
            }
        }
        mSensorHelper = new SensorEventHelper(this);
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
        UiSettings settings = aMap.getUiSettings();
        settings.setZoomControlsEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        aMap.setMapCustomEnable(true);//设置自定义的地图颜色
        aMap.setOnCameraChangeListener(this);//用于获取当前的缩放等级
        aMap.setOnMarkerClickListener(this);//设置marker的点击事件
        routeSearch = new RouteSearch(this);//计算路径规划
        routeSearch.setRouteSearchListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setInfoWindowAdapter(this);
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        EventBus.getDefault().register(this);
        registerMessageReceiver();//注册广播，用于接收开锁通知
        rl_all.setSelected(true);//进入的时候默认选择全部
        popSrevice = new PopWindowSrevice(this);
        popSrevice.initPopWindow(this);
        addRlList();
        mRefreshAnim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_refresh);
        mRefreshAnim.setInterpolator(new LinearInterpolator());
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {//每分钟获取一次当前订单的价钱
                    Connect.currentOrder(MainActivity.this, device_id, order_sn, MainActivity.this);
                }
                super.handleMessage(msg);
            }
        };
        //判断需不需要进入指导页面
        if (!PrefUtils.getBooleanWithName(this, "first", "is_intro_showed", false)) {
            startActivity(new Intent(MainActivity.this, IntroActivity.class).putExtra("from", "home"));
        }
        String bookJson = getIntent().getStringExtra("order");
        if (null != bookJson) {
            COrder order = new Gson().fromJson(bookJson, COrder.class);
            order_sn = order.getData().getCurrent_order().getOrder_sn();
            if ("0".equals(order.getData().getCurrent_order().getOrder_state())) {
                setOrdering1(order);
                bicycle_sn = order.getData().getCurrent_order().getLock_sn();
            } else if ("1".equals(order.getData().getCurrent_order().getOrder_state())) {
                device_id = order.getData().getCurrent_order().getLock_sn();
                setRiding();
            }
        }
    }


    /**
     * 加入所有单车类型的分类
     */
    private void addRlList() {
        rlList = new ArrayList<>();
        rlList.add(0, rl_all);
        rlList.add(1, rl_single);
        rlList.add(2, rl_double);
        rlList.add(3, rl_family);
    }


    /**
     * 注册广播
     */
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MyReceiver.MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getId() != markerId) {
            if (status != 3 && status != 4) {
                hud = Utils.createHud(MainActivity.this);
                if (!markerId.equals(marker.getId())) {
                    blat = marker.getPosition().latitude;
                    blng = marker.getPosition().longitude;
                    if (walkRouteOverlay != null) {
                        walkRouteOverlay.removeFromMap();
                    }
                    try {
                        JSONObject object = new JSONObject((String) marker.getObject());
                        setOrderOneStep(object);
                    } catch (Exception e) {
                    }
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(clat, clng), new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude));
                    RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WalkDefault);
                    routeSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
                }
                return true;
            }
        }
        return true;
    }

    //走路的路径规划
    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int i) {
        hud.dismiss();
        if (result != null && result.getPaths() != null) {
            if (result.getPaths().size() > 0) {
                mWalkRouteResult = result;
                final WalkPath walkPath = mWalkRouteResult.getPaths()
                        .get(0);
                walkRouteOverlay = new WalkRouteOverlay(
                        this, aMap, walkPath,
                        mWalkRouteResult.getStartPos(),
                        mWalkRouteResult.getTargetPos());
                walkRouteOverlay.removeFromMap();
                walkRouteOverlay.addToMap();
                walkRouteOverlay.zoomToSpan();//移动地图 到可是路径规划
                ViewTools.setStringToTextView(MainActivity.this, R.id.tv_length, (int) walkPath.getDistance() + "");
                ViewTools.setStringToTextView(MainActivity.this, R.id.tv_time, AMapUtil.getFriendlyTime((int) walkPath.getDuration()));
            }
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (status == 2) {
            setOrderOneStepExit();
        }
    }

    /**
     * 这个是  15分钟倒计时完成后的回调
     *
     * @param result
     */
    @Override
    public void onDownTimeFinish(String result) {
        setOrderOneStepExit();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MyReceiver.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                //这里接收到开锁信息后开始计费
                try {
                    if (getCode(intent.getStringExtra("message")) == 0) {
                        JSONObject data = new JSONObject(intent.getStringExtra("message")).getJSONObject("data");
                        order_sn = data.getString("order_sn");
                        device_id = data.getString("device_id");
                        if ("open".equals(data.getString("cmd"))) {//开锁显示计费  和通知关闭
                            cancelTimer();
                            EventBus.getDefault().post("lock_open");
                            setRiding();
                        } else if ("close".equals(data.getString("cmd"))) {//关锁跳转到计费完成页面   和隐藏计费 恢复初始状态
                            setRidingOver();
                            startActivity(new Intent(MainActivity.this, RideOverActivity.class).putExtra("order_sn", order_sn).putExtra("device_id", device_id));
                        }
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    /**
     * 设置为骑行状态  让其他按钮不可点
     */
    private void setRiding() {
        status = 4;
        destroyMarker(aMap.getMapScreenMarkers());//开锁之后移除所有的marker点
        ViewTools.setVisible(MainActivity.this, R.id.ll_runing);//显示计费界面
        ViewTools.setGone(MainActivity.this, R.id.ib_zxing);//隐藏扫码按钮
        ViewTools.setGone(MainActivity.this, R.id.ll_ordering);//隐藏预约中界面
        ViewTools.setGone(MainActivity.this, R.id.iv_position);//隐藏地图中间的大头针
        ib_service.setEnabled(false);//设置客户服务按钮不可点
        iv_home_head.setEnabled(false);//个人中心按钮不可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置不可滑出侧滑菜单
        sendHandlerEveryMin();
        if (walkRouteOverlay != null) {
            walkRouteOverlay.removeFromMap();
        }
    }

    /**
     * 设置为骑行结束状态  让其他按钮可点
     */
    private void setRidingOver() {
        status = 1;
        ViewTools.setGone(MainActivity.this, R.id.ll_runing);//隐藏计费界面
        ViewTools.setVisible(MainActivity.this, R.id.ib_zxing);//显示扫码按钮
        ViewTools.setVisible(MainActivity.this, R.id.iv_position);//显示大头针
        ib_service.setEnabled(true);//设置客户服务按钮可点
        iv_home_head.setEnabled(true);//个人中心按钮可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开手势滑动
    }

    /**
     * 预约第一步 点击marker后弹出
     */
    private void setOrderOneStep(JSONObject object) {
        status = 2;
        try {
            if ("1".equals(object.getString("type"))) {
                ViewTools.setStringToTextView(MainActivity.this, R.id.tv_bike_type, "单人自行车");
            } else if ("2".equals(object.getString("type"))) {
                ViewTools.setStringToTextView(MainActivity.this, R.id.tv_bike_type, "双人自行车");
            } else if ("3".equals(object.getString("type"))) {
                ViewTools.setStringToTextView(MainActivity.this, R.id.tv_bike_type, "家庭自行车");
            }
//            bicycle_sn=object.getString("bicycle_sn");//现在用的是锁的sn，到时候要换成单车sn
            bicycle_sn = object.getString("lock_sn");
            scenic_spot_name = object.getString("scenic_spot_name");
            ViewTools.setStringToTextView(MainActivity.this, R.id.tv_scenic_name, object.getString("scenic_spot_name"));
            ViewTools.setStringToTextView(MainActivity.this, R.id.tv_fee, object.getString("fee"));
        } catch (JSONException e) {
        }
        ViewTools.setVisible(MainActivity.this, R.id.ll_start_order);
        ViewTools.setGone(MainActivity.this, R.id.iv_position);//隐藏地图中间的大头针
        ib_service.setEnabled(false);//设置客户服务按钮不可点
        ib_zxing.setEnabled(false);//设置客户服务按钮不可点
        iv_home_head.setEnabled(false);//个人中心按钮不可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置不可滑出侧滑菜单
    }

    /**
     * 预约第一步取消
     */
    private void setOrderOneStepExit() {
        status = 1;
        ViewTools.setGone(MainActivity.this, R.id.ll_start_order);
        ViewTools.setGone(MainActivity.this, R.id.ll_ordering);
        ViewTools.setVisible(MainActivity.this, R.id.iv_position);
        ib_zxing.setEnabled(true);//设置扫码按钮可点
        ib_service.setEnabled(true);//设置客户服务按钮可点
        iv_home_head.setEnabled(true);//个人中心按钮可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开手势滑动
        ViewTools.setVisible(MainActivity.this, R.id.iv_position);//显示地图中间的大头针
        if (walkRouteOverlay != null) {
            walkRouteOverlay.removeFromMap();
        }
    }

    /**
     * 预约中
     */
    private void setOrdering(BikeOrder result, String time) {
        status = 3; // 预约中
        ViewTools.setGone(MainActivity.this, R.id.ll_start_order);
        ViewTools.setVisible(MainActivity.this, R.id.ll_ordering);
        ib_service.setEnabled(false);//设置客户服务按钮不可点
        ib_zxing.setEnabled(true);//设置搜索按钮可点
        iv_home_seach.setEnabled(false);//搜索按钮不可点
        iv_home_head.setEnabled(false);//个人中心按钮不可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置不可滑出侧滑菜单
        ViewTools.setStringToTextView(MainActivity.this, R.id.tv_scenic_spot_name, scenic_spot_name);
        ViewTools.setStringToTextView(MainActivity.this, R.id.tv_bike_sn, result.getData().getBicycle_sn());
        myCount = new MyCount(Integer.parseInt(result.getData().getKeep_time()) * 1000, 1000, tv_count_down_time, MainActivity.this);
        myCount.start();
    }

    /**
     * 预约中(杀掉进程)
     */
    private void setOrdering1(COrder result) {
        status = 3; // 预约中
        ViewTools.setGone(MainActivity.this, R.id.ll_start_order);
        ViewTools.setVisible(MainActivity.this, R.id.ll_ordering);
        ib_service.setEnabled(false);//设置客户服务按钮不可点
        ib_zxing.setEnabled(true);//设置搜索按钮可点
        iv_home_head.setEnabled(false);//个人中心按钮不可点
        index_drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置不可滑出侧滑菜单
        ViewTools.setStringToTextView(MainActivity.this, R.id.tv_scenic_spot_name, scenic_spot_name);
        ViewTools.setStringToTextView(MainActivity.this, R.id.tv_bike_sn, result.getData().getCurrent_order().getBicycle_sn());
        myCount = new MyCount(result.getData().getCurrent_order().getKeep_time() * 1000, 1000, tv_count_down_time, MainActivity.this);
        myCount.start();
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        popSrevice.hidePopWindow();
        switch (view.getId()) {
            case R.id.btn_report_illegally_park://违停举报
                intent = new Intent(MainActivity.this, InformParkActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_find_bug://发现故障
                intent = new Intent(MainActivity.this, FindBikeFaultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_help://使用帮助
                intent = new Intent(MainActivity.this, UserGuideActivity.class);
                startActivity(intent);
                break;
            case R.id.sure_btn:
                myDialog.dismiss();
                if ("预约".equals(Utils.getStringFromButton(view, R.id.sure_btn))) {
                    Connect.book(MainActivity.this, mLat + "", mLng + "", bicycle_sn, MainActivity.this);
                } else if ("确定".equals(Utils.getStringFromButton(view, R.id.sure_btn))) {
                    Connect.cancelBook(MainActivity.this, order_sn, MainActivity.this);
                }
                break;
        }
    }

    //通过注解  设置点击事件
    @Event(value = {R.id.rl_all, R.id.rl_single, R.id.rl_double, R.id.rl_family, R.id.ci_head_img, R.id.iv_home_seach, R.id.btn_order_one,
            R.id.btn_logion_or_integral, R.id.iv_home_head, R.id.ib_zxing, R.id.ib_service, R.id.ib_location, R.id.ll_my_wallet,
            R.id.ll_my_route, R.id.btn_cancel_order, R.id.btn_order_nav,
            R.id.ll_my_msg, R.id.ll_invite_friend, R.id.ll_user_guide, R.id.ll_setting, R.id.ll_order_one, R.id.rl_bell})
    private void onEventClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_all:
                setIndexSelect(0);
                break;
            case R.id.rl_single:
                setIndexSelect(1);
                break;
            case R.id.rl_double:
                setIndexSelect(2);
                break;
            case R.id.rl_family:
                setIndexSelect(3);
                break;
            case R.id.ll_order_one:
                setOrderOneStepExit();
                break;
            case R.id.btn_order_one:
                //判断 有没有登录
                if ("-1".equals(MyApp.userId)) {
                    startActivity(new Intent(MainActivity.this, PhoneNumVerifyActivity.class));
                    setOrderOneStepExit();
                } else {
                    myDialog = new MyDialog();
                    myDialog.createBooleanDialog(this, "你确定要预约这辆单车吗？", "预约", this, false);
                    myDialog.show();
                }
                break;
            case R.id.rl_bell:
                Connect.beepLock(MainActivity.this, bicycle_sn, MainActivity.this);
                break;
            case R.id.btn_cancel_order://取消预约
                myDialog = new MyDialog();
                myDialog.createBooleanDialog(this, "你确定要取消吗？", "确定", this, false);
                myDialog.show();
                break;
            case R.id.btn_order_nav://导航
                intent = new Intent();
                intent.putExtra("lat", blat);
                intent.putExtra("lng", blng);
                intent.putExtra("mlat", mLat);
                intent.putExtra("mlng", mLng);
                intent.setClass(MainActivity.this, NaviActivity.class);
                startActivity(intent);
                break;
            case R.id.ci_head_img://头像
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : UserInfoActivity.class));
                break;
            case R.id.iv_home_seach://搜索
                if (status == 1) {
                    startActivityForResult(new Intent(MainActivity.this, SeachActivity.class).putExtra("addr", addr), SEACH);
                } else {
                    ToastUtils.showShort(this, "预约和骑行状态该功能不可用");
                }
                break;
            case R.id.btn_logion_or_integral://注册登录/显示积分   需要判断状态
                MainActivityPermissionsDispatcher.showStorageWithCheck(this);
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : CreditScoreActivity.class));
                break;
            case R.id.iv_home_head://侧滑菜单
                if (mDrawerLayout.isDrawerOpen(findViewById(R.id.dl_menu))) {
                    mDrawerLayout.closeDrawers();
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.ib_zxing://扫码开锁
                MainActivityPermissionsDispatcher.showCameraWithCheck(this);
                break;
            case R.id.ib_service://客户服务
                if ("-1".equals(MyApp.userId)) {
                    startActivity(new Intent(MainActivity.this, PhoneNumVerifyActivity.class));
                } else {
                    popSrevice.showPopWindow(index_drawerlayout);
                }
                break;
            case R.id.ib_location://定位到现在的位置
                if (status == 2) {
                    setOrderOneStepExit();
                }
                goTo(mLat, mLng);
                break;
            case R.id.ll_my_wallet://我的钱包
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : MyWalletActivity.class));
                break;
            case R.id.ll_my_route://我的行程
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : MyTravelActivity.class));
                break;
            case R.id.ll_my_msg://我的消息
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : MyMessageActivity.class));
                break;
            case R.id.ll_invite_friend://邀请好友
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : InviteFriendActivity.class));
                break;
            case R.id.ll_user_guide://用户指南
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : UserGuideActivity.class));
                break;
            case R.id.ll_setting://用户设置
                startActivity(new Intent(MainActivity.this, "-1".equals(MyApp.userId) ? PhoneNumVerifyActivity.class : SettingActivity.class));
                break;
        }
    }

    /**
     * 设置选中的类型
     *
     * @param i 第几个
     */
    private void setIndexSelect(int i) {
        for (int j = 0; j < rlList.size(); j++) {
            if (i == j) {
                rlList.get(j).setSelected(true);
                type = j;
            } else {
                rlList.get(j).setSelected(false);
            }
        }
        if (clat != 0) {
            getMarker();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mMapView.onDestroy();
        if (mLocMarker != null) {
            mLocMarker.destroy();
        }
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
            mSensorHelper = null;
        }
        deactivate();
        unregisterReceiver(mMessageReceiver);//注销广播
    }

    @Override
    protected void onResume() {
        super.onResume();
        user_id = PrefUtils.getString(this, "user_id", "-1");
        if (!"".equals(PrefUtils.getString(this, "avatar", ""))) {
            ViewTools.setVisible(this, R.id.tv_phone_num);
            x.image().bind(ci_head_img, PrefUtils.getString(this, "avatar", ""));
            btn_logion_or_integral.setText(" 信用积分  " + PrefUtils.getString(this, "credit_point", "") + "  ＞");
            ViewTools.setStringToTextView(this, R.id.tv_phone_num, PrefUtils.getString(this, "nickname", ""));
        } else {
            btn_logion_or_integral.setText("登录/注册");
            ViewTools.setInvisible(this, R.id.tv_phone_num);
            x.image().bind(ci_head_img, "assets://user_avatar_default.png");
        }
        if (!"-1".equals(user_id)) {
            Connect.info(this, this);
        }
        state = PrefUtils.getString(this, "state", "-1");
        MyApp.userId = user_id;
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
        mMapView.onResume();
        if (!registed && !"-1".equals(user_id)) {
            registerPush(user_id);//注册别名
        }
    }

    /**
     * 极光注册别名
     *
     * @param id 用户id
     */
    private void registerPush(final String id) {
        JPushInterface.setAlias(this, id, new TagAliasCallback() {
            @Override
            public void gotResult(int arg0, String arg1, Set<String> arg2) {
                if (arg0 == 0) {
                    // 进行判断是否注册
                    registed = true;
                } else {
                    registerPush(id);
                }
            }
        });
    }

    //处理消息，用于更新UI
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void keepUseBike(String event) {
        if (event.contains("keepUseBike")) {
            Connect.book(MainActivity.this, mLat + "", mLng + "", event.replace("keepUseBike", ""), MainActivity.this);
            Connect.lockPosition(MainActivity.this, event.replace("keepUseBike", ""), MainActivity.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
        }
        mMapView.onPause();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 设置定位参数使用连续
            mLocationOption.setOnceLocation(false);
            mLocationOption.setInterval(1000);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
//        if (mListener != null && aMapLocation != null) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                // 获取位置信息
                mLat = aMapLocation.getLatitude();
                mLng = aMapLocation.getLongitude();
                addr = aMapLocation.getPoiName();
                LatLng location = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                if (!isFirstLoc) {
                    isFirstLoc = true;
                    addMarker(location);//添加定位图标
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
                } else {
                    mLocMarker.setPosition(location);
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                }
            }
        }
    }

    /**
     * 添加定位的marker  小蓝点
     *
     * @param latlng 经纬度
     */
    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        mLocMarker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource
                (this.getResources(), R.mipmap.navi_map_gps_locked))).anchor(0.5f, 0.5f).position(latlng));
        markerId = mLocMarker.getId();
    }

    /**
     * 挪到当前定位点
     *
     * @param lat 经度
     * @param lng 纬度
     */
    private void goTo(double lat, double lng) {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 17));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 处理二维码扫描结果
            case Connect.ZXING_CODE://扫码
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        //这里还要加上二维码的判断    不是所有二维码 都可以往服务器发送
                        if (!Validator.isLockCode(result)) {
                            Toast toast = Toast.makeText(this, "     请扫描车锁二维码     ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return;
                        }
                        Connect.openLock(this, result, mLat + "", mLng + "", this);//开锁
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        ToastUtils.showShort(MainActivity.this, "解析二维码失败");
                    }
                }
                break;
            case Connect.SEACH:
                if (null != data) {
                    SearchHistorysBean entity = (SearchHistorysBean) data.getSerializableExtra("entity");
                    goTo(entity.getLat(), entity.getLng());
                }
                break;
            case Connect.PERM:
                // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
                if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                    finish();
                }
                break;
            case Connect.REQUEST_PERMISSION_SETTING:
                MainActivityPermissionsDispatcher.showLocationWithCheck(this);
                break;
        }
    }

    // 按两次返回键退出应用
    DoubleClickExitHelper doubleClick = new DoubleClickExitHelper(this);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (status == 2) {
                setOrderOneStepExit();
                return true;
            } else {
                PrefUtils.setString(this, "mLat", mLat + "");//退出之前存下经纬度  用于下次进入的时候的默认中心点
                PrefUtils.setString(this, "mLng", mLng + "");
                return doubleClick.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    public void stopAnim() {
        mRefreshAnim.reset();
        iv_pb.clearAnimation();
        iv_pb.setVisibility(View.GONE);
    }

    public void startAnim() {
        iv_pb.setVisibility(View.VISIBLE);
        mRefreshAnim.reset();
        iv_pb.clearAnimation();
        iv_pb.startAnimation(mRefreshAnim);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        if (marker123 != null) {
            marker123.hideInfoWindow();
        }
        ViewTools.setInvisible(MainActivity.this, R.id.iv_hint);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        zoom = cameraPosition.zoom;//获取当前地图的缩放等级   用于获取marker地址
        if (isFirstLoc) {
            if (clat != 0.0) {
                if (AMapUtils.calculateLineDistance(new LatLng(clat, clng), new LatLng(cameraPosition.target.latitude,
                        cameraPosition.target.longitude)) > 100) {//移动距离大于100米  才会去获取marker点  避免请求过多
                    clat = cameraPosition.target.latitude;
                    clng = cameraPosition.target.longitude;
                    getMarker();
                }
            } else {
                clat = cameraPosition.target.latitude;
                clng = cameraPosition.target.longitude;
                getMarker();
            }
        }
    }

    /**
     * 获取marker点
     */
    public void getMarker() {
        if (status == 1 && zoom > 15) {
            Connect.getMarker(clat + "", clng + "", zoom + "", type + "", MainActivity.this);
            startAnim();
        }
    }

    /**
     * 每分钟发送一次handler
     */
    private void sendHandlerEveryMin() {
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    // 需要做的事:发送消息
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            };
            timer.schedule(task, 1, 60000);
        }
    }

    /**
     * 取消倒计时的任务
     */
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        if (myCount != null) {
            myCount.cancel();
            myCount = null;
        }
    }

    /**
     * 销毁marker点
     *
     * @param mapScreenMarkers
     */
    private void destroyMarker(List<Marker> mapScreenMarkers) {
        for (int i = 0; i < mapScreenMarkers.size(); i++) {
            if (!mapScreenMarkers.get(i).getId().equals(markerId)) {
                mapScreenMarkers.get(i).destroy();
            }
        }
    }

    /**
     * 地图上增加marker点
     *
     * @param list 接口获取的markerlist
     */
    private void addBikeMarker(List<MarkerBean.DataBean> list) {
        List<MarkLength> listMarkLength = new ArrayList<MarkLength>();
        for (int k = 0; k < list.size(); k++) {
            //排序  计算哪个离我最近
            listMarkLength.add(new MarkLength((int) AMapUtils.calculateLineDistance(CoordinateUtil.convert(MainActivity.this, new
                    LatLng(Double.parseDouble(list.get(k).getLat()), Double.parseDouble(list.get(k).getLng())), com.amap.api.maps.
                    CoordinateConverter.CoordType.valueOf("GPS")), new LatLng(clat, clng)), k));
        }
        Collections.sort(listMarkLength);
        mid = listMarkLength.get(0).getId();//排序  计算哪个离我最近
        for (int i = 0; i < list.size(); i++) {
            LatLng latLng = new LatLng(Double.parseDouble(list.get(i).getLat()), Double.parseDouble(list.get(i).getLng()));
            latLng = CoordinateUtil.convert(MainActivity.this, latLng, com.amap.api.maps.CoordinateConverter.CoordType.valueOf("GPS"));
            if ("1".equals(list.get(i).getType())) {//单人的marker
                if (i == mid) {
                    marker123 = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_green))).position(latLng).snippet("离我最近"));
                    marker123.setObject(list.get(i).toString());
                } else {
                    aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_green))).position(latLng)).setObject(list.get(i).toString());
                }
            } else if ("2".equals(list.get(i).getType())) {//双人marker
                if (i == mid) {
                    marker123 = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_pink))).position(latLng).snippet("离我最近"));
                    marker123.setObject(list.get(i).toString());
                } else {
                    aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_pink))).position(latLng)).setObject(list.get(i).toString());
                }
            } else {//家庭marker
                if (i == mid) {
                    marker123 = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_yellow))).position(latLng).snippet("离我最近"));
                    marker123.setObject(list.get(i).toString());
                } else {
                    aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.marker_yellow))).position(latLng)).setObject(list.get(i).toString());
                }
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//延时是为了避免infowindows跳动的情况
                marker123.showInfoWindow();
                marker123.setToTop();
            }
        }, 100);
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void showLocation() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("location")
                .commitAllowingStateLoss();
    }

    @NeedsPermission(Manifest.permission.BODY_SENSORS)
    void showSensor() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("sensor")
                .commitAllowingStateLoss();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showStorage() {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("storage")
                .commitAllowingStateLoss();
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForLocation(PermissionRequest request) {
        showRationaleDialog(R.string.permission_location_rationale, request);
    }

    @OnShowRationale(Manifest.permission.BODY_SENSORS)
    void showRationaleForSensor(PermissionRequest request) {
        showRationaleDialog(R.string.permission_sensor_rationale, request);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleForStorage(PermissionRequest request) {
        showRationaleDialog(R.string.permission_storage_rationale, request);
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }


    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void onLocationNeverAskAgain() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
        Toast toast = Toast.makeText(this, R.string.permission_location_never_askagain, Toast.LENGTH_LONG);
        showMyToast(toast, 10 * 1000);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        startZing();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        startZing();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    private void startZing() {
        if ("-1".equals(MyApp.userId)) {//没有登录状态
            startActivity(new Intent(MainActivity.this, PhoneNumVerifyActivity.class));
        } else {//登录状态
            if ("0".equals(state)) {
                startActivity(new Intent(MainActivity.this, TopUpDepositActivity.class));
            } else if ("1".equals(state)) {
                startActivity(new Intent(MainActivity.this, CertificationActivity.class));
            } else if ("4".equals(state)) {
                startActivity(new Intent(MainActivity.this, TopUpRechargeActivity.class));
            } else {
                startActivityForResult(new Intent(MainActivity.this, ZxingActivity.class), ZXING_CODE);
            }
        }
    }


    @Override  //请求成功后
    public void onSuccess(String result, int whereRequest) {
        if (getCode(result) == 99) {
            exitLogin(this,result);
            return;
        }
        switch (whereRequest) {
            case Connect.OPEN_LOCK:
                showMsg(result);
                if (getCode(result) == 0) {
                    startActivity(new Intent().setClass(this, OpenLockNumActivity.class));
                }
                break;
            case Connect.GET_BICYCLE_LOCATION:
                showMsg(result);
                stopAnim();
                if (getCode(result) == 0) {
                    MarkerBean markerBean = new Gson().fromJson(result, MarkerBean.class);
                    List<MarkerBean.DataBean> list = markerBean.getData();
                    if (list.size() == 0) {
                        ViewTools.setVisible(MainActivity.this, R.id.iv_hint);
                    } else {
                        ViewTools.setInvisible(MainActivity.this, R.id.iv_hint);
                    }
                    destroyMarker(aMap.getMapScreenMarkers());
                    addBikeMarker(list);
                }
                break;
            case Connect.BOOK:
                showMsg(result);
                if (getCode(result) == 0) {
                    BikeOrder bikeOrder = new Gson().fromJson(result, BikeOrder.class);
                    order_sn = bikeOrder.getData().getOrder_sn();
                    bicycle_sn = bikeOrder.getData().getLock_sn();//这里后面要换成单车sn
                    setOrdering(bikeOrder, "");
                }
                break;
            case Connect.GET_ORDER_INFO:
                if (getCode(result) == 0) {
                    CurrentOrder currentOrder = new Gson().fromJson(result, CurrentOrder.class);
                    ViewTools.setStringToTextView(MainActivity.this, R.id.tv_riding_time,
                            ("0".equals(currentOrder.getData().getTime().getHours()) ? "" : currentOrder.getData().getTime().getHours()
                                    + " 时 ") + currentOrder.getData().getTime().getMin());
                    ViewTools.setStringToTextView(MainActivity.this, R.id.tv_riding_bike_num, currentOrder.getData().getBicycle_sn());
                    ViewTools.setStringToTextView(MainActivity.this, R.id.tv_cur_price, currentOrder.getData().getOrder_amount() + "");
                }
                break;
            case Connect.CANCEL_ORDER:
                showMsg(result);
                if (getCode(result) == 0) {
                    cancelTimer();
                    setOrderOneStepExit();
                }
                break;
            case Connect.INFO:
                if (getCode(result) == 0) {
                    UserInfoBean userInfo = new Gson().fromJson(result, UserInfoBean.class);
                    UserInfoBean.DataBean data = userInfo.getData();
                    x.image().bind(ci_head_img, "".equals(data.getAvatar()) ? "assets://user_avatar_default.png" : data.getAvatar(),
                            new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.user_avatar_default).build());
                    btn_logion_or_integral.setText(" 信用积分  " + data.getCredit_point() + "  ＞");
                    ViewTools.setStringToTextView(this, R.id.tv_phone_num, data.getNickname());
                    PrefUtils.setString(MainActivity.this, "avatar", data.getAvatar());
                    PrefUtils.setString(MainActivity.this, "credit_point", data.getCredit_point());
                    PrefUtils.setString(MainActivity.this, "nickname", data.getNickname());
                    if ("1".equals(data.getDeposit_state()) && "1".equals(data.getVerify_state())) {
                        if ("0.00".equals(data.getAvailable_deposit())) {
                            PrefUtils.setString(this, "state", "2");
                            state = "2";
                        } else {
                            PrefUtils.setString(this, "state", "3");
                            state = "3";
                        }
                    } else if ("0".equals(data.getDeposit_state()) && "1".equals(data.getVerify_state())) {
                        PrefUtils.setString(this, "state", "4");
                        state = "4";
                    } else if ("1".equals(data.getDeposit_state()) && "0".equals(data.getVerify_state())) {
                        PrefUtils.setString(this, "state", "1");
                        state = "1";
                    }
                }
                break;
            case Connect.BEEP_LOCK:
                showMsg(result);
                break;
            case Connect.LOCK_POSITION:
                if (getCode(result) == 0) {
                    LockPosition lp = new Gson().fromJson(result, LockPosition.class);
                    JZLocationConverter.LatLng latlng = JZLocationConverter.wgs84ToGcj02(
                            new JZLocationConverter.LatLng(Double.parseDouble(lp.getData().getLat()), Double.parseDouble(lp.getData().getLng())));
                    blat = latlng.getLatitude();//保留用车   因为没有marker点了  所以要查找锁位置并赋值给原来的锁的坐标
                    blng = latlng.getLongitude();
                }
                break;
        }
    }

    @Override  //请求失败后
    public void onError(String errorMsg, int whereRequest) {
        switch (whereRequest) {
            case Connect.OPEN_LOCK:
                break;
            case Connect.GET_BICYCLE_LOCATION:
                stopAnim();
                break;
            case Connect.BOOK:
                break;
            case Connect.GET_ORDER_INFO:
                break;
            case Connect.CANCEL_ORDER:
                break;
        }
    }
}
