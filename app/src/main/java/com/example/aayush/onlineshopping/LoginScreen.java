package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.PasswordOps;

import static xdroid.toaster.Toaster.toast;

public class LoginScreen extends AppCompatActivity {
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setTitle("Login");

        extras = getIntent().getExtras();
    }

    public void loginClick(View view){
        Context current = getApplicationContext();

        int source = extras.getInt("source");

        EditText email = findViewById(R.id.email);
        EditText loginPassword = findViewById(R.id.loginPassword);

        String emailId = email.getText().toString();
        String password = loginPassword.getText().toString();

        if(emailId == null || password == null){
            Toast.makeText(current, "Fields cannot be blank!", Toast.LENGTH_SHORT).show();
        }

        if(source == 1){
            VendorThread vendorThread = new VendorThread(emailId, this, password);
            vendorThread.start();
        }
        else{
            UserThread userThread = new UserThread(emailId, this, password);
            userThread.start();
        }
    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(this, WelcomeScreen.class);
        startActivity(goBack);
    }

    public void registerClick(View view) {
        assert extras != null;
        int source = extras.getInt("source");
        Intent regPage = new Intent(this, RegistrationScreen.class);
        regPage.putExtra("source", source);
        startActivity(regPage);
    }
}

class VendorThread extends Thread {
    private String emailId;
    private Activity current;
    private String password;

    VendorThread(String emailId, Activity current, String password){
        this.emailId = emailId;
        this.current = current;
        this.password = password;
    }

    @Override
    public void run(){
        Databases.VendorDatabase db = Databases.VendorDatabase.getVendorDatabase(current);
        DAOs.VendorDAO vendorAcc;
        vendorAcc = db.vendorDAO();
        Entities.VendorEntity vendor;
        vendor = vendorAcc.getVendorByEmail(emailId);
        if(vendor == null){
            toast(R.string.invalid_format);
        }
        else{
            byte[] salt = vendor.getSalt().getBytes();
            String hashedPW = PasswordOps.getSecurePassword(password, salt);
            if(!vendor.getPassword().equals(hashedPW)){
                toast(R.string.invalid_format);
            }
            else{
                Intent nextPage = new Intent(current, VendorHomepage.class);
                int vendID = vendor.getId();
                nextPage.putExtra("vendID",vendID);
                current.startActivity(nextPage);
            }
        }
    }
}

class UserThread extends Thread {
    private String emailId;
    private Activity current;
    private String password;

    UserThread(String emailId, Activity current, String password){
        this.emailId = emailId;
        this.current = current;
        this.password = password;
    }

    @Override
    public void run(){
        Databases.UserDatabase db = Databases.UserDatabase.getUserDatabase(current);
        DAOs.UserDAO userAcc;
        userAcc = db.userDAO();
        Entities.UserEntity user = userAcc.getUserByEmail(emailId);

        if(user == null){
            toast(R.string.invalid_format);
        }
        else{
            byte[] salt = user.getSalt().getBytes();
            String hashedPW = PasswordOps.getSecurePassword(password, salt);
            if(!user.getPassword().equals(hashedPW)){
                toast(R.string.invalid_format);
            }
            else{
                Intent nextPage = new Intent(current, CategoryPage.class);
                int vendID = user.getId();
                nextPage.putExtra("vendID", vendID);
                current.startActivity(nextPage);
            }
        }
    }
}
