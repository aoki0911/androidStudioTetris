package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class hold extends View {
    int [][] holdBlock =new int[Draw.blockLenght][Draw.blockLenght];
    blocks bs=new blocks();
    public hold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void hold(){
        if(holdBlock==null){
            for(int i=0;i<Draw.blockLenght;i++){
                for(int j=0;j<Draw.blockLenght;j++){
                    holdBlock[i][j]=blocks.nowBlock[i][j];
                }
            }
            bs.randomNumbers.remove(0);
        }else{
            int[][] copyNowBlock=new int[Draw.blockLenght][Draw.blockLenght];
            int[][] copyHoldBlock=new int[Draw.blockLenght][Draw.blockLenght];

            for(int i=0;i<Draw.blockLenght;i++){
                for(int j=0;j<Draw.blockLenght;j++){
                    copyNowBlock[i][j]=blocks.nowBlock[i][j];
                    copyHoldBlock[i][j]=holdBlock[i][j];
                }
            }
            for(int i=0;i<Draw.blockLenght;i++){
                for(int j=0;j<Draw.blockLenght;j++){
                    blocks.nowBlock[i][j]=copyHoldBlock[i][j];
                    holdBlock[i][j]=copyNowBlock[i][j];
                }
            }
        }
    }
}
