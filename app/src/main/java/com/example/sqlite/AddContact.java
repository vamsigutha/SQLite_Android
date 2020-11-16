package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText contactName, contactMob, contactEmail;
    Context context=this;
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
        setContentView(R.layout.activity_add_contact);

        contactName = (EditText) findViewById(R.id.contact_name);
        contactMob = (EditText) findViewById(R.id.contact_mobile);
        contactEmail = (EditText) findViewById(R.id.contact_email);

    }

    public void saveContact(View view){
        String name = contactName.getText().toString();
        String mobile = contactMob.getText().toString();
        String email = contactEmail.getText().toString();
        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        userDBHelper.addInformations(name,mobile,email,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"data saved",Toast.LENGTH_LONG).show();
        userDBHelper.close();
    }
}












