package com.example.isinyavskiy.lab5.view;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isinyavskiy.lab5.R;
import com.example.isinyavskiy.lab5.model.Character;
import com.squareup.picasso.Picasso;



public class MarvelAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Character> objects;

    MarvelAdapter(Context context, ArrayList<Character> products) {
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

        Character c = getCharacter(position);
        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.charName)).setText(c.getName());
        if(c.getDescription().equals(""))
            ((TextView) view.findViewById(R.id.charDescription)).setText("Герой без описания");
        else
        ((TextView) view.findViewById(R.id.charDescription)).setText(c.getDescription());
        ImageView img = view.findViewById(R.id.charIMG);
        Picasso.get().load(c.getThumbnail().getPath()+"."+c.getThumbnail().getExtension()).into(img);

        return view;
    }

    // товар по позиции
    Character getCharacter(int position) {
        return ((Character) getItem(position));
    }


}