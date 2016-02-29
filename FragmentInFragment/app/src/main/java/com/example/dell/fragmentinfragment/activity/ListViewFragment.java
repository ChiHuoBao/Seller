package com.example.dell.fragmentinfragment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dell.fragmentinfragment.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksii Shliama.
 */
public class ListViewFragment extends BaseRefreshFragment {

    private PullToRefreshView mPullToRefreshView;
    private  ListView mListView;
    private String[] telphone=new String[]{"123456","545646546546"};
    private String[] time=new String[]{"10:00","11:30"};
    private String[] address=new String[]{"东南大学","文汇人才公寓"};
    private String[] orderId=new String[]{"11111111111","222222222"};
    private String[] item_price=new String[]{"10","15"};
    private String[] item_count=new String[]{"2","5"};
    private String[] item_name=new String[]{"鱼香肉丝","京酱肉丝"};
    private String[] notice=new String[]{"多放辣","多加饭"};
    private String[] receipt=new String[]{"旭日科技有限公司","小旭私房菜"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
         mListView = (ListView) rootView.findViewById(R.id.list_view);
        //listView.setAdapter(simpleAdapter);
        //mListView.setAdapter(new SampleAdapter(getActivity(), R.layout.list_item, mSampleList));
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for (int i=0;i<orderId.length;i++){
            Map<String, Object> items=new HashMap<String,Object>();
            items.put("telphone",telphone[i]);
            items.put("time",time[i]);
            items.put("address",address[i]);
            items.put("orderId",orderId[i]);
            items.put("item_price",item_price[i]);
            items.put("item_count",item_count[i]);
            items.put("item_name",item_name[i]);
            items.put("notice",notice[i]);
            items.put("receipt",receipt[i]);
            listItems.add(items);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter
                (getActivity(), listItems,R.layout.order_processing_fragment,
                        new String[]{"telphone","time","address","orderId","item_price","item_count","item_name","notice","receipt"},

                        new int[]{R.id.order_search_result_item_telphone,R.id.order_search_result_item_time,R.id.order_search_result_item_address,R.id.order_search_result_item_orderId,
                                R.id.order_item_price,R.id.order_item_count,R.id.order_item_name,R.id.order_notice,R.id.order_receipt}
                );
        mListView.setAdapter(simpleAdapter);
        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

        return rootView;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
//        for (int i=0;i<orderId.length;i++){
//            Map<String, Object> items=new HashMap<String,Object>();
//            items.put("telphone",telphone[i]);
//            items.put("time",time[i]);
//            items.put("address",address[i]);
//            items.put("orderId",orderId[i]);
//            items.put("item_price",item_price[i]);
//            items.put("item_count",item_count[i]);
//            items.put("item_name",item_name[i]);
//            items.put("notice",notice[i]);
//            items.put("receipt",receipt[i]);
//        }
//        SimpleAdapter simpleAdapter=new SimpleAdapter
//                (getActivity(), listItems,R.layout.order_processing_fragment,
//                        new String[]{"telphone","time","address","orderId","item_price","item_count","item_name","notice","receipt"},
//
//                        new int[]{R.id.order_search_result_item_telphone,R.id.order_search_result_item_time,R.id.order_search_result_item_address,R.id.order_search_result_item_orderId,
//                                R.id.order_item_price,R.id.order_item_count,R.id.order_item_name,R.id.order_notice,R.id.order_receipt}
//                );
//        mListView.setAdapter(simpleAdapter);
//
//    }

        class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_ICON = "icon";
        public static final String KEY_COLOR = "color";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, parent, false);
                //viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));

            return convertView;
        }

        class ViewHolder {
            ImageView imageViewIcon;
        }

    }


}
