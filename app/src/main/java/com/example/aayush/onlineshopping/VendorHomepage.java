package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import dbs.DAOs;
import dbs.Databases;
import misc.Adapters;
import misc.Item;

public class VendorHomepage extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();
    private final int id = extras != null ? extras.getInt("id") : 0;

    ArrayList<String> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_homepage);
        setTitle("Home");

        Databases.VendorDatabase vendorDatabase =
                Databases.VendorDatabase.getVendorDatabase(getApplicationContext());
        Databases.ProductDatabase productDatabase =
                Databases.ProductDatabase.getProductDatabase(getApplicationContext());
        DAOs.VendorDAO vendorDAO = vendorDatabase.vendorDAO();
        DAOs.ProductDAO productDAO = productDatabase.productDAO();

        Cursor c;
        categoryList = new ArrayList<>();
        c = productDAO.getDistinctCategories(id);
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                String category = c.getString(c.getColumnIndex("category"));
                categoryList.add(category);
                c.moveToNext();
            }
        }
        c.close();

        RecyclerView rv = findViewById(R.id.rv_vendor_layout);
        Adapters.RVVendorAdapter myAdapter = new Adapters.RVVendorAdapter(this, categoryList);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(myAdapter);
    }

    public void addProduct(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
