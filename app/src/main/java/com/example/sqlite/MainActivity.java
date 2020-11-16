package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void addContact(View view){
        Intent i = new Intent(this, AddContact.class);
        startActivity(i);
    }

    public void viewContact(View view){
        Intent i = new Intent(this, DataListActivity.class);
        startActivity(i);
    }

    public void searchContact(View view){
        Intent i = new Intent(this, SearchContactActivity.class);
        startActivity(i);
    }

    public void updateContact(View view){
        Intent i = new Intent(this, UpdateContact.class);
        startActivity(i);
    }
}