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

class ListViewHolder{
    public TextView categoryView;
    public ImageView iconView;
}

class GridViewHolder{
    public TextView titleView;
    public TextView priceView;
    public ImageView iconView;
}

public interface Adapters{
    class CustomListAdapter extends BaseAdapter{
        Context context;
        String[] categories;
        int[] icons;
        LayoutInflater inflater;

        public CustomListAdapter(Context applicationContext, String[] categories, int[] icons){
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
            ListViewHolder holder;

            if(view == null){
                view = inflater.inflate(R.layout.category_list, viewGroup, false);
                holder = new ListViewHolder();
                holder.categoryView = view.findViewById(R.id.textView);
                holder.iconView = view.findViewById(R.id.icon);
                view.setTag(holder);
            }
            else{
                holder = (ListViewHolder)view.getTag();
            }

            holder.categoryView.setText(categories[i]);
            holder.iconView.setImageResource(icons[i]);
            return view;
        }
    }

    class CustomGridAdapter extends BaseAdapter{
        private Context context;
        String[] productTitle;
        String[] productPrice;
        int[] images;
        LayoutInflater inflater;

        public CustomGridAdapter(Context applicationContext, String[] productTitle, String[] productPrice, int[] images){
            this.context = applicationContext;
            this.productPrice = productPrice;
            this.productTitle = productTitle;
            this.images = images;
            inflater = LayoutInflater.from(applicationContext);
        }

        @Override
        public int getCount() {
            return productTitle.length;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            GridViewHolder holder;

            if(view == null){
                view = inflater.inflate(R.layout.product_grid, viewGroup, false);
                holder = new GridViewHolder();
                holder.iconView = view.findViewById(R.id.productImage);
                holder.priceView = view.findViewById(R.id.priceText);
                holder.titleView = view.findViewById(R.id.titleText);
                view.setTag(holder);
            }
            else{
                holder = (GridViewHolder)view.getTag();
            }

            holder.titleView.setText(productTitle[i]);
            holder.priceView.setText(productPrice[i]);
            holder.iconView.setImageResource(images[i]);
            return view;
        }
    }
}
