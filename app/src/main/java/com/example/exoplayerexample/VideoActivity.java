package com.example.exoplayerexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoActivity extends AppCompatActivity {
    private SimpleExoPlayer player;
    DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private PlayerView playerView;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private String ivHideControllerButton;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        imageView = (ImageView) findViewById(R.id.exo_controller);
        playerView = (PlayerView) findViewById(R.id.playerView);


        playerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory();

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        playerView.setPlayer(player);

        player.setPlayWhenReady(true);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), "ExoplayerDemo");


        //url for Audio -: http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3
        //url for Video -: http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4

        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).setExtractorsFactory(extractorsFactory).createMediaSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));

        player.prepare(mediaSource);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT <= 27) {
           // initializePlayer();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Util.SDK_INT <= 27) {
           // initializePlayer();
        }


    }
    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        updateStartPositon();

    }

    private void updateStartPositon() {
        player.setPlayWhenReady(false);

    }

}
