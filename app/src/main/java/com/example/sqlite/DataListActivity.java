package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDBHelper userDBHelper;
    Cursor cursor;
    DataListAdapter dataListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        listView = (ListView) findViewById(R.id.list_view);
        dataListAdapter = new DataListAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(dataListAdapter);

        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        cursor = userDBHelper.getInformations(sqLiteDatabase);



        if(cursor.moveToFirst()){
            do{
                String name, mobile, email;
                name = cursor.getString(0);
                mobile = cursor.getString(1);
                email = cursor.getString(2);
                //pass this data to dataProvider class to get object

                DataProvider dataProvider = new DataProvider(name,mobile,email);
                dataListAdapter.add(dataProvider);

            }while(cursor.moveToNext());
        }

    }
}