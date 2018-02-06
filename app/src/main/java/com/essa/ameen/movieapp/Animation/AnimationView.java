package com.essa.ameen.movieapp.Animation;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ameen on 06-Feb-18.
 */

public class AnimationView {

    private RecyclerView.ViewHolder holder;
    private boolean goDown;

    public AnimationView(RecyclerView.ViewHolder holder, boolean goDown) {
        this.holder = holder;
        this.goDown = goDown;

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(holder.itemView,
                                            "translationY", goDown ? 250 : -250, 0);

        mObjectAnimator.setDuration(1500);
        mObjectAnimator.start();
    }


}
