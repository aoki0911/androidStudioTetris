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
    blocks bs = new blocks();
    TextView scoreLabel;
    TextView highScoreLabel;
    SharedPreferences sp;
    int highScore;

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
        randomNumber();

        Button leftButton = findViewById(R.id.leftButton);
        setButtonFunction(leftButton, Draw.Left);

        Button rightButton = findViewById(R.id.righttButton);
        setButtonFunction(rightButton, Draw.Right);

        Button rotetaButton = findViewById(R.id.rotateButton);
        setButtonFunction(rotetaButton, Draw.rotate);

        Button downButton = findViewById(R.id.downButton);
        setDownButtonFunction(downButton);

        resetButton = findViewById(R.id.resetButton);
        setresetButton(resetButton);

        scoreLabel = findViewById(R.id.scoreLabel);
        highScoreLabel = findViewById(R.id.highScoreLabel);
        sp = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        highScore = sp.getInt("High_Score", 0);
    }

    private void setButtonFunction(Button button, final int motion) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dw.showfield(motion);
            }
        });
    }

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

    private void timerset() {
        //resetButton = findViewById(R.id.resetButton);
        if (startFlag) {
            final Runnable r = new Runnable() {
                @Override
                public void run() {

                    dw.showfield(Draw.Down);
                    handler.postDelayed(this, 1000);


                    TextView scoreText = findViewById(R.id.scoreText);
                    scoreText.setText(String.valueOf(score));

                    TextView gameOverText = findViewById(R.id.gameOverText);

                    if (dw.gameOverFlag) {
                        setresetButton(resetButton);
                        resetButton.setVisibility(View.VISIBLE);
                        gameOverText.setText(R.string.gameOver);
                        resetButton.setVisibility(View.VISIBLE);
                        resetButton.setText("Reset");
                        bgm.stop();
                        gameover.start();
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