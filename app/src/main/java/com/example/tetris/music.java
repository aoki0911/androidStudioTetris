package com.example.tetris;

import android.content.Context;
import android.media.MediaPlayer;

public class music {
    private MediaPlayer[] mediaPlayers;

    //音楽をそれぞれ定義
    public music(Context context) {
        mediaPlayers = new MediaPlayer[3];
        mediaPlayers[0] = MediaPlayer.create(context, R.raw.bgm);
        mediaPlayers[1] = MediaPlayer.create(context, R.raw.gameover);
        mediaPlayers[2] = MediaPlayer.create(context, R.raw.ongaku1);
    }

    //指定の音楽を取得
    public MediaPlayer getMuic(int index) {
        if (index >= 0 && index < mediaPlayers.length) {
            return mediaPlayers[index];
        } else {
            throw new IndexOutOfBoundsException("Invalod MediaPlayer index");
        }
    }
}