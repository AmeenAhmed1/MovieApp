package com.essa.ameen.movieapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.essa.ameen.movieapp.adapter.MovieAdapter;
import com.essa.ameen.movieapp.model.MovieListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Variables
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    Toolbar mToolBar;


    SwipeRefreshLayout mSwipeRefreshLayout;


    //The movie list from the url
    List<MovieListItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ** Views referances
         */
        mRecyclerView = findViewById(R.id.recycleGrid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mToolBar = findViewById(R.id.toolBar);
        mToolBar.setTitle("Top Movie");

        mToolBar.inflateMenu(R.menu.menu_main);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.exitButton){
                    finish();
                }
                return true;
            }
        });


        //Swipe to refresh layout
        mSwipeRefreshLayout = findViewById(R.id.ref);

        //Get data into the layout
        updateUI();

        //called if screen swaped
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUI();
            }
        });

    }


    public void updateUI() {

        Log.i(TAG, "updateUI: ");

        //to get the data
        new GettingData().execute();
    }



    public class GettingData extends AsyncTask<Void, Void, Void> {

        private static final String TAG = "GettingData";



        private static final String API_KEY = "cf2a0e44ebd0f153e44c7a54007b3f1c";
        private static final String URL_REQUEST = "https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY;

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                //To open the connection with service api
                URL url = new URL(URL_REQUEST);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                //Read the  response from the URL Api
                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                StringBuffer buffer = new StringBuffer();

                String line ="";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                //To Convert the response
                String jsonDataFinal = buffer.toString();
                convertStreamToString(jsonDataFinal);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mAdapter = new MovieAdapter(list , getApplicationContext());
            mRecyclerView.setAdapter(mAdapter);

            mSwipeRefreshLayout.setRefreshing(false);
        }

        //To convert the data from json to strings
        private void convertStreamToString(String finalData) {

            list = new ArrayList<>();

            try {
                //To get the first element
                JSONObject mJsonObject = new JSONObject(finalData);

                JSONArray mJsonArray = mJsonObject.getJSONArray("results");

                //To get every node in the array  list
                for(int i = 0 ; i < mJsonArray.length() ; ++i){

                    //to get the image url
                    //String image_URL = "http://image.tmdb.org/t/p/w185/";

                    //Json formating
                    JSONObject mJsonResult = mJsonArray.getJSONObject(i);
                    String title = mJsonResult.getString("original_title");
                    String overView = mJsonResult.getString("overview");
                    String date = mJsonResult.getString("release_date");
                    String image = mJsonResult.getString("poster_path");
                    String rate = mJsonResult.getString("vote_average");

                    /*Log.i(TAG, "convertStreamToString: Title : " + title);
                    Log.i(TAG, "convertStreamToString: View : " + overView);
                    Log.i(TAG, "convertStreamToString: Date : " + date);*/

                    String finalImageUrl = "http://image.tmdb.org/t/p/w500/" + image;

                    Log.i(TAG, "convertStreamToString: Image url = " + finalImageUrl);

                    MovieListItem x = new MovieListItem(title, overView, date, finalImageUrl, rate);
                    list.add(x);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
