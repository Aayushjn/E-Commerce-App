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
    public CardView clothes, books, appliances, electronics, furniture, food;
    public int categoryFlag;

    final Bundle extras = getIntent().getExtras();
    final Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        clothes = findViewById(R.id.clothing);
        books = findViewById(R.id.books);
        appliances = findViewById(R.id.appliances);
        electronics = findViewById(R.id.electronics);
        furniture = findViewById(R.id.furniture);
        food = findViewById(R.id.food);

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 1;
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 2;
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 3;
            }
        });

        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 4;
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 5;
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFlag = 6;
            }
        });

        if(categoryFlag >= 1 && categoryFlag <= 6){
            Intent intent = new Intent(this, CategoryProduct.class);
            intent.putExtra("categoryFlag", categoryFlag);
            if(cart != null){
                intent.putExtra("cart", cart);
            }
            startActivity(intent);
        }
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
