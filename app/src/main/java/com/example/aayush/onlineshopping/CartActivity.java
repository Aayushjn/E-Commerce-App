package com.example.aayush.onlineshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import misc.Adapters;
import misc.Item;

public class CartActivity extends AppCompatActivity {
    public List<Item> userCartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        userCartItems = new ArrayList<>();
        // TODO add items selected by user into the cart

        RecyclerView rv = findViewById(R.id.rv_cart_layout);
        Adapters.RVCartAdapter myAdapter = new Adapters.RVCartAdapter(this, userCartItems);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myAdapter);
    }
}
