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

    int[][] field= {
            {1,2,3,4,5,6,7,1,2,3}
            ,{7,6,5,4,3,2,1,2,3,4}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}//5
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}//10
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{7,6,5,4,3,2,1,2,3,4}
            ,{1,2,3,4,5,6,7,1,2,3}//15
    };


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
            blockdraw(ca);
        }
        public void blockdraw(Canvas ca){
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
                            case 1:
                                Paint pB=new Paint();
                                pB.setColor(Color.BLUE);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pB);
                                break;
                            case 2:
                                Paint pG=new Paint();
                                pG.setColor(Color.GREEN);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pG);
                                break;
                            case 3:
                                Paint pR=new Paint();
                                pR.setColor(Color.RED);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pR);
                                break;
                            case 4:
                                Paint pWB=new Paint();
                                pWB.setColor(Color.argb(250,0,255,255));
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pWB);
                                break;
                            case 5:
                                Paint pP=new Paint();
                                pP.setColor(Color.argb(255,255,0,255));
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pP);
                                break;
                            case 6:
                                Paint pY=new Paint();
                                pY.setColor(Color.YELLOW);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pY);
                                break;
                            case 7:
                                Paint pLG=new Paint();
                                pLG.setColor(Color.LTGRAY);
                                ca.drawRect(px+blocksize,py+blocksize,px,py,pLG);
                                break;
                        }
                        ca.drawRect(px+blocksize,py+blocksize,px,py,p1);
                    }
                }
            }
    }
}