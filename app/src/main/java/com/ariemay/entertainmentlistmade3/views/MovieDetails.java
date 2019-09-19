package com.ariemay.entertainmentlistmade3.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.databases.ShowHelper;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetails extends AppCompatActivity {

    public static String EXTRA_DATA = "extras";

    TextView name, genre, date, actor, description;
    ImageView poster;
    ImageButton favoriteButton;


    private Movies movies;
    private SQLiteDatabase database;
    private ShowHelper showHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movies movies = getIntent().getParcelableExtra(EXTRA_DATA);

        poster = findViewById(R.id.detail_movie_poster);
        name = findViewById(R.id.detail_movie_name);
        date = findViewById(R.id.detail_date);
        description = findViewById(R.id.description);
        favoriteButton = findViewById(R.id.favoriteButton);

        Glide.with(getApplicationContext())
                .load(movies.getBackdrop_path())
                .apply(new RequestOptions()
                        .override(150,150))
                .into(poster);

        name.setText(movies.getTitle());
        date.setText(movies.getRelease_date());
        description.setText(movies.getOverview());

        if (Exist(movies.getTitle())){
            favoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        saveFavorite();
                        Snackbar.make(buttonView,"Added to Favorite",Snackbar.LENGTH_SHORT).show();
                    }else {
                        int movie_id = movie.getId();
                        movieHelper = new MovieHelper(MovieDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteMovie(movie_id);
                        movieHelper.close();
                        Snackbar.make(buttonView,"Removed from Favorite",Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Added to Favorite", Snackbar.LENGTH_SHORT).show();
                    } else {
                        int movie_id = movie.getId();
                        movieHelper = new MovieHelper(MovieDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteMovie(movie_id);
                        movieHelper.close();
                        Snackbar.make(buttonView, "Removed from Favorite", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveFavorite() {
        movieHelper=new MovieHelper(this);
        String thumbnail=movie.getPoster();
        String movieName=movie.getName();
        String synopsis=movie.getOverview();
        Double rating=movie.getRate();

        movie.setName(movieName);
        movie.setOverview(synopsis);
        movie.setPoster(thumbnail);
        movie.setRate(rating);

        movieHelper.open();
        movieHelper.insertMovie(movie);
        movieHelper.close();
    }

    public boolean Exist(String item){
        String pilih= DatabaseContract.MovieColumns.TITLE+" =?";
        String[] pilihArg={item};
        String limit="1";
        movieHelper= new MovieHelper(this);
        movieHelper.open();
        DatabaseHelper dataBaseHelper= new DatabaseHelper(MovieDetailActivity.this);
        database = dataBaseHelper.getWritableDatabase();
        Cursor cursor= database.query(TABLE_MOVIE,null,pilih,pilihArg,null,null,null,limit);
        boolean exists;
        exists=(cursor.getCount() > 0);
        cursor.close();
        movieHelper.close();
        return exists;
    }
}
