package com.rider.goldenreign.imagetransitionactivity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.rider.goldenreign.imagetransitionactivity.R;

/**
 * Created by goldenreign on 4/3/2018.
 */

public class ImagesViewHolder extends RecyclerView.ViewHolder{

    public final ImageView image;

    public ImagesViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}