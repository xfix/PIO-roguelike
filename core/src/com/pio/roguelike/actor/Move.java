/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pio.roguelike.actor;

/**
 *
 * @author xfix
 */
public class Move implements Action {
    public int xPosition;
    public int yPosition;
    
    public Move(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
