package com.example.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> data;
    OnListViewClickLister listener;

    public MyAdapter(Context c, ArrayList<Student> d, OnListViewClickLister l){
        context=c;
        data=d;
        listener=l;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
//        TextView txt = v.findViewById(R.id.txt_name);
//        txt.setText(data.get(position).getName());
//        ImageView imageView = v.findViewById(R.id.image_view);
//        if(position%2==0)
//            imageView.setImageResource(R.drawable.ic_launcher_background);
//        else
//            imageView.setImageResource(R.drawable.ic_launcher_foreground);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(data.get(position).getName());
//            }
//        });
        MyViewHolder holder ;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
            holder = new MyViewHolder();

            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.imageView = convertView.findViewById(R.id.image_view);
        holder.txt =  convertView.findViewById(R.id.txt_name);

        holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        holder.txt.setText(data.get(position).getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data.get(position).getName());
            }
        });

        return convertView;
    }

    class MyViewHolder{
        ImageView imageView;
        TextView txt;
    }
}
