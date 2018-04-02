package com.example.aayush.onlineshopping;

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
    ArrayList<Item> items_list;
    final Bundle extras = getIntent().getExtras();

    final Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        items_list = new ArrayList<>();
        Cursor c;

        assert extras != null;
        int categoryFlag = extras.getInt("categoryFlag");

        Databases.ProductDatabase db =
                Databases.ProductDatabase.getProductDatabase(getApplicationContext());
        DAOs.ProductDAO dao;
        dao = db.productDAO();

        Item item;

        // TODO: Figure out images
        switch(categoryFlag){
            case 1:
                c = dao.getProductsByCategory("Clothing");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Clothing");
            break;
            case 2:
                c = dao.getProductsByCategory("Books");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Books");
            break;
            case 3:
                c = dao.getProductsByCategory("Appliances");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Appliances");
            break;
            case 4:
                c = dao.getProductsByCategory("Electronics");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Electronics");
            break;
            case 5:
                c = dao.getProductsByCategory("Furniture");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Furniture");
            break;
            case 6:
                c = dao.getProductsByCategory("Food");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                setTitle("Food");
            break;
            default:
                Log.e("WTF", "How did this happen?!?!");
        }
        RecyclerView rv = findViewById(R.id.rv_item_display_layout);
        Adapters.RecyclerViewAdapter myAdapter = new Adapters.RecyclerViewAdapter(this,
                items_list, cart);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(myAdapter);
    }
}
