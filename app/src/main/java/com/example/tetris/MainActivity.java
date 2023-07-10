package com.example.tetris;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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


    Draw dw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dw = this.findViewById(R.id.Draw);
        //dw.move(dw.Stational);

        //Draw dw = new Draw(this);
        setContentView(R.layout.activity_main);
        timerset();

        dw = this.findViewById(R.id.Draw);
        dw.showfield(Draw.Stational);

        Button leftButton = findViewById(R.id.leftButton);
        setButtonFunction(leftButton, Draw.Left);

        Button rightButton = findViewById(R.id.righttButton);
        setButtonFunction(rightButton, Draw.Right);

        Button rotetaButton = findViewById(R.id.rotateButton);
        setButtonFunction(rotetaButton, Draw.rotate);
    }

    private void setButtonFunction(Button button, final int motion) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dw.showfield(motion);
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
        final Runnable r = new Runnable() {
            @Override
            public void run() {

                dw.showfield(Draw.Down);
                handler.postDelayed(this, 1000);



                TextView scoreText = findViewById(R.id.scoreText);
                scoreText.setText(String.valueOf(dw.score));

                TextView gameOverText = findViewById(R.id.gameOverText);
                Button resetButton=findViewById(R.id.resetButton);
                if (dw.gameOverFlag == true) {
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
    }
}