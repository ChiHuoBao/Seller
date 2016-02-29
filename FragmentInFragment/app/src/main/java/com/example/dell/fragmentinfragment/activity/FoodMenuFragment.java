package com.example.dell.fragmentinfragment.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.fragmentinfragment.R;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuFragment extends Fragment {

	private static final String TAG = "MainActivity";
	String[] arr = new String[] { "套餐A", "套餐B", "套餐C", "套餐D", "套餐E", "套餐F" };

	private ListView listView;
	private ListView listView2 ;
	private Button mButton;
	/**
	 * 本来想做标题停留在最上方的，不想想了
	 */
	TextView textView;
	/**
	 * 左边listview的要使用的数组
	 */

	private ArrayList<String> foodType = new ArrayList<String>();
	private ArrayList<String> allFood = new ArrayList<String>();
	/**
	 * 用来记录每一个 1 2 3 4 5 6 在右边listview的位置；
	 */
	List<Integer> nums = new ArrayList<Integer>();

	private void initData(){


		String[] arr2 = new String[] { "food1", "food2", "food3", "food4", "food5" };
		String[] arr3 = new String[] {  "food1", "food2", "food3", "food4", "food5", "food6" };
		String[] arr4 = new String[] {  "food1", "food2", "food3", "food4" };
		String[] arr5 = new String[] {  "food", "food", "food", "food", "food", "food",
				"food" };
		String[] arr6 = new String[] {  "food", "food", "food" };
		String[] arr7 = new String[] { "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food" };

		for (int i = 0; i < arr.length; i++) {
			foodType.add(arr[i]);
		}

		String[][] arr8 = new String[][] { arr2, arr3, arr4, arr5, arr6, arr7 };
		for (int i = 0; i < foodType.size(); i++) {
			allFood.add(foodType.get(i));
			for (int j = 0; j < arr8[i].length; j++) {
				allFood.add(arr8[i][j]);
			}
		}

		for (int i = 0; i < foodType.size(); i++)
		{
			if (i == 0)
			{
				nums.add(0);
			} else if (i > 0 && i < foodType.size())
			{
				int num = 0;
				for (int j = 0; j < i; j++)
				{
					num = num + arr8[j].length+1;

				}
				nums.add(num);
			}
		}
		nums.add(1000);
	}

	/**
	 * 用来存放 food数组
	 */
	/*List<String> list;*/


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//引入我们的布局
		View foodMenuLayout =  inflater.inflate(R.layout.food_menu, container, false);
		initData();
		listView = (ListView)foodMenuLayout.findViewById(R.id.listView1);
		listView2 = (ListView)foodMenuLayout.findViewById(R.id.listView2);
		mButton= (Button) foodMenuLayout.findViewById(R.id.but);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"sadfdsafasd",Toast.LENGTH_LONG).show();
			}
		});
		initView();
		return foodMenuLayout;

	}
	private void initView()
	{
		//菜品种类的listView
		MyFoodTypeListViewAdapter myFoodTypeListViewAdapter = new MyFoodTypeListViewAdapter(getActivity(),foodType);

		listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,foodType));

		/*list = new ArrayList<String>();

		for (int j = 0; j < arr8.length; j++)
		{
			for (int j2 = 0; j2 < arr8[j].length; j2++)
			{
				list.add(arr8[j][j2]);
			}
		}*/



		Log.i(TAG, "nums.size()是否等于arr8.length" + (nums.size() == foodType.size() + 1));
		/*listView2 = (ListView) getActivity().findViewById(R.id.listView2);*/
		listView2.setAdapter(new MyListGroupAdapter(getActivity(),allFood,foodType));
		listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
			}
		});
		listView2.setOnScrollListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {


			}

			/**
			 * 下面这个函数表示当滑到了当前类别的食物时就将左边的类别listView变色
			 */
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {


					/*for (int i = 0; i < nums.size() - 1; i++) {
						if (firstVisibleItem < nums.indexOf(i+1)&& firstVisibleItem >= nums.indexOf(i)
								&& listView.getChildCount() > 0) {
							listView.getChildAt(i).setBackgroundColor(
									Color.rgb(100, 100, 100));

						} else {
							listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
						}
					}*/

				if (nums.contains(firstVisibleItem) && listView.getChildCount() > 0) {

					for (int i = 0; i < listView.getChildCount(); i++) {
						if (i == nums.indexOf(firstVisibleItem)) {
							listView.getChildAt(i).setBackgroundColor(
									Color.rgb(100, 100, 100));
						} else {
							listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

						}


					}


				}
			}
		});

		/**
		 * 下面这个函数表示点了种类表中的item中，item变色，然后右边的菜品列表跳转的当前种类置顶
		 */
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {
				for (int i = 0; i < listView.getChildCount(); i++) {
					if (i == position) {
						view.setBackgroundColor(Color.rgb(100, 100, 100));
					} else {
						view.setBackgroundColor(Color.TRANSPARENT);
					}
				}

				listView2.setSelection(nums.get(position));
				Log.d("position", "" + nums.get(position));

			}
		});

	}

	/*class MyAdapter extends BaseAdapter
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

	}*/


	private static class MyFoodTypeListViewAdapter extends ArrayAdapter{
		private ArrayList arrayList;
		public MyFoodTypeListViewAdapter(Context context,ArrayList<String> arrayList){
			super(context,0);
			this.arrayList = arrayList;
		}

		@Override
		public View getView(int position, View convertView1, ViewGroup parent) {
			View view = convertView1;
			view = LayoutInflater.from(getContext()).inflate(R.layout.food_type, null);
			view.setMinimumHeight(800);
			Log.d("text",getItem(position).toString());
			Log.d("number",position+"");
			TextView textView = (TextView) view.findViewById(R.id.food_type_text);
			textView.setText(getItem(position).toString());
			return view;
		}
	}





	private static class  MyListGroupAdapter extends ArrayAdapter {
		private ArrayList listType;

		public MyListGroupAdapter(Context context, ArrayList<String> all, ArrayList<String> listType) {
			super(context, 0, all);
			this.listType = listType;
		}




		@Override
		public boolean isEnabled(int position) {
			if(listType.contains(getItem(position))){
				return false;
			}
			return super.isEnabled(position);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(listType.contains(getItem(position))){
				view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item_tag, null);

			}else{
				view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item, null);
			}
			TextView textView = (TextView) view.findViewById(R.id.group_list_item_text);
			textView.setText(getItem(position).toString());


			return view;
		}



	}



}
