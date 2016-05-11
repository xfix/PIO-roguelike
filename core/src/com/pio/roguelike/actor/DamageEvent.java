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
public class DamageEvent {
    private final double damage;
    
    public DamageEvent(double damage) {
        this.damage = damage;
    }

    /**
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }
}
