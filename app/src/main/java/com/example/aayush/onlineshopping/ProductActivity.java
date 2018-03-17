package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {
    private ImageView image;
    private TextView priceView;
    private TextView titleView;
    private TextView categoryView;
    private TextView descriptionView;

    /*public void onCartClick(View view){
        Intent intent = new Intent(this, cartActivity.class);
        startActivity(intent);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = findViewById(R.id.productToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CategoryProduct.class));
            }
        });

        image = findViewById(R.id.productImage);
        priceView = findViewById(R.id.priceText);
        titleView = findViewById(R.id.titleText);
        categoryView = findViewById(R.id.categoryText);
        descriptionView = findViewById(R.id.descriptionText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            image.setImageResource(R.drawable.book);
            priceView.setText(extras.getString(CategoryProduct.PRICE));
            titleView.setText(extras.getString(CategoryProduct.TITLE));
            categoryView.setText(extras.getString(CategoryProduct.CATEGORY));
        }
    }
}
