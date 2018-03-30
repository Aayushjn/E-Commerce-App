package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import misc.Adapters;
import misc.Item;

public class CategoryProduct extends AppCompatActivity {
    ArrayList<Item> items_list;
    Bundle extras = getIntent().getExtras();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        items_list = new ArrayList<>();

        int categoryFlag = extras.getInt("categoryFlag");

        // TODO open a selected category's products db
        // TODO run a for loop and add items to items_list (items_list.add(new Item(name, category, price, quantity, size, image));

        switch(categoryFlag){
            case 1:
                // TODO add clothes-products
                break;
            case 2:
                // TODO add books-products
                break;
            case 3:
                // TODO add appliances-products
                break;
            case 4:
                // TODO add electronics-products
                break;
            case 5:
                // TODO add furniture-products
                break;
            case 6:
                // TODO add food-products
                break;
            default:
                break;
        }
        RecyclerView rv = findViewById(R.id.rv_item_display_layout);
        Adapters.RecyclerViewAdapter myAdapter = new Adapters.RecyclerViewAdapter(this, items_list);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(myAdapter);
    }
}
