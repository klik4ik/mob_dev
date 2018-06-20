package com.example.isinyavskiy.filmsserials;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
    }
    public void register(View v){

        EditText login = (EditText) findViewById(R.id.rLoginInput);
        EditText password = (EditText) findViewById(R.id.rPasswordInput);
        EditText FIO = (EditText) findViewById(R.id.rFioInput);
        EditText bDay = (EditText) findViewById(R.id.rBdayInput);
        db.execSQL("INSERT INTO users(login, password, FIO, bDay) VALUES('"+login.getText()+"', '"+password.getText()+"', '"+FIO.getText()+"', '"+bDay.getText()+"')");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Вы успешно зарегистрированы!", Toast.LENGTH_SHORT);
        toast.show();
        startActivity(intent);

    }
}
