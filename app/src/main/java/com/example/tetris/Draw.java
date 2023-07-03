package com.example.tetris;

import static com.example.tetris.MainActivity.moveflag;
import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Draw extends View {

    int motion=Stational;
    public static final int Stational=0;
    public static final int Left=1;
    public static final int Right=2;

    static final int xmax=10;
    static final int ymax=15;
    static final int blocksize=60;

    static int fieldW=xmax*blocksize;
    static int fieldH=ymax*blocksize;

    static int offsetx=0;
    static int offsety=0;

    public static final int blockLenght=4;
    int[][] field=new int[15][10];
    Paint paint=new Paint();

    public Draw(Context context){
        super(context);
    }

    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        blocks bs=new blocks();





        if(moveflag==false){
            bs.blocks();
            initstartpoi();
        }
        blockDraw(ca);
        move(motion);
        blockDrap();

    }

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
    public void initstartpoi(){
        offsetx=xmax/2-blockLenght/2;
        offsety=0;
    }
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
