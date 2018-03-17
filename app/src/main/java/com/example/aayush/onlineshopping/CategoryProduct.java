package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.aayush.onlineshopping.Adapters.CustomGridAdapter;

public class CategoryProduct extends AppCompatActivity {
    public static final String PRODUCT_IMAGE = "com.example.aayush.onlineshopping.PRODUCT_IMAGE";
    public static final String CATEGORY = "com.example.aayush.onlineshopping.CATEGORY";
    public static final String PRICE = "com.example.aayush.onlineshopping.PRICE";
    public static final String TITLE = "com.example.aayush.onlineshopping.TITLE";

    private final String[] categories = {"Clothing", "Consumer Electronics", "Appliances",
            "Furniture", "Books", "Food"};
    private final int[] icons = {R.drawable.clothing, R.drawable.consumer_electronics,
            R.drawable.appliances, R.drawable.furniture, R.drawable.book, R.drawable.food};
    private final String[] price = {"23.50", "25.00", "32.00", "30.00", "10.00", "20.50"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        final Toolbar toolbar = findViewById(R.id.productToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            toolbar.setTitle(extras.getString(CategoryPage.PRODUCT));
            toolbar.setLogo(extras.getInt(CategoryPage.PRODUCT_IMAGE));
        }
        else{
            toolbar.setTitle("Products");
        }

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CategoryPage.class));
            }
        });

        final GridView productGrid = findViewById(R.id.productGrid);
        final CustomGridAdapter gridAdapter = new CustomGridAdapter(getApplicationContext(), categories,
                price, icons);
        productGrid.setAdapter(gridAdapter);
        productGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra(PRODUCT_IMAGE, icons[position]);
                intent.putExtra(CATEGORY, categories[position]);
                intent.putExtra(TITLE, categories[position]);
                intent.putExtra(PRICE, price[position]);
                startActivity(intent);
            }
        });
    }
}
