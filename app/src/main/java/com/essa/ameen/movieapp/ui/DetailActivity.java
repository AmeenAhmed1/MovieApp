package com.essa.ameen.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.essa.ameen.movieapp.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    
    private TextView mTitle, mDate, mRate, mOverView;
    private ImageView mImage;
    private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.i(TAG, "onCreate: ");
        
        //Referances to the layout
        mTitle = findViewById(R.id.txtTitleDetail);
        mDate = findViewById(R.id.txtDateDetail);
        mRate = findViewById(R.id.txtRateDetail);
        mOverView = findViewById(R.id.txtOverViewDetail);
        mImage = findViewById(R.id.imageDetail);
        mImageButton = findViewById(R.id.imageBackButton);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gettingMovieData();

    }


    private void gettingMovieData(){

        Log.i(TAG, "gettingMovieData: ");

        //Get the data from the previous activity
        Intent mDataIntent = getIntent();
        String movieTitle = mDataIntent.getStringExtra("title");
        String movieDate = mDataIntent.getStringExtra("date");
        String movieRate = mDataIntent.getStringExtra("rate");
        String movieImage = mDataIntent.getStringExtra("image");
        String movieOverView = mDataIntent.getStringExtra("over_view");


        //Load the image
        Picasso.with(this)
                .load(movieImage)
                .into(mImage);

        mTitle.setText(movieTitle);
        mDate.setText("Release date: " + movieDate);
        mRate.setText("Average rate: " + movieRate);
        mOverView.setText(movieOverView);

    }
}
