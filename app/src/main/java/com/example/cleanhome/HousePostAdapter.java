package com.example.cleanhome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HousePostAdapter extends BaseAdapter {

    Context context;
    ArrayList<HousePost> arrayList;


    public HousePostAdapter(Context context, ArrayList<HousePost> arrayList) {
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
        convertView  = inflater.inflate(R.layout.about_layout, null);
        TextView address, price, date;
        ImageView imageView = convertView.findViewById(R.id.imageView8);

        address = convertView.findViewById(R.id.textView47);
        price = convertView.findViewById(R.id.textView52);
        date = convertView.findViewById(R.id.textView54);

        HousePost housePost = arrayList.get(position);
        String adrs = housePost.getAddress();
        byte[] img = housePost.getImage();
        String p = housePost.getPrice();
        String d = housePost.getDate();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        address.setText("Address: " +adrs);
        price.setText("Price: "+p);
        date.setText("Date: "+d);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
