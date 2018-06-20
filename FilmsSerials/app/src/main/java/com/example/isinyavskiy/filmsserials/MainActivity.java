package com.example.isinyavskiy.filmsserials;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private FilmsFragment filmsFragment;
    private SerialsFragment serialsFragment;
    private ProfileFragment profileFragment;
    ArrayList<Film> films;
    SQLiteDatabase db;
    FilmsAdapter filmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav =  (BottomNavigationView) findViewById(R.id.main_nav);

        db = this.openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS films (name TEXT, description TEXT, img TEXT, country TEXT, rating REAL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS serials (name TEXT, description TEXT, img TEXT, country TEXT, rating REAL)");

        db.execSQL("DELETE FROM films");
        db.execSQL("DELETE FROM serials");
        db.execSQL("INSERT INTO serials(name, description, img, country, rating) VALUES('Шерлок', '«Ше́рлок», также известен как «Шерлок Холмс» (англ. Sherlock) — британский телесериал компании Hartswood Films, снятый для BBC Wales', 'https://vignette.wikia.nocookie.net/sherlock/images/2/2c/%D0%A8%D0%B5%D1%80%D0%BB%D0%BE%D0%BA_%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9_%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80.jpeg/revision/latest?cb=20151015231814&path-prefix=ru', 'Великобритания', 4.9)");
        db.execSQL("INSERT INTO serials(name, description, img, country, rating) VALUES('Ходячие мертвецы', 'Сериал, создатели которого уже не знают, что снимать.', 'https://static.lostfilm.tv/Images/134/Posters/shmoster_s8.jpg', 'США', 3.6)");
        for(int i=1; i<10; i+=2) {
            db.execSQL("INSERT INTO films(name, description, img, country, rating) VALUES('Мстители "+i+"', 'Описание Мстителей', 'https://st.kp.yandex.net/im/poster/1/9/3/kinopoisk.ru-The-Avengers-1935824.jpg', 'США', 4.3)");
            db.execSQL("INSERT INTO films(name, description, img, country, rating) VALUES('Мстители "+(i+1)+"', 'Описание Мстителей', 'https://st.kp.yandex.net/images/film_iphone/iphone360_679830.jpg', 'США', 3.9)");
        }
         filmsFragment = new FilmsFragment();
        serialsFragment = new SerialsFragment();
        profileFragment = new ProfileFragment();

        setFragment(filmsFragment);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_films:
                        setFragment(filmsFragment);
                        return true;
                    case R.id.menu_serials:
                        setFragment(serialsFragment);
                        return true;
                    case R.id.menu_profile:
                        setFragment(profileFragment);
                        return true;
                    default: return false;
                }
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}