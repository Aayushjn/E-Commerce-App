package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.PasswordOps;

import static xdroid.toaster.Toaster.toast;

public class CardDetails extends AppCompatActivity {
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        extras = getIntent().getExtras();
    }

    public void submitCard(View view) {
        assert extras != null;
        int id = extras.getInt("id");

        EditText cardText = findViewById(R.id.cardNo);
        EditText pin1Text = findViewById(R.id.pin);
        EditText pin2Text = findViewById(R.id.pinRepeat);

        CardThread cardThread = new CardThread(id, this, cardText, pin1Text,
                pin2Text);
        cardThread.start();
    }
}

class CardThread extends Thread {
    private int id;
    private Activity current;
    private EditText cardText;
    private EditText pin1Text;
    private EditText pin2Text;

    CardThread(int id, Activity current, EditText cardText, EditText pin1Text, EditText pin2Text){
        this.id = id;
        this.current = current;
        this.cardText = cardText;
        this.pin1Text = pin1Text;
        this.pin2Text = pin2Text;
    }

    @Override
    public void run() {
        Databases.PaymentDatabase paymentDB;
        DAOs.PaymentDAO payAcc;
        paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        payAcc = paymentDB.paymentDAO();

        String pin1, pin2;
        long cardNo;

        cardNo = Long.parseLong(cardText.getText().toString());
        pin1 = pin1Text.getText().toString();
        pin2 = pin2Text.getText().toString();

        if((Long.toString(cardNo) == null) || (pin1 == null) || (pin2 == null)){
            toast("Fields Cannot be null");
        }
        else if(!pin1.equals(pin2)){
            toast("Passwords do not Match");
        }
        else{
            byte[] salt = new byte[0];
            try {
                salt = PasswordOps.getSalt();
            } catch (NoSuchAlgorithmException e) {
                Log.e("exception", "NoSuchAlgorithm");
            } catch (NoSuchProviderException e) {
                Log.e("exception", "NoSuchProvider");
            }
            String hashedPIN = PasswordOps.getSecurePassword(pin1, salt);
            Entities.PaymentEntity cardDet = new Entities.PaymentEntity(id, cardNo, hashedPIN,
                    100000, new String(salt));
            toast("Account added");
            payAcc.insertCard(cardDet);
            Intent move = new Intent(current, WelcomeScreen.class);
            current.startActivity(move);
        }
    }
}
