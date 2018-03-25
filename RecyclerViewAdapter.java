package com.example.dell.logindemo;

/**
 * Created by dell on 19/03/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mData;

    public RecyclerViewAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.item_name.setText(mData.get(position).getName());
        holder.item_price.setText(mData.get(position).getPrice());
        holder.item_image.setImageResource(mData.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, ItemActivity.class);

                // passing data to ItemActivity
                intent.putExtra("name", mData.get(position).getName());
                intent.putExtra("category", mData.get(position).getCategory());
                intent.putExtra("price", mData.get(position).getPrice());
                //intent.putExtra("color", mData.get(position).getColor());
                intent.putExtra("quantity", mData.get(position).getQuantity());
                intent.putExtra("size", mData.get(position).getSize());
                intent.putExtra("image", mData.get(position).getImage());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_price;
        ImageView item_image;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            item_price = (TextView) itemView.findViewById(R.id.tv_item_price);
            item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);
            cardView = (CardView) itemView.findViewById(R.id.cv_layout);
        }
    }
}
