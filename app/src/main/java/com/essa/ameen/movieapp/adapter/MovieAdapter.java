package com.essa.ameen.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essa.ameen.movieapp.Animation.AnimationView;
import com.essa.ameen.movieapp.R;
import com.essa.ameen.movieapp.model.MovieListItem;
import com.essa.ameen.movieapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ameen on 30-Jan-18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = "MovieAdapter";

    private List<MovieListItem> x;
    Context mContext;

    //for animation position
    private int previousPosition = 0;

    public MovieAdapter(List<MovieListItem> x, Context mContext) {
        this.x = x;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_layout_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //To get the current item in a list
        final MovieListItem mItems = x.get(position);

        holder.txtName.setText(mItems.getMovie_title());
        //holder.txtReleaseDate.setText(mItems.getRelease_date());
        holder.txtRate.setText(mItems.getMovie_rate());

        //Load the image from web and load it into imageView
        Picasso.with(mContext)
                .load(mItems.getImage_url())
                .into(holder.movieImage);

        //To set a listner when clieck an item
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(mContext, "Clicke: " + position, Toast.LENGTH_SHORT).show();

                //Log.i(TAG, "onClick: OverView" + x.get(position).getMovie_over_view());
                //Log.i(TAG, "onClick: Url" + x.get(position).getImage_url());
                //Log.i(TAG, "onClick: Name" + x.get(position).getMovie_title());
                //Log.i(TAG, "onClick: Date" + x.get(position).getRelease_date());
                //Log.i(TAG, "onClick: OverView" + x.get(position).getMovie_rate());
                getDetailPage(position, holder);

            }
        });


        //to get animation on swiping to down
        if(position > previousPosition)
            new AnimationView(holder, true);
        else
            new AnimationView(holder, false);

        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return x.size();
    }


    public void getDetailPage(int pos, final ViewHolder holder){

        Intent mDetailsIntent = new Intent(mContext, DetailActivity.class);

        //setting a flag cuz this is not an activity
        mDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mDetailsIntent.putExtra("title", x.get(pos).getMovie_title());
        //mDetailsIntent.putExtra("original_title", x.get(pos).);
        mDetailsIntent.putExtra("rate", x.get(pos).getMovie_rate());
        mDetailsIntent.putExtra("date", x.get(pos).getRelease_date());
        mDetailsIntent.putExtra("image",x.get(pos).getImage_url());
        mDetailsIntent.putExtra("over_view", x.get(pos).getMovie_over_view());
        mContext.startActivity(mDetailsIntent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtRate;
        ImageView movieImage;
        LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtMovieName);
            txtRate = itemView.findViewById(R.id.txtRate);
            movieImage = itemView.findViewById(R.id.movieImage);
            mLinearLayout = itemView.findViewById(R.id.movieLinearLayout);
        }
    }
}
