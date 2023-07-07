package com.example.tetris;


import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.nextBlock;
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
    private static int xmax = 5;
    private static int ymax = 5;


    public nextDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        bs.setNextBlock();
        Draw.initstartpoi();
        nextDraw(ca);
        invalidate();
    }

    public void nextDraw(Canvas ca) {

        System.out.println("----------");
        System.out.println(R.id.Draw);
        System.out.println("----------");

        Paint paint = new Paint();
        Paint p1 = new Paint();
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < Draw.blockLenght; i++) {
            for (int j = 0; j < Draw.blockLenght; j++) {
                switch (nextBlock[i][j]) {
                    case tBlock:
                        paint.setColor(Color.BLUE);
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case sBlock:
                        paint.setColor(Color.GREEN);
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case iBlock:
                        paint.setColor(Color.RED);
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case oBlock:
                        paint.setColor(Color.argb(250, 0, 255, 255));
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case lBlock:
                        paint.setColor(Color.argb(255, 255, 0, 255));
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case jBlock:
                        paint.setColor(Color.YELLOW);
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                    case zBlock:
                        paint.setColor(Color.argb(255, 255, 165, 0));
                        ca.drawRect(blocksize, blocksize, 0, 0, paint);
                        ca.drawRect(blocksize, blocksize, 0, 0, p1);
                        break;
                }
            }
        }
    }
}
