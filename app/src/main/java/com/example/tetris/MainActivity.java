package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static int xmax=10;
    static int ymax=15;
    static int blocksize=60;

    static int fieldW=xmax*blocksize;
    static int fieldH=ymax*blocksize;

    int[][] field=new int[ymax][xmax];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Myview mv=new Myview(this);
        setContentView(mv);
    }

    class Myview extends View {
        public Myview(Context context){super(context);}

        protected void onDraw(Canvas ca){
            super.onDraw(ca);

            Paint p0=new Paint();
            p0.setColor(Color.BLACK);
            Paint p1=new Paint();
            p1.setColor(Color.WHITE);
            p1.setStyle(Paint.Style.STROKE);
            ca.drawRect(0,0,fieldW,fieldH,p0);
            for(int i=0;i<ymax;i++){
                for(int j=0;j<xmax;j++){
                    ca.drawRect(0,0,j*blocksize,i*blocksize,p1);
                }
            }

        }
    }
}