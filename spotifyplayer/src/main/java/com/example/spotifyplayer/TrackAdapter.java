package com.example.spotifyplayer;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spotifyplayer.models.Images;
import com.example.spotifyplayer.models.Item;
import com.example.spotifyplayer.models.Track;

import java.io.IOException;
import java.util.List;

/**
 * Created by Luillo on 04/10/16.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    private List<Item> trackList;
    private Context context;
    private MediaPlayer mediaPlayer;

    public TrackAdapter(List<Item> trackList) {
        this.trackList = trackList;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        TrackViewHolder viewHolder = new TrackViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Item itemTrack = trackList.get(position);
        holder.bindTrack(itemTrack);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }


    public class TrackViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTrack;
        private TextView tvTrackName;
        private ImageView imgArrow;

        public TrackViewHolder(View itemView) {
            super(itemView);

            imgTrack = (ImageView) itemView.findViewById(R.id.item_service_img_profile);
            tvTrackName = (TextView) itemView.findViewById(R.id.item_tv_trackName);
            imgArrow = (ImageView) itemView.findViewById(R.id.item_arrow);

        }

        public void bindTrack(final Item itemTrack) {
            tvTrackName.setText(itemTrack.getName());

            List<Images> images = itemTrack.getAlbum().getImages();
            if (images.size() > 0) {
                String imageUrl = images.get(0).getUrl();
                Glide.with(context).load(imageUrl).into(imgTrack);
            }

            imgArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mediaPlayer == null) {
                        mediaPlayer = new MediaPlayer();
                    }

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                    try {
                        mediaPlayer.setDataSource(itemTrack.getUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }
}
