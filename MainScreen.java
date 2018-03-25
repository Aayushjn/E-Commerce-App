package com.example.dell.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {
    //private FirebaseAuth firebaseAuth;
    public CardView clothes, books, appliances, electronics,furniture, food;
    public int category_flag; // used identify the category and pass it to ItemDisplayActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //firebaseAuth = FirebaseAuth.getInstance();

        clothes = (CardView) findViewById(R.id.cv_clothes);
        books = (CardView) findViewById(R.id.cv_books);
        appliances = (CardView) findViewById(R.id.cv_appliances);
        electronics = (CardView) findViewById(R.id.cv_electronics);
        furniture = (CardView) findViewById(R.id.cv_furniture);
        food = (CardView) findViewById(R.id.cv_food);

        category_flag = 0;

        // TODO open a selected category's products db
        // TODO run a for loop and add items to products list (products.add(new Item(name, category, price, quantity, size, image));

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 1;
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 2;
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 3;
            }
        });

        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 4;
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 5;
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_flag = 6;
            }
        });
        if(category_flag >= 1 && category_flag <= 6){
            Intent intent = new Intent(MainScreen.this, ItemDisplayActivity.class);
            intent.putExtra("category_flag", category_flag);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // A menu which has logout and cart options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                //firebaseAuth.signOut();
                Toast.makeText(MainScreen.this, "Logging out!", Toast.LENGTH_SHORT).show();
                finish();
                // TODO after logout go to login activity
                //startActivity(new Intent(MainScreen.this, MainActivity.class));
                break;
            case R.id.menu_cart:
                startActivity(new Intent(MainScreen.this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
