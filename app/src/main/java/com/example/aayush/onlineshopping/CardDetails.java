package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.PasswordOps;

public class CardDetails extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
    }

    public void submitCard(View view) throws NoSuchProviderException, NoSuchAlgorithmException {
        Databases.PaymentDatabase paymentDB;
        DAOs.PaymentDAO payAcc;
        Context current = getApplicationContext();
        paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        payAcc = paymentDB.paymentDAO();

        int id = extras.getInt("id");

        String pin1, pin2, errorString = null;
        long cardNo;

        cardNo = Long.parseLong(((EditText)findViewById(R.id.cardNo)).getText().toString());
        pin1 = ((EditText)findViewById(R.id.pin)).getText().toString();
        pin2 = ((EditText)findViewById(R.id.pinRepeat)).getText().toString();

        if(Long.valueOf(cardNo) == null || pin1 == null || pin2 == null){
            errorString = "Fields Cannot be null";
        }
        else if(!pin1.equals(pin2)){
            errorString = "Passwords do not Match";
        }
        else{
            byte[] salt = PasswordOps.getSalt();
            String hashedPIN = PasswordOps.getSecurePassword(pin1, salt);
            Entities.PaymentEntity cardDet = new Entities.PaymentEntity(id, cardNo, hashedPIN,
                    10000, new String(salt));
            payAcc.insertCard(cardDet);
            Intent move = new Intent(this, WelcomeScreen.class);
            startActivity(move);
        }
        Context context = getApplicationContext();
        Toast.makeText(context, errorString, Toast.LENGTH_LONG).show();
    }
}
