package com.example.tetris;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    SensorManager sm;
    SEL sel;

    public static float[] a_vals = new float[3];

    static boolean moveflag;

    Draw dw;
    blocks bs = new blocks();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Draw dw = new Draw(this);
        setContentView(dw);
        dw.blockDropStart();
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
}