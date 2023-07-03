package com.example.tetris;

import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    SensorManager sm;
    SEL sel;

    float[] a_vals=new float[3];

    static final int xmax=10;
    static final int ymax=15;
    static final int blocksize=60;

    static int fieldW=xmax*blocksize;
    static int fieldH=ymax*blocksize;

    int offsetx=0;
    int offsety=0;

    int[][] field=new int[15][10];


    int motion=Stational;
    public static final int Stational=0;
    public static final int Left=1;
    public static final int Right=2;



    public static final int blockLenght=4;


    static boolean moveflag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Myview mv=new Myview(this);
        setContentView(mv);
    }

    class Myview extends View {
        public Myview(Context context){super(context);}

        protected void onDraw(Canvas ca) {
            super.onDraw(ca);
            blockDraw bd=new blockDraw();
            blocks bs=new blocks();
            Reset re=new Reset();
            setTimer st=new setTimer();
            movement mm=new movement();



            if(moveflag==false){
                bs.blocks();
                re.initstartpoi();
            }
            bd.blockDraw(ca);
            mm.move(Right);
            st.blockDrap();

        }
        class blockDraw{
            Paint paint=new Paint();

            public void blockDraw(Canvas ca){


                Paint p0=new Paint();
                p0.setColor(Color.BLACK);
                Paint p1=new Paint();
                p1.setColor(Color.WHITE);
                p1.setStyle(Paint.Style.STROKE);
                ca.drawRect(0,0,fieldW,fieldH,p0);
                for(int i=0;i<ymax;i++){
                    for(int j=0;j<xmax;j++){
                        int px=j*blocksize;
                        int py=i*blocksize;
                        switch (field[i][j]){
                            case tBlock:
                                paint.setColor(Color.BLUE);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case sBlock:
                                paint.setColor(Color.GREEN);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case iBlock:
                                paint.setColor(Color.RED);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case oBlock:
                                paint.setColor(Color.argb(250,0,255,255));
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case lBlock:
                                paint.setColor(Color.argb(255,255,0,255));
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case jBlock:
                                paint.setColor(Color.YELLOW);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                            case zBlock:
                                paint.setColor(Color.argb(255,255,255,0));
                                ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                                break;
                        }
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                    }
                }
                //ブロック出現処置
                for(int i=0;i<blockLenght;i++) {
                    for (int j = 0; j < blockLenght; j++) {
                        if (blocks.nowBlock[i][j] == 1) {
                            drawMoveBlock(+offsetx + j, offsety + i, ca);
                        }
                    }
                }
                invalidate();
            }


            public void drawMoveBlock(int x,int y,Canvas ca){

                Paint p1=new Paint();
                p1.setColor(Color.WHITE);
                p1.setStyle(Paint.Style.STROKE);

                int px=x*blocksize;
                int py=y*blocksize;

                switch (blocks.num){
                    case tBlock:
                        paint.setColor(Color.BLUE);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case sBlock:
                        paint.setColor(Color.GREEN);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case iBlock:
                        paint.setColor(Color.RED);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case oBlock:
                        paint.setColor(Color.argb(255,0,255,255));
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case lBlock:
                        paint.setColor(Color.argb(255,255,0,255));
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case jBlock:
                        paint.setColor(Color.YELLOW);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    case zBlock:
                        paint.setColor(Color.argb(255,255,255,0));
                        ca.drawRect(px+blocksize,py+blocksize,px,py,paint);
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                        break;

                    default:
                        break;
                };

            }
        }
    }

    protected void onResume() {
        super.onResume();
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sel = new SEL();
        sm.registerListener(sel, accelerometer,SensorManager.SENSOR_DELAY_NORMAL );
    }

    protected void onPause(){
        super.onPause();
        sm.unregisterListener(sel);
    }

    class SEL implements SensorEventListener {

        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
                a_vals=event.values;
            }
        }
        public void onAccuracyChanged(Sensor sensor,int accuracy) {
        }
    }







    class Reset{
        public void initstartpoi(){
            offsetx=xmax/2-blockLenght/2;
            offsety=0;
        }
    }

    class setTimer {
        public void blockDrap(){
            if(moveflag=true){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                offsety++;
            }
        }

    }
    class movement{
        public void move(int motion){
            /*if(a_vals[0]<-3){motion=Right;}
            if(a_vals[0]>3){motion=Left;}
            if(a_vals[0]<3&&a_vals[0]>-3){motion=Stational;}*/

            switch (motion){
                case Right:
                    offsetx++;
                    break;

                case Left:
                    offsetx--;
                    break;

                case Stational:
                    break;
            }
        }
    }
}