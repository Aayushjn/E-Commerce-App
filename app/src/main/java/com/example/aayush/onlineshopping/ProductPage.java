package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.aayush.onlineshopping.Adapters.CustomGridAdapter;

public class ProductPage extends AppCompatActivity {
    GridView productGrid;
    String[] categories = {"Clothing", "Consumer Electronics", "Appliances", "Furniture", "Books",
            "Food"};
    int[] icons = {R.drawable.clothing, R.drawable.consumer_electronics, R.drawable.appliances,
            R.drawable.furniture, R.drawable.book, R.drawable.food};
    String[] price = {"23.50", "25.00", "32.00", "30.00", "10.00", "20.50"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Toolbar toolbar = findViewById(R.id.productToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            toolbar.setTitle(extras.getString(CategoryPage.PRODUCT));
        }
        else{
            toolbar.setTitle("Products");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CategoryPage.class));
            }
        });

        productGrid = findViewById(R.id.productGrid);
        CustomGridAdapter listAdapter = new CustomGridAdapter(getApplicationContext(), categories,
                price, icons);
        productGrid.setAdapter(listAdapter);
       /* productGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), ProductPage.class);
                startActivity(intent);
            }
        });*/
    }
}
