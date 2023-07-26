package com.example.tetris;

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
import android.util.AttributeSet;
import android.view.View;

public class hold extends View {
    private static int[][] holdBlock = new int[Draw.blockLenght][Draw.blockLenght];
    blocks bs = new blocks();
    private static int blocksize = 40;
    private static int nextxmax = 5;
    private static int nextymax = 5;
    private static int nextH = nextymax * blocksize;
    private static int nextW = nextxmax * blocksize;
    private int holdNum = 0;
    private boolean holdinside = false;

    public hold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        if (MainActivity.holdButtonclick) {
            holdfun();
            MainActivity.holdButtonclick = false;
        }
        holdDraw(ca);

    }

    public void holdfun() {
        if (!holdinside) {
            for (int i = 0; i < Draw.blockLenght; i++) {
                for (int j = 0; j < Draw.blockLenght; j++) {
                    holdBlock[i][j] = blocks.nowBlock[i][j];
                }
            }
            holdNum = bs.randomNumbers.get(0);
            bs.randomNumbers.remove(0);
            Draw.moveflag = false;
            holdinside = true;
        } else {
            int[][] copyNowBlock = new int[Draw.blockLenght][Draw.blockLenght];
            int[][] copyHoldBlock = new int[Draw.blockLenght][Draw.blockLenght];


            int[] copyNumbers=new int[2];
            copyNumbers[0]= bs.randomNumbers.get(0);
            copyNumbers[1]= holdNum;

            holdNum = copyNumbers[0];

            bs.randomNumbers.add(0, copyNumbers[1]);
            bs.randomNumbers.remove(1);
            blocks.num=copyNumbers[1];
            for (int i = 0; i < Draw.blockLenght; i++) {
                for (int j = 0; j < Draw.blockLenght; j++) {
                    copyNowBlock[i][j] = blocks.nowBlock[i][j];
                    copyHoldBlock[i][j] = holdBlock[i][j];
                }
            }
            for (int i = 0; i < Draw.blockLenght; i++) {
                for (int j = 0; j < Draw.blockLenght; j++) {
                    blocks.nowBlock[i][j] = copyHoldBlock[i][j];
                    holdBlock[i][j] = copyNowBlock[i][j];
                }
            }
        }
    }

    public void holdDraw(Canvas ca) {
        Paint paint = new Paint();
        Paint p0 = new Paint();
        Paint p1 = new Paint();
        p0.setColor(Color.BLACK);
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);
        ca.drawRect(0, 0, nextH, nextW, p0);
        for (int i = 0; i < Draw.blockLenght; i++) {
            for (int j = 0; j < Draw.blockLenght; j++) {
                int px = j * blocksize;
                int py = i * blocksize;
                if (holdBlock[i][j] == 1) {
                    switch (holdNum) {
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
            }
        }
        invalidate();
    }
}
