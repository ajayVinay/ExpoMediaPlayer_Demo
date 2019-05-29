package com.example.exoplayerexample;

import android.app.AppComponentFactory;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    static final String AUDIO_PATH = "http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3";
    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }

    public void doClick(View view) {
        switch(view.getId()) {
            case R.id.startPlayerBtn:
                try {
                    playAudio(AUDIO_PATH);
                    //playLocalAudio();
                   // playLocalAudio_UsingDescriptor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.pausePlayerBtn:
                if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
                break;
            case R.id.restartPlayerBtn:
                if(mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
                break;
            case R.id.stopPlayerBtn:
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                    playbackPosition = 0;
                }
                break;
        }
    }
    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

   /* private void playLocalAudio() throws Exception
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.music_file);
        mediaPlayer.start();
    }
*/
   /* private void playLocalAudio_UsingDescriptor() throws Exception {

        AssetFileDescriptor fileDesc = getResources().openRawResourceFd(
                R.raw.music_file);
        if (fileDesc != null) {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc
                    .getStartOffset(), fileDesc.getLength());

            fileDesc.close();

            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

