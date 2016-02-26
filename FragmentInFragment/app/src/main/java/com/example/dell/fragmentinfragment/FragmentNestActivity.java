package com.example.dell.fragmentinfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 嵌套Fragment使用
 *
 * @author 农民伯伯
 * //@see http://www.cnblogs.com/over140/archive/2013/01/02/2842227.html
 *
 */
public class FragmentNestActivity extends FragmentActivity implements OnClickListener {




    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.nested_fragments);

        findViewById(R.id.btnModule1).setOnClickListener(this);
        findViewById(R.id.btnModule2).setOnClickListener(this);
        findViewById(R.id.btnModule3).setOnClickListener(this);

        findViewById(R.id.btnModule1).performClick();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnModule1:
                addFragmentToStack(FragmentParent.newInstance(0));
                break;
            case R.id.btnModule2:
                addFragmentToStack(FragmentParent.newInstance(1));
                break;
            case R.id.btnModule3:
                addFragmentToStack(FragmentParent.newInstance(2));
                break;
            default:
                break;
        }
    }

    private void addFragmentToStack(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    /** 嵌套Fragment */
    public final static class FragmentParent extends Fragment {
        private TextView textView1;
        private TextView textView2;
        private int currIndex;
        private int screenW;
        private ArrayList<Fragment> fragmentListView;


        public  final static FragmentParent newInstance(int position) {
            FragmentParent f = new FragmentParent();
            Bundle args = new Bundle(2);
            args.putInt("position", position);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View convertView = inflater.inflate(R.layout.viewpager_fragments, container, false);
            ViewPager pager = (ViewPager) convertView.findViewById(R.id.pager);
            textView1 = (TextView)convertView.findViewById(R.id.tv_have_handle);
             textView2 = (TextView)convertView.findViewById(R.id.tv_not_handle);
            DisplayMetrics metric = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
            screenW = metric.widthPixels;
            ListViewFragment listViewFragment = new ListViewFragment();
            RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
            fragmentListView = new ArrayList<Fragment>();
            fragmentListView.add(listViewFragment);
            fragmentListView.add(recyclerViewFragment);


            final int parent_position = getArguments().getInt("position");
            //注意这里的代码
//            getChildFragmentManager()
            SectionPagerAdapter adapter=new SectionPagerAdapter(getChildFragmentManager(),fragmentListView);
            pager.setAdapter(adapter);
            pager.setCurrentItem(0);
            pager.addOnPageChangeListener(new MyOnPageChangeListener());
            return convertView;
        }

        class SectionPagerAdapter extends FragmentPagerAdapter {
            private ArrayList<Fragment> fragmentList;

            public SectionPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
                super(fm);
                this.fragmentList = list;
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:

                        return "ListView";
                    case 1:
                    default:
                        return "RecyclerView";
                }
            }




        }
        public  void changeCursor(int index){
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
        class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

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


    }




   /* public static*/





}
