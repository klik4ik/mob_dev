package com.example.isinyavskiy.filmsserials;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SerialsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SerialsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ArrayList<Film> films;
    SQLiteDatabase db;
    FilmsAdapter filmsAdapter;

    public SerialsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serials, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Сериалы");
        db = getActivity().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM serials", null);
        Log.d("ad", "!!!!!"+cursor.getCount());
        cursor.moveToFirst();
        films = new ArrayList<Film>();
        do{
            films.add(new Film(cursor.getString(0), cursor.getString(2), cursor.getString(3), cursor.getFloat(4), cursor.getString(1)));
        } while(cursor.moveToNext());
        Log.d("qqqqq", films.size()+" asdd");
        filmsAdapter = new FilmsAdapter(getActivity(), films);
        ListView lv = (ListView) view.findViewById(R.id.serialList);
        lv.setAdapter(filmsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film curFilm = films.get(position);
                Intent intent = new Intent(getActivity(), FilmsDetailActivity.class);
                intent.putExtra("title", curFilm.title);
                intent.putExtra("description", curFilm.description);
                intent.putExtra("rating", curFilm.rating+"/"+5);
                intent.putExtra("img", curFilm.img);
                intent.putExtra("country", curFilm.country);
                intent.putExtra("type", "serial");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
