package com.example.isinyavskiy.lab5.presenter;

import android.widget.Toast;

import com.example.isinyavskiy.lab5.model.Character;
import com.example.isinyavskiy.lab5.model.CharacterDataWrapper;
import com.example.isinyavskiy.lab5.service.MarvelService;
import com.example.isinyavskiy.lab5.view.MarvelView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarvelPresenter {

    public static MarvelPresenter sInstance;

    private MarvelView marvelView;
    private MarvelService marvelService;

    private ArrayList<Character> characters;

    public MarvelPresenter(MarvelView view) {
        this.marvelView = view;

        if (this.marvelService == null) {
            this.marvelService = new MarvelService();
        }
    }

    public void getCharacters() {
        if(characters == null) {
            marvelService
                    .getAPI()
                    .getResults(40)
                    .enqueue(new Callback<CharacterDataWrapper>() {
                        @Override
                        public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                            if(response.body() != null) {
                                ArrayList<Character> chars = response.body().getData().getResults();
                                characters = chars;
                                marvelView.characterReady(chars);
                            }

                        }

                        @Override
                        public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
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
