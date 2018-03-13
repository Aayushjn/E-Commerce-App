package com.example.aayush.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Aayush on 13-Mar-18.
 */

class ViewHolder{
    public TextView categoryView;
    public ImageView iconView;
}

public class CustomAdapter extends BaseAdapter{
    Context context;
    String[] categories;
    int[] icons;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] categories, int[] icons){
        this.context = applicationContext;
        this.categories = categories;
        this.icons = icons;
        inflater = LayoutInflater.from(applicationContext);
    }

    public int getCount(){
        return categories.length;
    }

    public Object getItem(int i){
        return null;
    }

    public long getItemId(int i){
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.category_list, viewGroup, false);
            holder = new ViewHolder();
            holder.categoryView = view.findViewById(R.id.textView);
            holder.iconView = view.findViewById(R.id.icon);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }

        holder.categoryView.setText(categories[i]);
        holder.iconView.setImageResource(icons[i]);
        return view;
    }
}
