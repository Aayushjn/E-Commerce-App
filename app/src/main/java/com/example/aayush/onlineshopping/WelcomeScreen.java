package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void vendorClick(View view){
        Intent vendorIntent=new Intent(this, LoginScreen.class);
        vendorIntent.putExtra("source",1);
        startActivity(vendorIntent);
    }

    public void customerClick(View view) {
        //TODO:get information for customer login page
        Intent customerIntent = new Intent(this, LoginScreen.class);
        customerIntent.putExtra("source",0);
        startActivity(customerIntent);
    }
}
