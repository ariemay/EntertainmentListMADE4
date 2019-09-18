package com.ariemay.entertainmentlistmade3.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetails extends AppCompatActivity {

    public static String EXTRA_DATA = "extras";

    TextView name, genre, date, actor, description;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movies movies = getIntent().getParcelableExtra(EXTRA_DATA);

        poster = findViewById(R.id.detail_movie_poster);
        name = findViewById(R.id.detail_movie_name);
        date = findViewById(R.id.detail_date);
        description = findViewById(R.id.description);

        Glide.with(getApplicationContext())
                .load(movies.getBackdrop_path())
                .apply(new RequestOptions()
                        .override(150,150))
                .into(poster);

        name.setText(movies.getTitle());
        date.setText(movies.getRelease_date());
        description.setText(movies.getOverview());
    }
}
