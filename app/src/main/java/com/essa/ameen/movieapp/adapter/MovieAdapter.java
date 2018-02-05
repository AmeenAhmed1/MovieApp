package com.essa.ameen.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essa.ameen.movieapp.R;
import com.essa.ameen.movieapp.model.MovieListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ameen on 30-Jan-18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<MovieListItem> x;
    Context mContext;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //To get the current item in a list
        MovieListItem mItems = x.get(position);

        holder.txtName.setText(mItems.getMovie_title());
        //holder.txtReleaseDate.setText(mItems.getRelease_date());
        holder.txtReleaseDate.setText(mItems.getMovie_over_view());

        //Load the image from web and load it into imageView
        Picasso.with(mContext)
                .load(mItems.getImage_url())
                .into(holder.movieImage);

        //To set a listner when clieck an item
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicke: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return x.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtReleaseDate;
        ImageView movieImage;
        LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtMovieName);
            txtReleaseDate = itemView.findViewById(R.id.txtMovieReleaseDate);
            movieImage = itemView.findViewById(R.id.movieImage);
            mLinearLayout = itemView.findViewById(R.id.movieLinearLayout);
        }
    }
}
