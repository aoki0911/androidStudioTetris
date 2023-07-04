package com.example.tetris;

import static com.example.tetris.MainActivity.moveflag;
import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.nowBlock;
import static com.example.tetris.blocks.num;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {

    int motion = Stational;
    public static final int Stational = 0;
    public static final int Left = 1;
    public static final int Right = 2;

    static final int xmax = 10;
    static final int ymax = 15;
    static final int blocksize = 60;

    static int fieldW = xmax * blocksize;
    static int fieldH = ymax * blocksize;

    public int offsetx = 0;
    public int offsety = 0;

    public static final int blockLenght = 4;
    int[][] field = new int[15][10];

    public Handler handler = new android.os.Handler();
    Paint paint = new Paint();
    blocks bs=new blocks();

    public Draw(Context context) {
        super(context);
    }

    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        blocks bs = new blocks();


        if (moveflag == false) {
            bs.blocks();
            initstartpoi();
        }
        if (canMove(0, 1, nowBlock) == false) {
            blockFixt();
        }
        blockDraw(ca);
        move(motion);
    }

    public void blockDraw(Canvas ca) {


        Paint p0 = new Paint();
        p0.setColor(Color.BLACK);
        /*Paint p2=new Paint();
        p2.setColor(Color.RED);
        p2.setTextSize(200);*/
        Paint p1 = new Paint();
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);
        ca.drawRect(0, 0, fieldW, fieldH, p0);
        //ca.drawText(String.valueOf(canMove(0,1,nowBlock)),500,300,p2);
        for (int i = 0; i < ymax; i++) {
            for (int j = 0; j < xmax; j++) {
                int px = j * blocksize;
                int py = i * blocksize;
                switch (field[i][j]) {
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
                        paint.setColor(Color.argb(255, 255, 255, 0));
                        ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                        ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                        break;
                }
                ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
            }
        }
        //ブロック出現処置
        for (int i = 0; i < blockLenght; i++) {
            for (int j = 0; j < blockLenght; j++) {
                if (blocks.nowBlock[i][j] == 1) {
                    drawMoveBlock(+offsetx + j, offsety + i, ca);
                }
            }
        }
        invalidate();
    }


    public void drawMoveBlock(int x, int y, Canvas ca) {

        Paint p1 = new Paint();
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);

        int px = x * blocksize;
        int py = y * blocksize;

        switch (num) {
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
                paint.setColor(Color.argb(255, 0, 255, 255));
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
                paint.setColor(Color.argb(255, 255, 255, 0));
                ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                break;

            default:
                break;
        }
        ;

    }

    public void initstartpoi() {
        offsetx = xmax / 2 - blockLenght / 2;
        offsety = 0;
    }

    public void move(int motion) {
        if (MainActivity.a_vals[0] < -3) {
            motion = Right;
        }
        if (MainActivity.a_vals[0] > 3) {
            motion = Left;
        }
        if (MainActivity.a_vals[0] < 3 && MainActivity.a_vals[0] > -3) {
            motion = Stational;
        }

        switch (motion) {
            case Right:
                if (canMove(1, 0, nowBlock)) {
                    offsetx++;
                }
                break;

            case Left:
                if (canMove(-1, 0, nowBlock)) {
                    offsetx--;
                }

                break;

            case Stational:
                break;
        }
    }

    public boolean canMove(int dx, int dy, int[][] nowBlock) {
        for (int i = 0; i < blockLenght; i++) {
            for (int j = 0; j < blockLenght; j++) {
                if (nowBlock[i][j] != 0) {
                    int nx = offsetx + j + dx;
                    int ny = offsety + i + dy;
                    if (ny < 0) {
                        return false;
                    }
                    if (nx < 0) {
                        return false;
                    }
                    if (nx >= xmax) {
                        return false;
                    }
                    if (ny >= ymax) {
                        return false;
                    }
                    if (field[ny][nx] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void blockDropStart() {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                offsety++;
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(r);
    }

    public void blockFixt() {
        for (int i = 0; i < blockLenght; i++) {
            for (int j = 0; j < blockLenght; j++) {
                switch (num) {
                    case tBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 1;
                        }
                        break;
                    case sBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 2;
                        }
                        break;
                    case iBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 3;
                        }
                        break;
                    case oBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 4;
                        }
                        break;
                    case lBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 5;
                        }
                        break;
                    case jBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 6;
                        }
                        break;
                    case zBlock:
                        if (nowBlock[i][j] == 1) {
                            field[offsety + i][offsetx + j] = 7;
                        }
                        break;
                }
            }
        }
        moveflag = false;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (canMove(0, 0, nowBlock)) {
                bs.roteta();
            }
        }
        return true;
    }
}
