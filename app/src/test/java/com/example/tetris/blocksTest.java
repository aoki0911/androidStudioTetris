package com.example.tetris;

import static com.example.tetris.blocks.iBlock;
import static com.example.tetris.blocks.jBlock;
import static com.example.tetris.blocks.lBlock;
import static com.example.tetris.blocks.num;
import static com.example.tetris.blocks.oBlock;
import static com.example.tetris.blocks.sBlock;
import static com.example.tetris.blocks.tBlock;
import static com.example.tetris.blocks.zBlock;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class blocksTest {
    blocks bs=new blocks();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void randomNumber() {
        blocks bs=new blocks();
        bs.randomNumber();
        int sixe=6;
        assertEquals(sixe,bs.randomNumbers.size());
    }

    @Test
    public void blocks() {
    }

    @Test
    public void setNowBlock() {
        bs.randomNumber();
        assertNotNull(bs.randomNumbers.get(0));
    }

    @Test
    public void setNextBlock() {
        bs.randomNumber();
        assertNotNull(bs.randomNumbers.get(1));

    }

    @Test
    public void roteta() {
       bs.randomNumber();
        bs.roteta();
        int[][] block;
        switch (num){
            case tBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case sBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 1, 1},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case iBlock:
                block = new int[][]{
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case oBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case lBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 1},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case jBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            case zBlock:
                block = new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 1, 1},
                        {0, 0, 0, 0}
                };
                assertNotEquals(block,bs.nowBlock);
                break;

            default:
                break;
        }
    }
}