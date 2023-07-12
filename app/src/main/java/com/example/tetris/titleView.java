package com.example.tetris;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class titleView extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.titlelayout);


        ImageButton titleButton = findViewById(R.id.titleButton);
        mp = MediaPlayer.create(this, R.raw.ongaku1);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startFlag = true;
                Intent intent = new Intent(titleView.this, MainActivity.class);
                startActivity(intent);
                musicPlay();
                finish();
            }
        });
    }

    public void musicPlay() {
        if (mp != null && !mp.isPlaying()) {
            mp.start();
        }
    }

    public void musicStop() {
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }
}
