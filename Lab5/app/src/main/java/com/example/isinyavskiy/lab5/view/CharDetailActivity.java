package com.example.isinyavskiy.lab5.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isinyavskiy.lab5.R;
import com.squareup.picasso.Picasso;

public class CharDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_detail_view);
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.dCharName)).setText(intent.getStringExtra("name"));
        ((TextView) findViewById(R.id.dCharDescription)).setText(intent.getStringExtra("description"));
        String img = intent.getStringExtra("img");
        ImageView iv = findViewById(R.id.dCharImg);
        Picasso.get().load(img).into(iv);
        setTitle(intent.getStringExtra("name"));
    }
}
