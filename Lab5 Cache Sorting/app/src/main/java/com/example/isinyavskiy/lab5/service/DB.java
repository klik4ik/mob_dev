package com.example.isinyavskiy.lab5.service;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.isinyavskiy.lab5.model.Character;
import com.example.isinyavskiy.lab5.model.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CharactersLocal";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE characters (name TEXT, description TEXT, thumb_path TEXT, thumb_extension TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS characters");

        onCreate(db);
    }
    public void clearDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS characters");
        onCreate(db);
    }

    public void addCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", character.getName());
        values.put("description", character.getDescription());
        values.put("thumb_path", character.getThumbnail().getPath());
        values.put("thumb_extension", character.getThumbnail().getExtension());

        db.insert("characters", null, values);
        db.close();
    }


    public ArrayList<Character> getAllCharacters() {
        ArrayList<Character> contactList = new ArrayList<Character>();
        String selectQuery = "SELECT  * FROM characters";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Character contact = new Character();
                contact.setName(cursor.getString(0));
                contact.setDescription(cursor.getString(1));

                Image image = new Image();
                image.setPath(cursor.getString(2));
                image.setExtension(cursor.getString(3));

                contact.setThumbnail(image);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

}