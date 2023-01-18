package com.example.cleanhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedAdapter extends BaseAdapter {

    Context context;
    ArrayList<Feed> arrayList;

    public FeedAdapter(Context context, ArrayList<Feed> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {

        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView  = inflater.inflate(R.layout.about_feed_layout, null);
        TextView date, feed, giver;

        date = convertView.findViewById(R.id.textView61);
        feed = convertView.findViewById(R.id.textView59);
        giver = convertView.findViewById(R.id.textView60);

        Feed feed1 = arrayList.get(position);
        String feedBack = feed1.getFeedback();
        String feedGiver = feed1.getFeedbackGiver();
        String d = feed1.getDate();

        date.setText(d);
        feed.setText(feedBack);
        giver.setText(feedGiver);

        return convertView;
    }
}
