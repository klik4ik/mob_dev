package com.example.isinyavskiy.filmsserials;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();
        session = new Session(this);
        if(session.getusename()!=""){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (login TEXT, password TEXT, FIO TEXT, bDay TEXT)");
        db.execSQL("INSERT OR REPLACE INTO users(login, password) VALUES('admin', 'admin')");
    }

    public void login(View v){
        EditText login = (EditText) findViewById(R.id.rLoginInput);
        EditText password = (EditText) findViewById(R.id.rPasswordInput);
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE login = '"+login.getText()+"' AND password = '"+password.getText()+"'", null);
        Toast toast;
        if(cursor.getCount()!=0){
            toast = Toast.makeText(getApplicationContext(), "Успешная авторизация!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            session.setusename(login.getText()+"");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else{
            toast = Toast.makeText(getApplicationContext(), "Не правильный логин или пароль!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void registerClick(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
