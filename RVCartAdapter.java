package com.example.dell.logindemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 22/03/2018.
 */

public class RVCartAdapter extends RecyclerView.Adapter<RVCartAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mData;

    public RVCartAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RVCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_cart_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.item_name.setText(mData.get(position).getName());
        holder.item_category.setText(mData.get(position).getCategory());
        holder.item_price.setText(mData.get(position).getPrice());

        if(mData.get(position).getSize() != -1)
            holder.item_size.setText(mData.get(position).getSize());
        else
            holder.item_size.setVisibility(View.GONE);;

        holder.item_quantity.setText(mData.get(position).getQuantity());
        holder.item_image.setImageResource(mData.get(position).getImage());
        holder.cardView.setEnabled(true);

        holder.item_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO remove the item from the cart
                holder.cardView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_category;
        TextView item_price;
        TextView item_size;
        TextView item_quantity;
        ImageView item_image;
        Button item_remove;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            item_category = (TextView) itemView.findViewById(R.id.tv_item_category);
            item_price = (TextView) itemView.findViewById(R.id.tv_item_price);
            item_size = (TextView) itemView.findViewById(R.id.tv_item_size);
            item_quantity = (TextView) itemView.findViewById(R.id.tv_item_quantity);
            item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);
            item_remove = (Button) itemView.findViewById(R.id.btn_remove_from_cart);
            cardView = (CardView) itemView.findViewById(R.id.cv_cart_item_layout);
        }
    }
}
