package misc;

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

import com.example.aayush.onlineshopping.ProductActivity;
import com.example.aayush.onlineshopping.R;

import java.util.List;

/**
 * Created by Aayush on 13-Mar-18.
 */

public interface Adapters{
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
        private Context current;
        private List<Item> data;

        public RecyclerViewAdapter(Context current, List<Item> data) {
            this.current = current;
            this.data = data;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(current);
            view = inflater.inflate(R.layout.cardview_item, parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, final int position) {
            holder.itemName.setText(data.get(holder.getAdapterPosition()).getName());
            holder.itemPrice.setText(data.get(holder.getAdapterPosition()).getPrice());
            holder.itemImage.setImageResource(data.get(holder.getAdapterPosition()).getImage());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(current, ProductActivity.class);

                    intent.putExtra("name", data.get(holder.getAdapterPosition()).getName());
                    intent.putExtra("category",
                            data.get(holder.getAdapterPosition()).getCategory());
                    intent.putExtra("price",
                            data.get(holder.getAdapterPosition()).getPrice());
                    intent.putExtra("quantity",
                            data.get(holder.getAdapterPosition()).getQuantity());
                    intent.putExtra("size", data.get(holder.getAdapterPosition()).getSize());
                    intent.putExtra("image",
                            data.get(holder.getAdapterPosition()).getImage());

                    current.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView itemName;
            TextView itemPrice;
            ImageView itemImage;
            CardView cardView;

            CustomViewHolder(View itemView){
                super(itemView);
                itemName = itemView.findViewById(R.id.tv_item_name);
                itemPrice = itemView.findViewById(R.id.tv_item_price);
                itemImage = itemView.findViewById(R.id.iv_item_image);
                cardView = itemView.findViewById(R.id.cv_layout);
            }
        }
    }

    class RVCartAdapter extends RecyclerView.Adapter<RVCartAdapter.CustomViewHolder> {

        private Context current;
        private List<Item> data;

        public RVCartAdapter(Context current, List<Item> data) {
            this.current = current;
            this.data = data;
        }

        @Override
        public RVCartAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(current);
            view = inflater.inflate(R.layout.cardview_cart_item, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, final int position) {
            holder.itemName.setText(data.get(holder.getAdapterPosition()).getName());
            holder.itemCategory.setText(data.get(holder.getAdapterPosition()).getCategory());
            holder.itemPrice.setText(data.get(holder.getAdapterPosition()).getPrice());

            if(data.get(holder.getAdapterPosition()).getSize() != -1) {
                holder.itemSize.setText(data.get(holder.getAdapterPosition()).getSize());
            }
            else {
                holder.itemSize.setVisibility(View.GONE);
            }

            holder.itemQty.setText(data.get(holder.getAdapterPosition()).getQuantity());
            holder.itemImage.setImageResource(data.get(holder.getAdapterPosition()).getImage());
            holder.cardView.setEnabled(true);

            holder.itemRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO remove the item from the cart
                    holder.cardView.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public static class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView itemName;
            TextView itemCategory;
            TextView itemPrice;
            TextView itemSize;
            TextView itemQty;
            ImageView itemImage;
            Button itemRemove;
            CardView cardView;

            public CustomViewHolder(View itemView){
                super(itemView);
                itemName = itemView.findViewById(R.id.tv_item_name);
                itemCategory = itemView.findViewById(R.id.tv_item_category);
                itemPrice = itemView.findViewById(R.id.tv_item_price);
                itemSize = itemView.findViewById(R.id.tv_item_size);
                itemQty = itemView.findViewById(R.id.tv_item_quantity);
                itemImage = itemView.findViewById(R.id.iv_item_image);
                itemRemove = itemView.findViewById(R.id.btn_remove_from_cart);
                cardView = itemView.findViewById(R.id.cv_cart_item_layout);
            }
        }
    }
}
