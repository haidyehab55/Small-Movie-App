package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name,Desc;
    Button Add,Show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add = findViewById(R.id.Add);
        Show = findViewById(R.id.Show);
        Name = findViewById(R.id.Name);
        Desc = findViewById(R.id.Desc);
        final MovieDBhelper newMovie = new MovieDBhelper(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMovie.createNewMovie(Name.getText().toString(), Desc.getText().toString());
                Toast.makeText(getApplicationContext(),"Movie Added", Toast.LENGTH_LONG).show();
            }
        });
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AllMovies = new Intent(MainActivity.this,ShowMovie.class);
                startActivity(AllMovies);
            }
        });

    }
}