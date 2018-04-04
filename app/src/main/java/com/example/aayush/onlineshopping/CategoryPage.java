package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import misc.Cart;

public class CategoryPage extends AppCompatActivity {
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        final Bundle extras = getIntent().getExtras();
        assert extras != null;
        cart = (Cart) extras.getSerializable("cart");


        ImageView clothes = findViewById(R.id.clothingImage);
        ImageView books = findViewById(R.id.booksImage);
        ImageView appliances = findViewById(R.id.appliancesImage);
        ImageView electronics = findViewById(R.id.electronicsImage);
        ImageView furniture = findViewById(R.id.furnitureImage);
        ImageView food = findViewById(R.id.foodImage);

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 1);
                intent.putExtra("id", extras.getInt("id"));
                if(cart != null){
                    intent.putExtra("cart", cart);
                }
                startActivity(intent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 2);
                intent.putExtra("id", extras.getInt("id"));
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 3);
                intent.putExtra("id", extras.getInt("id"));
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 4);
                intent.putExtra("id", extras.getInt("id"));
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 5);
                intent.putExtra("id", extras.getInt("id"));
                if(cart != null){
                    intent.putExtra("cart", cart);
                    startActivity(intent);
                }
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryProduct.class);
                intent.putExtra("categoryFlag", 6);
                intent.putExtra("id", extras.getInt("id"));
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
