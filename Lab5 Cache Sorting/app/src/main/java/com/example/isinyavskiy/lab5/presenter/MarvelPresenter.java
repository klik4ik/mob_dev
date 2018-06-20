package com.example.isinyavskiy.lab5.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isinyavskiy.lab5.model.Character;
import com.example.isinyavskiy.lab5.model.CharacterDataWrapper;
import com.example.isinyavskiy.lab5.service.DB;
import com.example.isinyavskiy.lab5.service.MarvelService;
import com.example.isinyavskiy.lab5.view.MarvelView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarvelPresenter {

    public static MarvelPresenter sInstance;
    public static String sort = "name";
    private MarvelView marvelView;
    private MarvelService marvelService;
    private DB db;


    private ArrayList<Character> characters;

    public MarvelPresenter(MarvelView view) {
        this.marvelView = view;

        if (this.marvelService == null) {
            this.marvelService = new MarvelService();
        }
    }

    public void getCharacters(Context context, String sort) {
        db = new DB(context);
        if(characters == null) {
            marvelService
                    .getAPI()
                    .getResults(5, sort)
                    .enqueue(new Callback<CharacterDataWrapper>() {
                        @Override
                        public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                            if(response.body() != null) {
                                ArrayList<Character> chars = response.body().getData().getResults();
                                characters = chars;
                                marvelView.characterReady(chars);
                                db.clearDB();
                                for(Character ch : chars) {
                                    db.addCharacter(ch);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                            ArrayList<Character> chars = db.getAllCharacters();
                            marvelView.characterReady(chars);
                            marvelView.fail("Нет подключения к сети");
                        }
                    });
        } else {
            marvelView.characterReady(characters);
        }

    }

    public static MarvelPresenter getInstance(MarvelView view) {
        if(sInstance == null) {
            sInstance = new MarvelPresenter(view);
        } else {
            sInstance.marvelView = view;
        }
        return sInstance;
    }
}
