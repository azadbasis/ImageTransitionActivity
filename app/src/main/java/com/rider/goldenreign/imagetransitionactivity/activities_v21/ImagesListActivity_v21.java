package com.rider.goldenreign.imagetransitionactivity.activities_v21;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rider.goldenreign.imagetransitionactivity.ImageFilesCreateLoader;
import com.rider.goldenreign.imagetransitionactivity.R;
import com.rider.goldenreign.imagetransitionactivity.activities.ImagesListActivity;
import com.rider.goldenreign.imagetransitionactivity.adapter.Image;
import com.rider.goldenreign.imagetransitionactivity.adapter.ImagesAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagesListActivity_v21 extends AppCompatActivity implements ImagesAdapter.ImagesAdapterCallback {

    private static final String TAG = ImagesListActivity_v21.class.getSimpleName();

    private final List<Image> mImagesList = new ArrayList<>();

    private static final int SPAN_COUNT = 2;

    private Picasso mImageDownloader;
    private RecyclerView mRecyclerView;
    private ImagesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_list);

        mImageDownloader = Picasso.with(this);

        mAdapter = new ImagesAdapter(this, mImagesList, mImageDownloader, SPAN_COUNT);

        getLoaderManager().initLoader(0, null, new ImageFilesCreateLoader(this, new ImageFilesCreateLoader.LoadFinishedCallback() {
            @Override
            public void onLoadFinished(List<Image> imagesList) {
                mImagesList.addAll(imagesList);
                mAdapter.notifyDataSetChanged();
            }
        })).forceLoad();

        mRecyclerView = (RecyclerView) findViewById(R.id.accounts_recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mRecyclerView.setAdapter(mAdapter);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("List Activity Lollipop");

        Button switchButton = (Button) findViewById(R.id.switch_to);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            switchButton.setVisibility(View.VISIBLE);
            switchButton.setText("Switch to Ice Cream Sandwich List Activity");
            switchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ImagesListActivity_v21.this.finish();

                    Intent startActivityIntent = new Intent(ImagesListActivity_v21.this, ImagesListActivity.class);
                    startActivity(startActivityIntent);

                }
            });
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void enterImageDetails(String sharedImageTransitionName, File imageFile, ImageView image, Image imageModel) {
        ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(this, image, sharedImageTransitionName);

        getWindow().setSharedElementEnterTransition(new ChangeImageTransform(this, null));

        Intent startIntent = ImageDetailsActivity_v21.getStartIntent(this, sharedImageTransitionName, imageFile);
        startActivity(startIntent, activityOptions.toBundle());
    }
}