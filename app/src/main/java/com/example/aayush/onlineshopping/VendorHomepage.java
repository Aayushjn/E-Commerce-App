package com.example.aayush.onlineshopping;

import android.app.Activity;
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
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_homepage);
        setTitle("Home");

        Bundle extras = getIntent().getExtras();
        id = extras != null ? extras.getInt("id") : 0;

        RecyclerView rv = findViewById(R.id.rv_vendor_layout);

        VendorHomepageThread vendorHomepageThread = new
                VendorHomepageThread(this, rv, id);
        vendorHomepageThread.start();
    }

    public void addProduct(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}

class VendorHomepageThread extends Thread {
    private Activity current;
    private RecyclerView rv;
    private int id;

    VendorHomepageThread(Activity current, RecyclerView rv, int id){
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
        ArrayList<Item> productList = new ArrayList<>();
        Item item;
        c = productDAO.getProductsByVendorId(id);
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                String name = c.getString(c.getColumnIndex("name"));
                String category = c.getString(c.getColumnIndex("category"));
                float price = c.getFloat(c.getColumnIndex("cost"));
                int qty = c.getInt(c.getColumnIndex("quantity"));
                item = new Item(name, category, price, qty, 0);
                productList.add(item);
                c.moveToNext();
            }
        }
        c.close();

        Adapters.RVVendorAdapter myAdapter = new Adapters.RVVendorAdapter(current, productList);
        rv.setLayoutManager(new GridLayoutManager(current, 3));
        rv.setAdapter(myAdapter);
    }
}
