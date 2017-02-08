package cn.estronger.bike.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.utils.DensityUtils;
import cn.estronger.bike.utils.PrefUtils;
import cn.estronger.bike.widget.DepthPageTransformer;

import org.xutils.x;

import java.util.ArrayList;

public class IntroActivity extends Activity implements OnClickListener {

	String[] drawableRes = new String[] { "assets://intro1.png", "assets://intro2.png", "assets://intro3.png", "assets://intro4.png"};
	private ViewPager vpGuide;
	private ArrayList<ImageView> mImageViewList;
	private LinearLayout llPointGroup;// 引导圆点的父控件
	private int mPointWidth;// 圆点间的距离
	private View viewRedPoint;// 小红点
	private RelativeLayout guide_start,rl;
	private TextView into;
//	ImageOptions imageOptions;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 更新sp, 表示已经展示了新手引导
		if ("home".equals(getIntent().getStringExtra("from"))){
			PrefUtils.setBooleanWithName(IntroActivity.this,"first", "is_intro_showed", true);
		}else {
			PrefUtils.setBooleanWithName(IntroActivity.this,"first", "is_intro_showed_zxing", true);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		setContentView(R.layout.activity_intro);

		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
		viewRedPoint = findViewById(R.id.view_red_point);
		guide_start = (RelativeLayout) findViewById(R.id.guide_start);
		rl = (RelativeLayout) findViewById(R.id.rl);
		guide_start.setOnClickListener(this);
		into = (TextView) findViewById(R.id.into);

		initViews();
		vpGuide.setAdapter(new GuideAdapter());

		vpGuide.setOnPageChangeListener(new GuidePageListener());
//		imageOptions = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_launcher).build();
//				.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
//				.setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
//				.setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//				.setImageScaleType(ImageView.ScaleType.FIT_XY)//缩放
//				.setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
//				.setUseMemCache(true)//设置使用缓存
//				.setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
//				.build();
		vpGuide.setPageTransformer(true, new DepthPageTransformer());
//		vpGuide.setPageTransformer(true, new ZoomOutPageTransformer());
	}

	/**
	 * 初始化界面
	 */
	private void initViews() {
		mImageViewList = new ArrayList<ImageView>();
		// 初始化引导页的3个页面
		for (int i = 0; i < drawableRes.length; i++) {
			ImageView image = new ImageView(this);
			image.setScaleType(ImageView.ScaleType.FIT_XY);
			x.image().bind(image, drawableRes[i]);
			mImageViewList.add(image);
			if (i == (drawableRes.length - 1)) {
				image.setOnClickListener(this);
			}
		}

		// 初始化引导页的小圆点
		for (int i = 0; i < drawableRes.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);// 设置引导页默认圆点

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DensityUtils.dp2px(this, 6), DensityUtils.dp2px(this, 6));
			if (i > 0) {
				params.leftMargin = DensityUtils.dp2px(this, 10);// 设置圆点间隔
			}

			point.setLayoutParams(params);// 设置圆点的大小

			llPointGroup.addView(point);// 将圆点添加给线性布局
		}

		// 获取视图树, 对layout结束事件进行监听
		llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// 当layout执行结束后回调此方法
					@Override
					public void onGlobalLayout() {
						llPointGroup.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWidth = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
					}
				});
	}

	/**
	 * ViewPager数据适配器
	 *
	 * @author Kevin
	 *
	 */
	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return drawableRes.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	/**
	 * viewpager的滑动监听
	 *
	 * @author Kevin
	 *
	 */
	class GuidePageListener implements OnPageChangeListener {

		// 滑动事件
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// System.out.println("当前位置:" + position + ";百分比:" + positionOffset
			// + ";移动距离:" + positionOffsetPixels);
			int len = (int) (mPointWidth * positionOffset) + position
					* mPointWidth;
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
					.getLayoutParams();// 获取当前红点的布局参数
			params.leftMargin = len;// 设置左边距

			viewRedPoint.setLayoutParams(params);// 重新给小红点设置布局参数
		}

		// 某个页面被选中
		@Override
		public void onPageSelected(int position) {
			// 最后一个页面
			if (position == (drawableRes.length - 1)) {
				into.setText("完成");
			} else {
				into.setText("跳过");
			}
		}

		// 滑动状态发生变化
		@Override
		public void onPageScrollStateChanged(int state) {
		}

	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
