package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import misc.Adapters;
import misc.Cart;

public class CartActivity extends AppCompatActivity {
    private FloatingActionButton fab;

    private Cart cart;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        fab = findViewById(R.id.fab);
        RecyclerView rView = findViewById(R.id.rv_cart_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cart = (Cart) extras.getSerializable("cart");
            id = extras.getInt("id");
        }

        assert cart != null;
        Adapters.RVCartAdapter myAdapter = new Adapters.RVCartAdapter(this,
                cart.getItemList());
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setAdapter(myAdapter);

        rView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
    }

    public void proceedToPay(View view){
        Intent payIntent = new Intent(this, PaymentAuth.class);
        payIntent.putExtra("cart", cart);
        payIntent.putExtra("id", id);
        startActivity(payIntent);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, CategoryPage.class);
        backIntent.putExtra("cart", cart);
        backIntent.putExtra("id", id);
        startActivity(backIntent);

        super.onBackPressed();
    }
}
