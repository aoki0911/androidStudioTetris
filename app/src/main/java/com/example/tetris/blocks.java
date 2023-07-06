package com.example.tetris;


import static com.example.tetris.Draw.blockLenght;

public class blocks {
    static int num = 0;
    int postnum = num;
    private static int rotetanum = 0;
    public static final int tBlock = 1;
    public static final int sBlock = 2;
    public static final int iBlock = 3;
    public static final int oBlock = 4;
    public static final int lBlock = 5;
    public static final int jBlock = 6;
    public static final int zBlock = 7;

    static int[][] nowBlock = new int[blockLenght][blockLenght];
    int count = 0;

    public void blocks() {
        int prenum = 0;
        if (count == 0) {
            num = (int) (Math.random() * 7) + 1;
            count++;
        } else {
            //ブロックランダム抽選
            prenum = (int) (Math.random() * 7) + 1;
            if (postnum == prenum) {
                while (true) {
                    prenum = (int) (Math.random() * 7) + 1;
                    if (postnum != prenum) {
                        num = prenum;
                        break;
                    }
                }
            } else {
                num = prenum;
            }
        }

        switch (num) {
            case tBlock:
                nowBlock = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}
                };
                break;

            case sBlock:
                nowBlock = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

            case iBlock:
                nowBlock = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}
                };
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
                nowBlock = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 1},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

            case jBlock:
                nowBlock = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case zBlock:
                nowBlock = new int[][]{
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
        MainActivity.moveflag = true;
        rotetanum = 0;
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
