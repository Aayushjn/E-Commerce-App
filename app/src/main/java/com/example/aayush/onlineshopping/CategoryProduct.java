package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import dbs.DAOs;
import dbs.Databases;
import misc.Adapters;
import misc.Cart;
import misc.Item;

public class CategoryProduct extends AppCompatActivity {

    private Cart cart;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        int categoryFlag = extras.getInt("categoryFlag");
        int id = extras.getInt("id");

        if (extras.getSerializable("cart") != null) {
            cart = (Cart) extras.getSerializable("cart");
        }

        RecyclerView rv = findViewById(R.id.rv_item_display_layout);

        DBThread dbThread = new DBThread(categoryFlag, this, cart, rv, id);
        dbThread.start();
    }
}

class DBThread extends Thread{
    private int categoryFlag;
    private Activity current;
    private Cart cart;
    private RecyclerView rv;
    private int id;

    DBThread(int categoryFlag, Activity current, Cart cart, RecyclerView rv, int id){
        this.categoryFlag = categoryFlag;
        this.current = current;
        this.cart = cart;
        this.rv = rv;
        this.id = id;
    }

    @Override
    public void run() {
        ArrayList<Item> items_list = new ArrayList<>();
        Cursor c;
        Item item;

        Databases.ProductDatabase db =
                Databases.ProductDatabase.getProductDatabase(current);
        DAOs.ProductDAO dao;
        dao = db.productDAO();

        // TODO: Figure out images
        switch(categoryFlag){
            case 1:
                c = dao.getProductsByCategory("Clothing");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            case 2:
                c = dao.getProductsByCategory("Books");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            case 3:
                c = dao.getProductsByCategory("Appliances");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            case 4:
                c = dao.getProductsByCategory("Electronics");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            case 5:
                c = dao.getProductsByCategory("Furniture");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            case 6:
                c = dao.getProductsByCategory("Food");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                break;
            default:
                Log.e("WTF", "How did this happen?!?!");
        }


        Adapters.RecyclerViewAdapter myAdapter = new Adapters.RecyclerViewAdapter(current,
                items_list, cart, id);
        rv.setLayoutManager(new GridLayoutManager(current, 3));
        rv.setAdapter(myAdapter);
    }
}
