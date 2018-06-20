package com.example.jean.retrofitexample.view;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jean.retrofitexample.model.Character;
import com.squareup.picasso.Picasso;



public class FilmsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Character> objects;

    FilmsAdapter(Context context, ArrayList<Character> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_films, parent, false);
        }

        Film f = getProduct(position);
        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.filmName)).setText(f.title);
        ((TextView) view.findViewById(R.id.filmCountry)).setText("Страна: "+ f.country);
        ((TextView) view.findViewById(R.id.filmRating)).setText("Рейтинг: "+f.rating);
        ImageView img = view.findViewById(R.id.filmIMG);
        Picasso.get().load(f.img).into(img);

        return view;
    }

    // товар по позиции
    Film getProduct(int position) {
        return ((Film) getItem(position));
    }


}