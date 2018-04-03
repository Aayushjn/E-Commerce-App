package com.example.aayush.onlineshopping;

import android.content.Context;
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

public class VendorHomepage extends AppCompatActivity {
    private Bundle extras;
    private final int id = extras != null ? extras.getInt("id") : 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_homepage);
        setTitle("Home");

        extras = getIntent().getExtras();

        RecyclerView rv = findViewById(R.id.rv_vendor_layout);

        VendorHomepageThread vendorHomepageThread = new
                VendorHomepageThread(getApplicationContext(), rv, id);
        vendorHomepageThread.start();
    }

    public void addProduct(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}

class VendorHomepageThread extends Thread {
    private Context current;
    private RecyclerView rv;
    private int id;

    VendorHomepageThread(Context current, RecyclerView rv, int id){
        this.current = current;
        this.rv = rv;
        this.id = id;
    }

    @Override
    public void run() {
        Databases.ProductDatabase productDatabase =
                Databases.ProductDatabase.getProductDatabase(current);
        DAOs.ProductDAO productDAO = productDatabase.productDAO();

        Cursor c;
        ArrayList<String> categoryList = new ArrayList<>();
        c = productDAO.getDistinctCategories(id);
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                String category = c.getString(c.getColumnIndex("category"));
                categoryList.add(category);
                c.moveToNext();
            }
        }
        c.close();

        Adapters.RVVendorAdapter myAdapter = new Adapters.RVVendorAdapter(current, categoryList);
        rv.setLayoutManager(new GridLayoutManager(current, 3));
        rv.setAdapter(myAdapter);
    }
}
