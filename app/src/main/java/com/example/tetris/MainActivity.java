package com.example.tetris;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    SensorManager sm;
    SEL sel;

    public static float[] a_vals = new float[3];

    static boolean moveflag;
    Handler handler = new Handler();

    Draw dw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dw = this.findViewById(R.id.Draw);

        //Draw dw = new Draw(this);
        setContentView(R.layout.activity_main);
        timerset();


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
                dw.offsety++;
                handler.postDelayed(this, 1000);

                TextView scoreText = findViewById(R.id.scoreText);
                scoreText.setText(String.valueOf(dw.score));

                TextView gameOverText=findViewById(R.id.gameOverText);
                if(dw.gameOverFlag==true){
                    gameOverText.setText(R.string.gameOver);
                }else{
                    gameOverText.setText("");
                }

            }
        };
        handler.post(r);
    }
}