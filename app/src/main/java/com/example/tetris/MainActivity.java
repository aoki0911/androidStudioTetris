package com.example.tetris;

import static com.example.tetris.Draw.score;
import static com.example.tetris.blocks.randomNumber;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Button resetButton;


    Handler handler = new Handler();
    public static boolean startFlag = false;
    music ms;
    MediaPlayer bgm;
    MediaPlayer gameover;
    Draw dw;
    TextView scoreLabel;
    TextView highScoreLabel;
    SharedPreferences sp;
    int highScore;
    public static boolean holdButtonclick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dw = this.findViewById(R.id.Draw);
        dw.showfield(Draw.Stational);

        ms = new music(getApplicationContext());
        if (bgm == null) {
            bgm = ms.getMuic(0);
        }
        gameover = ms.getMuic(1);

        if (startFlag = true) {
            timerset();
            bgm.start();
        }
        //ブロックのランダムメソッド
        randomNumber();

        //左移動のボタン定義、機能定義
        Button leftButton = findViewById(R.id.leftButton);
        setButtonFunction(leftButton, Draw.Left);

        //右移動のボタン定義、機能定義
        Button rightButton = findViewById(R.id.righttButton);
        setButtonFunction(rightButton, Draw.Right);

        //回転のボタン定義、機能定義
        Button rotetaButton = findViewById(R.id.rotateButton);
        setButtonFunction(rotetaButton, Draw.rotate);

        //高速落下のボタン定義、機能定義
        Button downButton = findViewById(R.id.downButton);
        setDownButtonFunction(downButton);

        //リセットのボタン定義、機能定義
        resetButton = findViewById(R.id.resetButton);
        setresetButton(resetButton);

        //ホールドのボタン定義、機能定義
        Button holdButton=findViewById(R.id.holdButton);
        setHoldButtonFunction(holdButton);

        //最高得点のなどの定義
        scoreLabel = findViewById(R.id.scoreLabel);
        highScoreLabel = findViewById(R.id.highScoreLabel);
        sp = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        highScore = sp.getInt("High_Score", 0);
    }

    //移動機能定義メソッド
    private void setButtonFunction(Button button, final int motion) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dw.showfield(motion);
            }
        });
    }

    //ホールドボタンの機能定義メソッド
    private void setHoldButtonFunction(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdButtonclick=true;
            }
        });
    }

    //高速落下ボタンの機能定義メソッド
    private void setDownButtonFunction(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (true) {
                    Draw.offsety++;
                    if (dw.canMove(0, 1, blocks.nowBlock) == false) {
                        dw.blockFixt();
                        dw.checkfield();
                        break;
                    }
                }
            }
        });
    }

    //リセットボタンの機能定義メソッド
    private void setresetButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dw.reset();
                try {
                    bgm.prepare();
                } catch (Exception e) {
                }
                bgm.start();
            }
        });
    }

    //一定間隔の時間で処理を行うメソッド
    private void timerset() {
        //resetButton = findViewById(R.id.resetButton);
        if (startFlag) {
            final Runnable r = new Runnable() {
                @Override
                public void run() {

                    //一定周期の落下
                    dw.showfield(Draw.Down);
                    handler.postDelayed(this, 1000);

                    //スコアの表示
                    TextView scoreText = findViewById(R.id.scoreText);
                    scoreText.setText(String.valueOf(score));

                    TextView gameOverText = findViewById(R.id.gameOverText);

                    //ゲームオーバーの時の処理
                    if (dw.gameOverFlag) {
                        //リセットボタンの機能定義、ボタンの表示
                        setresetButton(resetButton);
                        resetButton.setVisibility(View.VISIBLE);
                        //テキストセット
                        gameOverText.setText(R.string.gameOver);
                        resetButton.setText("Reset");
                        //音楽停止と再生
                        bgm.stop();
                        gameover.start();
                        //ハイスコア表示、更新処理
                        if (score >= highScore) {
                            highScoreLabel.setText("High Score :" + score);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("High_Score", score);
                            editor.apply();
                        } else {
                            highScoreLabel.setText("High Socre :" + highScore);
                            scoreLabel.setText("Score :" + score);
                        }
                    } else {
                        //リセットボタンの非表示
                        gameOverText.setText("");
                        resetButton.setVisibility(View.INVISIBLE);
                    }
                }
            };
            handler.post(r);
        } else {
            resetButton.setVisibility(View.INVISIBLE);
        }
    }
}