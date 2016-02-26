package com.example.imooc_weixinfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeixinFragment extends Fragment {
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private ImageView image;
	private TextView textView1, textView2;
	private int currIndex;//当前页卡编号
	private int bmpW;//横线图片宽度
	private int offset;//图片移动的偏移量
	private int screenW;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//引入我们的布局
		View orderManageLayout = inflater.inflate(R.layout.order_manage, container, false);
		/*DisplayMetrics metric = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenW = metric.widthPixels;
		initView();


		InitViewPager();*/
		return orderManageLayout;
	}
	private void  initView(){
		textView1 = new TextView(getActivity());
		textView2 = new TextView(getActivity());
		textView1 = (TextView)getView().findViewById(R.id.tv_have_handle);
		textView2 = (TextView)getView().findViewById(R.id.tv_not_handle);


		textView1.setOnClickListener(new txListener(0));
		textView2.setOnClickListener(new txListener(1));

	}
	public class txListener implements View.OnClickListener{
		private int index=0;

		public txListener(int i) {
			index =i;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index);
		}
	}

	public void InitViewPager(){
		mPager = (ViewPager)getActivity().findViewById(R.id.viewpager);
		fragmentList = new ArrayList<Fragment>();
		Fragment btFragment= new ButtonFragment();
		Fragment secondFragment = TestFragment.newInstance("this is second fragment");

		fragmentList.add(btFragment);
		fragmentList.add(secondFragment);


		//给ViewPager设置适配器
		mPager.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList));
		mPager.setCurrentItem(0);//设置当前显示标签页为第一页
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
	}

	public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

		private int one = screenW/2;//两个相邻页面的偏移量

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//平移动画
			currIndex = arg0;
			changeCursor(currIndex);
			animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
			animation.setDuration(200);//动画持续时间0.2秒
            /*image.startAnimation(animation);//是用ImageView来显示动画的*/
			int i = currIndex + 1;
			Toast.makeText(getActivity(), "您选择了第" + i + "个页卡", Toast.LENGTH_SHORT).show();
		}
	}


	public void changeCursor(int index){
		textView1 = (TextView)getActivity().findViewById(R.id.tv_have_handle);
		textView2 = (TextView)getActivity().findViewById(R.id.tv_not_handle);
		if (index == 0){
			textView1.setBackgroundColor(Color.parseColor("#0099cc"));
			textView1.setTextColor(Color.parseColor("#ffffff"));
			textView2.setBackgroundColor(Color.parseColor("#ffffff"));
			textView2.setTextColor(Color.parseColor("#0099cc"));
		}else {
			textView2.setBackgroundColor(Color.parseColor("#0099cc"));
			textView2.setTextColor(Color.parseColor("#ffffff"));
			textView1.setBackgroundColor(Color.parseColor("#ffffff"));
			textView1.setTextColor(Color.parseColor("#0099cc"));

		}
	}

}
