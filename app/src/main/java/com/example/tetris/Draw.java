package com.example.tetris;

import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.nowBlock;
import static com.example.tetris.blocks.num;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.randomNumber;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {

    private int motion = Stational;
    public static final int Stational = 0;
    public static final int Left = 1;
    public static final int Right = 2;
    public static final int rotate = 3;
    public static final int Down = 4;

    private static final int xmax = 10;
    private static final int ymax = 15;
    static final int blocksize = 75;
    static int fieldW = xmax * blocksize;
    static int fieldH = ymax * blocksize;

    public static int offsetx = 0;
    public static int offsety = 0;

    public static final int blockLenght = 4;
    int[][] field = new int[15][10];
    public static int score = 0;
    public static boolean gameOverFlag = false;
    static boolean moveflag;


    Paint paint = new Paint();
    blocks bs = new blocks();

    public Draw(Context context) {
        super(context);
    }

    public Draw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Draw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas ca) {
        super.onDraw(ca);
        randomNumber();

        if (!moveflag) {
            initstartpoi();
            bs.setNowBlock();
        }
        if (!gameOverFlag) {
            switch (motion) {
                case Right:
                    if (canMove(1, 0, nowBlock)) {
                        offsetx = offsetx + 1;
                    }
                    break;

                case Left:
                    if (canMove(-1, 0, nowBlock)) {
                        offsetx = offsetx - 1;
                    }
                    break;

                case rotate:
                    if (canMove(0, 0, nowBlock)) {
                        bs.roteta();
                    }
                    break;

                case Down:
                    if (canMove(0, 1, nowBlock)) {
                        offsety++;

                    } else {
                        if (!canMove(0, 0, nowBlock)) {
                            gameOverFlag = true;
                        }
                        blockFixt();
                        checkfield();
                    }

                default:
                    break;
            }
            blockDraw(ca);
        }
    }

    public void showfield(int motion) {
        invalidate();
        this.motion = motion;
    }

    public void reset() {
        gameOverFlag = false;
        score = 0;
        motion = Stational;
        moveflag=false;
        resetfield();
    }

    public void resetfield() {
        for (int i = 0; i < ymax; i++) {
            for (int j = 0; j < xmax; j++) {
                field[i][j] = 0;
            }
        }
    }


    public void blockDraw(Canvas ca) {


        Paint p0 = new Paint();
        Paint p1 = new Paint();
        p0.setColor(Color.BLACK);
        p1.setColor(Color.WHITE);
        p1.setStyle(Paint.Style.STROKE);
        ca.drawRect(0, 0, fieldW, fieldH, p0);
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
                        paint.setColor(Color.argb(255, 255, 165, 0));
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
                paint.setColor(Color.argb(255, 255, 165, 0));
                ca.drawRect(px + blocksize, py + blocksize, px, py, paint);
                ca.drawRect(px + blocksize, py + blocksize, px, py, p1);
                break;

            default:
                break;
        }
        ;

    }

    public static void initstartpoi() {
        offsetx = xmax / 2 - blockLenght / 2;
        offsety = 0;
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
        bs.randomNumbers.remove(0);
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

    public void clearLine(int row) {
        for (int j = 0; j < xmax; j++) {
            field[row][j] = 0;
        }
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < xmax; j++) {
                field[i][j] = field[i - 1][j];
            }
        }
        for (int j = 0; j < xmax; j++) {
            field[0][j] = 0;
        }
        score += 100;
    }

    public void checkfield() {
        for (int i = 0; i < ymax; i++) {
            for (int j = 0; j < xmax; j++) {
                if (field[i][j] == 0) {
                    break;
                } else if (j == xmax - 1 && field[i][j] > 0) {
                    clearLine(i);
                }
            }
        }
    }
}
