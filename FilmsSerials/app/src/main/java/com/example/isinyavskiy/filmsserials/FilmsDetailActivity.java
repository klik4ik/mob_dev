package com.example.isinyavskiy.filmsserials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String img = intent.getStringExtra("img");
        String country = intent.getStringExtra("country");
        String rating = intent.getStringExtra("rating");
        String description = intent.getStringExtra("description");
        String type = intent.getStringExtra("type");
        if(type.equals("serial")) setTitle("Сериал "+title);
        else if(type.equals("film")) setTitle("Фильм "+title);
        ((TextView) findViewById(R.id.dFilmName)).setText(title);
        ((TextView) findViewById(R.id.dFilmCountry)).setText(country);
        ((TextView) findViewById(R.id.dFilmRating)).setText(rating);
        ((TextView) findViewById(R.id.dFilmDescription)).setText(description);
        ImageView iv = findViewById(R.id.dFilmIMG);
        Picasso.get().load(img).into(iv);
    }
}
