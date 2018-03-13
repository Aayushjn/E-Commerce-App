package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryPage extends AppCompatActivity {
    ListView categoryList;
    String[] categories = {"Clothing", "Consumer Electronics", "Appliances", "Furniture", "Books",
            "Food"};
    int[] icons = {R.drawable.clothing, R.drawable.consumer_electronics, R.drawable.appliances,
            R.drawable.furniture, R.drawable.book, R.drawable.food};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        Toolbar toolbar = findViewById(R.id.categoryToolbar);
        toolbar.setTitle("Categories");
        setSupportActionBar(toolbar);

        categoryList = findViewById(R.id.categoryList);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), categories, icons);
        categoryList.setAdapter(customAdapter);
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), ProductPage.class);
                //intent.putExtra("Category", parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}
