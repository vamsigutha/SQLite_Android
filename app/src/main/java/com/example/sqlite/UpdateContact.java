package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {
    EditText searchNameToUpdate, updateName, updateMobile, updateEmail;
    String Name, Mobile, Email,search_name;
    ConstraintLayout constraintLayout;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        searchNameToUpdate = (EditText) findViewById(R.id.search_name_to_update);
        updateName = (EditText)findViewById(R.id.update_name);
        updateMobile = (EditText) findViewById(R.id.update_mobile);
        updateEmail = (EditText) findViewById(R.id.update_email);
        constraintLayout = (ConstraintLayout) findViewById(R.id.innerLayout);

        constraintLayout.setVisibility(View.INVISIBLE);

    }

    public void searchContactButton(View view){
        search_name = searchNameToUpdate.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContact(search_name,sqLiteDatabase);

        if(cursor.moveToFirst()){

            Mobile = cursor.getString(0);
            Email = cursor.getString(1);
            Name = search_name;

            updateName.setText(Name);
            updateMobile.setText(Mobile);
            updateEmail.setText(Email);

            constraintLayout.setVisibility(View.VISIBLE);
        }

    }

    public void updateContactButton(View view){
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        int count = userDBHelper.updateContact(search_name, updateName.getText().toString(),
                updateMobile.getText().toString(), updateEmail.getText().toString(),sqLiteDatabase);
        Toast.makeText(getApplicationContext(),count+" rows are updated",Toast.LENGTH_LONG).show();
        finish();
    }

}