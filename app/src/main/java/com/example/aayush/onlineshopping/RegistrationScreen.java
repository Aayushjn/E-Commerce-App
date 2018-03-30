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

public class RegistrationScreen extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(this, WelcomeScreen.class);
        startActivity(goBack);
    }

    public void registerClick(View view) throws NoSuchProviderException, NoSuchAlgorithmException {
        int source = extras.getInt("source");
        Context current = getApplicationContext();

        String name, pw1, pw2, email, address, errorString;
        name = ((EditText)findViewById(R.id.name)).getText().toString();
        pw1 = ((EditText)findViewById(R.id.vendPW1)).getText().toString();
        pw2 = ((EditText)findViewById(R.id.vendPW2)).getText().toString();
        email = ((EditText)findViewById(R.id.email)).getText().toString();
        address = ((EditText)findViewById(R.id.address)).getText().toString();
        errorString = null;

        if(name == null || pw1 == null|| pw2 == null || email == null || address == null){
            errorString = "Fields Cannot be null";
        }

        if(source == 1){
            DAOs.VendorDAO vendorAcc;
            Databases.VendorDatabase db = Databases.VendorDatabase.getVendorDatabase(current);
            vendorAcc = db.vendorDAO();
            Entities.VendorEntity vendor = null;

            if(vendorAcc.isUnique(email) != 1){
                errorString = "Email must be unique";
            }
            else if(!pw1.equals(pw2)){
                errorString = "Passwords do not Match";
            }
            else{
                byte[] salt = PasswordOps.getSalt();
                String hashedPW = PasswordOps.getSecurePassword(pw1,salt);
                vendor.setName(name);
                vendor.setAddress(address);
                vendor.setEmailId(email);
                vendor.setPassword(hashedPW);
                vendor.setSalt(new String(salt));
                int id = vendorAcc.insertVendor(vendor);
                Intent moveOn = new Intent(this, CardDetails.class);
                moveOn.putExtra("id", id);
                startActivity(moveOn);
            }
        }
        else{
            DAOs.UserDAO userAcc;
            Databases.UserDatabase db = Databases.UserDatabase.getUserDatabase(current);
            userAcc = db.userDAO();
            Entities.UserEntity user = null;

            if(userAcc.isUnique(email) != 1){
                errorString = "Email must be unique";
            }
            else if(!pw1.equals(pw2)){
                errorString = "Passwords do not Match";
            }
            else{
                byte[] salt = PasswordOps.getSalt();
                String hashedPW = PasswordOps.getSecurePassword(pw1,salt);
                user.setName(name);
                user.setAddress(address);
                user.setEmailId(email);
                user.setPassword(hashedPW);
                user.setSalt(new String(salt));
                int id = userAcc.insertUser(user);
                Intent moveOn = new Intent(this, CardDetails.class);
                moveOn.putExtra("id", id);
                startActivity(moveOn);
            }
        }

        Toast.makeText(current,errorString,Toast.LENGTH_LONG).show();
    }
}
