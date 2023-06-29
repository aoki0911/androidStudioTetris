package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final int xmax=10;
    static final int ymax=15;
    static final int blocksize=60;

    static int fieldW=xmax*blocksize;
    static int fieldH=ymax*blocksize;

    int offsetx=0;
    int offsety=0;

    int[][] field=new int[15][10];
    public static final int tBlock = 1;
    public static final int sBlock = 2;
    public static final int iBlock = 3;
    public static final int oBlock = 4;
    public static final int lBlock = 5;
    public static final int jBlock = 6;
    public static final int zBlock = 7;

    int num=0;

    public static final int blockLenght=4;
    int[][] nowBlock=new int[blockLenght][blockLenght];


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
            blockdraw bd=new blockdraw();
            blocks bs=new blocks();


            bs.blocks();
            bd.blockdraw(ca);



        }
        class blockdraw{
            Paint paint=new Paint();

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
                for(int i=0;i<blockLenght;i++){
                    for(int j=0;j<blockLenght;j++){
                        if(nowBlock[i][j]==1){
                            drawMoveBlock(+offsetx+j,offsety+i,ca);
                        }
                    }
                }
                //invalidate();
            }


            public void drawMoveBlock(int x,int y,Canvas ca){

                Paint p1=new Paint();
                p1.setColor(Color.WHITE);
                p1.setStyle(Paint.Style.STROKE);

                int px=x*blocksize;
                int py=y*blocksize;

                switch (num){
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




        class blocks{

            public void blocks(){

                //ブロックランダム抽選
                num=(int)(Math.random()*7)+1;

                switch (num){
                    case tBlock:
                        nowBlock=new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {0, 1, 1, 1},
                                {0, 0, 0, 0}
                        };
                        break;

                    case sBlock:
                        nowBlock=new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        break;

                    case iBlock:
                        nowBlock=new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0}
                        };
                        break;

                    case oBlock:
                        nowBlock=new int[][]{
                                {0, 1, 1, 0},
                                {0, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        };
                        break;

                    case lBlock:
                        nowBlock=new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 1},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        break;

                    case jBlock:
                        nowBlock=new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 1, 1},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        };
                        break;
                    case zBlock:
                        nowBlock=new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 1},
                                {0, 0, 0, 0}
                        };
                        break;
                    default:
                        break;
                };
            }
        }

        class reset{

        }
    }
}