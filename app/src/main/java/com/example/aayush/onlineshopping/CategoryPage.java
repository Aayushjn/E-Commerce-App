package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import misc.Cart;

public class CategoryPage extends AppCompatActivity {
    private int categoryFlag;

    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        Bundle extras = getIntent().getExtras();
        cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);


        CardView clothes = findViewById(R.id.clothing);
        CardView books = findViewById(R.id.books);
        CardView appliances = findViewById(R.id.appliances);
        CardView electronics = findViewById(R.id.electronics);
        CardView furniture = findViewById(R.id.furniture);
        CardView food = findViewById(R.id.food);

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 1;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                }
                startActivity(intent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 2;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 3;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 4;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 5;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 6;
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", categoryFlag);
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                Toast.makeText(this, "Logging out!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(CategoryPage.this, LoginScreen.class));
                break;
            case R.id.menu_cart:
                Intent intent = new Intent(this, CartActivity.class);
                if(cart != null){
                    intent.putExtra("cart", cart);
                }
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
