package com.example.dell.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    public List<Item> userCartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        userCartItems = new ArrayList<>();
        // TODO add items selected by user into the cart

        RecyclerView myrv = (RecyclerView) findViewById(R.id.rv_cart_layout);
        RVCartAdapter myAdapter = new RVCartAdapter(this, userCartItems);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);
    }
}
