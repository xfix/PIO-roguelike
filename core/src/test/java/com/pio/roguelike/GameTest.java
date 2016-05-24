package com.pio.roguelike;

import static org.junit.Assert.*;

/**
 * Created by arvamer on 5/24/16.
 */
public class GameTest {
    @org.junit.Test
    public void update() throws Exception {
        Game gra = new Game();
        gra.create();
        gra.generateFont();
    }

}