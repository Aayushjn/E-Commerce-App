package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

public class PaymentAuth extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_auth);

        //TODO: Write the yucky on create db opening code I cant do this its too repetitive.
        TextView amt = findViewById(R.id.payAmt);
        amt.setText(cartValue);
        TextView em = findViewById(R.id.userEmail);
        amt.setText(user.emailId);
        TextView card = findViewById(R.id.userCard);
        card.setText(userent.cardNumber);
    }

    public void cancelPay(View view){
        Intent back = new Intent(this,userHome.class);
        startActivity(back);
    }

    public void confirmPay(View view){
        Databases paymentDB, vendorDB;
        DAOs.PaymentDAO payAcc;
        DAOs.VendorDAO vendAcc;
        Context current = getApplicationContext();
        paymentDB = Databases.getPaymentDatabase(current);
        vendorDB = Databases.getVendorDatabase(current);
        payAcc = paymentDB.getDAO();
        vendAcc = vendorDB.getDAO();

        ArrayList<Entities.ProductEntity> products = extras.getParcelableArrayList("cart");
        int userID = extras.getInt("userID");

        Entities.UserEntity user = payAcc.getUserByID(userID);
        Entities.ProductEntity prod;
        Entities.VendorEntity vendor = null;

        int cartQuantity = 0;
        if (products != null) {
            cartQuantity = products.size();
        }
        float userBalance = payAcc.getAmountById(userID);
        float cartValue = extras.getFloat("cartValue");

        if (userBalance < cartValue){
            Intent exit = new Intent(this, UserHomepage.class);
            exit.putExtra("userID", userID);
            Toast.makeText(current,"Insufficient Funds", Toast.LENGTH_LONG).show();
            startActivity(exit);
        }

        float uAmount = 0, vAmount;
        for(int i = 0;i < cartQuantity;i++){
            vAmount = 0;
            prod = products.get(i);
            vendor = vendAcc.getVendorById(prod.getVendId());
            uAmount -= prod.getCost() * prod.getQuantity();
            vAmount += prod.getCost() * prod.getQuantity();
            payAcc.updateAmountById(vAmount, vendor.getId());
        }
        payAcc.updateAmountById(uAmount, userID);

        Toast.makeText(current,"PAYMENT COMPLETE", Toast.LENGTH_LONG).show();
        Intent paymentDone = new Intent(this, UserHomepage.class);
        paymentDone.putExtra("userID", userID);
        startActivity(paymentDone);
    }
}
