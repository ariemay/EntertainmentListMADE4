package com.ariemay.entertainmentlistmade3.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TVDetails extends AppCompatActivity {

    public static String EXTRA_DATA = "extras";

    TextView name, startDate, endDate, rating, description;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetails);

        TV tv = getIntent().getParcelableExtra(EXTRA_DATA);

        name = findViewById(R.id.detail_tv_name);
        startDate = findViewById(R.id.start);
        endDate = findViewById(R.id.end);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.description);
        poster = findViewById(R.id.detail_tv_poster);

        Glide.with(getApplicationContext())
                .load(tv.getBackdrop_path())
                .apply(new RequestOptions()
                        .override(150,150))
                .into(poster);

        name.setText(tv.getName());
        startDate.setText(String.valueOf(tv.getFirst_air_date()));
        rating.setText(tv.getVote_average());
        description.setText(tv.getOverview());
    }

}
