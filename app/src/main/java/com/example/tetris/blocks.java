package com.example.tetris;


import static com.example.tetris.MainActivity.blockLenght;

public class blocks {
    static int num=0;
    public static final int tBlock = 1;
    public static final int sBlock = 2;
    public static final int iBlock = 3;
    public static final int oBlock = 4;
    public static final int lBlock = 5;
    public static final int jBlock = 6;
    public static final int zBlock = 7;

    static int[][] nowBlock=new int[blockLenght][blockLenght];
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
        MainActivity.moveflag=true;
    }
}
