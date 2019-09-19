package com.ariemay.entertainmentlistmade3.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.models.TV;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.BACKDROP_PATH_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.ID_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.OVERVIEW_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.POSTER_PATH_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.RELEASE_DATE_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.TITLE_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.MovieColumns.VOTE_AVERAGE_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.BACKDROP_PATH_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.FIRST_AIR_DATE_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.ID_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.NAME_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.OVERVIEW_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.POSTER_PATH_TV;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TvColumns.VOTE_AVERAGE_TV;

public class ShowHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static final String DATABASE_TABLE1 = TABLE_TV;
    private static DBHelper dbHelper;
    private static ShowHelper INSTANCE;
    private static SQLiteDatabase database;

    public ShowHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static ShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movies movies;
        if (cursor.getCount() > 0) {
            do {
                movies = new Movies();
                movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                movies.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE)));
                movies.setVote_average(cursor.getInt(cursor.getColumnIndexOrThrow(VOTE_AVERAGE_MOVIE)));
                movies.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE_MOVIE)));
                movies.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_MOVIE)));
                movies.setBackdrop_path(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH_MOVIE)));
                arrayList.add(movies);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<TV> getAllTv() {
        ArrayList<TV> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE1, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TV movies;
        if (cursor.getCount() > 0) {
            do {
                movies = new TV();
                movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TV)));
                movies.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME_TV)));
                movies.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(FIRST_AIR_DATE_TV)));
                movies.setVote_average(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE_TV)));
                movies.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW_TV)));
                movies.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_TV)));
                movies.setBackdrop_path(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH_TV)));
                arrayList.add(movies);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movies movies) {
        ContentValues conts = new ContentValues();
        conts.put(ID_MOVIE, movies.getId());
        conts.put(TITLE_MOVIE, movies.getTitle());
        conts.put(VOTE_AVERAGE_MOVIE, movies.getVote_average());
        conts.put(OVERVIEW_MOVIE, movies.getOverview());
        conts.put(RELEASE_DATE_MOVIE, movies.getRelease_date());
        conts.put(POSTER_PATH_MOVIE, movies.getPoster_path());
        conts.put(BACKDROP_PATH_MOVIE, movies.getBackdrop_path());
        return database.insert(DATABASE_TABLE, null, conts);
    }
    public long insertTv(TV movies) {
        ContentValues conts = new ContentValues();
        conts.put(ID_TV, movies.getId());
        conts.put(NAME_TV, movies.getName());
        conts.put(FIRST_AIR_DATE_TV, movies.getFirst_air_date());
        conts.put(VOTE_AVERAGE_TV, movies.getVote_average());
        conts.put(OVERVIEW_TV, movies.getOverview());
        conts.put(POSTER_PATH_TV, movies.getPoster_path());
        conts.put(BACKDROP_PATH_TV, movies.getBackdrop_path());
        return database.insert(DATABASE_TABLE1, null, conts);
    }

    public int updateMovie(Movies movies) {
        ContentValues conts = new ContentValues();
        conts.put(ID_MOVIE, movies.getId());
        conts.put(TITLE_MOVIE, movies.getTitle());
        conts.put(VOTE_AVERAGE_MOVIE, movies.getVote_average());
        conts.put(OVERVIEW_MOVIE, movies.getOverview());
        conts.put(RELEASE_DATE_MOVIE, movies.getRelease_date());
        conts.put(POSTER_PATH_MOVIE, movies.getPoster_path());
        conts.put(BACKDROP_PATH_MOVIE, movies.getBackdrop_path());
        return database.update(DATABASE_TABLE, conts, ID_MOVIE + "= '" + movies.getId() + "'", null);
    }
    public int updateTv(TV movies) {
        ContentValues conts = new ContentValues();
        conts.put(ID_TV, movies.getId());
        conts.put(NAME_TV, movies.getName());
        conts.put(FIRST_AIR_DATE_TV, movies.getFirst_air_date());
        conts.put(VOTE_AVERAGE_TV, movies.getVote_average());
        conts.put(OVERVIEW_TV, movies.getOverview());
        conts.put(POSTER_PATH_TV, movies.getPoster_path());
        conts.put(BACKDROP_PATH_TV, movies.getBackdrop_path());
        return database.update(DATABASE_TABLE1, conts, ID_TV + "= '" + movies.getId() + "'", null);
    }

    public int deleteMovie(int id) {
        return database.delete(TABLE_MOVIE, ID_MOVIE + " = '" + id + "'", null);
    }
    public int deleteTv(int id) {
        return database.delete(TABLE_TV, ID_TV + " = '" + id + "'", null);
    }
}
