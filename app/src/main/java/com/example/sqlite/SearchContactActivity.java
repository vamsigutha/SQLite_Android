package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContactActivity extends AppCompatActivity {

    TextView displayMobile, displayEmail;
    EditText searchName;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String searchByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        displayMobile = (TextView) findViewById(R.id.display_mobile);
        displayEmail = (TextView) findViewById(R.id.display_email);
        searchName = (EditText) findViewById(R.id.search_name);


    }

    public void searchButton(View view){
        searchByName = searchName.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor= userDBHelper.getContact(searchByName, sqLiteDatabase);
        if(cursor.moveToFirst()){
            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);
            displayMobile.setText(MOBILE);
            displayEmail.setText(EMAIL);
        }
    }

    public void deleteContact(View view){
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        userDBHelper.deleteContact(searchByName, sqLiteDatabase);
        Toast.makeText(getApplicationContext(),"contact deleted",Toast.LENGTH_LONG).show();
    }
}