package com.example.tetris;


import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.nextBlock;
import static com.example.tetris.blocks.nextNum;
import static com.example.tetris.blocks.num;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class nextDraw extends View {

    blocks bs = new blocks();
    //Draw dw=new Draw();
    //Draw dw= this.findViewById(R.id.Draw);;


    private static int blocksize = 40;
    private static int nextxmax = 5;
    private static int nextymax = 5;
    private static int nextH=nextymax*blocksize;
    private static int nextW=nextxmax*blocksize;



    public nextDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        bs.setNextBlock();
        nextDraw(ca);
        invalidate();
    }

    public void nextDraw(Canvas ca) {


        Paint paint = new Paint();
        Paint p0=new Paint();
        Paint p1 = new Paint();
        p0.setColor(Color.BLACK);
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);
        ca.drawRect(0,0,nextH,nextW,p0);
        for (int i = 0; i < Draw.blockLenght; i++) {
            for (int j = 0; j < Draw.blockLenght; j++) {
                int px = j * blocksize;
                int py = i * blocksize;
                if (nextBlock[i][j] == 1) {
                    switch (nextNum) {
                        case tBlock:
                            paint.setColor(Color.BLUE);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case sBlock:
                            paint.setColor(Color.GREEN);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case iBlock:
                            paint.setColor(Color.RED);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case oBlock:
                            paint.setColor(Color.argb(250, 0, 255, 255));
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case lBlock:
                            paint.setColor(Color.argb(255, 255, 0, 255));
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case jBlock:
                            paint.setColor(Color.YELLOW);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                        case zBlock:
                            paint.setColor(Color.argb(255, 255, 165, 0));
                            ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                            ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                            break;
                    }
                }
                ca.drawRect(0,0,nextH,nextW,p1);
            }
        }
    }
}
