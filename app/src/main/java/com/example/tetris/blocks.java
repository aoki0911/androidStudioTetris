package com.example.tetris;


import static com.example.tetris.Draw.blockLenght;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class blocks {
    static int num = 0;
    static int num2=0;

    private static int rotetanum = 0;
    public static final int tBlock = 1;
    public static final int sBlock = 2;
    public static final int iBlock = 3;
    public static final int oBlock = 4;
    public static final int lBlock = 5;
    public static final int jBlock = 6;
    public static final int zBlock = 7;

    static int[][] block = new int[blockLenght][blockLenght];
    static int[][] nowBlock=new int[blockLenght][blockLenght];
    static int[][] nextBlock=new int[blockLenght][blockLenght];
    List<Integer> blockList= Arrays.asList(1,2,3,4,5,6,7);
    List<Integer> blockRandom=new ArrayList<>(blockList);



    public void randomPick(){
        if(blockRandom.size()!=0){
            Collections.shuffle(blockRandom);
        }else{
            blockRandom.addAll(blockList);
        }
    }
    public void blocks(int number) {
        if(number==0){
            num=blockRandom.get(number);
        }

        switch (number) {
            case tBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}
                };
                break;

            case sBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

            case iBlock:
                block = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}
                };
                break;

            case oBlock:
                block = new int[][]{
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

            case lBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 1},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

            case jBlock:
                block = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case zBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 1, 1},
                        {0, 0, 0, 0}
                };
                break;
            default:
                break;
        }
        ;
    }

    public void setNowBlock(){
        blocks(0);
        for(int i=0;i<blockLenght;i++){
            for(int j=0;j<blockLenght;j++){
                nowBlock[i][j]=block[i][j];
            }
        }
        Draw.moveflag = true;
        rotetanum = 0;
    }

    public void setNextBlock(){
        blocks(1);
        for(int i=0;i<blockLenght;i++){
            for(int j=0;j<blockLenght;j++){
                nextBlock[i][j]=block[i][j];
            }
        }
    }

    public void roteta() {
        switch (num) {
            case tBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {0, 1, 1, 1},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 1, 1},
                                {0, 0, 1, 0}
                        };
                        rotetanum++;
                        break;
                    case 2:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 1, 1, 1},
                                {0, 0, 1, 0}
                        };
                        rotetanum++;
                        break;
                    case 3:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 0}
                        };
                        rotetanum = 0;
                        break;
                }
                break;
            case sBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum = 0;
                        break;
                }
                break;
            case iBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {1, 1, 1, 1},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum = 0;
                        break;
                }
                break;
            case oBlock:
                nowBlock = new int[][]{
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case lBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 1},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 0},
                                {0, 0, 1, 0}
                        };
                        rotetanum++;
                        break;
                    case 2:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {1, 1, 1, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 3:
                        nowBlock = new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum = 0;
                        break;
                }
                break;
            case jBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 1, 0, 0},
                                {0, 1, 1, 1},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {0, 1, 1, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 2:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {1, 1, 1, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 3:
                        nowBlock = new int[][]{
                                {0, 0, 1, 0},
                                {0, 0, 1, 0},
                                {0, 1, 1, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum = 0;
                        break;
                }
                break;
            case zBlock:
                switch (rotetanum) {
                    case 0:
                        nowBlock = new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 1},
                                {0, 0, 0, 0}
                        };
                        rotetanum++;
                        break;
                    case 1:
                        nowBlock = new int[][]{
                                {0, 0, 1, 0},
                                {0, 1, 1, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        };
                        rotetanum = 0;
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
