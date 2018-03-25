package com.example.dell.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemDisplayActivity extends AppCompatActivity {

    List<Item> items_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);

        items_list = new ArrayList<>();

        Intent intent = getIntent();
        int category_flag = intent.getExtras().getInt("category_flag");

        // TODO open a selected category's products db
        // TODO run a for loop and add items to items_list (items_list.add(new Item(name, category, price, quantity, size, image));

        switch(category_flag){
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
        RecyclerView myrv = (RecyclerView) findViewById(R.id.rv_item_display_layout);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, items_list);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }
}
