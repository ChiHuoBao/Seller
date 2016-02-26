package com.example.imooc_weixinfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FrdFragment extends Fragment {

	private static final String TAG = "MainActivity";

	private ListView listView;
	private ListView listView2 ;
	/**
	 * 本来想做标题停留在最上方的，不想想了
	 */
	TextView textView;
	/**
	 * 左边listview的要使用的数组
	 */
	String[] arr = new String[] { "套餐A", "套餐B", "套餐C", "套餐D", "套餐E", "套餐F" };


	String[] arr2 = new String[] { "1", "food", "food", "food", "food", "food" };
	String[] arr3 = new String[] { "2", "food", "food", "food", "food", "food", "food" };
	String[] arr4 = new String[] { "3", "food", "food", "food", "food" };
	String[] arr5 = new String[] { "4", "food", "food", "food", "food", "food", "food",
			"food" };
	String[] arr6 = new String[] { "5", "food", "food", "food" };
	String[] arr7 = new String[] { "6", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food" };

	String[][] arr8 = new String[][] { arr2, arr3, arr4, arr5, arr6, arr7 };

	/**
	 * 用来存放 food数组
	 */
	List<String> list;

	/**
	 * 用来记录每一个 1 2 3 4 5 6 在右边listview的位置；
	 */
	List<Integer> nums = new ArrayList<Integer>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//引入我们的布局
		View foodMenuLayout =  inflater.inflate(R.layout.food_menu, container, false);
		listView = (ListView)foodMenuLayout.findViewById(R.id.listView1);
		listView2 = (ListView)foodMenuLayout.findViewById(R.id.listView2);
		initView();
		return foodMenuLayout;

	}
	private void initView()
	{



		//菜品种类的listView
		listView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_expandable_list_item_1, arr));

		list = new ArrayList<String>();

		for (int j = 0; j < arr8.length; j++)
		{
			for (int j2 = 0; j2 < arr8[j].length; j2++)
			{
				list.add(arr8[j][j2]);
			}
		}

		for (int i = 0; i < arr8.length; i++)
		{
			if (i == 0)
			{
				nums.add(0);
			} else if (i > 0 && i < arr8.length)
			{
				int num = 0;
				for (int j = 0; j < i; j++)
				{
					num = num + arr8[j].length;

				}
				nums.add(num);
			}
		}

		Log.i(TAG, "nums.size()是否等于arr8.length" + (nums.size() == arr8.length));
		/*listView2 = (ListView) getActivity().findViewById(R.id.listView2);*/
		listView2.setAdapter(new MyAdapter());

		listView2.setOnScrollListener(new AbsListView.OnScrollListener()
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{

			}

			/**
			 * 下面这个函数表示当滑到了当前类别的食物时就将左边的类别listView变色
			 */
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount)
			{
				if (nums.contains(firstVisibleItem) && listView.getChildCount() > 0)
				{

					for (int i = 0; i < listView.getChildCount(); i++)
					{
						if (i == nums.indexOf(firstVisibleItem))
						{
							listView.getChildAt(i).setBackgroundColor(
									Color.rgb(100, 100, 100));
						} else
						{
							listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

						}


					}



				}
			}
		});

		/**
		 * 下面这个函数表示点了种类表中的item中，item变色，然后右边的菜品列表跳转的当前种类置顶
		 */
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				for (int i = 0; i < listView.getChildCount(); i++)
				{
					if (i == position)
					{
						view.setBackgroundColor(Color.rgb(100, 100, 100));
					} else
					{
						view.setBackgroundColor(Color.TRANSPARENT);
					}
				}

				listView2.setSelection(nums.get(position));

			}
		});

	}

	class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return list.size();
		}

		@Override
		public Object getItem(int position)
		{
			return list.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			TextView textView = new TextView(getActivity());
			textView.setText(list.get(position));
			textView.setGravity(Gravity.CENTER_VERTICAL);
			textView.setMinHeight(100);

			if (nums.contains(position))
			{
				textView.setBackgroundColor(Color.argb(80,80, 80, 80));
			}

			return textView;
		}

	}

}
