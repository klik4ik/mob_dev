package com.example.isinyavskiy.lab5.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isinyavskiy.lab5.R;
import com.example.isinyavskiy.lab5.model.Character;
import com.example.isinyavskiy.lab5.presenter.MarvelPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MarvelView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MarvelPresenter marvelPresenter = MarvelPresenter.getInstance(this);
        marvelPresenter.getCharacters(this, "name");
    }

    public void fail(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
    @Override

    public void characterReady(final ArrayList<Character> characters) {

        ListView lv = (ListView) findViewById(R.id.characterList);
        MarvelAdapter ma = new MarvelAdapter(this, characters);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Character character = characters.get(position);
                Intent intent = new Intent(MainActivity.this, CharDetailActivity.class);
                intent.putExtra("name", character.getName());
                intent.putExtra("img", character.getThumbnail().getPath()+"."+character.getThumbnail().getExtension());
                intent.putExtra("description", character.getDescription().equals("") ? "Герой не имеет описания"
                        : character.getDescription());
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        MarvelPresenter marvelPresenter = new MarvelPresenter(this);
        switch (item.getItemId())
        {
            case R.id.sortName:
                marvelPresenter.getCharacters(this, "name");
                return true;
            case R.id.sortModify:
                marvelPresenter.getCharacters(this, "modified");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
