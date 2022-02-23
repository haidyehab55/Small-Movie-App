package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ShowMovie extends AppCompatActivity {

    ListView movieList;
    ArrayAdapter<String> MovieAdapter;
    MovieDBhelper movies;
    TextView Desc;
    EditText getDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);
        movieList = (ListView) findViewById(R.id.ListView);
        MovieAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        movieList.setAdapter(MovieAdapter);

        movies = new MovieDBhelper(getApplicationContext());
        Cursor cursor = movies.fetchAllMovies();

        while (!cursor.isAfterLast()) {
            MovieAdapter.add(cursor.getString(0));
            cursor.moveToNext();
        }
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Desc = findViewById(R.id.getDesc);
                getDesc = findViewById(R.id.Desc);
                String description = movies.getMovieDesc(((TextView) view).getText().toString());
                Desc.setText(description);
            }
        });
    }
}