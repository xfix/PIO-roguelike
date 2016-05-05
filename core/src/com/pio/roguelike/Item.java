/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pio.roguelike;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author rafal
 */
public class Rougelike {

    class Item {
        String name;
        float price;
        int id;
    }
    
public class Weapon extends Item{
    float v1;
    float v2;
    float speed;
}
public class Armor extends Item {
    float dmg_reduction;
    
}
    

    public static void main(String[] args) throws FileNotFoundException, YamlException {
       
            YamlReader reader = new YamlReader(new FileReader("Long Sword.yml"));
            Weapon Long_Sword = reader.read(Weapon.class);
            System.out.println(Long_Sword.speed);
           
 
    }
    
}
