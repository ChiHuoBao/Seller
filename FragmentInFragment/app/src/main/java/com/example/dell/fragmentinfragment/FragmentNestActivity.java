package com.example.dell.fragmentinfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

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

            final int parent_position = getArguments().getInt("position");
            //注意这里的代码
//            getChildFragmentManager()
            SectionPagerAdapter adapter=new SectionPagerAdapter(getChildFragmentManager());
            pager.setAdapter(adapter);
            return convertView;
        }
    }
    public static class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListViewFragment();
                case 1:
                default:
                    return new RecyclerViewFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
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
}
