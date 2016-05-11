package com.pio.roguelike.actor;

import com.pio.roguelike.InputAction;
import com.pio.roguelike.InputListener;
import com.pio.roguelike.Item;
import com.pio.roguelike.map.ASCIIMap;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Actor extends Observable implements InputListener {
    private final ActorSprite sprite;
    private final String name;
    private double hp = 100;
    private int strength = 10;
    private int agility = 10;
    // Use ItemContainer class when implemented
    private final ArrayList<Item> items = new ArrayList<>();
    
    public Actor(ASCIIMap map, String name) {
        sprite = new ActorSprite(map);
        sprite.addObserver((actorSprite, event) -> notifyObservers(event));
        this.name = name;
    }

    @Override
    public void execute(InputAction action) {
        getSprite().execute(action);
    }
    
    public void damage(double damage) {
        if (hp == 0) return;
        setChanged();
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            notifyObservers(new Death());
        } else {
            notifyObservers(new DamageEvent(damage));
        }
    }
    
    public void addItem(Item item) {
        items.add(item);
        setChanged();
        notifyObservers(new ItemObtained(item));
    }

    /**
     * @return the sprite
     */
    public ActorSprite getSprite() {
        return sprite;
    }
}
