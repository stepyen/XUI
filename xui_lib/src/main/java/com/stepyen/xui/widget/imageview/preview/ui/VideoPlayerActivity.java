
package com.stepyen.xui.widget.imageview.preview.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.widget.Toast;
import android.widget.VideoView;

import com.stepyen.xui.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * 视频播放界面
 *
 * @author xuexiang
 * @since 2018/12/5 上午11:49
 */
public class VideoPlayerActivity extends FragmentActivity {

    public static final String KEY_URL = "com.xuexiang.xui.widget.preview.KEY_URL";

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity_video_player);
        mVideoView = findViewById(R.id.video);
        mVideoView.setVideoPath(getIntent().getStringExtra(KEY_URL));
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(VideoPlayerActivity.this, R.string.xui_preview_play_failed, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mVideoView.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

    /***
     * 启动播放视频
     * @param fragment context
     * @param url url
     **/
    public static void start(Fragment fragment, String url) {
        Intent intent = new Intent(fragment.getContext(), VideoPlayerActivity.class);
        intent.putExtra(KEY_URL, url);
        fragment.startActivity(intent);
    }
}
