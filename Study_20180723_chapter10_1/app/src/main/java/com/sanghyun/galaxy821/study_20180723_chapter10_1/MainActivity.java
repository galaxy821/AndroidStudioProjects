package com.sanghyun.galaxy821.study_20180723_chapter10_1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    static final String AUDIO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr"; //음악 파일 URL
    static final String VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4"; //비디어 파일 URL

    private MediaPlayer mediaPlayer; //MediaPlayer 변수 선언
    private int playbackPosition=0; //재생위치변수 선언

    Button startbtn;
    Button pausebtn;
    Button restartbtn;

    VideoView videoView;
    Button playbtn;
    Button volumebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn = (Button) findViewById(R.id.playbtn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playAudio(AUDIO_URL);

                    Toast.makeText(getApplicationContext(), "음악파일 재생 시작됨", Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        pausebtn = (Button) findViewById(R.id.pausebtn);
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playbackPosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
                Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.", Toast.LENGTH_LONG).show();
            }
        });

        restartbtn = (Button) findViewById(R.id.restartbtn);
        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null && ! mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playbackPosition);
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 재시작됨.", Toast.LENGTH_LONG).show();
                }
            }
        });

        volumebtn = (Button)findViewById(R.id.volumeBtn);
        volumebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                int maxVolum = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolum, AudioManager.FLAG_SHOW_UI);
            }
        });

        videoView = (VideoView) findViewById(R.id.videoView);

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(VIDEO_URL));
        videoView.requestFocus();
    }

    private void playAudio(String URL) throws Exception{
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(URL);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    protected void onDestroy(){
        super.onDestroy();
        killMediaPlayer();
    }

    private  void killMediaPlayer(){
        if(mediaPlayer != null){
            try{
                mediaPlayer.release();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
