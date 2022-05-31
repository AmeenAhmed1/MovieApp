//package com.essa.ameen.movieapp.presentation.ui;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.essa.ameen.movieapp.R;
//
//public class NoInternetActivity extends AppCompatActivity {
//
//    Button mButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.no_internet_avtivity);
//
//        mButton = findViewById(R.id.retryButton);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isNetworkAvailable()){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(NoInternetActivity.this, TopRatedActivity.class);
//                            startActivity(intent);
//                            NoInternetActivity.this.finish();
//                        }
//                    }, 1500);
//
//                }else {
//                    Toast.makeText(NoInternetActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//
//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return networkInfo != null && networkInfo.isConnected();
//    }
//}
