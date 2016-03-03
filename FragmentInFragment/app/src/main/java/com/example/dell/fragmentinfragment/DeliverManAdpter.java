package com.example.dell.fragmentinfragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 2016/3/2.
 */
public class DeliverManAdpter extends BaseAdapter {
    private List<DeliverMan> mDeliverMans;
    private int recouce;
    //private LayoutInflater mInflater;
    private Context mContext;
    public DeliverManAdpter(List<DeliverMan> deliverManList, int recouce, Context context){
        this.mDeliverMans=deliverManList;
        this.recouce=recouce;
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return mDeliverMans.size();
    }

    @Override
    public DeliverMan getItem(int position) {
        return mDeliverMans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(mContext);
            convertView=inflater.inflate(recouce,parent, false);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.photo= (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.telphone= (TextView) convertView.findViewById(R.id.telephone);
            convertView.setTag(viewHolder);
       }else{
            Log.i("!!!!!!!!!!!",""+convertView.getTag());
            viewHolder=(ViewHolder)convertView.getTag();
        }
        DeliverMan deliverMan=getItem(position);
        if (deliverMan!=null){
            Log.i("22",""+viewHolder);
            viewHolder.name.setText(deliverMan.getName());
            viewHolder.photo.setImageResource(deliverMan.getImageId());
            viewHolder.telphone.setText(deliverMan.getTelephone());
        }
        return convertView;
    }
    private  class ViewHolder
    {
        TextView telphone;
        TextView name;
        ImageView photo;
    }
}
