package com.example.tetris;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    SensorManager sm;
    SEL sel;

    public static float[] a_vals = new float[3];


    Handler handler = new Handler();
    public static boolean startFlag = false;
    MediaPlayer bgm;
    MediaPlayer gameover;

    Draw dw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dw = this.findViewById(R.id.Draw);
        dw.showfield(Draw.Stational);

        if(startFlag=true) {
            timerset();
        }

        Button leftButton = findViewById(R.id.leftButton);
        setButtonFunction(leftButton, Draw.Left);

        Button rightButton = findViewById(R.id.righttButton);
        setButtonFunction(rightButton, Draw.Right);

        Button rotetaButton = findViewById(R.id.rotateButton);
        setButtonFunction(rotetaButton, Draw.rotate);

        Button downButton = findViewById(R.id.downButton);
        setDownButtonFunction(downButton);
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
            }
        });
    }

    protected void onResume() {
        super.onResume();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sel = new SEL();
        sm.registerListener(sel, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(sel);
    }

    class SEL implements SensorEventListener {

        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                a_vals = event.values;
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private void timerset() {
        Button resetButton = findViewById(R.id.resetButton);
        if (startFlag) {
            final Runnable r = new Runnable() {
                @Override
                public void run() {

                    dw.showfield(Draw.Down);
                    handler.postDelayed(this, 1000);


                    TextView scoreText = findViewById(R.id.scoreText);
                    scoreText.setText(String.valueOf(dw.score));

                    TextView gameOverText = findViewById(R.id.gameOverText);

                    if (dw.gameOverFlag) {
                        resetButton.setVisibility(View.VISIBLE);
                        gameOverText.setText(R.string.gameOver);
                        resetButton.setVisibility(View.VISIBLE);
                        resetButton.setText("Reset");
                        setresetButton(resetButton);
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